package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentanaAltas extends JFrame {
    JTextField txtNombre, txtObservaciones;
    Connection conexion;

    public VentanaAltas(Connection conexion){

        this.conexion=conexion;

        setBounds(100, 100, 500, 300);

        getContentPane().setLayout(new FlowLayout());

        JLabel lblNombre=new JLabel("Nombre:");
        add(lblNombre);

        txtNombre=new JTextField("", 40);
        add(txtNombre);

        txtObservaciones=new JTextField("Observaciones", 40);
        add(txtObservaciones);

        JButton btnAlta=new JButton("Añadir equipo");
        btnAlta.addActionListener(new CallBackAlta());
        add(btnAlta);

    }

    private class CallBackAlta implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            PreparedStatement s=null;
            String consulta="INSERT INTO equipos (nombre, observaciones) " +
                    "VALUES (?, ?)";
            try {
                s=conexion.prepareStatement(consulta);

                s.setString(1, txtNombre.getText());
                s.setString(2, txtObservaciones.getText());

                s.executeUpdate();

                txtNombre.setText("");
                txtObservaciones.setText("");

                JOptionPane.showMessageDialog(null, "Equipo añadido");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
