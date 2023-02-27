package lk.ijse.computerShop.model;

import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.Order;
import lk.ijse.computerShop.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrderModel.save(new Order(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getCustomerId()));
            if (isOrderAdded) {
                boolean isUpdated = ItemModel.updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
                    if (isOrderDetailAdded) {
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
    }
}










/*
try {
        DBConnection.getDbConnection().getConnection().setAutoCommit(false);
        boolean isOrderAdded = OrderModel.save(new Order(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getCustomerId()));
        if (isOrderAdded) {
        boolean isUpdated = ItemModel.updateQty(placeOrder.getOrderDetails());
        if (isUpdated) {
        boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
        if (isOrderDetailAdded) {
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
