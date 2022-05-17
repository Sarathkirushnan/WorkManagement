package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.Jop;

@Repository
public interface JopRepository extends JpaRepository<Jop, Long> {

	boolean existsByName(String name);

}
