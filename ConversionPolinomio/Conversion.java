import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.TitledBorder;
/**
 * Programa que hace la conversion de un tren de bits a un polinomio
 * Ejemplo: Tren de Bits = 1101 --> Polinomio = x^3 + x^2 + 1
 * 			Polinomio = x^5 + x^3 + x^1  --> 101010
 * @author Diaz Ruiz Alejandro
 * @version 27-10-2020
 */
public class Conversion extends JFrame{
    //Declaración de los componentes para la conversion de bits a polinomio
    private JLabel title;
    private JLabel txtBits;
    private JLabel txtPoli;
    private JLabel polinomio;
    private JButton generar;
    private JTextField trenBits;
    //Declaracion de los componenetes para la conversion de polinomio a bits
    private JLabel txtPolinomio;
    private JTextField polinomioC;
    private JButton generarEstructura;
    private JLabel txtBitsT;
    private JLabel variables;
    //Atributos de configuración para letra, altura y ancho de los componentes
    private Font font = new Font("Consolas",1,20);
    private final int alt = 40;
    private final int anch = 300;

    /**
    * Constructor de la clase principal
    */
    public Conversion (){
        setTitle("Calculo Polinomio");                          //Asigna el titulo al JFrame
        
        JPanel panel = new JPanel(null);                        //Crea un nuevo panel para almacenar las dos conversiones
        panel.setLayout(new GridLayout(2,1));
        
        JPanel encabezado = new JPanel();                       //Panel para el encabezado
        encabezado.setLayout(new GridLayout(1,1));              //Con distribucion de una fila por una columna (fila, columna)
        encabezado.setBackground(new Color (40, 171, 185));

        title = new JLabel ("Conversor de polinomio");          //Instancia del titulo + características
        title.setHorizontalAlignment(JLabel.CENTER); title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(font);
        encabezado.add(title);

        panel.add(bitsAPolinomio());                            //Agrega al panel principal el panel de la conversion1
        panel.add(polinomioABits());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         //Cerrar la ventana
        add(encabezado,BorderLayout.PAGE_START);                //Agrega los panels al JFrame
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         //Cerrar ventana
        setVisible(true);                                       //Visibilidad activada
        setSize(800,500);                                       //Tamaño de la ventana
        setLocationRelativeTo(null); 							//Localización del Frame en la pantalla
    }

    /**
    *  Método que permite generar los elementos del panel
    *  para convertir de bits a polinomio
    *  @return JPanel que contiene los componentes para realizar la conversión
    */
    public JPanel bitsAPolinomio(){
        JPanel bitsPolinomio = new JPanel();                                 //Nuevo panel que contendrá todos los elementos 
        bitsPolinomio.setLayout(null);                                       //Sin ningún tipo de distribución
        bitsPolinomio.setBackground(new Color (239, 250, 211));              //Agrega las características al panel
        bitsPolinomio.setBorder(BorderFactory.createTitledBorder(null, "Tren de Bits --> Polinomio", 
    		TitledBorder.CENTER, TitledBorder.TOP, font, Color.BLACK));                     
            
        txtBits = new JLabel ("Ingrese el tren de bits = ");
        txtBits.setFont(font); txtBits.setBounds(40, 30, anch, alt);

        trenBits = new JTextField();
        trenBits.setFont(font); trenBits.setBounds(40+anch, 30, anch, alt);  
        trenBits.setHorizontalAlignment(JTextField.CENTER); 
                                                                         
        txtPoli = new JLabel ("Polinomio Generado      = ");
        txtPoli.setFont(font); txtPoli.setBounds(40, 100, anch, alt);  
                
        polinomio = new JLabel ("");
        polinomio.setFont(font); polinomio.setBounds(40+anch, 100, anch*3, alt);
        
        generar = new JButton("Generar");
        generar.setFont(font); generar.setBounds(300, 160, 140, alt);

        trenBits.addKeyListener(                                             //Al componente trenBits se le agrega un Listener 
            new KeyAdapter(){                                                //Nuevo objeto de clase KeyAdapter (Teclado)
                /**
                 *  Método que recupera el evento generado por el teclado
                 *  Únicamente permite que el usuario introduzca 0´s y 1´s
                 *  @param ke Tecla pulsada por el usuario
                 */
                public void keyTyped(KeyEvent ke){                          
                    char k = ke.getKeyChar();                                //Recupera en una variable char la tecla pulsada 
                    if (!Character.isDigit(k) ||  
                         Character.getNumericValue(k)!=1 &&
                         Character.getNumericValue(k)!=0)      
                        ke.consume();                                        //No registra la entrada (.consume() - El evento se consume)
                }                                                            //Fin del método
            }                                                                //fin del objeto
        ); 

        generar.addActionListener(											 //Agrega un listener al botón generar
            new ActionListener(){                   
                /**
                 *  Método que se ejecuta cuando se realiza un evento dentro de un botón
                 *  cuando se pulse el botón, generará el polinomio correspondiente al tren de bits
                 *  @param ae Evento del mouse sobre un botón
                 */
                public void actionPerformed(ActionEvent ae) {
                    String tb   = trenBits.getText();						 //Pasa a un String el tren de bits del JTextField
                    char [] tb1 = tb.toCharArray();							 //El String lo convierte a un arreglo por cada caracter
                    String str="";											 //String que almacenará el polinomio
                    int l = tb1.length-1;									 //Longitud del arreglo
                    for (int i = 0 ; i < tb1.length ; i++){				     //Ciclo que recorre el arreglo de caracteres
                        if (tb1[i] == '1'){								     //Cuando el valor en la posición i del arreglo sea '1'
                            str += " x^"+l+" +";   							 //Concatena el string con el valor del exponente 
                        }
                        l--;
                    }
                    str = str.replace("x^0 +","1");							 //Cuando el String tenga un x^0 sustituye por 1
                    if (str.endsWith("+")){									 //Si el String termina en +
                        str = str.substring(0,str.length()-1);			     //Se elimina
                        polinomio.setText(str);								 //Asigna el strign al JLabel
                    }else {
                    	polinomio.setText(str);
                    }
                }
            });																 //Fin de la clase anónima

		//Agrega los componentes al panel             
        bitsPolinomio.add(txtBits);
        bitsPolinomio.add(trenBits); 
        bitsPolinomio.add(txtPoli);
        bitsPolinomio.add(polinomio);
        bitsPolinomio.add(generar);
        return bitsPolinomio;
    }

