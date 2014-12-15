package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import com.trascender.contabilidad.gui.abmDigestoMunicipal.AdminDigestoMunicipal;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.AdminPlanDeCuenta;
import com.trascender.contabilidad.gui.abmPresupuesto.PresupuestoABMModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Estado;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMPresupuesto extends ABMController<Presupuesto> {

	@Override
	public abstract PresupuestoABMModel getAbmModel();
	@Override
	public abstract ABMPresupuestoView getView();
	public abstract LineaPresupuestoTableModel getTableModel();

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListener();
		this.setCommonProperties();

		this.getView().getPnlBtnTabla().getBtnEliminar().setVisible(false);
		this.getView().getPnlBtnTabla().getBtnQuitarTodos().setVisible(false);
	}

	private void setCommonProperties() {
		this.getView().getPnlBtnTabla().getBtnModificar().setVisible(false);
	}

	private void setModels(){
		this.getView().setAbmModel(this.getAbmModel());

		this.getView().getCbTipo().setModel(new TDefaultComboBoxModel(Tipo.values()));
		this.getView().getCbEstado().setModel(new TDefaultComboBoxModel(Estado.values())); 
	}

	private void setListener() {
		this.getView().getPnlBotonesSeleccionDigesto().getBtnSeleccionar().addActionListener(new BtnSeleccionarDigestoListener(this));
		this.getView().getPnlBotonesSeleccionDigesto().getBtnLimpiar().addActionListener(new BtnLimpiarDigestoListener(this));

		this.getView().getPnlBtnTabla().getBtnAgregar().addActionListener(new BtnAgregarCuentaListener(this));
		this.getView().getPnlBtnTabla().getBtnEliminar().addActionListener(new BtnQuitarListener(this));
		this.getView().getPnlBtnTabla().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosListener(this));
	}

	@Override
	public void actualizarABMModel() {
		Object locTipo = this.getView().getCbTipo().getSelectedItem();
		if (locTipo != null) this.getAbmModel().setTipo((Presupuesto.Tipo)locTipo);
		else this.getAbmModel().setTipo(null);

		this.getAbmModel().setNombre(this.getView().getTfNombre().getText());

		Object locEstado = this.getView().getCbEstado().getSelectedItem();
		if (locEstado != null) this.getAbmModel().setEstado((Presupuesto.Estado)locEstado);
		else this.getAbmModel().setEstado(null);

		Integer locAnio = Conversor.getInteger(this.getView().getTfAnioPeriodo().getText());
		this.getAbmModel().setAnio(locAnio);

		if (this.getTableModel() != null) {
			List<LineaPresupuesto> locLista = this.getTableModel().getListaObjetos();
			this.getAbmModel().getLineaPresupuesto().clear();
			this.getAbmModel().getLineaPresupuesto().addAll(locLista);
		}

		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getCbTipo().setSelectedItem(this.getAbmModel().getTipo());
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getCbEstado().setSelectedItem(this.getAbmModel().getEstado());
		this.getView().getTfDigesto().setText(Conversor.getVacioSiNull(this.getAbmModel().getDigestoMunicipal()));

		Integer locAnio = this.getAbmModel().getAnio();
		String anio = "";
		if (locAnio != null) {
			anio = String.valueOf(locAnio);
		}
		this.getView().getTfAnioPeriodo().setText(anio);

		if (this.getAbmModel().getLineaPresupuesto() != null && this.getTableModel() != null) {
			List<LineaPresupuesto> locList = new ArrayList<LineaPresupuesto>(this.getAbmModel().getLineaPresupuesto());
			this.ordenarListaLineasPresupuesto(locList);
			//			
			this.getTableModel().clearTable();
			this.getTableModel().addRows(locList);

			try {
				this.setTableCellEditor();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
	}



	protected void setTableCellEditor() throws Exception {
		//		if (tipoPresupuesto.equals(Tipo.GASTOS)) {
		//			this.tableModel = new LineaPresupuestoGastosABMTableModel();
		//		}
		//		else if (tipoPresupuesto.equals(Tipo.RECURSOS)) {
		//			this.tableModel = new LineaPresupuestoRecursoABMTableModel();
		//		}

		if (this.getTableModel() != null) {

			//this.getView().getPnlTabla().getTblDatos().setModel(this.getTableModel());

			this.getView().getPnlTabla().getTblDatos().setRowHeight(22);

			JTextField tfValor = new JTextField();
			//TableCellEditor ceValor = new LineaPresupuestoCellEditor(tfValor);
			LineaPresupuestoCellEditor ceValor = new LineaPresupuestoCellEditor(tfValor);
			ceValor.addCellEditorListener(new LineaPresupuestoCellEditorListener(this));

			this.getView().setCellEditor(ceValor);


			//			TableColumnModel cmValor = this.getView().getPnlTabla().getTblDatos().getColumnModel();
			//			TableColumn tcValor = cmValor.getColumn(LineaPresupuestoTableModel.COLUMNA_VALOR_MODIFICABLE);
			//			tcValor.setCellEditor(ceValor);
		}
	}

	private void ordenarListaLineasPresupuesto(List<LineaPresupuesto> lista) {
		Collections.sort(lista, new Comparator<LineaPresupuesto>(){
			@Override
			public int compare(LineaPresupuesto o1, LineaPresupuesto o2) {
				return o1.getCuenta().toString().compareTo(o2.getCuenta().toString());
			}
		});
	}

	private void ordenarListaCuentas(List<Cuenta> lista) {
		Collections.sort(lista, new Comparator<Cuenta>() {
			@Override
			public int compare(Cuenta o1, Cuenta o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
	}

	public LineaPresupuesto getSelectedRowTablaLineaPresupuesto() {
		LineaPresupuesto objetoSeleccionado = null;
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			int selectedRow = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
			if (selectedRow > -1) {
				objetoSeleccionado = this.getTableModel().getRow(selectedRow);
			}
		}
		return objetoSeleccionado;
	}

	void seleccionarDigestoMunicipal() throws Exception {
		AdminDigestoMunicipal adminDigestoMunicipal = new AdminDigestoMunicipal(this.getView());
		DigestoMunicipal locDigestoMunicipal = adminDigestoMunicipal.openSelect();
		if (locDigestoMunicipal != null) {
			this.getAbmModel().setDigestoMunicipal(locDigestoMunicipal);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}

	void limpiarDigestoMunicipal() throws Exception {
		this.getAbmModel().setDigestoMunicipal(null);
		this.actualizarABMModel();
		this.actualizarView();
	}

	private void addLineaPresupuestoGastos(List<LineaPresupuesto> lista, Cuenta cuenta) throws Exception {
		//		LineaPresupuestoGastos locLinea;
		//		Cuenta cuentaRecuperada = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(cuenta.getIdCuenta());
		//
		//		if (cuentaRecuperada.getCuentasHijos() == null || cuentaRecuperada.getCuentasHijos().isEmpty()) {
		//			locLinea = new LineaPresupuestoGastos();
		//			locLinea.setCuenta(cuentaRecuperada);
		//			locLinea.setMontoPresupuestado(0.0);
		//			locLinea.setPresupuesto(this.getAbmModel().getObjetoABM());
		//			lista.add(locLinea);
		//		}
		//		
		//		for (Cuenta cadaCuenta : cuentaRecuperada.getCuentasHijos()) {
		//			this.addLineaPresupuestoGastos(lista, cadaCuenta);
		//		}

		LineaPresupuestoGastos locLinea;
		Cuenta cuentaRecuperada = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(cuenta.getIdCuenta());

		if (cuentaRecuperada.getCuentasHijos() == null || cuentaRecuperada.getCuentasHijos().isEmpty()) {
			Iterator<LineaPresupuesto> locIterador = lista.iterator();
			boolean locExiste = false;
			while(locIterador.hasNext() && !locExiste){
				LineaPresupuesto locLineaPresupuesto = locIterador.next();
				if(locLineaPresupuesto.getCuenta().equals(cuentaRecuperada)){
					locExiste = true;
				}
			}

			if(!locExiste){
				locLinea = new LineaPresupuestoGastos();
				locLinea.setCuenta(cuentaRecuperada);
				locLinea.setMontoPresupuestado(0.0);
				locLinea.setPresupuesto(this.getAbmModel().getObjetoABM());
				lista.add(locLinea);
			}

		}

		for (Cuenta cadaCuenta : cuentaRecuperada.getCuentasHijos()) {
			this.addLineaPresupuestoGastos(lista, cadaCuenta);
		}
	}

	private void addLineaPresupuestoRecursos(List<LineaPresupuesto> lista, Cuenta cuenta) throws Exception {
		LineaPresupuestoRecursos locLinea;
		Cuenta cuentaRecuperada = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(cuenta.getIdCuenta());

		if (cuentaRecuperada.getCuentasHijos() == null || cuentaRecuperada.getCuentasHijos().isEmpty()) {
			Iterator<LineaPresupuesto> locIterador = lista.iterator();
			boolean locExiste = false;
			while(locIterador.hasNext() && !locExiste){
				LineaPresupuesto locLineaPresupuesto = locIterador.next();
				if(locLineaPresupuesto.getCuenta().equals(cuentaRecuperada)){
					locExiste = true;
				}
			}

			if(!locExiste){
				//				locLineaPresupuestoRecursos = new LineaPresupuestoRecursos();
				//				locLineaPresupuestoRecursos.setCuenta(cuentaRecuperada);
				//				locLineaPresupuestoRecursos.setMontoEstimado(0.0);
				//				locLineaPresupuestoRecursos.setPresupuesto(this.getAbmModel().getObjetoABM());
				//				locListaLineaPresupuesto.add(locLineaPresupuestoRecursos);
				locLinea = new LineaPresupuestoRecursos();
				locLinea.setCuenta(cuentaRecuperada);
				locLinea.setMontoEstimado(0.0);
				locLinea.setPresupuesto(this.getAbmModel().getObjetoABM());
				lista.add(locLinea);
			}
		}

		for (Cuenta cadaCuenta : cuentaRecuperada.getCuentasHijos()) {
			this.addLineaPresupuestoRecursos(lista, cadaCuenta);
		}
	}

	void agregarCuentas() throws Exception {
		this.actualizarABMModel();	
		AdminPlanDeCuenta adminPlanDeCuenta = new AdminPlanDeCuenta(this.getView());
		PlanDeCuenta planDeCuenta = adminPlanDeCuenta.openSelect();
		if (planDeCuenta != null) {

			planDeCuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getPlanDeCuentaByID(planDeCuenta.getIdPlanDeCuenta());

			List<LineaPresupuesto> locListaLineaPresupuesto = new ArrayList<LineaPresupuesto>();
			if (this.getTableModel().getListaObjetos() != null && this.getTableModel().getListaObjetos().size() > 0) {
				locListaLineaPresupuesto.addAll(this.getTableModel().getListaObjetos());
			}

			List<LineaPresupuesto> locListaLineaPresupuestoOriginal = new ArrayList<LineaPresupuesto>();
			locListaLineaPresupuestoOriginal.addAll(locListaLineaPresupuesto);

			if (this.getView().getCbTipo().getSelectedItem() != null) {
				if (this.getView().getCbTipo().getSelectedItem().equals(Tipo.GASTOS)) {
					LineaPresupuestoGastos locLineaPresupuestoGastos;

					List<Cuenta> listaOrdenadaCuentas = new ArrayList<Cuenta>(planDeCuenta.getListaCuentas());
					this.ordenarListaCuentas(listaOrdenadaCuentas);

					for (Cuenta cuenta : listaOrdenadaCuentas) {
						Cuenta cuentaRecuperada = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(cuenta.getIdCuenta());						

						if (cuentaRecuperada.getCuentasHijos() == null || cuentaRecuperada.getCuentasHijos().isEmpty()) {

							Iterator<LineaPresupuesto> locIterador = locListaLineaPresupuesto.iterator();
							boolean locExiste = false;
							while(locIterador.hasNext() && !locExiste){
								LineaPresupuesto locLineaPresupuesto = locIterador.next();
								if(locLineaPresupuesto.getCuenta().equals(cuentaRecuperada)){
									locExiste = true;
								}
							}

							if(!locExiste){
								locLineaPresupuestoGastos = new LineaPresupuestoGastos();
								locLineaPresupuestoGastos.setCuenta(cuentaRecuperada);
								locLineaPresupuestoGastos.setMontoPresupuestado(0.0);
								locLineaPresupuestoGastos.setPresupuesto(this.getAbmModel().getObjetoABM());
								locListaLineaPresupuesto.add(locLineaPresupuestoGastos);
							}

						}
						for (Cuenta cadaCuenta : cuentaRecuperada.getCuentasHijos()) {
							this.addLineaPresupuestoGastos(locListaLineaPresupuesto, cadaCuenta);
						}

					}
				}
				else {
					if (this.getView().getCbTipo().getSelectedItem().equals(Tipo.RECURSOS)) {

						LineaPresupuestoRecursos locLineaPresupuestoRecursos;

						List<Cuenta> listaOrdenadaCuentas = new ArrayList<Cuenta>(planDeCuenta.getListaCuentas());
						this.ordenarListaCuentas(listaOrdenadaCuentas);

						for (Cuenta cuenta : planDeCuenta.getListaCuentas()) {
							Cuenta cuentaRecuperada = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(cuenta.getIdCuenta());
							if (cuentaRecuperada.getCuentasHijos() == null || cuentaRecuperada.getCuentasHijos().isEmpty()) {
								Iterator<LineaPresupuesto> locIterador = locListaLineaPresupuesto.iterator();
								boolean locExiste = false;
								while(locIterador.hasNext() && !locExiste){
									LineaPresupuesto locLineaPresupuesto = locIterador.next();
									if(locLineaPresupuesto.getCuenta().equals(cuentaRecuperada)){
										locExiste = true;
									}
								}

								if(!locExiste){
									locLineaPresupuestoRecursos = new LineaPresupuestoRecursos();
									locLineaPresupuestoRecursos.setCuenta(cuentaRecuperada);
									locLineaPresupuestoRecursos.setMontoEstimado(0.0);
									locLineaPresupuestoRecursos.setPresupuesto(this.getAbmModel().getObjetoABM());
									locListaLineaPresupuesto.add(locLineaPresupuestoRecursos);
								}
							}
							for (Cuenta cadaCuenta : cuentaRecuperada.getCuentasHijos()) {
								this.addLineaPresupuestoRecursos(locListaLineaPresupuesto, cadaCuenta);
							}
						}
					}
				}
			}
			this.ordenarListaLineasPresupuesto(locListaLineaPresupuesto);
			this.getTableModel().setListaObjetos(locListaLineaPresupuesto);
			this.getAbmModel().setLineaPresupuesto(new HashSet<LineaPresupuesto>(locListaLineaPresupuesto));
		}
	}

	void quitarCuenta() throws Exception {
		//Se deshabilito xq si se quieta la fila anda bien mientras que el foco está en la columna de nombre cuenta, 
		//pero si está en la columna de valor (textfiel) no anda.
		int locFila = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
		this.getTableModel().deleteRow(locFila);
		// TODO: eliminar de la base para modificarPresupuesto ???
	}

	void quitarTodosCuenta() throws Exception {
		// TODO: eliminar TODAS de la base para modificarPresupuesto ???
		if (!this.getTableModel().getListaObjetos().isEmpty()) {
			if (AppManager.getInstance().showConfirmMsg(this.getView(), "¿Desea quitar todas las Cuentas?")) {
				this.getTableModel().clearTable();
			}
		}
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;

		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();

		List<Integer> attMin = new ArrayList<Integer>();
		List<JLabel> lblMin = new ArrayList<JLabel>();
		List<Integer> cantMin = new ArrayList<Integer>();

		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();

		attNulos.add(Conversor.getVacioSiNull(this.getView().getCbTipo().getSelectedItem()));
		lblNulos.add(this.getView().getLblTipo());

		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());

		attNulos.add(Conversor.getVacioSiNull(this.getView().getCbEstado().getSelectedItem()));
		lblNulos.add(this.getView().getLblEstado());

		attNulos.add(this.getView().getTfDigesto().getText());
		lblNulos.add(this.getView().getLblDigesto());

		attNulos.add(this.getView().getTfAnioPeriodo().getText());
		lblNulos.add(this.getView().getLblAnioPeriodo());

		attMin.add(this.getView().getPnlTabla().getTblDatos().getRowCount());
		lblMin.add(new JLabel("La Lista de Cuentas"));
		cantMin.add(1);

		attAnio.add(this.getView().getTfAnioPeriodo().getText());
		lblAnio.add(this.getView().getLblAnioPeriodo());
		longAnio.add(4);

		List<String> listaErrores = new ArrayList<String>();

		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarCantidadMinima(attMin, lblMin, cantMin));
			listaErrores.addAll(Validador.validarEnteros(attAnio, lblAnio));
			listaErrores.addAll(Validador.validarLongitudExacta(attAnio, lblAnio, longAnio));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
		}

		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}

		return validacionOK;
	}

}

