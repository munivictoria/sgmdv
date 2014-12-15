package com.trascender.compras.exception;

import com.trascender.framework.exception.TrascenderException;

public class TrascenderComprasException extends TrascenderException{
	
	public TrascenderComprasException(int pCodeTrascenderComprasException){
		super(4000 + pCodeTrascenderComprasException);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object objetoRelacion1;
	private Object objetoRelacion2;
	
	public TrascenderComprasException (int pCodeTrascenderComprasException, Object pObjectoRelacion1, Object pObjectoRelacion2) {
		super(6000 + pCodeTrascenderComprasException);
		this.objetoRelacion1 = pObjectoRelacion1;
		this.objetoRelacion2 = pObjectoRelacion2;
		
		this.setMsg();
	}
	
	@Override
	protected void setMsg() {
		switch(getCodeTrascenderException()){
				
		/**
		 * BUSINESS
		 */
		// SOLICITUD SUMINISTRO
		case 4050: this.msg = "No puede cambiar la fecha de ingreso de la solicitud de suministro.";break;
		case 4051: this.msg = "Las Solicitudes de Suministro solo se finalizan como ANULADAS o CUMPLIDAS.";break;
		case 4052: this.msg = "Solo las Solicitudes en estado ABIERTA puden ser modificadas.";break;
		case 4053: this.msg = "Todas las Solicitudes de Suministros deben estar APROBADA.";break;
		case 4054: this.msg = "No puede modificar una solicitud de suministro que se encuentra en curso."; break;
		case 4055: this.msg = "No se puede firmar la solicitud de suministros. Verifique las firmas necesarias."; break;
		case 4056: this.msg = "No se puede firmar una solicitud de suministros que no posee una cuenta asociada."; break;
		case 4057: this.msg = "Solo pueden firmarse Solicitudes en estado ABIERTA o PRE APROBADAS."; break;
		case 4058: this.msg = "El monto total de las lineas de solicitud de suministro debe ser mayor a 0."; break;
		case 4059: this.msg = "El valor estimado de las lineas de solicitud de suministro debe ser mayor a 0."; break;
		case 4060: this.msg = "La cantidad de bienes de las lineas de solicitud de suministro debe ser mayor a 0."; break;
		case 4061: this.msg = "Debe seleccionar un Proveedor para la busqueda.";break;
		case 4062: this.msg = "El Proveedor seleccionado no posee Actividades CIIU asociados.";break;
		case 4063: this.msg = "No se ha definido un Estado de Solicitud Suministro inicial.";break;
		case 4064: this.msg = "No hay Autorización válida para esta Solicitud.";break;
		case 4065: this.msg = "Ya existe un Estado de Solicitud con ese Nombre.";break;
		case 4066: this.msg = "Ya existe un Estado de Solicitud del tipo \"Inicial\". No puede agregar otro.";break;
		case 4067: this.msg = "No se puede eliminar el Estado ya que esta asociado a una o mas Solicitudes de Suministro.";break;
		case 4068: this.msg = "No se puede eliminar el Estado ya que esta asociado a una o mas Reglas de Firmas.";break;
		
		// ORDEN COMPRA
		case 4069: this.msg = "No se puede eliminar el Tipo de Orden de Compra, ya que posee una o mas Orden de Compra asociada.";break;
		case 4070: this.msg = "Listas nulas en las lineas de orden de compra o en las solicitudes de suministro.";break;
		case 4071: this.msg = "Debe asociar solicitudes de suministro a la orden de compra.";break;
		case 4072: this.msg = "No se puede modificar una orden de compra que no esté en estado NUEVA o APROBADA.";break;
		case 4073: this.msg = "El moto total de la orden de compras no se corresponde con los rangos fijados por el tipo de orden de compra";break;
		case 4074: this.msg = "La orden de compra ya está creada";break;
		case 4075: this.msg = "No se puede aceptar la orden de compras. Verifique las firmas";break;
		case 4076: this.msg = "El bien en las lineas de solicitud de suministro no corresponde con el bien de la linea de orden de compra."
									+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n"+((this.objetoRelacion2!= null)?this.objetoRelacion2.toString():"")+"\n";
										break;
		case 4077: this.msg = "El estado de la Orden de Compra no se corresponde con la tarea que desea realizar.";break;
		case 4078: this.msg = "Las firmas actuales de la Orden de Compra no coinciden con las necesarias";break;
		case 4079: this.msg = "No existen lineas de solicitud suministro asociada a la linea orden de compra"; break;
		case 4080: this.msg = "La Orden de Compra no puede asociarse a un Proveedor inactivo";break; 
		case 4081: this.msg = "Las Orden de compra solo se puede finalizar como ANULADAS o RESCINDIDA";break;
		case 4082: this.msg = "Los datos para realizar la trasnferencia no son validos."; break;
		case 4083: this.msg = "No se puede realizar una transferencia hacia el mismo proveedor."; break;
		case 4084: this.msg = "La orden de compra debe estar aprobada para realizar la trasnferencia."; break;
		case 4085: this.msg = "Solo se puede firmar una orden de compra que esté en estado NUEVA."; break;
		case 4086: this.msg = "No se ha definido un Tipo de Orden de Compra con Firmantes para Ordenes de Compra con este monto";break;
		case 4087: this.msg = "La suma del total de los Pagos no debe exceder el total de la Orden de Compra";break;
		case 4088: this.msg = "Ya existe un Usuario Firmante con ese Usuario.";break;
		
		
		//ADJUDICACIONES
		case 4090: this.msg = "Existen Lineas sin Proveedores adjudicados.";break;
		
		// REMITO
		case 4100: this.msg = "No se puede agregar un remito sin ninguna asociación a una orden de compra";break;
		case 4101: this.msg = "El remito debe corresponder con una orden de compras CURSADA al proveedor";break;
		case 4102: this.msg = "La cantidad recepcionada de un producto determinado debe ser mayor a cero (0)";break;
		case 4103: this.msg = "La cantidad recepcionada de un producto determinado no debe ser mayor a la cantidad faltante por recibir";break;
		case 4104: this.msg = "No se puede borrar un remito ya pagado";break;
		case 4105: this.msg = "No se puede borrar un remito de una orden de compras que no está en curso o entregada";break;
		case 4106: this.msg = "La fecha Desde debe ser menor a la fecha Hasta";break;
		case 4107: this.msg = "No se puede modificar un remito ya facturado";break;
		
		// FACTURA
		case 4120: this.msg = "Debe asociar remitos a la factura";break;
		case 4121: this.msg = "Existen remitos que ya están facturados";break;
		case 4122: this.msg = "La factura debe estar asociada a un proveedor";break;
		case 4123: this.msg = "No se puede modificar una factura de un proveedor que no está en curso";break;
		case 4124: this.msg = "El estado de la factura no se corresponde con la tarea que desea realizar";break;
		case 4125: this.msg = "El descuento en la factura no puede ser negativo";break;
		case 4126: this.msg = "El descuento no puede ser mayor que el monto de la factura";break;
		case 4127: this.msg = "La factura no puede estar asociada a un Proveedor inactivo"; break;
		case 4128: this.msg = "Ya existe una Factura con ese número para ese Proveedor"; break;
		case 4129: this.msg = "No puede generar una Factura con pagos pertenecientes a una Orden de Compra que posee pagos anteriores sin abonar"; break;
		
		//BIEN
		case 4140: this.msg = "Ya existe una Categoría con ese nombre.";break;
		case 4141: this.msg = "La Categoría no puede ser eliminada ya que esta asociada a uno o mas Bienes."; break;
		case 4142: this.msg = "La Categoría no puede ser eliminada ya que esta asociada a uno o mas Proveedores."; break;
		
		//AUTORIZACION SOLICITUD SUMINISTRO
		case 4150: this.msg = "Ya existe una Autorización de Solicitud de Suministro para ese año y Área."; break;
		case 4151: this.msg = "No existe una Autorización de Solicitud de Suministro para el año corriente."; break;
		
		//Unidad
		case 4200: this.msg = "No se ha podido agregar la unidad. Por favor, intente nuevamente."; break;
		case 4201: this.msg = "No se ha podido actualizar la unidad. Por favor, intente nuevamente."; break;
		case 4202: this.msg = "No se ha podido eliminar la unidad. Por favor, intente nuevamente."; break;
		case 4203: this.msg = "No se ha podido obtener la unidad. Por favor, intente nuevamente"; break;
		case 4204: this.msg = "No se ha podido obtener el listado de unidades. Por favor, intente nuevamente"; break;
		case 4205: this.msg = "La Unidad se encuentra asociada a uno o más bienes."; break;
		case 4206: this.msg = "Ya existe una unidad con esa descripción."; break;
		
		/**
		 * SYSTEM
		 */
		// GRUPO BIENES
		case 4300: this.msg = "No se ha podido agregar el grupo de bienes. Intente nuevamente";break;
		case 4301: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4302: this.msg = "No se han podido actualizar los datos del grupo de bienes. Intente nuevamente";break;
		case 4303: this.msg = "No se ha podido eliminar el grupo de bienes. Intente nuevamente";break;
		case 4304: this.msg = "No se ha podido eliminar el grupo de bienes, posee subgrupos asociados.";break;
		case 4305: this.msg = "No se ha podido eliminar el grupo de bienes, posee bienes asociados."; break;
		
		// BIENES
		case 4320: this.msg = "No se ha podido agregar el bien. Intente nuevamente";break;
		case 4321: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4322: this.msg = "No se han podido actualizar los datos del bienes. Intente nuevamente";break;
		case 4323: this.msg = "No se ha podido eliminar el bien. Intente nuevamente";break;
		case 4324: this.msg = "No se ha podido restaurar el bien. Intente nuevamente";break;
		case 4325: this.msg = "No se puede modificar un bien inactivo. Debe restaurar el bien";break;
		
		// GRUPO PROVEEDORES
		case 4340: this.msg = "No se ha podido agregar el grupo de proveedores. Intente nuevamente";break;
		case 4341: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4342: this.msg = "No se han podido actualizar los datos del grupo de proveedores. Intente nuevamente";break;
		case 4343: this.msg = "No se ha podido eliminar el grupo de proveedores. Intente nuevamente";break;
		case 4344: this.msg = "El nombre del grupo de proveedor ya se encuentra en uso";break;
		case 4345: this.msg = "No se pudo eliminar el grupo de proveedores, posee subgrupos asociados."; break;
		case 4346: this.msg = "No se puedo eliminar el grupo de proveedores, posee proveedores asociados."; break;
		
		// PROVEEDOR
		case 4350: this.msg = "No se ha podido agregar el proveedor. Intente nuevamente";break;
		case 4351: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4352: this.msg = "No se han podido actualizar los datos del proveedor. Intente nuevamente";break;
		case 4353: this.msg = "No se ha podido eliminar el proveedor. Intente nuevamente";break;
		case 4354: this.msg = "No se ha podido restaurar el proveedor. Intente nuevamente";break;
		case 4355: this.msg = "No se puede modificar un proveedor inactivo. Debe restaurar el proveedor";break;
		case 4356: this.msg = "No se puede quitar el Bien Provisto pues se esta usando en una Orden de Compra.";break;
		case 4357: this.msg = "Ya existe un proveedor con esa Razón Social.";break;
		case 4358: this.msg = "El proveedor que decea Agregar/Modificar no puede ser nulo."; break;
		case 4359: this.msg = "El proveedor debe tener asociado un proveedor local válido."; break;
		case 4360: this.msg = "Ya existe un proveedor con esa dirección de E-mail."; break;
		
		// SOLICITUD SUMINISTRO
		case 4370: this.msg = "No se ha podido agregar la solicitud de suministro. Intente nuevamente";break;
		case 4371: this.msg = "No se puede asociar una orden de compra a una solicitud de suministro que no ha sido creada";break;
		case 4372: this.msg = "No se puede modificar una solicitud de suministro ya procesada";break;
		case 4373: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4374: this.msg = "No se han podido actualizar los datos de la solicitud de suministro. Intente nuevamente";break;
		case 4375: this.msg = "No puede cancelar una solicitud de suministro ya procesada";break;
		
		// TIPO ORDEN COMPRA
		case 4382: this.msg = "No puede cambiar el estado de tipo de orden de compra en forma explícita";break;
		case 4383: this.msg = "Los montos deden ser mayores a cero";break;
		case 4384: this.msg = "Verifique los montos. Existe solapamiento con los tipos de ordenes de compra registrados";break;
		case 4385: this.msg = "El monto máximo debe ser mayor al monto mínimo";break;
		case 4390: this.msg = "No se ha podido agregar el tipo de orden de compra. Intente nuevamente";break;
		case 4391: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4392: this.msg = "No se han podido actualizar los datos del tipo de orden de compra. Intente nuevamente";break;
		case 4393: this.msg = "No se ha podido eliminar el tipo de orden de compra. Intente nuevamente";break;
		case 4394: this.msg = "No se puede agregar un Tipo de Orden de Compras con un nombre ya existente.";break;
		case 4395: this.msg = "No se puede modificar el nombre de un Tipo de Orden de Compras por uno ya existente.";break;
		
		// CONDICI�N DE COMPRAS
		case 4400: this.msg = "No puede modificar una condición de compra en estado INACTIVO";break;
		case 4401: this.msg = "No se ha podido agregar la condición de compras. Intente nuevamente";break;
		case 4402: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4403: this.msg = "No se han podido actualizar los datos de la condición de compras. Intente nuevamente";break;
		case 4404: this.msg = "No se ha podido eliminar el tipo de orden de compra. Intente nuevamente";break;
		case 4405: this.msg = "No se puede agregar una Condición de Compra con un nombre ya existente.";break;
		case 4406: this.msg = "No se puede modificar el nombre de una Condición de Compra por uno ya existente.";break;
		case 4407: this.msg = "No se puede eliminar una Condición de Compra asociada a una Orden de Compra.";break;
		
		// ORDEN DE COMPRA
		case 4420: this.msg = "Usted no puede firmar esta Orden de Compra.";break;
		case 4421: this.msg = "No se puede agregar una solicitud de suministro ya procesada";break;
		case 4422: this.msg = "Este proveedor no provee todos los productos solicitados";break;
		case 4423: this.msg = "Esta firma ya se encuentra en la orden de compra";break;
		case 4430: this.msg = "No se ha podido agregar la orden de compras. Intente nuevamente";break;
		case 4431: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4432: this.msg = "No se han podido actualizar los datos de la orden de compras. Intente nuevamente";break;
		case 4435: this.msg = "Solo se pueden firmar ordenes de compra CREADAS";break;
		case 4436: this.msg = "No se puede cancelar una orden de compra ya entregada o cancelada";break;
		case 4437: this.msg = "No se puede finalizar una orden de compra que no está en estado ENTREGADA";break;
		case 4438: this.msg = "No se puede marcar como ENTREGADA la orden de compras. Intente nuevamente";break;
		case 4440: this.msg = "Solo puede marcar como ENTREGADA las ordenes de compras en curso";break;
		case 4441: this.msg = "El monto de cada cuota no puede ser cero.";break;
		case 4442: this.msg = "El monto del pago inicial no puede ser superior al monto total.";break;
		case 4443: this.msg = "Existen Pagos asociados a Facturas.";break;
		case 4444: this.msg = "Usted ya firmó esta Orden de Compra."; break;
		
		
		// REMITO
		case 4450: this.msg = "No se ha podido agregar el remito. Intente nuevamente";break;
		case 4451: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4452: this.msg = "No se han podido actualizar los datos del remito. Intente nuevamente";break;
		case 4453: this.msg = "No se ha podido eliminar el remito. Intente nuevamente";break;
		
		// FACTURA
		case 4470: this.msg = "No se ha podido agregar la factura. Intente nuevamente";break;
		case 4471: this.msg = "No se han podido recuperar los datos solicitados. Intente nuevamente";break;
		case 4472: this.msg = "No se han podido actualizar los datos de la factura del proveedor. Intente nuevamente";break;
		case 4473: this.msg = "No se ha podido eliminar la factura del proveedor. Intente nuevamente";break;
		
		//LICITACIONES
		
		case 4501: this.msg = "Ya existe una licitacion con el numero ingresado.";break;
		case 4502: this.msg = "El proveedor oferente no se encuentra en la lista de proveedores autorizados.";break;
		case 4503: this.msg = "Solo pueden modificarse las Licitaciones en estado Vigente";break;
		case 4504: this.msg = "Solo pueden eliminarse las Contrataciones en estado NUEVA."; break;
		case 4505: this.msg = "No puede eliminarse la Oferta por estar asociada a una Licitacion finalizada";break;
		
		//Contrataciones
		case 4525: this.msg = "Seleccione el Tipo de la Contratación para continuar.";break;
		
		//ARTICULOS
		
		case 4551: this.msg = "Ya existe un articulo con el codigo ingresado.";break;
		
		
		//ACTA APERTURA
		case 4600: this.msg = "El Acta Apertura no puede ser nulo."; break;
		case 4601: this.msg = "La Licitacion asociada ya posee un Acta Apertura"; break; 
		case 4602: this.msg = "No se puede eliminar un Acta Apertura que esté asociado a una Licitación"; break;
		case 4603: this.msg = "Para agregar un Acta Apertura la Licitacion de estar en estado -Cerrada-."; break;
		
		/************************************************************************************************************************
		 * PERMISOS
		 ************************************************************************************************************************/
		// GRUPO BIENES
		case 4700: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4701: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4702: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4703: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		
		// BIENES
		case 4720: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4721: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4722: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4723: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4724: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		
		// GRUPO PROVEEDORES
		case 4740: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4741: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4742: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		case 4743: this.msg = "No posee permisos suficientes para realizar la operaciòn. Pòngase en contacto con el administrador";break;
		
		// PROVEEDOR
		case 4750: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4751: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4752: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4753: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4754: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		// SOLICITUD SUMINISTRO
		case 4770: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4773: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4774: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4775: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		// TIPO ORDEN COMPRA
		case 4790: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4791: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4792: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4793: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		// CONDICI�N DE COMPRAS
		case 4801: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4802: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4803: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4804: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		// ORDEN DE COMPRAS
		case 4830: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4831: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4832: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4834: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		
		// REMITO
		case 4850: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4851: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4852: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4853: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		// FACTURA
		case 4870: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4871: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4872: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		case 4873: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		//FACTURA SUBSIDIOS
		case 4880: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador.";break;
		case 4881: this.msg = "No se ha podido agregar la factura por subsidio. Por favor, intente nuevamente."; break;
		case 4882: this.msg = "No se ha podido actualizar la factura por subsidio. Por favor, intente nuevamente."; break;
		case 4883: this.msg = "No se ha podido eliminar la factura por subsidio. Por favor, intente nuevamente."; break;
		case 4884: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		
		//FACTURA CONTRATOS
		case 4890: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador.";break;
		case 4891: this.msg = "No se ha podido agregar la factura por contrato. Por favor, intente nuevamente."; break;
		case 4892: this.msg = "No se ha podido actualizar la factura por contrato. Por favor, intente nuevamente."; break;
		case 4893: this.msg = "No se ha podido eliminar la factura por contrato. Por favor, intente nuevamente."; break;
		case 4894: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		
		//FACTURA SERVICIOS
		case 4900: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador.";break;
		case 4901: this.msg = "No se ha podido agregar la factura por servicio. Por favor, intente nuevamente."; break;
		case 4902: this.msg = "No se ha podido actualizar la factura por servicio. Por favor, intente nuevamente."; break;
		case 4903: this.msg = "No se ha podido eliminar la factura por servicio. Por favor, intente nuevamente."; break;
		case 4904: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		
		//LINEA_FACTURA
		case 4910: this.msg = "La factura debe tener por lo menos un ítem de factura.";break;
		case 4911: this.msg = "No se ha podido recuperar la Linea de Factura.";break;
		
		//LIQUIDACION_COMPRA
		case 4912: this.msg = "No se ha podido agregar la Liquidación de Compra. Por favor, intente nuevamente."; break;
		case 4913: this.msg = "No se ha podido modificar la Liquidación de Compra. Por favor, intente nuevamente."; break;
		case 4914: this.msg = "No se ha podido eliminar la Liquidación de Compra. Por favor, intente nuevamente."; break;
		case 4915: this.msg = "No se ha podido recuperar la Liquidación de Compra. Por favor, intente nuevamente."; break;
		case 4916: this.msg = "No se ha podido recuperar la lista de Liquidación de Compra. Por favor, intente nuevamente."; break;
		case 4917: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador";break;
		
		//AUTORIZACION SOLICITUD SUMINISTROS
		case 4920: this.msg = "No se ha podido agregar la autorización de solicitudes de suministros. Por favor, intente nuevamente."; break;
		case 4921: this.msg = "No se ha podido modificar la autorización de solicitudes de suministros. Por favor, intente nuevamente."; break;
		case 4922: this.msg = "No se ha podido eliminar la autorización de solicitudes de suministros. Por favor, intente nuevamente."; break;
		case 4923: this.msg = "No se ha podido recuperar la autorización de solicitudes de suministros. Por favor, intente nuevamente."; break;
		case 4924: this.msg = "No se ha podido recuperar la lista de autorizaciones de solicitudes de suministros. Por favor, intente nuevamente."; break;
		case 4925: this.msg = "No se ha podido firmar. Por favor, contactese con el administrador."; break;
		case 4926: this.msg = "Su usuario no puede firmar ya que no se encuentra en la lista de usuarios autorizadores. Por favor, contactese con el administrador."; break;
		case 4927: this.msg = "Usted ya firmó esta solicitud de suministros."; break;
		case 4928: this.msg = "Su usuario no es firmante de esta Solicitud de Suministro.";break;
		case 4929: this.msg = "Su usuario no puede firmar una Solicitud de Suministro en el estado actual.";break;
		case 4930: this.msg = "Su Usuario no puede firmar una Solicitud de Suministro porque tiene un Usuario suplente en vigencia.";break;
		
		/************************************************************************************************************************
		 * FIN DE C�DIGO DE ERRORES
		 ************************************************************************************************************************/
		
		case 4998: this.msg = "No posee permisos para realizar la acción."; break;
		// DEFAULT
			default:
				this.msg = "Ha ocurrido un error desconocido. Póngase en contacto con el administrador";
		}
		
	}

}
