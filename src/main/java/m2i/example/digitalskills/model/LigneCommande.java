// src/main/java/m2i/example/digitalskills/model/LigneCommande.java
package m2i.example.digitalskills.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prix_unitaire")
    private BigDecimal prixUnitaire;

    private Integer quantité;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    @JsonIgnoreProperties("lignesCommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    @JsonIgnoreProperties("ligneCommandes")
    private Produit produit;

    public LigneCommande() { }

    // -------------------- Getters & Setters --------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getQuantité() {
        return quantité;
    }

    public void setQuantité(Integer quantité) {
        this.quantité = quantité;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
