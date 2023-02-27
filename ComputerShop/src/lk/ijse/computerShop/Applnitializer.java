package lk.ijse.computerShop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.computerShop.repository.CustomerRepository;
import lk.ijse.computerShop.to.Customer;

import java.io.IOException;
import java.net.URL;

public class Applnitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/computerShop/view/UserLoginFrom.fxml"))));
        primaryStage.setTitle("Computer Shop");
        primaryStage.show();
        /*URL resource = this.getClass().getResource("/lk/ijse/computerShop/view/DashBordForm.fxml");
        Parent window = FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard Form");
        primaryStage.centerOnScreen();



        primaryStage.show();*/

        /*CustomerRepository customerRepository=new CustomerRepository();
        Customer customer=new Customer("C001","Dushan","galle","088788");
        customerRepository.saveCustomer(customer);*/

    }
}
