package lk.ijse.computerShop.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.Order;
import lk.ijse.computerShop.view.tm.OrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class OrdersFromController {


    public AnchorPane OrdersAcnhor;
    public TextField txtOrdId;
    public TextField txtCusid;
    public TextField txtOrdDate;
    public TableView <OrderTm>ordersTable;
    public TableColumn colOrdId;
    public TableColumn colOrdDate;
    public TableColumn colOrdCusId;
    public TableColumn colOption;
    public Label lblDateTime;
    public TextField txtSearch;

    public void initialize(){
        colOrdId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrdDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrdCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomer();
    }
    public void btnBackWindo(ActionEvent actionEvent) {
        OrdersAcnhor.getChildren().clear();
    }

    public void btnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Order order=new Order(txtOrdId.getText(), LocalDate.now(),txtCusid.getText());
        Connection connection= DBConnection.getDbConnection().getConnection();
        String sql = "INSERT INTO orders Values(?,?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,order.getOrderId());
        statement.setString(2, String.valueOf(order.getDate()));
        statement.setString(3,order.getCustomerId());


        if (statement.executeUpdate()>0){
            new Alert(Alert.AlertType.INFORMATION,"Order Save").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Order not Save").show();
        }
    }

    private void  searchCustomer() {
//        String searchText = "%" + text + "%";

        try {
            ObservableList<OrderTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getDbConnection().getConnection();
            String sql = "select * from orders ";
            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, searchText);
//            statement.setString(2, searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()) {

                Button btn = new Button("Delete");
                OrderTm tm = new OrderTm(set.getString(1),
                        set.getDate(2),
                        set.getString(3), btn);
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
                            String sql1 = "Delete from orders where ordId=?";
                            PreparedStatement statement1 = connection1.prepareStatement(sql1);
                            statement1.setString(1, tm.getOrderId());

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
                ordersTable.setItems(tmList);
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
    }
}
