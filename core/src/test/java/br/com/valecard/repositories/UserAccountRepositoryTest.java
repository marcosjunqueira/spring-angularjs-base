/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.repositories;

import br.com.valecard.config.TestConfig;
import br.com.valecard.model.UserAccount;
import java.util.Date;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository instance;
    UserAccount a1;
    UserAccount a2;

    public UserAccountRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        a1 = new UserAccount();
        a1.setPassword("123456");
        a1.setAccountNonExpired(true);
        a1.setCredentialsNonExpired(true);
        a1.setEnabled(true);
        a1.setEmail("marcos.junqueira01@gmail.com");
        a1.setMobile("3599671830");
        a1.setFirstName("a1");
        a1.setLastName("a1");
        a1.setCreateDate(new Date());
        instance.save(a1);

        a2 = new UserAccount();
        a2.setPassword("123");
        a2.setAccountNonExpired(true);
        a2.setCredentialsNonExpired(true);
        a2.setEnabled(true);
        a2.setEmail("marcos.junqueira01@sitetune.com.br");
        a2.setMobile("3432109289");
        a2.setFirstName("a2");
        a2.setLastName("a2");
        a2.setCreateDate(new Date());
        instance.save(a2);
    }

    @After
    public void tearDown() {
        instance.deleteAll();
    }

    /**
     * Test of testFindByEmail method, of class UserUserAccountRepository.
     */
    @Test
    public void testFindByEmail() {
        System.out.println("findByEmail");
        String email = "marcos.junqueira01@gmail.com";
        UserAccount result = instance.findByEmail(email);
        assertEquals(a1, result);

        email = "marcos.junqueira01@sitetune.com.br";
        result = instance.findByEmail(email);
        assertEquals(a2, result);
    }

    /**
     * Test of findByMobile method, of class UserUserAccountRepository.
     */
    @Test
    public void testFindByMobile() {
        System.out.println("findByMobile");
        String mobile = "3599671830";
        UserAccount result = instance.findByMobile(mobile);
        assertEquals(a1, result);

        mobile = "3432109289";
        result = instance.findByMobile(mobile);
        assertEquals(a2, result);
    }

    private static final Logger LOG = Logger.getLogger(UserAccountRepositoryTest.class.getName());

    /**
     * Test of findByIdOrEmailOrMobile method, of class UserAccountRepository.
     */
    @Test
    public void testFindByIdOrEmailOrMobile() {
        System.out.println("findByIdOrEmailOrMobile");
        Long id = 0L;
        String email = "marcos.junqueira@gmail.com";
        String mobile = "3599671830";
        UserAccount expResult = a1;
        UserAccount result = instance.findByIdOrEmailOrMobile(id, email, mobile);
        assertEquals(expResult, result);
    }

}
