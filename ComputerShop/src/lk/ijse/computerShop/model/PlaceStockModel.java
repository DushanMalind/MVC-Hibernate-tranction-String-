package lk.ijse.computerShop.model;

import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.PlaceStock;
import lk.ijse.computerShop.to.Stock;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceStockModel {
    public static boolean placeOrder(PlaceStock placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isOrderAdded = StockModel.save(new Stock(placeOrder.getStockId(),placeOrder.getSupId(),LocalDate.now()));
            if (isOrderAdded) {
//                boolean isUpdated = ItemModel2.updateQty(placeOrder.getOrderDetails());
//                if (isUpdated) {
                    boolean isOrderDetailAdded = StockDeatilsModel.saveOrderDetails(placeOrder.getOrderDetails());
                    if (isOrderDetailAdded) {
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
//            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }
}
