package com.cleanwave.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {
    
    private Map<String, String> responses;
    
    public ChatbotService() {
        initializeResponses();
    }
    
    private void initializeResponses() {
        responses = new HashMap<>();
        
        // Greeting responses
        responses.put("hello", "Hello! I'm CleanWave AI Assistant. I can help you with:\n" +
                "1. Submitting civic issue reports\n" +
                "2. Understanding report categories\n" +
                "3. Tracking your report status\n\n" +
                "How can I assist you today?");
        
        responses.put("hi", "Hi there! How can I help you report civic issues today?");
        
        // Report submission
        responses.put("report", "To submit a report:\n" +
                "1. Click the 'Report New Issue' button\n" +
                "2. Select the category (Road Damage, Waste Management, etc.)\n" +
                "3. Add a detailed description\n" +
                "4. Upload a photo (optional but recommended)\n" +
                "5. Confirm your location\n" +
                "6. Submit!\n\n" +
                "Your report will be reviewed by our team within 24 hours.");
        
        // Categories
        responses.put("category", "We handle these categories:\n\n" +
                "🚗 Road Damage - Potholes, cracks, broken roads\n" +
                "🗑️ Waste Management - Garbage collection, littering\n" +
                "💡 Street Light - Non-functional lights\n" +
                "💧 Water Supply - Leaks, no water, quality issues\n" +
                "🌊 Drainage - Blocked drains, flooding\n" +
                "📝 Other - Any other civic issues");
        
        // Status tracking
        responses.put("status", "You can track your reports on the dashboard. Each report has a status:\n\n" +
                "🟡 Open - Just submitted, awaiting review\n" +
                "🔵 In Progress - Assigned to a worker, being resolved\n" +
                "🟢 Closed - Issue resolved\n\n" +
                "You'll receive email notifications for status updates!");
        
        // Photo upload
        responses.put("photo", "Photos are very important! They help us:\n" +
                "✓ Verify the issue quickly\n" +
                "✓ Assess the severity\n" +
                "✓ Assign the right team\n" +
                "✓ Document the problem\n\n" +
                "Please upload clear photos showing the issue from different angles if possible.");
        
        // Location
        responses.put("location", "Location tagging helps us:\n" +
                "✓ Find the exact problem spot\n" +
                "✓ Send the right workers\n" +
                "✓ Track issues in your area\n\n" +
                "The app will automatically capture your location when you submit a report. " +
                "Please ensure location services are enabled!");
        
        // Help
        responses.put("help", "I can help you with:\n\n" +
                "📝 How to submit a report\n" +
                "📂 Available categories\n" +
                "📊 Tracking report status\n" +
                "📸 Photo upload guidelines\n" +
                "📍 Location tagging\n" +
                "📧 Email notifications\n\n" +
                "What would you like to know more about?");
        
        // Default response
        responses.put("default", "I'm here to help you report and track civic issues. " +
                "You can ask me about:\n" +
                "- How to submit a report\n" +
                "- Available categories\n" +
                "- Tracking status\n" +
                "- Photo uploads\n\n" +
                "What would you like to know?");
    }
    
    public String getResponse(String query) {
        if (query == null || query.trim().isEmpty()) {
            return responses.get("default");
        }
        
        String lowerQuery = query.toLowerCase().trim();
        
        // Check for exact matches first
        for (Map.Entry<String, String> entry : responses.entrySet()) {
            if (lowerQuery.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        
        // Pattern matching
        if (lowerQuery.contains("submit") || lowerQuery.contains("create") || lowerQuery.contains("new")) {
            return responses.get("report");
        } else if (lowerQuery.contains("track") || lowerQuery.contains("check") || lowerQuery.contains("progress")) {
            return responses.get("status");
        } else if (lowerQuery.contains("categor") || lowerQuery.contains("type") || lowerQuery.contains("kind")) {
            return responses.get("category");
        } else if (lowerQuery.contains("photo") || lowerQuery.contains("image") || lowerQuery.contains("picture")) {
            return responses.get("photo");
        } else if (lowerQuery.contains("location") || lowerQuery.contains("gps") || lowerQuery.contains("address")) {
            return responses.get("location");
        }
        
        return responses.get("default");
    }
}
