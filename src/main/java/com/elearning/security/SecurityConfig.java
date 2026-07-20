//package com.elearning.security;
//
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//
//@Configuration
//@EnableMethodSecurity
//public class SecurityConfig {
//
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
//
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//
//    }
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//
//
//        http
//            .csrf(csrf -> csrf.disable())
//
//
//            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//
//
//            .authorizeHttpRequests(auth -> auth
//
//            	    .requestMatchers(
//            	        "/api/auth/**",
//            	        "/swagger-ui/**",
//            	        "/swagger-ui.html",
//            	        "/v3/api-docs/**",
//            	        "/swagger-resources/**",
//            	        "/webjars/**"
//            	    )
//            	    .permitAll()
//
//
//            	    .requestMatchers(
//            	        "/api/courses/**",
//            	        "/api/enrollments/**"
//            	    )
//            	    .authenticated()
//
//
//            	    .requestMatchers(
//            	        org.springframework.http.HttpMethod.OPTIONS,
//            	        "/**"
//            	    )
//            	    .permitAll()
//
//
//            	    .anyRequest()
//            	    .authenticated()
//
//            	)
//            .addFilterBefore(
//                jwtAuthenticationFilter,
//                UsernamePasswordAuthenticationFilter.class
//            );
//
//
//        return http.build();
//
//    }
//
//
//
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource(){
//
//
//        CorsConfiguration configuration = new CorsConfiguration();
//
//
//        configuration.setAllowedOrigins(
//            List.of("http://localhost:5173")
//        );
//
//
//        configuration.setAllowedMethods(
//            List.of(
//                "GET",
//                "POST",
//                "PUT",
//                "DELETE",
//                "OPTIONS"
//            )
//        );
//
//
//        configuration.setAllowedHeaders(
//            List.of("*")
//        );
//
//
//        configuration.setAllowCredentials(true);
//
//
//
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//
//
//        source.registerCorsConfiguration(
//            "/**",
//            configuration
//        );
//
//
//        return source;
//
//    }
//
//
//}

package com.elearning.security;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {


        http

            .csrf(csrf -> csrf.disable())


            .cors(cors -> 
                cors.configurationSource(corsConfigurationSource())
            )


            .authorizeHttpRequests(auth -> auth



                // ==========================
                // PUBLIC APIs
                // ==========================

                .requestMatchers(
                        "/api/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**"
                )
                .permitAll()



                // ==========================
                // COURSE APIs
                // ==========================


                // Anyone logged in can view courses
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/courses/**"
                )
                .authenticated()



                // Only ADMIN can create course
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/courses/**"
                )
                .hasRole("ADMIN")



                // Only ADMIN can update course
                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/courses/**"
                )
                .hasRole("ADMIN")



                // Only ADMIN can delete course
                .requestMatchers(
                        HttpMethod.DELETE,
                        "/api/courses/**"
                )
                .hasRole("ADMIN")





                // ==========================
                // ENROLLMENT APIs
                // ==========================



                // Student enroll course
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/enrollments/add"
                )
                .hasRole("STUDENT")



                // Student My Courses
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/enrollments/my-courses"
                )
                .hasRole("STUDENT")



                // Admin view all enrollments
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/enrollments/all"
                )
                .hasRole("ADMIN")





                // OPTIONS request for CORS
                .requestMatchers(
                        HttpMethod.OPTIONS,
                        "/**"
                )
                .permitAll()



                // Everything else requires login
                .anyRequest()
                .authenticated()

            )


            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );



        return http.build();

    }







    @Bean
    public CorsConfigurationSource corsConfigurationSource(){


        CorsConfiguration configuration =
                new CorsConfiguration();



        configuration.setAllowedOrigins(
                List.of(
                    "http://localhost:5173"
                )
        );



        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );



        configuration.setAllowedHeaders(
                List.of("*")
        );



        configuration.setAllowCredentials(true);



        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();



        source.registerCorsConfiguration(
                "/**",
                configuration
        );


        return source;

    }


}