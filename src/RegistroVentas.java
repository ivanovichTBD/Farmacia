import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroVentas extends JFrame {

	private JPanel contentPane;
	private JTable tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroVentas frame = new RegistroVentas();
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
	public RegistroVentas() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 664, 316);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		LlenarTabla();
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE VENTAS REALIZADAS");
		lblNewLabel.setBounds(228, 36, 229, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//HoraFecha vista = new HoraFecha();
				//vista.setVisible(true);
				//dispose();
			}
		});
		btnSalir.setBounds(585, 427, 89, 23);
		contentPane.add(btnSalir);
	}
	public void LlenarTabla(){
		Conector conexion = new Conector();
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
        try {
        	conn = conexion.conectar();
        	stm= conn.createStatement();
        	String[]titulos = {"Fecha","Hora","Nombre","Precio Unitario","Cantidad","Precio Total"}; 
            String sql = "SELECT f.fecha fecha, h.hora hora, n.nomproducto nombre, p.precioproducto precio, cantidadvendida, subtotal FROM detalleventa dv INNER JOIN venta f ON dv.codventa =  f.codventa INNER JOIN venta h ON dv.codventa =  h.codventa INNER JOIN producto n ON dv.codproducto =  n.codproducto INNER JOIN producto p ON dv.codproducto =  p.codproducto";
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            rs=stm.executeQuery(sql);
            
            String[]fila = new String[6];
            while(rs.next()){
                fila[0]=rs.getString("fecha");
                fila[1]=rs.getString("hora");
                fila[2]=rs.getString("nombre");
                fila[3]=rs.getString("precio");
                fila[4]=rs.getString("cantidadvendida");
                fila[5]=rs.getString("subtotal");
                model.addRow(fila);
            }
            tabla.setModel(model);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
