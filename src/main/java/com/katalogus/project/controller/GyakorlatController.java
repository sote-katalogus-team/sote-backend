package com.katalogus.project.controller;

import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.service.ClassesProvider;
import com.katalogus.project.service.GyakorlatProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gyakorlat")
public class GyakorlatController {

    @Autowired
    GyakorlatProvider gyakorlatProvider;

    @PostMapping("/add")
    public ResponseEntity saveNewGyakorlat(@RequestBody Gyakorlat gyakorlat) {
        Boolean successful = gyakorlatProvider.saveNewGyakorlat(gyakorlat);
        if (successful) {
            return ResponseEntity.ok("New Gyakorlat created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{gyakorlat_id}/update")
    public ResponseEntity updateGyakorlatById(@RequestBody Gyakorlat gyakorlat, @PathVariable("gyakorlat_id") Long gyakorlatId) {
        Boolean successful = gyakorlatProvider.updateGyakorlatById(gyakorlat, gyakorlatId);
        if (successful) {
            return ResponseEntity.ok("Gyakorlat updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{gyakorlat_id}/delete")
    public void deleteGyakorlatById(@PathVariable("gyakorlat_id") Long gyakorlatId) {
        gyakorlatProvider.deleteGyakorlatById(gyakorlatId);
    }
}
