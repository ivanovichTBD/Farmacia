import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class factura extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					factura frame = new factura();
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
	public factura() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFactura = new JLabel("FACTURA");
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFactura.setBounds(307, 11, 101, 25);
		contentPane.add(lblFactura);
		
		table = new JTable();
		table.setBounds(22, 65, 638, 295);
		contentPane.add(table);
		Conector con = new Conector();
		Connection conector = con.conectar();
		String sql1 = "SELECT * FROM  detalleventa";
		Statement st;
		//if(Item.equalsIgnoreCase("Cliente")) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Codigo Detalle");
			model.addColumn("Codigo venta");
			model.addColumn("Codigo Producto");
			model.addColumn("Cantidad Vendida");
			model.addColumn("Subtotal");
			table.setModel(model);
			String[] dato = new String[5];
			st = conector.createStatement();
			try {
				st = conector.createStatement();
				ResultSet result = st.executeQuery(sql1);
				while(result.next()) {
					dato[0] = result.getString(1);
					dato[1] = result.getString(2);
					dato[2] = result.getString(3);
					dato[3] = result.getString(4);
					dato[4] = result.getString(5);
					model.addRow(dato);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(320, 406, 89, 23);
		contentPane.add(btnSalir);
	}

}
