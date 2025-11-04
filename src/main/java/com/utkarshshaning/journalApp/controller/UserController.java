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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

@Autowired
    private UserEntryService userEntryService;

@GetMapping
    public List<UserEntry> getAlluser(){
    return userEntryService.getAll();
}

@PostMapping
    public void createUser(@RequestBody UserEntry userEntry){
    userEntryService.saveEntry(userEntry);
}

@PutMapping("/{username}")
    public ResponseEntity<UserEntry> updateUser(@RequestBody UserEntry userEntry,@PathVariable String username){
    UserEntry userINDB = userEntryService.findByUserName(username);
    if(userINDB!=null){
        userINDB.setUsername(userEntry.getUsername());
        userINDB.setPasssword(userEntry.getPasssword());
        userEntryService.saveEntry(userINDB);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
