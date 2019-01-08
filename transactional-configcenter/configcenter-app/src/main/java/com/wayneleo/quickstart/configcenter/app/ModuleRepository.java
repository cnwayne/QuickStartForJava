package com.wayneleo.quickstart.configcenter.app;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wayneleo.quickstart.configcenter.model.Module;

public interface ModuleRepository extends JpaRepository<Module, String> {}
