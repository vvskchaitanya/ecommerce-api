package com.vvsk.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vvsk.app.ecommerce.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
