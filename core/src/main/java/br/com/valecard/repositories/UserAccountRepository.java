/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.repositories;

import br.com.valecard.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
@RepositoryRestResource(collectionResourceRel = "userAccount", path = "userAccount")
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

    UserAccount findByEmail(@Param("email") String email);

    UserAccount findByMobile(@Param("mobile") String mobile);

    UserAccount findByIdOrEmailOrMobile(@Param("id") Long id, @Param("email") String email, @Param("mobile") String mobile);

    Page<UserAccount> findByFirstNameOrLastNameAllIgnoreCase(@Param("lastName") String firstName, @Param("lastName") String lastName, Pageable p);

}
