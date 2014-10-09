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
import br.com.valecard.model.Category;
import br.com.valecard.repositories.CategoryRepository;
import java.util.List;
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
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository instance;

    public CategoryRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Categoria 1");
        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("Categoria 2");
        Category c3 = new Category();
        c3.setId(3L);
        c3.setName("Categoria 3");
        Category c4 = new Category();
        c4.setId(4L);
        c4.setName("Automotiva");
        instance.save(c1);
        instance.save(c2);
        instance.save(c3);
        instance.save(c4);
    }

    @After
    public void tearDown() {
        instance.deleteAll();
    }

    /**
     * Test of findByNameStartingWithOrderByNameAsc method, of class CategoryRepository.
     */
    @Test
    public void testFindByNameLikeOrderByNameAsc() {
        System.out.println("findByNameLikeOrderByNameAsc");
        String name = "Cat";
        List<Category> result = instance.findByNameStartingWithOrderByNameAsc(name);
        assertEquals(3, result.size());
    }

}
