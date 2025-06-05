package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Client;
import m2i.example.digitalskills.model.Commande;
import m2i.example.digitalskills.model.LigneCommande;
import m2i.example.digitalskills.model.LignePanier;
import m2i.example.digitalskills.model.Panier;
import m2i.example.digitalskills.model.Produit;
import m2i.example.digitalskills.repository.ProduitRepository;
import m2i.example.digitalskills.service.CommandeService;
import m2i.example.digitalskills.service.PanierService;
import m2i.example.digitalskills.repository.ClientRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CheckoutController {


}
