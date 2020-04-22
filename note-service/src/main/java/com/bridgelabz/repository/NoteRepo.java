package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.entity.Note;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long>{

}
