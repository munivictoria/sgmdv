package com.trascender.catastro.exception;

import com.trascender.framework.exception.TrascenderException;

public class CatastroException extends TrascenderException {

	public CatastroException(int pCodeCatastroException) {
		super(2000+pCodeCatastroException);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8761572123795901666L;

	protected void setMsg() {
		switch(getCodeTrascenderException()){
			//BusinessRegistroPropiedad
			case 2000: this.msg = "El Título de Propiedad ya se encuentra registrado.";break;
			case 2001: this.msg = "El Tipo de Transacción Catastral ya se encuentra registrado.";break;
			case 2002: this.msg = "Algunos de los datos requeridos son Nulos.";break;
			case 2003: this.msg = "El Título de Propiedad debe pertenecer a una Parcela.";break;
			case 2004: this.msg = "La parcela no tiene propietarios registrados. Verifique la información cargada."; break;
			case 2005: this.msg = "La parcela seleccionada ya posee un título de propiedad.";break;
			case 2006: this.msg = "El titulo de propiedad no es valido.";
			case 2007: this.msg = "El titulo de propiedad debe tener al menos un propietario.";break;
			
			//BusinessRegistroGeografico
			case 2017: this.msg = "La Cuadra que intenta eliminar se encuentra asociada a una Manzana"; break;
			case 2018: this.msg = "Ya existe una zona igual para esa Zonificación."; break;
			case 2019: this.msg = "La Zona no puede ser nula."; break;
			case 2020: this.msg = "El código ingresado ya se encuentra registrado."; break;
			case 2021: this.msg = "El Tipo de Calle no puede ser Nulo."; break;
			case 2022: this.msg = "Otra Calle diferente ya se encuentra registrada con ese Código."; break;
			case 2023: this.msg = "El Tipo de Calle ya se encuentra registrado."; break;
			case 2024: this.msg = "Otro Tipo de Calle se encuentra registrado con el mismo Nombre."; break;
			case 2025: this.msg = "El Servicio no se encuentra registrado."; break;
			case 2026: this.msg = "Ya existe un Servicio con el nombre ingresado."; break;
			case 2027: this.msg = "La Zona ya se encuentra registrada."; break;
			case 2028: this.msg = "La Zona no se encuentra registrada."; break;
			case 2029: this.msg = "Otra Zona diferente ya se encuentra registrada con el mismo Nombre."; break;
			case 2030: this.msg = "Ya hay registrada una manzana activa con el mismo nombre."; break;
			case 2031: this.msg = "La Manzana debe pertenecer a una Zona."; break;
			case 2032: this.msg = "La Manzana no se encuentra registrada."; break;
			case 2033: this.msg = "Ya hay una Manzana registrada con el mismo Nombre."; break;
			case 2034: this.msg = "El Número de Manzana no puede ser Nulo."; break;
			case 2035: this.msg = "La Zona no puede ser eliminada ya que existen elementos relacionados."; break;
			case 2036: this.msg = "El Tipo de Calle no puede ser eliminado ya que existen Calles de ese Tipo."; break;
			case 2037: this.msg = "El Servicio ya se encuentra registrado."; break;
			case 2038: this.msg = "El Servicio no puede ser eliminado ya que existen elementos relacionados."; break;
			case 2039: this.msg = "El Código Postal de la Cuadra debe ser único."; break;
			case 2040: this.msg = "El Número de la Manzana ya se encuentra registrado."; break;
			case 2041: this.msg = "La Manzana no puede ser Nula."; break;
			case 2042: this.msg = "No se puede eliminar una Calle que tenga Cuadras activas."; break;
			case 2043: this.msg = "La Parcela no puede ser Nula."; break;
			case 2044: this.msg = "La Manzana de la Parcela no puede ser Nula."; break;
			case 2045: this.msg = "El Número de Parcela debe ser único en la Manzana."; break;
			case 2046: this.msg = "El Número Desde de la Cuadra debe ser menor al Número Hasta";break;
			case 2047: this.msg = "La Numeración de la Cuadra no puede ser menor a uno";break;
			case 2048: this.msg = "La Cuadra tiene Parcelas Asociadas. Para eliminarla deben borrarse todas las Parcelas pertenecientes a la Cuadra";break;
			case 2049: this.msg = "La parcela que intenta agregar ya se encuentra registrada"; break;
			case 2069: this.msg = "No se puede eliminar una Manzana que tenga cuadras asociadas."; break;
			case 2107: this.msg = "No se puede eliminar una Manzana que este asociado a una Parcela."; break;
			case 2109: this.msg = "Ya existe una Cuadra con la misma Calle, Calle de comienzo, Calle de finalización y Tipo Numeración."; break;
			
			//BusinessCodigosCatastrales
			case 2050: this.msg = "Ya existe un Tipo de Construcción con ese nombre."; break;
			case 2051: this.msg = "Faltan atributos requeridos."; break;
			case 2052: this.msg = "Faltan atributos requeridos."; break;
			case 2053: this.msg = "El Tipo de Construcción de la Categoría no puede ser Nulo."; break;
			case 2054: this.msg = "El Código de la Categoría no puede ser Nulo."; break;
			case 2055: this.msg = "Ya existe una Categoría con el Código ingresado para el Tipo de Construcción."; break;
			case 2056: this.msg = "Ya existe una Categoría con el Código ingresado para el Tipo de Construcción."; break;
			case 2057: this.msg = "El Coeficiente de Depreciación no puede ser eliminado porque es actualmente utilizado."; break;
			case 2058: this.msg = "Ninguno de los datos ingresados puede ser Nulo."; break;
			case 2059: this.msg = "El Año del Valor Básico de Mejora no puede ser Nulo."; break;
			case 2060: this.msg = "La Categoría del Valor Básico de Mejora no puede ser Nula."; break;
			case 2061: this.msg = "Ya existe un Valor Básico de Mejora para ese Año en la Categoría ingresada."; break;
			case 2062: this.msg = "Existe otro Valor Básico de Mejora para ese Año en la Categoría ingresada."; break;
			case 2063: this.msg = "Los parámetros ingresados no pueden ser Nulos."; break;
			case 2064: this.msg = "Los datos de la Lista no son del tipo CoeficienteDepreciación.";break;
			case 2065: this.msg = "La Categoría no puede tener valor Nulo."; break;
			case 2066: this.msg = "El Coeficiente de Depreciación nunca fue guardado."; break;
			case 2067: this.msg = "Ya existe un Coeficiente de Depreciación para el mismo Año en la misma Categoría."; break;
			case 2068: this.msg = "Ya existe una Categoría con el nombre ingresado para el Tipo de Construcción."; break;
			
			//BusinessRegistroParcelario
			case 2070: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2071: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2072: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2073: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2074: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2075: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2076: this.msg = "Algunos de los datos requeridos del Plano de Mensura son Nulos."; break;
			case 2077: this.msg = "El Plano de Mensura ya se encuentra registrado."; break;
			case 2078: this.msg = "Otro Plano de Mensura ya se encuentra registrado con el mismo Código."; break;
			case 2079: this.msg = "Algunos de los datos requeridos del Plano de Mensura poseen valor Nulo."; break;
			case 2080: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2081: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2082: this.msg = "Algunos de los datos requeridos son Nulos."; break;
			case 2083: this.msg = "El Número de Parcela debe ser único por Manzana."; break;	/*Nota JP: ver este c�digo*/
			case 2084: this.msg = "El plano de construcción o la parcela no pueden ser nulos."; break;
			case 2085: this.msg = "Ya existe un plano de construcción con ese Código, para esa parcela."; break;
			case 2086: this.msg = "Todos los números de registro de la parcela deben ser unicos."; break;
			case 2087: this.msg = "No se puede agregar una sub parcela que exceda la superficie de la misma."; break;
			case 2088: this.msg = "No se puede crear una sub parcela sin un titutar."; break;
			case 2089: this.msg = "La parcela debe poseer todos los numeros de registro validos."; break;
			case 2108: this.msg = "La parcela no puede tener Registros Mejora activos y ser Baldío."; break;
			
			//BusinessZonificacion
			case 2090: this.msg = "Para eliminar la zonificación, no debe tener ninguna zona asociada, Elimine las zonas primero e intente nuevamente."; break;
			case 2091: this.msg = "Algunos datos requeridos para la zonificación son nulos. Verifique los datos ingresados"; break;
			case 2092: this.msg = "La zonificación ya se encuentra registrada. Verifique los datos ingresados."; break;
			case 2093: this.msg = "Otra zonificación con el mismo nombre ya se encuentra registrada, cámbiele de nombre e intente nuevamente."; break;
			case 2094: this.msg = "No puede haber dos zonas con la misma prioridad y otra zona ya se encuentra registrada con la misma prioridad, Verifique los datos ingresado."; break;
			case 2095: this.msg = "No se puede eliminar una zona sin haber sido guardada anteriormente. Cancele la operación."; break;
			case 2096: this.msg = "No se ha podido recuperar la lista de zonificaciones.";break;
			case 2097: this.msg = "No se ha podido agregar la zonificación.";break;
			case 2098: this.msg = "No se han podido actualizar los datos de la zonificación.";break;
			case 2099: this.msg = "No se ha podido eliminar la zonificación.";break;
			case 2100: this.msg = "No puede eliminarse una Zona que tenga Parcelas asociadas.";break;
			case 2101: this.msg = "El número de partida provincial no es válido."; break;
			case 2102: this.msg = "El número de Partida Provincial ya existe."; break;
			case 2103: this.msg = "El número de Registro ya existe."; break;
			case 2104: this.msg = "El número de Matricula provincial ya existe."; break;
			case 2105: this.msg = "El número de Cuenta provincial ya existe."; break;
			case 2106: this.msg = "La zonicicación no es valida o no existe."; break;
			
			//SystemRegistroPropiedad
			case 2300: this.msg = "No se ha podido agregar el Título de Propiedad. Por favor, intente nuevamente."; break;
			case 2301: this.msg = "No se ha podido actualizar los datos del Título de Propiedad. Por favor, intente nuevamente."; break;
			case 2302: this.msg = "No se ha podido recuperar los datos de los Títulos de Propiedad. Por favor, intente nuevamente."; break;
			case 2303: this.msg = "No se ha podido agregar el Tipo de Transacción Catastral. Por favor, intente nuevamente."; break;
			case 2304: this.msg = "No se ha podido actualizar el Tipo de Transacción Catastral. Por favor, intente nuevamente."; break;
			case 2305: this.msg = "No se ha podido eliminar el Tipo de Transacción Catastral. Por favor, intente nuevamente."; break;
			case 2306: this.msg = "No se ha podido recuperar los datos de los Tipos de Transacción Catastral. Por favor, intente nuevamente."; break;

			//SystemInformacionGeogr�fica
			case 2340: this.msg = "No se ha podido agregar la Calle. Por favor, intente nuevamente."; break;
			case 2341: this.msg = "No se ha podido actualizar los datos de la Calle. Por favor, intente nuevamente."; break;
			case 2342: this.msg = "No se ha podido recuperar los datos de las Calles. Por favor, intente nuevamente."; break;
			case 2343: this.msg = "No se ha podido eliminar la Calle requerida. Por favor, intente nuevamente."; break;
			case 2344: this.msg = "No se ha podido agregar el Tipo de Calle. Por favor, intente nuevamente."; break;
			case 2345: this.msg = "No se ha podido actualizar los datos del Tipo de Calle. Por favor, intente nuevamente."; break;
			case 2346: this.msg = "No se ha podido eliminar el Tipo de Calle. Por favor, intente nuevamente."; break;
			case 2347: this.msg = "No se ha podido recuperar los datos de los Tipos de Calle. Por favor, intente nuevamente."; break;
			case 2348: this.msg = "No se ha podido agregar el Servicio. Por favor, intente nuevamente."; break;
			case 2349: this.msg = "No se ha podido actualizar los datos del Servicio. Por favor, intente nuevamente."; break;
			case 2350: this.msg = "No se ha podido eliminar el Servicio. Por favor, intente nuevamente."; break;
			case 2351: this.msg = "No se ha podido recuperar los datos de los Servicios. Por favor, intente nuevamente."; break;
			case 2352: this.msg = "No se ha podido agregar la Zona. Por favor, intente nuevamente."; break;
			case 2353: this.msg = "No se ha podido actualizar los datos de la Zona. Por favor, intente nuevamente."; break;
			case 2354: this.msg = "No se ha podido eliminar la Zona. Por favor, intente nuevamente."; break;
			case 2355: this.msg = "No se ha podido recuperar los datos de las Zonas. Por favor, intente nuevamente."; break;
			case 2356: this.msg = "No se ha podido agregar la Manzana. Por favor, intente nuevamente."; break;
			case 2357: this.msg = "No se ha podido actualizar los datos de la Manzana. Por favor, intente nuevamente."; break;
			case 2358: this.msg = "No se ha podido eliminar la Manzana. Por favor, intente nuevamente."; break;
			case 2359: this.msg = "No se ha podido recuperar los datos de las Manzanas. Por favor, intente nuevamente."; break;
			case 2360: this.msg = "No se ha podido agregar la Cuadra. Por favor, intente nuevamente."; break;
			case 2361: this.msg = "No se ha podido actualizar los datos de la Cuadra. Por favor, intente nuevamente."; break;
			case 2362: this.msg = "No se ha podido eliminar la Cuadra. Por favor, intente nuevamente."; break;
			case 2363: this.msg = "No se ha podido recuperar los datos de las Cuadras. Por favor, intente nuevamente."; break;
			case 2364: this.msg = "No se ha podido restaurar la Calle. Por favor, intente nuevamente."; break;
			case 2365: this.msg = "No se ha podido restaurar el Tipo de Calle. Por favor, intente nuevamente."; break;
			case 2366: this.msg = "No se ha podido restaurar la Cuadra. Por favor, intente nuevamente."; break;
			case 2367: this.msg = "No se ha podido recuperar los datos de las Manzanas. Por favor, intente nuevamente."; break;
			case 2368: this.msg = "No se han podido recuperar los datos de la zona. Por favor intente nuevamente."; break;
			case 2369: this.msg = "No se ha podido recuperar la Zonificación."; break;
			//SystemAdministracionDDJJ
			case 2370: this.msg = "No se ha podido agregar la Declaración Jurada. Por favor, intente nuevamente."; break;
			case 2371: this.msg = "No se ha podido actualizar los datos de la Declaración Jurada. Por favor, intente nuevamente."; break;
			case 2372: this.msg = "No se ha podido recuperar los datos de las Declaraciones Juradas. Por favor, intente nuevamente."; break;
			case 2373: this.msg = "No se ha podido agregar el Registro de Mejora. Por favor, intente nuevamente."; break;
			case 2374: this.msg = "No se ha podido actualizar los datos del Registro de Mejora. Por favor, intente nuevamente."; break;
			case 2375: this.msg = "No se ha podido eliminar el Registro de Mejora. Por favor, intente nuevamente."; break;
			case 2376: this.msg = "No se ha podido restaurar el Registro de Mejora. Por favor, intente nuevamente."; break;
			case 2377: this.msg = "No se ha podido recuperar los datos de los Registros de Mejora. Por favor, intente nuevamente."; break;
			case 2378: this.msg = "Ya existe una declaración jurada para esa parcela con ese codido."; break;
			case 2379: this.msg = "El Registro de Mejora y/o la parcela del mismo no pueden ser nulos."; break;
			//SystemCodigosCatastrales
			case 2380: this.msg = "No se ha podido agregar el Tipo de Construcción. Por favor, intente nuevamente."; break;
			case 2381: this.msg = "No se ha podido actualizar los datos del Tipo de Construcción. Por favor, intente nuevamente."; break;
			case 2382: this.msg = "No se ha podido recuperar los datos de los Tipos de Construcción. Por favor, intente nuevamente."; break;
			case 2383: this.msg = "No se ha podido eliminar el Tipo de Construcción. Por favor, intente nuevamente."; break;
			case 2384: this.msg = "No se ha podido restaurar el Tipo de Construcción. Por favor, intente nuevamente."; break;
			case 2385: this.msg = "No se ha podido agregar la Categoría. Por favor, intente nuevamente."; break;
			case 2386: this.msg = "No se ha podido actualizar los datos de la Categoría. Por favor, intente nuevamente."; break;
			case 2387: this.msg = "No se ha podido recuperar los datos de las Categorías. Por favor, intente nuevamente."; break;
			case 2388: this.msg = "No se ha podido eliminar la Categoría. Por favor, intente nuevamente."; break;
			case 2389: this.msg = "No se ha podido restaurar la Categoría. Por favor, intente nuevamente."; break;
			case 2390: this.msg = "No se ha podido agregar Coeficiente de Depreciación. Por favor, intente nuevamente."; break;
			case 2391: this.msg = "No se ha podido actualizar los datos del Coeficiente de Depreciación. Por favor, intente nuevamente."; break;
			case 2392: this.msg = "No se ha podido recuperar los datos de los Coeficientes de Depreciación. Por favor, intente nuevamente."; break;
			case 2393: this.msg = "No se ha podido recuperar los datos de los Coeficientes de Depreciación. Por favor, intente nuevamente."; break;
			case 2394: this.msg = "No se ha podido agregar el Valor Básico de Mejora. Por favor, intente nuevamente."; break;
			case 2395: this.msg = "No se ha podido actualizar los datos del Valor Básico de Mejora. Por favor, intente nuevamente."; break;
			case 2396: this.msg = "No se ha podido eliminar el Valor Básico de Mejora. Por favor, intente nuevamente."; break;
			case 2397: this.msg = "No se ha podido recuperar los Valores Básicos de Mejora. Por favor, intente nuevamente."; break;
			case 2398: this.msg = "No se ha podido recuperar los Valores Básicos de Mejora. Por favor, intente nuevamente."; break;
			case 2399: this.msg = "No se ha podido eliminar el Coeficiente de Depreciación. Por favor, intente nuevamente.";break;
			case 2400: this.msg = "No se ha podido almacenar los datos de los Coeficientes de Depreciación. Por favor, intente nuevamente."; break;
					
			case 2410: this.msg = "No se ha podido agregar la Parcela. Por favor, intente nuevamente."; break;
			case 2411: this.msg = "No se ha podido actualizar los datos de la Parcela. Por favor, intente nuevamente."; break;
			case 2412: this.msg = "No se ha podido recuperar los datos de las Parcelas. Por favor, intente nuevamente."; break;
			case 2413: this.msg = "No se ha podido recuperar los datos de las Parcelas. Por favor, intente nuevamente."; break;
			case 2414: this.msg = "No se ha podido recuperar los datos de las Parcelas. Por favor, intente nuevamente."; break;
			case 2415: this.msg = "No se ha podido recuperar los datos de los Volantes Catastrales. Por favor, intente nuevamente."; break;
			case 2416: this.msg = "No se ha podido recuperar los datos de los Planos de Mensura. Por favor, intente nuevamente."; break;
			case 2417: this.msg = "No se ha podido recuperar los datos de las Declaraciones Juradas. Por favor, intente nuevamente."; break;
			case 2418: this.msg = "No se ha podido recuperar los datos de los Registros de Mejoras. Por favor, intente nuevamente."; break;
			case 2419: this.msg = "No se ha podido recuperar los datos de los Planos de Construcción. Por favor, intente nuevamente."; break;
			case 2430: this.msg = "No se ha podido agregar el Volante Catastral. Por favor, intente nuevamente."; break;
			case 2431: this.msg = "No se ha podido actualizar el Volante Catastral. Por favor, intente nuevamente.";break;
			case 2432: this.msg = "No se ha podido eliminar el Volante Catastral. Por favor, intente nuevamente.";break;
			case 2433: this.msg = "No se ha podido agregar el Plano de Mensura. Por favor, intente nuevamente.";break;
			case 2434: this.msg = "No se ha podido actualizar los datos del Plano de Mensura. Por favor, intente nuevamente.";break;
			case 2435: this.msg = "No se ha podido eliminar el Plano de Mensura. Por favor, intente nuevamente.";break;
			case 2436: this.msg = "No se ha podido agregar el Plano de Construcción. Por favor, intente nuevamente.";break;
			case 2437: this.msg = "No se ha podido actualizar el Plano de Construcción. Por favor, intente nuevamente.";break;
			case 2438: this.msg = "No se ha podido eliminar el Plano de Construcción. Por favor, intente nuevamente.";break;
			case 2439: this.msg = "No se ha podido recuperar los datos de los Planos de Construcción. Por favor, intente nuevamente.";break;
			case 2440: this.msg = "No se ha podido recuperar las Cuadras que pertenecen a esa Parcela. Por favor, intente nuevamente."; break;
			case 2441: this.msg = "No se ha podido calcular el Avalúo de Mejoras de la Parcela. Por favor, intente nuevamente."; break;
			case 2442: this.msg = "No se ha podido calcular la Superficie de Mejoras de la Parcela. Por favor, intente nuevamente."; break;
			case 2443: this.msg = "No se ha podido generar el volante catastral";break;
			case 2444: this.msg = "No se ha podido recuperar la superficie de mejoras";break;

//			InformacionParcelaria
			case 2700: this.msg = "No se ha podido eliminar la parcela. Por favor, intente nuevamente.";break;
			case 2701: this.msg = "La cantidad de sub-parcelas a actualizar no es valida."; break;
			case 2702: this.msg = "Hay sub-parcelas no validas asociadas a esta parcela."; break;
			case 2703: this.msg = "Los datos necesarios para la union sub-parcelaria no son validos."; break;
			case 2704: this.msg = "La superficie de las sub-parcelas restantes no es valida."; break;
			case 2705: this.msg = "No hay Parcela o Subparcelas para el Subparcelamiento."; break;
			case 2706: this.msg = "Algunas de las sub-parcelas no es valida."; break;
			case 2707: this.msg = "Todas las sub-parcelas deben estar asociadas a la misma parcela padre."; break;
			case 2708: this.msg = "No se puede agregar una sub-parcela que ya esta registrada en la parcela."; break;
			case 2709: this.msg = "No se puede sub-parcelar una parcela que no posee titulo de propiedad."; break;

//			CodigosCatastrales
			case 2710: this.msg = "No puede eliminarse un Tipo de Construcción asignado a una Categoría."; break;
			case 2711: this.msg = "Una sub-parcela no puede poseer un plano de mensura."; break;
			case 2712: this.msg = "La sub-parcela no puede ser nula"; break;
			case 2713: this.msg = "El plano de mensura no puede ser nulo."; break;
			case 2714: this.msg = "La sub-parcela que desea mensurar no pertenece a la parcela padre."; break;
			case 2715: this.msg = "Ninguno de los campos de compatibilidad numerica puede ser nulo."; break;
			case 2716: this.msg = "No se puede mensurar una sub-parcela."; break;
			case 2717: this.msg = "No se puede agregar una Parcela ya persistida como Sub Parcela de otra."; break;
			case 2718: this.msg = "La superficie de las sub-parcelas no es valida."; break;
			case 2719: this.msg = "La superficie de la Parcela no puede ser menor a la de sus respectivas sub-parcelas."; break;
			case 2720: this.msg = "Es necesaria la parcela para realizar la busqueda."; break;
			case 2721: this.msg = "No puede eliminarse una Categoria que esté asociada a un Valor Basico de Mejora."; break;
			case 2722: this.msg = "No puede eliminarse una Categoria que esté asociada a un Regístro de Mejora."; break;
			case 2723:
			case 2724:
			case 2725:
			case 2726:
			case 2727:
			case 2728:
			case 2729:
				
//			RegistroGeografico
			case 2740:
			case 2741:
			case 2742:
			case 2743:
			case 2744:
			case 2745:
			case 2746:
			case 2747:
			case 2748:
			case 2749:
			case 2750:
			case 2751:
			case 2752:
			case 2753:
			case 2754:
			case 2755:
			case 2756:
			case 2757:
			case 2758:
			case 2759:
			case 2760:
			case 2761:
			case 2762:
			case 2763:
			case 2764:
			case 2765:
			case 2766:
			case 2767:
				
//			RegistroPropiedad
			case 2770: this.msg= "No puede eliminarse una Transacción Catastral que esté asignada a un registro de propiedad."; break;
			case 2771:
			case 2772:
			case 2773:
			case 2774:
			case 2775:
			case 2776:
			
//			AdministracionDDJJ
			case 2780:
			case 2781:
			case 2782:
			case 2783:
			case 2784:
			case 2785:
			case 2786:
			case 2787:
			case 2788:
			case 2789: 
			case 2790:
			case 2791: this.msg = "No posee Permisos suficientes para realizar la Operación. Póngase en contacto con el Administrador."; break;

			default: this.msg = "Ha ocurrido un error desconocido. Póngase en contacto con el Administrador.";
		}
	}

}
