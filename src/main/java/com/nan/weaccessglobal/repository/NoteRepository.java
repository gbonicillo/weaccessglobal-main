package com.nan.weaccessglobal.repository;

import com.nan.weaccessglobal.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Notes,Integer> {


}
