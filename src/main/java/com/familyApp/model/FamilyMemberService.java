package com.familyApp.model;

import com.familyApp.repository.FamilyMemberRepository;
import com.familyApp.table.FamilyMember;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberService {

    @Autowired
    private FamilyMemberRepository familyMemberRepository;
    
    public String searchFamilyMember(String memberId){
        int id=-1;
        try{
            id = Integer.valueOf(memberId);
        }catch(Exception e){
            return "";
        }
        List<FamilyMember> members = familyMemberRepository.findFamilyMembersByFamilyId(id);
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(members);
        }catch(IOException e){            
        }
        return "";
    }
    
    public void createFamilyMember(String json){
        FamilyMember member = JsonConverter.convertJsonToFamilyMember(json);        
        familyMemberRepository.save(member);        
    }
    
    public void deleteFamilyMembers(String memberId){
        int id=-1;
        try{
            id = Integer.valueOf(memberId);
        }catch(Exception e){
            return;
        }
        List<FamilyMember> members = familyMemberRepository.findFamilyMembersByFamilyId(id);
        for(FamilyMember member:members){
            familyMemberRepository.delete(member);
        }
    }
}
