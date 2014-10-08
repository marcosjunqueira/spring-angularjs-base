/*
 * The License
 *
 * Copyright 2014 Agendoo.me.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Agendoo.me.
 */
package br.com.valecard.repositories.events;

import br.com.valecard.context.event.EventPublisher;
import br.com.valecard.events.SendMailEvent;
import br.com.valecard.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcos
 */
@Component
@RepositoryEventHandler(UserAccount.class)
public class UserAccountEventHandler {

    @Autowired
    private EventPublisher eventPublisher;
    
    @HandleBeforeCreate
    public void handleBeforeCreate(UserAccount userAccount) {
        //executar alguma operação com userAccount
        
        //exemplo de lançamento de evendo assincrono
        eventPublisher.publishEvent(new SendMailEvent(userAccount.getEmail(), this));
    }
    // outras opções
//    @HandleAfterCreate
//    @HandleAfterDelete
//    @HandleAfterLinkDelete
//    @HandleAfterLinkSave
//    @HandleAfterSave
//    @HandleBeforeCreate
//    @HandleBeforeDelete
//    @HandleBeforeLinkDelete
//    @HandleBeforeLinkSave
//    @HandleBeforeSave
    
}
