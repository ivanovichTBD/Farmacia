import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Personal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personal frame = new Personal();
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
	public Personal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnRegistrarPersonal = new JTextPane();
		txtpnRegistrarPersonal.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtpnRegistrarPersonal.setText("REGISTRAR PERSONAL");
		txtpnRegistrarPersonal.setBounds(232, 24, 240, 33);
		contentPane.add(txtpnRegistrarPersonal);
		
		textField = new JTextField();
		textField.setBounds(327, 88, 211, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreCompleto = new JLabel("NOMBRE COMPLETO:");
		lblNombreCompleto.setBounds(145, 92, 128, 24);
		contentPane.add(lblNombreCompleto);
		
		textField_1 = new JTextField();
		textField_1.setBounds(327, 142, 211, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSueldo = new JLabel("SUELDO :");
		lblSueldo.setBounds(145, 151, 46, 14);
		contentPane.add(lblSueldo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(327, 203, 211, 33);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDireccion = new JLabel("DIRECCION :");
		lblDireccion.setBounds(145, 212, 106, 24);
		contentPane.add(lblDireccion);
		
		textField_3 = new JTextField();
		textField_3.setBounds(327, 257, 211, 33);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTelefono = new JLabel("TELEFONO :");
		lblTelefono.setBounds(145, 266, 88, 24);
		contentPane.add(lblTelefono);
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String Nombre = textField.getText();
				String sueldo = textField_1.getText();
				String Direccion = textField_2.getText();
				String Telefono = textField_3.getText();
				
				Conector conexion = new Conector();
				Connection conn = null;
				Statement stm = null;
				
				try {
					conn = conexion.conectar();
					stm = conn.createStatement();
					
				String sql = "insert into cajero (NOMCAJERO,SUELDOCAJERO,DIRCAJERO,TELFCAJERO)"+"values(?,?,?,?)";
				PreparedStatement ps = conn.prepareCall(sql);
				ps.setString(1, Nombre);
				ps.setString(2, sueldo);
				ps.setString(3, Direccion);
				ps.setString(4, Telefono);
				
				ps.executeUpdate();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
				//System.out.print(Nombre);
			}
		});
		btnRegistrar.setBounds(284, 341, 139, 33);
		contentPane.add(btnRegistrar);
	}

}
