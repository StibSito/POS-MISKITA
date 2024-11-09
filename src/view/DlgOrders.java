package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import interfaces.OrderListener;
import model.Order;
import model.OrderTableCellRenderer;
import model.OrderTicket;

public class DlgOrders extends JDialog implements ActionListener, OrderListener {

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
		cboEstado.setModel(
				new DefaultComboBoxModel<String>(new String[] { "PENDIENTE", "EN PREPARACION", "LISTO", "CANCELADO" }));
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
		model.addColumn("FECHA");
		model.addColumn("NOMBRE");
		model.addColumn("Total");
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

		// Agregamos el listener para detectar clics en las filas de la tabla
		tblTabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblTabla.getSelectedRow();
				if (selectedRow >= 0) {
					// Cargamos los datos de la fila seleccionada en los campos de texto
					txtNumPedido.setText(model.getValueAt(selectedRow, 0).toString());
					txtNombreCLiente.setText(model.getValueAt(selectedRow, 3).toString());
					cboEstado.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
				}
			}
		});

		listOrders();
	}

	@Override
	public void onOrderProcessed() {
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
		cboEstado.setSelectedIndex(0);
		txtNumPedido.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true, true, true); // Habilitar los botones de opciones
	}

	public void actionPerformedBtnModificar() {
		tipoOperacion = MODIFICAR;
		txtNumPedido.setEditable(true);
		habilitarBotones(false, false, false); // Deshabilitar todos los botones excepto OK
		txtNumPedido.requestFocus();
	}

	public void actionPerformedBtnConsultar() {
		tipoOperacion = CONSULTAR;
		txtNumPedido.setEditable(true);
		habilitarBotones(false, false, false); // Deshabilitar todos los botones excepto Buscar y OK
		txtNumPedido.requestFocus();
		btnBuscar.setEnabled(true);
	}

	public void actionPerformedBtnEliminar() {
		tipoOperacion = ELIMINAR;
		txtNumPedido.setEditable(true);
		habilitarBotones(false, false, true); // Solo habilitar el botón de eliminar y OK
		txtNumPedido.requestFocus();
	}

	void modificar() {
		try {
			int codigo = Integer.parseInt(txtNumPedido.getText());
			String nombre = txtNombreCLiente.getText().trim();
			Order order = orderControl.orderDetail(codigo);
			if (order != null) {
				order.setNumOrder(codigo);
				order.setNomCli(nombre);
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
			mensaje("Ingrese el código");
		}
	}

	void consultar() {
		try {
			int codigo = Integer.parseInt(txtNumPedido.getText());
			Order order = orderControl.orderDetail(codigo);

			if (order != null) {
				txtNombreCLiente.setText(order.getNomCli());
				cboEstado.setSelectedIndex(order.getState());

				if (tipoOperacion == MODIFICAR) {
					habilitarEntradas(true);
					txtNumPedido.setEditable(false);
					habilitarBotones(true, false, false);
					txtNombreCLiente.requestFocus();
				}
				if (tipoOperacion == ELIMINAR) {
					txtNumPedido.setEditable(false);
					habilitarBotones(false, false, true);
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
		OrderTableCellRenderer color = new OrderTableCellRenderer();
		model.setRowCount(0);

		for (OrderTicket order : orderControl.listOrders()) {
			Object[] fila = { order.getNumOrder(), order.getIdCliente(), order.getFecha(), order.getNomCli(),
					order.getTotal(), order.isDelivery() ? "SI" : "NO", Estado(order.getState()) };
			model.addRow(fila);
		}

		for (int i = 0; i < tblTabla.getColumnCount(); i++) {
			tblTabla.getColumnModel().getColumn(i).setCellRenderer(color);
		}
	}

	void habilitarEntradas(boolean sino) {
		txtNombreCLiente.setEditable(sino);
	}

	void habilitarBotones(boolean modificarHabilitado, boolean consultarHabilitado, boolean eliminarHabilitado) {
		btnModificar.setEnabled(modificarHabilitado);
		btnConsultar.setEnabled(consultarHabilitado);
		btnEliminar.setEnabled(eliminarHabilitado);
		btnOpciones.setEnabled(!modificarHabilitado && !consultarHabilitado && !eliminarHabilitado);
		btnOk.setEnabled(!modificarHabilitado || !consultarHabilitado || !eliminarHabilitado); 
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
