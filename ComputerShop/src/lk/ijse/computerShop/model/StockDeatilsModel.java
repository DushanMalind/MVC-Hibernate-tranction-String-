package lk.ijse.computerShop.model;

import lk.ijse.computerShop.to.CartDeatilStock;
import lk.ijse.computerShop.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockDeatilsModel {
    public static boolean saveOrderDetails(ArrayList<CartDeatilStock> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDeatilStock cartDetail : cartDetails) {
            if (!save(cartDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(CartDeatilStock cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO stockDetails VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, cartDetail.getStockId(), cartDetail.getSupId(), cartDetail.getQty(), cartDetail.getUnitPrice());
    }
}
