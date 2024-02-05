package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Useralbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UseralbumRepository extends JpaRepository<Useralbum, Integer>, JpaSpecificationExecutor<Useralbum> {

}