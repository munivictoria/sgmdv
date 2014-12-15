package com.trascender.catastro.system.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.remoting.samples.chat.exceptions.InvalidArgumentException;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.persistent.AsociacionParcela;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaBridge;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCalle;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCuadra;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaManzana;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zona.Estado;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.catastro.system.interfaces.SystemAdministracionZona;

@Stateful(name = "ejb/SystemAdministracionZona")
public class SystemAdministracionZonaBean implements SystemAdministracionZona {

	private static final long serialVersionUID = 6995015525949473901L;
	private Zona zona;
	
	@EJB
	private BusinessZonificacionLocal systemZonificacion;
	
	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;
	

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @throws NamingException
	 * @ejb.create-method
	 */
	public void setZona(Zona pZona) throws Exception {
		try{
		if (pZona == null) {
			throw new CreateException("La zona no puede ser nula");
		}
		if (pZona.getIdZona() != -1) {
			this.zona = this.systemZonificacion.getZonaById(pZona.getIdZona());
		} else {
			this.zona = pZona;
		}
		this.cargarZona();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Se encarga de leer de la base de datos lo necesario para poder
	 * comunicarse con la interfaz sin problemas de lazy
	 */
	private void cargarZona() {
		
		for (AsociacionParcelaBridge cadaParcelaBridge : this.zona.getListaAsociacionParcela()) {
			cadaParcelaBridge.getTipo().toString();
			cadaParcelaBridge.getNombre().toString();
		}
	}

	public List<AsociacionParcelaBridge> getListaAsociaciones() {
		return this.zona.getListaAsociacionParcela();
	}

	public void agregarAsociacionesParcela(List<Parcela> pListaParcelas)
			throws Exception {
		try {
			for (Parcela cadaParcela : pListaParcelas) {
					AsociacionParcela cadaAsociacionParcela = new AsociacionParcela();
					cadaAsociacionParcela.setParcela(cadaParcela);
					this.zona.add(cadaAsociacionParcela);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void agregarAsociacionesCalle(List<Calle> pListaCalles)
			throws Exception {
		for (Calle cadaCalle : pListaCalles) {
				AsociacionParcelaCalle locAsociacionParcelaCalle = new AsociacionParcelaCalle();
				locAsociacionParcelaCalle.setCalle(cadaCalle);
				this.zona.add(locAsociacionParcelaCalle);
		}
	}

	public void agregarAsociacionManzana(List<Manzana> pListaManzanas)
			throws Exception {
		for (Manzana cadaManzana : pListaManzanas) {
				AsociacionParcelaManzana locAsociacion = new AsociacionParcelaManzana();
			locAsociacion.setManzana(cadaManzana);
			this.zona.add(locAsociacion);
		
		}
	}

	public void agregarAsociacionCuadra(List<Cuadra> pListaCuadras)
			throws Exception {
		for (Cuadra cadaCuadra : pListaCuadras) {
				AsociacionParcelaCuadra locAsociacion = new AsociacionParcelaCuadra();
				locAsociacion.setCuadra(cadaCuadra);
				this.zona.add(locAsociacion);
		}

	}

	public void guardar() throws Exception {
			this.validarZona(zona);
			this.entityManager.merge(zona);
	}

	public void eliminar() throws Exception {
		if (zona.getIdZona() == -1) {
			throw new CatastroException(95);
		}
		
		if(!this.zona.getListaAsociacionParcela().isEmpty()){
			throw new CatastroException(35);
		}
		this.zona.setEstado(Estado.INACTIVO);
		this.entityManager.merge(this.zona);
	}

	public void removeAsociacion(AsociacionParcelaBridge pAsociacion) {
		this.zona.getListaAsociacionParcela().remove(pAsociacion);
	}

	/**
	 * Valida que no haya una zona con el mismo nombre
	 * @param pSession
	 * @param pZona
	 * @throws Exception
	 */
	private void validarZona(Zona pZona) throws Exception {
		if (pZona == null) {
		throw new InvalidArgumentException();
	}
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Zona.class)
			.add(Restriccion.LIKE("nombre",pZona.getNombre(),false,Posicion.EXACTA))
			.add(Restriccion.IGUAL("zonificacion", pZona.getZonificacion()))
			.add(Restriccion.IGUAL("estado", Zona.Estado.ACTIVO))
			.setProyeccion(Proyeccion.COUNT());
			
		if(pZona.getIdZona() != -1){
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idZona", pZona.getIdZona())));
		}
		
		if((Long)locCriterio.uniqueResult() > 0){
			throw new CatastroException(18);
		}

	}

	public void mostrarDatos() {
		System.out.println("Zona");
		System.out.println("________________" + this.zona);
		System.out.println(this.zona.getNombre());
		System.out.println(this.zona.getDescripcion());
	}

	public String getDescripcion() {
		return zona.getDescripcion();
	}

	public String getNombre() {
		return zona.getNombre();
	}

	public Integer getPrioridad() {
		return zona.getPrioridad();
	}

	public void setDescripcion(String descripcion) {
		zona.setDescripcion(descripcion);
	}

	public void setNombre(String nombre) {
		zona.setNombre(nombre);
	}

	public void setPrioridad(Integer prioridad) {
		zona.setPrioridad(prioridad);
	}

	public void setZonificacion(Zonificacion zonificacion) {
		if(!this.entityManager.contains(zonificacion)){
			try{
				zonificacion = this.entityManager.find(Zonificacion.class, zonificacion.getIdZonificacion());
				if(zonificacion == null){
					throw new CatastroException(106);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		zona.setZonificacion(zonificacion);
	}

	public Zonificacion getZonificacion() {
		return zona.getZonificacion();
	}
}
