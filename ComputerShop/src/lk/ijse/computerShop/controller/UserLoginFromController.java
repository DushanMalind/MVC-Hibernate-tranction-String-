package lk.ijse.computerShop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computerShop.to.User;
import lk.ijse.computerShop.util.Navigation;
import lk.ijse.computerShop.util.Routes;

import java.io.IOException;

public class UserLoginFromController {
    public PasswordField txtPassword;
    public TextField txtuserName;
    public AnchorPane pane1;
    public ProgressIndicator processdiactor;
    public ProgressBar processBar;
    public Button btnlogin;

    public void initialize() {
        processBar.setStyle("-fx-accent:#00FF00;");
    }

    public void btnSingOnAction(ActionEvent actionEvent) {
        User user = new User(txtuserName.getText(), txtPassword.getText());
        String userName = txtuserName.getText();
        String password = txtPassword.getText();
        try {
            if (userName.contains("") && password.contains("")) {
//                txtuserName.setText("user");
//                txtPassword.setText("123");
                processBar(processdiactor);
                processBar(processBar);
                txtuserName.requestFocus();
                txtPassword.requestFocus();
                Navigation.navigation(Routes.DASHBOARD, pane1);
                new Alert(Alert.AlertType.INFORMATION, "Your login").show();
            } else if (userName.contains("admin") && password.contains("123")) {
                processBar(processdiactor);
                processBar(processBar);
                Navigation.navigation(Routes.DASHBOARD2, pane1);
                new Alert(Alert.AlertType.INFORMATION, "Your login").show();
            } else {
                txtuserName.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
                txtPassword.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
                new Alert(Alert.AlertType.WARNING, "Try Again! UserName Or Password Invalid").show();
            }

        } catch (NullPointerException | IOException e) {

        }
    }

    private void processBar(ProgressIndicator p) {
        double value = p.getProgress();
        if (value < 0) {
            value = 2.1;
        } else {
            value = value + 0.1;
            if (value >= 1.0) {
                value = 1.0;
            }
        }
        p.setProgress(value);
    }

}
