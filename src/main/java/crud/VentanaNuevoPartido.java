package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaNuevoPartido extends JFrame {
    JTextField txtVisitante, txtCasa, txtVisitante_Puntos, txtCasa_Puntos;
    JLabel lblVis, lblVisSCO, lblCas, lblCasSCO;
    JLabel lblId;
    Connection conexion;
    ResultSet resultSet;

    public VentanaNuevoPartido(Connection conexion){

        this.conexion=conexion;

        setBounds(100, 100, 500, 300);

        getContentPane().setLayout(new FlowLayout());

        txtVisitante=new JTextField("Equipo Visitante", 40);
        add(txtVisitante);
        txtVisitante_Puntos=new JTextField("Puntos", 40);
        add(txtVisitante_Puntos);
        txtCasa=new JTextField("Equipo en Casa", 40);
        add(txtCasa);
        txtCasa_Puntos=new JTextField("Puntos", 40);
        add(txtCasa_Puntos);

        JButton btnAlta=new JButton("Añadir partido");
        btnAlta.addActionListener(new VentanaNuevoPartido.CallBackAlta());
        add(btnAlta);

    }

    private class CallBackAlta implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            PreparedStatement s=null;
            String consulta="INSERT INTO partidos (visitante, visitante_puntos, casa, casa_puntos) " +
                    "VALUES (?, ?, ?, ?)";
            try {
                s=conexion.prepareStatement(consulta);

                s.setString(1, txtVisitante.getText());
                s.setString(2, txtVisitante_Puntos.getText());
                s.setString(3, txtCasa.getText());
                s.setString(4, txtCasa_Puntos.getText());

                s.executeUpdate();

                txtVisitante.setText("");
                txtVisitante_Puntos.setText("");
                txtCasa.setText("");
                txtCasa_Puntos.setText("");

                consulta="SELECT * FROM partidos";
                Statement statement;
                statement = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                resultSet = statement.executeQuery(consulta);

                JOptionPane.showMessageDialog(null, "Partido añadido");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
