/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
@Configuration
//@EnableWebMvcSecurity
@ImportResource("classpath:br/com/valecard/config/security.xml")
public class SecurityConfig {//extends WebSecurityConfigurerAdapter {

    public static final String SECRET = "coloque aqui um segredo forte";//TODO mudar aqui

//    @Override
//    protected void configure( HttpSecurity http ) throws Exception {
//        http.authorizeRequests()
//                .antMatchers( "/resources/**" ).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage( "signin" ).permitAll()
//                .and()
//                .logout().permitAll();
//
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder(SECRET);
    }

    @Bean
    public TextEncryptor textEncryptor() {
        return Encryptors.noOpText();
    }
}
