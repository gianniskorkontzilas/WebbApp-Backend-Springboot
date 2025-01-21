package Knowledge.WebApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Authorization");
    }

}

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*") // Επιτρέπει όλα τα origins για δοκιμές
//                .allowedMethods("*") // Επιτρέπει όλα τα methods
//                .allowedHeaders("*") // Επιτρέπει όλα τα headers
//                .allowCredentials(true) // Για cookies και headers
//                .exposedHeaders("Authorization");
//        System.out.println("CORS configuration applied successfully!");
//    }
//}