package lk.ijse.computerShop.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane mealPackagesIdMain;
    public static void navigation(Routes routes,AnchorPane mealPackagesIdMain) throws IOException {
        Navigation.mealPackagesIdMain=mealPackagesIdMain;
        Navigation.mealPackagesIdMain.getChildren().clear();
        Stage window=(Stage) Navigation.mealPackagesIdMain.getScene().getWindow();

        switch (routes){
            case DASHBOARD:
                window.setTitle("DashBoard From");
                initUI("DashBordFrom.fxml");
                break;
            case CUSTOMER:
                window.setTitle("Customer From");
                initUI("CustomerFrom.fxml");
                break;
            case ITEM:
                window.setTitle("Item From");
                initUI("ItemFrom.fxml");
                break;
            case ORDERS:
                window.setTitle("Orders From");
                initUI("OrdersFrom.fxml");
                break;
            case PLACEORDERS:
                window.setTitle("Place Orders From");
                initUI("PlaceOrderFrom.fxml");
                break;
            case ORDERDETALIS:
                window.setTitle("Order Details From");
                initUI("OrderDeatilsFrom.fxml");
                break;
            case SUPPLYER:
                window.setTitle("Supplyer Details From");
                initUI("SupplyerDeatils.fxml");
                break;
            case STOCK:
                window.setTitle("Stock  From");
                initUI("StockDeatilsFrom.fxml");
                break;
            case DEATILSORDER:
                window.setTitle("Stock Details From");
                initUI("DeatilsStockFrom.fxml");
                break;
            case EMPLOYES:
                window.setTitle("Employes From");
                initUI("EmployesFrom.fxml");
                break;
            case SERVISE:
                window.setTitle("Servise From");
                initUI("ServiseFrom.fxml");
                break;
            case ORDERSSUPPLYER:
                window.setTitle("Oreders Suppplyer From");
                initUI("SupplyerPlaceOrderFrom.fxml");
                break;
            case LOGUOT:
                window.setTitle("lOGUT From");
                initUI("UserLoginFrom.fxml");
                break;
            case REPORT:
                window.setTitle("Report From");
                initUI("ReportFrom.fxml");
                break;
            case ABOUT:
                window.setTitle("About From");
                initUI("AboutFrom.fxml");
                break;
            case DASHBOARD2:
                window.setTitle("Dashboard1 From");
                initUI("DashBordFrom2.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.mealPackagesIdMain.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/computerShop/view/" + location)));
    }
}
