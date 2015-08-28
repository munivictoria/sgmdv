package com.trascender.caja.gui.cobro;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.caja.gui.cobro.abm.pagoTicketCheque.AgregarPagoTicketCheque;
import com.trascender.caja.gui.cobro.abm.pagoTicketCompensacion.AgregarPagoTicketCompensacion;
import com.trascender.caja.gui.cobro.abm.pagoTicketDeposito.AgregarPagoTicketDeposito;
import com.trascender.caja.gui.cobro.abm.pagoTicketEfectivo.AgregarPagoTicketEfectivo;
import com.trascender.caja.gui.impresion.Imprimible;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.PagoTicket;
import com.trascender.contabilidad.recurso.persistent.PagoTicketCheque;
import com.trascender.contabilidad.recurso.persistent.PagoTicketEfectivo;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

/**
 * @author adrian
 * @author Mariano Lusardi
 */
public class Cobro  {

	String FORMATO_NUMEROS_DECIMALES = "#,###,##0.00";

	//	private CobroView view;
	//	private TicketCaja ticket;
	//	private CobroTableModel tableModel = new CobroTableModel(ticket);
	//	private long idDeuda;

	private final CobroView view;
	private List<TicketDeuda> listaTicket;
	private final CobroTableModel tableModel = new CobroTableModel();
	private final PagoTicketTableModel pagosTableModel = new PagoTicketTableModel();

	public Cobro(JFrame owner) throws Exception {
		this.view = new CobroView(owner);
		this.listaTicket = new ArrayList<TicketDeuda>();
		this.init();
	}

	public Cobro(JDialog owner) throws Exception {

		this.view = new CobroView(owner);
		this.listaTicket = new ArrayList<TicketDeuda>();
		this.init();
	}	
	
	//
	/**
	 * Inicializa el modelo, los eventos y la vista 
	 */
	protected void init() {

		this.view.getTfCodigoLiquidacion().requestFocus();
		this.setModels();
		this.setListeners();
		this.setTextField();
		this.getView().getPnlPie().getBtnAceptar().setEnabled(false);
	}


	/**
	 * coloca los textfield en 0
	 */
	private void setTextField() {
		this.getView().getTfTotalPagado().setText("0.0");
		this.getView().getTfVuelto().setText("0.0");
	}


	/**
	 * Prepara el modelo de las tablas
	 */
	private void setModels() {
		this.getView().setTableModel(this.getTableModel());
		this.getView().getPnlTablaPagos().getPnlTabla().getTblDatos().setModel(this.pagosTableModel);
	}


