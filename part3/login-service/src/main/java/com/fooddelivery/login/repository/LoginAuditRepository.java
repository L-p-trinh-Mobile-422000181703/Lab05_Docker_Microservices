package com.fooddelivery.login.repository;

import com.fooddelivery.login.model.LoginAudit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginAuditRepository extends MongoRepository<LoginAudit, String> {
}
