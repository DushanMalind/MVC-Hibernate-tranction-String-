package lk.ijse.computerShop.view.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.Date;

public class OrderTm {
    private String orderId;
    private Date date;
    private String customerId;
    private Button btn;

    public OrderTm() {
    }

    public OrderTm(String orderId, Date date, String customerId, Button btn) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.btn = btn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "OrderTm{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                ", btn=" + btn +
                '}';
    }
}
