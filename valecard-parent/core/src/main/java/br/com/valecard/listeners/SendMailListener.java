/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.listeners;

import br.com.valecard.events.SendMailEvent;
import br.com.valecard.model.UserAccount;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
@Component
public class SendMailListener implements ApplicationListener<SendMailEvent> {

    private static final String XMC_TRACK = "X-MC-Track";
    private static final String XMC_TEMPLATE = "X-MC-Template";
    private static final String XMC_MERGE_VARS = "X-MC-MergeVars";
    private static final Logger LOG = Logger.getLogger(SendMailListener.class.getName());

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(SendMailEvent event) {
        MimeMessage mm = mailSender.createMimeMessage();
        final String property = event.getProperty();
        try {
            mm.setRecipients(Message.RecipientType.TO, property);
            mm.setText(property);
//            mm.addHeader(XMC_TRACK, track);
//            mm.addHeader(XMC_TEMPLATE, property);
//            mm.addHeader(XMC_MERGE_VARS, mergeVars);
        } catch (MessagingException ex) {
            LOG.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            mailSender.send(mm);
        } catch (MailException ex) {
            LOG.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
}
