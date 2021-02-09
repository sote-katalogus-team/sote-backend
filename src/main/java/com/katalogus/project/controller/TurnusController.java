package com.katalogus.project.controller;

import com.katalogus.project.entity.Turnus;
import com.katalogus.project.service.TurnusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnus")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TurnusController {

    @Autowired
    TurnusProvider turnusService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Turnus> getAllTurnus() {
        return turnusService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String>  saveNewTurnus(@RequestBody Turnus turnus) {
        Boolean successful = turnusService.saveNewTurnus(turnus);
        if (successful) {
            return ResponseEntity.ok("New Turnus created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{turnus_id}/update")
    public ResponseEntity<String>  updateTurnusById(@RequestBody Turnus turnus, @PathVariable("turnus_id") Long turnusId) {
        Boolean successful = turnusService.updateTurnusById(turnus, turnusId);
        if (successful) {
            return ResponseEntity.ok("Turnus updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{turnus_id}/delete")
    public ResponseEntity<String> deleteTurnusById(@PathVariable("turnus_id") Long turnusId) {
        Boolean successful = turnusService.deleteTurnusById(turnusId);
        if (successful) {
            return ResponseEntity.ok("Turnus deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
