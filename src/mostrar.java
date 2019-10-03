import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

public class mostrar extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=-45,169
	 */
	private final JToolBar toolBar = new JToolBar();
	private JButton btnSalir;
	private JButton btnMostrar;
	private JLabel lblquDeseaVer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mostrar frame = new mostrar();
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
	public mostrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cliente", "Inventario", "Agregar mas"}));
		comboBox.setBounds(499, 28, 175, 20);
		contentPane.add(comboBox);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(585, 405, 89, 23);
		contentPane.add(btnSalir);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stock st = new inventario(this, true);
				stsetVisible(true);
			}	
		});
		btnMostrar.setBounds(541, 94, 89, 23);
		contentPane.add(btnMostrar);
		
		lblquDeseaVer = new JLabel("\u00BFQu\u00E9 desea ver?");
		lblquDeseaVer.setBounds(60, 31, 352, 14);
		contentPane.add(lblquDeseaVer);
	}
}
