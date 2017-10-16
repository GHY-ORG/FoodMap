package ghy.foodmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "District")
    private int district;
    @Column(name = "Type")
    private int type;
    @Column(name = "Per_floor")
    private int per_floor;
    @Column(name = "Per_ceiling")
    private int per_ceiling;
    @Column(name = "Contact")
    private String contact;
    @Column(name = "Address")
    private String address;
    @Column(name = "Intro")
    private String intro;
    @Column(name = "Status")
    private int status;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDistrict() {
        return district;
    }
    public void setDistrict(int district) {
        this.district = district;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getPer_floor() {
        return per_floor;
    }
    public void setPer_floor(int per_floor) {
        this.per_floor = per_floor;
    }
    public int getPer_ceiling() {
        return per_ceiling;
    }
    public void setPer_ceiling(int per_ceiling) {
        this.per_ceiling = per_ceiling;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
