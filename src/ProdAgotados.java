import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ProdAgotados extends JFrame {

	private JPanel contentPane;
	private JTable tablaAg;
	private JLabel lblLosSiguientesProductos;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdAgotados frame = new ProdAgotados();
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
	public ProdAgotados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 370, 235);
		contentPane.add(scrollPane);
		
		tablaAg = new JTable();
		scrollPane.setViewportView(tablaAg);
		
		lblLosSiguientesProductos = new JLabel("LOS SIGUIENTES PRODUCTOS ESTAN A PUNTO DE AGOTARSE,");
		lblLosSiguientesProductos.setBounds(10, 21, 364, 19);
		contentPane.add(lblLosSiguientesProductos);
		
		lblNewLabel = new JLabel("POR FAVOR REAVASTECER SU INVENTARIO.");
		lblNewLabel.setBounds(10, 46, 303, 14);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(291, 327, 89, 23);
		contentPane.add(btnNewButton);
		LlenarTabla();
	}
	public void LlenarTabla(){
		Conector conexion = new Conector();
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
        try {
        	conn = conexion.conectar();
        	stm= conn.createStatement();
        	String[]titulos = {"Nombre","Cantidad"}; 
            String sql = "select nomproducto, cantidadprod from producto where cantidadprod<10";
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            rs=stm.executeQuery(sql);
            
            String[]fila = new String[2];
            while(rs.next()){
                fila[0]=rs.getString("nomproducto");
                fila[1]=rs.getString("cantidadprod");
                model.addRow(fila);
            }
            tablaAg.setModel(model);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