class BtnSeleccionarDigestoListener implements ActionListener {
	private final ABMPresupuesto controller;
	public BtnSeleccionarDigestoListener(ABMPresupuesto controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarDigestoMunicipal();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarDigestoListener implements ActionListener {
	private final ABMPresupuesto controller;
	public BtnLimpiarDigestoListener(ABMPresupuesto controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarDigestoMunicipal();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarCuentaListener implements ActionListener {
	private final ABMPresupuesto controller;
	public BtnAgregarCuentaListener(ABMPresupuesto controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarCuentas();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarListener implements ActionListener {
	private final ABMPresupuesto controller;
	public BtnQuitarListener(ABMPresupuesto controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnQuitarTodosListener implements ActionListener {
	private final ABMPresupuesto controller;
	public BtnQuitarTodosListener(ABMPresupuesto controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.quitarTodosCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class LineaPresupuestoCellEditorListener implements CellEditorListener {
	private final ABMPresupuesto controller;
	public LineaPresupuestoCellEditorListener(ABMPresupuesto controller) {
		this.controller = controller;
	}


	@Override
	public void editingStopped(ChangeEvent e) {
		DefaultCellEditor ce = (DefaultCellEditor)e.getSource();
		Double valor = Conversor.getDouble(ce.getCellEditorValue().toString());
		LineaPresupuesto locLineaPresupuesto = this.controller.getSelectedRowTablaLineaPresupuesto();

		if (this.controller.getView().getCbTipo().getSelectedItem().equals(Tipo.GASTOS)) {
			LineaPresupuestoGastos locLineaPresupuestoGastos = (LineaPresupuestoGastos)locLineaPresupuesto;
			locLineaPresupuestoGastos.setMontoPresupuestado(valor);
		}
		else if (this.controller.getView().getCbTipo().getSelectedItem().equals(Tipo.RECURSOS)) {
			LineaPresupuestoRecursos locLineaPresupuestoRecursos = (LineaPresupuestoRecursos)locLineaPresupuesto;
			locLineaPresupuestoRecursos.setMontoEstimado(valor);
		}

	}

	@Override
	public void editingCanceled(ChangeEvent e) {

	}

}
