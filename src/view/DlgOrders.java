package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import model.Order;

public class DlgOrders extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNumPedido;
	private JComboBox<String> cboEstado;
	private JButton btnModificar;
	private JButton btnBuscar;
	private JButton btnOk;
	private JScrollPane scrollPane;
	private JTable tblTabla;
	private DefaultTableModel model;

	OrderController orderControl = new OrderController();
	private int tipoOperacion;
	public final static int CONSULTAR = 0;
	public final static int MODIFICAR = 1;
	public final static int ELIMINAR = 2;

	private JTextField txtNombreCLiente;
	private JButton btnConsultar;
	private JButton btnEliminar;
	private JButton btnOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgOrders dialog = new DlgOrders();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgOrders() {
		setBounds(100, 100, 980, 613);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Numero de Pedido :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 125, 27);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Estado :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 77, 94, 14);
		panel.add(lblNewLabel_1);

		cboEstado = new JComboBox<String>();
		cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "PENDIENTE", "EN PREPARACION", "LISTO" }));
		cboEstado.setBounds(145, 69, 146, 22);
		panel.add(cboEstado);

		txtNumPedido = new JTextField();
		txtNumPedido.setBounds(145, 16, 149, 20);
		panel.add(txtNumPedido);
		txtNumPedido.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.addActionListener(this);
		btnModificar.setBounds(836, 15, 120, 23);
		panel.add(btnModificar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(304, 15, 89, 23);
		panel.add(btnBuscar);

		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOk.addActionListener(this);
		btnOk.setBounds(304, 69, 89, 23);
		panel.add(btnOk);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 946, 463);
		panel.add(scrollPane);

		tblTabla = new JTable();
		scrollPane.setViewportView(tblTabla);

		model = new DefaultTableModel();
		model.addColumn("NUMERO PEDIDO");
		model.addColumn("ID CLIENTE");
		model.addColumn("NOMBRE");
		model.addColumn("DELIVERY");
		model.addColumn("ESTADO");
		tblTabla.setModel(model);

		txtNombreCLiente = new JTextField();
		txtNombreCLiente.setBounds(145, 43, 149, 20);
		panel.add(txtNombreCLiente);
		txtNombreCLiente.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nombre Cliente :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 52, 125, 14);
		panel.add(lblNewLabel_2);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setEnabled(false);
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultar.setBounds(836, 42, 120, 23);
		panel.add(btnConsultar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(836, 68, 120, 23);
		panel.add(btnEliminar);

		btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(737, 27, 89, 52);
		panel.add(btnOpciones);

		listOrders();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar();
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar();
		}
		if (arg0.getSource() == btnOk) {
			actionPerformedBtnOk();
		}

		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar();
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones();
		}
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar();
		}
	}
	
	public void actionPerformedBtnBuscar() {
		consultar();
	}

	
	public void actionPerformedBtnOk() {
		switch (tipoOperacion) {
		case CONSULTAR:
			consultar();
			break;
		case MODIFICAR:
			modificar();
			break;
		case ELIMINAR:
			eliminar();
		}
	}
	
	public void actionPerformedBtnOpciones() {
		txtNumPedido.setText("");
		txtNombreCLiente.setText("");
		cboEstado.setSelectedIndex(0);;
		txtNumPedido.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);
	}


	public void actionPerformedBtnModificar() {
		tipoOperacion = MODIFICAR;
		txtNumPedido.setEditable(true);
		habilitarBotones(false);
		txtNumPedido.requestFocus();
		
	}
	
	
	public void actionPerformedBtnConsultar() {
		tipoOperacion = CONSULTAR;
		txtNumPedido.setEditable(true);
		habilitarBotones(false);
		txtNumPedido.requestFocus();
		btnBuscar.setEnabled(true);
	}
	
	public void actionPerformedBtnEliminar() {

	}

	
	void modificar() {
		try {
			String numPedido = txtNumPedido.getText();
			Order order = orderControl.orderDetail(numPedido);
			if (order != null) {
				order.setState(leerEstado());
				int result = orderControl.updateOrder(order);
				if (result > 0) {
					System.out.println("Pedido actualizado correctamente");
				} else {
					System.out.println("Error al actualizar el pedido");
				}
				listOrders();
			} else {
				System.out.println("Pedido no encontrado");
			}
		} catch (Exception e) {
			mensaje("ingrese el codigo");
		}
		
	}

	void consultar(){
		try {
			String codigo = txtNumPedido.getText();
			Order order = orderControl.orderDetail(codigo);

			if (order != null) {

				txtNombreCLiente.setText(order.getNomCli());
				cboEstado.setSelectedIndex(order.getState());

				if (tipoOperacion == MODIFICAR) {
					habilitarEntradas(false);
					txtNumPedido.setEditable(false);
					btnBuscar.setEnabled(true);
					btnOk.setEnabled(true);
					txtNombreCLiente.requestFocus();
				}
				if (tipoOperacion == ELIMINAR) {
					txtNumPedido.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOk.setEnabled(true);
				}
			} else {
				error("El código " + codigo + " no existe", txtNumPedido);
			}
		} catch (Exception e) {
			error("Ingrese CÓDIGO correcto", txtNumPedido);
		}
	}
	

	void eliminar() {
		
	}
	
	void listOrders() {
		model.setRowCount(0);
		for (Order order : orderControl.listOrders()) {
			Object[] fila = { order.getNumOrder(), order.getIdCliente(), order.getNomCli(),
					order.isDelivery() ? "SI" : "NO", Estado(order.getState()) };
			model.addRow(fila);
		}
	}

	void habilitarEntradas(boolean sino) {
		txtNombreCLiente.setEditable(sino);
	}

	void habilitarBotones(boolean sino) {
		if (tipoOperacion == MODIFICAR)
			btnOk.setEnabled(!sino);
		else {
			btnBuscar.setEnabled(sino);
			btnOk.setEnabled(!false);
		}
		btnConsultar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnEliminar.setEnabled(sino);
		btnOpciones.setEnabled(!sino);
	}

	int leerEstado() {
		return cboEstado.getSelectedIndex();
	}

	String Estado(int i) {
		return cboEstado.getItemAt(i);
	}
	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

}
