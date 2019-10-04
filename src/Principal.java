import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Principal extends JFrame {

	private JPanel contentPane;
	JLabel hora = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700	, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label5 = new JLabel("New label");
		label5.setBounds(333, 182, 64, 43);
		contentPane.add(label5);
		
		JLabel label6 = new JLabel("BIENVENIDOS A LA FARMACIA");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setBounds(221, 41, 289, 29);
		contentPane.add(label6);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//VerExistencias();
			}
		});
		btnNewButton.setBounds(47, 88, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(47, 137, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(47, 192, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(47, 266, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(47, 327, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(47, 387, 89, 23);
		contentPane.add(btnNewButton_5);
		
		hora.setBounds(577, 48, 79, 14);
		contentPane.add(hora);
		
		JLabel fecha = new JLabel("New label");
		fecha.setBounds(577, 23, 79, 14);
		contentPane.add(fecha);
		
		//FECHA DEL SISTEMA
        Date sistFecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
        fecha.setText(formato.format(sistFecha));
        //HORA DEL SISTEMA
        Timer tiempo = new Timer(10 , new Principal.horas());
        tiempo.start();
	}
	public class horas implements ActionListener{
        public void actionPerformed(ActionEvent e){
	        Date sistHora = new Date() ;
	        String pmAm = "hh:mm:ss a";
	        SimpleDateFormat format = new SimpleDateFormat(pmAm);
	        Calendar hoy = Calendar.getInstance();
	        hora.setText(String.format(format.format(sistHora), hoy));
	        String horaVariable = hora.getText();
	        String horaFija = "04:06:00 AM";
	        if(horaFija.equals(horaVariable)) {
	        	VerExistencias();
	        }
        }
    }
	public void VerExistencias() {
		Conector conexion = new Conector();
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
        	conn = conexion.conectar();
        	stm= conn.createStatement();
            String sql = "select cantidadprod from producto";
            rs=stm.executeQuery(sql);
            Boolean band = false;
            
            while(rs.next()) {
				int cant = rs.getInt(1);
				if(cant < 10) {
					band = true;
				}
			}
            if(band) {
            	JOptionPane.showMessageDialog(null, "Hay productos apunto de agotar");
            	ProdAgotados vista = new ProdAgotados();
				vista.setVisible(true);
            }else {
            	JOptionPane.showMessageDialog(null, "No hay productos apunto de agotar");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
