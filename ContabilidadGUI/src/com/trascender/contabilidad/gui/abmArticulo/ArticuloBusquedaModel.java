package com.trascender.contabilidad.gui.abmArticulo;

import java.util.List;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Articulo.EstadoContable;
import com.trascender.compras.system.interfaces.SystemStock;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ArticuloBusquedaModel extends TAbstractBusquedaModel<Articulo>{
	
	private String codigo;
	private String nombre;
	private Area area;
	private EstadoContable estadoContable;

	@Override
	public List<Articulo> buscar() throws Exception {
		SystemStock systemStock = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemStock();
		return systemStock.findListaArticulo(nombre, codigo, null, null, estadoContable, area);
	}

	@Override
	public void reiniciar() {
		codigo = null;
		nombre = null;
		area = null;
		estadoContable = null;
		fireActualizarDatos();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public EstadoContable getEstadoContable() {
		return estadoContable;
	}

	public void setEstadoContable(EstadoContable estadoContable) {
		this.estadoContable = estadoContable;
	}
}
