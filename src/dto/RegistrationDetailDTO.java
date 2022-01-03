package dto;


public class RegistrationDetailDTO {
    private int id;
    private String sId;
    private String cId;
    private String program;
    private String date;
    private String time;

    public RegistrationDetailDTO() {
    }

    public RegistrationDetailDTO(int id, String sId, String cId,String program, String date, String time) {
        this.setId(id);
        this.setsId(sId);
        this.setcId(cId);
        this.setProgram(program);
        this.setDate(date);
        this.setTime(time);
    }

    public RegistrationDetailDTO(String sId, String cId, String program,String date, String time) {
        this.setsId(sId);
        this.setcId(cId);
        this.setProgram(program);
        this.setDate(date);
        this.setTime(time);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
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
}
