package com.familyApp.controller;

import com.familyApp.model.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyAppController {

    @Autowired
    private FamilyService familys;

    //it should be post cuz it will return id of created family
    @PostMapping("/createFamily")
    public String createFamily(@RequestBody String json) {        
        return familys.createFamily(json) + "";
    }

    //http://localhost:8080/getFamily?id=1 eg
    @GetMapping(value = "/getFamily", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getFamily(@RequestParam String id) {
        return familys.getFamily(id);
    }
    
    //http://localhost:8080/deleteFamily?id=1 eg
    @DeleteMapping("/deleteFamily")
    public void deleteFamily(@RequestParam String id){
        familys.deleteFamily(id);
    }
    
}
