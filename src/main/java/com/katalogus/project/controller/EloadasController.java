package com.katalogus.project.controller;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.service.ClassesProvider;
import com.katalogus.project.service.EloadasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eloadas")
public class EloadasController {

    @Autowired
    EloadasProvider eloadasProvider;

    @PostMapping("/add")
    public ResponseEntity saveNewEloadas(@RequestBody Eloadas eloadas) {
        Boolean successful = eloadasProvider.saveNewEloadas(eloadas);
        if (successful) {
            return ResponseEntity.ok("New Eloadas created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{eloadas_id}/update")
    public ResponseEntity updateEloadasById(@RequestBody Eloadas eloadas, @PathVariable("eloadas_id") Long eloadasId) {
        Boolean successful = eloadasProvider.updateEloadasById(eloadas, eloadasId);
        if (successful) {
            return ResponseEntity.ok("Eloadas updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{eloadas_id}/delete")
    public void deleteEloadasById(@PathVariable("eloadas_id") Long eloadasId) {
        eloadasProvider.deleteEloadasById(eloadasId);
    }
}
