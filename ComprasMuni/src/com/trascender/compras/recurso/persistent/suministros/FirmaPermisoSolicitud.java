package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.FirmaPermiso;

@Entity
@Table(name = "RELA_SOL_SUM_FIR_PER")
public class FirmaPermisoSolicitud implements Serializable{

	public static final long serialVersionUID = 2261757767711121940L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_rela_sol_sum_fir_per")
	@SequenceGenerator(name = "gen_id_rela_sol_sum_fir_per", sequenceName = "gen_id_rela_sol_sum_fir_per",allocationSize = 1)
	@Column(name="ID_RELA_SOL_SUM_FIR_PER")
	private long idFirmaPermisoSolicitud = -1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SOLICITUD_SUMINISTRO")
	private SolicitudSuministro solicitudSuministro;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FIRMA_PERMISO_HAB")
	private FirmaPermiso firmaPermiso;

	public long getIdFirmaPermisoSolicitud() {
		return idFirmaPermisoSolicitud;
	}
	public SolicitudSuministro getSolicitudSuministro() {
		return solicitudSuministro;
	}
	public FirmaPermiso getFirmaPermiso() {
		return firmaPermiso;
	}
	public void setIdFirmaPermisoSolicitud(long idFirmaPermisoSolicitud) {
		this.idFirmaPermisoSolicitud = idFirmaPermisoSolicitud;
	}
	public void setSolicitudSuministro(SolicitudSuministro solicitudSuministro) {
		this.solicitudSuministro = solicitudSuministro;
	}
	public void setFirmaPermiso(FirmaPermiso firmaPermiso) {
		this.firmaPermiso = firmaPermiso;
	}
	@Override
	public String toString() {
		return "FirmaPermisoSolicitud [" + firmaPermiso.getUsuario() + "]";
	}
}
