package entity;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
public class TrainingProgram {
    @Id
    private String programID;
    private String programName;
    private String duration;
    private double fee;
    @OneToMany(mappedBy = "cId",cascade = {CascadeType.REMOVE})
    private List<RegistrationDetail> registrationDetailList;

    public TrainingProgram() {
    }

    public TrainingProgram(String programID, String programName, String duration, double fee) {
        this.setProgramID(programID);
        this.setProgramName(programName);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<RegistrationDetail> getRegistrationDetailList() {
        return registrationDetailList;
    }

    public void setRegistrationDetailList(List<RegistrationDetail> registrationDetailList) {
        this.registrationDetailList = registrationDetailList;
    }
}
