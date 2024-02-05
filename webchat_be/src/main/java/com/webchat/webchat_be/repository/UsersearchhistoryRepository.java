package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Usersearchhistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersearchhistoryRepository extends JpaRepository<Usersearchhistory, Integer>, JpaSpecificationExecutor<Usersearchhistory> {

}