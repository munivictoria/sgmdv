//package com.trascender.contabilidad.business.ejb;
//
//import java.rmi.RemoteException;
//import java.util.Date;
//import java.util.List;
//
//import javax.ejb.EJBException;
//import javax.ejb.SessionBean;
//import javax.ejb.SessionContext;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Restrictions;
//
//import com.trascender.contabilidad.exception.TrascenderContabilidadException;
//import com.trascender.contabilidad.recurso.persistent.PeriodoContable;
//
//public class BusinessPeriodoContableBean implements SessionBean{
//
//	private static final long serialVersionUID = 1174155152314034305L;
//	private static final String NOMBRE = "CON|Adm Periodo Contable";
//	
//	
//	public BusinessPeriodoContableBean(){
//		super();
//	}
//
//
//	@Override
//	public void ejbActivate() throws EJBException, RemoteException {
//		
//	}
//
//
//	@Override
//	public void ejbPassivate() throws EJBException, RemoteException {
//		
//	}
//
//
//	@Override
//	public void ejbRemove() throws EJBException, RemoteException {
//		
//	}
//
//
//	@Override
//	public void setSessionContext(SessionContext arg0) throws EJBException,
//			RemoteException {
//		
//	}
//	
//	/*
//	 * Business method
//	 * @ejb.interface-method view-type="local"
//	 * @param pPeriodoContable
//	 * @return
//	 * @throws Exception
//	 */
//	public PeriodoContable addPeriodoContable(PeriodoContable pPeriodoContable) throws Exception{
//		Session locSession = GestorPersistenciaContabilidad.getInstance().getSession();
//		Transaction locTransaction = locSession.beginTransaction();
//		try{
//			locSession.save(pPeriodoContable);
//			locTransaction.commit();
//			locSession.refresh(pPeriodoContable);
//			return pPeriodoContable;
//		}
//		catch (Exception e){
//			locTransaction.rollback();
//			throw e;
//		}
//		finally{
//			locSession.close();
//		}
//	}
//	
//	/*
//	 * Business method
//	 * @ejb.interface-method view-type="local"
//	 * @param pPeriodoContable
//	 * @return
//	 * @throws Exception
//	 */
//	public PeriodoContable updatePeriodoContable (PeriodoContable pPeriodoContable) throws Exception{
//		Session locSession = GestorPersistenciaContabilidad.getInstance().getSession();
//		Transaction locTransaction = locSession.beginTransaction();
//		try{
//			locSession.update(pPeriodoContable);
//			locTransaction.commit();
//			locSession.refresh(pPeriodoContable);
//			return pPeriodoContable;
//		}
//		catch (Exception e){
//			locTransaction.rollback();
//			throw e;
//		}
//		finally{
//			locSession.close();
//		}
//	}
//	
//	/*
//	 * Business method
//	 * @ejb.interface-method view-type="local"
//	 * @param pPeriodoContable
//	 * @throws Exception
//	 */
//	public void deletePeriodoContable (PeriodoContable pPeriodoContable) throws Exception{
//		Session locSession = GestorPersistenciaContabilidad.getInstance().getSession();
//		Transaction locTransaction = locSession.getTransaction();
//		try{
//			locSession.delete(pPeriodoContable);
//			locTransaction.commit();
//		}
//		catch (Exception e){
//			locTransaction.rollback();
//			throw e;
//		}
//		finally{
//			locSession.close();
//		}
//	}
//	
//	/*
//	 * Business method
//	 * @ejb.interface-method view-type ="local"
//	 * @param pId
//	 * @return
//	 * @throws Exception
//	 */
//	public PeriodoContable getPeriodoContableByID(Long pId) throws Exception{
//		if (pId == null) {
//			throw new TrascenderContabilidadException(298);
//		}
//		if (pId ==-1 ) {
//			throw new TrascenderContabilidadException(299);
//		}
//		Session locSession = GestorPersistenciaContabilidad.getInstance().getSession();
//		try{
//			PeriodoContable locPeriodoContable = (PeriodoContable)locSession.createCriteria(PeriodoContable.class)
//												 .add(Restrictions.idEq(pId))
//												 .uniqueResult();
//			return locPeriodoContable;
//			
//		}
//		catch (Exception e){
//			throw e;
//		}
//		finally{
//			locSession.close();
//		}
//	}
//	
//	/*
//	 * Business method
//	 * @ejb.interface-method view-type="local"
//	 * @param pNombre
//	 * @param fechaInicioDesde
//	 * @param fechaInicioHasta
//	 * @param fechaFinDesde
//	 * @param fechaFinHasta
//	 * @param pEstado
//	 * @return
//	 * @throws Exception
//	 */
//	public List findListaPeriodoContable (String pNombre, Date fechaInicioDesde, Date fechaInicioHasta, 
//			Date fechaFinDesde, Date fechaFinHasta, PeriodoContable.Estado pEstado) throws Exception{
//		Session locSession = GestorPersistenciaContabilidad.getInstance().getSession();
//		try{
//			Criteria locCriteria = locSession.createCriteria(PeriodoContable.class);
//			
//			if (pNombre != null && !pNombre.trim().equals("")) {
//				locCriteria.add(Restrictions.ilike("nombre", pNombre, MatchMode.START));
//			}
//			if (fechaInicioDesde != null && fechaInicioHasta != null && fechaInicioDesde.compareTo(fechaInicioHasta) <= 0) {
//				locCriteria.add(Restrictions.between("fechaInicio", fechaInicioDesde, fechaInicioHasta));
//			}
//			if (fechaFinDesde != null && fechaFinHasta != null && fechaFinDesde.compareTo(fechaFinHasta) <= 0) {
//				locCriteria.add(Restrictions.between("fechaFin", fechaFinDesde, fechaFinHasta));
//			}
//			if (pEstado != null) {
//				locCriteria.add(Restrictions.eq("estado", pEstado));
//			}
//			
//			List listaPeriodosContables = locCriteria.list();
//			
//			return listaPeriodosContables;
//		}
//		catch (Exception e){
//			throw e;
//		}
//		finally{
//			locSession.close();
//		}
//		
//	}
//	
//}
