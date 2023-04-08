import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class Window extends JFrame {
	
	private JPanel ventana_actual = new Graduandos();
	private Container content;
	
	public Window() {
		JFrame frame = new JFrame("BlockChain");          
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          
	    frame.setSize(500, 500);
	    this.content = frame.getContentPane();
	    
	    JMenuBar menuBar = new JMenuBar();
	    frame.setJMenuBar(menuBar);
	    
	    JButton boton_graduandos = new JButton("Insertar Graduando");
	    boton_graduandos.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            JPanel nueva_ventana = new Graduandos();
	            ventana_actual = nueva_ventana;
	            content.removeAll();
	            content.add(nueva_ventana);
	            content.validate();
	            content.repaint();
	        }
	    });
        
        
	    JButton boton_certificados = new JButton("Insertar Certificado");
	    boton_certificados.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            JPanel nueva_ventana = new Certificados();
	            ventana_actual = nueva_ventana;
	            content.removeAll();
	            content.add(nueva_ventana);
	            content.validate();
	            content.repaint();
	        }
	    });
	    
	    JButton boton_buscar_certificados = new JButton("Buscar Certificado");
	    boton_buscar_certificados.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            JPanel nueva_ventana = new BuscarCertificados();
	            ventana_actual = nueva_ventana;
	            content.removeAll();
	            content.add(nueva_ventana);
	            content.validate();
	            content.repaint();
	        }
	    });
	    
	    menuBar.add(boton_graduandos);
	    menuBar.add(boton_certificados);
	    menuBar.add(boton_buscar_certificados);
	    
	    content.add(this.ventana_actual);
	    
	    frame.setVisible(true);
	}
}
