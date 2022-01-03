package dto;

public class TrainingProgramDTO {
    private String programID;
    private String programName;
    private String duration;
    private double fee;

    public TrainingProgramDTO() {
    }

    public TrainingProgramDTO(String programID, String programName, String duration, double fee) {
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
}
