/*
 * The License
 *
 * Copyright 2014 Busca Profissional (www.agendoo.me).
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Busca Profissional (www.agendoo.me).
 */
package br.com.valecard.services.security.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos
 */
@Service("authenticationHandler")
public class AuthenticationHandlerImpl implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    //Essa classe pode ser usada para tratar eventos de sucesso e falha de autenticação

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    }

}
