package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**")
//        .allowedMethods("*")
//        .allowedOrigins("*")
//        .allowedHeaders("*")
//        .allowCredentials(false)
//        .maxAge(-1);
//  }
//}
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig{

	@Bean
	public WebMvcConfigurer corsConfigurer() {
	   return new WebMvcConfigurer() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	         registry.addMapping("/**")
	         .allowedOrigins("*")
	         .allowedMethods("GET", "POST", "PUT", "DELETE")
	         .allowedHeaders("*");
	      }    
	   };
	}
}

//@Configuration
//@EnableWebMvc
//public class CorsConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//            .allowedOrigins("*")
//            .allowedMethods("GET", "POST", "PUT", "DELETE")
//            .allowedHeaders("*")
//            .exposedHeaders("*")
//            .allowCredentials(false).maxAge(3600);
//    }
//}



