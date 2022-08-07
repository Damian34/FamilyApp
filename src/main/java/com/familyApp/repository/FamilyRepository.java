package com.familyApp.repository;

import com.familyApp.table.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long>{

    @Query("SELECT f FROM Family f WHERE f.id = ?1")
    public Family findFamilyById(long id);
    
    @Query("Select max(f.id) FROM Family f")
    public Integer findMaxId();
    
}
