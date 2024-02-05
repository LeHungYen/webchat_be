package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.UserCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SuercertificateRepository extends JpaRepository<UserCertificate, Integer>, JpaSpecificationExecutor<UserCertificate> {

}