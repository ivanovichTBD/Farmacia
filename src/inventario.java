import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class inventario extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventario frame = new inventario();
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
	public inventario() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStock = new JLabel("STOCK");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblStock.setBounds(324, 11, 94, 35);
		contentPane.add(lblStock);
		
		table = new JTable();
		table.setBounds(31, 70, 631, 313);
		contentPane.add(table);
		
		Conector con = new Conector();
		Connection conector = con.conectar();
		String sql1 = "SELECT * FROM  producto";
		Statement st;
		//if(Item.equalsIgnoreCase("Cliente")) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Codigo");
			model.addColumn("Nombre");
			model.addColumn("Precio");
			model.addColumn("Stock");
			table.setModel(model);
			String[] dato = new String[4];
			st = conector.createStatement();
			try {
				st = conector.createStatement();
				ResultSet result = st.executeQuery(sql1);
				while(result.next()) {
					dato[0] = result.getString(1);
					dato[1] = result.getString(2);
					dato[2] = result.getString(3);
					dato[3] = result.getString(4);
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
		btnSalir.setBounds(341, 407, 89, 23);
		contentPane.add(btnSalir);
	}

}
