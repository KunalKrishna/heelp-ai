package edu.unc.cs.heelp_ai.config;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

/**
 * This class checks for Google Cloud credentials.
 * 
 * DISABLED for Ollama switch.
 * To re-enable for Vertex AI:
 * 1. Uncomment @Component
 * 2. Uncomment Dependency
 * 3. Implement CommandLineRunner
 */
// @Component
public class CredentialCheck { // implements CommandLineRunner {

    // @Override
    public void run(String... args) {
        System.out.println("----------------------------------------");
        System.out.println("🔍 TESTING GOOGLE CLOUD CREDENTIALS...");
        System.out.println("----------------------------------------");

        try {
            // 1. Load credentials exactly how Spring AI does it
            // GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();

            // // 2. ADD SCOPE
            // if (credentials.createScopedRequired()) {
            // credentials =
            // credentials.createScoped("https://www.googleapis.com/auth/cloud-platform",
            // "https://www.googleapis.com/auth/cloud-platform.read-only");
            // }

            // // 3. Now try to refresh
            // credentials.refreshIfExpired();

            // System.out.println("✅ SUCCESS! Credentials Valid & Active.");

            // if (credentials instanceof ServiceAccountCredentials) {
            // System.out.println("🆔 Identity: " + ((ServiceAccountCredentials)
            // credentials).getClientEmail());
            // System.out.println("📂 Source: JSON Key File (Docker Ready)");
            // }

        } catch (Exception e) {
            System.err.println("❌ FAILED: " + e.getMessage());
        }

        System.out.println("----------------------------------------");
    }
}