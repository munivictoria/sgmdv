package com.trascender.habilitaciones.exception;

import java.util.List;

import com.trascender.framework.exception.TrascenderException;

public class HabilitacionesException extends TrascenderException {

	public HabilitacionesException(int pCodeSystemException) {
		super(4000+pCodeSystemException);
	}
	
	public HabilitacionesException (int pCodeSystemException, Object pObjectoRelacion1) {
		super(4000 + pCodeSystemException);
		this.objetoRelacion1 = pObjectoRelacion1;
		
		this.setMsg();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2504070673365252674L;
	private Object objetoRelacion1;
	
	@Override
	protected void setMsg() {
		switch(this.getCodeTrascenderException()){
		//Clase persistente
		case 4150: this.msg="No se ha podido calcular la base de consumo, verifique que haya sido cargada la tabla de BaseConsumo";break; 


		//BusinessPlantillaObligacion
		case 4000: this.msg="Sólo puede existir una Plantilla de Obligación de ese Tipo con ese nombre.";break;
		case 4001: this.msg="Algunos de los datos requeridos son Nulos o están vacíos."; break;
		case 4002: this.msg="Las Plantillas de Documentos Habilitantes Compuestos deben tener Documentos hijos."; break;
		case 4003: this.msg="Las Plantillas de Permisos deben tener un Rol asociado."; break;
		case 4004: this.msg="Existe otra Plantilla de Obligación registrada con el mismo nombre y Tipo."; break;
		case 4005: this.msg="La Plantilla de Obligación no puede ser Nula."; break;
		case 4006: this.msg="No se ha encontrado ninguna plantilla de obligación."; break;
		case 4007: this.msg="No se ha podido generar un numero de tramite. Por fabor intente nuevamente."; break;
		case 4008: this.msg = "No se pudo encontrar la Plantilla Obligacion necesaria. Por fabor intente nuevamente."; break;

		//BusinessObligacion
		case 4020: this.msg="El Documento Habilitante Compuesto debe tener al menos un Documento hijo."; break;
		case 4021: this.msg="El Usuario actual no tiene Permiso para firmar el Documento Habilitante."; break;
		case 4022: this.msg="Alguna de las Libretas Sanitarias debe pertenecer a la misma Persona que suscribe a la Obligación."; break;
		case 4023: this.msg="El Número de Libreta Sanitaria debe ser único."; break; 
		case 4024: this.msg="Los Números de Cuenta y Subcuenta deben ser únicos."; break;
		case 4025: this.msg="El código de medidor debe ser único."; break;
		case 4026: this.msg="Las personas juridicas no pueden tener Obligaciones SHPS asociadas"; break;
		case 4027: this.msg="La parcela ya posee una obligación de ese tipo."; break;
		case 4028: this.msg = "No se ha podido agregar el rubro por no exite un tipo obligacion SHPS."; break;

		//BusinessDocumentoSHPS
		case 4040: this.msg="Algunos de los datos requeridos del Documento son Nulos."; break;
		case 4041: this.msg="Algunos de los datos requeridos del Local Comercial son Nulos."; break;
		case 4042: this.msg="Algunos de los datos requeridos del Local Comercial son Nulos."; break;
		case 4043: this.msg="Algunos de los datos requeridos de la Inspección Comercial son Nulos."; break;
		case 4044: this.msg="Algunos de los datos requeridos de la Inspección Comercial son Nulos."; break;
		case 4045: this.msg="Algunos de los datos requeridos de la Inspección Vehicular son Nulos."; break;
		case 4046: this.msg="Algunos de los datos requeridos de la Inspección Vehicular son Nulos."; break;
		case 4047: this.msg="Ya hay una Libreta Sanitaria con el mismo número."; break;
		case 4048: this.msg="Algunos de los datos requeridos de la Libreta Sanitaria son Nulos."; break;
		case 4049: this.msg="Otra Libreta Sanitaria con el mismo Número ya se encuentra registrada."; break;
		case 4050: this.msg="Algunos de los datos requeridos de la Libreta Sanitaria son Nulos."; break;
		case 4051: this.msg="Algunos de los datos requeridos del Transporte Vehicular son Nulos."; break;
		case 4052: this.msg="Algunos de los datos requeridos del Inspector son Nulos."; break;
		case 4053: this.msg="Algunos de los datos requeridos del Inspector son Nulos."; break;
		case 4054: this.msg="El Inspector ha realizado Inspecciones, por lo que no puede ser eliminado."; break;
		case 4055: this.msg="Algunos de los datos requeridos del Transporte Vehicular son Nulos."; break;
		case 4056: this.msg="Algunos de los datos requeridos de la Clausura de SHPS son Nulos."; break;
		case 4057: this.msg="Algunos de los datos requeridos de la Clausura de SHPS son Nulos."; break;
		case 4058: this.msg="La Clausura debe pertenecer a alguna Obligación."; break;
		case 4059: this.msg="Ya existe un Local Comercial con el mismo número de inscripción."; break;
		case 4060: this.msg="Ya existe un Transporte Vehicular con el mismo número de inscripción."; break;
		case 4061: this.msg="No se puede eliminar un Local Comercial que esté asociado a una Obligación SHPS."; break;
		case 4062: this.msg= "Ya exite otra Libreta Sanitaria con la misma persona."; break;
		case 4063: this.msg = "Ya existe un Local Comercial en esa misma parcela."; break;
		case 4064: this.msg = "Debe seleccionar una parcela valida para el local."; break;

		//BusinessTipoAlicuota
		case 4069: this.msg="No se puede eliminar un Servicio que esté asociado a una Obligacion."; break; 
		case 4070: this.msg="Algunos de los datos requeridos del Rubro del Código Tributario son Nulos."; break;
		case 4071: this.msg="Algunos de los datos requeridos del Rubro del Código Tributario son Nulos."; break;
		case 4072: this.msg="Otro Servicio con el mismo Código ya se encuentra registrado. "; break;
		case 4073: this.msg="Algunos de los datos requeridos del Servicio de Obras y Servicios Públicos son Nulos."; break;
		case 4074: this.msg="Otro Servicio con el mismo Código ya se encuentra registrado."; break;
		case 4075: this.msg="Alguno de los valores requeridos del servicio de obras y servicios públicos es nulo. Verifique los datos ingresados"; break;
		case 4076: this.msg="Si el Servicio es Medido debe poseer una Base de Consumo."; break;
		case 4077: this.msg="Si el Servicio es Medido debe poseer una Unidad de Medida."; break;
		case 4078: this.msg="Si el Servicio es medido debe poseer un Valor por Excedente."; break;
		case 4079: this.msg="Otro Rubro con el mismo Código ya se encuentra registrado."; break;

		//BusinessDocumentoOSP			
		case 4080: this.msg="Algunos de los datos requeridos del Documento de Obras y Servicios Públicos son Nulos.";break;
		case 4081: this.msg="Algunos de los datos requeridos del Documento de Obras y Servicios Públicos son Nulos.";break;
		case 4082: this.msg="Todos los valores del consumo básico deben ser mayores o iguales a cero";break;
		case 4083: this.msg="El valor de consumo básico debe representar todo el rango posible de consumos";break;
		case 4084: this.msg="El consumo básico no puede ser nulo";break;
		case 4085: this.msg="El valor mínimo de superficie de mejoras debe ser una unidad mayor al máximo de superficie de mejoras anterior.";break;
		case 4086: this.msg="No se ha podido agregar el consumo básico.";break;
		case 4087: this.msg="No se ha podido actualizar los datos del consumo básico.";break;
		case 4088: this.msg="No se ha podido eliminar el consumo básico.";break;
		case 4089: this.msg="No se puede eliminar un Codigo de Servicio OSP que esté asociado a una Obligación."; break;
		case 4104: this.msg="Ya existe una base de consumo en la cual este contenida dicha superficie de mejora."; break;


		//BusinessDocumentoPlanObra			
		case 4090: this.msg="Algunos de los datos requeridos de la Obra son Nulos.";break;
		case 4091: this.msg="Algunos de los datos requeridos de la Obra son Nulos.";break;
		case 4092: this.msg="Algunos de los datos requeridos del Documento del Plan de Obra son Nulos.";break;
		case 4093: this.msg="Algunos de los datos requeridos del Documento del Plan de Obra son Nulos.";break;
		case 4094: this.msg="Algunos de los datos requeridos del Plan de Cuenta son Nulos.";break;
		case 4095: this.msg="Algunos de los datos requeridos del Plan de Cuenta son Nulos.";break;
		case 4096: this.msg="Esta Obra ya ha sido Liquidada y no puede Modificarse."; break;
		case 4097: this.msg="No se puede eliminar una obra que este asociada a una Obligación.";break;
		//case 4097: this.msg=
		case 4098: this.msg="La obra que intenta actualizar es inválida, por favor verifique"; break;
		case 4099: this.msg="El número de la obra ya se encuentra registrado, por favor verifique"; break;
		case 4103: this.msg="No se puede eliminar un plan cuenta obra que este asociado a una obra."; break;

		//BusinessDocumentoTGI
		case 4100: this.msg="Algunos de los datos requeridos del Documento de Tasa General Inmobiliaria son Nulos.";break;
		case 4101: this.msg="Algunos de los datos requeridos del Documento de Tasa General Inmobiliaria son Nulos."; break;
		case 4102: this.msg="La Parcela asociada a la Obligación TGI está asignada otra Obligación.";break;

		//BusinessTipoTasa
		case 4110: this.msg="El Nombre de la Variable debe ser único."; break;
		case 4111: this.msg="La Fórmula es inválida."; break;
		case 4112: this.msg="No se acepta el estado '"+com.trascender.habilitaciones.recurso.persistent.TipoTasa.Estado.INACTIVA+"' por existir múltiples instancias de esta Fórmula de Cálculo. Utilice otro Método de Búsqueda.";break;
		case 4113: this.msg="Sólo las Fórmulas de Cálculo en estado 'En Espera' pueden ser modificadas. Intente agregar una Fórmula de Cálculo nueva."; break;
		case 4114: this.msg="Las Tasas deben pasar el Proceso de Autorización.";break;
		case 4115: this.msg="Error en condición del Modificador " + objetoRelacion1;break;
		case 4116: this.msg="La Fórmula de Cálculo de alguna de las Condiciones de Vencimientos es inválida.";break;
		case 4117: this.msg="Ha ocurrido un Error al parsear la Fórmula de Cálculo. Verifique que todas las Variables posean un valor válido.";break;
		case 4118: this.msg ="La Zonificación seleccionada no posee Zonas asociadas."; break; 
		case 4119: this.msg="El tipo de tasa anterior no ha podido ser desactivada.";break;
		case 4120: this.msg="El tipo de tasa no ha podido ser activada."; break;
		case 4121: this.msg = "El parámetro grupo zona no pudo obtener la zona a la que pertenece la parcela."; break;
		case 4122: this.msg = "La fórmula del interés posee errores. Verifique la composición de la fórmula de la misma"; break;
		case 4123: this.msg = "La fórmula del recargo posee errores. Verifique la composición de la misma."; break;
		case 4124: this.msg = "Solo se pueden eliminar formulas que esten en estado -EN_ESPERA-."; break;
		case 4125: this.msg = "Ya existe un Plan por defecto para este Tipo de Obligación."; break;

		//BusinessTransito
		case 4200: this.msg = "No se puede eliminar un Vehículo que esté asociado a un Transporte Vehicular.";break;
		case 4201: this.msg = "No se puede eliminar un Vehículo que esté asociado a una Obligación SHPS.";break;
		
		// business Periodo
		case 4250: this.msg = "No se puede realizar la busqueda de un Periodo sin indicar a que Tipo Obligación Pertenece."; break;
		case 4251: this.msg = "El Calendario Municipal no puede ser nulo."; break;
		case 4252: this.msg = "El Calendario Municipal debe estar asociado a un Tipo Obligación."; break;
		case 4253: this.msg = "Ya existe un Calendario Municipal para ese Tipo Obligación para el mismo año y con la misma Periodicidad."; break;
		case 4254: this.msg = "El Calendario Municipal debe tener asociado al menos un Periodo."; break;
		case 4255: this.msg = "No se puede modificar un Calendario que esté asociado a una Liquidación."; break;

		//System
		//SystemRegistroAlicuota
		case 4300: this.msg="No se ha podido agregar el Rubro del Código Tributario. Por favor, intente nuevamente."; break;
		case 4301: this.msg="No se ha podido actualizar los datos del Rubro del Código Tributario. Por favor, intente nuevamente."; break;
		case 4302: this.msg="No se ha podido eliminar el Rubro del Código Tributario. Por favor, intente nuevamente."; break;
		case 4303: this.msg="No se ha podido recuperar el Listado de Registros de Alícuota. Por favor, intente nuevamente."; break;
		case 4304: this.msg="No se ha podido recuperar el Rubro de Código Tributario. Por favor, intente nuevamente."; break;

		//SystemBromatología
		case 4320: this.msg="No se ha podido agregar la Libreta Sanitaria. Por favor, intente nuevamente."; break;
		case 4321: this.msg="No se ha podido actualizar los datos de la Libreta Sanitaria. Por favor, intente nuevamente."; break;
		case 4322: this.msg="No se ha podido recuperar el Listado de Libretas Sanitarias. Por favor, intente nuevamente."; break;
		case 4323: this.msg="No se ha podido eliminar la Libreta Sanitaria. Por favor, intente nuevamente."; break;
		case 4324: this.msg="No se ha podido agregar la Inspección. Por favor, intente nuevamente."; break;
		case 4325: this.msg="No se ha podido actualizar los datos de la Inspección. Por favor, intente nuevamente."; break;
		case 4326: this.msg="No se ha podido eliminar la Inspección. Por favor, intente nuevamente."; break;
		case 4327: this.msg="No se ha podido recuperar el Listado de Inspecciones Vehiculares. Por favor, intente nuevamente."; break;
		case 4328: this.msg="No se ha podido recuperar el Listado de Inspecciones Comerciales. Por favor, intente nuevamente."; break;
		case 4329: this.msg="No se ha podido agregar el Local Comercial. Por favor, intente nuevamente."; break;
		case 4330: this.msg="No se ha podido actualizar el Local Comercial. Por favor, intente nuevamente."; break;
		case 4331: this.msg="No se ha podido eliminar el Local Comercial. Por favor, intente nuevamente."; break;
		case 4332: this.msg="No se ha podido recuperar el Listado de Locales Comerciales. Por favor, intente nuevamente."; break;
		case 4333: this.msg="No se ha podido agregar el Transporte Vehicular. Por favor, intente nuevamente."; break;
		case 4334: this.msg="No se ha podido actualizar el Transporte Vehicular. Por favor, intente nuevamente."; break;
		case 4335: this.msg="No se ha podido eliminar el Transporte Vehicular. Por favor, intente nuevamente."; break;
		case 4336: this.msg="No se ha podido recuperar el Transporte Vehicular. Por favor, intente nuevamente."; break;
		case 4337: this.msg="No se ha podido recuperar el Listado de Transportes Vehiculares. Por favor, intente nuevamente."; break;
		case 4338: this.msg="No se ha podido recuperar la Libreta Sanitaria. Por favor, intente nuevamente."; break;
		case 4339: this.msg="No se ha podido recuperar el Documento SHPS. Por favor, intente nuevamente."; break;
		case 4340: this.msg="No se ha podido agregar la Clausura al Documento de SHPS. Por favor, intente nuevamente."; break;
		case 4341: this.msg="No se ha podido actualizar la Clausura. Por favor, intente nuevamente."; break;
		case 4342: this.msg="No se ha podido recuperar el Listado de Clausuras. Por favor, intente nuevamente."; break;
		case 4343: this.msg="No se ha podido recuperar el Transporte Vehicular. Por favor, intente nuevamente."; break;
		case 4344: this.msg="No se ha podido recuperar el Local Comercial. Por favor, intente nuevamente."; break;
		case 4345: this.msg="No se ha podido actualizar los datos de la Obligación. Por favor, intente nuevamente."; break;
		case 4346: this.msg="No se ha podido firmar la Clausura. Por favor, intente nuevamente.";break;
		case 4347: this.msg="No se ha podido recuperar el Listado de las Clausuras. Por favor, intente nuevamente.";break;
		case 4348: this.msg="No se ha podido recuperar el Listado de Documentos SHPS. Por favor, intente nuevamente."; break;

		//SystemInspector
		case 4360: this.msg="No se ha podido agregar el Inspector. Por favor, intente nuevamente."; break;
		case 4361: this.msg="No se ha podido actualizar el Inspector. Por favor, intente nuevamente."; break;
		case 4362: this.msg="No se ha podido eliminar el Inspector. Por favor, intente nuevamente."; break;
		case 4363: this.msg="No se ha podido recuperar el Listado de Inspectores. Por favor, intente nuevamente."; break;
		case 4364: this.msg="Ya hay una inspección para ese local, en esa fecha.";break;
		case 4365: this.msg="El inspector no puede ser nulo."; break;

		//SystemObligacion
		case 4370: this.msg="La Persona a la que pertenece la Obligación no puede ser Nula."; break;
		case 4371: this.msg="No se ha podido agregar la Obligación. Por favor, intente nuevamente."; break;
		case 4372: this.msg="El Domicilio Postal en la Obligación no puede ser Nulo."; break;
		case 4373: this.msg="No se ha podido actualizar los datos de la Obligación. Por favor, intente nuevamente."; break;
		case 4374: this.msg="No se ha podido recuperar el Listado de las Obligaciones. Por favor, intente nuevamente."; break;
		case 4375: this.msg="No se ha podido recuperar el Listado de Documento Habilitante Especializado. Por favor, intente nuevamente."; break;
		case 4376: this.msg="No se ha podido firmar el Documento Habilitante. Por favor, intente nuevamente."; break;
		case 4377: this.msg="No se ha podido recuperar el Listado de Permisos a Firmar. Por favor, intente nuevamente."; break;
		case 4378: this.msg="No se ha podido recuperar la Obligación. Por favor, intente nuevamente."; break;
		case 4379: this.msg="No se ha podido recuperar el Tipo de Obligación desde la Obligación. Por favor, intente nuevamente."; break;
		case 4380: this.msg="No se ha podido recuperar el Listado de Obligaciones de Tasa General Inmobiliaria. Por favor, intente nuevamente."; break;
		case 4381: this.msg="No se ha podido recuperar el Listado de Obligaciones de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4382: this.msg="No se ha podido recuperar el Listado de Obligaciones de Plan de Financiación de Obras. Por favor, intente nuevamente."; break;

		//SystemPlantillaObligacion
		case 4390: this.msg="No se ha podido agregar la Plantilla de Obligación. Por favor, intente nuevamente."; break;
		case 4391: this.msg="No se ha podido actualizar los datos de la Plantilla de Obligación. Por favor, intente nuevamente."; break;
		case 4392: this.msg="No se ha podido recuperar el Listado de Plantillas de Obligaciones. Por favor, intente nuevamente."; break;
		case 4393: this.msg="No se ha podido eliminar la Plantilla de Obligación. Por favor, intente nuevamente."; break;
		case 4394: this.msg="No se ha podido generar la Obligación a partir de la Plantilla. Por favor, intente nuevamente."; break;
		case 4395: this.msg="No se ha podido recuperar el Rol de la Plantilla permiso. Por favor, intente nuevamente."; break;
		case 4396: this.msg="No se ha podido recuperar la Plantilla de Obligaciones. Por favor, intente nuevamente."; break;

		//SystemDocumentoAutomotor
		case 4400: this.msg="No se ha podido agregar el Vehículo. Por favor, intente nuevamente."; break;
		case 4401: this.msg="No se ha podido actualizar el Vehículo. Por favor, intente nuevamente."; break;
		case 4402: this.msg="No se ha podido eliminar el Vehículo. Por favor, intente nuevamente."; break;
		case 4403: this.msg="No se ha podido recuperar el Listado de Vehículos. Por favor, intente nuevamente."; break;
		case 4404: this.msg="No se ha podido recuperar el Vehículo. Por favor, intente nuevamente."; break;
		case 4405: this.msg="No se ha podido actualizar el Documento Automotor. Por favor, intente nuevamente."; break;
		case 4406: this.msg="No se ha podido eliminar el Documento de Automotor. Por favor, intente nuevamente."; break;
		case 4407: this.msg="No se ha podido recuperar el Listado de Documentos de Automotor. Por favor, intente nuevamente."; break;
		case 4408: this.msg="No se ha podido recuperar el Documento de Automotor. Por favor, intente nuevamente."; break;
		case 4399: this.msg="El documento de Valuaciones Acara no tiene un formato valido.";break;
		case 4398: this.msg="Ya existe una Obligación Automor para este Vehiculo.";break; 
		case 4409: this.msg="No se ha podido agregar el Documento Automotor. Por favor, intente nuevamente."; break;
		case 4397: this.msg="El titulo de propiedad no es valido.";break;
		case 4389: this.msg="El titulo de propiedad debe tener al menos un propietario.";break;
		
		//SystemdocumentoOSP
		case 4410: this.msg="No se ha podido agregar el Documento de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4411: this.msg="No se ha podido actualizar los datos del Documento de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4412: this.msg="No se ha podido eliminar el Documento de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4413: this.msg="No se ha podido recuperar el Listado de Documentos de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4414: this.msg="No se ha podido recuperar el Documento de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4415: this.msg="No se ha podido agregar el Servicio de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4416: this.msg="No se ha podido actualizar el Servicio de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4417: this.msg="No se ha podido eliminar el Servicio de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4418: this.msg="No se ha podido recuperar el Listado de Servicios de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4419: this.msg="No se ha podido recuperar el Servicio de Obras y Servicios Públicos. Por favor, intente nuevamente."; break;
		case 4429: this.msg="No se ha podido recuperar el consumo basico. por favor, intente nuevamente."; break;
		//SystemDocumentoTGI
		case 4420: this.msg="No se ha podido agregar el Documento de Tasa General Inmobiliaria. Por favor, intente nuevamente."; break;
		case 4421: this.msg="No se ha podido actualizar el Documento Tasa General Inmobiliaria. Por favor, intente nuevamente."; break;
		case 4422: this.msg="No se ha podido eliminar el Documento de Tasa General Inmobiliaria. Por favor, intente nuevamente."; break;
		case 4423: this.msg="No se ha podido recuperar el Listado de Documentos de Tasa General Inmobiliaria. Por favor, intente nuevamente."; break;
		case 4424: this.msg="No se ha podido recuperar el Documento de Tasa General Inmobiliaria. Por favor, intente nuevamente."; break;

		//SystemPlanFinanciacionObra
		case 4430: this.msg="No se ha podido agregar la Obra. Por favor, intente nuevamente."; break;
		case 4431: this.msg="No se ha podido actualizar la Obra. Por favor, intente nuevamente."; break;
		case 4432: this.msg="No se ha podido eliminar la Obra. Por favor, intente nuevamente."; break;
		case 4433: this.msg="No se ha podido recuperar el Listado de Obras. Por favor, intente nuevamente."; break;
		case 4434: this.msg="No se ha podido agregar el Plan de Cuenta. Por favor, intente nuevamente."; break;
		case 4435: this.msg="No se ha podido actualizar el Plan de Cuenta. Por favor, intente nuevamente."; break;
		case 4436: this.msg="No se ha podido eliminar el Plan de Cuenta. Por favor, intente nuevamente."; break;
		case 4437: this.msg="No se ha podido recuperar el Listado de Planes de Cuenta. Por favor, intente nuevamente."; break;
		case 4438: this.msg="No se ha podido generar las Obligaciones a partir de la Obra. Por favor, intente nuevamente."; break;
		case 4439: this.msg="No se ha podido recuperar el Documento de Plan de Financiación de Obras. Por favor, intente nuevamente."; break;
		case 4440: this.msg="No se ha podido recuperar el Listado de Documentos de Planes de Financiación de Obras. Por favor, intente nuevamente."; break;
		case 4441: this.msg="No se ha podido recuperar la Obra. Por favor, intente nuevamente."; break;
		case 4442: this.msg="No se ha podido actualizar el Plan de Cuenta. Por favor, intente nuevamente."; break;
		case 4443: this.msg="No se ha podido actualizar el Documento de Plan de Financiación de Obras. Por favor, intente nuevamente."; break;
		case 4444: this.msg="No se ha podido recuperar el Listado de Cuadras Afectadas. Por favor, intente nuevamente.";break;

		//SystemDocumentoCementerio
		case 4445: this.msg="No se ha podido agregar el Documento Cementerio. Por favor, intente nuevamente."; break;
		case 4446: this.msg="No se ha podido actualizar el Documento Cementerio. Por favor, intente nuevamente."; break;
		case 4447: this.msg="No se ha podido eliminar el Documento Cementerio. Por favor, intente nuevamente."; break;
		case 4448: this.msg="No se ha podido recuperar el Listado de Documentos Cementerio. Por favor, intente nuevamente."; break;
		case 4449: this.msg="No se ha podido recuperar el Documento Cementerio. Por favor, intente nuevamente."; break;
		case 4450: this.msg="No se ha podido agregar la Parcela Cementerio. Por favor, intente nuevamente."; break;
		case 4451: this.msg="No se ha podido actualizar la Parcela Cementerio. Por favor, intente nuevamente."; break;
		case 4452: this.msg="No se ha podido eliminar la Parcela Cementerio. Por favor, intente nuevamente."; break;
		case 4453: this.msg="No se ha podido recuperar el Listado de Parcelas de Cementerio. Por favor, intente nuevamente."; break;
		case 4454: this.msg="No se ha podido recuperar la Parcela Cementerio. Por favor, intente nuevamente."; break;
		case 4455: this.msg="No se ha podido agregar el Tipo de Sepultura. Por favor, intente nuevamente."; break;
		case 4456: this.msg="No se ha podido actualizar el Tipo de Sepultura. Por favor, intente nuevamente."; break;
		case 4457: this.msg="No se ha podido eliminar el Tipo de Sepultura. Por favor, intente nuevamente."; break;
		case 4458: this.msg="No se ha podido recuperar el Listado de Tipos de Sepultura. Por favor, intente nuevamente."; break;
		case 4459: this.msg="No se ha podido recuperar el Tipo de Sepultura. Por favor, intente nuevamente."; break;
		case 4460: this.msg="No se ha podido agregar el Difunto. Por favor, intente nuevamente."; break;
		case 4461: this.msg="No se ha podido actualizar el Difunto. Por favor, intente nuevamente."; break;
		case 4462: this.msg="No se ha podido eliminar el Difunto. Por favor, intente nuevamente."; break;
		case 4463: this.msg="No se ha podido recuperar el Listado de Difuntos. Por favor, intente nuevamente."; break;
		case 4464: this.msg="No se ha podido recuperar el Difunto. Por favor, intente nuevamente."; break;

		//SystemTipoTasa
		case 4500: this.msg="No se ha podido agregar el Tipo de Parámetro Constante. Por favor, intente nuevamente.";break;
		case 4501: this.msg="No se ha podido agregar el Tipo de Parámetro de Grupo de Zonas. Por favor, intente nuevamente.";break;
		case 4502: this.msg="No se ha podido recuperar el Listado de Parámetros Parcelarios. Por favor, intente nuevamente.";break;
		case 4503: this.msg="No se ha podido recuperar el Listado de Constantes. Por favor, intente nuevamente.";break;
		case 4504: this.msg="No se ha podido recuperar el Listado de Grupos de Zonas. Por favor, intente nuevamente.";break;
		case 4505: this.msg="No se ha podido recuperar el Listado de Parámetros de Obras. Por favor, intente nuevamente.";break;
		case 4506: this.msg="No se ha podido validar la Fórmula de Cálculo. Por favor, intente nuevamente.";break;
		case 4507: this.msg="No se ha podido agregar la Fórmula de Cálculo. Por favor, intente nuevamente.";break;
		case 4508: this.msg="No se ha podido recuperar el Listado de Fórmulas de Cálculo. Por favor, intente nuevamente.";break;
		case 4509: this.msg="No se ha podido actualizar la Fórmula de Cálculo. Por favor, intente nuevamente.";break;
		case 4510: this.msg="No se ha podido recuperar la Fórmula de Cálculo. Por favor, intente nuevamente.";break;
		case 4511: this.msg="No se ha podido recuperar el Listado de Tipos de Parámetros de Personas. Por favor, intente nuevamente.";break;
		case 4512: this.msg="No se ha podido recuperar el Listado de Parámetros de Obras y Servicios Públicos. Por favor, intente nuevamente.";break;
		case 4513: this.msg="No se ha podido actualizar el Documento de Tipo de Parámetro Constante. Por favor, intente nuevamente.";break;
		case 4514: this.msg="No se ha podido actualizar el Documento de Tipo de Parámetro de Grupo de Zonas. Por favor, intente nuevamente.";break;
		case 4515: this.msg="No se ha podido recuperar el Listado de Parámetros de SHPS. Por favor, intente nuevamente.";break;
		case 4516: this.msg="No se ha podido eliminar el Tipo de Parámetro de Constante. Por favor, intente nuevamente.";break;
		case 4517: this.msg="No se ha podido eliminar el Tipo de Parámetro de Grupo de Zonas. Por favor, intente nuevamente.";break;
		case 4518: this.msg="No se ha podido recuperar el Tipo de Parámetro Constante. Por favor, intente nuevamente.";break;
		case 4519: this.msg="No se ha podido recuperar el Tipo de Parámetro de Grupos de Zonas. Por favor, intente nuevamente.";break;
		case 4520: this.msg="No se ha podido recuperar el Tipo de Parámetro de TGI. Por favor, intente nuevamente.";break;
		case 4521: this.msg="No se ha podido recuperar el Tipo de Parámetro de PFO. Por favor, intente nuevamente.";break;
		case 4522: this.msg="No se ha podido recuperar el Tipo de Parámetro de Vencimiento. Por favor, intente nuevamente.";break;
		case 4523: this.msg="No se ha podido activar la Fórmula de Cálculo. Por favor, intente nuevamente.";break;
		case 4524: this.msg="No se ha podido calcular la Fórmula de Cálculo. Por favor, intente nuevamente.";break;
		case 4525: this.msg="No se ha podido calcular los Modificadores aplicados sobre la Tasa. Por favor, intente nuevamente.";break;
		case 4526: this.msg="No se ha podido calcular los Vencimientos. Por favor, intente nuevamente.";break;
		case 4527: this.msg="No se ha podido calcular los Modificadores aplicados sobre el SubTotal. Por favor, intente nuevamente.";break;
		case 4529: this.msg="No se han podido calcular el interés y el recargo. Verifique los datos ingresados."; break;
		case 4532: this.msg="No se ha podido recuperar el Tipo de Parámetro de Cementerio. Por favor, intente nuevamente.";break;
		case 4533: this.msg="No se ha podido recuperar el Tipo de Parámetro de Automotor. Por favor, intente nuevamente.";break;
		case 4534: this.msg="No se ha podido recuperar el Listado de Planes. Por favor, intente nuevamente.";break;
		case 4535: this.msg="No se ha podido recuperar el Tipo de Parámetro de Deuda. Por favor, intente nuevamente.";break;
		case 4536: this.msg="Ya existe un Tipo Parametro Grilla con ese nombre.";break;

		//ZONIFICACION
		case 4528: this.msg = "El parámetro Grupo Zona debe pertenecer a alguna zonificación. Por favor verifique los datos ingresados";break;
		case 4530: this.msg = "El Tipo Parámetro no puede ser nulo."; break;
		case 4531: this.msg = "El Tipo Parámetro debe tener un nombre válido."; break;

		//Permisos
		//SystemRegistroAlicuota
		case 4700: this.msg="No puede eliminarse un Rubro que está asociado a una Obligación Activa."; break;
		case 4701:
		case 4702:
		case 4703:

			//com.trascender.habilitaciones.util.Util
		case 4720:this.msg = "Error al intentar eliminar un Domicilio Despreciado, Hay elementos nulos";break;
		case 4721:
		case 4722:
		case 4723:
		case 4724:
			// Documento Tasa Menor
		case 4725: this.msg = "El nombre de la Plantilla ya existe.";break;
		case 4726: this.msg = "La persona ya posee una Obligación activa para esa Tasa.";break;
		case 4727: this.msg = "Existen nombres repetidos en una o ambas listas de datos.";break;
		case 4728: this.msg = "No se puede generar una Obligaciòn con una Plantilla Tasa Menor que esté Inactiva."; break;
		case 4729:

			//SystemBromatologia
		case 4730:
		case 4731:
		case 4732:
		case 4733:
		case 4734: 
		case 4735:
		case 4736:
		case 4737:
		case 4738:
		case 4739:
		case 4740:
		case 4741:
		case 4742:
		case 4743:
		case 4744:
		case 4745:
		case 4746:
		case 4747:
		case 4748:
		case 4749:
		case 4750:

			//SystemInspector
		case 4760:
		case 4761:
		case 4762:
		case 4763:			

			//SystemObligacion
		case 4770:
		case 4771:
		case 4772:
		case 4773:
		case 4774:
		case 4775:
		case 4776:
		case 4777:
		case 4778:
		case 4779:
		case 4780:
		case 4781:
		case 4782:

			//SystemPlantillaObligacion				
			/*	case 4780:
			case 4781:
			case 4782:*/
		case 4783:
		case 4784:

			//SystemTransito
		case 4800:
		case 4801:
		case 4802:
		case 4803:
		case 4804:
		case 4805:

			//SystemDocumentoOSP
		case 4810:
		case 4811:
		case 4812:
		case 4813:
		case 4814:
		case 4815:
		case 4816:
		case 4817:
		case 4818:
		case 4819:

			//SystemPlanObra
		case 4830:
		case 4831:
		case 4832:
		case 4833:
		case 4834:
		case 4835:
		case 4836:
		case 4837:
		case 4838:
		case 4839:
		case 4840:
		case 4841:
		case 4842:
		case 4843:
		case 4844:

			//SystemDocumentoOSP
		case 4820:
		case 4821:
		case 4822:
		case 4823:
		case 4824:

			//SystemDocumentoCementerio
		case 4850: this.msg = "Debe seleccionar un Tipo de Sepultura"; break;
		case 4851:
		case 4852:
		case 4853:
		case 4854:

			//SystemTipoTasa
		case 4860: this.msg = "El Tipo de Obligacion es un dato obligatorio"; break;
		case 4861:
		case 4862:
		case 4863:
		case 4864:
		case 4865:
		case 4866:
		case 4867:
		case 4868:
		case 4869:
		case 4870:
		case 4871:
		case 4872:
		case 4873:
		case 4874:
		case 4875:
		case 4876:
		case 4877:
		case 4878:
		case 4879:
		case 4880:
		case 4881:
		case 4882:
		case 4883:
		case 4884:
		case 4885:
		case 4886:

			//Excepciones específicas de las liquidaciones SHPS debido a la falta de carga de registros valuados
		case 4900: this.msg = "No se ha podido liquidar la tasa de OySP. Por favor, verifique los valores de medidores cargados.";break;
		case 4901: this.msg = "No se ha podido liquidar la tasa de SHPS. Por favor, verifique los importes de las DDJJ cargados.";break;

		//Exencion Obligacion
		case 4910: this.msg="La Obligación no ha podido ser Anulada debido a que se encuentra asociada a una o más Exenciones Vigentes.";break;
		case 4911: this.msg = "La Obligación se encuentra asociada a otra Exención para el Período Seleccionado.";break;
		case 4912: this.msg = "Debe seleccionar un Período antes de agregar Obligaciones a la Exención.";break;
		case 4913: this.msg = "Las Obligaciones anuladas no pueden quedar Exentas."; break;
		case 4914: this.msg = "El nombre que intenta asignar no está disponible. Por favor, ingrese un nombre distinto."; break;
		case 4915: this.msg = "No se pueden Terminar Exenciones que ya esten terminadas."; break;
		case 4916: this.msg = "No se pueden Autorizar Exenciones que ya esten Vigentes."; break;

		case 4799: this.msg = "No posee Permisos suficientes para realizar la Operación. Póngase en contacto con el Administrador."; break;

		default: this.msg="Error desconocido. Póngase en contacto con el Administrador."; 
		}
	}

	public Object getObjetoRelacion1() {
		return objetoRelacion1;
	}	
}
