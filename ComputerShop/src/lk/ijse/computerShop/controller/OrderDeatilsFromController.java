package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.ItemDeatils;
import lk.ijse.computerShop.view.tm.OrderTm;
import lk.ijse.computerShop.view.tm.orderDeatilsTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class OrderDeatilsFromController {
    public AnchorPane orderAchorPane;
    public TableView <orderDeatilsTm>OrderdeatilsTable;
    public TableColumn colOrdId;
    public TableColumn colItemId;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colOption;


    public void initialize(){
        colOrdId.setCellValueFactory(new PropertyValueFactory<>("ordId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomer();

    }
   /* public static boolean saveOrderDetails(ArrayList<ItemDeatils> orderDetails) throws ClassNotFoundException, SQLException {

        for (ItemDeatils ord : orderDetails) {
            if (!addOrderDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    public static boolean addOrderDetail(ItemDeatils ord) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getDbConnection().getConnection().prepareStatement("Insert into orderdetails values(?,?,?,?)");

        stm.setObject(1, ord.getCode());
        stm.setObject(2, ord.getItemCode());
        stm.setObject(3, ord.getUnitPrice());
        stm.setObject(4, ord.getQty());

        return stm.executeUpdate() > 0;
    }
*/
    public void btnBackOnAction(ActionEvent actionEvent) {
        orderAchorPane.getChildren().clear();
    }

    private void  searchCustomer() {
//        String searchText = "%" + text + "%";

        try {
            ObservableList<orderDeatilsTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getDbConnection().getConnection();
            String sql = "select * from orderDetails ";
            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, searchText);
//            statement.setString(2, searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()) {

                Button btn = new Button("Delete");
                orderDeatilsTm tm = new orderDeatilsTm(set.getString(1),
                        set.getString(2),
                        set.getInt(3),
                        set.getDouble(4),btn);
                tmList.add(tm);


                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Customer Record Deleted?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {

                        try {

                            Connection connection1 = DBConnection.getDbConnection().getConnection();
                            String sql1 = "Delete from orderDetails where ordId=?";
                            PreparedStatement statement1 = connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getOrdId());

                            if (statement1.executeUpdate() > 0) {
                                searchCustomer();
                                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
//                                clearFields();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Customer NOT Deleted!").show();
                            }
                        } catch (ClassNotFoundException | SQLException e) {

                        }

//                            boolean isDeleted=DataBase.customerTable.remove(c);

                    }
                });
                OrderdeatilsTable.setItems(tmList);
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
    }
}
