package lk.ijse.computerShop.view.tm;

import javafx.scene.control.Button;

public class orderDeatilsTm {
    private String ordId;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private Button btn;

    public orderDeatilsTm() {
    }

    public orderDeatilsTm(String ordId, String itemCode, int qty, double unitPrice, Button btn) {
        this.ordId = ordId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.btn = btn;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "orderDeatilsTm{" +
                "ordId='" + ordId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", btn=" + btn +
                '}';
    }
}
