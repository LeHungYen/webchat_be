package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userphoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserphotoRepository extends JpaRepository<Userphoto, Integer>, JpaSpecificationExecutor<Userphoto> {

}