package com.wayneleo.quickstart.configcenter.app;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wayneleo.quickstart.configcenter.model.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {}
