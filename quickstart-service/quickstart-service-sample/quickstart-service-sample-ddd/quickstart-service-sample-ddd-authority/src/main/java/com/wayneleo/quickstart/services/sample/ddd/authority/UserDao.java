package com.wayneleo.quickstart.services.sample.ddd.authority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {}