package com.trascender.gui.framework.abmStandard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Recursos;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public abstract class AdminView extends JDialog {
	
	//-- Seccion: CABECERA
	private PnlAdminCabecera pnlCabecera;
	//-- Seccion: CUERPO
	private PnlAdminCuerpo pnlCuerpo;
		//-- Seccion: BUSQUEDA
		private PnlAdminBusqueda pnlBusqueda;
		private PnlAdminBotonesBusqueda pnlBotonesBusqueda;
		//-- Seccion: TABLA
		private JPanel pnlContenedorTabla;
			private JLabel lblTitulo;
			private PnlTabla pnlTabla;
	//-- Seccion: PIE
	private PnlAdminPie pnlPie;
	
	public AdminView() {
		super();
		this.init();
	}
	
	public AdminView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		this.initVentana();
		
		this.pnlCabecera = new PnlAdminCabecera();
		this.getContentPane().add(this.pnlCabecera, BorderLayout.NORTH);
		
		this.pnlCuerpo = new PnlAdminCuerpo();
		this.getContentPane().add(this.pnlCuerpo, BorderLayout.CENTER);
		
		this.pnlBusqueda = new PnlAdminBusqueda();
		this.pnlCuerpo.add(this.pnlBusqueda, BorderLayout.NORTH);
		
		this.pnlBotonesBusqueda = new PnlAdminBotonesBusqueda();
		
		this.pnlContenedorTabla = new JPanel();
		this.pnlContenedorTabla.setLayout(new BorderLayout());
		
			this.lblTitulo = new JLabel();
			this.lblTitulo.setFont(new Font("Verdana",0,12));
			this.lblTitulo.setPreferredSize(new Dimension(ConstantesTamanio.LBL_WIDTH, ConstantesTamanio.LBL_HEIGHT));
			this.lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 20, 6, 0));
			this.lblTitulo.setVisible(false);
			this.pnlContenedorTabla.add(this.lblTitulo, BorderLayout.NORTH);
			
			this.pnlTabla = new PnlTabla();
			this.pnlContenedorTabla.add(this.pnlTabla, BorderLayout.CENTER);
			
		this.pnlCuerpo.add(this.pnlContenedorTabla, BorderLayout.CENTER);
		
		this.pnlPie = new PnlAdminPie();
		this.getContentPane().add(this.pnlPie, BorderLayout.SOUTH);
		
		Color locColor = Constantes.COLOR_ADMIN;
		this.pnlCabecera.setBackground(locColor);
		this.pnlPie.setBackground(locColor);
		this.pnlCabecera.getLblImagen().setIcon(AppManager.getInstance().getAdminRecursos().getIcon(Recursos.IMG_CAB_ADMIN));
	}
	
	private void initVentana() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setFocusCycleRoot(true);
		this.setLayout(new BorderLayout());
		this.setModal(true);
	}
	
	/**
	 * Establece el título a la ventana y en la cabecera.
	 */
	public abstract void setTituloVentana();
	
	/**
	 * Establece una descripción abajo del título de la cabecera.
	 */
	public abstract void setDescripcionVentana();

	/**
	 * Establece el tamaño y la posición del panel que contiene los botones de búsqueda.
	 * @param pNumeroDeFila Número de fila en que debería ir el panel.
	 */
	public void setPosicionPanelBotonesBusqueda(int pNumeroDeFila) {
		this.pnlBotonesBusqueda.setBounds(ConstantesPosicion.COLUMN_INPUT_POS_INI_X, 
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+pNumeroDeFila*ConstantesSeparacion.INCREMENTO_Y, 
				Double.valueOf(this.pnlBotonesBusqueda.getSize().getWidth()).intValue(), 
				Double.valueOf(this.pnlBotonesBusqueda.getSize().getHeight()).intValue());
	}
	
	/**
	 * Establece el tamaño y la posición de la ventana.
	 * Si no alcanzan los valores por defecto sobrescribir este método.
	 * @param pCantidadFilasComponentes Cantidad de filas de componentes usados en el Admin.
	 */
	public void setTamanioPosicionVentana() {
		this.setSize(new Dimension(ConstantesTamanio.ADMIN_WINDOW_WIDTH, ConstantesTamanio.ADMIN_WINDOW_HEIGHT));
		this.setLocationRelativeTo(null);
	}

	/**
	 * Establece el tamaño del panel de búsqueda.
	 * @param pCantidadFilasComponentes
	 */
	public void setTamanioPanelBusqueda(int pCantidadFilasComponentes) {
		this.pnlBusqueda.setPreferredSize(new Dimension(
				ConstantesTamanio.ADMIN_WINDOW_WIDTH,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y*2+pCantidadFilasComponentes*ConstantesSeparacion.INCREMENTO_Y-ConstantesSeparacion.SEPARADOR_VERTICAL)
		);
	}

	/**
	 * Cambia los componentes correspondientes para indicar que comenzó la búsqueda.
	 */
	public void iniBusqueda() {
		this.getPnlBotonesBusqueda().getBtnBuscar().setEnabled(false);
		this.pnlTabla.setCargando(true);
		this.pnlTabla.repaint();
		this.pnlTabla.getTblDatos().setEnabled(false);
	}
	
	/**
	 * Restablece el estado de los componentes para poder iniciar una nueva búsqueda.
	 */
	public void finBusqueda() {
		this.pnlTabla.setCargando(false);
		this.pnlTabla.setEnabled(true);
		this.getPnlBotonesBusqueda().getBtnBuscar().setEnabled(true);
		this.pnlTabla.getTblDatos().setEnabled(true);
		this.pnlTabla.repaint();
	}

	/**
	 * 
	 * @return Un Panel de Busqueda.
	 */
	public PnlAdminBusqueda getPnlBusqueda() {
		return pnlBusqueda;
	}

	/**
	 * 
	 * @return Un Panel para la Cabecera de la ventana.
	 */
	public PnlAdminCabecera getPnlCabecera() {
		return pnlCabecera;
	}

	/**
	 * 
	 * @return Un Panel para el Cuerpo de la ventana.
	 */
	public PnlAdminCuerpo getPnlCuerpo() {
		return pnlCuerpo;
	}
	
	/**
	 * 
	 * @return Un Panel para el Panel de Búsqueda con los botones que se utilizan para buscar.
	 */
	public PnlAdminBotonesBusqueda getPnlBotonesBusqueda() {
		return pnlBotonesBusqueda;
	}
	
	/**
	 * 
	 * @return Un Panel para el Pie de la ventana.
	 */
	public PnlAdminPie getPnlPie() {
		return pnlPie;
	}
	
	/**
	 * 
	 * @return Un Label para agregar un titulo a la tabla con los resultados de la búsqueda.
	 */
	public JLabel getLblTitulo() {
		return lblTitulo;
	}
	
	/**
	 * 
	 * @return Un ScrollPane para la tabla con los resultados de la búsqueda.
	 */
	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}
	
}
