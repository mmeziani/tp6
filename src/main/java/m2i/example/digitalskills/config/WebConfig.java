package m2i.example.digitalskills.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Le chemin d'accès aux images depuis l'extérieur
        registry.addResourceHandler("/uploads/**")
                // Le répertoire réel où sont stockées les images
                .addResourceLocations("file:uploads/");
    }
}
