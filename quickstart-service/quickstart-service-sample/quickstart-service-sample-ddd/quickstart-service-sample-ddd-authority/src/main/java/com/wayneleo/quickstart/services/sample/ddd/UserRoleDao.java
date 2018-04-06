package com.wayneleo.quickstart.services.sample.ddd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole, String> {}
