package com.trascender.caja.gui.abmCaja;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.gui.framework.model.TAbstractABMModel;
/**
 * @author adrian
 */
public class CajaABMModel extends TAbstractABMModel<Caja> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().addCaja(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().deleteCaja(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().updateCaja(this.getObjetoABM());
	}

	public String getIdentificador() {
		return this.objetoABM.getNumero();
	}

	public void setIdentificador(String identificador) {
		this.objetoABM.setNumero(identificador);
	}

	public String getIp() {
		return this.objetoABM.getIpAddress();
	}

	public void setIp(String ip) {
		this.objetoABM.setIpAddress(ip);
	}

	public String getNombre() {
		return this.objetoABM.getNombre();
	}

	public void setNombre(String nombre) {
		this.objetoABM.setNombre(nombre);
	}

	public String getPuerto() {
		return this.objetoABM.getPuertoImpresion();
	}

	public void setPuerto(String puerto) {
		this.objetoABM.setPuertoImpresion(puerto);
	}

}
