package com.trascender.framework.exception;

public class TrascenderFrameworkException extends TrascenderException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2872775385699239306L;
	//Se agregan tantos objetos de relación como sean necesarios
	private Object objetoRelacion1;
	private Object objetoRelacion2;

	public TrascenderFrameworkException(int pCodeTrascenderException, Object pObjectoRelacion1, Object pObjectoRelacion2) {
		super(1000 + pCodeTrascenderException);
		this.objetoRelacion1 = pObjectoRelacion1;
		this.objetoRelacion2 = pObjectoRelacion2;

		this.setMsg();

	}

	public TrascenderFrameworkException(int pCodeTrascenderException) {
		super(1000 + pCodeTrascenderException);

	}
	//Ejemplo: case 1038: this.mensaje = "El Cuim y Número de Documento ya se encuentran asociados a: " + ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n";break;

	@Override
	protected void setMsg() {


		switch(this.getCodeTrascenderException()){
		//EXCEPCIONES INTERFAZ DE NEGOCIO
		//Excepciones de Municipalidad
		case 1000: this.msg = "El Área ya se encuentra registrada."; break;
		case 1001: this.msg = "El País ya se encuentra registrado."; break;
		case 1002: this.msg = "El País que desea actualizar no se encuentra registrado."; break;
		case 1003: this.msg = "La Provincia ya se encuentra registrada."; break;
		case 1004: this.msg = "La Localidad ya se encuentra registrada."; break;
		case 1005: this.msg = "La Localidad que desea actualizar no se encuentra registrada."; break;
		case 1006: this.msg = "La Localidad debe pertenecer a una Provincia."; break;
		case 1007: this.msg = "Algunos de los datos requeridos son Nulos.";break;
		case 1008: this.msg = "Algunos de los datos requeridos son Nulos.";break;
		case 1009: this.msg = "Algunos de los datos requeridos son Nulos.";break;
		case 1010: this.msg = "La municipalidad no se encuentra registrada, verifique que haya sido registrado el domicilio municipal"; break;
		case 1011: this.msg = "Ya existe una provincia con ese nombre en ese país.";break;
		case 1012: this.msg = "Ya existe una provincia con ese código en ese país.";break;
		case 1013: this.msg = "Ya existe una provincia con esa abreviatura en ese país.";break;
		case 1014: this.msg = "Ya existe un país con ese nombre."; break;
		case 1015: this.msg = "Ya existe un país con esa abreviatura."; break;
		case 1016: this.msg = "No se puede eliminar una provincia que este asociada a una Localidad."; break;
		case 1017: this.msg = "Existen provincias asociadas a este Pais";break;
		case 1018: this.msg = "La Localidad está asociada a uno o mas domicilios.";break;

		//Excepciones de Persona
		case 1020: this.msg = "La Persona Física ya se encuentra registrada."; break;
		case 1021: this.msg = "La Persona Física que desea actualizar no se encuentra registrada."; break;
		case 1022: this.msg = "El Cuit ya se encuentra registrado en otra Persona Jurídica."; break;
		case 1023: this.msg = "La Persona Jurídica debe poseer un Titular."; break;
		case 1024: this.msg = "La Persona Jurídica que desea actualizar no se encuentra registrada."; break;
		case 1025: this.msg = "La Persona Jurídica debe poseer un Titular."; break;
		case 1026: this.msg = "La Persona Física no se encuentra registrada."; break;
		case 1027: this.msg = "La Persona Jurídica no se encuentra registrada."; break;
		case 1028: this.msg = "La persona Física con el DNI que intenta ingresar ya se encuentra dada de alta."; break;
		case 1029: this.msg = "El cuim se encuentra repetido. Consulte con su administrador de sistemas";break;
		case 1030: this.msg = "Existe una persona eliminada con el mismo CUIM o DNI.";break;

		//Excepciones de rol
		case 1040: this.msg = "El Rol ya se encuentra registrado."; break;

		//Excepciones usuario
		case 1060: this.msg = "El Usuario ya se encuentra registrado."; break;
		case 1061: this.msg = "El Usuario 'root' no puede ser eliminado."; break;
		case 1062: this.msg = "El Usuario que realiza la Firma no puede ser Nulo."; break;
		case 1063: this.msg = "La Clave anterior es incorrecta."; break;
		case 1064: this.msg = "No se ha podido realizar el cambio de Clave."; break;
		case 1065: this.msg = "El Rol no está habilitado a firmar."; break;
		case 1066: this.msg = "No se ha podido generar la Firma del Usuario actual. Por favor, intente nuevamente."; break;
		case 1067: this.msg = "El usuario está eliminado. Por favor, contáctese con el administrador."; break;

		//Excepciones Parametro
		case 1990 : this.msg = "No se pudieron recuperar los Reportes. Comuníquese con el Administrador.";break;
		case 1080: this.msg = "Ya existe un Parametro con el Codigo o Nombre que intenta agregar";
		case 1081: this.msg = "Debe ingresar un ID de recurso valido antes de agregar una validaciòn."; break;
		case 1082: this.msg = "El del Id Recurso no puede ser nulo."; break;
		case 1083: this.msg = "El Numero de Orden no puede ser nulo."; break;
		case 1084: this.msg = "El Nombre Actual no puede ser nulo."; break;
		case 1085: this.msg = "El Nombre Alias no puede ser nulo."; break;
		case 1086: this.msg = "Ya existe una Configuración Recurso para ese Recurso."; break;
		case 1087: this.msg = "Ya existe un elemento con el mismo Orden."; break;
		case 1088: this.msg = "Ya existe un elemento con el mismo Nombre."; break;
		case 1089: this.msg = "Ya existe un elemento con el mismo Alias."; break;

		case 1100: this.msg = "La validación no puede ser nula."; break;
		case 1101: this.msg = "El mensaje de error no puede ser nulo."; break;
		case 1102: this.msg = "Solo se puede agregar o un Numero de Mensaje o un Mensaje especifico."; break;
		case 1103: this.msg = "No se puede agregar la validación por que ya existen elementos que no cumplen con la restricción.\n" +
				"Para solucionar el inconveniente verifique aquellas entidades que no cumplan con los requisitos de la restricción" +
				"y adaptela para que cumpla la restricción ingresada."; break;

		case 1104: this.msg = "No existe una configuración para el configurado para el Recurso al cual " +
				"se desea agregar la validación. Comuniquesé con el Administrador."; break;

				//Business Calendario
		case 1250: this.msg = "Ya hay un Calendario Municipal Activo para ese Tipo de Obligación en el mismo año."; break;
		case 1251: this.msg = "El Calendario Municipal debe tener asociado al menos 1 o mas periodos."; break;
		case 1252: this.msg = "Uno de los periodos asociados al calendario no se encuentra asociado al mismo."; break;
		//
		//EXCEPCIONES INTERFAZ DE SISTEMA
		//Excepciones de Municipalidad 
		case 1300: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 1301: this.msg = "No se ha podido actualizar los datos de la Municipalidad. Por favor, intente nuevamente."; break;
		case 1302: this.msg = "No se ha podido agregar el Área. Por favor, intente nuevamente."; break;
		case 1303: this.msg = "No se ha podido actualizar los datos del Área. Por favor, intente nuevamente."; break;
		case 1304: this.msg = "No se ha podido eliminar el Área. Por favor, intente nuevamente."; break;
		case 1305: this.msg = "No se ha podido restaurar el Área. Por favor, intente nuevamente."; break;
		case 1306: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 1307: this.msg = "No se ha podido agregar el País. Por favor, intente nuevamente."; break;
		case 1308: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 1309: this.msg = "No se ha podido actualizar los datos del País. Por favor, intente nuevamente."; break;
		case 1310: this.msg = "No se ha podido eliminar el País. Por favor, intente nuevamente."; break;
		case 1311: this.msg = "No se ha podido agregar la Localidad. Por favor, intente nuevamente."; break;
		case 1312: this.msg = "No se ha podido actualizar los datos de la Localidad. Por favor, intente nuevamente."; break;
		case 1313: this.msg = "No se ha podido eliminar la Localidad. Por favor, intente nuevamente."; break;
		case 1314: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 1315: this.msg = "No se ha podido agregar la Provincia. Por favor, intente nuevamente."; break;
		case 1316: this.msg = "No se ha podido actualizar los datos de la Provincia. Por favor, intente nuevamente."; break;
		case 1317: this.msg = "No se ha podido eliminar la Provincia. Por favor, intente nuevamente."; break;
		case 1318: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 1319: this.msg = "No se ha podido registrar el Día Feriado. Por favor, intente nuevamente.";break;
		case 1320: this.msg = "No se ha podido actualizar los datos del Día Feriado. Por favor, intente nuevamente.";break;
		case 1321: this.msg = "No se ha podido eliminar el Día Feriado. Por favor, intente nuevamente.";break;
		case 1322: this.msg = "No se ha podido recuperar el Listado de Días Feriados. Por favor, intente nuevamente.";break;
		case 1323: this.msg = "No se ha podido agregar el digesto municipal. Por favor, intente nuevamente.";break;
		case 1324: this.msg = "No se han podido actualizar los datos del digesto municipal. Por favor, intente nuevamente.";break;
		case 1325: this.msg = "No se ha podido eliminar el digesto muncipal. Por favor, intente nuevamente.";break;
		case 1326: this.msg = "No se ha podido recuperar el Listado de Digestos municipales. Por favor, intente nuevamente.";break;
		case 1327: this.msg = "No se ha podido recuperar el digesto municipal. Por favor intente nuevamente."; break; 
		case 1328: this.msg = "No puede eliminar un Digesto Municipal que se encuentra asociado a otro Digesto."; break;
		case 1329: this.msg = "Existen mas de un Digesto Municipal asociado al mismo nombre."; break;
		case 1330: this.msg = "El código postal debe poseer solo cuatro dígitos.";break;
		case 1331: this.msg = "El código postal solo puede poseer caracteres numéricos.";break;
		case 1332: this.msg = "Ya hay un dia feriado con esa fecha. En el respectivo año.";break;
		case 1333: this.msg = "Ya hay un dia feriado con ese nombre. En el respectivo año.";break;


		//Excepciones de PersonaFisica
		case 1340: this.msg = "No se ha podido agregar la Persona Física. Por favor, intente nuevamente."; break;
		case 1341: this.msg = "No se ha podido actualizar los datos de la Persona Física. Por favor, intente nuevamente."; break;
		case 1342: this.msg = "No se ha podido eliminar la Persona Física. Por favor, intente nuevamente."; break;
		case 1343: this.msg = "No se ha podido restaurar la Persona Física. Por favor, intente nuevamente."; break;
		case 1344: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;

		//Excepciones de Persona Jur�dica
		case 1360: this.msg = "No se ha podido agregar la Persona Jurídica. Por favor, intente nuevamente."; break;
		case 1361: this.msg = "No se ha podido actualizar los datos de la Persona Jurídica. Por favor, intente nuevamente."; break;
		case 1362: this.msg = "No se ha podido eliminar la Persona Jurídica. Por favor, intente nuevamente."; break;
		case 1363: this.msg = "No se ha podido restaurar la Persona Jurídica. Por favor, intente nuevamente."; break;
		case 1364: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 1365: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;

		//Excepciones de Rol
		case 1380: this.msg = "No se ha podido agregar el Rol. Por favor, intente nuevamente."; break;
		case 1381: this.msg = "No se ha podido actualizar los datos del Rol. Por favor, intente nuevamente."; break;
		case 1382: this.msg = "No se ha podido eliminar el Rol. Por favor, intente nuevamente."; break;
		case 1383: this.msg = "No se ha podido restaurar el Rol. Por favor, intente nuevamente."; break;
		case 1384: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;

		//SystemUsuario
		case 1400: this.msg = "No se ha podido agregar el Usuario. Por favor, intente nuevamente."; break;
		case 1401: this.msg = "No se ha podido actualizar los datos del Usuario. Por favor, intente nuevamente."; break;
		case 1402: this.msg = "No se ha podido eliminar el Usuario. Por favor, intente nuevamente."; break;
		case 1403: this.msg = "No se ha podido restaurar el Usuario. Por favor, intente nuevamente."; break;
		case 1404: this.msg = "No se ha podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 1405: this.msg = "Nombre de Usuario o Contraseña inválidos. Por favor, intente nuevamente."; break;
		case 1406: this.msg = "No se ha podido recuperar los datos de los Usuario. Por favor, intente nuevamente."; break;
		case 1407: this.msg = "No se ha podido recuperar los datos de los Recursos. Por favor, intente nuevamente.";break;
		case 1408: this.msg = "No se ha podido realizar el Login. Comuníquese con el Administrador.";break;

		//SystemLogSeguridad
		case 1450: this.msg = "No se ha podido registrar el Log de Seguridad. Comuníquese con el Administrador."; break;

		//System

		//Util
		case 1500: this.msg="La Fecha de Vencimiento no puede ser superior a la Fecha de Emisión.";break;

		//Excepciones de Municipalidad//
		case 1501: this.msg = "No se puede eliminar una Secretaría que se encuentre asociado a un Área."; break;

		//Excepciones de Contrato
		case 1700: this.msg="La Fecha de Inicio del Contrato no puede ser nula.";break;
		case 1701: this.msg="La Fecha de Finalización del Contrato no puede ser nula.";break;
		case 1702: this.msg="El Precio de la cuota no puede ser Nulo o 0.";break; 
		case 1703: this.msg="La Cantidad de cuotas del contrato no puede ser Nulo o 0."; break;
		case 1704: this.msg="La Fecha de Inicio del Contrato no puede ser mayor a la Fecha de Finalización.";break;
		case 1705: this.msg="No se ha podido agregar el Contrato. Por favor, intente nuevamente."; break;
		case 1706: this.msg="No se ha podido actualizar el Contrato. Por favor, intente nuevamente."; break;
		case 1707: this.msg="No se ha podido eliminar el Contrato. Por favor, intente nuevamente."; break;
		case 1708: this.msg="No se ha podido recuperar el Contrato. Por favor, intente nuevamente."; break;
		case 1709: this.msg= "El Contrato que intenta agregar ya se encuentra Registrado";break;
		case 1710: this.msg= "El contrato no puede eliminarse ya que posee facturas asociadas";break;
		case 1711: this.msg = "El contrato debe poseer una persona."; break;
		case 1712: this.msg = "No se puede eliminar un contrato que esté asociado a una Factura Contrato."; break;
		case 1713:
		case 1714:
		case 1715:
		case 1716:
		case 1717:
		case 1718:
		case 1719:
		case 1720:
		case 1721:
		case 1722:
		case 1723:
		case 1724:
		case 1726:
		case 1727:
		case 1728:
		case 1729:
		case 1730:
		case 1731:

			//Excepciones de Persona Física
		case 1740: 
		case 1741:
		case 1742:
		case 1743:
		case 1744:

			//Excepciones de Persona Jurídica
		case 1760:
		case 1761:
		case 1762:
		case 1763:
		case 1764:
		case 1765:

			//Excepciones de rol
		case 1780:
		case 1781:
		case 1782:
		case 1783:
		case 1784:

			//Excepciones de usuario
		case 1800:
		case 1801:
		case 1802:
		case 1803:
		case 1804: 
		case 1805: this.msg = "No posee Permisos suficientes para realizar la Operación. Póngase en contacto con el Administrador."; break;

		//Excepciones de Digesto Municipal
		case 1200: this.msg = "El Tipo de Digesto Municipal no puede ser nulo."; break;
		case 1201: this.msg = "El Estado del Digesto Municipal no puede ser nulo."; break;
		case 1202: this.msg = "Debe completar el Nombre del Digesto Municipal."; break;
		case 1203: this.msg = "La Fecha de vigencia del Digesto Municipal no puede ser nula."; break;
		case 1204: this.msg = "La Fecha del Digesto Municipal no puede ser superior a la actual."; break;
		case 1205: this.msg = "La Descripción no puede ser nula. "; break;
		case 1206: this.msg = "Ya existe un Digesto de este tipo con ese número.";break;
		case 1208: this.msg = "Error al actualizar el Digesto Municipal, Ya existe un Digesto con ese nombre.";break;
		case 1209: this.msg = "El tema del digesto no puede ser nulo";break;
		case 1210: this.msg = "El Digesto Municipal esta siendo usado en otro lugar y no puede eliminarse.";break;

		//Excepciones de Temas
		case 1600: this.msg = "No se puede eliminar un Tema que se encuentre asociado a Digestos."; break;
		case 1601: this.msg = "No se ha podido registrar el Tema. Por favor, intente nuevamente."; break;
		case 1602: this.msg = "No se ha podido actualizar el Tema. Por favor, intente nuevamente."; break;
		case 1603: this.msg = "No se ha podido eliminar el Tema. Por favor, intente nuevamente."; break;
		case 1604: this.msg = "No se ha podido recuperar el Tema. Póngase en contacto con el Administrador."; break;
		case 1605: this.msg = "El Tema que intenta agregar ya se encuentra registrado";break;
		case 1606: this.msg = "No se ha podido recuperar la sección. Póngase en contacto con el Administrador."; break;
		case 1607: this.msg = "No se ha podido recuperar el grupo. Póngase en contacto con el Administrador."; break;

		//Excepciones SecurityMgr 
		case 1820: this.msg = "Su registro ha caducado. Por favor, ingrese nuevamente al Sistema."; break;
		case 1821: this.msg = "El Usuario no se encuentra logueado. Posiblemente su Conexión haya caducado.";break;

		//Excepciones de Permiso (Persistent)
		case 1840: this.msg = "Los Permisos no pueden mezclarse. Los Recursos a los que hacen referencia no son iguales.";

		//Calendario
		case 1850: this.msg = "No se ha podido Agregar el Calendario. Por favor, intente nuevamente. "; break;
		case 1851: this.msg = "No se ha podido Actualizar el Calendario. Por favor, intente nuevamente. "; break;
		case 1852: this.msg = "No se ha podido recuperar el listado de Calendarios. Por favor, intente nuevamente. "; break;
		case 1853: this.msg = "No se ha podido recuperar el Periodo. Por favor, intente nuevamente. "; break;
		case 1854: this.msg = "No se ha podido recuperar la Cuota. Por favor, intente nuevamente. "; break;

		//Excepciones de Persona

		case 1900: this.msg ="Error al actualizar El domicilio. No se aceptan valores nulos."; break;
		case 1920: this.msg = "No se ha podido registrar el Parametro. Por favor, intente nuevamente.";break;
		case 1921: this.msg = "No se ha podido actualizar el Parametro. Por favor, intente nuevamente.";break;
		case 1922: this.msg = "No se ha podido eliminar el Parametro. Por favor, intente nuevamente.";break;
		case 1923: this.msg = "No se ha podido recuperar el Parametro. Por favor, intente nuevamente.";break;

		//Excepciones en Atributos Dinamicos

		case 1940 : this.msg = "No se puedo agregar el Atributo Dinamico. Comuníquese con el Administrador.";break;
		case 1941 : this.msg = "No se puedo modificar el Atributo Dinamico. Comuníquese con el Administrador.";break;
		case 1942 : this.msg = "No se puedo eliminar el Atributo Dinamico. Comuníquese con el Administrador.";break;
		case 1943 : this.msg = "No se pudieron recuperar los Atributos Dinamicos. Comuníquese con el Administrador.";break;
		case 1944 : this.msg = "El valor asignado al atributo no existe como opcion";break;

		//Excepciones de Pagos
		case 1980 : this.msg = "Esa persona ya ha realizado un pago en esta fecha";break;
		case 1981: this.msg = "No posee Permisos suficientes para realizar la Operación. Póngase en contacto con el Administrador."; break;
		case 1982: this.msg = "No se ha podido agregar el pago. Por favor, intente nuevamente."; break;
		case 1983: this.msg = "No se ha podido actualizar los datos del pago. Por favor, intente nuevamente."; break;
		case 1984: this.msg = "No se ha podido eliminar el País. Por favor, intente nuevamente."; break;
		case 1985: this.msg = "No se ha podido recuperar el Listado de Digestos municipales. Por favor, intente nuevamente.";break;
		case 1999: this.msg = "Un error inesperado ha ocurrido al realizar la operación. Póngase en contacto con el Administrador."; break; 


		default: this.msg = "Error Desconocido. Póngase en contacto con el Administrador.";



		}

	}

	public Object getObjetoRelacion1(){
		return this.objetoRelacion1;
	}

	public Object getObjetoRelacion2(){
		return this.objetoRelacion2;
	}




}
