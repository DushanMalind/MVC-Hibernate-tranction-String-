package lk.ijse.computerShop.to;

public class Employes {
    private String empId;
    private String empName;
    private String address;
    private String cuId;

    public Employes() {
    }

    public Employes(String empId, String empName, String address, String cuId) {
        this.empId = empId;
        this.empName = empName;
        this.address = address;
        this.cuId = cuId;
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

    public String getCuId() {
        return cuId;
    }

    public void setCuId(String cuId) {
        this.cuId = cuId;
    }

    @Override
    public String toString() {
        return "Employes{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", address='" + address + '\'' +
                ", cuId='" + cuId + '\'' +
                '}';
    }
}
