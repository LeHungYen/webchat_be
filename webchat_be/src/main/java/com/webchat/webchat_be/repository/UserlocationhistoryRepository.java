package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userlocationhistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserlocationhistoryRepository extends JpaRepository<Userlocationhistory, Integer>, JpaSpecificationExecutor<Userlocationhistory> {

}