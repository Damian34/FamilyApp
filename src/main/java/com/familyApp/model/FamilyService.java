package com.familyApp.model;

import com.familyApp.repository.FamilyRepository;
import com.familyApp.table.Family;
import com.familyApp.table.FamilyMember;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private Environment environment;

    public String getFamily(String familyId) {
        int id = -1;
        try {
            id = Integer.valueOf(familyId);
        } catch (Exception e) {
            return "";
        }
        Family family = familyRepository.findFamilyById(id);
        if(family==null){
            return "";
        }
        String membersJson = searchFamilyMemberJson(id).replace(" ","");
        ObjectMapper mapper = new ObjectMapper();
        String familyJson = "";
        try{
            familyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(family).replace(" ", "");
        }catch(IOException e){            
        }
        return "{\"Family\":"+familyJson+",\"FamilyMember\":"+membersJson+"}";
    }

    public int createFamily(String json) {
        Object[] ob = JsonConverter.convertFamilyJson(json);
        if (ob == null) {
            return -1;
        }
        Family family = (Family) ob[0];
        List<FamilyMember> members = (List<FamilyMember>) ob[1];
        
        if (validateFamilyData(family, members)) {            
            familyRepository.save(family);
            int newId = getFamilyLastID();
            for (FamilyMember member : members) {
                member.setFamilyId(newId);
                sendToCreateFamilyMember(member);
            }
            return newId;
        }        
        return -1;
    }

    private String searchFamilyMemberJson(int id) {
        try {
            URL url = new URL(getHomeUrl() + "searchFamilyMember?id=" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                String json="";
                while (scan.hasNext()) {
                    json += scan.nextLine();
                }
                return json;
            }
        } catch (IOException e) {
        }
        return "";
    }

    private void sendToCreateFamilyMember(FamilyMember member) {
        String memberJson = JsonConverter.convertFamilyMemberToJson(member);        
        try {
            URL url = new URL(getHomeUrl() + "createFamilyMember");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(memberJson);
            out.close();
            conn.getInputStream();
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
    }

    private boolean validateFamilyData(Family family, List<FamilyMember> members) {
        int infants = 0;
        int children = 0;
        int adults = 0;
        for (FamilyMember member : members) {
            if(!member.getFamilyName().equals(family.getFamilyName())){
                return false;
            }
            int age = member.getAge();
            if (age >= 0 && age < 4) {
                infants++;
            } else if (age < 16) {
                children++;
            } else {
                adults++;
            }
        }
        if (infants == family.getNrOfInfants() && children == family.getNrOfChildren() && adults == family.getNrOfAdults()) {
            return true;
        }
        return false;
    }

    public void deleteFamily(String familyId){
        int id=-1;
        try{
            id = Integer.valueOf(familyId);
        }catch(Exception e){
            return;
        }
        Family family = familyRepository.findFamilyById(id);
        if(family == null){
            return;
        }
        familyRepository.delete(family);
        try {
            URL url = new URL(getHomeUrl() + "deleteFamilyMembers?id="+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
            conn.setRequestMethod("DELETE");
            conn.getInputStream();
        } catch (IOException e) {
            System.out.println("error: " + e);
        }        
    }
    
    private int getFamilyLastID() {
        Integer nextId = familyRepository.findMaxId();
        return nextId == null ? 1 : nextId;//id is added automatically if needed, cuz it is SERIAL type
    }

    private String getHomeUrl() {
        return "http://localhost:" + environment.getProperty("local.server.port") + "/";
    }
}
