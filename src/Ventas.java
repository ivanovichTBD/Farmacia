import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.Font;

public class Ventas extends JFrame {

	private JPanel contentPane;
	private JTextField txtSn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventas frame = new Ventas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"COMPRIMIDO", "CAPSULA", "JARABE"}));
		comboBox.setBounds(227, 113, 99, 30);
		contentPane.add(comboBox);
		
		JLabel lblTipo = new JLabel("TIPO MEDICAMENTO :");
		lblTipo.setBounds(68, 117, 119, 23);
		contentPane.add(lblTipo);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Pracetamol", "Ibuprofeno", "Lebozan", "Digestan", "Resaka", "Aspirina", "Mentisan", "Baselina"}));
		comboBox_1.setBounds(227, 158, 135, 29);
		contentPane.add(comboBox_1);
		
		JLabel lblNombreMedicamento = new JLabel("NOMBRE MEDICAMENTO :");
		lblNombreMedicamento.setBounds(68, 165, 149, 22);
		contentPane.add(lblNombreMedicamento);
		
		JLabel lblCantidad = new JLabel("CANTIDAD :");
		lblCantidad.setBounds(68, 212, 81, 19);
		contentPane.add(lblCantidad);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(228, 206, 58, 30);
		contentPane.add(spinner);
		
		txtSn = new JTextField();
		txtSn.setText("S/N");
		txtSn.setBounds(227, 72, 158, 30);
		contentPane.add(txtSn);
		txtSn.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel("CI CLIENTE :");
		lblNombreCliente.setBounds(68, 76, 108, 23);
		contentPane.add(lblNombreCliente);
		
		JTextPane txtpnCrearVentas = new JTextPane();
		txtpnCrearVentas.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtpnCrearVentas.setText("CREAR VENTAS");
		txtpnCrearVentas.setBounds(236, 22, 163, 30);
		contentPane.add(txtpnCrearVentas);
	}
}
