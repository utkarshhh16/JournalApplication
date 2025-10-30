package com.utkarshshaning.journalApp.repository;

import com.utkarshshaning.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUsername(String username);
}
