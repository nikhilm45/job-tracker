package com.nikhil.jobtracker.service;

import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.Note;
import com.nikhil.jobtracker.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note addNote(Note note, Job job) {
        note.setJob(job);
        note.setCreatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public List<Note> getNotesByJob(Job job) {
        return noteRepository.findByJob(job);
    }
}
