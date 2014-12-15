/**
 * 
 */
package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.FirmaPermiso;

@Entity
@Table(name = "RELA_OC_FIRMA_PERMISO_HAB")
public class AutorizacionOrdenCompra implements Serializable {

	public static final long serialVersionUID = -6862102748942832363L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_rela_firma_permiso_hab")
	@SequenceGenerator(name = "gen_id_rela_firma_permiso_hab", sequenceName = "gen_id_rela_firma_permiso_hab",allocationSize = 1)
	@Column(name="ID_RELA_OC_FIRMA_PERMISO_HAB")
	private long idAutorizacionOrdenCompra=-1;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA")
	private OrdenCompra ordenCompra;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FIRMA_PERMISO_HAB")
	private FirmaPermiso firmaPermiso;
	
	public long getIdAutorizacionOrdenCompra() {
		return idAutorizacionOrdenCompra;
	}
	public void setIdAutorizacionOrdenCompra(long idAutorizacionOrdenCompra) {
		this.idAutorizacionOrdenCompra = idAutorizacionOrdenCompra;
	}
	public FirmaPermiso getFirmaPermiso() {
		return firmaPermiso;
	}
	public void setFirmaPermiso(FirmaPermiso pFirmaPermiso) {
		this.firmaPermiso = pFirmaPermiso;
	}
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompra pOrdenCompra) {
		this.ordenCompra = pOrdenCompra;
	}
	
	@Override
	public String toString() {
		return this.firmaPermiso.getUsuario().getNombrePersonaFisica();
	}
	
}
