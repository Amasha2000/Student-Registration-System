package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import bo.custom.TrainingProgramBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Validation;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;


public class StudentRegistrationFormController implements Initializable {

    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.STUDENT);
    private final TrainingProgramBO trainingProgramBO = (TrainingProgramBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.PROGRAM);

    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtPhoneNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtGuardianName;
    public JFXTextField txtId;
    public JFXButton btnAddStudent;


    public TableView<StudentTM> tblStudents;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colPhoneNumber;
    public TableColumn colNIC;
    public TableColumn colGender;
    public TableColumn colGuardianName;
    public TableColumn colDelete;
    public JFXButton btnUpdateStudent;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtCourseName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXRadioButton rdBtnMale;
    public JFXRadioButton rdBtnFemale;
    public ToggleGroup toggleDescGroup;
    public JFXComboBox cmbCourseId;
    public AnchorPane studentContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern studentIdPattern = Pattern.compile("^[S][-]([0-5][0-9][0-9][1-9])$");
    Pattern studentNamePattern = Pattern.compile("^[[A-z]+(?[\\s.]+[A-z]+)*]{10,45}$");
    Pattern addressPattern = Pattern.compile("^[[0-9A-z'\\.\\\\\\-\\s\\,]+]{10,60}$");
    Pattern nicPattern = Pattern.compile("[[0-9][V]?[X]?]{10,12}");
    Pattern phoneNumberPattern = Pattern.compile("^[0-9]{3}[-][0-9]{7}$");
    Pattern guardianNamePattern = Pattern.compile("^[[A-z]+(?[\\s.]+[A-z]+)*]{10,45}$");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        validateInit();

        btnAddStudent.setDisable(true);
        btnUpdateStudent.setDisable(true);
        try {
            setTableDetail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadDateAndTime();
        try {
            List<String> courseIds = trainingProgramBO.getAllProgramIds();
            cmbCourseId.getItems().addAll(courseIds);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cmbCourseId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCourseData((String) newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void validateInit() {
        map.put(txtId, studentIdPattern);
        map.put(txtName, studentNamePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtPhoneNumber, phoneNumberPattern);
        map.put(txtNIC, nicPattern);
        map.put(txtGuardianName, guardianNamePattern);
    }

    private void setCourseData(String id) throws IOException {
        TrainingProgramDTO trainingProgramDTO = trainingProgramBO.searchProgram(id);
        if (trainingProgramDTO != null) {
            txtCourseName.setText(trainingProgramDTO.getProgramName());
            txtDuration.setText(trainingProgramDTO.getDuration());
            txtFee.setText(String.valueOf(trainingProgramDTO.getFee()));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
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


    private void setTableDetail() throws IOException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPhoneNumber.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colNIC.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colGuardianName.setCellValueFactory(new PropertyValueFactory<>("guardianName"));
        colGuardianName.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colGender.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colDelete.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");

        setStudentDetailsToTable(studentBO.getAllStudents());

        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loadStudentData(newValue);
        });
    }

    private void loadStudentData(StudentTM tm) {
        try {
            txtId.setText(tm.getId());
            txtName.setText(tm.getName());
            txtAddress.setText(tm.getAddress());
            txtPhoneNumber.setText(tm.getPhoneNumber());
            txtNIC.setText(tm.getNic());
            txtGuardianName.setText(tm.getGuardianName());
            if (tm.getGender().equals("Male")) {
                rdBtnMale.setSelected(true);
            } else if (tm.getGender().equals("Female")) {
                rdBtnFemale.setSelected(true);
            }
        } catch (Exception e) {
        }
    }


    private void setStudentDetailsToTable(List<StudentDTO> allStudents) {
        ObservableList<StudentTM> studentList = FXCollections.observableArrayList();
        allStudents.forEach(e -> {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color:#81ecec");
            studentList.add(new StudentTM(e.getId(), e.getName(), e.getAddress(), e.getPhoneNumber(), e.getNic(), e.getGuardianName(), e.getGender(), btn));

            btn.setOnAction((event) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this student? 🤔", yes, no);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(yes) == no) {

                } else {
                    try {
                        if (studentBO.deleteStudent(e.getId())) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Student is Deleted 👍").show();
                            setTableDetail();
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        });

        tblStudents.setItems(studentList);
    }


    public void addStudentOnAction(ActionEvent actionEvent) throws IOException {

        ArrayList<RegistrationDetailDTO> detailList = new ArrayList<>();


        detailList.add(new RegistrationDetailDTO(
                        txtId.getText(),
                        String.valueOf(cmbCourseId.getValue()),
                        txtCourseName.getText(),
                        lblDate.getText(),
                        lblTime.getText()
                )
        );

        RadioButton rdb = (RadioButton) toggleDescGroup.getSelectedToggle();
        StudentDTO studentDTO = new StudentDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtPhoneNumber.getText(),
                txtNIC.getText(),
                txtGuardianName.getText(),
                rdb.getText(),
                detailList
        );
        try {
            if (studentBO.addStudent(studentDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student has Registered 👌").show();
                setTableDetail();
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtPhoneNumber.clear();
                txtNIC.clear();
                txtGuardianName.clear();
                toggleDescGroup.getSelectedToggle().setSelected(false);
                txtCourseName.clear();
                txtDuration.clear();
                txtFee.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again 😑").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "This student already exists 😑").show();
        }
    }

    public void updateStudentOnAction(ActionEvent actionEvent) throws IOException {

        RadioButton rdb = (RadioButton) toggleDescGroup.getSelectedToggle();
        StudentDTO studentDTO = new StudentDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtPhoneNumber.getText(),
                txtNIC.getText(),
                txtGuardianName.getText(),
                rdb.getText()
        );
        try {
            if (studentBO.updateStudent(studentDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Details Updated 👌").show();
                setTableDetail();
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtPhoneNumber.clear();
                txtNIC.clear();
                txtGuardianName.clear();
                toggleDescGroup.getSelectedToggle().setSelected(false);
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again 😑").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.WARNING, "Add Student Details To Update 😑").show();
        }
    }


    public void keyReleased(KeyEvent keyEvent) {
        Object response = Validation.validate(map, btnAddStudent);
        if (response instanceof TextField) {
            TextField errorText = (TextField) response;
            if (keyEvent.getCode() == KeyCode.ENTER) {
                errorText.requestFocus();
            }
            btnUpdateStudent.setDisable(true);
        } else if (response instanceof Boolean) {
            btnAddStudent.setDisable(false);
            btnUpdateStudent.setDisable(false);
        }
    }

    public void openHome(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) studentContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }
}

