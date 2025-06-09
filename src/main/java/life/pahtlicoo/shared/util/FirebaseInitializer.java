package life.pahtlicoo.shared.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;

import com.google.cloud.ServiceOptions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Startup
public class FirebaseInitializer {
    @ConfigProperty(name = "SECRET_ID")
    String secretId;

    @PostConstruct
    public void init() throws IOException {
        if(FirebaseApp.getApps().isEmpty()){
            try {
                try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
                    SecretVersionName secretVersionName = SecretVersionName.of(ServiceOptions.getDefaultProjectId(), secretId, "latest");
                    AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);
                    String secretJson = response.getPayload().getData().toStringUtf8();

                    InputStream serviceAccountStream = new ByteArrayInputStream(secretJson.getBytes());

                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                            .build();

                    FirebaseApp.initializeApp(options);
                    System.out.println("Firebase initialized successfully");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize Firebase", e);
            }
        } else {
            System.out.println("Firebase already initialized");
        }
    }
}