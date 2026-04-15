package com.nikhil.jobtracker.repository;

import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.Note;
import com.nikhil.jobtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByJob(Job job);

}
