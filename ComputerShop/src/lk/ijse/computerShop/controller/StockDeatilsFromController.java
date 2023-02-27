package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.Order;
import lk.ijse.computerShop.to.Stock;
import lk.ijse.computerShop.view.tm.OrderTm;
import lk.ijse.computerShop.view.tm.StockTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class StockDeatilsFromController {
    public AnchorPane stockAnchorPane;
    public TextField txtStockId;
    public TextField txtSupId;
    public TextField txtDate;
    public TableView <StockTm>tblStock;
    public TableColumn colStockId;
    public TableColumn colSupId;
    public TableColumn colDate;
    public TableColumn colOption;

    public void initialize(){
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomer();

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        stockAnchorPane.getChildren().clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Stock stock=new Stock(txtStockId.getText(),txtSupId.getText(),LocalDate.now());
        Connection connection= DBConnection.getDbConnection().getConnection();
        String sql = "INSERT INTO stock Values(?,?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,stock.getStockId());
        statement.setString(2,stock.getSupId());
        statement.setString(3,String.valueOf(stock.getDate()));

        if (statement.executeUpdate()>0){
            new Alert(Alert.AlertType.INFORMATION,"Stock Save").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Stock not Save").show();
        }

    }

    private void  searchCustomer() {
//        String searchText = "%" + text + "%";

        try {
            ObservableList<StockTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getDbConnection().getConnection();
            String sql = "select * from stock ";
            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, searchText);
//            statement.setString(2, searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()) {

                Button btn = new Button("Delete");
                StockTm tm = new StockTm(set.getString(1),
                        set.getString(2),
                        set.getDate(3),btn);
                tmList.add(tm);


                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Customer Record Deleted?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {

                        try {
//                                Class.forName("com.mysql.cj.jdbc.Driver");
//
//                                Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
                            Connection connection1 = DBConnection.getDbConnection().getConnection();
                            String sql1 = "Delete from stock where stockId=?";
                            PreparedStatement statement1 = connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getStockId());

                            if (statement1.executeUpdate() > 0) {
                                searchCustomer();
                                new Alert(Alert.AlertType.INFORMATION, "Stock Deleted!").show();
//                                clearFields();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Stock NOT Deleted!").show();
                            }
                        } catch (ClassNotFoundException | SQLException e) {

                        }

//                            boolean isDeleted=DataBase.customerTable.remove(c);

                    }
                });
                tblStock.setItems(tmList);
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
    }
}
