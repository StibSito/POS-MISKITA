package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controller.ClientController;
import controller.OrderController;
import controller.ProductController;
import controller.SellsController;
import controller.TipoPagoController;
import interfaces.OrderListener;
import lib.NumeroLetras;
import model.CheckboxTableModel;
import model.Client;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.TicketDetail;
import model.TicketHeader;
import model.Tipo;

public class DlgSells extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigoCliente;
	private JButton btnProcesar;
	private JButton btnBorrar;
	private DefaultTableModel model;
	private JButton btnEliminar;
	private JButton btnImprimir;
	private JTextField txtEfectivo;
	private JLabel lblNewLabel_7;
	private JTextField txtTotalPagar;
	private JTextField txtFecha;
	private JTextField txtNumBoleta;
	private JButton btnDecremento;
	private JButton btnIncrementar;

	DecimalFormat df = new DecimalFormat("0.00");
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	private JLabel lblNewLabel_10;
	private JComboBox<String> cboEstado;
	private JComboBox<String> cboDelivery;
	private JLabel lblNewLabel_11;
	private JTextField txtNombreCliente;

	SellsController sells = new SellsController();
	ProductController prod = new ProductController();
	OrderController orderControl = new OrderController();
	ClientController cli = new ClientController();
	private JScrollPane scrollPane_1;
	private JTable tblTabla;
	private JTextArea txtS;
	private JPanel panel;
	private JButton btnBuscar;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private DlgOrders ordersFrame;
	private CheckboxTableModel checkboxModel;
	private JTable tblCheckTable;
	private List<OrderListener> listeners = new ArrayList<>();
	private JComboBox<String> cboTipoPagos;

	public DlgSells() {
		setBounds(new Rectangle(0, 0, 0, 0));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		components();
		fechaActual();
		numBoleta();
		ordersFrame = new DlgOrders(); // Inicializa ordersFrame aquí
	}

	public void components() {
		setBounds(100, 100, 1300, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 235, 215));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre del Cliente :");
		lblNewLabel.setBounds(800, 630, 131, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblNewLabel);

		btnProcesar = new JButton("Procesar\r\n\r\n");
		btnProcesar.setBounds(1150, 655, 120, 40);
		btnProcesar.setIcon(new ImageIcon("E:\\PROYECTO-AED\\ProyectoVentas\\icons\\comprobado.png"));
		btnProcesar.addActionListener(this);
		contentPanel.add(btnProcesar);

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBounds(626, 631, 129, 22);
		contentPanel.add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(930, 75, 330, 512);
		contentPanel.add(scrollPane);

		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);

		btnBorrar = new JButton("Limpiar");
		btnBorrar.setBounds(934, 713, 125, 40);
		btnBorrar.setIcon(new ImageIcon("E:\\PROYECTO-AED\\ProyectoVentas\\icons\\herramienta-de-limpieza.png"));
		btnBorrar.addActionListener(this);
		contentPanel.add(btnBorrar);

		btnEliminar = new JButton("Eliminar ");
		btnEliminar.setBounds(804, 152, 116, 33);
		btnEliminar.setIcon(new ImageIcon("E:\\PROYECTO-AED\\ProyectoVentas\\icons\\disgusto.png"));
		btnEliminar.addActionListener(this);
		contentPanel.add(btnEliminar);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(1145, 713, 125, 40);
		btnImprimir.addActionListener(this);
		contentPanel.add(btnImprimir);

		txtEfectivo = new JTextField();
		txtEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEfectivo.setBounds(800, 259, 120, 29);
		contentPanel.add(txtEfectivo);
		txtEfectivo.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Efectivo : ");
		lblNewLabel_6.setBounds(520, 259, 120, 33);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPanel.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("TOTAL  : ");
		lblNewLabel_7.setBounds(520, 215, 133, 30);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 26));
		contentPanel.add(lblNewLabel_7);

		txtTotalPagar = new JTextField();
		txtTotalPagar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalPagar.setBounds(663, 216, 257, 27);
		txtTotalPagar.setEditable(false);
		txtTotalPagar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		contentPanel.add(txtTotalPagar);
		txtTotalPagar.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fecha : ");
		lblNewLabel_8.setBounds(1020, 12, 71, 14);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblNewLabel_8);

		txtFecha = new JTextField();
		txtFecha.setBounds(1118, 7, 142, 26);
		txtFecha.setEditable(false);
		txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(txtFecha);
		txtFecha.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Nº BOLETA: ");
		lblNewLabel_9.setBounds(1021, 47, 85, 18);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblNewLabel_9);

		txtNumBoleta = new JTextField();
		txtNumBoleta.setBounds(1116, 43, 144, 26);
		txtNumBoleta.setEditable(false);
		txtNumBoleta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(txtNumBoleta);
		txtNumBoleta.setColumns(10);

		btnIncrementar = new JButton("+");
		btnIncrementar.setBounds(581, 152, 59, 33);
		btnIncrementar.setBackground(Color.GREEN);
		btnIncrementar.addActionListener(this);
		contentPanel.add(btnIncrementar);

		btnDecremento = new JButton("-");
		btnDecremento.setBounds(520, 152, 51, 33);
		btnDecremento.setBackground(Color.RED);
		btnDecremento.addActionListener(this);
		contentPanel.add(btnDecremento);

		JLabel lblNewLabel_1 = new JLabel("DELIVERY :");
		lblNewLabel_1.setBounds(800, 685, 90, 13);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(lblNewLabel_1);

		cboDelivery = new JComboBox<String>();
		cboDelivery.setBounds(930, 685, 129, 21);
		cboDelivery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboDelivery.setModel(new DefaultComboBoxModel<String>(new String[] { "SI", "NO" }));
		contentPanel.add(cboDelivery);
		lblNewLabel_10 = new JLabel("ESTADO :");
		lblNewLabel_10.setBounds(800, 658, 80, 13);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(lblNewLabel_10);

		cboEstado = new JComboBox<String>();
		cboEstado.setBounds(930, 655, 129, 21);
		cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "PENDIENTE", "EN PREPARACION" }));
		contentPanel.add(cboEstado);

		lblNewLabel_11 = new JLabel("Codigo Cliente :");
		lblNewLabel_11.setBounds(520, 631, 131, 17);
		lblNewLabel_11.setBackground(SystemColor.windowBorder);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblNewLabel_11);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(520, 657, 129, 19);
		contentPanel.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(520, 13, 400, 129);
		contentPanel.add(scrollPane_1);

		tblTabla = new JTable();
		scrollPane_1.setViewportView(tblTabla);
		model = new DefaultTableModel();
		model.addColumn("CODIGO");
		model.addColumn("DESCRIPCION");
		model.addColumn("UNIDADES");
		model.addColumn("PRECIO");
		model.addColumn("TOTAL");
		tblTabla.setModel(model);

		panel = new JPanel();
		panel.setBounds(10, 12, 484, 479);
		contentPanel.add(panel);

		panel.setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		Font tabFont = new Font("Arial", Font.PLAIN, 15); // Nueva fuente
		tabbedPane.setFont(tabFont); // Establecer la fuente para todas las pestañas
		panel.add(tabbedPane, BorderLayout.WEST);

		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.loadCategoriesAndProducts(tabbedPane, this);

		btnBuscar = new JButton("buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(520, 600, 94, 21);
		contentPanel.add(btnBuscar);

		txtDireccion = new JTextField();
		txtDireccion.setVisible(false);
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(520, 695, 96, 19);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setVisible(false);
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(520, 724, 96, 19);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 508, 493, 245);
		contentPanel.add(scrollPane_2);

		tblCheckTable = new JTable();
		tblCheckTable.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(tblCheckTable);

		checkboxModel = new CheckboxTableModel(
				new Object[] { "Producto", "ENSA", "MAYO", "KET", "MOS", "GOLF", "TAR", "ACE", "AJI" }, 0);

		// Asociar el modelo a la tabla
		tblCheckTable.setModel(checkboxModel);

		cboTipoPagos = new JComboBox<String>();
		cboTipoPagos.setBounds(759, 470, 109, 21);
		contentPanel.add(cboTipoPagos);
		cboTipoPagos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int tipoPago = cboTipoPagos.getSelectedIndex();
					if (tipoPago == 1) { // Efectivo
						txtEfectivo.setEnabled(true);
						txtEfectivo.setEditable(true); // Asegúrate de que sea editable
						txtEfectivo.setText(""); // Limpia el campo para nuevo ingreso
					} else { // Yape o Plin, o "Seleccione"
						txtEfectivo.setEnabled(false);
						txtEfectivo.setEditable(false);
						txtEfectivo.setText(""); // Limpia el texto
					}
				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel("Tipo de Pago :");
		lblNewLabel_2.setBounds(662, 478, 87, 13);
		contentPanel.add(lblNewLabel_2);

		for (int i = 1; i < tblCheckTable.getColumnCount(); i++) {
			tblCheckTable.getColumnModel().getColumn(i)
					.setCellRenderer(tblCheckTable.getDefaultRenderer(Boolean.class));
			tblCheckTable.getColumnModel().getColumn(i).setCellEditor(tblCheckTable.getDefaultEditor(Boolean.class));
			tblCheckTable.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		}

		llenarComboTipos();
		ajustarColumnas();
		alinearColumnas();
	}

	void llenarComboTipos() {

		TipoPagoController pagocon = new TipoPagoController();
		cboTipoPagos.addItem("-Seleccione-");
		for (Tipo tipo : pagocon.lstTipoPagos()) {
			cboTipoPagos.addItem(tipo.getName());
		}

	}

	void alinearColumnas() {
		// centro
		DefaultTableCellRenderer colCentro = new DefaultTableCellRenderer();
		colCentro.setHorizontalAlignment(SwingConstants.CENTER);

		// izquierda
		DefaultTableCellRenderer colLeft = new DefaultTableCellRenderer();
		colLeft.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	void ajustarColumnas() {
		TableColumnModel tcm = tblTabla.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(1).setPreferredWidth(100);
		tcm.getColumn(2).setPreferredWidth(50);
		tcm.getColumn(3).setPreferredWidth(60);
		tcm.getColumn(4).setPreferredWidth(60);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnProcesar) {
			actionPerformedBtnProcesar(arg0);
		}

		if (arg0.getSource() == btnBorrar) {
			actionPerformedBtnBorrar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnImprimir) {
			actionPerformedBtnImprimir(arg0);
		}
		if (arg0.getSource() == btnIncrementar) {
			actionPerformedBtnIncrementar(arg0);
		}
		if (arg0.getSource() == btnDecremento) {
			actionPerformedBtnDecremento(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnSearchId(arg0);
		}

	}

	public void addOrderListener(OrderListener listener) {
		listeners.add(listener);
	}

	public void actionPerformedBtnSearchId(ActionEvent e) {
		searchClient();
	}

	private void searchClient() {
		try {
			int code = Integer.parseInt(txtCodigoCliente.getText());
			Client c = cli.searchClientId(code);
			if (c != null) {
				txtNombreCliente.setText(c.getName());
				txtDireccion.setText(c.getAddress());
				txtTelefono.setText(c.getPhone());
			} else {
				mensaje("Cliente no encontrado");
			}
		} catch (Exception e) {
			mensaje("Ingrese datos");
		}

	}

	String twoDecimals(double num) {
		return String.format("%.2f", num);
	}

	public void actionPerformedBtnIncrementar(ActionEvent arg0) {
		incrementar();
	}

	public void actionPerformedBtnDecremento(ActionEvent arg0) {
		decrementar();
	}

	void incrementar() {

		try {
			int selectedRow = tblTabla.getSelectedRow();
			// Verificar si hay una fila seleccionada
			if (selectedRow != -1) {

				// Obtener la cantidad actual de la fila seleccionada
				int cantidadActual = (int) model.getValueAt(selectedRow, 2);
				double precio = Double.parseDouble(model.getValueAt(selectedRow, 3).toString());
				cantidadActual++;
				double total = cantidadActual * precio;

				// Añadir filas a la tabla de cremas
				for (int i = 0; i < tblTabla.getRowCount(); i++) {
					Object[] filaCremas = { model.getValueAt(selectedRow, 1), false, false, false, false, false, false,
							false, false, false };
					checkboxModel.addRow(filaCremas);

				}

				model.setValueAt(cantidadActual, selectedRow, 2);
				model.setValueAt(total, selectedRow, 4);
				calcularTotal();
			} else
				mensaje("seleccione un fila");
		} catch (Exception e) {

		}

	}

	void decrementar() {

		try {
			int selectedRow = tblTabla.getSelectedRow();
			// Verificar si hay una fila seleccionada
			if (selectedRow != -1) {

				// Obtener la cantidad actual de la fila seleccionada
				int cantidadActual = (int) model.getValueAt(selectedRow, 2);
				double precio = Double.parseDouble(model.getValueAt(selectedRow, 3).toString());
				cantidadActual--;
				// También eliminar la fila correspondiente en la tabla de cremas
				for (int i = 0; i < checkboxModel.getRowCount(); i++) {
					if (checkboxModel.getValueAt(i, 0).equals(model.getValueAt(selectedRow, 1))) {
						checkboxModel.removeRow(i);
					}

				}
				if (cantidadActual < 1) {
					Object[] filaCremas = { model.getValueAt(selectedRow, 1), false, false, false, false, false, false,
							false, false, false };
					checkboxModel.addRow(filaCremas);

				}
				if (cantidadActual < 1) {
					cantidadActual = 1;

				}
				double total = cantidadActual * precio;

				model.setValueAt(cantidadActual, selectedRow, 2);
				model.setValueAt(total, selectedRow, 4);
				calcularTotal();
			} else
				mensaje("seleccione una fila");

		} catch (Exception e) {

		}

	}

	public void actionPerformedBtnProcesar(ActionEvent arg0) {
		// realizar la venta
		procesar();
	}

	public void actionPerformedBtnBorrar(ActionEvent arg0) {
		limpieza(model, checkboxModel);
		txtNumBoleta.setText(sells.generarNumBoleta());

	}

	public void actionPerformedBtnEliminar(ActionEvent arg0) {
		// eliminar produco de la tabla
		int filaSeleccionada = tblTabla.getSelectedRow();

		if (filaSeleccionada != -1) {
			int ok = confirmar("Desea eliminar el producto?");
			if (ok == 0) {
				model.removeRow(filaSeleccionada);
				for (int i = 0; i < checkboxModel.getRowCount(); i++) {
					if (checkboxModel.getValueAt(i, 0).equals(model.getValueAt(i, 1))) {
						checkboxModel.removeRow(i);
					}
				}
				calcularTotal();
				mensaje("producto eliminado ");
			}
		} else {
			mensaje("Selecciona una fila para eliminar.");
		}
	}

	void procesarCremas(CheckboxTableModel checkboxModel) {

		// Recorre las filas de la tabla de checkboxes
		for (int i = 0; i < checkboxModel.getRowCount(); i++) {
			// Lee el nombre del producto de la primera columna
			String producto = (String) checkboxModel.getValueAt(i, 0);

			// Crea una lista para almacenar las cremas marcadas para este producto
			ArrayList<String> cremasMarcadas = new ArrayList<>();

			// Recorre las columnas de cremas (a partir de la segunda columna)
			for (int j = 1; j < checkboxModel.getColumnCount(); j++) {
				// Si la crema está marcada, agrégala a la lista
				if ((boolean) checkboxModel.getValueAt(i, j)) {
					cremasMarcadas.add(checkboxModel.getColumnName(j));
				}
			}

			// Imprime el resultado
			if (!cremasMarcadas.isEmpty() || !producto.isEmpty()) {
				txtS.append(" " + producto + "  : ");
				for (String crema : cremasMarcadas) {
					txtS.append(crema + "  ");
				}
				// salto de línea después de imprimir todas las cremas
				imprimir();
			}

		}
	}

	void procesar() {
		try {
			double efectivo = 0;
			double total = Double.parseDouble(txtTotalPagar.getText());
			int tipoPago = cboTipoPagos.getSelectedIndex();
			double importeSubTotal = 0;

			String nombre = txtNombreCliente.getText();

			try {
				if (nombre.isEmpty()) {
					error("Agregue el nombre del cliente", txtNombreCliente);
					return;
				}

				// Verificar el tipo de pago antes de registrar en la base de datos
				if (tipoPago == 1) { // Pago en efectivo
					try {
						efectivo = Double.parseDouble(txtEfectivo.getText());
						if (efectivo < total) {
							error("El monto en efectivo es insuficiente", txtEfectivo);
							return; // Sale del método si el efectivo es insuficiente
						}
					} catch (NumberFormatException e) {
						error("Debe ingresar un valor numérico válido en el campo de efectivo", txtEfectivo);
						return;
					}
				}

				// Insertar la cabecera del pedido en la tabla Pedido
				Order order = new Order();
				String clienteIdText = txtCodigoCliente.getText();
				order.setIdCliente(clienteIdText.isEmpty() ? 0 : Integer.parseInt(clienteIdText));
				order.setNomCli(txtNombreCliente.getText());
				order.setDelivery(leerDelivery() == 0);
				order.setState(leerEstado());

				int generatedOrderNumber = orderControl.addOrder(order);
				// insertar a la cabecera
				TicketHeader cab = new TicketHeader();
				cab.setIdtipo(cboTipoPagos.getSelectedIndex());
				cab.setTotal(Double.parseDouble(txtTotalPagar.getText()));
				cab.setNum_order(generatedOrderNumber);

				if (generatedOrderNumber <= 0) {
					System.out.println("No se pudo obtener el número de orden generado.");
					return; // Manejar el error si no se generó el número de orden
				}

				// Insertar los detalles del pedido en la tabla DetallePedido
				for (int i = 0; i < model.getRowCount(); i++) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setNumOrder(generatedOrderNumber);
					orderDetail.setIdproduct((int) model.getValueAt(i, 0));
					orderDetail.setQuantity((int) model.getValueAt(i, 2));
					orderDetail.setTotal(Double.parseDouble(model.getValueAt(i, 4).toString())); // Total

					orderControl.addOrderDetail(orderDetail);
				}

				Client c = new Client();
				c.setName(txtNombreCliente.getText());
				// Llamar a listOrders() solo después de asegurarte de que ordersFrame no es
				// null

				// Procesar la orden, pasar la cabecera y los detalles
				int result = sells.processOrder(cab);
				ordersFrame.listOrders(); // Esto debería funcionar ahora
				// Notifica a todos los observadores
				for (OrderListener listener : listeners) {
					listener.onOrderProcessed();
				}

				imprimir();
				imprimir("\t            NELLY'S BURGER");
				imprimir("\t               MISKITA MIKUY");
				imprimir("                        " + "AV. MANUEL SEGURA, LIMA 15060");
				imprimir("\t    BARRANCO - LIMA - PERÚ");
				imprimir(" *************************************************************** ");
				imprimir("\tBOLETA VENTA ELECTRONICA");
				imprimir("\t               " + txtNumBoleta.getText());
				imprimir(" *************************************************************** ");
				imprimir();
				imprimir(" TN\t\t : " + dtf.format(now));
				imprimir(" Caja\t\t : " + "01");
				imprimir(" Código del Cliente\t : " + txtCodigoCliente.getText());
				imprimir(" Nombre \t\t : " + txtNombreCliente.getText());
				imprimir(" DNI \t\t : " + " ");
				imprimir(" observacion");
				imprimir("  * V *");
				imprimir();
				imprimir(" CANT      PRODUCTO\t     P.UNIT\t    TOTAL");
				imprimir("  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

				for (int i = 0; i < model.getRowCount(); i++) {
					int codigoProductoTable = (int) model.getValueAt(i, 0);
					Product productoTable = prod.searchProductId(codigoProductoTable);
					int unidadesTable = (int) model.getValueAt(i, 2);

					if (productoTable != null) {
						double precioUnitario = productoTable.getPrice();
						double totalProducto = unidadesTable * precioUnitario;
						importeSubTotal += totalProducto;

						String descripcionProducto = productoTable.getDescription().toUpperCase();
						if (descripcionProducto.length() > 15) {
							descripcionProducto = descripcionProducto.substring(0, 15) + "...";
						}

						if (totalProducto >= 100) {
							imprimir(String.format(" %-7d %-17s\t %8s\t%11s", unidadesTable, descripcionProducto,
									formatearNumero3dig(df.format(precioUnitario)),
									formatearNumero3dig(df.format(totalProducto))));
						} else if (totalProducto >= 10) {
							imprimir(String.format(" %-7d %-17s\t %8s\t%11s", unidadesTable, descripcionProducto,
									formatearNumero2dig(df.format(precioUnitario)),
									formatearNumero2dig(df.format(totalProducto))));
						} else {
							imprimir(String.format(" %-7d %-17s\t %8s\t%12s", unidadesTable, descripcionProducto,
									formatearNumero(df.format(precioUnitario)),
									formatearNumero(df.format(totalProducto))));
						}
					}
				}

				imprimir("  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
				double operacion = (importeSubTotal * 100) / 118;
				double importeIGV = operacion * 0.18;
				double importeTotal = operacion + importeIGV;
				double vuelto = efectivo - importeTotal;
				long parteEntera = (long) importeTotal;

				showtotals(" OP.GRAVADA", operacion);
				showtotals(" SUBTOTAL", importeSubTotal);
				showtotals(" IGV 18%", importeIGV);
				showtotals(" TOTAL VENTA", total);
				if (tipoPago == 1) {
					showtotals(" EFECTIVO", total);
					showtotals(" VUELTO", vuelto);

				} else if (tipoPago == 2) {
					showtotals(" VUELTO", 0.00);
					showtotals(" TIPO PAGO", "YAPE");

				} else if (tipoPago == 3) {
					showtotals(" VUELTO", 0.00);
					showtotals(" TIPO PAGO", "PLIN");
				}

				imprimir();
				imprimir();
				imprimir("  SON : " + NumeroLetras.texto(parteEntera).toUpperCase() + " CON "
						+ String.format("%.2f", importeTotal % 1).substring(2) + "/100" + " SOLES");

				if (checkboxModel.getRowCount() > 0) {
					imprimir("  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
					imprimir(" Nombre : " + nombre);
					imprimir(" Pedido : Numero " + generatedOrderNumber);
					procesarCremas(checkboxModel);
					imprimir("  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
				} else {
					imprimir("");
				}

				limpiarTabla(model, checkboxModel);
				Font font = new Font("Arial", Font.BOLD, 23); // Ajusta el tamaño de la fuente
				UIManager.put("OptionPane.messageFont", font);
				UIManager.put("OptionPane.buttonFont", font);
				UIManager.put("OptionPane.background", Color.GREEN); // Cambiar el color de fondo
				UIManager.put("OptionPane.messageForeground", Color.WHITE); // Cambiar el color del texto

				// dimensiones de la pantalla
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				if (tipoPago == 2 || tipoPago == 3)
					vuelto = 0;
				// mensaje HTML
				String message = "<html><body style='font-family:Arial;font-size:23px;width:100%;height:100%'>"
						+ "<p style='color:black'>VENTA COMPLETADA</p>"
						+ "<p><span style='color:green;'>COBRADO:</span> <span style='color:green;'>"
						+ df.format(importeTotal) + "</span></p>"
						+ "<p><span style='color:red;'>CAMBIO:</span> <span style='color:red;'>" + df.format(vuelto)
						+ "</span></p>" + "</body></html>";

				JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);

				// tamaño del JOptionPane
				pane.setPreferredSize(new Dimension(500, 300));

				JDialog dialog = pane.createDialog("Mensaje"); // título para el cuadro de diálogo
				dialog.setSize(500, 300);

				// Calcular la posición para centrar el cuadro de diálogo
				int x = (int) ((screenSize.getWidth() - dialog.getWidth()) / 2);
				int y = (int) ((screenSize.getHeight() - dialog.getHeight()) / 2);

				// Establecer la posición del cuadro de diálogo
				dialog.setLocation(x, y);

				// Mostrar el cuadro de diálogo
				dialog.setVisible(true);

				if (result > 0) {
					System.out.println("Venta registrada exitosamente");
					;
				} else {
					System.out.println("Error al registrar la venta");
				}
			} catch (Exception e) {
				System.out.println("Error al procesar la venta: " + e.getMessage());
			}
		} catch (

		Exception e) {
			mensaje("Debe ingresar los datos" + "\n" + "para procesar la venta");
		}

	}

	private void showtotals(String tittle, String string) {
		imprimir(tittle + "\t\t   " + "   :\t  " + string);

	}

	private void showtotals(String tittle, Double amount) {
		if (amount >= 100) {
			imprimir(tittle + "\t\t" + "S/:\t" + formatearNumero3dig(df.format(amount)));
		} else if (amount >= 10 && amount < 100) {
			imprimir(tittle + "\t\t" + "S/:\t" + formatearNumero2dig(df.format(amount)));
		} else
			imprimir(tittle + "\t\t" + "S/:\t" + formatearNumero(df.format(amount)));

	}

	private static String formatearNumero3dig(String string) {
		return String.format("%10s", string);
	}

	private static String formatearNumero2dig(String string) {
		return String.format("%11s", string);
	}

	private static String formatearNumero(String string) {
		return String.format("%12s", string);
	}

	void fechaActual() {
		DateTimeFormatter year = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDateTime now = LocalDateTime.now();
		txtFecha.setText(year.format(now));
	}

	String printNumBol() {
		return sells.generarNumBoleta();
	}

	void numBoleta() {
		txtNumBoleta.setText(sells.generarNumBoleta() + "");
	}

	public void actionPerformedBtnImprimir(ActionEvent arg0) {

	}

	void limpieza(DefaultTableModel model, CheckboxTableModel checkboxModel) {
		txtTotalPagar.setText("");
		txtCodigoCliente.setText("");
		txtNombreCliente.setText("");
		txtCodigoCliente.requestFocus();
		txtEfectivo.setText("");
		limpiarTabla(model, checkboxModel);
		txtS.setText("");

	}

	void limpiarTabla(DefaultTableModel model, CheckboxTableModel checkboxModel) {
		// limpiar la tabla
		while (model.getRowCount() > 0) {
			model.removeRow(0);

		}

		while (checkboxModel.getRowCount() > 0) {
			checkboxModel.removeRow(0);
		}
	}

	public void addProductToTable(int productId, String productName, double productPrice) {
		int currentQuantity = 1;
		double total = currentQuantity * productPrice;

		// Añadir filas a la tabla de cremas
		for (int i = 0; i < currentQuantity; i++) {
			Object[] filaCremas = { productName, false, false, false, false, false, false, false, false, false };
			checkboxModel.addRow(filaCremas);

		}

		for (int i = 0; i < model.getRowCount(); i++) {
			if ((int) model.getValueAt(i, 0) == productId) {
				currentQuantity = (int) model.getValueAt(i, 2);
				int newQuantity = currentQuantity + 1;
				model.setValueAt(productName, i, 1);
				model.setValueAt(newQuantity, i, 2);
				total = newQuantity * productPrice;
				model.setValueAt(String.format("%.2f", total), i, 4);
				calcularTotal(); // Calcular total después de actualizar la cantidad
				return;
			}
		}
		model.addRow(new Object[] { productId, productName, currentQuantity, productPrice, total });
		calcularTotal(); // Calcular total después de agregar el primer producto
	}

	void calcularTotal() {
		double totalPagar = 0;
		for (int i = 0; i < model.getRowCount(); i++) {
			double totalAcumulado = Double.parseDouble(model.getValueAt(i, 4).toString());
			totalPagar += totalAcumulado;
		}

		txtTotalPagar.setText(twoDecimals(totalPagar));
	}

	public void updateProductQuantity(int productId, int quantity) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if ((int) model.getValueAt(i, 0) == productId) {
				double productPrice = (double) model.getValueAt(i, 3);
				model.setValueAt(quantity, i, 2);
				model.setValueAt(String.format("%.2f", quantity * productPrice), i, 4);
				return;
			}
		}
	}

	private class DatabaseHelper {

		private static final String DB_URL = "jdbc:mysql://localhost:3306/MISKITA";
		private static final String USER = "root";
		private static final String PASS = "18S09A11N";

		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(DB_URL, USER, PASS);
		}

		public void loadCategoriesAndProducts(JTabbedPane tabbedPane, DlgSells dlgSells) {
			String categoryQuery = "SELECT idCategory, name FROM tb_category";
			String productQuery = "SELECT idProduct, description, price FROM tb_Product WHERE idCategory = ?";

			try (Connection conn = getConnection();
					PreparedStatement categoryStmt = conn.prepareStatement(categoryQuery);
					PreparedStatement productStmt = conn.prepareStatement(productQuery)) {

				ResultSet categoryRs = categoryStmt.executeQuery();
				while (categoryRs.next()) {
					int categoryId = categoryRs.getInt("idCategory");
					String categoryName = categoryRs.getString("name");

					JPanel categoryPanel = new JPanel();
					categoryPanel.setLayout(new GridBagLayout()); // Usar GridBagLayout para el panel de categoría
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre botones
					gbc.fill = GridBagConstraints.HORIZONTAL; // Los botones llenan la celda horizontalmente
					gbc.anchor = GridBagConstraints.NORTHWEST; // Alinear componentes en la parte superior izquierda
					gbc.weightx = 1.0; // Asegura que los botones se expandan horizontalmente
					gbc.weighty = 0.0; // Los botones no se expanden verticalmente

					productStmt.setInt(1, categoryId);
					ResultSet productRs = productStmt.executeQuery();

					int productCount = 0;
					while (productRs.next()) {
						int productId = productRs.getInt("idProduct");
						String productName = productRs.getString("description");
						double productPrice = productRs.getDouble("price");

						JButton productButton = new JButton(
								"<html><b>" + productName + "</b><br>S/." + twoDecimals(productPrice) + "</html>");
						Font pro = new Font("Arial", Font.PLAIN, 12);
						productButton.setFont(pro);
						productButton.setPreferredSize(new Dimension(65, 65)); // Hacer que los botones sean cuadrados
						productButton.setMaximumSize(new Dimension(65, 65)); // Asegurar el tamaño máximo
						productButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								dlgSells.addProductToTable(productId, productName, productPrice);
							}
						});

						productButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (e.getButton() == MouseEvent.BUTTON3) { // Click derecho
									String quantityStr = JOptionPane.showInputDialog(dlgSells,
											"Ingrese cantidad para " + productName + ":");
									try {
										int quantity = Integer.parseInt(quantityStr);
										dlgSells.addProductToTable(productId, productName, productPrice);
										dlgSells.updateProductQuantity(productId, quantity);
									} catch (NumberFormatException ex) {
										JOptionPane.showMessageDialog(dlgSells, "Cantidad invalida.", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						});

						// Configurar las restricciones del GridBagLayout
						gbc.gridx = productCount % 3; // Columna
						gbc.gridy = productCount / 3; // Fila

						categoryPanel.add(productButton, gbc);
						productCount++;
					}

					// Ajustar tamaño del panel para que se ajuste al contenido
					JScrollPane scrollPane = new JScrollPane(categoryPanel);
					tabbedPane.addTab(categoryName, scrollPane); // Agregar el JScrollPane directamente al tabbedPane

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	void imprimir() {
		imprimir("");
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	void imprimir(String s) {
		txtS.append(s + '\n');
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}

	int leerDelivery() {
		return cboDelivery.getSelectedIndex();
	}

	String isDelivery(int i) {
		return (String) cboDelivery.getItemAt(i);
	}

	int leerEstado() {
		return cboEstado.getSelectedIndex();
	}

	String Estado(int i) {
		return cboEstado.getItemAt(i);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
}
