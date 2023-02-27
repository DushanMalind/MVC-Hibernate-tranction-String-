package lk.ijse.computerShop.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.computerShop.util.Navigation;
import lk.ijse.computerShop.util.Routes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBordFormController {

    public Label lblDateTime;
    public AnchorPane mealPackagesIdMain;
    public AnchorPane pane;


    public void initialize(){
        setDateAndTime();
    }


    private void setDateAndTime(){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("  yyyy-MM-dd  HH:mm:ss a");
                    lblDateTime.setText(LocalDateTime.now().format(formatter));
                }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void btnCustomerFrom(ActionEvent actionEvent) throws IOException {
//        Stage stage=(Stage) mealPackagesId.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
        Navigation.navigation(Routes.CUSTOMER,pane);
    }


    public void btnOrderFrom(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.ORDERS,pane);
    }

    public void btnItemFom(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.ITEM,pane);
    }

    public void btnPlaceOrdersFrom(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.PLACEORDERS,pane);
    }

    public void btnOrederDeatils(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.ORDERDETALIS,pane);
    }

    public void btnSupplyerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.SUPPLYER,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.STOCK,pane);
    }

    public void btnDeatlsStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.DEATILSORDER,pane);
    }

    public void btnEmpOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.EMPLOYES,pane);
    }

    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.SERVISE,pane);
    }

    public void btnOrderPlaceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.ORDERSSUPPLYER,pane);
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.REPORT,pane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.LOGUOT,mealPackagesIdMain);
    }

    public void btnAboutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.ABOUT,pane);
    }
}