    /**
    *  Método que permite generar los elementos del panel
    *  para convertir del polinomio al tren de bits
    *  @return JPanel que contiene los componentes para realizar la conversión
    */
    public JPanel polinomioABits(){
        JPanel polinomioBits = new JPanel();
        polinomioBits.setLayout(null);                                     //Sin ningún tipo de distribución
        polinomioBits.setBackground(new Color (45, 97, 135));              //Agrega las características al panel
        polinomioBits.setBorder(BorderFactory.createTitledBorder(null, "Polinomio --> Tren de Bits", 
    		TitledBorder.CENTER, TitledBorder.TOP, font, Color.WHITE));  

        txtPolinomio = new JLabel ("Ingrese el polinomio   = "); txtPolinomio.setForeground(Color.WHITE);	
        txtPolinomio.setFont(font); txtPolinomio.setBounds(40, 30, anch, alt);

        polinomioC = new JTextField(); 
        polinomioC.setFont(font); polinomioC.setBounds(40+anch, 30, anch, alt); 
        polinomioC.setHorizontalAlignment(JTextField.CENTER); 

        generarEstructura = new JButton("Generar Tren de Bits");
        generarEstructura.setFont(font); generarEstructura.setBounds(220, 160, anch, alt);

        variables = new JLabel (""); variables.setForeground(Color.WHITE);	
        variables.setFont(font); variables.setBounds(40+anch, 100, anch, alt);

        txtBitsT = new JLabel("Tren de Bits           ="); txtBitsT.setForeground(Color.WHITE);	
        txtBitsT.setFont(font); txtBitsT.setBounds(40, 100, anch, alt); 
        
        polinomioBits.add(txtPolinomio);
        polinomioBits.add(polinomioC);
        polinomioBits.add(generarEstructura);
        polinomioBits.add(txtBitsT);
        polinomioBits.add(variables);
          
        generarEstructura.addActionListener(						//Agrega un listener al botón					
            new ActionListener(){                   
                /**
                 *  Método que se ejecuta cuando se realiza un evento dentro de un botón
                 *  cuando se pulse el botón, generará el tren de bits correspondiente al polinomio
                 *  @param ae Evento del mouse sobre un botón
                 */
                public void actionPerformed(ActionEvent ae) {		
                    String pm = polinomioC.getText();				//Recupera en un string el polinomio introducido
                    pm = pm.replace("x^","");						//Limpia el String para que queden puros números
                    pm = pm.replace("+","");
                    int expn [] = new int [pm.length()];			//Guarda en un arreglo los exponentes resultantes
                    double local = 0;								//Variable que almacena los resultados
                    for (int i = 0; i < pm.length(); i++){		    //Recorre el string de numeros, y los guarda en un arreglo
                        expn[i] = pm.charAt(i) - '0';
                    }

                    for (int j = 0 ; j < expn.length ; j ++){       //Ciclo que recorre el arreglo de numeros 
                        double swap = 1*Math.pow(10,expn[j]);		//Operación exponencial 10^n
                        local += swap;
                    }
                    variables.setText(""+(int)local);				//Asigna el valor resultante al JLabel
                    }
	            });														//Fin de la clase anónima
        return polinomioBits;
    }
    
    public static void main (String [] args){
        Conversion p1 = new Conversion();
    }
}