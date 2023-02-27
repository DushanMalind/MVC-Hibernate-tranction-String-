package lk.ijse.computerShop.model;

import lk.ijse.computerShop.to.Customer;
import lk.ijse.computerShop.util.CrudUtil;

import java.sql.SQLException;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Customer VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getId(),customer.getName(),customer.getAddress(),customer.getContact());
    }

    /*public static CustomerTm searchCustomer(String id) throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> tmList= FXCollections.observableArrayList();

        String sql="SELECT  * FROM Customer WHERE id = ?";
        ResultSet resultSet=CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return new CustomerTm(
              resultSet.getString(1),
              resultSet.getString(2),
              resultSet.getString(3),
              resultSet.getString(4)
            );
        }
        return null;

    }*/


}
