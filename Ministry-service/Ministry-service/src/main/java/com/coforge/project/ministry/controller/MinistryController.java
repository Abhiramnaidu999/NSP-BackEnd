package com.coforge.project.ministry.controller;

import java.util.Map;

/**
*Author:Koppula.Reddy
*date:Dec 14, 2024
*time:12:10:50 PM
*project:Ministry-service

**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.project.ministry.model.Ministry;
import com.coforge.project.ministry.services.MinistryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ministry")
public class MinistryController {

    @Autowired
    private MinistryService ministryService;

    @PostMapping("/register")
    public ResponseEntity<Ministry> registerMinistry(@RequestBody Ministry ministry) {
        Ministry savedMinistry = ministryService.registerMinistry(ministry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMinistry);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginMinistry(@RequestBody Ministry ministry) {
        Ministry authenticatedMinistry = ministryService.authenticate(ministry.getEmail(), ministry.getPassword());
        if (authenticatedMinistry != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    @PostMapping("/ministry/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
        String username = request.get("email");
        String newPassword = request.get("newPassword");
        boolean updated = ministryService.updatePassword(username, newPassword);
        if (updated) {
            return ResponseEntity.ok("Password updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
    }
 
 
}

