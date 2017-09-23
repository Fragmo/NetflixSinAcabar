 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseListener;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Carrusel{
    Connection conexion;
    Statement estado; 
    ResultSet resultado;
    
    ArrayList <JLabel> caratulasJLabel = new <JLabel> ArrayList();
    ArrayList <ImageIcon> caratulasIcon = new <ImageIcon> ArrayList();
    int contador =0;
    int multiplicador =0; 
    
    
    
    public void carrusel(JPanel panelCarrusel, JLabel idPelicula, int contadorBoton, int i ){
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            //Indico los parametros de la conexion
            conexion = DriverManager.getConnection("jdbc:mysql://192.168.157.134/videoclub", "root", "1234"); // Localhost * ip

            //Realizo la conexion
            estado = conexion.createStatement();
            
            //Realizo la consulta
            resultado = estado.executeQuery("SELECT * FROM videoclub.peliculas");
            
             multiplicador++;  
         while (resultado.next()) {
             
             String aux = resultado.getString("id_pelicula");
URL direccioFotos = getClass().getResource("/caratulas/"+seleccionaFoto(idPelicula, aux, i ) + ".jpg");
ImageIcon fotillo = new ImageIcon(new ImageIcon(direccioFotos).getImage().
getScaledInstance(100, 200,Image.SCALE_DEFAULT));
        
//             System.out.println(fotoArreglada);
             caratulasIcon.add(contador, fotillo);
             JLabel lol = new JLabel();          
             lol.setBounds(0+90*contador,0 , 100, 200);
             lol.setIcon(fotillo);
//             lol(); mouse listener
             caratulasJLabel.add(lol);

          panelCarrusel.add(lol);
             contador  ++;
               

      
        
            }
            panelCarrusel.revalidate();
            panelCarrusel.repaint();
            
        }
        catch (ClassNotFoundException ex){
            System.out.println("NO SE HA ENCONTRADO EL DRIVER");
        }
        catch (SQLException ex){
            System.out.println("NO SE HA PODIDO CONECTAR CON EL SERVIDOR");

        }
            
        }
       
    
        public String seleccionaFoto(JLabel idPelicula, String aux, int i ){
        String foto1 = aux;
        
        
       // i = i+6*multiplicador;
//       int fotoInt = Integer.parseInt(aux) + (i ) ;
      //  int fotoInt = Integer.parseInt(aux);
        //foto1 = foto1.valueOf(fotoInt) ;
        String fotoArreglada = "";
        
          
        for(int j = foto1.length(); j<6; j++){
            fotoArreglada = fotoArreglada + "0";
        }
        fotoArreglada = fotoArreglada + foto1;
            System.err.println(fotoArreglada);
            
        return fotoArreglada;
        
    }
    }