	/**
	 * Prepara los eventos
	 */
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnCobrarListener(this));
		this.getView().getPnlPie().getBtnCancelar().addActionListener(new BtnCancelarListener(this.getView()));
		this.getView().getTfCodigoLiquidacion().addKeyListener(new TfCodigoLiquidacionListener(this));
		this.getView().getBtnAgregarLiquidacion().addActionListener(new BtnAgregarLiquidacionListener(this) );
		this.getView().getTfTotalPagado().addKeyListener(new TfTotalPagadoKeyListener(this));
		this.getView().getBtnQuitar().addActionListener(new BtnQuitarListener(this));
		this.getView().getPnlBtnTabla().getBtnAgregar().addActionListener(new BtnDetalleListener(this));
		this.getView().getPnlPie().getBtnImprimir().addActionListener(new BtnNuevoCobroListener(this));
		this.getView().getBtnComponerPago().addActionListener(new BtnComponerPagoListener(this));
		this.getView().getPnlTablaPagos().getPnlVerticalBotones().getBtnAgregar().addActionListener(new BtnAgregarPagoTicketCompensacion(this));
		this.getView().getPnlTablaPagos().getPnlVerticalBotones().getBtnModificar().addActionListener(new BtnAgregarPagoTicketEfectivo(this));
		this.getView().getBtnPagoCheque().addActionListener(new BtnAgregarPagoTicketCheque(this));
		this.getView().getBtnPagoDeposito().addActionListener(new BtnAgregarPagoTicketDeposito(this));
		this.getView().getPnlTablaPagos().getPnlVerticalBotones().getBtnEliminar().addActionListener(new BtnQuitarPagoListener(this));
		this.getView().getPnlTablaPagos().getPnlVerticalBotones().getBtnQuitarTodos().addActionListener(new BtnQuitarPagoTodosListener(this));
		//		this.getView().getTfCodigoLiquidacion().addCaretListener(new AgregarTicketAutomaticamenteListener(this));

	}

	/**
	 * Limpia la vista
	 */
	protected void limpiarVista() {
		this.getView().getTfCodigoLiquidacion().setText(null);
		this.getView().getTfMonto().setText(null);
		this.getView().getTfTotalPagado().setText(null);
		this.setTextField();
		this.borrarTabla();
		this.getView().getPnlPie().getBtnAceptar().setEnabled(false);
	}

	private void borrarTabla() {
		this.getView().getTableModel().getListaTicket().clear();
		this.getView().getTableModel().getListaDeuda().clear();
		this.getView().getTableModel().clearTable();
	}

	/**
	 * Abre la ventana y la coloca modal, esperando eventos
	 */
	public void open() {
		this.getView().setVisible(true);
	}


	/**
	 * Elimina la ventana y limpia los recursos asociados
	 */
	public void close() {
		this.getView().dispose();
	}

	/**
	 * Getter del table model
	 * @return CobroTableModel 
	 */
	public CobroTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Obtiene la vista
	 * @return vista del cobro
	 */
	public CobroView getView() {
		return view;
	}

	/**
	 * Actualiza el valor de la deuda para acutalziar a los textfield del vuelto
	 * @throws Exception
	 */
	public void actualizarDeuda() throws Exception{

		double importeTotal = 0;

		for (TicketDeuda ticketDeuda : this.listaTicket) {
			importeTotal += ticketDeuda.getTicket().getImporteTotal();
		}

		Double totalPagado;

		if (Validador.isValidFloat(this.getView().getTfTotalPagado().getText()) 
				&& !this.getView().getTfTotalPagado().getText().equals("")) {
			totalPagado= Conversor.getDouble((this.getView().getTfTotalPagado().getText()));
		}
		else {
			totalPagado= 0.0;
		}

		double totalADevolver = totalPagado - importeTotal;
		String texto;
		NumberFormat locFormato = NumberFormat.getCurrencyInstance();

		if (totalADevolver<0){
			texto = locFormato.format(0);
		}
		else{
			texto = locFormato.format(totalADevolver);
		}

		this.getView().getTfVuelto().setText(texto);
	}
	
	public static void main(String... args) {
		try {
			System.out.println("Prueba");
			
			//Armamos pagos, un cheque de 100 y 10 de efectivo.
			PagoTicketCheque pagoCheque = new PagoTicketCheque();
			pagoCheque.setMonto(40d);
			pagoCheque.setNumero("10000");
			
			//Armamos pagos, un cheque de 100 y 10 de efectivo.
			PagoTicketCheque pagoCheque2 = new PagoTicketCheque();
			pagoCheque2.setMonto(60d);
			pagoCheque2.setNumero("1000022");
			
			PagoTicketEfectivo pagoEfectivo = new PagoTicketEfectivo();
			pagoEfectivo.setMonto(10d);
			pagoEfectivo.setComentario("Efectivo");
			
			List<PagoTicket> listaPagos = new ArrayList<PagoTicket>();
			listaPagos.add(pagoCheque);
			listaPagos.add(pagoCheque2);
			listaPagos.add(pagoEfectivo);
			
			//Armamos dos tickets, uno de 50 y uno de 60.
			TicketCaja ticket1 = new TicketCaja();
			ticket1.setImporteTotal(50d);
			
			TicketCaja ticket2 = new TicketCaja();
			ticket2.setImporteTotal(60d);
			
			List<TicketCaja> listaTickets = new ArrayList<TicketCaja>();
			listaTickets.add(ticket1);
			listaTickets.add(ticket2);
			
			ListIterator<PagoTicket> itPagos = listaPagos.listIterator();
			PagoTicket pagoActual = itPagos.next();
			Double importePago = pagoActual.getMonto();
			for (TicketCaja cadaTicket : listaTickets) {
				boolean pagado = false;
				Double importeRestanteDeTicket = cadaTicket.getImporteTotal();
				while (!pagado) {
					PagoTicket nuevoPago = pagoActual.getClon(cadaTicket);
					cadaTicket.addPagoTicket(nuevoPago);
					//Alcanza para pagar con el pago actual?
					if (importeRestanteDeTicket <= importePago) {
						//Alcanza
						pagado = true;
						//Resto del pago
						importePago = importePago - importeRestanteDeTicket;
						nuevoPago.setMonto(importeRestanteDeTicket);
					} else {
						//No alcanza.
						nuevoPago.setMonto(importePago);
						importeRestanteDeTicket = importeRestanteDeTicket - importePago;
						//Si no alcanza, no nos queda importe en este pago
						importePago = 0D;
					}
					if (importePago <= 0D && itPagos.hasNext()) {
						pagoActual = itPagos.next();
						importePago = pagoActual.getMonto();
					}
				}
			}
			
			for (TicketCaja cadaTicket : listaTickets) {
				for (PagoTicket cadaPago : cadaTicket.getListaPagosTicket()) {
					System.out.println("Ticket de "+ cadaTicket.getImporteTotal()+", "
							+cadaPago.getDescripcion()+": "+cadaPago.getMonto());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void armarPago() throws Exception{

		List<PagoTicket> locListaPagosTicket = this.pagosTableModel.getListaObjetos();
		//Limpiamos los pagos de los tickets.
		for (TicketDeuda cadaTicket : this.listaTicket){
			cadaTicket.getTicket().getListaPagosTicket().clear();
		}

		//Si no hay pagos armados, el caso mas común, se arma un pago en efectivo para cada cobro.
		if (locListaPagosTicket.isEmpty()){
			for (TicketDeuda ticketDeuda : listaTicket) {
				PagoTicketEfectivo locPagoEfectivo = new PagoTicketEfectivo();
				locPagoEfectivo.setMonto(ticketDeuda.getTicket().getImporteTotal());
				ticketDeuda.getTicket().addPagoTicket(locPagoEfectivo);
			}
		} else if (listaTicket.size() == 1) {	//Si hay un solo ticket, se asocia el/los pagos a ese ticket
			TicketCaja locTicket = listaTicket.get(0).getTicket();
			for (PagoTicket cadaPago : locListaPagosTicket){
				locTicket.addPagoTicket(cadaPago);
			}
		} 
		else if (listaTicket.size() > 1 && locListaPagosTicket.size() == 1){
			//			//Si hay mas de un ticket y una forma de pago, usamos esa forma de pago para todos los tickets
			// validar q la suma del monto de los tickets coincidan con lo ingresado como pago
			PagoTicket locPago = locListaPagosTicket.get(0);

			Double sumaMontosTicket = new Double(0);
			for (TicketDeuda cadaTicketDeuda : listaTicket){
				sumaMontosTicket += cadaTicketDeuda.getTicket().getImporteTotal();
			}

			DecimalFormat decimalFormat = new DecimalFormat(FORMATO_NUMEROS_DECIMALES);
			if (!decimalFormat.format(sumaMontosTicket).equals(decimalFormat.format(locPago.getMonto()))){
				throw new Exception("Los montos del los Tickets no coninciden con el monto del Pago.");
			}

			for (TicketDeuda cadaTicketDeuda : listaTicket) {
				TicketCaja cadaTicket = cadaTicketDeuda.getTicket();
				PagoTicket nuevoPago = locPago.getClon(cadaTicket);
				cadaTicket.addPagoTicket(nuevoPago);
			}
		} else if (locListaPagosTicket.size() > 1) {
//			throw new Exception("No componerse mas de un Pago para mas de un Ticket.");
			ListIterator<PagoTicket> itPagos = locListaPagosTicket.listIterator();
			PagoTicket pagoActual = itPagos.next();
			Double importePago = pagoActual.getMonto();
			for (TicketDeuda cadaTicketDeuda : listaTicket) {
				TicketCaja cadaTicket = cadaTicketDeuda.getTicket();
				boolean pagado = false;
				Double importeRestanteDeTicket = cadaTicket.getImporteTotal();
				while (!pagado) {
					PagoTicket nuevoPago = pagoActual.getClon(cadaTicket);
					cadaTicket.addPagoTicket(nuevoPago);
					//Alcanza para pagar con el pago actual?
					if (importeRestanteDeTicket <= importePago) {
						//Alcanza
						pagado = true;
						//Resto del pago
						importePago = importePago - importeRestanteDeTicket;
						nuevoPago.setMonto(importeRestanteDeTicket);
					} else {
						//No alcanza.
						nuevoPago.setMonto(importePago);
						importeRestanteDeTicket = importeRestanteDeTicket - importePago;
						//Si no alcanza, no nos queda importe en este pago
						importePago = 0D;
					}
					if (importePago <= 0D && itPagos.hasNext()) {
						pagoActual = itPagos.next();
						importePago = pagoActual.getMonto();
					}
				}
			}
		}
		for (TicketDeuda cadaTicketDeuda : listaTicket){
			DecimalFormat decimalFormat = new DecimalFormat(FORMATO_NUMEROS_DECIMALES);
			if (!decimalFormat.format(cadaTicketDeuda.getTicket().getMontoPagosTicket())
					.equals(decimalFormat.format(cadaTicketDeuda.getTicket().getImporteTotal()))){
				throw new Exception("Los montos del los Tickets no coninciden con los Pagos armados.");
			}
		}
	}

	/**
	 * Adhiere un TicketCaja
	 * @throws Exception
	 */
	protected void cobrar() throws Exception {
		this.getView().getPnlPie().getBtnImprimir().setEnabled(false);
		boolean cobroConfirmado = CajaGUI.getInstance().showConfirmMsg(this.getView(), "¿Desea confirmar el cobro?");
		if (cobroConfirmado) {
			//			CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().validarListaTicketCaja(locTicketCajas);
			boolean mostrarAvisoPasePorMostrador = false;
			try{
				for (TicketDeuda ticketDauda : listaTicket) {
					this.armarPago();
					TicketCaja locTicketGenerado = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().addTicketCaja(ticketDauda.getTicket());
					ticketDauda.getTicket().setNumero(locTicketGenerado.getNumero());
					ticketDauda.getTicket().setFecha(locTicketGenerado.getFecha());
					ticketDauda.getTicket().setIdTicketCaja(locTicketGenerado.getIdTicketCaja());
					if (ticketDauda.getPersona().getIdPersona() == 0){
						mostrarAvisoPasePorMostrador = true;
					}

					System.out.println("EL TICKET ES..." + ticketDauda.getTicket().getNumero() + " - " + ticketDauda.getTicket().getDetalles());
				}
			} catch (Exception e){
				e.printStackTrace();
				CajaGUI.getInstance().showErrorMsg(view, e.getMessage());
				return;
			}
			imprimirTickets();
			this.listaTicket.clear();
			this.pagosTableModel.clearTable();
			this.getView().getPnlPie().getBtnImprimir().setEnabled(true);
			this.getView().getTfTotalPagado().setEnabled(true);
			this.getView().getTfTotalPagado().setEditable(true);
			this.borrarTabla();
			this.disabledAll(true);
			this.getView().getTfCodigoLiquidacion().setText("");
			this.getView().getTfTotalPagado().setText("0.0");
			this.getView().getTfVuelto().setText("0.0");
			if (mostrarAvisoPasePorMostrador) {
				CajaGUI.getInstance().showInformationMsg(view, "Pase por mostrador para actualizar los datos del titular");
			}
			this.view.getTfCodigoLiquidacion().requestFocus();
		}
	}  

	private void disabledAll(boolean pEstado) {
		this.view.getTfCodigoLiquidacion().setEnabled(pEstado);
		this.view.getBtnAgregarLiquidacion().setEnabled(pEstado);
		this.view.getBtnQuitar().setEnabled(pEstado);
		this.view.getPnlPie().getBtnAceptar().setEnabled(pEstado);
	}

	/**
	 * Imprime todos los tickets del cobro
	 * @throws TrascenderException 
	 * @throws RemoteException 
	 * @throws Exception
	 */
	private void imprimirTickets(){
		try{
			for (TicketDeuda ticketDauda : this.listaTicket) {
				Imprimible locImprimible = Imprimible.getInstance(ticketDauda.getTicket(), ticketDauda.getPersona());
				if (locImprimible.isValido()){
					locImprimible.imprimir();
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), "Ha ocurrido un error al intentar imprimir. Comuníquese con el administrador.");
		}
	}

	private void validar() {
		if(this.getTableModel().getRowCount() == 0){
			this.view.getPnlPie().getBtnAceptar().setEnabled(false);
			this.getView().getTfVuelto().setText("0.0");
		}
	}

	/**
	 * Agrega a la tabla una deuda y setea los datos en el ticket
	 * @throws Exception
	 */
	protected void agregarLiquidacion() throws Exception {
		try{

			/**
			 * - Si o si: No vacio y empezar con con  los digitos 1, 2 o 3.
			 * - Una de dos: Tener 19 digitos y ser todo numerico 
			 * 		o
			 *   Tener 25 digitos.   
			 */

			String codigoIngresado = this.getView().getTfCodigoLiquidacion().getText();
			if (codigoIngresado.equals("")){
				return;
			}

			if (!codigoIngresado.startsWith("1")
					&& !codigoIngresado.startsWith("2")
					&& !codigoIngresado.startsWith("3")){
				return;
			}

			if (codigoIngresado.length() != 25 
					&& codigoIngresado.length() != 19){
				return; 
			}

			if (codigoIngresado.length() == 19
					&& !Validador.isValidInteger(codigoIngresado)){
				return;
			}
			TicketDeuda locTicketDeuda = new TicketDeuda();
			locTicketDeuda.setIdDeuda(codigoIngresado);

			if (this.getTableModel().getListaTicket().contains(locTicketDeuda)){
				return;
			}
			List<DetalleTicketCaja> locListaDetalles = 
					CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos()
					.getListaDetalleByIdLiquidacion(codigoIngresado);

			Deuda locDeuda = new Deuda();
			double montoTotal = 0;

			for (DetalleTicketCaja cadaDetalle : locListaDetalles){
				montoTotal += cadaDetalle.getImporte();
				locDeuda.getListaPagables().add(cadaDetalle.getDeuda());
				locDeuda.setPersona(cadaDetalle.getDeuda().getPersona());
				locTicketDeuda.setPersona(cadaDetalle.getDeuda().getPersona());//Seteamos la persona para usar mas tarde.
				locDeuda.setNombre(cadaDetalle.getDeuda().getNombre());
			}
			locDeuda.setMonto(montoTotal);

			for (int i = 0 ; i < locListaDetalles.size() ; i++) {
				DetalleTicketCaja locDetalle = locListaDetalles.get(i);
				locDetalle.setNumeroLinea(1);
				locDetalle.setTicketCaja(locTicketDeuda.getTicket());
				locTicketDeuda.getTicket().getDetalles().add(locDetalle); //aca se pudre todo //en tu gestión, capaz
			}

			locTicketDeuda.getTicket().setImporteTotal(montoTotal);
			locTicketDeuda.getTicket().setCaja(CajaGUI.getInstance().getCaja());
			locTicketDeuda.getTicket().setCodigoBarras(codigoIngresado);

			this.getTableModel().addRow(locDeuda,locTicketDeuda); 
			//						this.listaTicket.add(locTicketDauda);
			this.listaTicket = this.getTableModel().getListaTicket();

			Double monto = 0d;
			for (TicketDeuda unTicketDeuda : this.listaTicket) {
				monto += unTicketDeuda.getTicket().getImporteTotal();
			}

			this.getView().getTfMonto().setText(new DecimalFormat(FORMATO_NUMEROS_DECIMALES).format(monto));

			System.out.println("CANTIDAD DE TICKET : " + this.listaTicket.size());
			System.out.println("TOTAL COBRO : " + monto);

			this.getView().getPnlPie().getBtnAceptar().setEnabled(true);
			this.actualizarDeuda();
		} catch(TrascenderException e){
			throw e;
		}
		catch(NullPointerException e){
			//FIXME PONÉ ACÁ ALGO COMO LA GENTE!!!!
			throw new Exception("El código ingresado no posee un formato válido. Verifique los datos ingresados.");
		} finally {
			this.view.getTfCodigoLiquidacion().setText("");
		}
	}

	void eliminarLineaCobro() throws Exception {
		int fila = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
		Deuda locDeuda = this.getTableModel().getListaObjetos().get(fila);

		this.getTableModel().deleteRow(locDeuda);

		this.listaTicket = this.getTableModel().getListaTicket();


		Double monto = 0d;
		for (TicketDeuda unTicketDeuda : this.listaTicket) {
			monto += unTicketDeuda.getTicket().getImporteTotal();
		}

		this.getView().getTfMonto().setText(new DecimalFormat(FORMATO_NUMEROS_DECIMALES).format(monto));

		this.validar();
		this.actualizarDeuda();

		int i = -1;
		Double total = 0d;
		for (TicketDeuda locd : this.listaTicket) {
			i++;
			System.out.println("ticket " +  i + " : " + locd.getTicket().getImporteTotal());
			total += locd.getTicket().getImporteTotal();
		}
		System.out.println("CANTIDAD DE TICKET : " + this.listaTicket.size());
		System.out.println("TOTAL COBRO : " + total);

	}

	void quitarPagoTicket() throws Exception {
		int fila = this.getView().getPnlTablaPagos().getPnlTabla().getTblDatos().getSelectedRow();
		PagoTicket locPagoTicket = this.pagosTableModel.getListaObjetos().get(fila);

		this.pagosTableModel.deleteRow(locPagoTicket);
	}

	void quitarTodosPagoTicket() throws Exception {
		this.pagosTableModel.clearTable();
	}

	// Prueba

	void detalleLineaCobro() throws Exception {
		//		int fila = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
		//		Deuda locDeuda = (Deuda)this.getTableModel().getListaObjetos().get(fila);
		//		
		//		
		//		if (fila != -1 ) {
		//			if(locDeuda.getPagable() instanceof LiquidacionTasa){
		//				LiquidacionTasa lt= (LiquidacionTasa)locDeuda.getPagable();
		//				DetalleCobroRegistroDeuda detalleCobroRD = new DetalleCobroRegistroDeuda(this.getView());
		//				detalleCobroRD.getAbmModel().setObjetoABM(lt);
		//				detalleCobroRD.actualizarView();
		//				detalleCobroRD.open();
		//				
		//				}
		//			else if(locDeuda.getPagable() instanceof CuotaRefinanciacion){
		//				CuotaRefinanciacion cr = (CuotaRefinanciacion)locDeuda.getPagable();
		//				DetalleCobroRegistroDeuda detalleCobroRD = new DetalleCobroRegistroDeuda(this.getView());
		//				detalleCobroRD.getAbmModel().setObjetoABM(cr);
		//				detalleCobroRD.actualizarView();
		//				detalleCobroRD.open();
		//			}
		//			else if(locDeuda.getPagable() instanceof SelladoAdministrativo){
		//				SelladoAdministrativo sa = (SelladoAdministrativo)locDeuda.getPagable();
		//				DetalleCobroSelladoAdministrativo detalleCobroSA = new DetalleCobroSelladoAdministrativo(this.getView());
		//				detalleCobroSA.getAbmModel().setObjetoABM(sa);
		//				detalleCobroSA.actualizarView();
		//				detalleCobroSA.open();
		//			}
		//		}
	}

	public void nuevoCobro() {
		this.listaTicket.clear();
		this.limpiarVista();
		this.disabledAll(true);
		this.getView().getPnlPie().getBtnAceptar().setEnabled(false);
	}

	public void componerPago(){
		boolean flag = !this.getView().getPnlTablaPagos().isVisible();
		this.getView().getPnlTablaPagos().setVisible(flag);
		Dimension dimension = this.getView().getSize();
		if (flag) {
			this.getView().setSize(dimension.width + 400 , dimension.height);
		} else {
			this.getView().setSize(dimension.width - 400, dimension.height);
		}
	}

	public void agregarPagoTicketCompensacion() throws Exception{
		AgregarPagoTicketCompensacion controller = new AgregarPagoTicketCompensacion(this.getView());
		controller.open();
		if (controller.isOperacionRealizada()){
			this.pagosTableModel.addRow(controller.getABMModel().getObjetoABM());
		}
	}

	public void agregarPagoTicketEfectivo() throws Exception{
		AgregarPagoTicketEfectivo controller = new AgregarPagoTicketEfectivo(this.getView());
		controller.open();
		if (controller.isOperacionRealizada()){
			this.pagosTableModel.addRow(controller.getABMModel().getObjetoABM());
		}
	}

	public void agregarPagoTicketCheque() throws Exception{
		AgregarPagoTicketCheque controller = new AgregarPagoTicketCheque(this.getView());
		controller.open();
		if (controller.isOperacionRealizada()){
			this.pagosTableModel.addRow(controller.getABMModel().getObjetoABM());
		}
	}

	public void agregarPagoTicketDeposito() throws Exception{
		AgregarPagoTicketDeposito controller = new AgregarPagoTicketDeposito(this.getView());
		controller.open();
		if (controller.isOperacionRealizada()){
			this.pagosTableModel.addRow(controller.getABMModel().getObjetoABM());
		}
	}


	//	public void controlarCaracteresCodigo() throws Exception {
	//		if(!(this.getView().getTfCodigoLiquidacion().getText().equals("")) && ((this.getView().getTfCodigoLiquidacion().getText().substring(0, 1).equals("1"))||(this.getView().getTfCodigoLiquidacion().getText().substring(0, 1).equals("2")))) { //No se porq con la negacion no me anda... por eso esta en el else
	//		} else { 
	//			SwingUtilities.invokeLater(new Runnable() {
	//		        public void run() {
	//		             view.getTfCodigoLiquidacion().setText("");
	//		        }
	//		    });
	//		}
	//		
	//		if(this.getView().getTfCodigoLiquidacion().getText().length() == 19) {
	//			if(Validador.isValidInteger(this.getView().getTfCodigoLiquidacion().getText())){ 
	//				boolean locEncontrado = false;
	//				for (TicketDeuda unTicket : this.listaTicket) {
	//					Long locUno = Conversor.getLong(this.getView().getTfCodigoLiquidacion().getText());
	//					Long locDos = (Long)unTicket.getIdDeuda();
	//					System.out.println("Codigo lista : " + locUno + " - Codigo del Textfield : " + locDos);
	//					if(locUno.equals(locDos)) {
	//						locEncontrado = true;
	//					}
	//				}
	//				
	//				if(locEncontrado == false){
	//					this.agregarLiquidacion();
	//					SwingUtilities.invokeLater(new Runnable() {
	//				        public void run() {
	//				             view.getTfCodigoLiquidacion().setText("");
	//				        }
	//				    });
	//				}
	//			} else {
	//				AppManager.getInstance().showErrorMsg(this.getView(), "El código debe ser numérico.");
	//			}
	//		}
	//	}

	//	public void controlarCaracteresCodigo() throws Exception {
	//		if(this.getView().getTfCodigoLiquidacion().getText().length() == 19) {
	//			if(Validador.isValidInteger(this.getView().getTfCodigoLiquidacion().getText())){ 
	//				boolean locEncontrado = false;
	//				for (TicketDeuda unTicket : this.listaTicket) {
	//					Long locUno = Conversor.getLong(this.getView().getTfCodigoLiquidacion().getText());
	//					Long locDos = (Long)unTicket.getIdDeuda();
	//					System.out.println("Codigo lista : " + locUno + " - Codigo del Textfield : " + locDos);
	//					if(locUno.equals(locDos)) {
	//						locEncontrado = true;
	//					}
	//				}
	//				
	//				if(locEncontrado == false){
	//					this.agregarLiquidacion();
	//				}
	//			} else {
	//				AppManager.getInstance().showErrorMsg(this.getView(), "El código debe ser numérico.");
	//			}
	//		} else {
	//			if(this.getView().getTfCodigoLiquidacion().getText().length()>19){
	////				String locTexto = this.getView().getTfCodigoLiquidacion().getText();
	////				String locTexto2 = locTexto.substring(0, 18);
	////				this.getView().getTfCodigoLiquidacion().setText(locTexto2);
	//				SwingUtilities.invokeLater(new Runnable() {
	//			        public void run() {
	//			             view.getTfCodigoLiquidacion().setText("");
	//			        }
	//			    });
	//			}
	//		}
	//	}
}

/**
 * Clase para agrupar el ticketCaja con el id de la deuda (codigo de barras)
 */
class TicketDeuda { 

	private TicketCaja ticket;
	private String idDeuda;
	private Persona persona;

	public TicketDeuda(TicketCaja pTicket, String pIdDeuda) {
		this.ticket = pTicket;
		this.idDeuda = pIdDeuda;
	}

	public TicketDeuda() {
		this.ticket = new TicketCaja();
	}

	public TicketCaja getTicket() {
		return ticket;
	}

	public void setTicket(TicketCaja ticket) {
		this.ticket = ticket;
	}

	public String getIdDeuda() {
		return idDeuda;
	}

	public void setIdDeuda(String idDeuda) {
		this.idDeuda = idDeuda;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDeuda == null) ? 0 : idDeuda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketDeuda other = (TicketDeuda) obj;
		if (idDeuda == null) {
			if (other.idDeuda != null)
				return false;
		} else if (!idDeuda.equals(other.idDeuda))
			return false;
		return true;
	}

}

class BtnQuitarListener implements ActionListener {

	private final Cobro cobro;

	public BtnQuitarListener(Cobro pCobro) {
		this.cobro = pCobro;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.cobro.eliminarLineaCobro();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

class BtnQuitarPagoListener implements ActionListener {

	private final Cobro cobro;

	public BtnQuitarPagoListener(Cobro pCobro) {
		this.cobro = pCobro;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.cobro.quitarPagoTicket();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

class BtnQuitarPagoTodosListener implements ActionListener {

	private final Cobro cobro;

	public BtnQuitarPagoTodosListener(Cobro pCobro) {
		this.cobro = pCobro;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.cobro.quitarTodosPagoTicket();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

class BtnNuevoCobroListener implements ActionListener {

	private final Cobro cobro;

	public BtnNuevoCobroListener(Cobro pCobro) {
		this.cobro = pCobro;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.cobro.nuevoCobro();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}


/**
 * Cancela la acción de cobrar
 * @author adrian
 *
 */
class BtnCancelarListener implements ActionListener {

	private final ABMView view;

	public BtnCancelarListener(ABMView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		this.view.dispose();
	}
}

/**
 * Acción del botón cobrar
 * @author adrian
 */
class BtnCobrarListener implements ActionListener {

	private final Cobro controller;

	public BtnCobrarListener(Cobro controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.cobrar();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

//class AgregarTicketAutomaticamenteListener implements CaretListener {
//	
//	private Cobro controller;
//	
//	AgregarTicketAutomaticamenteListener(Cobro pController){
//		controller = pController;
//	}
//
//	public void caretUpdate(CaretEvent e) {
//		try {
//			controller.controlarCaracteresCodigo();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
//			System.out.println("Ocurrió un error al agregar el ticket automaticamente.");
//		}
//	}
//} 

/**
 * Cuando presiona enter el usuario sobre el TextField de liquidación, 
 * llama a agregarLiquidacion().
 * @author adrian
 *
 */
class TfCodigoLiquidacionListener extends KeyAdapter {

	private final Cobro controller;

	public TfCodigoLiquidacionListener (Cobro controller){
		this.controller = controller;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				this.controller.agregarLiquidacion();
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
			}
		}
	}
}

/**
 * Muestra el panel para componer un pago.
 * @author ferna
 *
 */
class BtnComponerPagoListener implements ActionListener {

	private final Cobro controller;

	public BtnComponerPagoListener(Cobro controller){
		this.controller = controller;
	}

	public void actionPerformed (ActionEvent e){
		try {
			this.controller.componerPago();
		}catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		} 
	}
}

class BtnAgregarPagoTicketCompensacion implements ActionListener {

	private final Cobro controller;

	public BtnAgregarPagoTicketCompensacion(Cobro controller){
		this.controller = controller;
	}

	public void actionPerformed (ActionEvent e){
		try {
			this.controller.agregarPagoTicketCompensacion();
		}catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		} 
	}
}

class BtnAgregarPagoTicketEfectivo implements ActionListener {

	private final Cobro controller;

	public BtnAgregarPagoTicketEfectivo(Cobro controller){
		this.controller = controller;
	}

	public void actionPerformed (ActionEvent e){
		try {
			this.controller.agregarPagoTicketEfectivo();
		}catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		} 
	}
}

class BtnAgregarPagoTicketCheque implements ActionListener {

	private final Cobro controller;

	public BtnAgregarPagoTicketCheque(Cobro controller){
		this.controller = controller;
	}

	public void actionPerformed (ActionEvent e){
		try {
			this.controller.agregarPagoTicketCheque();
		}catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		} 
	}
}

class BtnAgregarPagoTicketDeposito implements ActionListener {

	private final Cobro controller;

	public BtnAgregarPagoTicketDeposito(Cobro controller){
		this.controller = controller;
	}

	public void actionPerformed (ActionEvent e){
		try {
			this.controller.agregarPagoTicketDeposito();
		}catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		} 
	}
}

/**
 * Evento que llama a agregarLiquidación, 
 * cuando se presiona el botón de agregar liq
 * @author Mariano Lusardi
 */
class BtnAgregarLiquidacionListener implements ActionListener {

	private final Cobro controller;

	public BtnAgregarLiquidacionListener(Cobro controller){
		this.controller = controller;
	}

	public void actionPerformed (ActionEvent e){
		try {
			this.controller.agregarLiquidacion();
		}catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		} 
	}
}

/**
 * 
			if(this.getView().getTfCodigoLiquidacion().getText().length()>19){
				SwingUtilities.invokeLater(new Runnable() {
			        public void run() {
			        	String locTexto = getView().getTfCodigoLiquidacion().getText();
						String locTexto2 = locTexto.substring(0, 19);
						getView().getTfCodigoLiquidacion().setText(locTexto2);
			        }
			    });
			}
 *  
 * @author marina y maru
 *
 */
class TfTotalPagadoKeyListener extends KeyAdapter {

	private final Cobro cobro;

	public TfTotalPagadoKeyListener(Cobro pCobro) {
		this.cobro = pCobro;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		try {
			if ((e.getKeyCode() == KeyEvent.VK_PERIOD) ||
					(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) ||
					(e.getKeyCode() == KeyEvent.VK_ENTER) ||
					(e.getKeyCode() == 127) ||
					(e.getKeyCode() >= 96 && e.getKeyCode() <=105) || 
					(e.getKeyCode() == 110) ||
					(e.getKeyCode() >= 48 && e.getKeyCode() <= 57)) {
				this.cobro.actualizarDeuda();
			}
			else {
				this.cobro.getView().getTfTotalPagado().setText("");
				this.cobro.actualizarDeuda();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}	
}

class BtnDetalleListener implements ActionListener {

	private final Cobro cobro;

	public BtnDetalleListener(Cobro pCobro) {
		this.cobro = pCobro;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.cobro.detalleLineaCobro();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

