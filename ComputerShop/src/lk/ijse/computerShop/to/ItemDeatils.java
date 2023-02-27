package lk.ijse.computerShop.to;

public class ItemDeatils {
    private String code;
    private String itemCode;
    private double unitPrice;
    private int qty;

    public ItemDeatils(String code, double unitPrice, int qty) {
    }

    public ItemDeatils(String code, String itemCode, double unitPrice, int qty) {
        this.code = code;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
