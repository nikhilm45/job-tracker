package com.nikhil.jobtracker.controller;

import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.Note;
import com.nikhil.jobtracker.repository.JobRepository;
import com.nikhil.jobtracker.repository.NoteRepository;
import com.nikhil.jobtracker.service.JobService;
import com.nikhil.jobtracker.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private JobService jobService;

    @PostMapping
    public Note addNote(@RequestBody Note note, @RequestParam Long jobId) {
        Job job = jobService.getJobById(jobId); // you need to create this
        return noteService.addNote(note, job);
    }

    @GetMapping("/{jobId}")
    public List<Note> getNotesByJob(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);
        return noteService.getNotesByJob(job);
    }
}
