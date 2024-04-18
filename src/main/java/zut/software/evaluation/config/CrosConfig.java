package zut.software.evaluation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")

                .allowCredentials(true)

                .allowedOriginPatterns("*")

                .allowedMethods("GET", "POST", "DELETE", "PUT")

                .maxAge(3600);
    }
}
