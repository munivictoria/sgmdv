package com.trascender.habilitaciones.test.ejb3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.transito.BaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TablaBaseImponible;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoAutomotor;
import com.trascender.habilitaciones.system.interfaces.SystemTransito;

public class TransitoTest extends JAserciones{
	

	private static SystemUsuario systemUsuario;
	private static SystemDocumentoAutomotor systemAutomor;
	private static SystemTransito systemTransito;
	
	private static long llave = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			InitialContext initialContext = new InitialContext();

			systemUsuario = (SystemUsuario) initialContext.lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("root", "Emilia15");

			systemAutomor = (SystemDocumentoAutomotor) new InitialContext().lookup(SystemDocumentoAutomotor.JNDI_NAME);
			systemAutomor.setLlave(llave);
			
			systemTransito = (SystemTransito) initialContext.lookup(SystemTransito.JNDI_NAME);
			systemTransito.setLlave(llave);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaTransporteVehiculares() throws Exception{
		try{
//			List locListaResultado = this.systemAutomor.findListaVehiculo(null);
//				assertNotNull(locListaResultado);
//				assertIsEmpty(locListaResultado);
//				
//				mostrarLista(Vehiculo.class, locListaResultado);
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addVehiculo() throws Exception {
		try {
			Vehiculo locVehiculo = new Vehiculo();
			
			locVehiculo.setDescripcion("aesede");
			locVehiculo.setPatente("xxx-987");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findModelo() throws Exception {
		try {
			
			Marca locMarca = this.systemAutomor.getMarcaById(1l);
				assertNotNull(locMarca);
				System.out.println(locMarca);
				
			TipoVehiculo locTipoVehiculo = this.systemAutomor.getTipoVehiculoById(1l);
				assertNotNull(locTipoVehiculo);
				System.out.println(locTipoVehiculo);
				
//			List<Modelo> locLista = systemAutomor.findListaModelo(null, null, locTipoVehiculo);
//				assertNotNull(locLista);
//				assertIsEmpty(locLista);
//			
//			for (Modelo cadaModelo : locLista) {
//				System.out.println(cadaModelo);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findMarca() throws Exception {
		try {
//			List<Marca> locLista = systemAutomor.findListaMarca("BMW", null);
//				assertNotNull(locLista);
//				assertIsEmpty(locLista);
//			
//			for (Marca cadaMarca : locLista) {
//				System.out.println(cadaMarca);
//			}
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findTipoVehiculo() throws Exception {
		try {
//			List<Modelo> locLista = systemAutomor.findListaModelo(null, null, null);
//				assertNotNull(locLista);
//				assertIsEmpty(locLista);
//			
//			for (Modelo cadaModelo : locLista) {
//				System.out.println(cadaModelo);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddModelo() throws Exception {
		try {
			Modelo locModelo = new Modelo();
				locModelo.setDescripcion("asdasd");
					Marca locMarca = this.systemAutomor.getMarcaById(1l);
					assertNotNull(locMarca);
					System.out.println(locMarca);
				locModelo.setMarca(locMarca);
				locModelo.setMinimo(0d);
				locModelo.setNombre("TSI Twin turbo");
					TipoVehiculo locTipoVehiculo = this.systemAutomor.getTipoVehiculoById(1l);
					assertNotNull(locTipoVehiculo);
					System.out.println(locTipoVehiculo);
				locModelo.setTipoVehiculo(locTipoVehiculo);
				
			System.out.println(this.systemAutomor.addModelo(locModelo));
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addMarca() throws Exception {
		try {
			Marca locMarca = new Marca();
				locMarca.setCodigo("00");
				locMarca.setNombre("Renault");
				
			locMarca = this.systemAutomor.addMarca(locMarca);
			
			if(locMarca != null){
				System.out.println(locMarca);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateMarca() throws Exception {
		try {
			Marca locMarca = systemAutomor.getMarcaById(1l);
				assertNotNull(locMarca);
				System.out.println(locMarca);
				
			locMarca.setCodigo("02");
			
			locMarca = this.systemAutomor.updateMarca(locMarca);
			
			System.out.println(locMarca + " " + locMarca.getCodigo());
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findListaTipoVehiculo() throws Exception {
		try {
//			Collection<TipoVehiculo> locListaTiposVehiculos = systemAutomor.findListaTipoVehiculo("Sedan 10 Puertas.", null);
//				assertNotNull(locListaTiposVehiculos);
//				assertIsEmpty(locListaTiposVehiculos);
//				
//			for (TipoVehiculo cadaTipo : locListaTiposVehiculos) {
//				System.out.println(cadaTipo.toString());
//			}
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddTipoVehiculo() throws Exception {
		try {
			TipoVehiculo locTipoVehiculo = new TipoVehiculo();
				locTipoVehiculo.setCodigo("FF");
				locTipoVehiculo.setDescripcion("UNA DESCRIPCION");
				locTipoVehiculo.setNombre("Sedan 10 Puertas.");
			
			locTipoVehiculo = this.systemAutomor.addTipoVehiculo(locTipoVehiculo);
			
			System.out.println(locTipoVehiculo.toString() + " - " +locTipoVehiculo.getIdTipoVehiculo() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddTablaBaseImponible() throws Exception {
		try {
			TablaBaseImponible locTabla = new TablaBaseImponible();
				locTabla.setActivo(true);
				locTabla.setNombre("Tabla DNRPA");
			
			System.out.println(this.systemTransito.addTablaBaseImponible(locTabla));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTablabaseImponibleById() throws Exception {
		try {
			TablaBaseImponible locTabla = this.systemTransito.getTablaBaseImponibleById(1l);
				assertNotNull(locTabla);
				System.out.println(locTabla);
				
			Vehiculo locVehiculo = this.systemAutomor.getVehiculoPorId(1l);
				assertNotNull(locVehiculo);
				System.out.println(locVehiculo);
				
			System.out.println(locTabla.getValor(locVehiculo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddBaseImponible() throws Exception {
		try {
			BaseImponible locBI = new BaseImponible();
				locBI.setExento(false);
					Modelo locModelo = this.systemAutomor.getModeloById(1L);
					assertNotNull(locModelo);
					System.out.println(locModelo);
				locBI.setModelo(locModelo);
					TablaBaseImponible locTablaBI = this.systemTransito.getTablaBaseImponibleById(1L);
					assertNotNull(locTablaBI);
					System.out.println(locTablaBI);
				locBI.setTablaBaseImponible(locTablaBI);
				locBI.setValor(150D);
				
			System.out.println(this.systemTransito.addBaseImponible(locBI));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findBaseImponible() throws Exception {
		try {
			
			Marca locMarca = this.systemAutomor.getMarcaById(1l);
				assertNotNull(locMarca);
				System.out.println(locMarca);
			TipoVehiculo locTipoVehiculo = this.systemAutomor.getTipoVehiculoById(1l);
				assertNotNull(locTipoVehiculo);
				System.out.println(locTipoVehiculo);
			Modelo locModelo = this.systemAutomor.getModeloById(1l);
				assertNotNull(locModelo);
				System.out.println(locModelo);
			TablaBaseImponible locTabla = this.systemTransito.getTablaBaseImponibleById(1l);
				assertNotNull(locTabla);
				System.out.println(locTabla);
				
				
			Collection<BaseImponible> locListaBases = this.systemTransito.findListaBaseImponible(locMarca, locModelo,locTipoVehiculo, locTabla);
				assertNotNull(locListaBases);
				assertIsEmpty(locListaBases);
				
			for (BaseImponible cadaBaseImponible : locListaBases) {
				System.out.println(cadaBaseImponible);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTablaBaseImponible() throws Exception {
		try {
			TablaBaseImponible locTabla = this.systemTransito.getTablaBaseImponibleById(1l);
				assertNotNull(locTabla);
				System.out.println(locTabla);
			
			System.out.println((this.systemTransito.deleteTablaBaseImponible(locTabla))?"SE BORRO":"NO SE BORRO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddValuacionAcara() throws Exception {
		try {
			ValuacionAcara locValuacion = new ValuacionAcara();
				locValuacion.setAnio(2013);
				locValuacion.setMoneda(ValuacionAcara.Moneda.PESO);
				locValuacion.setValor(14.30d);
				System.out.println(systemAutomor);
			locValuacion = systemAutomor.addValuacionAcara(locValuacion);
			
			if(locValuacion != null){
				System.out.println(locValuacion);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindValuacionAcara() throws Exception {
		try {
			
			ValuacionAcara locValuacionAcara = systemAutomor
					.getValuacionAcaraById(1l);
			System.out.println(locValuacionAcara);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
