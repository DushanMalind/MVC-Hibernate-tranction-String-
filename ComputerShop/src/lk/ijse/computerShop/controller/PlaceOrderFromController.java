package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.db.ItemDatabase;
import lk.ijse.computerShop.view.tm.CartTm;
import lk.ijse.computerShop.model.OrderModel;
import lk.ijse.computerShop.model.PlaceOrderModel;
import lk.ijse.computerShop.to.*;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFromController {
    public AnchorPane placeAccorPane;
    public TextField txtDate;
    public ComboBox <String> cmdCustomerIds;
    public ComboBox <String> cmdItemIds;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TableView <CartTm> tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOption;
    public TextField txtQtyOnHand;
    public Label lblTotal;
    public TextField txtOrderId;


    public void initialize()  {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        setDate();
        try {
            loardAllCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loardAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadNextOrderId();

        cmdCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setCustomerDeatils();
            }
        });

        cmdItemIds.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setItemDeatils();
            }
        }));
    }

    private void setItemDeatils() {
        /*try {
            for (Item i:ItemFromController.getAllItem()
                 ) {
                if (i.getCode().equals(cmdItemIds.getValue())){
                    txtDescription.setText(i.getDescription());
                    txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
                    txtQty.setText(String.valueOf(i.getQtyOnHand()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private void setCustomerDeatils() {
       /* try {
            for (Customer c:CustomerFormController.getAllCustomer()
                 ) {
                if (c.getId().equals(cmdCustomerIds.getValue())){
                    txtName.setText(c.getName());
                    txtAddress.setText(c.getAddress());
                    txtContact.setText(c.getContact());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private void loardAllItems() throws SQLException, ClassNotFoundException {
      /*  for (Item c:ItemFromController.getAllItem()
        ) {
            cmdItemIds.getItems().add(c.getCode());
        }*/
    }

    private void loardAllCustomer() throws SQLException, ClassNotFoundException {
       /* for (Customer c:CustomerFormController.getAllCustomer()
             ) {
            cmdCustomerIds.getItems().add(c.getId());
        }*/
    }

    private void setDate(){
        /*Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String d=df.format(date);
        txtDate.setText(d);*/
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }

    public void btnBackWindow(ActionEvent actionEvent) {
        placeAccorPane.getChildren().clear();
    }

    ObservableList<CartTm>obList= FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        int qty=Integer.parseInt(txtQtyOnHand.getText());
        double total=qty*unitPrice;
        Button button=new Button("Delete");

        int row=isAlredyExists(cmdItemIds.getValue());
        if (row==-1){
            CartTm tm=new CartTm(cmdItemIds.getValue(),txtDescription.getText(),unitPrice,qty,total,button);
            obList.add(tm);
            tblCart.setItems(obList);
        }else {
            int tempQty=obList.get(row).getQty()+qty;
            double tempTotal=unitPrice*tempQty;
            obList.get(row).setQty(tempQty);
            obList.get(row).setTotal(tempTotal);
            tblCart.refresh();
        }
        calculateTotal();
        clearFileds();
        cmdItemIds.requestFocus();

        button.setOnAction(event -> {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure Delete Record",ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get()==ButtonType.YES){
                for (CartTm tm:obList
                     ) {
                    if (tm.getCode().equals(tm.getCode())){
                        obList.remove(tm);
                        calculateTotal();
                        tblCart.refresh();
                        return;
                    }
                }
            }
        });
    }

    private void clearFileds() {
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtQty.clear();
    }

    private int isAlredyExists(String code) {
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getCode().equals(code)){
                return i;
            }
        }
        return -1;
    }

    private void calculateTotal(){
        double total=0.00;
        for (CartTm tm:obList
             ) {
            total+=tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

   /* public static String getNextOrderId() throws ClassNotFoundException, SQLException {
        Connection connection=DBConnection.getDbConnection().getConnection();
        ResultSet result = connection.prepareStatement("SELECT ordId FROM orders ORDER BY ordId DESC LIMIT 1").executeQuery();

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }
*/
   /* private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");

            System.out.println(split[1]);
            int id = Integer.parseInt(split[1]);

            id += 1;
            return "D0" + id;
        }
        return "001";

    }*/

    /*public static boolean placeOrder(Order order) throws ClassNotFoundException, SQLException {

        try {
            //sql automate nawathinawa
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);

            //add order
            PreparedStatement stm = DBConnection.getDbConnection().getConnection().prepareStatement("Insert into orders values(?,?,?)");
            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getDate());
            stm.setObject(3, order.getCusId());

            boolean isAddedOrder = stm.executeUpdate() > 0;

            if (isAddedOrder) {
                boolean saveOrderDetails = OrderDeatilsFromController.saveOrderDetails(order.getItemDeatils());

                if (saveOrderDetails) {
                    boolean updateStock = ItemFromController.updateStock(order.getItemDeatils());

                    if (updateStock) {
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
            }

            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }*/

    public void PlaceOrdeOnAction(ActionEvent actionEvent) {
       /* if (obList.isEmpty()) return;
        ArrayList<ItemDeatils> itemDeatils=new ArrayList<ItemDeatils>();
        for (CartTm tm:obList
             ) {
            itemDeatils.add(new ItemDeatils(tm.getCode(),tm.getUnitPrice(),tm.getQty()));
        }
        Order order=new Order(txtOrderId.getText(),new Date(),Double.parseDouble(lblTotal.getText()),cmdItemIds.getValue(),itemDeatils);

        OrdersDatabase.ordTable.add(order);
        mangeQty();
        clearAll();*/
        String orderId = txtOrderId.getText();
        String customerId =cmdCustomerIds.getValue();

        ArrayList<CartDetail> cartDetails = new ArrayList<>();


        for (int i = 0; i < tblCart.getItems().size(); i++) {
            CartTm tm = obList.get(i);
            cartDetails.add(new CartDetail(orderId,tm.getCode(),tm.getQty(),tm.getDescription(),tm.getUnitPrice()));
        }

        PlaceOrder placeOrder = new PlaceOrder(customerId, orderId, cartDetails);
        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(placeOrder);
            if (isPlaced) {
                obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            txtOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void mangeQty(){
        /*for (CartTm tm:obList
             ) {
            for (Item i:ItemDatabase.itemTable
                 ) {
                if (i.getCode().equals(tm.getCode())){
                    i.setQtyOnHand(i.getQtyOnHand()-tm.getQty());
                    break;
                }
            }
        }*/
    }

    private void clearAll() {
        obList.clear();
        calculateTotal();

        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        clearFileds();
    }
}
