package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.repository.CustomerRepository;
import lk.ijse.computerShop.to.Customer;
import lk.ijse.computerShop.view.tm.CustomerTm;

public class CustomerFormController {

    public AnchorPane mealPackagesId;

    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtAddress;
    public TextField txtContact;
    public TableView<CustomerTm> customerTable;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;
    public TextField txtSearch;
    public Button btnCustomer;
    public Label lblId;
    public Label lblName;
    public Label lblAddress;
    public Label lblContact;


    private Matcher userId;
    private Matcher userName;
    private Matcher userAddress;
    private Matcher userContact;
    private String searchText = "";

    public static ArrayList<Customer> getAllCustomer() throws ClassNotFoundException, SQLException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet result = connection.prepareStatement("SELECT * FROM customer").executeQuery();
        ArrayList<Customer> data = new ArrayList();
        while (result.next()) {
            Customer c = new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );

            data.add(c);
        }
        return data;*/
        return null;
    }

    public void btnBackMainWindow(ActionEvent actionEvent) throws IOException {
//        Navigation.navigation(Routes.DASHBOARD,mealPackagesId);
        mealPackagesId.getChildren().clear();
    }

    public void initialize() {
        /*colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomers(searchText);

        customerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            searchCustomers(searchText);
        });

        setPatterns();*/



    }

    private void setData(CustomerTm tm) {
        txtCustomerId.setText(tm.getId());
        txtCustomerName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        btnCustomer.setText("  Update Customer");
    }

    private void searchCustomers(String text) {

    }

    private void setPatterns() {

        Pattern userIdPattern = Pattern.compile(".*(C0)([1-9]{1})([0-9]{1}.*)");  //(c0)([1-9]{1})([0-9]{1})
        userId = userIdPattern.matcher(txtCustomerId.getText());

        Pattern userNamePattern = Pattern.compile(".*[a-zA-Z]{4,}"); //[a-zA-Z0-9]{4,}
        userName = userNamePattern.matcher(txtCustomerName.getText());

        Pattern userAddressPattern = Pattern.compile(".*[a-zA-Z0-9]{4,}"); //^[a-zA-Z0-9]{4,}$
        userAddress = userAddressPattern.matcher(txtAddress.getText());

        Pattern userContactPattern = Pattern.compile(".*(?:7|0|(?:\\+94))[0-9]{9,10}");//.*^(?:7|0|(?:\+94))[0-9]{9,10}$*
        userContact = userContactPattern.matcher(txtContact.getText());

    }

    private void clearFields() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void btnSaveCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerRepository customerRepository=new CustomerRepository();
        Customer customer=getCustomerEntity();
        customerRepository.saveCustomer(customer);
    }

    private Customer getCustomerEntity(){
        Customer customer=new Customer();
        customer.setId(String.valueOf(txtCustomerId.getText()));
        customer.setName(txtCustomerName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setContact(txtContact.getText());
        /*customer.setId("C001");
        customer.setName("kamal");
        customer.setAddress("gall");
        customer.setContact("012121");*/
        return  customer;
    }

    public void btnClearDatan(ActionEvent actionEvent) {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void btnNewCustomer(ActionEvent actionEvent) {
        btnCustomer.setText("Save Customer");
    }
}



