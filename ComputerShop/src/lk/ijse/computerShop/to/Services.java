package lk.ijse.computerShop.to;

public class Services {
    private String serId;
    private String name;
    private String empId;
    private double price;

    public Services() {
    }

    public Services(String serId, String name, String empId, double price) {
        this.serId = serId;
        this.name = name;
        this.empId = empId;
        this.price = price;
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

    @Override
    public String toString() {
        return "Services{" +
                "serId='" + serId + '\'' +
                ", name='" + name + '\'' +
                ", empId='" + empId + '\'' +
                ", price=" + price +
                '}';
    }
}
