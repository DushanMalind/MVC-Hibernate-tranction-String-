package lk.ijse.computerShop.model;

import lk.ijse.computerShop.db.DBConnection;
import lk.ijse.computerShop.to.Order;
import lk.ijse.computerShop.to.PlaceOrder;
import lk.ijse.computerShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderModel {
    public static boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?,?,?)";
        return CrudUtil.execute(sql, order.getOrderId(), order.getDate(), order.getCustomerId());
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ordId FROM orders ORDER BY ordId DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "D00" + id;
        }
        return "01";
    }

   /* public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
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
}
