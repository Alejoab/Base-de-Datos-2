import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Certificados extends JPanel {

	private JTextField textPasaporte;
	private JTextField textPrograma;
	private JTextField textFecha;
	private JTextField textDiploma;
	private JTextArea textOutput;
	
	
	
	public Certificados() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel data = new JPanel();
		add(data);
		GridBagLayout gbl_data = new GridBagLayout();
		gbl_data.columnWidths = new int[]{166, 255};
		gbl_data.rowHeights = new int[]{0, 0, 0, 0, 37};
		gbl_data.columnWeights = new double[]{0.0, 0.0};
		gbl_data.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		data.setLayout(gbl_data);
		
		JLabel labelPasaporte = new JLabel("Pasaporte");
		GridBagConstraints gbc_labelPasaporte = new GridBagConstraints();
		gbc_labelPasaporte.insets = new Insets(0, 0, 5, 5);
		gbc_labelPasaporte.gridx = 0;
		gbc_labelPasaporte.gridy = 0;
		data.add(labelPasaporte, gbc_labelPasaporte);
		
		textPasaporte = new JTextField();
		GridBagConstraints gbc_textPasaporte = new GridBagConstraints();
		gbc_textPasaporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPasaporte.insets = new Insets(0, 0, 5, 0);
		gbc_textPasaporte.gridx = 1;
		gbc_textPasaporte.gridy = 0;
		data.add(textPasaporte, gbc_textPasaporte);
		textPasaporte.setColumns(10);
		
		JLabel labelPrograma = new JLabel("Codigo de Programa");
		GridBagConstraints gbc_labelPrograma = new GridBagConstraints();
		gbc_labelPrograma.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrograma.gridx = 0;
		gbc_labelPrograma.gridy = 1;
		data.add(labelPrograma, gbc_labelPrograma);
		
		textPrograma = new JTextField();
		GridBagConstraints gbc_textPrograma = new GridBagConstraints();
		gbc_textPrograma.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPrograma.insets = new Insets(0, 0, 5, 0);
		gbc_textPrograma.gridx = 1;
		gbc_textPrograma.gridy = 1;
		data.add(textPrograma, gbc_textPrograma);
		textPrograma.setColumns(10);
		
		JLabel labelFecha = new JLabel("Fecha de Expedición");
		GridBagConstraints gbc_labelFecha = new GridBagConstraints();
		gbc_labelFecha.insets = new Insets(0, 0, 5, 5);
		gbc_labelFecha.gridx = 0;
		gbc_labelFecha.gridy = 2;
		data.add(labelFecha, gbc_labelFecha);
		
		textFecha = new JTextField();
		GridBagConstraints gbc_textFecha = new GridBagConstraints();
		gbc_textFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFecha.insets = new Insets(0, 0, 5, 0);
		gbc_textFecha.gridx = 1;
		gbc_textFecha.gridy = 2;
		data.add(textFecha, gbc_textFecha);
		textFecha.setColumns(10);
		
		JLabel labelDiploma = new JLabel("Diploma");
		GridBagConstraints gbc_labelDiploma = new GridBagConstraints();
		gbc_labelDiploma.insets = new Insets(0, 0, 5, 5);
		gbc_labelDiploma.gridx = 0;
		gbc_labelDiploma.gridy = 3;
		data.add(labelDiploma, gbc_labelDiploma);
		
		textDiploma = new JTextField();
		GridBagConstraints gbc_textDiploma = new GridBagConstraints();
		gbc_textDiploma.insets = new Insets(0, 0, 5, 0);
		gbc_textDiploma.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDiploma.gridx = 1;
		gbc_textDiploma.gridy = 3;
		data.add(textDiploma, gbc_textDiploma);
		textDiploma.setColumns(10);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setBackground(new Color(255, 255, 255));
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = Multichain.insertarCertificado(textPasaporte.getText(), textPrograma.getText(), textFecha.getText(), textDiploma.getText());
				textOutput.setText(result);
			}
		});
		
		
		GridBagConstraints gbc_btnPublicar = new GridBagConstraints();
		gbc_btnPublicar.anchor = GridBagConstraints.SOUTH;
		gbc_btnPublicar.gridx = 1;
		gbc_btnPublicar.gridy = 4;
		data.add(btnPublicar, gbc_btnPublicar);
		
		JPanel output = new JPanel();
		add(output);
		output.setLayout(new GridLayout(1, 1));
		
		textOutput = new JTextArea();
		textOutput.setRows(12);
		textOutput.setEditable(false);
		JScrollPane scroll = new JScrollPane(textOutput);
		output.add(scroll);
	}

}
