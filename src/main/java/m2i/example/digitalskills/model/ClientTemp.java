package m2i.example.digitalskills.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_temp")
public class ClientTemp {

    @Id
    private String clientId;  // correspond à VARCHAR(255) PRIMARY KEY

    private LocalDateTime dateCreation; // correspond à TIMESTAMP

    // Constructeurs
    public ClientTemp() {}

    public ClientTemp(String clientId) {
        this.clientId = clientId;
        this.dateCreation = LocalDateTime.now();
    }

    // Getters & Setters
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
