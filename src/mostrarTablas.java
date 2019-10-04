import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class mostrarTablas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mostrarTablas frame = new mostrarTablas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public mostrarTablas() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel btnMostrar = new JLabel("LISTA DE CLIENTES");
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMostrar.setBounds(249, 23, 308, 23);
		contentPane.add(btnMostrar);
		
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(585, 427, 89, 23);
		contentPane.add(btnSalir);
		
		table1 = new JTable();
		table1.setBounds(50, 74, 595, 343);
		contentPane.add(table1);
		Conector con = new Conector();
		Connection conector = con.conectar();
		String sql1 = "SELECT * FROM  producto";
		String sql2 = "SELECT * FROM  cliente";
		Statement st;
		Statement st1;
		String Item;
		//if(Item.equalsIgnoreCase("Cliente")) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Codigo");
			model.addColumn("Nombre");
			model.addColumn("NIT");
			table1.setModel(model);
			String[] dato = new String[3];
			st = conector.createStatement();
			try {
				st = conector.createStatement();
				ResultSet result = st.executeQuery(sql2);
				while(result.next()) {
					dato[0] = result.getString(1);
					dato[1] = result.getString(2);
					dato[2] = result.getString(3);
					model.addRow(dato);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}//
			/**if (Item.equalsIgnoreCase("Inventario")) {
				DefaultTableModel model1 = new DefaultTableModel();
				model1.addColumn("Codigo");
				model1.addColumn("Nombre");
				model1.addColumn("Stock");
				table1.setModel(model1);
				String[] dato1 = new String[3];
				st1 = conector.createStatement();
				try {
					st1 = conector.createStatement();
					ResultSet result1 = st1.executeQuery(sql1);
					while(result1.next()) {
						dato1[0] = result1.getString(1);
						dato1[1] = result1.getString(2);
						dato1[2] = result1.getString(3);
						model1.addRow(dato1);
					}
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
			
			
			
		}**/
	}



