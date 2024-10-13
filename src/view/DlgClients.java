package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ClientController;
import model.Client;

public class DlgClients extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombres;
	private JTextField txtTelefono;
	private JTable tblTabla;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnConsultar;
	private JButton btnEliminar;
	private JButton btnOpciones;
	private JButton btnOk;
	private JButton btnBuscar;
	private DefaultTableModel model;

	private ClientController clienteController = new ClientController();

	private int tipoOperacion;

	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR = 3;
	private JLabel lblNewLabel_2;
	private JTextField txtDireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgClients dialog = new DlgClients();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public DlgClients() {
		setBounds(100, 100, 820, 599);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(705, 11, 89, 23);
		contentPanel.add(btnAgregar);

		JLabel lblNewLabel = new JLabel("Código : ");
		lblNewLabel.setBounds(10, 15, 62, 14);
		contentPanel.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(82, 12, 107, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		txtNombres.setBounds(82, 36, 198, 20);
		contentPanel.add(txtNombres);
		txtNombres.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(82, 61, 129, 20);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombres :");
		lblNewLabel_1.setBounds(10, 39, 62, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Teléfono :");
		lblNewLabel_3.setBounds(10, 63, 62, 14);
		contentPanel.add(lblNewLabel_3);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(705, 35, 89, 23);
		contentPanel.add(btnModificar);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(705, 59, 89, 23);
		contentPanel.add(btnConsultar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(705, 84, 89, 23);
		contentPanel.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 137, 784, 412);
		contentPanel.add(scrollPane);

		tblTabla = new JTable();
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);

		model = new DefaultTableModel();
		model.addColumn("CÓDIGO");
		model.addColumn("NOMBRES");
		model.addColumn("TELÉFONO");
		model.addColumn("DIRECCION");
		tblTabla.setModel(model);

		btnOpciones = new JButton("Opciones");
		btnOpciones.setEnabled(false);
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(606, 22, 89, 75);
		contentPanel.add(btnOpciones);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(201, 11, 79, 23);
		contentPanel.add(btnBuscar);

		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.addActionListener(this);
		btnOk.setBounds(232, 104, 72, 23);
		contentPanel.add(btnOk);

		lblNewLabel_2 = new JLabel("Direccion :");
		lblNewLabel_2.setBounds(11, 89, 61, 13);
		contentPanel.add(lblNewLabel_2);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(82, 86, 129, 19);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);

		listar();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnOk) {
			actionPerformedBtnOk(arg0);
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(arg0);
		}

	}

	public void actionPerformedBtnBuscar(ActionEvent arg0) {
		consultarCliente();
	}

	public void actionPerformedBtnOk(ActionEvent arg0) {
		switch (tipoOperacion) {
		case ADICIONAR:
			agregarCliente();
			txtCodigo.setText(clienteController.getNextClientId() + "");
			break;
		case CONSULTAR:
			consultarCliente();
			break;
		case MODIFICAR:
			modificarCliente();
			break;
		case ELIMINAR:
			eliminarCliente();
		}
	}

	void limpiar() {
		txtNombres.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");

	}

	public void actionPerformedBtnOpciones(ActionEvent arg0) {
		txtCodigo.setText("");
		txtNombres.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtCodigo.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);
	}

	public void actionPerformedBtnAgregar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		txtCodigo.setText(clienteController.getNextClientId() + "");
		habilitarEntradas(true);
		habilitarBotones(false);
		txtNombres.requestFocus();
	}

	public void actionPerformedBtnConsultar(ActionEvent arg0) {
		tipoOperacion = CONSULTAR;
		txtCodigo.setEditable(true);
		habilitarBotones(false);
		txtCodigo.requestFocus();
	}

	public void actionPerformedBtnModificar(ActionEvent arg0) {
		tipoOperacion = MODIFICAR;
		txtCodigo.setEditable(true);
		habilitarBotones(false);
		txtCodigo.requestFocus();

	}

	public void actionPerformedBtnEliminar(ActionEvent arg0) {
		tipoOperacion = ELIMINAR;
		txtCodigo.setEditable(true);
		habilitarBotones(false);
		txtCodigo.requestFocus();
	}

	void agregarCliente() {

		try {

			Client cliente = new Client();

			String name = leerNombre();
			if (name.isEmpty()) {
				error("Ingrese Nombre correcto", txtNombres);
				return;
			}

			String address = leerDireccion();

			if (address.isEmpty()) {
				error("Ingrese Direccion correcta", txtDireccion);
				return;
			}

			String telefono = leerTelefono();
			if (telefono.isEmpty()) {
				error("Ingrese un teléfono correcto", txtTelefono);
				return;
			}

			if (telefono.length() < 9 || telefono.length() > 9) {
				error("Ingrese un número de teléfono válido", txtTelefono);
				return;
			}

			cliente.setName(name);
			cliente.setAddress(address);
			cliente.setPhone(telefono);
			clienteController.addFullClient(cliente);
			limpiar();
			listar();

		} catch (Exception e) {

		}

	}

	void consultarCliente() {

		try {
			int codigo = leerCodigo();
			Client cliente = clienteController.clientDetail(codigo);

			if (cliente != null) {

				txtNombres.setText(cliente.getName());
				txtDireccion.setText(cliente.getAddress());
				txtTelefono.setText(cliente.getPhone());

				if (tipoOperacion == MODIFICAR) {
					habilitarEntradas(true);
					txtCodigo.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOk.setEnabled(true);
					txtNombres.requestFocus();
				}
				if (tipoOperacion == ELIMINAR) {
					txtCodigo.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOk.setEnabled(true);
				}
			} else {
				error("El código " + codigo + " no existe", txtCodigo);
			}
		} catch (Exception e) {
			error("Ingrese CÓDIGO correcto", txtCodigo);
		}
	}

	void modificarCliente() {
		try {
			Client cliente = new Client();

			String name = leerNombre();
			if (name.isEmpty()) {
				error("Ingrese Nombre correcto", txtNombres);
				return;
			}

			cliente.setIdClient(leerCodigo());
			cliente.setName(name);
			cliente.setPhone(leerTelefono());
			cliente.setAddress(leerDireccion());
			clienteController.updateClient(cliente);
			limpiar();
			listar();
		} catch (Exception e) {
			error("Ingrese código correcto", txtCodigo);
		}

	}

	void eliminarCliente() {
		int codigo = leerCodigo();
		try {
			
			clienteController.deleteClient(codigo);
			mensaje("Cliente "+ codigo + " Eliminado");
			listar();
		} catch (Exception e) {
			error("el codigo" + codigo + "no existe", txtCodigo);
		}
		
	}

	void listar() {
		model.setRowCount(0);
		for (Client cliente : clienteController.clientList()) {
			Object[] fila = { cliente.getIdClient(),
							  cliente.getName(),
							  cliente.getPhone(),
							  cliente.getAddress(),
							};
			model.addRow(fila);
		}
	}

	void habilitarEntradas(boolean sino) {
		txtNombres.setEditable(sino);
		if (tipoOperacion == MODIFICAR)
			txtCodigo.setEditable(sino);
		txtDireccion.setEditable(sino);
		txtTelefono.setEditable(sino);
	}

	void habilitarBotones(boolean sino) {
		if (tipoOperacion == ADICIONAR)
			btnOk.setEnabled(!sino);
		else {
			btnBuscar.setEnabled(!sino);
			btnOk.setEnabled(false);
		}
		btnAgregar.setEnabled(sino);
		btnConsultar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnEliminar.setEnabled(sino);
		btnOpciones.setEnabled(!sino);
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

	int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	String leerNombre() {
		return txtNombres.getText().trim();
	}

	String leerDireccion() {
		return txtDireccion.getText().trim();
	}



	String leerTelefono() {
		return txtTelefono.getText().trim();
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
}
