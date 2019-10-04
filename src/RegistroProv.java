import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroProv extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblPresentacin;
	private Conector conexion = new Conector();
	public String tipo = null;
	public int idtipo;
	public int idlabo;
	public String laboratorio = null;
	public String producto = null;
	public String presentacion = null;
	public int precio;
	public int precioVenta;
	public int cantidad;
	public int lote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroProv frame = new RegistroProv();
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
	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	public RegistroProv() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setName("Seleccionar");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Recibir de proveedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(12, 13, 247, 22);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEnabled(false);
		String formato = "dd/MM/yyyy";
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
		LocalDateTime ahora = LocalDateTime.now();
		String fecha = formateador.format(ahora);
		textField.setText(fecha);
		textField.setBounds(12, 103, 60, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(509, 103, 161, 22);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(588, 176, 82, 22);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(12, 74, 56, 16);
		contentPane.add(lblFecha);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(84, 74, 56, 16);
		contentPane.add(lblTipo);
		
		JLabel lblNewLabel_1 = new JLabel("Laboratorio");
		lblNewLabel_1.setBounds(253, 74, 83, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(12, 147, 56, 16);
		contentPane.add(lblProducto);
		
		JLabel lblNroDeLote = new JLabel("Nro de lote");
		lblNroDeLote.setBounds(509, 74, 161, 16);
		contentPane.add(lblNroDeLote);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(588, 147, 56, 16);
		contentPane.add(lblCantidad);
		
		lblPresentacin = new JLabel("Presentaci\u00F3n");
		lblPresentacin.setBounds(356, 147, 220, 16);
		contentPane.add(lblPresentacin);
		// COMBO LABO
		JComboBox comboLabo = new JComboBox();
		comboLabo.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				laboratorio = (String)comboLabo.getSelectedItem();
				System.out.println(laboratorio);
				System.out.println(comboLabo.getSelectedItem());
				if(laboratorio=="Seleccione") {
					JOptionPane.showMessageDialog(null, "Debe elegir un laboratorio");
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		comboLabo.setBounds(253, 103, 244, 22);
		comboLabo.addItem("Seleccione");
		Connection conn2 = null;
		Statement stm2 = null;
		ResultSet rs2 = null;
		try {
			conn2 = conexion.conectar();
			stm2 = conn2.createStatement();
			rs2 = stm2.executeQuery("SELECT NOMBRELAB,IDLAB FROM laboratorio");
			while(rs2.next()) {
				String nombre = rs2.getString(1);
				System.out.println("laboratorio " + nombre);
				comboLabo.addItem(nombre);
			}
			conn2.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		contentPane.add(comboLabo);
		
		//COMBO TIPO
		JComboBox comboTipo = new JComboBox();
		comboTipo.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				tipo = (String)comboTipo.getSelectedItem();
				System.out.println(tipo);
				System.out.println(comboTipo.getSelectedItem());
				if(tipo=="Seleccione") {
					JOptionPane.showMessageDialog(null, "Debe elegir una opcion");
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		comboTipo.setBounds(84, 103, 155, 22);
		comboTipo.addItem("Seleccione");
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			conn = conexion.conectar();
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT NOMBRETPO,IDTIPO FROM tipo");
			System.out.println("Tipos leidos de la base de datos");
			while(rs.next()) {
				String nombre = rs.getString(1);
				System.out.println("tipo " + nombre);
				comboTipo.addItem(nombre);
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		contentPane.add(comboTipo);
		
		//COMBO PROD
		JComboBox comboProd = new JComboBox();
		comboProd.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				producto = (String)comboProd.getSelectedItem();
				System.out.println(producto);
				System.out.println(comboProd.getSelectedItem());
				if(producto=="Seleccione") {
					JOptionPane.showMessageDialog(null, "Debe elegir una opcion");
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		comboProd.addItem("Seleccione");
		Connection conn3 = null;
		Statement stm3 = null;
		ResultSet rs3 = null;
		try {
			conn3 = conexion.conectar();
			stm3 = conn3.createStatement();
			rs3 = stm3.executeQuery("SELECT NOMPRODUCTO FROM producto");
			while(rs3.next()) {
				String nombre = rs3.getString(1);
				System.out.println("producto " + nombre);
				comboProd.addItem(nombre);
			}
			conn3.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		comboProd.setBounds(12, 176, 332, 22);
		contentPane.add(comboProd);
		
		//COMBO PRES
		JComboBox comboPres = new JComboBox();
		comboPres.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				presentacion = (String)comboPres.getSelectedItem();
				System.out.println(presentacion);
				System.out.println(comboPres.getSelectedItem());
				if(presentacion=="Seleccione") {
					JOptionPane.showMessageDialog(null, "Debe elegir una presentacion");
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		comboPres.setBounds(356, 176, 220, 22);
		comboPres.addItem("Seleccione");
		Connection conn4 = null;
		Statement stm4 = null;
		ResultSet rs4 = null;
		try {
			conn4 = conexion.conectar();
			stm4 = conn4.createStatement();
			rs4 = stm4.executeQuery("SELECT PRESENTACION FROM producto");
			while(rs4.next()) {
				String nombre = rs4.getString(1);
				System.out.println("tipo " + nombre);
				comboPres.addItem(nombre);
			}
			conn4.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		contentPane.add(comboPres);
		
		JButton btnEnviar = new JButton("Enviar");
		
		Connection conn7 = null;
		Statement stm7 = null;
		ResultSet rs7 = null;
		try {
			conn7 = conexion.conectar();
			stm7 = conn7.createStatement();
			rs7 = stm7.executeQuery("SELECT laboratorio.IDLAB,tipo.IDTIPO,producto.PRECIOPRODUCTO,producto.PRECIOVENTA FROM laboratorio,tipo,producto WHERE laboratorio.NOMBRELAB="+laboratorio+" AND tipo.NOMBRETPO="+tipo);
			while(rs7.next()) {
				idtipo = rs7.getInt(1);
				idlabo = rs7.getInt(2);
				precio = rs7.getInt(3);
				precioVenta = rs7.getInt(4);
			}
			conn7.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		btnEnviar.addMouseListener(new MouseAdapter() {
			Connection conn8 = null;
			
			public void mouseClicked(MouseEvent arg0) {
				lote = Integer.parseInt(textField_5.getText());
				try {
					String query = "INSERT INTO producto(IDLAB, IDTIPO, NOMPRODUCTO, PRECIOPRODUCTO, PRECIOVENTA, CANTIDADPROD, PRESENTACION,LOTE) values (?, ?, ?, ?, ?, ?, ?, ?)";
					conn8 = conexion.conectar();
					PreparedStatement preparedStmt = conn8.prepareStatement(query);
					preparedStmt.setInt(1, idlabo);
					preparedStmt.setInt(2, idtipo);
					preparedStmt.setString(3, producto);
					preparedStmt.setInt(4, precio);
					preparedStmt.setInt(5, precioVenta);
					preparedStmt.setInt(6, cantidad);
					preparedStmt.setString(7, presentacion);
					preparedStmt.setInt(8, lote);
					preparedStmt.execute();
					conn8.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(555, 230, 115, 36);
		contentPane.add(btnEnviar);
	}
}
