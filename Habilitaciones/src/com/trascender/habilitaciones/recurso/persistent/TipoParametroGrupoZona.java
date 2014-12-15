package com.trascender.habilitaciones.recurso.persistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


@Entity
@DiscriminatorValue(value = "GRUPO_ZONA")
public class TipoParametroGrupoZona extends TipoParametro implements EntidadTrascender{
	public static final long serialVersionUID = -621870375783021522L;
	
	@Column(name = "NOMBRE")
	private String nombreGrupoZona;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "VALOR_GRUPO_ZONA", joinColumns = @JoinColumn(name = "ID_TIPO_PARAMETRO"))
	@MapKeyJoinColumn(name = "ID_ZONA")
	@Column(name = "VALOR")
	private Map<Zona, Double> listaZonas=new HashMap<Zona,Double>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ZONIFICACION")
	private Zonificacion zonificacion;
	
	@Transient
	private List<Map<Long, Long>> mapaParcelaZona;
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public TipoParametroGrupoZona() {
		super();
	}

	public Map<Zona, Double> getListaZonas() {
		return listaZonas;
	}
	public void setListaZonas(Map<Zona, Double> listaZonas) {
		this.listaZonas = listaZonas;
	}

	public String getNombreGrupoZona() {
		return nombreGrupoZona;
	}
	public void setNombreGrupoZona(String nombreGrupoZona) {
		this.nombreGrupoZona = nombreGrupoZona;
	}
	
	@Transient
	private int count = -1;
	
	@Override
	public Double getValor(DocHabilitanteEspecializado pDocumento) throws TrascenderException{
		try {
			if (pDocumento!=null){
				count++;
				if(count < 1){
					Zona locZona = getBusinessZonificacion().getZonaFromParcela(pDocumento.getParcela(), this.zonificacion);
					if(locZona != null) {
						Double valor = listaZonas.get(locZona);
						if(valor != null) {
							return valor;
						}
					}
				}
				else {
					Long locIdZona = this.getIdZonaFromParcela(pDocumento.getParcela());
					
					for (Zona cadaZona : this.getListaZonas().keySet()){
						if (cadaZona.getIdZona() == locIdZona){
							return this.getListaZonas().get(cadaZona).doubleValue();
						}
					}
				}
			}
			return 0d;
		}
		catch(Exception e){
//			System.out.println("La Parcela no se encuentra asociada a ninguna Zona.");
//			e.printStackTrace();
			return 0d;
		}
	}
	
	private Long getIdZonaFromParcela(Parcela pParcela) throws Exception{
		if (this.mapaParcelaZona == null){
			inicializarMapa();
		}
		for (Map<Long, Long> cadaMapa : this.mapaParcelaZona){
			Long idZona = cadaMapa.get(pParcela.getIdParcela());
			if (idZona != null) {return idZona;}
		}
		return null;
	}
	
	private void inicializarMapa() throws Exception{
		this.mapaParcelaZona = getBusinessZonificacion().getMapaParcelaZona(this.zonificacion);
	}
	
	
	@Override
	public TipoParametro clone() throws CloneNotSupportedException {
		TipoParametroGrupoZona locTipoParametro= (TipoParametroGrupoZona)super.clone();
		Map<Zona, Double> locMapa=new HashMap<Zona,Double>();
		
		System.out.println("LISTA ZONA= " + this.getListaZonas());
		System.out.println("KEYSET DE ZONAS= " + this.getListaZonas().keySet().size());
		
		for (Zona locZona : this.getListaZonas().keySet()){
			locMapa.put(locZona, this.getListaZonas().get(locZona).doubleValue());
		}
		locTipoParametro.setListaZonas(locMapa);
		return locTipoParametro;
	}

	public Zonificacion getZonificacion() {
		return zonificacion;
	}

	public void setZonificacion(Zonificacion zonificacion) {
		this.zonificacion = zonificacion;
	}
	
	private BusinessZonificacionLocal getBusinessZonificacion() throws Exception{
		Context ctx = new InitialContext();
		BusinessZonificacionLocal businessZonificacionLocal = 
			(BusinessZonificacionLocal) ctx.lookup(BusinessZonificacionLocal.JNDI_NAME);
		
		return businessZonificacionLocal;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		
	}
	
	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
	}

	public long getIdEntidad() {
		return this.getIdTipoParametro();
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idTipoParametro";
	}

	public boolean isAuditable() {
		return true;
	}
}
