/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase encargada de la creacion del nivel principiante y sus funciones de accion durante la ejecucion del juego. 
 * @author mario
 */
public class principiante extends JPanel implements ActionListener {

    private int casillasFaltantes = 0, minas1 = 10;
	int valoresPrincipiante[][] = new int[minas1][minas1];
	public JLabel labelP;	
	private JButton botonesPrincipiante[][] = new JButton[minas1][minas1];
	public JButton botonP;
	
	public String[] archi = {"/Imagenes/gano.png", "/Imagenes/perdio.png", "/Imagenes/nueva.png"};
	private String archivos[] = {"/Imagenes/0.PNG", "/Imagenes/1.PNG", "/Imagenes/2.PNG", "/Imagenes/3.PNG", "/Imagenes/4.PNG", "/Imagenes/5.PNG", "/Imagenes/6.PNG", "/Imagenes/7.PNG", "/Imagenes/8.PNG", "/Imagenes/9.PNG"};
	
	private ImageIcon[] imagenes = new ImageIcon[10];    
	public ImageIcon[] ima = new ImageIcon[3];   
        
        private boolean visiblePrincipiante[][] = new boolean[minas1][minas1];
        
 public principiante(){
		this.setLayout(null);
		for(int i=0;i<3;i++){
            ima[i] = new ImageIcon(getClass().getResource(archi[i]));
        }
		nuevaPartidaPrincipiante();        
		this.setSize(200, 240);
		botonP = new JButton();
		botonP.setBounds(86, 5, 30, 30);
        botonP.setIcon(ima[2]);                
        this.add(botonP);
        this.botonP.addActionListener(this);
		labelP = new JLabel();
		labelP.setBounds(15, 15, 40, 15);
		this.add(labelP);
	}
    
    public void nuevaPartidaPrincipiante(){      
		ponerBotonesPrincipiante();	
        casillasFaltantes = 0;
        verPrincipiante(false);
        ponerMinasPrincipiante();
        contornoPrincipiante();
        visualizarMinasPrincipiante();
        eventosPrincipiante();         
    }	
	
	public void ponerBotonesPrincipiante(){
        for(int i=0;i<10;i++){
            imagenes[i] = new ImageIcon(getClass().getResource(archivos[i]));
        }        
        for(int f = 0; f<minas1; f++){
        for(int c = 0; c<minas1; c++){
            botonesPrincipiante[f][c] = new JButton();
            botonesPrincipiante[f][c].setBounds(20*f, 20*c+40,20, 20);
            botonesPrincipiante[f][c].setBackground(Color.gray);
            this.add(botonesPrincipiante[f][c]);
        }
        }        
    }
    
    public void ponerMinasPrincipiante(){
        for(int f=0;f<minas1;f++){
        for(int c=0;c<minas1;c++){
            valoresPrincipiante[f][c]=0;
        }
        }
           int f1, c1;
        for ( int i=0;i<minas1;i++){
            do{
                f1=(int)(Math.random()*minas1);
                c1=(int)(Math.random()*minas1);
            }while(valoresPrincipiante[f1][c1]!=0);
            valoresPrincipiante[f1][c1]=9;
        }
    }
	
	public void contornoPrincipiante(){
        for(int f=0;f<minas1;f++){
            for(int c=0;c<minas1;c++){
                if(valoresPrincipiante[f][c]==9){
                    for(int f2=f-1;f2<=f+1;f2++){
                        for(int c2=c-1;c2<=c+1;c2++){
                            if(f2>=0 && f2<minas1 && c2>=0 && c2<minas1 && valoresPrincipiante[f2][c2]!=9)
                                valoresPrincipiante[f2][c2]++;
                        }
                      }
                   }
                }
            }
        }
	
	public void verPrincipiante(boolean valor){
        for(int f=0;f<minas1;f++){
        for(int c=0;c<minas1;c++){
            visiblePrincipiante[f][c]=valor;
        }
        }
    }
	
