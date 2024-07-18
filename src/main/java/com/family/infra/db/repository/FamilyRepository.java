package com.family.infra.db.repository;

import com.family.infra.db.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FamilyRepository extends JpaRepository<Family, UUID>{
}