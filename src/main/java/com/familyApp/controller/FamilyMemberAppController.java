package com.familyApp.controller;

import com.familyApp.model.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyMemberAppController {

    @Autowired
    private FamilyMemberService members;

    //put method cuz it only add new member aand not respond later
    @PutMapping("/createFamilyMember")
    public void createFamilyMember(@RequestBody String json) {
        members.createFamilyMember(json);
    }

    //http://localhost:8080/searchFamilyMember?id=1 eg
    @GetMapping(value = "/searchFamilyMember", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchFamilyMember(@RequestParam String id) {
        return members.searchFamilyMember(id);
    }
    
    //http://localhost:8080/deleteFamilyMembers?id=1 eg
    @DeleteMapping("/deleteFamilyMembers")
    public void deleteFamilyMembers(@RequestParam String id){
        members.deleteFamilyMembers(id);
    }
}
