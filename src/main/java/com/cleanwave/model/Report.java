package com.cleanwave.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "reports")
public class Report {

    @Id
    private String id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;

    private String category;
    private String description;
    private Location location;
    private String imageDataUrl;

    private String createdBy;   // reporter email
    private AssignedWorker assignedTo;

    private ReportStatus status;

    public enum ReportStatus {
        OPEN("Open"),
        IN_PROGRESS("In Progress"),
        CLOSED("Closed");

        private final String displayName;

        ReportStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
