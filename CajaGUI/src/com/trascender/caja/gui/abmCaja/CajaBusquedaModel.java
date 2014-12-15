package com.trascender.caja.gui.abmCaja;

import java.util.List;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.Caja.Estado;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.gui.framework.util.Conversor;


/**
 * @author adrian
 */
public class CajaBusquedaModel extends TAbstractBusquedaModel<Caja>{

	private String nombre;
	private Estado estado;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Caja> buscar() throws Exception {
		SystemAdministracionIngresos locSystem = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos();
		
		List<Caja> locLista = locSystem.findListaCaja(this.getNombre(), this.getEstado());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		this.setEstado(Estado.ACTIVO);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = Conversor.getNullSiVacio(nombre);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
		this.fireActualizarDatos();
	}

}
