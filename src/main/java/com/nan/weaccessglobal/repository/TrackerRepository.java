package com.nan.weaccessglobal.repository;

import com.nan.weaccessglobal.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TrackerRepository extends JpaRepository<Lead, Long> {

    @Query("FROM Lead l inner join Ambassador a on a.ambassadorId = l.ambassador.ambassadorId where l.ambassador.ambassadorId = ?1")
    List<Lead> findByAmbassador(Integer ambId);


}
