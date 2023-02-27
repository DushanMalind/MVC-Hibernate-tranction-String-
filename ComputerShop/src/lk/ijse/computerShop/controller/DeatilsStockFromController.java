package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.view.tm.StockDeatilsFromTm;
import lk.ijse.computerShop.view.tm.orderDeatilsTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DeatilsStockFromController {
    public TableView <StockDeatilsFromTm> tblStockDetail;
    public TableColumn colStockId;
    public TableColumn colSupId;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public AnchorPane stockDeatilsAchorPane;
    public TableColumn colOption;


    public void initialize(){
        colStockId.setCellValueFactory(new PropertyValueFactory<>("StockId"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("SupId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomer();

    }

    private void  searchCustomer() {
//        String searchText = "%" + text + "%";

        try {
            ObservableList<StockDeatilsFromTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getDbConnection().getConnection();
            String sql = "select * from stockDetails ";
            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, searchText);
//            statement.setString(2, searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()) {

                Button btn = new Button("Delete");
                StockDeatilsFromTm tm = new StockDeatilsFromTm(set.getString(1),
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
                            String sql1 = "Delete from stockDetails where stockId=?";
                            PreparedStatement statement1 = connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getStockId());

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
                tblStockDetail.setItems(tmList);
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        stockDeatilsAchorPane.getChildren().clear();
    }
}
