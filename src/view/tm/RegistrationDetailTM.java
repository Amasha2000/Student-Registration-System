package view.tm;

public class RegistrationDetailTM {
    private String sId;
    private String cId;
    private String name;
    private String date;
    private String time;

    public RegistrationDetailTM() {
    }

    public RegistrationDetailTM(String sId, String cId, String name, String date, String time) {
        this.setsId(sId);
        this.setcId(cId);
        this.setName(name);
        this.setDate(date);
        this.setTime(time);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
