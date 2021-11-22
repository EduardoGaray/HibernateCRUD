package crud;

import org.hibernate.Session;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    static Connection conexion;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection( "jdbc:mysql://localhost:3306/equipos", "root", "");
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        VentanaPrincipal v=new VentanaPrincipal(conexion);
        v.setVisible(true);
//        GUI gui = new GUI();
//        Controlador controlador = new Controlador();
//        gui.crearinterfaz();



    }
}
