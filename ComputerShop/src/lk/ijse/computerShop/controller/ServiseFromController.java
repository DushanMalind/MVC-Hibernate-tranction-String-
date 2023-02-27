package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.Customer;
import lk.ijse.computerShop.to.Services;
import lk.ijse.computerShop.view.tm.CustomerTm;
import lk.ijse.computerShop.view.tm.ServiseTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiseFromController {
    public AnchorPane ServiseAnchorPane;

    public TextField txtName;
    public TextField txtEmId;
    public TextField txtPrice;
    public Button btnSave;
    public TableView <ServiseTm> tblServices;
    public TableColumn colService;
    public TableColumn colName;
    public TableColumn colEmId;
    public TableColumn colPrice;
    public TableColumn colOption;
    public TextField txtServiceId;
    public TextField txtSerach;

    private Matcher userId;
    private Matcher userName;
    private Matcher userAddress;
    private Matcher userContact;
    private String searchText="";

    public void initialize(){
        colService.setCellValueFactory(new PropertyValueFactory<>("serId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomers(searchText);

        tblServices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (null!=newValue) {
                setData(newValue);
            }
        });

        txtSerach.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchCustomers(searchText);
        });

//        setPatterns();

    }
    private void setData(ServiseTm tm){
        txtServiceId.setText(tm.getSerId());
        txtName.setText(tm.getName());
        txtEmId.setText(tm.getEmpId());
        txtPrice.setText(String.valueOf(tm.getPrice()));
        btnSave.setText("Update Service");
    }

    private void searchCustomers(String text){
        String searchText="%"+text+"%";
        try {
            ObservableList<ServiseTm> tmList= FXCollections.observableArrayList();

            Connection connection=DBConnection.getDbConnection().getConnection();
            String sql="select * from servise WHERE partName LIKE ?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,searchText);
//            statement.setString(2,searchText);
            ResultSet set=statement.executeQuery();

            while (set.next()){
                Button btn=new Button("Delete");
                ServiseTm tm=new ServiseTm(set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getDouble(4),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Deleted ?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){

                        try {
                            Connection connection1=DBConnection.getDbConnection().getConnection();
                            String sql1="Delete from servise where serviseId=?";
                            PreparedStatement statement1=connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getSerId());

                            if (statement1.executeUpdate()>0){
                                searchCustomers(searchText);
                                new Alert(Alert.AlertType.INFORMATION,"Service Deleted").show();
                            }else {
                                new Alert(Alert.AlertType.INFORMATION,"Service Not Deleted").show();
                            }

                        }catch (ClassNotFoundException | SQLException e){

                        }
                        /*boolean isDeleted=CustomerDataBase.customerTable.remove(c);*/
                    }
                });
            }
            tblServices.setItems(tmList);
        }catch (ClassNotFoundException | SQLException e){

        }
        /*ObservableList<CustomerTm> tmList= FXCollections.observableArrayList();
        for (Customer c:CustomerDataBase.customerTable
             ) {
        }*/

    }
    private void setPatterns() {

        Pattern userIdPattern = Pattern.compile(".*(C0)([1-9]{1})([0-9]{1}.*)");  //(c0)([1-9]{1})([0-9]{1})
        userId = userIdPattern.matcher(txtServiceId.getText());

        Pattern userNamePattern = Pattern.compile(".*[a-zA-Z]{4,}"); //[a-zA-Z0-9]{4,}
        userName = userNamePattern.matcher(txtName.getText());

        Pattern userAddressPattern = Pattern.compile(".*[a-zA-Z0-9]{4,}"); //^[a-zA-Z0-9]{4,}$
        userAddress = userAddressPattern.matcher(txtEmId.getText());

        Pattern userContactPattern = Pattern.compile(".*(?:7|0|(?:\\+94))[0-9]{9,10}");//.*^(?:7|0|(?:\+94))[0-9]{9,10}$*
        userContact = userContactPattern.matcher(txtPrice.getText());

    }
    private void clearFields(){
        txtServiceId.clear();
        txtName.clear();
        txtEmId.clear();
        txtPrice.clear();
    }



    public void btnBackOnAction(ActionEvent actionEvent) {
        ServiseAnchorPane.getChildren().clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
       /* setPatterns();
        if(userId.matches()) {
            if(userName.matches()) {
                if(userAddress.matches()) {
                    if(userContact.matches()) {


                    } else {
                        txtContact.requestFocus();
                        txtContact.setText("invalid Contact ");
                    }
                } else {
                    txtAddress.requestFocus();
                    txtAddress.setText("invalid Address ");
                }
            } else {
                txtCustomerName.requestFocus();
                txtCustomerName.setText("invalid Name ");
            }
        } else {
            txtCustomerId.requestFocus();
            txtCustomerId.setText("invalid ID ");
        }*/
        Services c1=new Services(txtServiceId.getText(),txtName.getText(),txtEmId.getText(),Double.parseDouble(txtPrice.getText()));
        try {
            if (btnSave.getText().equalsIgnoreCase("Save Service")) {

                Connection connection = DBConnection.getDbConnection().getConnection();
                String sql = "INSERT INTO servise Values(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, c1.getSerId());
                statement.setString(2, c1.getName());
                statement.setString(3, c1.getEmpId());
                statement.setDouble(4, c1.getPrice());
                if (statement.executeUpdate()> 0) {
                    searchCustomers(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Service Saved").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Service Not Saved").show();
                }


            }else {
                try {
                    Connection connection=DBConnection.getDbConnection().getConnection();
                    String sql="Update servise set partName=?,empId=?,Price=? where serviseId=?";
                    PreparedStatement statement=connection.prepareStatement(sql);
                    statement.setString(1,c1.getName());
                    statement.setString(2,c1.getEmpId());
                    statement.setDouble(3,c1.getPrice());
                    statement.setString(4,c1.getSerId());

                    if (statement.executeUpdate()>0){
                        searchCustomers(searchText);
                        new Alert(Alert.AlertType.INFORMATION,"Service Update").show();
                        clearFields();
                    }else {
                        new Alert(Alert.AlertType.WARNING,"Service not Update").show();
                    }


                }catch (ClassNotFoundException | SQLException e){

                }

            }
        }catch (NumberFormatException |ClassNotFoundException | SQLException e){
            new Alert(Alert.AlertType.WARNING,"Please Data Add").show();
        }

    }

    public void btnClear(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnNewService(ActionEvent actionEvent) {
        btnSave.setText("Save Service");
    }
}
