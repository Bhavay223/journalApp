package com.springProject.journalApp.Controller;

import com.springProject.journalApp.Entity.JournalEntry;
import com.springProject.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;
    @GetMapping("/GET")
    public List<JournalEntry> getAll()
    {
        List<JournalEntry> js = journalEntryService.getAll();
       return js;
    }
    @PostMapping("/POST")
    public boolean createEntry(@RequestBody JournalEntry myEntry)
    {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }
    @DeleteMapping("/DELETE/{ID}")
    public boolean deleteById(@PathVariable ObjectId ID)
    {
        journalEntryService.deleteById(ID);
        return true;
    }
    @GetMapping("/GET/{ID}")
    public Optional<JournalEntry> findById(@PathVariable ObjectId ID)
    {
        return journalEntryService.findById(ID);
    }
    @PutMapping("/POST/{ID}")
    public JournalEntry updateById(@PathVariable ObjectId ID, @RequestBody JournalEntry updatedEntry)
    {
        return journalEntryService.updateById(ID,updatedEntry);
    }
}
