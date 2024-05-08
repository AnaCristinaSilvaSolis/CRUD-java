/*
Ana Cristina Silva Solis 2626998
Kelly Alejandra Arango Cardona 2626999
Maycoll D Carroll Gómez 2626998
 */
package jdbccrud;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCCRUD {

    public static void main(String[] args) {
        
        //configura la conexión a la base de datos
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3307/appsalon_mvc";
        
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,user,password);
            statement = conexion.createStatement(); //permite ejecutar SQL
            
            // Inserción de datos
            statement.executeUpdate("INSERT INTO clientes(nombre,apellido,telefono,correo) VALUES('ana','perez',123456,'aleja@correo.com')");
            System.out.println("Datos insertados correctamente.");
            
            // Consulta de datos
            rs = statement.executeQuery("SELECT * FROM clientes");
            while (rs.next()) {
                System.out.println(rs.getInt("id_cliente") + " : " +
                        rs.getString("nombre") + "  " +
                        rs.getString("apellido") + "  " +
                        rs.getString("telefono") + "  " +
                        rs.getString("correo"));
            }
            
            // Actualización de datos
            int filasActualizadas = statement.executeUpdate("UPDATE clientes SET telefono = 23456 WHERE nombre = 'ana'");
            System.out.println("Se han actualizado " + filasActualizadas + " filas.");
            
            // Eliminación de datos
            int filasEliminadas = statement.executeUpdate("DELETE FROM clientes WHERE nombre = 'ana'");
            System.out.println("Se han eliminado " + filasEliminadas + " filas.");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cierre de recursos
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

