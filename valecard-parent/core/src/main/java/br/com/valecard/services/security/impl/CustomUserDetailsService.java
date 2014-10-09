/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.services.security.impl;

import br.com.valecard.model.UserAccount;
import br.com.valecard.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //o intuito desse método é retornar o objeto usuário que implementa UserDetails
        //para que o spring security possa fazer a autenticação.
        UserAccount retValue;
        retValue = userAccountRepository.findByEmail(username);
        return retValue;
    }

}
