package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userachievementprogress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserachievementprogressRepository extends JpaRepository<Userachievementprogress, Integer>, JpaSpecificationExecutor<Userachievementprogress> {

}