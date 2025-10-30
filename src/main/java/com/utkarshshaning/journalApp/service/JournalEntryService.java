package com.utkarshshaning.journalApp.service;

import com.utkarshshaning.journalApp.entity.JournalEntry;
import com.utkarshshaning.journalApp.entity.UserEntry;
import com.utkarshshaning.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        UserEntry userEntry = userEntryService.findByUserName(username);

        // ADD THIS NULL CHECK
        if (userEntry == null) {
            throw new RuntimeException("User not found: " + username);
        }

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        userEntry.getJournalEntries().add(saved);
        userEntryService.saveEntry(userEntry);
    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findbyID(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public void deletebyID(ObjectId id, String username){
        try {
            UserEntry userEntry = userEntryService.findByUserName(username);

            if (userEntry == null) {
                throw new RuntimeException("User not found: " + username);
            }
            userEntry.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userEntryService.saveEntry(userEntry);
            journalEntryRepository.deleteById(id);
            System.out.println("Successfully deleted journal entry: " + id);
        } catch (Exception e) {
            System.err.println("Error deleting journal entry: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
