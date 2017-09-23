/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ruben
 */
public class Login {
    
    int i =0; 
    public int validarIngreso(){
        String usuario = VentanaInicio.txUsuario.getText();
        
        String contraseña = String.valueOf(VentanaInicio.txContraseña.getPassword());
        
        int resultado = 0;
        
        String SSQL = "SELECT DNI FROM videoclub.usuarios WHERE DNI = '"+usuario+"'";
        
        Connection conexion = null; 
        
       
        try {

        conexion = DriverManager.getConnection("jdbc:mysql://192.168.157.134/videoclub", "root", "1234");
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(SSQL);

        if(rs.next()){

            resultado=1;

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);

    }finally{


        try {

            conexion.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

        }

    }

return resultado;

}
}
