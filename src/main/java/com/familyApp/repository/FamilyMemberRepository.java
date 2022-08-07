package com.familyApp.repository;

import com.familyApp.table.FamilyMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long>{
        
    @Query("SELECT fm FROM FamilyMember fm WHERE fm.familyId = ?1")
    public List<FamilyMember> findFamilyMembersByFamilyId(int id);    
}
