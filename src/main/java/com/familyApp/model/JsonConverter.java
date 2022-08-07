package com.familyApp.model;

import com.familyApp.table.Family;
import com.familyApp.table.FamilyMember;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonConverter {

    public static Object[] convertFamilyJson(String json) {
        json = json.replace("\n", "");
        try {
            String[] jsonTab = json.split("\"FamilyMember\":");
            String familyJson = jsonTab[0].replace("{\"Family\":{", "{").replace("},", "}");
            String membersJson = jsonTab[1].replace("}]}", "}]");
            ObjectMapper objectMapper = new ObjectMapper();

            Family family = objectMapper.readValue(familyJson, Family.class);
            List<FamilyMember> members = Arrays.asList(objectMapper.readValue(membersJson, FamilyMember[].class));
            Object[] ob = {family, members};
            return ob;
        } catch (IOException e) {
        } catch (Exception e) {
        }
        return null;
    }

    //here i needed to create json on my own cuz in FamilyMember are ignoring columns and i need 1 more
    public static String convertFamilyMemberToJson(FamilyMember member) {
        String json = "{\"familyId\":" + member.getFamilyId() + ",\"givenName\":\"" + member.getGivenName()
                + "\",\"familyName\":\"" + member.getFamilyName() + "\",\"age\":" + member.getAge() + "}";
        return json;
    }

    //the same as above but in 2nd way
    public static FamilyMember convertJsonToFamilyMember(String json) {
        json = json.replace("{", "").replace("{", "");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] s = json.split(",\"givenName\":");
            int familyid = Integer.valueOf(s[0].replace("\"familyId\":", ""));
            String json2 = "{\"givenName\":" + s[1] + "}";
            FamilyMember member = objectMapper.readValue(json2, FamilyMember.class);
            member.setFamilyId(familyid);
            return member;
        } catch (NumberFormatException | IOException e) {
        }
        return null;
    }
}
