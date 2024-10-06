package com.springProject.journalApp.Service;

import com.springProject.journalApp.Entity.JournalEntry;
import com.springProject.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    public JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public void saveEntry(JournalEntry myEntry) {
        journalEntryRepository.save(myEntry);
    }

    public void deleteById(ObjectId ID) {
        journalEntryRepository.deleteById(ID);
    }

    public Optional<JournalEntry> findById(ObjectId ID) {
        return journalEntryRepository.findById(ID);
    }

    public JournalEntry updateById(ObjectId ID, JournalEntry updatedEntry) {
        JournalEntry oldEntry = findById(ID).orElse(null);
        if (oldEntry != null) {
            oldEntry.setName(updatedEntry.getName() != null && !updatedEntry.getName().equals("") ? updatedEntry.getName() : oldEntry.getName());
            oldEntry.setDescription(updatedEntry.getDescription() != null && !updatedEntry.getDescription().equals("") ? updatedEntry.getDescription() : oldEntry.getDescription());
        }
        saveEntry(oldEntry);
        return oldEntry;
    }
}
