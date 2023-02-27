package lk.ijse.computerShop.view.tm;

import javafx.scene.control.Button;

public class StockDeatilsFromTm {
    private String StockId;
    private String SupId;
    private int qty;
    private double unitPrice;
    private Button btn;

    public StockDeatilsFromTm() {
    }

    public StockDeatilsFromTm(String stockId, String supId, int qty, double unitPrice, Button btn) {
        StockId = stockId;
        SupId = supId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.btn = btn;
    }

    public String getStockId() {
        return StockId;
    }

    public void setStockId(String stockId) {
        StockId = stockId;
    }

    public String getSupId() {
        return SupId;
    }

    public void setSupId(String supId) {
        SupId = supId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "StockDeatilsFrom{" +
                "StockId='" + StockId + '\'' +
                ", SupId='" + SupId + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", btn=" + btn +
                '}';
    }
}
