package controller;

import bo.BOFactory;
import bo.custom.TrainingProgramBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.TrainingProgramDTO;
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
import util.Validation;
import view.tm.TrainingProgramTM;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageTrainingProgramsFormController implements Initializable {

    private final TrainingProgramBO trainingProgramBO= (TrainingProgramBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.PROGRAM);
    
    public JFXTextField txtId;
    public JFXTextField txtProgram;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public TableView<TrainingProgramTM> tblPrograms;
    public TableColumn colId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;
    public TableColumn colDelete;
    public JFXButton btnAddProgram;
    public JFXButton btnUpdate;
    public AnchorPane courseContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^[C][T][0-9][0-9][0-9][0-9]$");
    Pattern namePattern = Pattern.compile("^[[A-z]+(?[\\s.]+[A-z]+)*]{10,45}$");
    Pattern durationPattern = Pattern.compile("^[1-9][0-9]?[\\s][A-z]*$");
    Pattern feePattern = Pattern.compile("^(0(?!\\.00)|[1-9]\\d{0,6})\\.\\d{2}$");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddProgram.setDisable(true);
        btnUpdate.setDisable(true);
        validateInit();
        try {
            setTableDetail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateInit() {
        map.put(txtId,idPattern);
        map.put(txtProgram,namePattern);
        map.put(txtDuration,durationPattern);
        map.put(txtFee,feePattern);
    }

    private void setTableDetail() throws IOException {
        colId.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colId.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colProgram.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDuration.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colFee.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colDelete.setStyle("-fx-alignment:CENTER;" + "-fx-font-size:18px;");
        
        setProgramDetailsToTable(trainingProgramBO.getAllPrograms());

        tblPrograms.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
            loadProgramDetails(newValue);
        } );
    }

    private void loadProgramDetails(TrainingProgramTM tm) {
      try {
          txtId.setText(tm.getProgramID());
          txtProgram.setText(tm.getProgramName());
          txtDuration.setText(tm.getDuration());
          txtFee.setText(String.valueOf(tm.getFee()));
      }catch(NullPointerException e){}
    }

    private void setProgramDetailsToTable(List<TrainingProgramDTO> allPrograms) {
        ObservableList<TrainingProgramTM> programList= FXCollections.observableArrayList();
        allPrograms.forEach(e->{
            Button btn=new Button("Delete");
            btn.setStyle("-fx-background-color:#81ecec");
            programList.add(new TrainingProgramTM(e.getProgramID(),e.getProgramName(),e.getDuration(),e.getFee(),btn));

            btn.setOnAction((event )->{
                ButtonType yes=new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no=new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this student? 🤔",yes,no);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.orElse(yes)==no){

                }else {

                    try {
                        if( trainingProgramBO.deleteProgram(e.getProgramID())){
                            new Alert(Alert.AlertType.CONFIRMATION, "Student is Deleted 👍").show();
                            try {
                                setTableDetail();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } );
        });

        tblPrograms.setItems(programList);
    }

    public void addProgramOnAction(ActionEvent actionEvent) throws IOException {
        TrainingProgramDTO trainingProgramDTO=new TrainingProgramDTO(
                txtId.getText(),
                txtProgram.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        try {
            if (trainingProgramBO.addProgram(trainingProgramDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Program has Saved 👌").show();
                setTableDetail();
                txtId.clear();
                txtProgram.clear();
                txtDuration.clear();
                txtFee.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again 😑").show();
            }
        }catch (Exception e){
                new Alert(Alert.AlertType.WARNING, "Training Program already exists 😑").show();
        }
    }

    public void updateProgramOnAction(ActionEvent actionEvent) throws IOException {
        TrainingProgramDTO trainingProgramDTO=new TrainingProgramDTO(
                txtId.getText(),
                txtProgram.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        try {
            if (trainingProgramBO.updateProgram(trainingProgramDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Program has Updated 👌").show();
                try {
                    setTableDetail();
                } catch (NullPointerException e) {
                }
                txtId.clear();
                txtProgram.clear();
                txtDuration.clear();
                txtFee.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again 😑").show();
            }
        }catch(Exception e){
                new Alert(Alert.AlertType.WARNING, "Add Course Details to update😑").show();
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        Object response = Validation.validate(map, btnAddProgram);
        if (response instanceof TextField) {
            TextField errorText = (TextField) response;
            if (keyEvent.getCode() == KeyCode.ENTER) {
                errorText.requestFocus();
            }
            btnUpdate.setDisable(true);
        } else if (response instanceof Boolean) {
            btnAddProgram.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    public void openHome(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) courseContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }
}
