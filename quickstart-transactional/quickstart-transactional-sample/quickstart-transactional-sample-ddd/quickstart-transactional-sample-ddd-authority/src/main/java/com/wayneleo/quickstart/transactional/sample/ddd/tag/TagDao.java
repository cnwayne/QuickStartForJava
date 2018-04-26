package com.wayneleo.quickstart.transactional.sample.ddd.tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag, String> {}
