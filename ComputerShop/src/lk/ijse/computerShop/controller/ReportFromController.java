package lk.ijse.computerShop.controller;

import javafx.event.ActionEvent;
import lk.ijse.computerShop.db.DBConnection;


import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportFromController {
    public void btnCustomerOnAction(ActionEvent actionEvent) {
      /*  InputStream stream=this.getClass().getResourceAsStream("lk/ijse/computerShop/report/CustomerFrom.jrxml");

        HashMap<String,Object> hm=new HashMap<>();
        hm.put("Hello","ma");

        try {
            JasperReport jasperReport= JasperCompileManager.compileReport(stream);
            try {
                JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,hm, DBConnection.getDbConnection().getConnection());
                 JasperPrintManager.printReport(jasperPrint,true);
                JasperViewer.viewReport(jasperPrint);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }catch (JRException e){
        }*/
    }

    public void btnSupplyerOnAction(ActionEvent actionEvent) {
        /*InputStream stream=this.getClass().getResourceAsStream("lk/ijse/computerShop/report/Supplyer.jrxml");

        HashMap<String,Object>hm=new HashMap<>();
        hm.put("Hello","ma");

        try {
            JasperReport jasperReport= JasperCompileManager.compileReport(stream);
            try {
                JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,hm, DBConnection.getDbConnection().getConnection());
                 JasperPrintManager.printReport(jasperPrint,true);
                JasperViewer.viewReport(jasperPrint);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }catch (JRException e){

        }*/
    }

    public void btnServiceReport(ActionEvent actionEvent) {
        /*InputStream stream=this.getClass().getResourceAsStream("lk/ijse/computerShop/report/Serivicejrxml.jrxml");

        HashMap<String,Object>hm=new HashMap<>();
        hm.put("Hello","ma");

        try {
            JasperReport jasperReport= JasperCompileManager.compileReport(stream);
            try {
                JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,hm, DBConnection.getDbConnection().getConnection());
                 JasperPrintManager.printReport(jasperPrint,true);
                JasperViewer.viewReport(jasperPrint);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }catch (JRException e){
        }*/
    }

    public void btnItemReport(ActionEvent actionEvent) {
        /*InputStream stream=this.getClass().getResourceAsStream("lk/ijse/computerShop/report/Item.jrxml");

        HashMap<String,Object>hm=new HashMap<>();
        hm.put("Hello","ma");

        try {
            JasperReport jasperReport= JasperCompileManager.compileReport(stream);
            try {
                JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,hm, DBConnection.getDbConnection().getConnection());
//                 JasperPrintManager.printReport(jasperPrint,true);
                JasperViewer.viewReport(jasperPrint);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }catch (JRException e){
        }*/
    }
}
