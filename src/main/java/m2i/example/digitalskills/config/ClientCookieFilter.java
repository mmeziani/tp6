package m2i.example.digitalskills.config;

import m2i.example.digitalskills.model.Client;
import m2i.example.digitalskills.repository.ClientRepository;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@Component
public class ClientCookieFilter extends OncePerRequestFilter {

    private final ClientRepository clientRepository;

    public ClientCookieFilter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1) Vérifier si le cookie "clientId" existe
        Long clientId = null;
        if (request.getCookies() != null) {
            Optional<Cookie> maybe = Arrays.stream(request.getCookies())
                    .filter(c -> "clientId".equals(c.getName()))
                    .findFirst();
            if (maybe.isPresent()) {
                try {
                    clientId = Long.parseLong(maybe.get().getValue());
                } catch (NumberFormatException e) {
                    clientId = null;
                }
            }
        }

        // 2) Si non, créer un Client « temporaire » et ajouter cookie
        if (clientId == null) {
            Client nouveauClient = new Client();
            // Vous pouvez, dans l'entité Client, avoir un champ boolean isTemporaire = true
            // pour repérer qu'il s'agit d'un visiteur anonyme.
            // Si votre entité n'a pas ce champ, stockez null name/email pour l'instant.
            Client saved = clientRepository.save(nouveauClient);
            clientId = saved.getId();

            // Créer un cookie "clientId" qui expire dans, par ex., 30 jours
            ResponseCookie cookie = ResponseCookie.from("clientId", clientId.toString())
                    .httpOnly(true)
                    .path("/")
                    .maxAge(Duration.ofDays(30))
                    .sameSite("Lax")
                    .build();
            response.addHeader("Set-Cookie", cookie.toString());
        }

        // 3) On peut stocker cet ID dans un attribut de la requête pour y accéder plus tard
        request.setAttribute("clientId", clientId);

        // Continuer la chaîne de filtres
        filterChain.doFilter(request, response);
    }
}
