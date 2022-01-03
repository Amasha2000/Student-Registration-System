package entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
public class Student {
        @Id
        private String id;
        private String name;
        private String address;
        private String phoneNumber;
        private String nic;
        private String guardianName;
        private String gender;
        @OneToMany(mappedBy = "sId",cascade = {CascadeType.REMOVE})
        private List<RegistrationDetail> registrationDetailList;

    public Student() {
    }

    public Student(String id, String name, String address, String phoneNumber, String nic, String guardianName, String gender) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setNic(nic);
        this.setGuardianName(guardianName);
        this.setGender(gender);
    }

    public Student(String id, String name, String address, String phoneNumber, String nic, String guardianName, String gender, List<RegistrationDetail> registrationDetailList) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setNic(nic);
        this.setGuardianName(guardianName);
        this.setGender(gender);
        this.setRegistrationDetailList(registrationDetailList);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<RegistrationDetail> getRegistrationDetailList() {
        return registrationDetailList;
    }

    public void setRegistrationDetailList(List<RegistrationDetail> registrationDetailList) {
        this.registrationDetailList = registrationDetailList;
    }
}
