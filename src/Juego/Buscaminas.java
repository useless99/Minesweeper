/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Clase encargada de la de la emulacion del juego y especificaciones del frame
 * @author mario
 */
public class Buscaminas extends JFrame implements ActionListener{
     principiante nivelPrincipiante = new principiante();
     intermedio nivelIntermedio = new intermedio();
     
    
    private JMenuBar barra;
    private JMenu juego, ayuda;
    private JMenuItem principiante, intermedio;    
    private boolean prin=true, inter=false; 
    
    private String[] archi= {"/Imagenes/gano.png", "/Imagenes/perdio.png", "/Imagenes/nueva.png"};
    
    private ImageIcon[] ima= new ImageIcon[3];
    
    /**
     * Clase constructora en ventana del juego.
     */
    public Buscaminas(){    	
    	for(int i=0;i<3;i++){
            ima[i] = new ImageIcon(getClass().getResource(archi[i]));
        }
    	this.add(nivelPrincipiante);
    	this.setLayout(null);
    	this.setTitle("MINESWEEPER");        
        barra = new JMenuBar();

        juego = new JMenu("Juego");
        ayuda = new JMenu("Ayuda");

        principiante = new JMenuItem("Principiante");
        intermedio = new JMenuItem("Intermedio");
       
        //agregamos los items de menu
        juego.add(principiante);
        juego.add(intermedio);
        
        //agregado los menu ala barra
        barra.add(juego);
        barra.add(ayuda);
        //bara agregada al frame
        this.setJMenuBar(barra);
        this.principiante.addActionListener(this);
        this.intermedio.addActionListener(this);
        

        //propiedades del frame
        setSize(206, 294);        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setVisible(false);
    }
    
    /**
     * Clase de ejucion de acciones sobre ventana frame
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== intermedio){
            nivelIntermedio.botonI.setIcon(ima[2]);
        	nivelIntermedio.quitarBotonesIntermedio();
        	nivelIntermedio.setVisible(false);            
        	nivelIntermedio.labelI.setText("");
        	nivelIntermedio.nuevaPartidaIntermedio();
        	nivelIntermedio.setVisible(true);
        	if(prin){
        		this.remove(nivelPrincipiante);
        		this.add(nivelIntermedio);
        		prin=false;
        		inter=true;   
        	}
            setSize(406, 495);
            setLocationRelativeTo(null); 
        }else if(e.getSource()== principiante){
            nivelPrincipiante.botonP.setIcon(ima[2]);
        	nivelPrincipiante.quitarBotonesPrincipiante();
        	nivelPrincipiante.setVisible(false);            
            nivelPrincipiante.labelP.setText("");
            nivelPrincipiante.nuevaPartidaPrincipiante();
            nivelPrincipiante.setVisible(true);
        	if(inter){
        		this.remove(nivelIntermedio);
        		this.add(nivelPrincipiante);
        		inter=false;
        		prin=true; 
        	}
        	}	               
            setSize(206, 294);
            setLocationRelativeTo(null);
        }
    
    
       /**
     * Clase encargada de la ejecucion del juego en pantalla. 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Buscaminas i= new Buscaminas(); 
        i.setVisible(true);
}

}


