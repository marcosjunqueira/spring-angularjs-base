/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.repositories;

import br.com.valecard.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}
