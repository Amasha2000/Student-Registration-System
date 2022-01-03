package view.tm;

import javafx.scene.control.Button;

public class TrainingProgramTM {
    private String programID;
    private String programName;
    private String duration;
    private double fee;
    private Button btn;

    public TrainingProgramTM() {
    }

    public TrainingProgramTM(String programID, String programName, String duration, double fee, Button btn) {
        this.setProgramID(programID);
        this.setProgramName(programName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setBtn(btn);
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
