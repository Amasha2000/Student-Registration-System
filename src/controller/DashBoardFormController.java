package controller;

import javafx.animation.ScaleTransition;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DashBoardFormController {
    public ImageView imgStudents;
    public ImageView imgCourses;
    public ImageView imgRegistration;
    public AnchorPane dashBoardContext;
    private JFXPanel root;

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            switch (icon.getId()) {
                case "imgStudents":
                    break;
                case "imgCourses":
                    break;
                case "imgRegistration":
                      break;
            }
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }


    public void openHome(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) dashBoardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LogInForm.fxml"))));
    }

    public void navigateStudents(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) dashBoardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/StudentRegistrationForm.fxml"))));
    }

    public void navigateDetails(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) dashBoardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/RegistrationDetailForm.fxml"))));
    }

    public void navigatePrograms(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) dashBoardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageTrainingProgramsForm.fxml"))));
    }
}
