package lk.ijse.computerShop.view.tm;

import javafx.scene.control.Button;

public class EmploysTm {
    private String empId;
    private String empName;
    private String address;
    private String cusId;
    private Button btn;

    public EmploysTm() {
    }

    public EmploysTm(String empId, String empName, String address, String cusId, Button btn) {
        this.empId = empId;
        this.empName = empName;
        this.address = address;
        this.cusId = cusId;
        this.btn = btn;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "EmploysTm{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", address='" + address + '\'' +
                ", cusId='" + cusId + '\'' +
                ", btn=" + btn +
                '}';
    }
}
