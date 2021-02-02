package com.katalogus.project.controller;

import com.katalogus.project.entity.Turnus;
import com.katalogus.project.service.TurnusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnus")
public class TurnusController {

    @Autowired
    TurnusProvider turnusService;

    @GetMapping("/all")
    public List<Turnus> getAllTurnus() {
        return turnusService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity saveNewTurnus(@RequestBody Turnus turnus) {
        Boolean successful = turnusService.saveNewTurnus(turnus);
        if (successful) {
            return ResponseEntity.ok("New Turnus created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{turnus_id}/update")
    public ResponseEntity updateTurnusById(@RequestBody Turnus turnus, @PathVariable("turnus_id") Long turnusId) {
        Boolean successful = turnusService.updateTurnusById(turnus, turnusId);
        if (successful) {
            return ResponseEntity.ok("Turnus updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{turnus_id}/delete")
    public void deleteTurnusById(@PathVariable("turnus_id") Long turnusId) {
        turnusService.deleteTurnusById(turnusId);
    }
}
