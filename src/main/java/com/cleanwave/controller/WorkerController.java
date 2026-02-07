package com.cleanwave.controller;

import com.cleanwave.model.Report;
import com.cleanwave.model.User;
import com.cleanwave.service.ReportService;
import com.cleanwave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('WORKER')")
public class WorkerController {
    
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/my-assignments")
    public ResponseEntity<List<Report>> getMyAssignments(Authentication auth) {
        User worker = userService.getUserByEmail(auth.getName());
        return ResponseEntity.ok(reportService.getReportsByWorker(worker.getId()));
    }
    
    @PutMapping("/reports/{id}/status")
    public ResponseEntity<Report> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        Report report = reportService.updateStatus(id, status);
        return ResponseEntity.ok(report);
    }
}
