package com.wayneleo.quickstart.configcenter.app;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wayneleo.quickstart.configcenter.model.Key;

public interface KeyRepository extends JpaRepository<Key, String> {}
