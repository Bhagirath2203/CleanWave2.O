package com.cleanwave.repository;

import com.cleanwave.model.Report;
import com.cleanwave.model.Report.ReportStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByStatus(ReportStatus status);
    List<Report> findByBy(String by);
    List<Report> findByAssignedToId(String workerId);
    List<Report> findByCategory(String category);
}
