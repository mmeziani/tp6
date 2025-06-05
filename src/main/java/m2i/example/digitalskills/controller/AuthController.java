package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.dto.AuthRequest;
import m2i.example.digitalskills.dto.AuthResponse;
import m2i.example.digitalskills.model.Utilisateur;
import m2i.example.digitalskills.repository.UtilisateurRepository;
import m2i.example.digitalskills.service.Imp.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        if (!new BCryptPasswordEncoder().matches(authRequest.getPassword(), utilisateur.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        String token = jwtService.generateToken(utilisateur.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, utilisateur));
    }
}
