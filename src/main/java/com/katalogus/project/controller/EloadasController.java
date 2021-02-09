package com.katalogus.project.controller;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.service.ClassesProvider;
import com.katalogus.project.service.EloadasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eloadas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EloadasController {

    @Autowired
    EloadasProvider eloadasProvider;

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/{eloadas_id}")
    public Eloadas getEloadasById(@PathVariable("eloadas_id") Long eloadasId) {
        return eloadasProvider.getEloadasById(eloadasId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> saveNewEloadas(@RequestBody Eloadas eloadas) {
        Boolean successful = eloadasProvider.saveNewEloadas(eloadas);
        if (successful) {
            return ResponseEntity.ok("New Eloadas created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{eloadas_id}/update")
    public ResponseEntity<String> updateEloadasById(@RequestBody Eloadas eloadas, @PathVariable("eloadas_id") Long eloadasId) {
        Boolean successful = eloadasProvider.updateEloadasById(eloadas, eloadasId);
        if (successful) {
            return ResponseEntity.ok("Eloadas updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{eloadas_id}/delete")
    public ResponseEntity<String> deleteEloadasById(@PathVariable("eloadas_id") Long eloadasId) {
        Boolean successful = eloadasProvider.deleteEloadasById(eloadasId);
        if (successful) {
            return ResponseEntity.ok("Eloadas deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @PutMapping("/{eloadas_id}/change_status")
    public ResponseEntity<String> changeActiveStatusById(@PathVariable("eloadas_id") Long eloadasId) {
        Boolean successful = eloadasProvider.changeActiveStatusById(eloadasId);
        if (successful) {
            return ResponseEntity.ok("Eloadas's status changed successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/{eloadas_id}/openClass")
    public String openClassForAttendance(@PathVariable("eloadas_id") Long eloadasId) {
        return eloadasProvider.openClassForAttendace(eloadasId);
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @PostMapping("/{eloadas_id}/closeClass")
    public ResponseEntity<String> closeClassForAttendance(@PathVariable("eloadas_id") Long eloadasId) {
        Boolean successful = eloadasProvider.closeClassForAttendace(eloadasId);
        if (successful) {
            return ResponseEntity.ok("Eloadas closed successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
