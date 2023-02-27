package lk.ijse.computerShop.view.tm;

import javafx.scene.control.Button;

import java.util.Date;

public class StockTm {
    private String stockId;
    private String supId;
    private Date date;
    private Button btn;

    public StockTm() {
    }

    public StockTm(String stockId, String supId, Date date, Button btn) {
        this.stockId = stockId;
        this.supId = supId;
        this.date = date;
        this.btn = btn;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "StockTm{" +
                "stockId='" + stockId + '\'' +
                ", supId='" + supId + '\'' +
                ", date=" + date +
                ", btn=" + btn +
                '}';
    }
}
