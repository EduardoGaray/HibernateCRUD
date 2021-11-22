package crud;


import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class CSV {

    public void export() {

        String jdbcURL = "jdbc:mysql://localhost:3306/equipos";
        String username = "root";
        String password = "";

        String csvFilePath = "Partidos.csv";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM partidos";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("visitante,visitante_puntos,casa,casa_puntos");

            while (result.next()) {
                String visitante = result.getString("visitante");
                int visitante_puntos = result.getInt("visitante_puntos");
                String casa = result.getString("casa");
                int casa_puntos = result.getInt("casa_puntos");
                if (visitante == null) {
                    visitante = "";   // write empty value for null
                } else {
                    visitante = "\"" + visitante + "\""; // escape double quotes
                }
                String line = String.format(visitante,visitante_puntos,casa,casa_puntos);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            statement.close();
            fileWriter.close();
            showMessageDialog(null,"Archivo CSV exportado");

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }

    }

}
