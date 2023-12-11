package com.nan.weaccessglobal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
public class WeAccessGlobalApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeAccessGlobalApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Angel")
                    .lastname("Ofalsa")
                    .email("angelcmofalsa@gmail.com")
                    .password("#xq01du1nochain")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());


        };
    }*/

}
