package codigo;

import java.awt.Image;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcPEN
 */
public class VentanaInicio extends javax.swing.JFrame {

    /**
     * 
     * 
     * Creates new form VentanaInicio
     */
    Login logeo = new Login();
    Carrusel miCarrusel = new Carrusel();
    int contadorBoton = 6;  
    int i = -6;
    static int ANCHOPANTALLA = 500;
    static int ALTOPANTALLA = 300;
    String [] pelis =  new String[8];
    
    
    
    
        public VentanaInicio() {
        initComponents();
        inicializarPantalla();
        usuarioIniciado();
        listadoPeliculas();
        //idPeliculalistado();
        miCarrusel.carrusel(panelCarrusel, idPelicula, contadorBoton, i);
        
        
        seleccionPeliculas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange()== ItemEvent.SELECTED){
                   String valor = String.valueOf(seleccionPeliculas.getSelectedIndex() +1);
                   idPelicula.setText(valor);
                   reseña.setText(pelis[7]);
                   
            }}
        });
        
        
    }
    
    
     Connection conexion; //alamacena la conexión al servidor de la BBDD
    
    Statement estado; //almacena el estado de la conexión
    Statement estado2; //almacena el estado de la conexión
    
    ResultSet resultado; // almacena el resultado de la consulta a la BBDD
    
    ResultSet idPeliculars;
    
