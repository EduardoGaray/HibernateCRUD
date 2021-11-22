package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaModificar extends JFrame {
    JTextField txtNombre, txtObservaciones;
    JLabel lblId;
    Connection conexion;
    ResultSet resultSet;

    public VentanaModificar(Connection conexion) {

        this.conexion = conexion;
        setBounds(100, 100, 500, 300);
        getContentPane().setLayout(new FlowLayout());

        lblId=new JLabel("0");
        add(lblId);

        txtNombre=new JTextField("", 40);
        add(txtNombre);

        txtObservaciones=new JTextField("", 40);
        add(txtObservaciones);

        JButton btnAnterior=new JButton("Anterior");
        add(btnAnterior);
        btnAnterior.addActionListener(new CallBackAnterior());

        JButton btnSiguiente=new JButton("Siguiente");
        add(btnSiguiente);
        btnSiguiente.addActionListener(new CallBackSiguiente());

        JButton btnModificar=new JButton("Modificar");
        add(btnModificar);
        btnModificar.addActionListener(new CallBackModificar());

        JButton btnEliminar=new JButton("Eliminar");
        add(btnEliminar);
        btnEliminar.addActionListener(new CallBackEliminar());

        try {
            //Creamos un statemen y realizamos la consulta

            String consulta="SELECT * FROM equipos";
            Statement statement;
            statement = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(consulta);

            //Nos colocamos en el primer elemento de la BBDD que devuelve la consulta
            resultSet.next();

            //Actualizamos los controles con los datos del primer registro
            lblId.setText(String.valueOf(resultSet.getInt("id")));
            txtNombre.setText(resultSet.getString("nombre"));
            txtObservaciones.setText(resultSet.getString("observaciones"));

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }

    private class CallBackEliminar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(lblId.getText());
            int respuesta = JOptionPane.showConfirmDialog(null,"¿Estás seguro?", "Warning", JOptionPane.YES_NO_OPTION);
            if(respuesta ==JOptionPane.YES_OPTION){
                String consulta = "DELETE FROM equipos WHERE id=?";
                try{
                    PreparedStatement ps = conexion.prepareStatement(consulta);
                    ps.setInt(1,id);
                    ps.executeUpdate();
                }  catch (SQLException sqle){
                    sqle.printStackTrace();
                }
            } else {

            }
        }
    }

    private class CallBackModificar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(lblId.getText());
            String consulta = "UPDATE equipos SET nombre=?, observaciones=? WHERE id=?";
            //String consulta = "UPDATE equipos SET nombre=?, observaciones=?, WHERE id=?";
            try {
                PreparedStatement ps = conexion.prepareStatement(consulta);
                ps.setString(1,txtNombre.getText());
                ps.setString(2,txtObservaciones.getText());
                ps.setInt(3,id);
                ps.executeUpdate();

                consulta="SELECT * FROM equipos";
                Statement statement;
                statement = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                resultSet = statement.executeQuery(consulta);



            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    private class CallBackSiguiente implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(resultSet.next()) {
                    //Actualizamos los controles con los datos del primer registro
                    lblId.setText(String.valueOf(resultSet.getInt("id")));
                    txtNombre.setText(resultSet.getString("nombre"));
                    txtObservaciones.setText(resultSet.getString("observaciones"));

                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
        }
    }

    private class CallBackAnterior implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(resultSet.previous()) {
                    //Actualizamos los controles con los datos del primer registro
                    lblId.setText(String.valueOf(resultSet.getInt("id")));
                    txtNombre.setText(resultSet.getString("nombre"));
                    txtObservaciones.setText(resultSet.getString("observaciones"));

                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
        }
    }
}
