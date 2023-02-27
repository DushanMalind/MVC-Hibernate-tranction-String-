package lk.ijse.computerShop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.computerShop.model.PlaceStockModel;
import lk.ijse.computerShop.model.StockModel;
import lk.ijse.computerShop.to.*;
import lk.ijse.computerShop.view.tm.CartTm;
import lk.ijse.computerShop.view.tm.CartTm2;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class SupplyerPlaceOrderFromController {
    public AnchorPane supplyerAnchorPPane;
    public TextField txtStockId;
    public TextField txtStockDate;
    public ComboBox <String> cmdItemId;
    public ComboBox <String> cmdSupId;
    public TextField txtDescription;
    public TextField txtItemUnitPrice;
    public TextField txtQtyOn;
    public TextField txtSupName;
    public TextField txtModel;
    public TextField txtSuppUnitPrice;
    public TextField txtByQty;
    public TableView<CartTm2> tblSupplyerPlace;
    public TableColumn colSupId;
    public TableColumn colName;
    public TableColumn colModel;
    public TableColumn colUnitPrice;
    public TableColumn colOption;
    public Label lblTotal;
    public TableColumn colQTY;
    public TableColumn colTotal;


    public void initialize()  {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setItemDeatils();
        setSupplyerDeatils();
        setDate();
        loadNextOrderId();

        try {
            loardAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loardAllSupplyer();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmdItemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setItemDeatils();
            }
        });

        cmdSupId.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setSupplyerDeatils();
            }
        }));
    }

    private void setItemDeatils() {
       /* try {
            for (Item i:ItemFromController.getAllItem()
            ) {
                if (i.getCode().equals(cmdItemId.getValue())){
                    txtDescription.setText(i.getDescription());
                    txtItemUnitPrice.setText(String.valueOf(i.getUnitPrice()));
                    txtQtyOn.setText(String.valueOf(i.getQtyOnHand()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private void setSupplyerDeatils() {
        try {
            for (Supplyer c:SupplyerDeatilsController.getAllItem()
            ) {
                if (c.getSupId().equals(cmdSupId.getValue())){
                    txtSupName.setText(c.getName());
                    txtSuppUnitPrice.setText(String.valueOf(c.getUnitPrice()));
                    txtModel.setText(c.getModel());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loardAllItems() throws SQLException, ClassNotFoundException {
        /*for (Item c:ItemFromController.getAllItem()
        ) {
            cmdItemId.getItems().add(c.getCode());
        }*/
    }

    private void loardAllSupplyer() throws SQLException, ClassNotFoundException {
        for (Supplyer c:SupplyerDeatilsController.getAllItem()
        ) {
            cmdSupId.getItems().add(c.getSupId());
        }
    }

    private void setDate(){
        txtStockDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public void btnBackWindow(ActionEvent actionEvent) {
        supplyerAnchorPPane.getChildren().clear();
    }

    ObservableList<CartTm2> obList= FXCollections.observableArrayList();

    public void btnAddCartOnAction(ActionEvent actionEvent) {
        double unitPrice=Double.parseDouble(txtSuppUnitPrice.getText());
        int qty=Integer.parseInt(txtByQty.getText());
        double total=qty*unitPrice;
        Button button=new Button("Delete");

        int row=isAlredyExists(cmdSupId.getValue());
        if (row==-1){
            CartTm2 tm=new CartTm2(cmdSupId.getValue(),txtSupName.getText(),txtModel.getText(),unitPrice,qty,total,button);
            obList.add(tm);
            tblSupplyerPlace.setItems(obList);
        }else {
            int tempQty=obList.get(row).getQty()+qty;
            double tempTotal=unitPrice*tempQty;
            obList.get(row).setQty(tempQty);
            obList.get(row).setTotal(tempTotal);
            tblSupplyerPlace.refresh();
        }
        calculateTotal();
        clearFileds();
        cmdSupId.requestFocus();

        button.setOnAction(event -> {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure Delete Record",ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get()==ButtonType.YES){
                for (CartTm2 tm:obList
                ) {
                    if (tm.getSupId().equals(tm.getSupId())){
                        obList.remove(tm);
                        calculateTotal();
                        tblSupplyerPlace.refresh();
                        return;
                    }
                }
            }
        });

    }

    private void clearFileds() {
        txtSupName.clear();
        txtModel.clear();
        txtSuppUnitPrice.clear();
        txtModel.clear();
        txtByQty.clear();
        txtDescription.clear();
        txtItemUnitPrice.clear();
        txtQtyOn.clear();
    }

    private int isAlredyExists(String code) {
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getSupId().equals(code)){
                return i;
            }
        }
        return -1;
    }

    private void calculateTotal(){
        double total=0.00;
        for (CartTm2 tm:obList
        ) {
            total+=tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        String orderId = txtStockId.getText();
        String suId = cmdSupId.getValue();

        ArrayList<CartDeatilStock> cartDetails = new ArrayList<>();


        for (int i = 0; i < tblSupplyerPlace.getItems().size(); i++) {
            CartTm2 tm = obList.get(i);
            cartDetails.add(new CartDeatilStock(orderId,tm.getSupId(),tm.getQty(),tm.getModel(),tm.getUnitPrice()));
        }

        PlaceStock placeOrder = new PlaceStock(orderId,suId,cartDetails);
        try {
            boolean isPlaced = PlaceStockModel.placeOrder(placeOrder);
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
            String orderId = StockModel.generateNextOrderId();
            txtStockId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
