
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessTransitoLocal;
import com.trascender.habilitaciones.exception.TransitoException;
import com.trascender.habilitaciones.recurso.persistent.transito.BaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TablaBaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Stateless(name = "ejb/BusinessTransitoLocal")
public class BusinessTransitoBean implements BusinessTransitoLocal {

	private static final long serialVersionUID = -5458248890896648475L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Tabla Base Imponible");

		Recurso baseImponible = new Recurso();
		baseImponible.setIdRecurso(TablaBaseImponible.serialVersionUID);
		baseImponible.setNombre("Base Imponible");
		baseImponible.setClase(TablaBaseImponible.class);
		grupo.getListaRecursos().add(baseImponible);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	public Double getValorBaseImponible(TablaBaseImponible tablaBaseImponible, Vehiculo pVehiculo) {
		Double locBaseImponible = Criterio.getInstance(this.entityManager, BaseImponible.class).add(Restriccion.IGUAL("marca", pVehiculo.getModelo().getMarca()))
				.add(Restriccion.IGUAL("modelo", pVehiculo.getModelo())).add(Restriccion.IGUAL("tipoVehiculo", pVehiculo.getModelo().getTipoVehiculo()))
				.setProyeccion(Proyeccion.PROP("valor")).uniqueResult();

		return locBaseImponible;
	}

	public boolean getIsExentoBaseImponible(TablaBaseImponible tablaBaseImponible, Vehiculo pVehiculo) {
		Boolean locBaseImponible = Criterio.getInstance(this.entityManager, BaseImponible.class).add(Restriccion.IGUAL("marca", pVehiculo.getModelo().getMarca()))
				.add(Restriccion.IGUAL("modelo", pVehiculo.getModelo())).add(Restriccion.IGUAL("tipoVehiculo", pVehiculo.getModelo().getTipoVehiculo()))
				.setProyeccion(Proyeccion.PROP("exento")).uniqueResult();

		return locBaseImponible;
	}

	public TablaBaseImponible addTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception {
		this.validarTablaBaseImponible(pTablaBaseImponible);

		this.entityManager.persist(pTablaBaseImponible);
		this.entityManager.flush();
		this.entityManager.refresh(pTablaBaseImponible);

		return pTablaBaseImponible;
	}

