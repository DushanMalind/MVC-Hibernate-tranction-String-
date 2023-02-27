package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.repository.ItemRepository;
import lk.ijse.computerShop.to.ItemDeatils;
import lk.ijse.computerShop.view.tm.ItemTm;
import lk.ijse.computerShop.to.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemFromController {

    public AnchorPane ItemAchorPane;
    public TextField txtItemId;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public Button btnItem;
    public TableView <ItemTm> itemTable;
    public TableColumn colItem;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colOption;
    public TextField txtSearch;
    private String searchText="";

    private Matcher userId;
    private Matcher userName;
    private Matcher userAddress;
    private Matcher userContact;

    public void initialize(){
        colItem.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomers(searchText);

        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (null!=newValue) {
                setData(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchCustomers(searchText);
        });

        setPatterns();

    }
    private void setData(ItemTm tm){
        txtItemId.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
        txtQty.setText(String.valueOf(tm.getQtyOnHand()));
        btnItem.setText("  Update Item");
    }

    private void searchCustomers(String text){

        /*try {
            ObservableList<ItemTm> tmList= FXCollections.observableArrayList();

            Connection connection= DBConnection.getDbConnection().getConnection();
            String sql="select * from item";
            PreparedStatement statement=connection.prepareStatement(sql);
            ResultSet set=statement.executeQuery();

            while (set.next()){
                Button btn=new Button("Delete");
                ItemTm tm=new ItemTm(set.getString(1),
                        set.getString(2),
                        set.getDouble(3),
                        set.getInt(4),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Deleted ?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){

                        try {
                            Connection connection1=DBConnection.getDbConnection().getConnection();
                            String sql1="Delete from item where itemId=?";
                            PreparedStatement statement1=connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getCode());

                            if (statement1.executeUpdate()>0){
                                searchCustomers(searchText);
                                new Alert(Alert.AlertType.INFORMATION,"Item Deleted").show();
                            }else {
                                new Alert(Alert.AlertType.INFORMATION,"Item Not Deleted").show();
                            }

                        }catch (ClassNotFoundException | SQLException e){

                        }
                        *//*boolean isDeleted=CustomerDataBase.customerTable.remove(c);*//*
                    }
                });
            }
            itemTable.setItems(tmList);
        }catch (ClassNotFoundException | SQLException e){

        }*/
    }

    private void setPatterns() {

        Pattern userIdPattern = Pattern.compile(".*(P0)([0-9]{1})([0-9]{1}.*)");  //(c0)([1-9]{1})([0-9]{1})
        userId = userIdPattern.matcher(txtItemId.getText());

        Pattern userNamePattern = Pattern.compile(".*[a-zA-Z]{4,}"); //[a-zA-Z0-9]{4,}
        userName = userNamePattern.matcher(txtDescription.getText());

        /*Pattern userAddressPattern = Pattern.compile(".*[^0-9].*"); //^[a-zA-Z0-9]{4,}$
        userAddress = userAddressPattern.matcher(txtUnitPrice.getText());*/

      /*  Pattern userContactPattern = Pattern.compile("");//.*^(?:7|0|(?:\+94))[0-9]{9,10}$*
        userContact = userContactPattern.matcher(txtQty.getText());*/

    }

    public void btnNewItem(ActionEvent actionEvent) {
        btnItem.setText("Save Item");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        ItemRepository itemRepository=new ItemRepository();
        Item item=getItemEntity();
        itemRepository.saveItem(item);

    }

    private  Item getItemEntity(){
        Item item=new Item();
        item.setCode(Long.parseLong(txtItemId.getText()));
        item.setDescription(txtDescription.getText());
        item.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
        item.setQtyOnHand(Integer.parseInt(txtQty.getText()));
        return item;
    }

    private void clearFields(){
        txtItemId.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
    }

    public void btnClear(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnbackWindow(ActionEvent actionEvent) {
        ItemAchorPane.getChildren().clear();
    }

    public static ArrayList<Item> getAllItem() throws ClassNotFoundException, SQLException {

      /*  Connection connection =DBConnection.getDbConnection().getConnection();

        ResultSet result = connection.prepareStatement("SELECT * FROM item").executeQuery();

        ArrayList<Item> data = new ArrayList();

        while (result.next()) {

            Item c = new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4)
            );

            data.add(c);
        }

        return data;*/
        return  null;
    }

   /* public static boolean updateStock(ArrayList<ItemDeatils> orderDetails) throws ClassNotFoundException, SQLException {
        for (ItemDeatils detail : orderDetails) {
            if (!updateItem(detail)) {
                return false;
            }
        }
        return true;
    }

    public static boolean updateItem(ItemDeatils detail) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getDbConnection().getConnection().prepareStatement("Update item set qtyOnhand=qtyOnhand-? where itemId=?");
        stm.setObject(1, detail.getQty());
        stm.setObject(2, detail.getItemCode());

        return stm.executeUpdate() > 0;
    }*/
}
