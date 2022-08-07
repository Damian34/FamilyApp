package com.familyApp.table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(schema = "familydb", name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "familyname")
    private String familyName;
    @Column(name = "nrofinfants")
    private int nrOfInfants;
    @Column(name = "nrofchildren")
    private int nrOfChildren;
    @Column(name = "nrofadults")
    private int nrOfAdults;

    public Family() {
    }

    public Family(String familyName, int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        this.familyName = familyName;
        this.nrOfInfants = nrOfInfants;
        this.nrOfChildren = nrOfChildren;
        this.nrOfAdults = nrOfAdults;
    }

    public Long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getNrOfInfants() {
        return nrOfInfants;
    }

    public int getNrOfChildren() {
        return nrOfChildren;
    }

    public int getNrOfAdults() {
        return nrOfAdults;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setNrOfInfants(int nrOfInfants) {
        this.nrOfInfants = nrOfInfants;
    }

    public void setNrOfChildren(int nrOfChildren) {
        this.nrOfChildren = nrOfChildren;
    }

    public void setNrOfAdults(int nrOfAdults) {
        this.nrOfAdults = nrOfAdults;
    }

}