	private void validarTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception {
		if(((Long) Criterio.getInstance(this.entityManager, TablaBaseImponible.class).add(Restriccion.IGUAL("activo", true)).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(4);
		}

	}

	public TablaBaseImponible updateTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception {
		this.validarTablaBaseImponible(pTablaBaseImponible);

		pTablaBaseImponible = this.entityManager.merge(pTablaBaseImponible);
		this.entityManager.flush();

		return pTablaBaseImponible;
	}

	public boolean deleteTablaBaseImponible(TablaBaseImponible pTablaBaseImponible) throws Exception {
		pTablaBaseImponible.setActivo(false);
		try {
			int result = this.entityManager
					.createQuery("UPDATE BaseImponible bs SET bs.fechaBaja = :locFechaBaja " + "WHERE bs.tablaBaseImponible = :locIdTablaBI AND bs.fechaBaja = NULL")
					.setParameter("locFechaBaja", Calendar.getInstance().getTime()).setParameter("locIdTablaBI", pTablaBaseImponible).executeUpdate();

			System.out.println("DEVOLVIO: " + result);

			if(result < 1) {
				throw new Exception("NO ACTUALIZO UNA GOMA");
			}

			this.entityManager.merge(pTablaBaseImponible);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public TablaBaseImponible getTablaBaseImponibleById(Long pIdTablaBaseImponible) throws Exception {
		TablaBaseImponible locTablaBaseImponible = this.entityManager.find(TablaBaseImponible.class, pIdTablaBaseImponible);

		if(locTablaBaseImponible != null) {
			locTablaBaseImponible.toString();
		}

		return locTablaBaseImponible;
	}

	public Set<TablaBaseImponible> findListaTablaBaseImponible(String pNombre, Boolean pActivo) throws Exception {
		Collection<TablaBaseImponible> listaTablas = Criterio.getInstance(this.entityManager, TablaBaseImponible.class).add(Restriccion.IGUAL("nombre", pNombre))
				.add(Restriccion.IGUAL("activo", pActivo)).list();

		return (Set<TablaBaseImponible>) listaTablas;
	}

	public BaseImponible addBaseImponible(BaseImponible pBaseImponible) throws Exception {
		this.validarBaseImponible(pBaseImponible);

		this.entityManager.persist(pBaseImponible);
		this.entityManager.flush();
		this.entityManager.refresh(pBaseImponible);

		return pBaseImponible;
	}

	/**
	 * Valida la integridad de datos de la base imponible
	 * 
	 * @param pBaseImponible
	 * @throws Exception
	 */
	private void validarBaseImponible(BaseImponible pBaseImponible) throws Exception {
		if(pBaseImponible == null) {
			throw new TransitoException(200);
		}

		if(pBaseImponible.getTablaBaseImponible() == null) {
			throw new TransitoException(201);
		}

		if((pBaseImponible.getModelo() == null) && (pBaseImponible.getModelo().getMarca() == null || pBaseImponible.getModelo().getTipoVehiculo() == null)) {
			throw new TransitoException(202);
		}

		if(!pBaseImponible.getTablaBaseImponible().isActivo()) {
			throw new TransitoException(204);
		}

		if(((Long) Criterio.getInstance(this.entityManager, BaseImponible.class).crearAlias("modelo", "locModelo")
				.add(Restriccion.IGUAL("tablaBaseImponible", pBaseImponible.getTablaBaseImponible())).add(Restriccion.IGUAL("locModelo", pBaseImponible.getModelo()))
				.add(Restriccion.IGUAL("locModelo.marca", pBaseImponible.getModelo().getMarca()))
				.add(Restriccion.IGUAL("locModelo.tipoVehiculo", pBaseImponible.getModelo().getTipoVehiculo())).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(205);
		}

	}

	public BaseImponible updateBaseImponible(BaseImponible pBaseImponible) throws Exception {
		this.validarBaseImponible(pBaseImponible);

		pBaseImponible = this.entityManager.merge(pBaseImponible);
		this.entityManager.flush();

		return pBaseImponible;
	}

	public boolean deleteBaseImponible(BaseImponible pBaseImponible) throws Exception {
		try {
			pBaseImponible.setFechaBaja(Calendar.getInstance().getTime());
			this.entityManager.merge(pBaseImponible);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public BaseImponible getBaseImponibleById(Long pIdBaseImponible) throws Exception {
		BaseImponible locBaseImponible = Criterio.getInstance(this.entityManager, BaseImponible.class).add(Restriccion.IGUAL("idBaseImponible", pIdBaseImponible)).uniqueResult();

		if(locBaseImponible != null) {
			locBaseImponible.toString();
			locBaseImponible.getModelo().toString();
			locBaseImponible.getModelo().getMarca().toString();
			locBaseImponible.getModelo().getTipoVehiculo().toString();

			for(AtributoDinamico<?> cadaAtributo : locBaseImponible.getListaAtributosDinamicos()) {
				cadaAtributo.toString();
			}
		}

		return locBaseImponible;
	}

	public Set<BaseImponible> findListaBaseImponible(Marca pmarca, Modelo pModelo, TipoVehiculo pTipoVehiculo, TablaBaseImponible pTablaBaseImponible) throws Exception {

		Collection<BaseImponible> locListaBasesImponibles = Criterio.getInstance(this.entityManager, BaseImponible.class).crearAlias("modelo", "locModelo")
				.add(Restriccion.IGUAL("locModelo.marca", pmarca)).add(Restriccion.IGUAL("locModelo", pModelo)).add(Restriccion.IGUAL("locModelo.tipoVehiculo", pTipoVehiculo))
				.add(Restriccion.IGUAL("tablaBaseImponible", pTablaBaseImponible)).list();

		if(locListaBasesImponibles != null && !locListaBasesImponibles.isEmpty()) {
			for(BaseImponible cadaBaseImponible : locListaBasesImponibles) {
				cadaBaseImponible.toString();
			}
		}

		return new HashSet<BaseImponible>(locListaBasesImponibles);
	}

}
