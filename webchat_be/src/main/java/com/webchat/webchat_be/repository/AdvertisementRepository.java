package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer>, JpaSpecificationExecutor<Advertisement> {

}