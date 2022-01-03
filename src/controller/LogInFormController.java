package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Validation;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LogInFormController implements Initializable {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogIn;
    public AnchorPane mainContext;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern patternUserName=Pattern.compile("^(Admin)$");
    Pattern patternPassword=Pattern.compile("^(?=.*[A-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-z\\d@$!%*#?&]{8}$");

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) mainContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }

    private void validateInit() {
        map.put(txtUserName,patternUserName);
        map.put(txtPassword,patternPassword);
    }


    public void keyReleased(KeyEvent keyEvent) {
        Object response = Validation.validate(map,btnLogIn);
        if (response instanceof TextField) {
            TextField errorText = (TextField) response;
            if (keyEvent.getCode() == KeyCode.ENTER) {
                errorText.requestFocus();
                btnLogIn.setDisable(true);
            }
        } else if (response instanceof Boolean) {
                btnLogIn.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateInit();
        btnLogIn.setDisable(true);
    }
}
