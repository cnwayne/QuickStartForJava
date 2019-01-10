package com.wayneleo.quickstart.configcenter.app;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wayneleo.quickstart.configcenter.model.Property;

public interface PropertyRepository extends JpaRepository<Property, String> {}
