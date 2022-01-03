package entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
public class RegistrationDetail {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="sId", referencedColumnName = "id")
    private Student sId;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="cId", referencedColumnName = "programID")
    private TrainingProgram cId;
    private String studentId;
    private String courseId;
    private String program;
    private String date;
    private String time;

    public RegistrationDetail() {
    }

    public RegistrationDetail(Student sId, TrainingProgram cId,String studentId,String courseId, String program, String date, String time) {
        this.setsId(sId);
        this.setcId(cId);
        this.setStudentId(studentId);
        this.setCourseId(courseId);
        this.setProgram(program);
        this.setDate(date);
        this.setTime(time);
    }

    public RegistrationDetail(String studentId, String courseId, String program, String date, String time) {
        this.setStudentId(studentId);
        this.setCourseId(courseId);
        this.setProgram(program);
        this.setDate(date);
        this.setTime(time);
    }


//    public RegistrationDetail( String id, String code,String program,String date,String time) {
//        this.registrationDetailPK=new RegistrationDetailPK(id,code);
//        this.setProgram(program);
//        this.setDate(date);
//        this.setTime(time);
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Student getsId() {
        return sId;
    }

    public void setsId(Student sId) {
        this.sId = sId;
    }

    public TrainingProgram getcId() {
        return cId;
    }

    public void setcId(TrainingProgram cId) {
        this.cId = cId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


//    public RegistrationDetailPK getRegistrationDetailPK() {
//        return registrationDetailPK;
//    }
//
//    public void setRegistrationDetailPK(RegistrationDetailPK registrationDetailPK) {
//        this.registrationDetailPK = registrationDetailPK;
//    }
}
