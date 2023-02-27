package lk.ijse.computerShop.view.tm;

import javafx.scene.control.Button;

public class ServiseTm {
    private String serId;
    private String name;
    private String empId;
    private double price;
    private Button btn;

    public ServiseTm() {
    }

    public ServiseTm(String serId, String name, String empId, double price, Button btn) {
        this.serId = serId;
        this.name = name;
        this.empId = empId;
        this.price = price;
        this.btn = btn;
    }

    public String getSerId() {
        return serId;
    }

    public void setSerId(String serId) {
        this.serId = serId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ServiseTm{" +
                "serId='" + serId + '\'' +
                ", name='" + name + '\'' +
                ", empId='" + empId + '\'' +
                ", price=" + price +
                ", btn=" + btn +
                '}';
    }
}
