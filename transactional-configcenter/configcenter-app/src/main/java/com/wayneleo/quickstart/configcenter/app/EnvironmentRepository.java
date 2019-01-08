package com.wayneleo.quickstart.configcenter.app;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wayneleo.quickstart.configcenter.model.Environment;

public interface EnvironmentRepository extends JpaRepository<Environment, String> {}
