package lk.ijse.computerShop.model;

import lk.ijse.computerShop.to.Stock;
import lk.ijse.computerShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockModel {
    public static boolean save(Stock order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO stock VALUES(?,?,?)";
        return CrudUtil.execute(sql,order.getStockId(),order.getSupId(),order.getDate());
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT stockId FROM stock ORDER BY stockId DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("W0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "W0" + id;
        }
        return "001";

    }
}