//    DefaultListModel modelo = new DefaultListModel();
    
   // String [][] arrayResultado;
   // ArrayList listaPeliculas;
    
  
     
     //inicializa el menu de inicio de usuario
    private void inicializarPantalla(){
            dialogInicioSesion.setSize(ANCHOPANTALLA, ALTOPANTALLA);
            dialogInicioSesion.setModal(true);
            dialogInicioSesion.setVisible(true);
        

// para la foto de perfil inicial
        int ancho = infoFoto.getWidth();
        int alto = infoFoto.getHeight();
        
        //almacenamos numero de DNI para añadirla a la URL de fotos
        String usuario = VentanaInicio.txUsuario.getText();
        
        //almacenamos numero
  
        URL direccioFotos = getClass().getResource("/fotosUsuarios/"+usuario + ".jpg");
        
     ImageIcon ImagenDefult = new ImageIcon(new ImageIcon(direccioFotos).getImage().
             getScaledInstance(ancho, alto,Image.SCALE_DEFAULT));
        
    
     try {
            Class.forName("com.mysql.jdbc.Driver");
            //Indico los parametros de la conexion
            conexion = DriverManager.getConnection("jdbc:mysql://192.168.157.134/videoclub", "root", "1234"); // Localhost * ip

            //Realizo la conexion
            estado = conexion.createStatement();
            
            //Realizo la consulta
            resultado = estado.executeQuery("SELECT * FROM videoclub.usuarios WHERE DNI ='"+usuario+"'");
            
               
         while (resultado.next()) {
            infoFoto.setIcon(ImagenDefult);
            }
            
        }
        catch (ClassNotFoundException ex){
            System.out.println("NO SE HA ENCONTRADO EL DRIVER");
        }
        catch (SQLException ex){
            System.out.println("NO SE HA PODIDO CONECTAR CON EL SERVIDOR");

        }
            
        }

    
    //muestra toda la informacion de las peliculas una vez iniciada la sesión
     private void listadoPeliculas(){
         
       
         try {
            Class.forName("com.mysql.jdbc.Driver");
            //Indico los parametros de la conexion
            conexion = DriverManager.getConnection("jdbc:mysql://192.168.157.134/videoclub", "root", "1234"); // Localhost * ip

            //Realizo la conexion
            estado = conexion.createStatement();
            
            //Realizo la consulta
            resultado = estado.executeQuery("SELECT * FROM videoclub.peliculas");
           
               
         while (resultado.next()) {
             //String [] pelis =  new String[8];
             pelis[0] = resultado.getString("id_pelicula");
             pelis[1] = resultado.getString("titulo");
             pelis[2] = resultado.getString("año");
             pelis[3] = resultado.getString("pais");
             pelis[4] = resultado.getString("genero");
             pelis[5] = resultado.getString("imdb");
             pelis[6] = resultado.getString("clasificacion_imdb");
             pelis[7] = resultado.getString("resumen");

             seleccionPeliculas.addItem((pelis[1]));
             reseña.append(pelis[7]);
             
             
             
//                 modelo.addElement(resultado.getString("titulo" + "\n"));
//                  jList1.setSelectedIndex(0);
//                  modelo.setElementAt(jList1,0);
         }
         
        
            
        }
        catch (ClassNotFoundException ex){
            System.out.println("NO SE HA ENCONTRADO EL DRIVER");
        }
        catch (SQLException ex){
            System.out.println("NO SE HA PODIDO CONECTAR CON EL SERVIDOR");

        }

  }
     
 
   
   
     
     
   //muestra toda la informacion del usuario en pantalla una vez iniciada la sesión
 private void usuarioIniciado(){
    

        
        //almacenamos numero de DNI para añadirla a la URL de fotos
        String usuario = VentanaInicio.txUsuario.getText();
        
        //almacenamos numero
  
        URL direccioFotos = getClass().getResource("/fotosUsuarios/"+usuario + ".jpg");
        
     ImageIcon ImagenDefult = new ImageIcon(new ImageIcon(direccioFotos).getImage().
             getScaledInstance(199, 161,Image.SCALE_DEFAULT));
        
    
     try {
            Class.forName("com.mysql.jdbc.Driver");
            //Indico los parametros de la conexion
            conexion = DriverManager.getConnection("jdbc:mysql://192.168.157.134/videoclub", "root", "1234"); // Localhost * ip

            //Realizo la conexion
            estado = conexion.createStatement();
            
            //Realizo la consulta
            resultado = estado.executeQuery("SELECT * FROM videoclub.usuarios WHERE DNI ='"+usuario+"'");
            
               
         while (resultado.next()) {
                 infoEmail1.setText(resultado.getString("email"));
                 infoApellido1.setText(resultado.getString("Apellido"));
                 infoNombre1.setText(resultado.getString("Nombre"));
                 infoFoto1.setIcon(ImagenDefult);
                
                 
               
                
               
            }
            
        }
        catch (ClassNotFoundException ex){
            System.out.println("NO SE HA ENCONTRADO EL DRIVER");
        }
        catch (SQLException ex){
            System.out.println("NO SE HA PODIDO CONECTAR CON EL SERVIDOR");

        }
        

 }
 

    

        
   


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogInicioSesion = new javax.swing.JDialog();
        btnSesionAceptar = new javax.swing.JButton();
        txContraseña = new javax.swing.JPasswordField();
        inicioDeSesionD = new javax.swing.JLabel();
        UsuarioUsuarioD = new javax.swing.JLabel();
        ContraseñaD = new javax.swing.JLabel();
        txUsuario = new javax.swing.JTextField();
        exit = new javax.swing.JButton();
        dialogPerfil = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        infoNombre1 = new javax.swing.JLabel();
        infoApellido1 = new javax.swing.JLabel();
        infoEmail1 = new javax.swing.JLabel();
        infoFoto1 = new javax.swing.JLabel();
        salirP = new javax.swing.JButton();
        idPelicula = new javax.swing.JLabel();
        seleccionPeliculas = new javax.swing.JComboBox<>();
        infoFoto = new javax.swing.JLabel();
        panelCarrusel = new javax.swing.JPanel();
        adelante = new javax.swing.JButton();
        atras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reseña = new javax.swing.JTextArea();

        dialogInicioSesion.setLocation(new java.awt.Point(400, 300));
        dialogInicioSesion.setUndecorated(true);

        btnSesionAceptar.setText("ACEPTAR");
        btnSesionAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSesionAceptarMouseClicked(evt);
            }
        });
        btnSesionAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSesionAceptarActionPerformed(evt);
            }
        });

        inicioDeSesionD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inicioDeSesionD.setText("INICIO DE SESIÓN");

        UsuarioUsuarioD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UsuarioUsuarioD.setText("USUARIO");

        ContraseñaD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContraseñaD.setText("CONTRASEÑA");

        txUsuario.setText("5036787");
        txUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txUsuarioActionPerformed(evt);
            }
        });

        exit.setText("Salir");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogInicioSesionLayout = new javax.swing.GroupLayout(dialogInicioSesion.getContentPane());
        dialogInicioSesion.getContentPane().setLayout(dialogInicioSesionLayout);
        dialogInicioSesionLayout.setHorizontalGroup(
            dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogInicioSesionLayout.createSequentialGroup()
                .addGroup(dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogInicioSesionLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(btnSesionAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialogInicioSesionLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ContraseñaD, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UsuarioUsuarioD, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txUsuario)
                            .addComponent(txContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogInicioSesionLayout.createSequentialGroup()
                .addGap(0, 149, Short.MAX_VALUE)
                .addGroup(dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogInicioSesionLayout.createSequentialGroup()
                        .addComponent(inicioDeSesionD, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogInicioSesionLayout.createSequentialGroup()
                        .addComponent(exit)
                        .addGap(38, 38, 38))))
        );
        dialogInicioSesionLayout.setVerticalGroup(
            dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogInicioSesionLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(inicioDeSesionD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsuarioUsuarioD))
                .addGap(29, 29, 29)
                .addGroup(dialogInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContraseñaD)
                    .addComponent(txContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(btnSesionAceptar)
                .addGap(18, 18, 18)
                .addComponent(exit)
                .addGap(24, 24, 24))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        infoNombre1.setText("nombre");

        infoApellido1.setText("apellido");

        infoEmail1.setText("email");
        infoEmail1.setToolTipText("");

        infoFoto1.setText("foto");

        salirP.setText("salir");
        salirP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                salirPMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salirP)
                .addGap(59, 59, 59))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addComponent(infoFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(infoFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(infoNombre1)
                        .addGap(54, 54, 54)
                        .addComponent(infoApellido1)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(salirP))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(infoEmail1)
                        .addGap(64, 64, 64)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogPerfilLayout = new javax.swing.GroupLayout(dialogPerfil.getContentPane());
        dialogPerfil.getContentPane().setLayout(dialogPerfilLayout);
        dialogPerfilLayout.setHorizontalGroup(
            dialogPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
            .addGroup(dialogPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dialogPerfilLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        dialogPerfilLayout.setVerticalGroup(
            dialogPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
            .addGroup(dialogPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dialogPerfilLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ventana1");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        idPelicula.setText("1");

        seleccionPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionPeliculasActionPerformed(evt);
            }
        });

        infoFoto.setText("foto");
        infoFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                infoFotoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelCarruselLayout = new javax.swing.GroupLayout(panelCarrusel);
        panelCarrusel.setLayout(panelCarruselLayout);
        panelCarruselLayout.setHorizontalGroup(
            panelCarruselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        panelCarruselLayout.setVerticalGroup(
            panelCarruselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        adelante.setText("->");
        adelante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                adelanteMousePressed(evt);
            }
        });

        atras.setText("<-");
        atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                atrasMousePressed(evt);
            }
        });

        reseña.setColumns(20);
        reseña.setRows(5);
        jScrollPane1.setViewportView(reseña);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelCarrusel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(seleccionPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(adelante, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seleccionPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(panelCarrusel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(atras)
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(adelante)
                        .addGap(184, 184, 184))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSesionAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionAceptarActionPerformed
      
    }//GEN-LAST:event_btnSesionAceptarActionPerformed

    private void btnSesionAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSesionAceptarMouseClicked
     
          if(logeo.validarIngreso() == 1){
            this.dispose();

        JOptionPane.showMessageDialog(null, "Bienvenido\n Has ingresado "
        + "satisfactoriamente al sistema", "Mensaje de bienvenida",
        JOptionPane.INFORMATION_MESSAGE);

        dialogInicioSesion.setVisible(false);

}else {
                    
        JOptionPane.showMessageDialog(null, "Acceso denegado:\n"
        + "Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado",
        JOptionPane.ERROR_MESSAGE);
       }
      
    }//GEN-LAST:event_btnSesionAceptarMouseClicked

    private void seleccionPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionPeliculasActionPerformed
       // miCarrusel.carrusel(panelCarrusel, idPelicula, contadorBoton, i);
    }//GEN-LAST:event_seleccionPeliculasActionPerformed

    private void txUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txUsuarioActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void infoFotoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoFotoMousePressed
        dialogPerfil.setSize(630,471);
         dialogPerfil.setModal(true);
        dialogPerfil.setVisible(true);
        usuarioIniciado();
    }//GEN-LAST:event_infoFotoMousePressed

    private void salirPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirPMousePressed

        dialogPerfil.setVisible(false);
    }//GEN-LAST:event_salirPMousePressed

    private void atrasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasMousePressed
        //miCarrusel.lol.setBounds(7, i, 90, 90);
        System.out.println(i + "multiplicador = " + miCarrusel.multiplicador);
    }//GEN-LAST:event_atrasMousePressed

    private void adelanteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adelanteMousePressed
    miCarrusel.caratulasJLabel.clear();
    panelCarrusel.revalidate();
    panelCarrusel.repaint();

    
    miCarrusel.carrusel(panelCarrusel, idPelicula, contadorBoton, i);
        
                
    }//GEN-LAST:event_adelanteMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ContraseñaD;
    private javax.swing.JLabel UsuarioUsuarioD;
    private javax.swing.JButton adelante;
    private javax.swing.JButton atras;
    private javax.swing.JButton btnSesionAceptar;
    private javax.swing.JDialog dialogInicioSesion;
    private javax.swing.JDialog dialogPerfil;
    private javax.swing.JButton exit;
    private javax.swing.JLabel idPelicula;
    private javax.swing.JLabel infoApellido1;
    private javax.swing.JLabel infoEmail1;
    private javax.swing.JLabel infoFoto;
    private javax.swing.JLabel infoFoto1;
    private javax.swing.JLabel infoNombre1;
    private javax.swing.JLabel inicioDeSesionD;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelCarrusel;
    private javax.swing.JTextArea reseña;
    private javax.swing.JButton salirP;
    private javax.swing.JComboBox<String> seleccionPeliculas;
    public static javax.swing.JPasswordField txContraseña;
    public static javax.swing.JTextField txUsuario;
    // End of variables declaration//GEN-END:variables
}
