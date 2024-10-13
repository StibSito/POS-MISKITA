package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProductController;
import model.CategoryItem;
import model.Product;

public class DlgProducts extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtDesc;
	private JTextField txtPrecio;
	private JButton btnAgregar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnOpciones;
	private JButton btnBuscar;
	private JButton btnOK;
	private JScrollPane scrollPane;
	private JTable tblTabla;
	private DefaultTableModel model;

	private int tipoOperacion;

	ProductController productController = new ProductController();

	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR = 3;
	private JComboBox<String> cboDisponible;
	private JComboBox<CategoryItem> cboCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgProducts dialog = new DlgProducts();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProducts() {
		setBounds(100, 100, 800, 590);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00F3digo :");
		lblNewLabel.setBounds(10, 11, 68, 14);
		contentPanel.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(88, 8, 108, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setEditable(false);
		txtDesc.setBounds(88, 38, 207, 20);
		contentPanel.add(txtDesc);
		txtDesc.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(88, 68, 108, 20);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(685, 7, 89, 23);
		contentPanel.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(685, 33, 89, 23);
		contentPanel.add(btnModificar);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(685, 60, 89, 23);
		contentPanel.add(btnConsultar);

		btnEliminar = new JButton("Elminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(685, 88, 89, 23);
		contentPanel.add(btnEliminar);

		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n :");
		lblNewLabel_1.setBounds(10, 36, 80, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Precio :");
		lblNewLabel_2.setBounds(10, 70, 46, 14);
		contentPanel.add(lblNewLabel_2);

		btnOpciones = new JButton("Opciones");
		btnOpciones.setEnabled(false);
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(587, 11, 89, 91);
		contentPanel.add(btnOpciones);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(206, 7, 89, 23);
		contentPanel.add(btnBuscar);

		btnOK = new JButton("Ok");
		btnOK.setEnabled(false);
		btnOK.addActionListener(this);
		btnOK.setBounds(209, 123, 73, 23);
		contentPanel.add(btnOK);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 764, 390);
		contentPanel.add(scrollPane);

		tblTabla = new JTable();
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);

		model = new DefaultTableModel();
		model.addColumn("CÓDIGO");
		model.addColumn("DESCRIPCIÓN");
		model.addColumn("PRECIO");
		model.addColumn("CATEGORIA");
		model.addColumn("DISPONIBLE");
		tblTabla.setModel(model);

		JLabel lblNewLabel_3 = new JLabel("Disponible :");
		lblNewLabel_3.setBounds(11, 124, 68, 13);
		contentPanel.add(lblNewLabel_3);

		cboDisponible = new JComboBox<String>();
		cboDisponible.setModel(new DefaultComboBoxModel<String>(new String[] { "SI", "NO" }));
		cboDisponible.setBounds(87, 121, 46, 21);
		contentPanel.add(cboDisponible);

		JLabel lblNewLabel_4 = new JLabel("Categoria :");
		lblNewLabel_4.setBounds(10, 101, 68, 13);
		contentPanel.add(lblNewLabel_4);

		cboCategoria = new JComboBox<CategoryItem>();
		cboCategoria.setBounds(86, 96, 154, 21);
		contentPanel.add(cboCategoria);

		loadCategories();
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
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(arg0);
		}

	}

	public void actionPerformedBtnBuscar(ActionEvent arg0) {
		consultarProducto();
	}

	public void actionPerformedBtnOK(ActionEvent arg0) {
		switch (tipoOperacion) {
		case ADICIONAR:
			agregarProducto();
			break;
		case CONSULTAR:
			consultarProducto();
			break;
		case MODIFICAR:
			modificarProducto();
			break;
		case ELIMINAR:
			eliminarProducto();
		}

	}

	public void actionPerformedBtnOpciones(ActionEvent arg0) {
		txtCodigo.setText("");
		txtDesc.setText("");
		txtPrecio.setText("");
		txtCodigo.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);
	}

	public void actionPerformedBtnAgregar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		txtCodigo.setText(productController.getNextProductId() + "");
		habilitarEntradas(true);
		habilitarBotones(false);
		txtDesc.requestFocus();
	}

	public void actionPerformedBtnModificar(ActionEvent arg0) {
		tipoOperacion = MODIFICAR;
		txtCodigo.setEditable(true);
		habilitarBotones(false);
		txtCodigo.requestFocus();
	}

	public void actionPerformedBtnConsultar(ActionEvent arg0) {

		tipoOperacion = CONSULTAR;
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

	void limpiar() {
		txtDesc.setText("");
		txtPrecio.setText("");
		cboDisponible.setSelectedIndex(0);
		cboCategoria.setSelectedIndex(0);
	}

	void agregarProducto() {
		try {
			
			String desc = leerDesc();
			if (desc.isEmpty()) {
				error("Ingrese un nombre", txtDesc);
				return;
			}

			double price = leerPrecio();
			if (price < 0.1) {
				error("Ingrese un precio valido", txtPrecio);
				return;
			}

			int categoryId = leerCategoria();
			if (categoryId == -1) {
				mensaje("Seleccione una categoría");
				return;
			}
			Product p = new Product();
			p.setDescription(desc);
			p.setPrice(price);
			p.setAvailable(leerDisponible() == 0);
			p.setIdCategory(categoryId);
			productController.addProduct(p);
			listar();
			limpiar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void consultarProducto() {
		try {
			int codigo = leerCodigo();
			Product product = productController.detailProduct(codigo);

			if (product != null) {

				txtDesc.setText(product.getDescription());
				txtPrecio.setText(product.getPrice() + "");
				cboDisponible.setSelectedIndex(product.isAvailable() ? 0 : 1);

				if (tipoOperacion == MODIFICAR) {
					habilitarEntradas(true);
					txtCodigo.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
					txtDesc.requestFocus();
				}
				if (tipoOperacion == ELIMINAR) {
					txtCodigo.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
				}
			} else {
				error("El código " + codigo + " no existe", txtCodigo);
			}
		} catch (

		Exception e) {
			error("Ingrese CÓDIGO correcto", txtCodigo);
		}
	}

	void modificarProducto() {
		try {
			Product p = new Product();

			String desc = leerDesc();
			if (desc.isEmpty()) {
				error("Ingrese un nombre", txtDesc);
				return;
			}

			double price = leerPrecio();
			if (price < 1) {
				error("Ingrese un precio valido", txtPrecio);
				return;
			}

			int categoryId = leerCategoria();
			if (categoryId == 0) {
				mensaje("Seleccione una categoría");
				return;
			}
			p.setId(leerCodigo());
			p.setDescription(desc);
			p.setPrice(price);
			p.setIdCategory(categoryId);
			p.setAvailable(leerDisponible() == 0);
			productController.updateProduct(p);
			listar();
			limpiar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void eliminarProducto() {

		int codigo = leerCodigo();
		try {

			productController.deleteProdcut(codigo);
			mensaje("Producto " + codigo + " Eliminado");
			listar();
		} catch (Exception e) {
			error("El codigo " + codigo + " no existe", txtCodigo);
		}
	}

	void listar() {
		model.setRowCount(0);
		for (Product product : productController.productList()) {
			Object[] fila = { product.getId(), product.getDescription(), product.getPrice(),
					category(product.getIdCategory()), product.isAvailable() ? "SI" : "NO" };
			model.addRow(fila);
		}
	}

	void habilitarEntradas(boolean sino) {
		txtDesc.setEditable(sino);
		txtPrecio.setEditable(sino);
	}

	void habilitarBotones(boolean sino) {
		if (tipoOperacion == ADICIONAR)
			btnOK.setEnabled(!sino);
		else {
			btnBuscar.setEnabled(!sino);
			btnOK.setEnabled(false);
		}
		btnAgregar.setEnabled(sino);
		btnConsultar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnEliminar.setEnabled(sino);
		btnOpciones.setEnabled(!sino);
	}

	public void loadCategories() {
		List<CategoryItem> categories = productController.getCategories();
		cboCategoria.removeAllItems();
		cboCategoria.addItem(new CategoryItem(0, "Seleccione una categoría")); // Elemento inicial
		for (CategoryItem category : categories) {
			cboCategoria.addItem(category);
		}
		cboCategoria.setSelectedIndex(0); // Seleccionar el primer elemento por defecto
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

	int leerCategoria() {
		return cboCategoria.getSelectedIndex();
	}

	CategoryItem category(int i) {
		return cboCategoria.getItemAt(i);
	}

	int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText().trim());
	}

	String leerDesc() {
		return txtDesc.getText().trim();
	}

	int leerDisponible() {
		return cboDisponible.getSelectedIndex();
	}

	String isDisponible(int i) {
		return cboDisponible.getItemAt(i);
	}

	double leerPrecio() {
		return Double.parseDouble(txtPrecio.getText().trim());
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
}
