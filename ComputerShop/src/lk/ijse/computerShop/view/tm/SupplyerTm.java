package lk.ijse.computerShop.view.tm;

import javafx.scene.control.Button;

public class SupplyerTm {
    private String supId;
    private String name;
    private double unitPrice;
    private String model;
    private Button btn;

    public SupplyerTm() {
    }

    public SupplyerTm(String supId, String name, double unitPrice, String model, Button btn) {
        this.supId = supId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.model = model;
        this.btn = btn;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "SupplyerTm{" +
                "supId='" + supId + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", model='" + model + '\'' +
                ", btn=" + btn +
                '}';
    }
}
