package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class conectar {

    Connection conect = null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/restaurantes", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }

        return conect;
    }

    public void desconectar() {
        try {
            conect.close();
        } catch (Exception ex) {
        }
    }

}
