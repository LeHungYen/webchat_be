package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userfavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserfavoriteRepository extends JpaRepository<Userfavorite, Integer>, JpaSpecificationExecutor<Userfavorite> {

}