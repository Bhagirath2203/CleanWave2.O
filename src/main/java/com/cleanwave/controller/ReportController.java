package com.cleanwave.controller;

import com.cleanwave.dto.ReportRequest;
import com.cleanwave.model.Report;
import com.cleanwave.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    
    @PostMapping
    public ResponseEntity<Report> createReport(@Valid @RequestBody ReportRequest request, Authentication auth) {
        String userEmail = auth.getName();
        Report report = reportService.createReport(request, userEmail);
        return ResponseEntity.ok(report);
    }
    
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }
    
    @GetMapping("/my-reports")
    public ResponseEntity<List<Report>> getMyReports(Authentication auth) {
        String userEmail = auth.getName();
        return ResponseEntity.ok(reportService.getReportsByUser(userEmail));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable String id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }
}
