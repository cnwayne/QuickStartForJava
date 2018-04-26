package com.wayneleo.quickstart.transactional.sample.ddd.authority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupDao extends JpaRepository<UserGroup, String> {}
