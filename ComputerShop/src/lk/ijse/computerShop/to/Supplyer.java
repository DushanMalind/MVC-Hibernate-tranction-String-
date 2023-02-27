package lk.ijse.computerShop.to;

public class Supplyer {
    private String supId;
    private String name;
    private double unitPrice;
    private String model;

    public Supplyer() {
    }

    public Supplyer(String supId, String name, double unitPrice, String model) {
        this.supId = supId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.model = model;
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

    @Override
    public String toString() {
        return "Supplyer{" +
                "supId='" + supId + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", model='" + model + '\'' +
                '}';
    }
}
