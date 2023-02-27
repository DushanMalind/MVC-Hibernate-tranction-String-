package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.Item;
import lk.ijse.computerShop.to.Supplyer;
import lk.ijse.computerShop.view.tm.CustomerTm;
import lk.ijse.computerShop.view.tm.SupplyerTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyerDeatilsController {

    public AnchorPane supplyerAcnchorPane;
    public TextField txtSupId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtModel;
    public Button savebtn;
    public TableView <SupplyerTm>tblSupplyer;
    public TableColumn colSupId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colModel;
    public TableColumn colOption;
    public TextField txtSerch;

    private Matcher userId;
    private Matcher userName;
    private Matcher userAddress;
    private Matcher userContact;
    private String searchText="";


    public void initialize(){
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomers(searchText);

        tblSupplyer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (null!=newValue) {
                setData(newValue);
            }
        });

        txtSerch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchCustomers(searchText);
        });

//        setPatterns();

    }

    private void setData(SupplyerTm tm){
        txtSupId.setText(tm.getSupId());
        txtName.setText(tm.getName());
        txtAddress.setText(String.valueOf(tm.getUnitPrice()));
        txtModel.setText(tm.getModel());
        savebtn.setText("  Update Customer");
    }


    private void searchCustomers(String text) {
        String searchText ="%"+text+"%";
        try {
            ObservableList<SupplyerTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getDbConnection().getConnection();
            String sql = "select * from supplyer WHERE name LIKE ? || model LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, searchText);
            statement.setString(2, searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Button btn = new Button("Delete");
                SupplyerTm tm = new SupplyerTm(set.getString(1),
                        set.getString(2),
                        set.getDouble(3),
                        set.getString(4), btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Deleted ?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {

                        try {
                            Connection connection1 = DBConnection.getDbConnection().getConnection();
                            String sql1 = "Delete from supplyer where cusId=?";
                            PreparedStatement statement1 = connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getSupId());

                            if (statement1.executeUpdate() > 0) {
                                searchCustomers(searchText);
                                new Alert(Alert.AlertType.INFORMATION, "Supplyer Deleted").show();
                            } else {
                                new Alert(Alert.AlertType.INFORMATION, "Supplyer Not Deleted").show();
                            }

                        } catch (ClassNotFoundException | SQLException e) {

                        }
                        /*boolean isDeleted=CustomerDataBase.customerTable.remove(c);*/
                    }
                });
            }
            tblSupplyer.setItems(tmList);
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    private void setPatterns() {
        Pattern userIdPattern = Pattern.compile(".*(C0)([1-9]{1})([0-9]{1}.*)");  //(c0)([1-9]{1})([0-9]{1})
        userId = userIdPattern.matcher(txtSupId.getText());

        Pattern userNamePattern = Pattern.compile(".*[a-zA-Z]{4,}"); //[a-zA-Z0-9]{4,}
        userName = userNamePattern.matcher(txtName.getText());

        Pattern userAddressPattern = Pattern.compile(".*[a-zA-Z0-9]{4,}"); //^[a-zA-Z0-9]{4,}$
        userAddress = userAddressPattern.matcher(txtAddress.getText());

        Pattern userContactPattern = Pattern.compile(".*(?:7|0|(?:\\+94))[0-9]{9,10}");//.*^(?:7|0|(?:\+94))[0-9]{9,10}$*
        userContact = userContactPattern.matcher(txtModel.getText());
    }
    public void btnBackOnAction(ActionEvent actionEvent) {
        supplyerAcnchorPane.getChildren().clear();
    }

    public void btnSaveSupplyer(ActionEvent actionEvent) {
        /*setPatterns();
        if(userId.matches()) {
            if(userName.matches()) {
                if(userAddress.matches()) {
                    if(userContact.matches()) {


                    } else {
                        txtModel.requestFocus();
                        txtModel.setText("invalid Contact ");
                    }
                } else {
                    txtAddress.requestFocus();
                    txtAddress.setText("invalid Address ");
                }
            } else {
                txtName.requestFocus();
                txtName.setText("invalid Name ");
            }
        } else {
            txtSupId.requestFocus();
            txtSupId.setText("invalid ID ");
        }*/

        Supplyer sup=new Supplyer(txtSupId.getText(),txtName.getText(),Double.parseDouble(txtAddress.getText()),txtModel.getText());

        try {
            if (savebtn.getText().equalsIgnoreCase("Save Supplyer")) {

                Connection connection = DBConnection.getDbConnection().getConnection();
                String sql = "INSERT INTO supplyer Values(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, sup.getSupId());
                statement.setString(2, sup.getName());
                statement.setDouble(3, sup.getUnitPrice());
                statement.setString(4, sup.getModel());
                if (statement.executeUpdate()> 0) {
                    searchCustomers(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Supplyer Saved").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Supplyer Not Saved").show();
                }

            }else {
                try {
                    Connection connection=DBConnection.getDbConnection().getConnection();
                    String sql="Update supplyer set name=?,unitPrice=?,model=? where supId=?";
                    PreparedStatement statement=connection.prepareStatement(sql);
                    statement.setString(1,sup.getName());
                    statement.setDouble(2,sup.getUnitPrice());
                    statement.setString(3,sup.getModel());
                    statement.setString(4,sup.getSupId());

                    if (statement.executeUpdate()>0){
                        searchCustomers(searchText);
                        new Alert(Alert.AlertType.INFORMATION,"Supplyer Update").show();
                        clearFields();
                    }else {
                        new Alert(Alert.AlertType.WARNING,"Supplyer not Update").show();
                    }


                }catch (ClassNotFoundException | SQLException e){

                }

            }
        }catch (NumberFormatException |ClassNotFoundException | SQLException e){
            new Alert(Alert.AlertType.WARNING,"Please Data Add").show();
        }


    }
    private void clearFields(){
        txtSupId.clear();
        txtName.clear();
        txtAddress.clear();
        txtModel.clear();
    }

    public void btnClear(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnNewSupplyer(ActionEvent actionEvent) {
        savebtn.setText("Save Supplyer");
    }

    public static ArrayList<Supplyer> getAllItem() throws ClassNotFoundException, SQLException {

        Connection connection =DBConnection.getDbConnection().getConnection();

        ResultSet result = connection.prepareStatement("SELECT * FROM supplyer").executeQuery();

        ArrayList<Supplyer> data = new ArrayList();

        while (result.next()) {

            Supplyer c = new Supplyer(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4)
            );

            data.add(c);
        }

        return data;
    }
}
