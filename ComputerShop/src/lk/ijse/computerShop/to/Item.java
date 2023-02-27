package lk.ijse.computerShop.to;

import javax.persistence.*;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name = "Item_code")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long code;
    @Column(name = "description")
    private String description;
    @Column(name = "unitPrice")
    private double unitPrice;
    @Column(name = "qtyOmHand")
    private int qtyOnHand;

    public Item() {
    }

    public Item(long code, String description, double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
