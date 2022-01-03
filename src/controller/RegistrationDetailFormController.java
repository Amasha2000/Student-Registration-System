package controller;

import bo.BOFactory;
import bo.custom.RegistrationDetailBO;
import bo.custom.StudentBO;
import bo.custom.TrainingProgramBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RegistrationDetailDTO;
import dto.StudentDTO;
import dto.TrainingProgramDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.RegistrationDetailTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationDetailFormController implements Initializable {
    private final RegistrationDetailBO registrationDetailBO = (RegistrationDetailBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.REGISTRATIONDETAIL);
    private final TrainingProgramBO trainingProgramBO  = (TrainingProgramBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.PROGRAM);
    private final StudentBO studentBO  = (StudentBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.STUDENT);

    public TableView<RegistrationDetailTM> tblDetails;
    public TableColumn colProgramName;
    public TableColumn colDate;
    public TableColumn colTime;
    public JFXComboBox cmbStudentId;
    public JFXComboBox cmbCourseId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtPhoneNumber;
    public JFXTextField txtNIC;
    public JFXTextField txtCourseName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXTextField txtGender;
    public JFXTextField txtGuardianName;

    public Label lblDate;
    public Label lblTime;
    public AnchorPane registrationContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setTableDetail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadDateAndTime();
        try {
            List<String> courseIds=trainingProgramBO.getAllProgramIds();
            List<String> studentIds=studentBO.getAllStudentIds();
            cmbStudentId.getItems().addAll(studentIds);
            cmbCourseId.getItems().addAll(courseIds);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setStudentData((String)newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cmbCourseId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCourseData((String)newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy").format(new Date()));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.format(DateTimeFormatter.ofPattern("HH : mm : ss a")));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setStudentData(String id) throws IOException {
        StudentDTO studentDTO = studentBO.searchStudent(id);
        if(studentDTO!=null){
            txtName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtPhoneNumber.setText(studentDTO.getPhoneNumber());
            txtNIC.setText(studentDTO.getNic());
            txtGender.setText(studentDTO.getGender());
            txtGuardianName.setText(studentDTO.getGuardianName());
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }
    }

    private void setCourseData(String id) throws IOException {
        TrainingProgramDTO trainingProgramDTO = trainingProgramBO.searchProgram(id);
        if(trainingProgramDTO!=null){
            txtCourseName.setText(trainingProgramDTO.getProgramName());
            txtDuration.setText(trainingProgramDTO.getDuration());
            txtFee.setText(String.valueOf(trainingProgramDTO.getFee()));
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }
    }

    private void setTableDetail() throws IOException {
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProgramName.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colDate.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colTime.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");

        setProgramDetailsToTable(registrationDetailBO.allRegistration());
    }

    private void setProgramDetailsToTable(List<RegistrationDetailDTO> allRegistration) {
        ObservableList<RegistrationDetailTM> list = FXCollections.observableArrayList();
        allRegistration.forEach(e -> {
            list.add(new RegistrationDetailTM(e.getProgram(),e.getTime(),e.getsId(),e.getDate(),e.getcId()));

        });
        tblDetails.setItems(list);
    }


    public void registerOnAction(ActionEvent actionEvent) throws IOException {
        RegistrationDetailDTO registrationDetailDTO=new RegistrationDetailDTO(
                String.valueOf(cmbStudentId.getValue()),
                String.valueOf(cmbCourseId.getValue()),
                txtCourseName.getText(),
                lblDate.getText(),
                lblTime.getText()
        );
        try {
            if (registrationDetailBO.addRegistration(registrationDetailDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student has Registered 👌").show();
                setTableDetail();
                txtName.clear();
                txtAddress.clear();
                txtPhoneNumber.clear();
                txtNIC.clear();
                txtCourseName.clear();
                txtDuration.clear();
                txtFee.clear();
                txtGender.clear();
                txtGuardianName.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again 😑").show();
            }
        }catch (Exception e){
                new Alert(Alert.AlertType.WARNING, "Try Again 😑").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {

        RegistrationDetailDTO registrationDetailDTO=new RegistrationDetailDTO(
                String.valueOf(cmbStudentId.getValue()),
                String.valueOf(cmbCourseId.getValue()),
                txtCourseName.getText(),
                lblDate.getText(),
                lblTime.getText()
        );

            if (registrationDetailBO.updateRegistration(registrationDetailDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration Details Updated 👌").show();
                setTableDetail();
                txtName.clear();
                txtAddress.clear();
                txtPhoneNumber.clear();
                txtNIC.clear();
                txtCourseName.clear();
                txtDuration.clear();
                txtFee.clear();
                txtGuardianName.clear();
                txtGender.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again 😑").show();
            }
    }

    public void openHome(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) registrationContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }
}
