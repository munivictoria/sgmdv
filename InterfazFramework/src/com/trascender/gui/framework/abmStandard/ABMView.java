package com.trascender.gui.framework.abmStandard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public abstract class ABMView extends JDialog {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	//-- Seccion: CABECERA
	private PnlABMCabecera pnlCabecera;
	//-- Seccion: CUERPO
	private PnlABMCuerpo pnlCuerpo;
	//-- Seccion: PIE
	private PnlABMPie pnlPie;
	
	public ABMView() {
		super();
		this.init();
	}
	
	public ABMView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	public ABMView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}
	
	private void init() {
		this.initVentana();
		
		this.pnlCabecera = new PnlABMCabecera();
		this.getContentPane().add(pnlCabecera, BorderLayout.NORTH);
		
		this.pnlCuerpo = new PnlABMCuerpo();
		this.getContentPane().add(pnlCuerpo, BorderLayout.CENTER);
		
		this.pnlPie = new PnlABMPie();
		this.getContentPane().add(pnlPie, BorderLayout.SOUTH);
	}
	
	/**
	 * Inicializa los atributos comunes a todas las ventanas.
	 *
	 */
	private void initVentana() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setFocusCycleRoot(true);
		this.setLayout(new BorderLayout());
		this.setModal(true);
	}
	
	/**
	 * Establece el título a la ventana y en la cabecera.
	 * 
	 */
	public abstract void setTituloVentana();
	
	/**
	 * Establece una descripción abajo del título de la cabecera.
	 *
	 */
	public abstract void setDescripcionVentana();
	
	/**
	 * Establece el color de fondo de la cabecera y el pie.
	 *
	 */
	public abstract void setColorFondo();
	
	/**
	 * Establece la propiedad <code>text</code> del botón aceptar.
	 *
	 */
	public abstract void setTextoBtnAceptar();
	
	/**
	 * Establece el tamaño y la posición de la ventana.
	 * @param pCantidadFilasComponentes Cantidad de filas de componentes usados en el ABM.
	 */
	public abstract void setTamanioPosicionVentana(int pCantidadFilasComponentes);
	
	
	/**
	 * 
	 * @return Un Panel para la Cabecera de un ABM.
	 */
	public PnlABMCabecera getPnlCabecera() {
		return pnlCabecera;
	}
	
	/**
	 * 
	 * @return Un Panel para el Cuerpo de un ABM.
	 */
	public PnlABMCuerpo getPnlCuerpo() {
		return pnlCuerpo;
	}
	
	/**
	 * 
	 * @return Un Panel para el pie de un ABM
	 */
	public PnlABMPie getPnlPie() {
		return pnlPie;
	}
	
	public void mostrarVistaPreviaReporte(JasperPrint pJasperPrint, String pTituloVentana){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		JDialog locDialog = new JDialog(this);
		JRViewer locViewer = new JRViewer(pJasperPrint);
		locDialog.add(locViewer);
		locDialog.pack();
		locDialog.setModal(true);
		locDialog.setSize(dimension.width, dimension.height);
		locDialog.setTitle(pTituloVentana);
		locDialog.setVisible(true);
	}
	
}
