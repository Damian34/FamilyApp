package com.familyApp;

import com.familyApp.table.Family;
import com.familyApp.table.FamilyMember;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.codehaus.jackson.map.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FamilyAppTests {

    /**
     * To run this test first run spring api and later open test
     * i used unity5 and not spring-boot-starter-test cuz i couldn't test corectly methods that i wanted
     * mainly becouse thse methods needed to have runing server ,
     * not some kind of mock cuz them was sending requet to other methods in this same server
    */
    public final static String host= "http://localhost:8080/";
    
    @Test
    void createFamilyFullTest() {
        String json = createJson();
        String resposne = "";
        try {
            URL url = new URL(host+"createFamily");//createFamily
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            resposne = reader.readLine();
            reader.close();            
            conn.disconnect();
        } catch (IOException e) {
            System.out.println("error: "+e);
        }        
        int ID = Integer.valueOf(resposne);        
        assertTrue(ID!=-1);//if response!=-1 its mean that there was no issues during adding records to DB        
        String jsonGet="";
        try {
            URL url = new URL(host+"getFamily?id="+ID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    jsonGet += scan.nextLine();
                }
            }
        } catch (IOException e) {
        }        
        assertTrue(!jsonGet.equals(""));//it mean that there was non issues during geting json
        
        //and at the end i'm cleaning DB from added records
        try {
            URL url = new URL(host+"deleteFamily?id="+ID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
            conn.setRequestMethod("DELETE");
            conn.getInputStream();
        } catch (IOException e) {
        }     
    }

    private String createJson() {
        Family family = new Family("Kowalski", 1, 1, 1);
        List<FamilyMember> members = Arrays.asList(new FamilyMember("Kowalski", "Kamil", 3),
                new FamilyMember("Kowalski", "Olivier", 12),
                new FamilyMember("Kowalski", "Mateusz", 24));

        ObjectMapper mapper = new ObjectMapper();
        try {
            String familyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(family);
            String membersJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(members);
            return "{\"Family\":" + familyJson + ",\"FamilyMember\":" + membersJson + "}";
        } catch (IOException e) {
        }
        return "";
    }
}
