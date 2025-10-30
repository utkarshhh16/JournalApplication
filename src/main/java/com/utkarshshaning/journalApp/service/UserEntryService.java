package com.utkarshshaning.journalApp.service;

import com.utkarshshaning.journalApp.entity.UserEntry;
import com.utkarshshaning.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveEntry(UserEntry userEntry){
        userEntryRepository.save(userEntry);
    }
    public List<UserEntry> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<UserEntry> findbyID(ObjectId id) {
        return userEntryRepository.findById(id);
    }
    public void deletebyID(ObjectId id){
        userEntryRepository.deleteById(id);
    }
    public UserEntry findByUserName(String username){
        return userEntryRepository.findByUsername(username);

    }
}
