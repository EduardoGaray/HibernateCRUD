package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class VentanaPartidos extends JFrame {
    VentanaNuevoPartido ventanaNuevoPartido;
    JTextField txtVisitante, txtCasa, txtVisitante_Puntos, txtCasa_Puntos;
    JLabel lblVis, lblVisSCO, lblCas, lblCasSCO;
    JLabel lblId;
    Connection conexion;
    ResultSet resultSet;

    public VentanaPartidos(Connection conexion){
        this.conexion = conexion;
        setBounds(100, 100, 500, 300);
        getContentPane().setLayout(new FlowLayout());

        ventanaNuevoPartido = new VentanaNuevoPartido(conexion);

        lblId=new JLabel("0");
        add(lblId);
        lblVis=new JLabel("Visitante");
        //add(lblVis);
        txtVisitante=new JTextField("", 40);
        add(txtVisitante);
        lblVisSCO=new JLabel("Puntos:");
        //add(lblVisSCO);
        txtVisitante_Puntos=new JTextField("", 40);
        add(txtVisitante_Puntos);
        lblCas=new JLabel("Casa");
        //add(lblCas);
        txtCasa=new JTextField("", 40);
        add(txtCasa);
        lblCasSCO=new JLabel("Puntos:");
        //add(lblCasSCO);
        txtCasa_Puntos=new JTextField("", 40);
        add(txtCasa_Puntos);

        JButton btnAnterior=new JButton("Anterior");
        add(btnAnterior);
        btnAnterior.addActionListener(new VentanaPartidos.CallBackAnterior());

        JButton btnSiguiente=new JButton("Siguiente");
        add(btnSiguiente);
        btnSiguiente.addActionListener(new VentanaPartidos.CallBackSiguiente());

        JButton btnModificar=new JButton("Modificar");
        add(btnModificar);
        btnModificar.addActionListener(new VentanaPartidos.CallBackModificar());

        JButton btnEliminar=new JButton("Eliminar");
        add(btnEliminar);
        btnEliminar.addActionListener(new VentanaPartidos.CallBackEliminar());

        JButton btnNuevo=new JButton("Nuevo");
        add(btnNuevo);
        btnNuevo.addActionListener(new VentanaPartidos.CallBackNuevo());

        JButton btnCSV=new JButton("Exportar a CSV");
        add(btnCSV);
        btnCSV.addActionListener(new VentanaPartidos.CallBackCSV());

        JButton btnXML=new JButton("Exportar a XML");
        add(btnXML);
        btnXML.addActionListener(new VentanaPartidos.CallBackXML());

        try {
            //Creamos un statemen y realizamos la consulta

            String consulta="SELECT * FROM partidos";
            Statement statement;
            statement = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(consulta);

            //Nos colocamos en el primer elemento de la BBDD que devuelve la consulta
            resultSet.next();

            //Actualizamos los controles con los datos del primer registro
            lblId.setText(String.valueOf(resultSet.getInt("id")));
            txtVisitante.setText(resultSet.getString("visitante"));
            txtVisitante_Puntos.setText(String.valueOf(resultSet.getInt("visitante_puntos")));
            txtCasa.setText(resultSet.getString("casa"));
            txtCasa_Puntos.setText(String.valueOf(resultSet.getInt("casa_puntos")));

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    private class CallBackEliminar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(lblId.getText());
            int respuesta = JOptionPane.showConfirmDialog(null,"¿Estás seguro?", "Warning", JOptionPane.YES_NO_OPTION);
            if(respuesta ==JOptionPane.YES_OPTION){
                String consulta = "DELETE FROM partidos WHERE id=?";
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

    private class CallBackNuevo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {ventanaNuevoPartido.setVisible(true);
        }
    }

    private class CallBackXML implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            XML xml = new XML("root","","localhost:3306/equipos");
            try {
                xml.getTableData("partidos");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            xml.xmltoFile(xml.file,"xml");
            showMessageDialog(null,"Archivo XML exportado");
        }

    }

    private class CallBackCSV implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            CSV csv = new CSV();
            csv.export();
        }
    }

    private class CallBackModificar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(lblId.getText());
            String consulta = "UPDATE partidos SET visitante=?, visitante_puntos=?, casa=?, casa_puntos=? WHERE id=?";
            //String consulta = "UPDATE equipos SET nombre=?, observaciones=?, WHERE id=?";
            try {
                PreparedStatement ps = conexion.prepareStatement(consulta);
                ps.setString(1,txtVisitante.getText());
                ps.setString(2,txtVisitante_Puntos.getText());
                ps.setString(3,txtCasa.getText());
                ps.setString(4,txtCasa_Puntos.getText());
                ps.setInt(5,id);
                ps.executeUpdate();

                consulta="SELECT * FROM partidos";
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
                    txtVisitante.setText(resultSet.getString("visitante"));
                    txtVisitante_Puntos.setText(String.valueOf(resultSet.getInt("visitante_puntos")));
                    txtCasa.setText(resultSet.getString("casa"));
                    txtCasa_Puntos.setText(String.valueOf(resultSet.getInt("casa_puntos")));


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
                    txtVisitante.setText(resultSet.getString("visitante"));
                    txtVisitante_Puntos.setText(String.valueOf(resultSet.getInt("visitante_puntos")));
                    txtCasa.setText(resultSet.getString("casa"));
                    txtCasa_Puntos.setText(String.valueOf(resultSet.getInt("casa_puntos")));

                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
        }
    }
}
