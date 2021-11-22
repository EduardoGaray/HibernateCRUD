package crud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private static final long serialVersionUID = 1L;

    public void creartabla() {
        JFrame f;
        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("JTable Example");

        JTable j;
        // Data to be displayed in the JTable
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setLayout(null);
        f.setVisible(true);
    }
    public void crearinterfaz(){
        int x0 = 10;
        int x1 = 100;
        int y1 = 50;
        int w1 = 250;
        int h1 = 20;
        JFrame f=new JFrame("Centro deportivo Vladimir Tachenko");
        final JLabel nequipo = new JLabel("Registrar Equipo");
        final JLabel lname = new JLabel("Nombre");
        final JLabel lobs = new JLabel("Observaciones");
        final JTextField tf=new JTextField();
        final JTextField tf2 = new JTextField();
        final JTextField tf3 = new JTextField();
        nequipo.setBounds(x0,y1-30,150,20);
        lname.setBounds(x0,y1,50,20);
        lobs.setBounds(x0,y1+20,100,20);
        tf.setBounds(x1,y1, w1,20);
        tf2.setBounds(x1,y1+20, w1,20);
        JButton b=new JButton("Agregar");
        b.setBounds(x0,y1+50,95,30);
        b.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                Controlador add = new Controlador();
                add.nombre = tf.getText();
                add.observacion = tf2.getText();
                add.insertarentabla();
            }
        });
        JTable j;
        // Data to be displayed in the JTable
        String[][] data = {
                { "1", "Real Molina", "Nottingham Prisa" },
                { "2", "Tus muertos fc", "PepitoCipote el capullote" }
        };

        // Column Names
        String[] columnNames = { "ID", "Nombre", "Observaciones" };

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(x0, y1+80, 470, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        sp.setBounds(j.getBounds());
        f.add(sp);
        f.add(b);
        f.add(tf);
        f.add(tf2);
        f.add(tf3);
        f.add(nequipo);
        f.add(lname);
        f.add(lobs);
        f.setSize(640,480);
        f.setLayout(null);
        f.setVisible(true);
    }

}
