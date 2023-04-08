import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Graduandos extends JPanel {
	private JTextField textPasaporte;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textFecha;
	private JTextField textCelular;
	private JTextArea textOutput;
	
	
	
	public Graduandos() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel data = new JPanel();
		add(data);
		GridBagLayout gbl_data = new GridBagLayout();
		gbl_data.columnWidths = new int[]{166, 255};
		gbl_data.rowHeights = new int[]{0, 0, 0, 0, 0, 37};
		gbl_data.columnWeights = new double[]{0.0, 0.0};
		gbl_data.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		JLabel labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 0;
		gbc_labelNombre.gridy = 1;
		data.add(labelNombre, gbc_labelNombre);
		
		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 1;
		data.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		JLabel labelApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_labelApellidos = new GridBagConstraints();
		gbc_labelApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellidos.gridx = 0;
		gbc_labelApellidos.gridy = 2;
		data.add(labelApellidos, gbc_labelApellidos);
		
		textApellidos = new JTextField();
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_textApellidos.gridx = 1;
		gbc_textApellidos.gridy = 2;
		data.add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(10);
		
		JLabel labelFecha = new JLabel("Fecha de Nacimiento");
		GridBagConstraints gbc_labelFecha = new GridBagConstraints();
		gbc_labelFecha.insets = new Insets(0, 0, 5, 5);
		gbc_labelFecha.gridx = 0;
		gbc_labelFecha.gridy = 3;
		data.add(labelFecha, gbc_labelFecha);
		
		textFecha = new JTextField();
		GridBagConstraints gbc_textFecha = new GridBagConstraints();
		gbc_textFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFecha.insets = new Insets(0, 0, 5, 0);
		gbc_textFecha.gridx = 1;
		gbc_textFecha.gridy = 3;
		data.add(textFecha, gbc_textFecha);
		textFecha.setColumns(10);
		
		JLabel labelCelular = new JLabel("Celular");
		GridBagConstraints gbc_labelCelular = new GridBagConstraints();
		gbc_labelCelular.insets = new Insets(0, 0, 5, 5);
		gbc_labelCelular.gridx = 0;
		gbc_labelCelular.gridy = 4;
		data.add(labelCelular, gbc_labelCelular);
		
		textCelular = new JTextField();
		GridBagConstraints gbc_textCelular = new GridBagConstraints();
		gbc_textCelular.insets = new Insets(0, 0, 5, 0);
		gbc_textCelular.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCelular.gridx = 1;
		gbc_textCelular.gridy = 4;
		data.add(textCelular, gbc_textCelular);
		textCelular.setColumns(10);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setBackground(new Color(255, 255, 255));
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = Multichain.insertarGraduando(textPasaporte.getText(), textNombre.getText(), textApellidos.getText(), textFecha.getText(), textCelular.getText());
				textOutput.setText(result);
			}
		});
		
		
		GridBagConstraints gbc_btnPublicar = new GridBagConstraints();
		gbc_btnPublicar.anchor = GridBagConstraints.SOUTH;
		gbc_btnPublicar.gridx = 1;
		gbc_btnPublicar.gridy = 5;
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
