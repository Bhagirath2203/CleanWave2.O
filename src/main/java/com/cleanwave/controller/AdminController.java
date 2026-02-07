package com.cleanwave.controller;

import com.cleanwave.model.Report;
import com.cleanwave.model.User;
import com.cleanwave.service.ReportService;
import com.cleanwave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }
    
    @GetMapping("/workers")
    public ResponseEntity<List<User>> getAllWorkers() {
        return ResponseEntity.ok(userService.getAllWorkers());
    }
    
    @PutMapping("/reports/{id}/assign")
    public ResponseEntity<?> assignWorker(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            String workerEmail = body.get("workerEmail");
            Report report = reportService.assignWorker(id, workerEmail);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/reports/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            String status = body.get("status");
            Report report = reportService.updateStatus(id, status);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
