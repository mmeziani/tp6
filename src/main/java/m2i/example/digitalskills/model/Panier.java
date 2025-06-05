// src/main/java/m2i/example/digitalskills/model/Panier.java
package m2i.example.digitalskills.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties("paniers")
    private Client client;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignes = new ArrayList<>();

    @Column(name = "prix_total")
    private BigDecimal prixTotal;

    @Column(name = "date_ajout")
    private LocalDateTime dateAjout;

    public Panier() {
        // Date d’ajout par défaut au moment de la création
        this.dateAjout = LocalDateTime.now();
        this.prixTotal = BigDecimal.ZERO;
    }

    // -------------------- Business Methods --------------------

    public void ajouterLigne(LignePanier ligne) {
        if (ligne == null) return;
        lignes.add(ligne);
        ligne.setPanier(this);
        if (prixTotal == null) {
            prixTotal = BigDecimal.ZERO;
        }
        BigDecimal montantLigne = ligne.getPrixUnitaire()
                .multiply(BigDecimal.valueOf(ligne.getQuantite()));
        prixTotal = prixTotal.add(montantLigne);
    }

    public void supprimerLigne(LignePanier ligne) {
        if (ligne == null) return;
        lignes.remove(ligne);
        ligne.setPanier(null);
        if (prixTotal != null) {
            BigDecimal montantLigne = ligne.getPrixUnitaire()
                    .multiply(BigDecimal.valueOf(ligne.getQuantite()));
            prixTotal = prixTotal.subtract(montantLigne);
        }
    }

    // -------------------- Getters & Setters --------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LignePanier> getLignes() {
        return lignes;
    }

    public void setLignes(List<LignePanier> lignes) {
        this.lignes = lignes;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }
}
