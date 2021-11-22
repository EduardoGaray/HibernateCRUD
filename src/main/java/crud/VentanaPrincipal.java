package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class VentanaPrincipal extends JFrame {
    JButton btnAltas, btnModificar, btnBuscar, btnResultados, btn;
    Connection conexion;
    VentanaAltas ventanaAltas;
    VentanaModificar ventanaModificar;
    VentanaBuscar ventanaBuscar;
    VentanaPartidos ventanaPartidos;

    public VentanaPrincipal(Connection conexion){
        this.conexion=conexion;
        setBounds(50, 50, 400, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        ventanaAltas=new VentanaAltas(conexion);
        ventanaModificar=new VentanaModificar(conexion);
        ventanaBuscar = new VentanaBuscar(conexion);
        ventanaPartidos = new VentanaPartidos(conexion);

        btnAltas=new JButton("Registrar Equipo");
        add(btnAltas);
        btnAltas.addActionListener(new CallBackVentanaAltas());

        btnModificar=new JButton("Actualizar Equipo");
        add(btnModificar);
        btnModificar.addActionListener(new CallBackVentanaModificar());

        btnBuscar=new JButton("Buscar Equipo");
        btnBuscar.addActionListener(new CallBackVentanaBuscar());
        add(btnBuscar);

        btnResultados=new JButton("Resultados de Partidos");
        btnResultados.addActionListener(new CallBackVentanaPartidos());
        add(btnResultados);

    }
    private class CallBackVentanaAltas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaAltas.setVisible(true);
        }
    }

    private class CallBackVentanaModificar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaModificar.setVisible(true);
        }
    }

    private class CallBackVentanaPartidos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaPartidos.setVisible(true);
        }
    }

    private class CallBackVentanaBuscar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaBuscar.setVisible(true);
        }
    }


}


