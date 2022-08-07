package com.familyApp.table;

import org.codehaus.jackson.annotate.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "familymemberdb", name = "familymember")
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @Column(name = "familyid")
    private int familyId;
    @Column(name = "familyname")
    private String familyName;
    @Column(name = "givenname")
    private String givenName;
    @Column(name = "age")
    private int age;

    public FamilyMember() {
    }

    public FamilyMember(String familyName, String givenName, int age) {
        this.familyName = familyName;
        this.givenName = givenName;
        this.age = age;
    }
    
    public Long getId() {
        return id;
    }

    public int getFamilyId() {
        return familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public int getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
