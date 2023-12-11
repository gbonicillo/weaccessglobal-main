package com.nan.weaccessglobal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nan.weaccessglobal.dto.request.AuthenticationRequest;
import com.nan.weaccessglobal.dto.request.RegisterRequest;
import com.nan.weaccessglobal.dto.response.AuthenticationResponse;
import com.nan.weaccessglobal.model.Token;
import com.nan.weaccessglobal.model.User;
import com.nan.weaccessglobal.model.Ambassador;
import com.nan.weaccessglobal.model.AmbassadorAddress;
import com.nan.weaccessglobal.model.interfaces.Role;
import com.nan.weaccessglobal.model.interfaces.TokenType;
import com.nan.weaccessglobal.repository.AmbassadorAddressRepository;
import com.nan.weaccessglobal.repository.AmbassadorRepository;
import com.nan.weaccessglobal.repository.TokenRepository;
import com.nan.weaccessglobal.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final AmbassadorRepository ambassadorRepository;

    private final AmbassadorAddressRepository ambassadorAddressRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf("AMBASSADOR"))
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        var ambassadorAddress = AmbassadorAddress.builder()
                .city(request.getCity())
                .state(request.getState())
                .street1(request.getStreet1())
                .street2(request.getStreet2())
                .postCode(request.getPostCode())
                .build();
        var ambassador  = Ambassador.builder()
                .abn(String.valueOf(request.getAbn()))
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .contactNumber(request.getContactNumber())
                .birthDate(request.getBirthDate())
                .user(user)
                .ambAddress(ambassadorAddress)
                .build();
        ambassadorAddressRepository.save(ambassadorAddress);
        ambassadorRepository.save(ambassador);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .ambId(user.getId())
                .isLoggedIn(true)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