	public void pulsarBotonVasPrincipiante(int f, int c){
        if(f>=0 && f<minas1 && c>=0 && c<minas1 && visiblePrincipiante[f][c]==false){
            if(visiblePrincipiante[f][c]==false){
            	visiblePrincipiante[f][c]=true;
                if(valoresPrincipiante[f][c]==9){
                    verPrincipiante(true);
                    JOptionPane.showMessageDialog(null, "              PERDISTE");
                    botonP.setIcon(ima[1]);}
            else if(visiblePrincipiante[f][c]==true){
                casillasFaltantes++;
                if (casillasFaltantes == 90){
                    verPrincipiante(true);
                    JOptionPane.showMessageDialog(null, "              GANASTE");
                    botonP.setIcon(ima[0]);
                    labelP.setText("");
                }
                labelP.setText(casillasFaltantes+"/90");
            }
            }
            if(valoresPrincipiante[f][c]==0){
                pulsarBotonVasPrincipiante(f, c-1);
                pulsarBotonVasPrincipiante(f, c+1);
                pulsarBotonVasPrincipiante(f-1, c);
                pulsarBotonVasPrincipiante(f+1, c);
            }
        }
    }
	
	public void pulsarBotonPrincipiante(int f, int c) {                       
        pulsarBotonVasPrincipiante(f,c);
        visualizarMinasPrincipiante();
    }
	
	public void eventosPrincipiante(){
        for(int f=0;f<minas1;f++){
        for(int c=0;c<minas1;c++){
            this.botonesPrincipiante[f][c].addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    for(int f=0;f<minas1;f++){
                    for(int c=0;c<minas1;c++){
                        if(e.getSource()==botonesPrincipiante[f][c])
                            pulsarBotonPrincipiante(f,c);
                    }
                    }
                }
                }
            );
        }
        }
    }
	
	public void visualizarMinasPrincipiante(){
        for(int f=0;f<minas1;f++){
        for(int c=0;c<minas1;c++){
         if(visiblePrincipiante[f][c]==false){
           botonesPrincipiante[f][c].setText("");
         }else if(visiblePrincipiante[f][c]==true){
           if(valoresPrincipiante[f][c]==0){
           botonesPrincipiante[f][c].setIcon(imagenes[0]);
           }else if(valoresPrincipiante[f][c]==1){
           botonesPrincipiante[f][c].setIcon(imagenes[1]);
           }else if(valoresPrincipiante[f][c]==2){
           botonesPrincipiante[f][c].setIcon(imagenes[2]);
           }else if(valoresPrincipiante[f][c]==3){
           botonesPrincipiante[f][c].setIcon(imagenes[3]);
           }else if(valoresPrincipiante[f][c]==4){
           botonesPrincipiante[f][c].setIcon(imagenes[4]);
           }else if(valoresPrincipiante[f][c]==5){
           botonesPrincipiante[f][c].setIcon(imagenes[5]);
           }else if(valoresPrincipiante[f][c]==6){
           botonesPrincipiante[f][c].setIcon(imagenes[6]);
           }else if(valoresPrincipiante[f][c]==7){
           botonesPrincipiante[f][c].setIcon(imagenes[7]);
           }else if(valoresPrincipiante[f][c]==8){
           botonesPrincipiante[f][c].setIcon(imagenes[8]);
           }else if(valoresPrincipiante[f][c]==9)
           botonesPrincipiante[f][c].setIcon(imagenes[9]);
          }

        }
       }
   }
	
	public void quitarBotonesPrincipiante(){
        for(int f1 = 0; f1<minas1; f1++){
               for(int c1 = 0; c1<minas1; c1++){
                   this.remove(botonesPrincipiante[f1][c1]);
               }
   }
 }
       
    @Override
	public void actionPerformed(ActionEvent eP) {
		if(eP.getSource()==botonP){
			botonP.setIcon(ima[2]);
            quitarBotonesPrincipiante();
            this.setVisible(false);            
            labelP.setText("");
			nuevaPartidaPrincipiante();
			this.setVisible(true);
		}		
	}
    }
    

