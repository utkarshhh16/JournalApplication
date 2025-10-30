package com.utkarshshaning.journalApp.controller;

import com.utkarshshaning.journalApp.entity.JournalEntry;
import com.utkarshshaning.journalApp.entity.UserEntry;
import com.utkarshshaning.journalApp.service.JournalEntryService;
import com.utkarshshaning.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesofUSers(@PathVariable String username) {
        UserEntry userEntry = userEntryService.findByUserName(username);
        List<JournalEntry> all = userEntry.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return ResponseEntity.ok(userEntry.getJournalEntries());
    }


    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String username){
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.OK);
    }

    @GetMapping("/id/{myID}")
    public ResponseEntity<JournalEntry> getjournalentrybyid(@PathVariable ObjectId myID) {
        Optional<JournalEntry> journalEntry = journalEntryService.findbyID(myID);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{myID}")
    public ResponseEntity<JournalEntry> deletejournalentrybyid(@PathVariable ObjectId myID,@PathVariable String username){
        journalEntryService.deletebyID(myID,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{username}/id/{myID}")
    public ResponseEntity<JournalEntry> updatejournalentrybyid(@PathVariable String username, @PathVariable ObjectId myID, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.findbyID(myID).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old,username);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
