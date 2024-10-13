package view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class Menutest extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem mntmClientes;
	private JMenuItem mntmProductos;
	private JMenu mnMantenimiento;
	private JMenu mnVentas;
	private JMenu mnReportes;
	private JMenuItem mntmRegistrarVenta;
	private JMenuItem mntmGenerarReporte;
	private JSeparator separator_1;
	private JMenuItem mntmPedidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Menutest frame = new Menutest();
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
	public Menutest() {
		getContentPane().setBackground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		getContentPane().setEnabled(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 682);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setMnemonic('M');
		menuBar.add(mnMantenimiento);

		JSeparator separator = new JSeparator();
		mnMantenimiento.add(separator);

		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setMnemonic('C');
		mntmClientes.setIcon(new ImageIcon("E:\\MISKITA\\MISKITA\\src\\icons\\clasificacion (2).png"));
		mntmClientes.addActionListener(this);
		mnMantenimiento.add(mntmClientes);

		JSeparator separator_2 = new JSeparator();
		mnMantenimiento.add(separator_2);

		mntmProductos = new JMenuItem("Productos");
		mntmProductos.setMnemonic('P');
		mntmProductos.setIcon(new ImageIcon("E:\\MISKITA\\MISKITA\\src\\icons\\productos (1).png"));
		mntmProductos.addActionListener(this);
		mnMantenimiento.add(mntmProductos);
		
		separator_1 = new JSeparator();
		mnMantenimiento.add(separator_1);
		
		mntmPedidos = new JMenuItem("Pedidos");
		mntmPedidos.setIcon(new ImageIcon("E:\\MISKITA\\MISKITA\\src\\icons\\orders.png"));
		mntmPedidos.addActionListener(this);
		mnMantenimiento.add(mntmPedidos);

		mnVentas = new JMenu("Ventas");
		menuBar.add(mnVentas);

		mntmRegistrarVenta = new JMenuItem("Registrar Venta");
		mntmRegistrarVenta
				.setIcon(new ImageIcon("E:\\MISKITA\\MISKITA\\src\\icons\\carrito-de-supermercado.png"));
		mntmRegistrarVenta.addActionListener(this);
		mnVentas.add(mntmRegistrarVenta);

		mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);

		mntmGenerarReporte = new JMenuItem("Generar Reporte");
		mntmGenerarReporte.setIcon(new ImageIcon("E:\\MISKITA\\MISKITA\\src\\icons\\lista-de-verificacion.png"));
		mntmGenerarReporte.addActionListener(this);
		mnReportes.add(mntmGenerarReporte);

	}


	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == mntmClientes) {
			actionPerformedMntmClientes(arg0);
		}

		if (arg0.getSource() == mntmProductos) {
			actionPerformedMntmProductos(arg0);
		}

		if (arg0.getSource() == mntmRegistrarVenta) {
			actionPerformedMnVentas(arg0);
		}

		if (arg0.getSource() == mntmGenerarReporte) {
			actionPerformedMnReportes(arg0);
		}
		if (arg0.getSource() == mntmPedidos) {
			actionPerformedMntmPedidos(arg0);
		}
	}

	public void actionPerformedMntmClientes(ActionEvent arg0){
		DlgClients cli = new DlgClients();
		cli.setLocationRelativeTo(this);
		cli.setModal(true);
		cli.setVisible(true);
	}

	public void actionPerformedMntmProductos(ActionEvent arg0) {
		DlgProducts pro = new DlgProducts();
		pro.setLocationRelativeTo(this);
		pro.setVisible(true);
	}

	public void actionPerformedMnVentas(ActionEvent arg0) {
		DlgSells ve = new DlgSells();
		ve.setLocationRelativeTo(this);
		ve.setVisible(true);
	}
	
	public void actionPerformedMntmPedidos(ActionEvent arg0) {
		DlgOrders pe = new DlgOrders();
		pe.setLocationRelativeTo(this);
		pe.setVisible(true);
	}
	

	public void actionPerformedMnReportes(ActionEvent arg0) {
		/*DlgReportes rep = new DlgReportes(arregloProductos, arregloFacturas);
		rep.setLocationRelativeTo(this);
		rep.setModal(true);
		rep.setVisible(true);*/
	}
}
