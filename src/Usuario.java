import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
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
	public Usuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnFormularioDeRegistro = new JTextPane();
		txtpnFormularioDeRegistro.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtpnFormularioDeRegistro.setText("REGISTRAR CLIENTE");
		txtpnFormularioDeRegistro.setBounds(193, 32, 251, 38);
		contentPane.add(txtpnFormularioDeRegistro);
		
		textField = new JTextField();
		textField.setBounds(302, 104, 238, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("NOMBRE CLIENTE:");
		lblNombre.setBounds(125, 109, 110, 23);
		contentPane.add(lblNombre);
		
		JLabel lblCi = new JLabel("C.I. :");
		lblCi.setBounds(125, 173, 46, 14);
		contentPane.add(lblCi);
		
		JLabel lblNit = new JLabel("NIT :");
		lblNit.setBounds(125, 260, 46, 14);
		contentPane.add(lblNit);
		
		JButton btnNewButton = new JButton("REGISTRAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String Nombre = textField.getText();
				
				String cedula = textField_1.getText();
				String nit = textField_2.getText();
				Conector conexion = new Conector();
				Connection conn = null;
				Statement stm = null;
				
				try {
					conn = conexion.conectar();
					stm = conn.createStatement();
					
				String sql = "insert into cliente (CODCLIENTE, NOMCLINTE, NITCLIENTE)"+"values(?,?,?)";
				PreparedStatement ps = conn.prepareCall(sql);
				ps.setString(1, cedula);
				ps.setString(2, Nombre);
				ps.setString(3, nit);
				ps.executeUpdate();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.print(Nombre +"  "+cedula+"  "+nit+"  ");
			}
		});
		btnNewButton.setBounds(215, 338, 150, 53);
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(302, 170, 238, 38);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(302, 251, 235, 32);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
	}
	
}
