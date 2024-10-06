package com.springProject.journalApp.Controller;

import com.springProject.journalApp.Entity.JournalEntry;
import com.springProject.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createEntry(@RequestBody JournalEntry myEntry)
    {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity(myEntry,HttpStatus.CREATED);
        }
         catch (Exception e) {
             return new ResponseEntity(myEntry,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/DELETE/{ID}")
    public boolean deleteById(@PathVariable ObjectId ID)
    {
        journalEntryService.deleteById(ID);
        return true;
    }
    @GetMapping("/GET/{ID}")
    public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId ID)
    {
        Optional<JournalEntry> myEntry= journalEntryService.findById(ID);
        if(myEntry.isPresent())
        {
            return new ResponseEntity<>(myEntry.get(), HttpStatus.OK);
        }
        else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }
    @PutMapping("/POST/{ID}")
    public JournalEntry updateById(@PathVariable ObjectId ID, @RequestBody JournalEntry updatedEntry)
    {
        return journalEntryService.updateById(ID,updatedEntry);
    }
}
