package com.trascender.saic.exception;

import com.trascender.framework.exception.TrascenderException;

public class SaicException extends TrascenderException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8034674526751522511L;


	public SaicException(int pCodeSystemException) {
		super(5000+pCodeSystemException);
	}


	@Override
	protected void setMsg() {

		switch (this.getCodeTrascenderException()){
		//BusinessRegistroValuado  
		case 5000: this.msg = "El Número de Período no se encuentra dentro del Rango de Valores válido.";break;
		case 5001: this.msg = "Sólo se pueden modificar las Declaraciones Juradas no liquidadas.";break;
		case 5002: this.msg = "El Período no puede ser Nulo.";break;
		case 5003: this.msg = "El Servicio no puede ser Nulo y debe ser Medido.";break;
		case 5004: this.msg = "Debe ingresar alguno de los atributos requeridos.";break;
		case 5005: this.msg = "La obligación no puede ser nula para generar el registro de deuda";break;
		case 5006: this.msg = "Debe seleccionar un Servicio Medido y/o un código de Medidor para realizar la operación.";break;
		case 5007: this.msg = "La busqueda devuelve mas de una obligacion, use el numero de inscripcion."; break;
		case 5008: this.msg = "Ya existe una Declaración Jurada para ese período."; break;
		
		//BusinessLiquidacionTasa
		case 5020: this.msg = "El Período no puede ser Nulo.";break;
		case 5021: this.msg = "La Obligación se encuentra asociada a una Fórmula de Cálculo en estado 'En Espera'.";break;
		case 5022: this.msg = "No se puede reliquidar una liquidación nula."; break;
		case 5023: this.msg = "El registro de deuda a cancelar es nulo.";break;
		case 5024: this.msg = "El registro de deuda ya ha sido pagado."; break;
		case 5025: this.msg = "El valor del medidor no ha sido cargado para el período seleccionado.";break;
		case 5026: this.msg = "La declaración jurada del documento no ha sido ingresada.";break;
		case 5027: this.msg = "No existe una fórmula de cálculo para la tasa de Obras y Servicios Públicos."; break;
		case 5028: this.msg = "No existe una fórmula de cálculo para la tasa de PFO.";break;
		case 5029: this.msg = "La fecha de reliquidación no puede ser inferior a la fecha de la vencimiento original.";break;
		case 5030: this.msg = "Seleccione una Persona y/o Rubro o una Declaración Jurada para generar la liquidación.";break;
		case 5031: this.msg = "La cuadra debe pertenecer a la calle pasada por parámetro.";break;
		case 5032: this.msg = "Los datos solicitados del servicio y el servicio seleccionado son inconsistentes.";break;
		case 5033: this.msg = "Si el período es nulo, al menos la persona o la obra debe no ser nula.";break;
		case 5034: this.msg = "La búsqueda requiere al menos un parametro";break;
		case 5035: this.msg = "La búsqueda requiere al menos el período o la persona.";break;
		case 5036: this.msg = "La cuadra debe pertenecer a la calle pasada por parámetro.";break;
		case 5037: this.msg = "La búsqueda requiere al menos un parametro";break;
		case 5038: this.msg = "El registro de deuda no es una Liquidación de Tasas.";break;
		case 5039: this.msg = "La deuda esta vencida. La deuda se debe reliquidar.";break;
		case 5043: this.msg = "Debe liquidar primero cuotas anteriores luego continue con la corriente."; break;
		case 5044: this.msg = "No se puede liquidar cuotas mayores a la primera si aun no se ha elegido una forma de pago"; break;
		case 5045: this.msg = "No se encontraron formulas Activas para liquidar. Contactese con el Administrador, y vuelva a intentarlo."; break;
		case 5046: this.msg = "No se puede liquidar la Obligacion ya que el tipo obligacion del calendario correspondiente no es compatible."; break;

		case 5050: this.msg = "No existe el registro de deuda.";break;
		case 5051: this.msg = "EL id no puede ser nulo";break;
		case 5052: this.msg = "El registro de deuda debe pertenecer a la TGI";break;
		case 5053: this.msg = "No se pudo generar la liquidación por que hay otro plan elegido para el año requerido.";break;
		case 5054: this.msg = "No se pudo generar la liquidación por que no hay Plan elegido para el año requerido.";break;
		case 5055: this.msg = "No hay una formula de calculo vigente para la cuota que desea liquidar.";break;
		case 5056: this.msg = "El periodo para esta obligacion ya fue liquidado."; break;

		//BusinessImpresion
		case 5040: this.msg = "El Período no puede ser Nulo.";break;
		case 5041: this.msg = "El Servicio no puede ser Nulo.";break;
		case 5042: this.msg = "El Período no puede ser Nulo.";break;

		//BusinessReliquidacion
		case 5070: this.msg = "Ha ocurrido un error al parsear la fórmula"; break;
		case 5071: this.msg = "Debe seleccionar una Persona o un Período para poder realizar la búsqueda.";break;
		case 5072: this.msg = "No se puede reliquidar una obligación con estado Reliquidada.";break;
		case 5073: this.msg = "No se puede reliquidar una obligación con estado Pagada.";break;
		case 5074: this.msg = "No se ha podido Actualizar, una o mas liquidaciones ya fue cancelada.";break;

		//BusinessEstadoCuenta
		case 5080: this.msg = "La cantidad mínima de cuotas para una refinanciación es una cuota."; break;
		case 5081: this.msg = "El importe condonado no puede superar el monto de la deuda."; break;
		case 5082: this.msg = "La refinanciación no puede tener un monto total igual a cero."; break;
		case 5083: this.msg = "La persona no puede ser nula.";break;
		case 5084: this.msg = "Las obligaciones no pertenecen a la misma persona, verifique los datos ingresados."; break;
		case 5085: this.msg = "Solo es aplicable para tasas TGI y/o OSP."; break;

		//BusinessRefinanciacion

		//SystemRegistroValuado
		case 5300: this.msg = "No se ha podido recuperar el Período. Por favor, intente nuevamente.";break;
		case 5301: this.msg = "No se ha podido generar las Declaraciones Juradas no ingresadas. Por favor, intente nuevamente.";break;
		case 5302: this.msg = "No se ha podido recuperar el Listado de Declaraciones Juradas. Por favor, intente nuevamente.";break;
		case 5303: this.msg = "No se ha podido cargar las Declaraciones Juradas. Por favor, intente nuevamente.";break;
		case 5304: this.msg = "No se ha podido actualizar la Declaración jurada de SHPS. Por favor, intente nuevamente.";break;
		case 5305: this.msg = "No se ha podido recuperar el Listado de Medidas para los Servicios de OySP. Por favor, intente nuevamente.";break;
		case 5306: this.msg = "No se ha podido actualizar el Valor del Medidor. Por favor, intente nuevamente.";break;
		case 5307: this.msg = "No se ha podido ingresar el Listado de Valores de Medidores. Por favor, intente nuevamente.";break;
		case 5308: this.msg = "No se ha podido recuperar el Listado de Valores de Medidores. Por favor, intente nuevamente.";break;
		case 5309: this.msg = "No se ha podido recuperar el Listado de Sellados a pagar. Por favor, intente nuevamente.";break;
		case 5310: this.msg = "No se ha podido eliminar la Declaración Juarada de SHPS."; break;
		case 5311: this.msg = "Debe seleccionar al menos un Calendario o Período o Cuota Liquidacion para realizar la operación."; break;
		case 5312: this.msg = "Debe seleccionar un Servicio Medido y/o un código de Medidor para realizar la operación.";break;
		case 5313: this.msg = "No se ha podido recuperar el Valor de Medidor."; break;
		case 5314: this.msg = "No se ha podido Agregar el Calendario Municipal, Por favor, intente nuevamente."; break;
		case 5315: this.msg = "No se ha podido recuperar el listado de Calendarios Municipales, Por favor, intente nuevamente."; break;

		//SystemLiquidacionTasa
		case 5320: this.msg = "No se ha podido liquidar la TGI. Por favor, intente nuevamente.";break;
		case 5321: this.msg = "No se ha podido liquidar las obligaciones de SHPS. Por favor, intente nuevamente.";break;
		case 5322: this.msg = "No se ha podido reliquidar la Tasa. Por favor, intente nuevamente.";break;
		case 5323: this.msg = "No se ha podido recuperar la Liquidación. Por favor, intente nuevamente.";break;
		case 5324: this.msg = "No se ha podido recuperar el Listado de Liquidaciones de la TGI. Por favor, intente nuevamente.";break;
		case 5325: this.msg = "No se ha podido liquidar la tasa de OySP. Por favor, intente nuevamente.";break;
		case 5326: this.msg = "No se ha podido recuperar el Listado de Liquidaciones de OySP. Por favor, intente nuevamente.";break;
		case 5327: this.msg = "No se ha podido recuperar el registro de deuda. Por favor. intente nuevamente.";break;
		case 5328: this.msg = "No se ha podido liquidar la Tasa de plan de financiación de obras. Por favor, intente nuevamente.";break;
		case 5329: this.msg = "No se ha podido recuperar el Listado de liquidaciones de PFO. Por favor, intente nuevamente.";break;
		case 5330: this.msg = "No se ha podido recuperar el Listado de liquidaciones de SHPS. Por favor, intente nuevamente.";break;
		case 5331: this.msg = "No se ha podido recuperar el listado de vencimiento.";break;
		case 5332: this.msg = "No hay ninguna zona asociada con la parcela, por favor verifique la zonificación utilizada."; break;
		case 5333: this.msg = "No ha podido anular la obligación."; break;
		case 5334: this.msg = "No se ha podido recuperar el Listado de liquidaciones de Automotor. Por favor, intente nuevamente.";break;
		case 5335: this.msg = "No se ha podido recuperar el Listado de liquidaciones de Cementerio. Por favor, intente nuevamente.";break;
		case 5336: this.msg = "No se ha podido recuperar el Listado de Cobros Externos. Por favor, intente nuevamente.";break;
		case 5337: this.msg = "No se ha podido recuperar el Cobro Externo. Por favor, intente nuevamente.";break;
		case 5338: this.msg = "Ha ocurrido un error en el procesamiento del archivo. Por favor, intente nuevamente.";break;
		case 5339: this.msg = "No se ha podido recuperar el Log Liquidación. Por favor, intente nuevamente.";break;
		
		//SystemImpresion
		case 5340: this.msg = "No se ha podido recuperar el Listado de Reporte de la TGI. Por favor, intente nuevamente.";break;
		case 5341: this.msg = "No se ha podido realizar la Impresión de la TGI. Por favor, intente nuevamente."; break;
		case 5342: this.msg = "No se ha podido recuperar el Listado de Reportes de TGI. Por favor, intente nuevamente.";break;
		case 5343: this.msg = "No se ha podido imprimir el listado de OSP. Intente nuevamente";break;
		case 5344: this.msg = "No se ha podido recuperar el Listado de Reportes de OySP. Por favor, intente nuevamente.";break;
		case 5345: this.msg = "No se ha podido recuperar el Listado de Reportes de PFO. Por favor, intente nuevamente.";break;
		case 5346: this.msg = "No se ha podido recuperar el Listado de Reportes de SHPS. Por favor, intente nuevamente.";break;
		case 5347: this.msg = "No se ha podido imprimir el listado de SHPS. Por favor, intente nuevamente";break;
		case 5348: this.msg = "No se ha podido recuperar el Listado de Estados de Cuenta. Por favor, intente nuevamente.";break;
		case 5349: this.msg = "No se ha podido imprimir el listado de Estados de Cuenta. Por favor, intente nuevamente";break;

		case 5350: this.msg = "No se ha podido generar el reporte de Reconocimiento de Deuda. Por favor, intente nuevamente";break;
		case 5351: this.msg = "No se ha podido imprimir el reporte de Reconocimiento de Deuda. Por favor, intente nuevamente";break;
		case 5352: this.msg = "No se ha podido recuperar el listado de Cuotas de Refinanciación. Por favor, intente nuevamente";break;
		case 5353: this.msg = "No se ha podido imprimir el reporte de Cuotas de Refinanciación. Por favor, intente nuevamente";break;


		case 5360: this.msg = "No se ha podido reliquidar la obligación. Por favor, intente nuevamente.";break;
		case 5361: this.msg = "La refinanciación debe estar avalada por una ordenanza o decreto. Ingrese los datos requeridos e intente nuevamente."; break;

		case 5362: this.msg = "La cantidad de cuotas no puede ser nula";break;
		case 5363: this.msg = "La Tasa Nominal Anual no puede ser nula";break;
		case 5364: this.msg = "El Mes de Inicio no puede ser nulo";break;
		case 5365: this.msg = "El Año de Inicio no puede ser nulo ";break;
		case 5366: this.msg = "El valor mínimo por cuota por la cantidad de cuotas sobrepasa al valor adeudado"; break;

		//			SystemEstadoCuenta
		case 5370: this.msg = "No se ha podido recuperar el listado de Obligaciones del contribuyente. Por favor, intente nuevamente";break;
		case 5371: this.msg = "No se ha podido recuperar el listado de Estado de Cuentas del contribuyente. Por favor, intente nuevamente";break;

		//Auditoria Tributaria
		case 5400: this.msg = "La Auditoria Tributaria no puede ser nula."; break;
		case 5401: this.msg = "Una auditoria debe estar asociada almenos a una deuda."; break;
		case 5402: this.msg = "Una deuda de las seleccionadas ya esta asociada a una Auditoria Tributaria, Por favor verifique."; break;
		case 5403: this.msg = "No se puede modificar una Auditoria tributaria que esté firmada y/o este en estado Normal."; break;
		case 5404: this.msg = "No se puede eliminar una Auditoria tributaria no que esté firmada y/o este en estado Normal."; break;
		case 5405: this.msg = "No se puede enjuiciar una auditoria que ya esta en dicho estado."; break;
		case 5406: this.msg = "Solo se puede enjuiciar una Auditoria si esta en estado Intimado o Provisorio."; break;
		case 5407: this.msg = "Solo se puede pasar una auditoria tributaria a estado provisorio si esta -En Juicio-"; break;
		case 5408: this.msg = "Solo se puede agregar una refinanciacion a una auditoria se esta está en estado -Provisorio-"; break;
		case 5409: this.msg = "La cantidad de Registros Deuda en el Documento Refinanciacion no coincide con los Registro Deuda de la Auditoria Tributaria, Por favor verifique los registros."; break;
		case 5410: this.msg = "El documento que desea agregar no es valido."; break;
		case 5411: this.msg = "No se puede enjuiciar una Auditoria Tributaria, que no este firmada."; break;
		case 5412: this.msg = "No se puede actualizar una Auditoria Tributaria ya esta Refinanciada."; break;
		case 5413: this.msg = "No puede actualizar una Auditoria Tributaria que no este persistida previamente."; break;
		case 5414: this.msg = "No se puede firmar una Auditoria Tributaria que ya esté firmada."; break;
		case 5415: this.msg = "Se produjo un error al momento de firmar la Auditoria Tributaria. Por favor intente nuevamente."; break;
		case 5416: this.msg = "No se encontro ninguna auditoria con ese ID."; break;
		//Seguridad

		//Liquidacion de Tasas Menores

		case 5501: this.msg = "No existe Fórmula de Calculo para esa Periodicidad";break;
		case 5502: this.msg = "El período no puedo ser nulo";break;
		case 5503: this.msg = "El Tipo de Tasa Menor no puede ser nulo"; break;
		case 5504: this.msg = "Solo se pueden modificar los Registros Valuados no liquidados"; break;
		case 5505: this.msg = "No se puede eliminar un Registro Valuado que fue liquidado"; break;

		//SystemRegistroValuado
		case 5700:
		case 5701:
		case 5702:this.msg = "No se ha podido recuperar el listado. Por favor, intente nuevamente."; break;
		case 5703:
		case 5704:
		case 5705:
		case 5706:
		case 5707:
		case 5708:
		case 5709:
		case 5710:
		case 5711:

			//SystemLiquidacionTasa				
		case 5720:
		case 5721:
		case 5722:
		case 5723:
		case 5724:
		case 5725:
		case 5726:
		case 5727:
		case 5728:
		case 5729:
		case 5730:
		case 5731:

			//SystemImpresion
		case 5740:
		case 5741:
		case 5742:
		case 5743:
		case 5744:
		case 5745:
		case 5746:
		case 5747: 
		case 5748:

			//SystemReliquidacion
		case 5760: this.msg = "No se ha podido recuperar el listado de Liquidaciones del Contribuyente.";break;
		case 5761: this.msg = "No se ha podido recuperar la reliquidacion."; break;

		//SystemLiquidacionTasa
		case 5770: this.msg = "No se ha podido realizar la liquidación. El monto de la liquidación es incorrécto.";break;
		case 5771: this.msg = "No se ha podido realizar la liquidación de la obligación porque no se pudo recuperar el valor del parámetro.";break;
		case 5772: this.msg = "No posee Permisos suficientes para realizar la Operación. Póngase en contacto con el Administrador.";break;

		//SystemExencionRegistroDeuda
		case 5800: this.msg = "El Registro de Deuda se encuentra asociado a otra Exención.";break;
		case 5801: this.msg = "El Periodo del Registro de Deuda debe ser igual al de la Exención.";break;
		case 5802: this.msg = "Debe seleccionar un Período antes de agregar Registros de Deuda a la Exención.";break;
		case 5803: this.msg = "No se puede Autorizar una Exención con Estado Vigente.";break;
		case 5804: this.msg = "No se puede Terminar una Exención con Estado Terminada.";break;
		case 5805: this.msg = "El nombre que intenta asignar no está disponible. Por favor, ingrese un nombre distinto.";break;

		default: this.msg = "Un error inesperado ha ocurrido. Por favor comuníquese con el Administrador";break;

		}
	}
}