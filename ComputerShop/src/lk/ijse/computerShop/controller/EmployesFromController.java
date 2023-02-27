package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.Customer;
import lk.ijse.computerShop.to.Employes;
import lk.ijse.computerShop.view.tm.CustomerTm;
import lk.ijse.computerShop.view.tm.EmploysTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployesFromController {
    public AnchorPane EmpIdAchorPane;
    public TextField txtEmpId;
    public TextField txtEmpName;
    public TextField txtAddress;
    public TextField txtCusId;
    public Button btnNewEmployes;
    public TableView <EmploysTm> tblEmployes;
    public TableColumn colEmId;
    public TableColumn colEmpName;
    public TableColumn colAddress;
    public TableColumn colCusId;
    public TableColumn colOption;
    public TextField txtSearch;

    private Matcher userId;
    private Matcher userName;
    private Matcher userAddress;
    private Matcher userContact;
    private String searchText="";

    public void initialize(){
        colEmId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomers(searchText);

        tblEmployes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (null!=newValue) {
                setData(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchCustomers(searchText);
        });

//        setPatterns();

    }
    private void setData(EmploysTm tm){
        txtEmpId.setText(tm.getEmpId());
        txtEmpName.setText(tm.getEmpName());
        txtAddress.setText(tm.getAddress());
        txtCusId.setText(tm.getCusId());
        btnNewEmployes.setText("Update Customer");
    }

    private void searchCustomers(String text){
        String searchText="%"+text+"%";
        try {
            ObservableList<EmploysTm> tmList= FXCollections.observableArrayList();

            Connection connection= DBConnection.getDbConnection().getConnection();
            String sql="select * from employees WHERE empName LIKE ? || address LIKE ?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set=statement.executeQuery();

            while (set.next()){
                Button btn=new Button("Delete");
                EmploysTm tm=new EmploysTm(set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Deleted ?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){

                        try {
                            Connection connection1=DBConnection.getDbConnection().getConnection();
                            String sql1="Delete from employees where empId=?";
                            PreparedStatement statement1=connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getEmpId());

                            if (statement1.executeUpdate()>0){
                                searchCustomers(searchText);
                                new Alert(Alert.AlertType.INFORMATION,"Employes Deleted").show();
                            }else {
                                new Alert(Alert.AlertType.INFORMATION,"Employes Not Deleted").show();
                            }

                        }catch (ClassNotFoundException | SQLException e){

                        }
                        /*boolean isDeleted=CustomerDataBase.customerTable.remove(c);*/
                    }
                });
            }
            tblEmployes.setItems(tmList);
        }catch (ClassNotFoundException | SQLException e){

        }
        /*ObservableList<CustomerTm> tmList= FXCollections.observableArrayList();
        for (Customer c:CustomerDataBase.customerTable
             ) {
        }*/

    }
    private void setPatterns() {

        Pattern userIdPattern = Pattern.compile(".*(C0)([1-9]{1})([0-9]{1}.*)");  //(c0)([1-9]{1})([0-9]{1})
        userId = userIdPattern.matcher(txtEmpId.getText());

        Pattern userNamePattern = Pattern.compile(".*[a-zA-Z]{4,}"); //[a-zA-Z0-9]{4,}
        userName = userNamePattern.matcher(txtEmpName.getText());

        Pattern userAddressPattern = Pattern.compile(".*[a-zA-Z0-9]{4,}"); //^[a-zA-Z0-9]{4,}$
        userAddress = userAddressPattern.matcher(txtAddress.getText());

        Pattern userContactPattern = Pattern.compile(".*(?:7|0|(?:\\+94))[0-9]{9,10}");//.*^(?:7|0|(?:\+94))[0-9]{9,10}$*
        userContact = userContactPattern.matcher(txtCusId.getText());

    }
    private void clearFields(){
        txtEmpId.clear();
        txtEmpName.clear();
        txtAddress.clear();
        txtCusId.clear();
    }


    public void btnBackOnAction(ActionEvent actionEvent) {
        EmpIdAchorPane.getChildren().clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        /*String id=txtCustomerId.getText();
        String name=txtCustomerName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();*/
//        setPatterns();
        /*if(userId.matches()) {
            if(userName.matches()) {
                if(userAddress.matches()) {
                    if(userContact.matches()) {
                    } else {
                        txtCusId.requestFocus();
                        txtCusId.setText("invalid Contact ");
                    }
                } else {
                    txtAddress.requestFocus();
                    txtAddress.setText("invalid Address ");
                }
            } else {
                txtEmpName.requestFocus();
                txtEmpName.setText("invalid Name ");
            }
        } else {
            txtEmpId.requestFocus();
            txtEmpId.setText("invalid ID ");
        }*/
        Employes c1=new Employes(txtEmpId.getText(),txtEmpName.getText(),txtAddress.getText(),txtCusId.getText());
        try {
            if (btnNewEmployes.getText().equalsIgnoreCase("Save Employes")) {

                Connection connection = DBConnection.getDbConnection().getConnection();
                String sql = "INSERT INTO employees Values(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, c1.getEmpId());
                statement.setString(2, c1.getEmpName());
                statement.setString(3, c1.getAddress());
                statement.setString(4, c1.getCuId());
                if (statement.executeUpdate()> 0) {
                    searchCustomers(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Employes Saved").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Employes Not Saved").show();
                }


           /* boolean isSaved=CustomerDataBase.customerTable.add(c1);
            if (isSaved){
                searchCustomers(searchText);
                new Alert(Alert.AlertType.INFORMATION,"Customer Saved").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Customer Not Saved").show();
            }*/
            }else {
                try {
                    Connection connection=DBConnection.getDbConnection().getConnection();
                    String sql="Update employees set empName=?,address=?,cusId=? where empId=?";
                    PreparedStatement statement=connection.prepareStatement(sql);
                    statement.setString(1,c1.getEmpName());
                    statement.setString(2,c1.getAddress());
                    statement.setString(3,c1.getCuId());
                    statement.setString(4,c1.getEmpId());

                    if (statement.executeUpdate()>0){
                        searchCustomers(searchText);
                        new Alert(Alert.AlertType.INFORMATION,"Employes Update").show();
                        clearFields();
                    }else {
                        new Alert(Alert.AlertType.WARNING,"Employes not Update").show();
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

    public void btnNewEmployesOnActoin(ActionEvent actionEvent) {
        btnNewEmployes.setText("Save Employes");
    }
}
