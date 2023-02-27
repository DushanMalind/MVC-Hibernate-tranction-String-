package lk.ijse.computerShop.model;

import lk.ijse.computerShop.to.CartDeatilStock;
import lk.ijse.computerShop.to.Item;
import lk.ijse.computerShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel2 {
    public static ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT code FROM Item";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static Item search(String code) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT *FROM item WHERE itemId = ?";
        ResultSet result = CrudUtil.execute(sql, code);

        if (result.next()) {
            return new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4)
            );
        }*/
        return null;
    }

    public static boolean updateQty(ArrayList<CartDeatilStock> cartDetails) throws SQLException, ClassNotFoundException {
        /*for (CartDeatilStock cartDetail : cartDetails) {
            if (!updateQty(new Item(cartDetail.getSupId(), cartDetail.getDescription(), cartDetail.getUnitPrice(), cartDetail.getQty()))) {
                return false;
            }
        }*/
        return true;
    }

    private static boolean updateQty(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET qtyOnhand = qtyOnhand +? WHERE itemId = ?";
        return CrudUtil.execute(sql, item.getQtyOnHand(), item.getCode());
    }
}
