package com.utkarshshaning.journalApp.service;

import com.utkarshshaning.journalApp.entity.UserEntry;
import com.utkarshshaning.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

//    @Autowired
//    private UserEntryRepository userEntryRepository;

    @Test
    public void testFindByUserName() {
//        UserEntry userEntry = userEntryRepository.findByUsername("srishti");
//        assertFalse(userEntry.getJournalEntries().isEmpty(),
//                "Expected journal entries for user 'srishti'");
//        assertNotNull(userEntryRepository.findByUsername("Rajesh"));
//        assertEquals(4,2+2);
//        assertTrue(5>4);

    }
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,3,5",
            "1,2,6"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
