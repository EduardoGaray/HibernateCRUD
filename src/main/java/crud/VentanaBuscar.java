package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaBuscar extends JFrame {
    Connection conexion;
    JTextField txtBusqueda;
    JTextArea txtResultado;

    public VentanaBuscar(Connection conexion){
        this.conexion = conexion;
        setBounds(100, 100, 500, 300);
        getContentPane().setLayout(new FlowLayout());

        txtBusqueda = new JTextField(30);
        add(txtBusqueda);

        JButton btnBuscar = new JButton("Buscar");
        add(btnBuscar);
        btnBuscar.addActionListener(new CallBackBuscar());
        txtResultado = new JTextArea(10,40);
        txtResultado.setEditable(false);
        add(txtResultado);
    }

    private class CallBackBuscar implements ActionListener{
        @Override
                public void actionPerformed(ActionEvent e){
            String texto = txtBusqueda.getText();
            String consulta = "SELECT * FROM equipos WHERE nombre LIKE ?";
            try{
                PreparedStatement ps = conexion.prepareStatement(consulta);
                ps.setString(1,texto);
                ResultSet rs = ps.executeQuery();
                String resultado = "";
                while(rs.next()){
                    resultado = resultado + rs.getInt("id");
                    resultado += ", " + rs.getString("nombre");
                    resultado += ", " + rs.getString("observaciones")+"\n";

                }
                txtResultado.setText(resultado);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }


    }

}
