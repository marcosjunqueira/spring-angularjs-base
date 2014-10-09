/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.repositories;

import br.com.valecard.model.Category;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

//    @Query(value = )
    List<Category> findByNameStartingWithOrderByNameAsc(String name);

//    @Query(value="{ 'id' : { $regex: ?0 } }", fields="{'cidade':1, 'estado':1 }")
//    List<City> findByIdLike(String name, Pageable pageable);
}
