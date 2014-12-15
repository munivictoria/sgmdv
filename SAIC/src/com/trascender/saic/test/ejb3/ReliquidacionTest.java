package com.trascender.saic.test.ejb3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.JAserciones;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.habilitaciones.system.interfaces.SystemPeriodo;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaRefer;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ParametroValuadoAlicuota;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;
import com.trascender.saic.system.interfaces.SystemEstadoCuentaContribuyente;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;
import com.trascender.saic.system.interfaces.SystemReliquidacion;

public class ReliquidacionTest extends JAserciones{

	private  SystemUsuario systemUsuario;
	private  SystemReliquidacion systemReliquidacion;
	private  SystemPersonaFisica systemPersonaF;
	private  SystemEstadoCuentaContribuyente systemEstadoCuenta;
	private  SystemPeriodo systemPeriodo;
	private  SystemTipoTasa systemTipoTasa;
	private  SystemLiquidacionTasa systemLiquidacionTasa;

	private long llave;


	@Before
	public  void setUpBeforeClass() throws Exception{
		try{
			systemUsuario = (SystemUsuario) new InitialContext().lookup(SystemUsuario.JNDI_NAME);
			llave = systemUsuario.login("fer", "11235813");

			systemReliquidacion = (SystemReliquidacion) new InitialContext().lookup(SystemReliquidacion.JNDI_NAME);
			systemReliquidacion.setLlave(llave);

			systemEstadoCuenta = (SystemEstadoCuentaContribuyente) new InitialContext().lookup(SystemEstadoCuentaContribuyente.JNDI_NAME);
			systemEstadoCuenta.setLlave(llave);

			systemPeriodo = (SystemPeriodo) new InitialContext().lookup(SystemPeriodo.JNDI_NAME);
			systemPeriodo.setLlave(llave);

			systemTipoTasa = (SystemTipoTasa) new InitialContext().lookup(SystemTipoTasa.JNDI_NAME);
			systemTipoTasa.setLlave(llave);

			systemLiquidacionTasa = (SystemLiquidacionTasa) new InitialContext().lookup(SystemLiquidacionTasa.JNDI_NAME);
			systemLiquidacionTasa.setLlave(llave);

			systemPersonaF = (SystemPersonaFisica) new InitialContext().lookup(SystemPersonaFisica.JNDI_NAME);
			systemPersonaF.setLlave(llave);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReliquidarCagada() {
		try {
			FiltroLiquidacionTasaRefer locFiltro = new FiltroLiquidacionTasaRefer();
			locFiltro.setNumeroParcela("2494,2498,2534,2543,2549,2558,2567,2575,2577,2578,2585,261,2621,2624,2634,2670,2672,2688,27,2700,2731,2757,2800,2803,282,2843,2882,2883,2888,2898,290,2911,2912,2916,2935,2942,2957,298,2984,2990,2996,3015,3083,3084,3110,3112,3119,3165,3174,3176,320,3225,3228,324,3241,3245,3253,3256,3267,3279,3304,3309,3319,3326,3330,3331,3332,3334,3335,3355,3358,3390,3401,3411,3440,3441,345,3466,3482,3498,35,3519,3560,3563,3595,3604,3613,3632,3635,3652,3677,3715,3720,3724,3727,3737,3749,3770,3787,3800,3806,3845,3894,3895,3934,3954,3972,3978,3987,4000,4044,4075,4082,4085,4092,4102,4104,4128,413,4160,4164,4173,4181,4202,4205,421,4226,4229,4243,4296,4302,4346,435,4357,4361,4369,4376,4392,4395,4396,4411,4429,4454,4461,4462,4469,4518,4528,4563,4571,4596,460,4617,4627,463,4646,4667,468,4681,4719,4762,4834,4843,4863,489,4896,4897,4909,4916,4917,4919,4928,4934,4944,495,4961,4970,4978,5014,5017,5033,5034,5051,5107,5137,5145,5148,5149,5165,5175,522,5260,5266,5271,5276,5277,5282,5325,5326,5330,5349,539,5398,54,5411,5413,5432,5468,5483,5487,5509,5517,5530,5547,5552,5556,5607,5615,5647,5649,5665,5680,5685,5691,5717,5726,5738,5739,5746,5755,5760,5769,579,5831,5837,5847,5882,5921,5961,5964,5989,6018,6019,602,6041,6043,6046,6055,6060,6090,61,6154,616,6170,62,6243,6244,6251,6272,6282,632,6341,6346,6347,6359,640,6412,6415,643,6464,6491,6494,6523,6533,6584,6593,6631,6637,664,666,6680,6688,6694,670,6733,674,6826,6936,6942,6973,7071,7109,7131,7144,7157,7180,7201,7212,7217,722,7267,7289,7376,7412,7420,7428,7443,7449,745,7469,7491,7539,7553,7574,761,7638,7644,7661,7703,7742,7787,7789,7810,7879,7890,7892,7905,7917,7936,7949,7961,7974,7984,7986,8026,8029,8032,8036,8044,8047,8048,8050,806,8076,8082,8100,8125,8130,8147,8150,8180,8203,8213,824,8291,8301,8316,8330,8363,8395,8396,8416,8429,8439,8440,8450,8457,8461,8472,8476,8483,8485,8487,8494,8495,8507,8512,8514,8522,8524,8526,8530,8533,8541,8543,8553,8566,8574,8581,863,867,8684,8688,8693,8697,873,8739,8761,8774,8798,8813,8831,8835,8840,8846,8895,894,8953,8958,8989,8995,9003,9010,9016,9035,9069,9081,9087,9089,9096,9108,9119,9144,915,9208,9228,9258,9292,9297,9298,9309,9329,9341,9386,9397,9434,9505,9514,9535,954,9540,9544,957,9587,9588,9638,9677,9706,9715,9749,9801,9807,982,9820,9822,9824,9836,9841,9843,9844,9869,988,9884,9951,9998");

			FiltroCalendarioMunicipal locFiltroCalendario = new FiltroCalendarioMunicipal();
			locFiltroCalendario.setAnio(2013);
			locFiltroCalendario.setNombre("10 cuotas 2013");

			CalendarioMunicipal locCalendario = systemPeriodo.findListaCalendarios(locFiltroCalendario).getListaResultados().get(0);
			PeriodoLiquidacion locPeriodo = locCalendario.findPeriodo(null, 10);
			CuotaLiquidacion locCuota = locPeriodo.getCuotaLiquidacion(1);

			locFiltro.setAnio(2013);
			locFiltro.setCalendario(locCalendario);
			locFiltro.setPeriodo(locPeriodo);
			locFiltro.setCuota(locCuota);

			List<TipoObligacion> locListaTipoObligacion = new ArrayList<TipoObligacion>();
			locListaTipoObligacion.addAll(systemTipoTasa.findListaTipoObligacion("Tgi"));
			locListaTipoObligacion.addAll(systemTipoTasa.findListaTipoObligacion("Oysp"));
			locFiltro.setListaTipoObligacion(locListaTipoObligacion);

			List<LiquidacionTasa> locListaFinal = new ArrayList<LiquidacionTasa>();
			List<LiquidacionTasaRefer> locListaResultado = 
					systemEstadoCuenta.findListaLiquidacionTasaRefer(locFiltro).getListaResultados();
			for (LiquidacionTasaRefer cadaLiqTasaRefer : locListaResultado) {
				for (Long cadaId : cadaLiqTasaRefer.getIdsRegistrosDeuda()) {
					systemLiquidacionTasa.setLlave(llave);
					LiquidacionTasa liq = systemLiquidacionTasa.getLiquidacionTasaPorId(cadaId);
					locListaFinal.add(liq);
				}
			}

			List<String> locListaNuevosParametros = new ArrayList<String>();
			locListaNuevosParametros.add("ES_ALGUN_PROPIETARIO_MOROSO_OSP");
			locListaNuevosParametros.add("ES_ALGUN_PROPIETARIO_MOROSO_TGI");

			systemReliquidacion = (SystemReliquidacion) new InitialContext().lookup(SystemReliquidacion.JNDI_NAME);
			systemReliquidacion.setLlave(llave);

			for (LiquidacionTasa cadaLiquidacion : locListaFinal){
				systemReliquidacion.reliquidarObligacion(cadaLiquidacion, new Date(), locListaNuevosParametros, 
						new ArrayList<ParametroValuadoAlicuota>(), null, true);
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testFindListaReliquidaciones() throws Exception{
		try{
			PersonaFisica locPersona = systemPersonaF.getPersonaFisicaPorCuim("20-34335998-6");
			assertNotNull(locPersona);

			List locListaResultado = systemReliquidacion.findListaRegistrosDeudaContribuyente(locPersona, null, null, null, null, null);
			assertNotNull(locListaResultado);
			assertIsEmpty(locListaResultado);

			mostrarLista(RegistroDeuda.class, locListaResultado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAplicarIntereses(){
		try {
			Parcela locParcela = new Parcela();
			locParcela.setIdParcela(5308);//Parcela 5353
			systemLiquidacionTasa.setLlave(llave);
			PersonaFisica locPersona = new PersonaFisica();
			locPersona.setIdPersona(15201); // Francisco Garcilazo
			Calendario locCalendario = new Calendario();
			locCalendario.setIdCalendario(12l);
			List<LiquidacionTasa> locListaLiquidaciones = systemLiquidacionTasa.findListaLiquidacionesOSP(
					locPersona, locParcela, null, null, null, 
					null, null, null, locCalendario, 
					2013, null, null, null);
			for (LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
//				systemReliquidacion.calcularIntereses(cadaLiquidacion, new Date(), true, true);
				System.out.println(cadaLiquidacion.getInteres());
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
