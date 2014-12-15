package com.trascender.contabilidad.exception;
import java.util.List;

import com.trascender.framework.exception.TrascenderException;

public class TrascenderContabilidadException  extends TrascenderException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Se agregan tantos objetos de relación como sean necesarios
	private Object objetoRelacion1;
	private Object objetoRelacion2;
	private List<Object> listaObjetoRelacion1;
	
	public TrascenderContabilidadException (int pCodeTrascenderContabilidadException, Object pObjectoRelacion1, List pListaObjectoRelacion1) {
		super(6000 + pCodeTrascenderContabilidadException);
		this.objetoRelacion1 = pObjectoRelacion1;
		this.listaObjetoRelacion1 = pListaObjectoRelacion1;
		
		this.setMsg();
	}
	
	public TrascenderContabilidadException (int pCodeTrascenderContabilidadException, Object pObjectoRelacion1, Object pObjectoRelacion2) {
		super(6000 + pCodeTrascenderContabilidadException);
		this.objetoRelacion1 = pObjectoRelacion1;
		this.objetoRelacion2 = pObjectoRelacion2;
		
		this.setMsg();
	}
	
	public TrascenderContabilidadException (int pCodeTrascenderContabilidadException) {
		super(6000 + pCodeTrascenderContabilidadException);
	}
	//Ejemplo: case 1038: this.mensaje = "El Cuim y Número de Documento ya se encuentran asociados a: " + ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n";break;
	
	@Override
	protected void setMsg() {
		switch(getCodeTrascenderException()){
		
		/**
		 * BUSSINESS
		 */
		
		//MOVIMIENTO CAJA CHICA
		case 6001: this.msg = "No se pudo Agregar el Movimiento de la Caja Chica. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente.";break;
		case 6002: this.msg = "El ID debe ser mayor a 0.";break;
		
		//CONCEPTO MOVIMIENTO CAJA CHICA
		case 6010: this.msg = "No se pudo agregar el Concepto de Movimiento de la Caja Chica. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente.";break;
		case 6011: this.msg = "No se pudo modificar el Concepto de Movimiento de la Caja Chica. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente.";break;
		
		//CAJA CHICA
		case 6020: this.msg = "No se pudo agregar la Caja Chica. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente.";break;
		case 6021: this.msg = "No se pudo modificar la Caja Chica. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente.";break;
		
		//MONEDA
		case 6030: this.msg = "No se pudo agregar el Tipo de Moneda. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente.";break;
		case 6031: this.msg = "No se pudo modificar el Tipo de Moneda. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente.";break;
		
		//CAJA
		case 6040: this.msg = "Faltan datos requeridos. La caja debe contener al menos un ip, un nombre y un identificador. Por favor, verifiquelos e intente nuevamente."; break;
		case 6041: this.msg = "No se pudo modificiar la Caja. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente"; break;
		case 6042: this.msg = "No se pudo restaurar la Caja. Por favor, verifique los datos e intente nuevamente";	break;
		case 6043: this.msg = "El usuario con el que ingreso no puede realizar cobros.";	break;
		case 6044: this.msg = "El ticket no posee detalles. Por favor, verifique los datos e intente nuevamente.";	break;
		case 6045: this.msg = "El ticket no tiene asociado una planilla diaria.";break;
		case 6046: this.msg = "La caja ya se encuentra registrada. Verfique las direcciones ip de las cajas activas e inactivas."; break;
		
		//INGRESO VARIO
		case 6050: this.msg = "El ingreso vario ya fue pagado.";break;
		case 5041: this.msg = "No se puede eliminar un ingreso vario que no esté en estado CREADO."; break;
		case 5042: this.msg = "El ingreso vario que intenta eliminar no es valido."; break;
		case 6047: this.msg = "El ingreso vario que intenta cobrar se encuentra en estado ANULADO."; break;
		
		//TICKET CAJA
		case 6054: this.msg = "No se ha podido recuperar los datos solicitados. Por favor. intente nuevamente."; break;
		case 6055: this.msg = "No se puede cancelar un ticket de caja que no esté activo."; break;
		case 6056: this.msg = "La fórmula de cálculo utilizada para la liquidación no posee ninguna cuenta asociada. Comuníquese con el contador."; break;
		case 6057: this.msg = "El ticket no tiene asociado una caja."; break;
		case 6058: this.msg = "No se ha podido encontrar la deuda solicitada."; break;
		case 6059: this.msg = "El número ingresado no es válido."; break; 
		//Subdiario
		case 6060: this.msg = "Ya se generó un subdiario de caja de ese tipo para esa fecha."; break;
		case 6061: this.msg = "No se ha generado un subdiario de caja de ese tipo para esa fecha.";break;
		//Cheque
		case 6070: this.msg = "Las ordenes de pago corresponden a más de un proveedor. Un cheque puede ser emitido solo a un proveedor.";break;
		case 6071: this.msg = "No se puede hacer un cheque en diferido con la misma fecha de emisión y pago.";break;
		//ASociaciones Cuenta Tipo Tasa/ Tipo Modificador
		case 6080: this.msg = "Ya existe una asociacion Cuenta/Formula de Cálculo con esos parámetros.";break;
		case 6081: this.msg = "Ya existe una asociacion Cuenta/Modificador con esos parámetros.";break;
		case 6082: this.msg = "Ya existe una asociacion Cuenta/Concepto de Ingreso Vario con esos parámetros."; break;
		case 6083: this.msg = "Ya existe una asociacion Cuenta/Linea de Factura con esos parámetros."; break;
		case 6084: this.msg = "Ya existe una asociacion Cuenta/Refinanciacion con esos parámetros."; break;
		case 6085: this.msg = "Ya existe una asociacion Cuenta/Articulo con esos parametros.";break;
		//Asociaciones Cuenta/Interes-Recargo
		case 6086: this.msg = "Ya existe una asociacion de Cuenta con esos parámetros.";break;
		
		//Presupuesto
		case 6090: this.msg = "El presupuesto debe estar en estado INACTIVO antes de ser eliminado.";break;
		case 6091: this.msg = "Ya hay otros presupuestos ACTIVOS para este Periodo con alguna de estas cuentas."+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n"+this.getListaObjetoRelacion1();break;
		case 6092: this.msg = "Existen asientos contables que imputaron sobre este presupuesto."; break;
		
		//SAIC 
		case 6100: this.msg = "El registro de deuda ya tiene asociado un registro de cancelacion. Por favor, verifique los datos e intente nuevamente."; break;
		case 6101: this.msg = "El registro de deuda no es una Liquidación de Tasas."; break;
		case 6102: this.msg = "La deuda esta vencida."; break;
		case 6103: this.msg = "La deuda esta anulada."; break;
		case 6104: this.msg = "La deuda que intenta cobrar ha sido refinanciada. Por favor, verifique los datos e intente nuevamente."; break;
		case 6105: this.msg = "La deuda que intenta cobrar ha sido reliquidada. Por favor, verifique los datos e intente nuevamente."; break;
		case 6106: this.msg = "La deuda que intenta cobrar ha sido "+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n";; break;
		
		//CUENTA 
		case 6107: this.msg ="La cuanta asociada para pagos atrasados no pertenece al período actual";break;
		case 6108: this.msg ="La formula de cálculo utilizada para liquidar la obligacion no posee asociación con una cuenta para pagos atrasados: " + ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n";; break;
		case 6109: this.msg ="No se pudo eliminar la cuenta. La cuenta está siendo utilizada por una asociación. Por favor, verifique los datos e intente nuevamente."; break;
		case 6110: this.msg ="No se pudo eliminar la cuenta. La cuenta está asociada a un asiento contable."; break;
		case 6111: this.msg ="No se pudo eliminar la cuenta. La cuenta está asociada a una orden de compra."; break;
		case 6112: this.msg ="No se pudo eliminar la cuenta. La cuenta está asociada a un presupuesto."; break;
		case 6113: this.msg ="No se pudo eliminar la cuenta. La cuenta está asociada a un subdiario de caja."; break;
		case 6114: this.msg ="No se pudo eliminar la cuenta. La cuenta está asociada a un historico de balance."; break;
		case 6115: this.msg ="No se pudo eliminar la cuenta. La cuenta está asociada a un tipo de balance."; break;
		case 6116: this.msg ="No se pudo eliminar la cuenta. La cuenta posee cuentas dependientes."; break;
		case 6117: this.msg ="La fórmula de cálculo utilizada para liquidar la obligación no posee una cuenta contable asociada. Comuníquese con el contador."+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n"; break;
		case 6118: this.msg = "El concepto de ingreso vario no posee una cuenta contable asociada. Comuníquese con el contador."+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n";; break;
		case 6119: this.msg = "La cuenta no puede recibir asientos."; break;
				
		//CUENTA BANCARIA 
		case 6120: this.msg ="La cuenta bancaria tiene boletas de deposito asociadas."; break;
		case 6121: this.msg ="La cuenta bancaria tiene libros de banco asociados."; break;
		case 6122: this.msg ="La cuenta bancaria tiene cheques asociados."; break;
		
		//Orden Pago
		case 6130: this.msg ="El importe de la orden de pago es mayor al valor del cheque y/o comprobante de retención."; break;
		case 6131: this.msg ="No se puede confirmar el retiro de una Orden de Pago ya cancelada."; break;
		case 6132: this.msg ="La Orden de Pago ya se encuentra confirmada."; break;
		case 6133: this.msg ="El proveedor de la Orden de Pago debe ser el mismo que las ordenes de compra."; break;
		case 6134: this.msg ="El importe de la orden de pago es menor al valor del cheque y/o comprobante de retención."; break;
		case 6135: this.msg ="El importe de la orden de pago es mayor al valor del cheque."; break;
		case 6136: this.msg ="El importe de la orden de pago es menor al valor del cheque."; break;
		case 6137: this.msg ="No se puede modificar una orden de pago confirmada."; break;
		
		//Asiento Contable
		case 6140: this.msg ="El importe del debe y el haber deben ser iguales."; break;
		case 6141: this.msg ="Solo se puede eliminar el último asiento contable cargado."; break;
		case 6142: this.msg ="Ya existe un asiento contable con ese número.";break;
		case 6143: this.msg ="No se puede modificar un asiento contable generado automaticamente.";break;
		case 6144: this.msg ="El tipo de asiento es nulo.";break;
		case 6145: this.msg ="La cuenta contable se encuentra en mas de un Presupuesto ACTIVO para este Periodo: "+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n"+this.getListaObjetoRelacion1();break;
		case 6146: this.msg ="La cuenta contable no está asociada a ningun Presupuesto ACTIVO para este Periodo: "+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n"+this.getListaObjetoRelacion1(); break;
		case 6147: this.msg ="La cuenta contable no está asociada a ningun Presupuesto: "+ ((this.objetoRelacion1 != null)?this.objetoRelacion1.toString():"")+"\n"; break;
		
		//Parametro Retencion
		case 6150: this.msg ="El nombre del parametro de retencion no puede ser vacío"; break;
		case 6151: this.msg ="La alicuota del parametro de retencion es requerido y debe ser mayor a cero"; break;
		case 6152: this.msg ="El importe mínimo del parametro de retencion es requerido y debe ser mayor a cero"; break;
		case 6153: this.msg ="El porcentaje del parametro de retencion es requerido y debe ser mayor a cero"; break;
		
		//TIPO CUENTA 
		case 6160: this.msg ="No se pudo agregar el tipo de cuenta. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente."; break;
		case 6161: this.msg ="No se pudo modificar el tipo de cuenta. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente."; break;
		case 6162: this.msg ="El tipo de cuenta posee cuentas asociadas."; break;
		//TIPO BALANCE
		case 6165: this.msg ="No se pudo agregar el tipo de balance. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente."; break;
		case 6166: this.msg ="No se pudo modificar el tipo de balance. Los datos son inválidos. Por favor, verifiquelos e intente nuevamente."; break;
		case 6167: this.msg ="No se puede eliminar esta plantilla de balance ya que posee balances generados."; break;
		case 6168: this.msg ="No se puede modificar esta plantilla de balance ya que posee balances generados."; break;
		
		//Mayor
		case 6170: this.msg ="Los datos para ese mes ya estan generados. Por favor, verifique la fecha e intente nuevamente."; break;
		case 6171: this.msg ="No hay asientos para el mes ingresado. Por favor, verifique la fecha e intente nuevamente."; break;
				
		//Plan de Cuenta
		case 6180: this.msg = "El plan de cuenta ya posee cuentas asociadas."; break;
		//Libro de Banco
		case 6190: this.msg = "No se puede crear el libro de banco. Ya existe un libro de banco activo para esa cuenta bancaria."; break;
		case 6191: this.msg = "El libro de banco no puede tener lineas con monto igual a 0."; break;
		
		//Asociacion cuenta / linea factura
		case 6200: this.msg ="La cuenta no posee ninguna asociación"; break;
		
		//Comprobante de Retencion
		case 6210: this.msg ="No se puede eliminar un comprobante de retención que esta asociado a una orden de pago."; break;
		
		//Libro Diario
		case 6220: this.msg ="No se puede eliminar un libro diario con asientos contables."; break;
		//Todos
		case 6298: this.msg = "El número de identificación única no puede ser nulo. Por favor, verifique los datos e intente nuevamente.";break;
		case 6299: this.msg = "El número de identificación no corresponde a un id válido. Por favor verifique los datos e intente nuevamente.";break;
		
		/**
		 * SYSTEM
		 */
		// CAJA
		case 6300: this.msg = "No se ha podido agregar la caja. Por favor, intente nuevamente.";break;
		case 6301: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6302: this.msg = "No se han podido actualizar los datos de la caja. Por favor, intente nuevamente.";break;
		case 6303: this.msg = "No se ha podido eliminar la caja. Por favor, intente nuevamente.";break;
		case 6304: this.msg = "No se ha podido recuperar la lista de cajas. Por favor, intente nuevamente.";break;
		case 6305: this.msg = "No se ha podido recuperar la caja por IP. Por favor, intente nuevamente."; break;
		case 6306: this.msg = "No se ha podido restaurar la caja. Por favor, intente nuevamente."; break;
		case 6307: this.msg = "Ha ocurrido un error al cerrar la caja. Por favor, intente nuevamente."; break;
		
		//TICKET CAJA
		case 6310: this.msg = "No se ha podido agregar el ticket de caja. Por favor, intente nuevamente.";break;
		case 6311: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6312: this.msg = "No se ha podido recuperar la lista de tickets de caja. Por favor, intente nuevamente.";break;
		case 6313: this.msg = "No se ha podido cancelar el ticket. Por favor, intente nuevamente."; break;
		
		//CAJA CHICA
		case 6320: this.msg = "No se ha podido agregar la caja chica. Por favor, intente nuevamente.";break;
		case 6321: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6322: this.msg = "No se han podido actualizar los datos de la caja chica. Por favor, intente nuevamente.";break;
		case 6323: this.msg = "No se ha podido eliminar la caja chica. Por favor, intente nuevamente.";break;
		case 6324: this.msg = "No se ha podido recuperar la lista de cajas chicas. Por favor, intente nuevamente.";break;
		
		//MOVIMIENTO CAJA CHICA
		case 6330: this.msg = "No se ha podido agregar el movimiento de caja chica. Por favor, intente nuevamente.";break;
		case 6331: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6332: this.msg = "No se han podido actualizar los datos del movimiento de caja chica. Por favor, intente nuevamente.";break;
		case 6333: this.msg = "No se ha podido eliminar el movimiento de caja chica. Por favor, intente nuevamente.";break;
		case 6334: this.msg = "No se ha podido recuperar la lista de movimientos de caja chica. Por favor, intente nuevamente.";break;
		
		//MONEDA
		case 6340: this.msg = "No se ha podido agregar el tipo de moneda. Por favor, intente nuevamente.";break;
		case 6341: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6342: this.msg = "No se han podido actualizar los datos del tipo de  moneda. Por favor, intente nuevamente.";break;
		case 6343: this.msg = "No se ha podido eliminar del tipo de moneda. Por favor, intente nuevamente.";break;
		case 6344: this.msg = "No se ha podido recuperar la lista de tipos de monedas. Por favor, intente nuevamente.";break;
		
		//PLANILLA DIARIA CAJA
		case 6350: this.msg = "No se ha podido agregar la planilla diaria de la caja. Por favor, intente nuevamente.";break;
		case 6351: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6352: this.msg = "No se ha podido recuperar la lista de planillas diarias. Por favor, intente nuevamente.";break;
		case 6353: this.msg = "No se ha podido actualizar la planilla diaria. Por favor, intente nuevamente."; break;
		
		//CONCEPTO MOVIMIENTO CAJA CHICA
		case 6360: this.msg = "No se ha podido agregar el concepto de movimiento de caja chica. Por favor, intente nuevamente.";break;
		case 6361: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6362: this.msg = "No se han podido actualizar los datos del concepto de movimiento de caja chica. Por favor, intente nuevamente.";break;
		case 6363: this.msg = "No se ha podido eliminar el concepto de movimiento de caja chica. Por favor, intente nuevamente.";break;
		case 6364: this.msg = "No se ha podido recuperar la lista de conceptos de movimientos de caja chica. Por favor, intente nuevamente.";break;
		
		//ARQUEO DE CAJA
		case 6370: this.msg = "No se ha podido agregar el arqueo de caja. Por favor, intente nuevamente.";break;
		case 6371: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente.";break;
		case 6372: this.msg = "No se han podido actualizar los datos del arqueo de caja. Por favor, intente nuevamente.";break;
		case 6373: this.msg = "No se ha podido eliminar el arqueo de caja. Por favor, intente nuevamente.";break;
		case 6374: this.msg = "No se ha podido recuperar la lista de arqueos de caja. Por favor, intente nuevamente.";break;
		
		//Banco
		case 6380: this.msg = "No se ha podido agregar el banco. Por favor, intente nuevamente.";break;
		case 6381: this.msg = "No se han podido recuperar los datos del banco solicitado. Por favor, intente nuevamente.";break;
		case 6382: this.msg = "No se han podido actualizar los datos del banco. Por favor, intente nuevamente.";break;
		case 6383: this.msg = "No se ha podido eliminar el banco. Por favor, intente nuevamente.";break;
		case 6384: this.msg = "No se ha podido recuperar la lista de bancos. Por favor, intente nuevamente.";break;
		
		//Libro Banco
		case 6390: this.msg = "No se ha podido agregar el libro de banco. Por favor, intente nuevamente.";break;
		case 6391: this.msg = "No se han podido recuperar los datos del libro de banco solicitado. Por favor, intente nuevamente.";break;
		case 6392: this.msg = "No se han podido actualizar los datos del libro de banco. Por favor, intente nuevamente.";break;
		case 6393: this.msg = "No se ha podido eliminar el libro de banco banco. Por favor, intente nuevamente.";break;
		case 6394: this.msg = "No se ha podido recuperar la lista de libros de bancos. Por favor, intente nuevamente.";break;
		case 6395: this.msg = "No se ha podido generar el libro de banco. Por favor, intente nuevamente.";break;
		
		//SAIC
		case 6410: this.msg = "No se ha podido recuperar los datos solicitados. Por favor. intente nuevamente."; break;
		case 6411: this.msg = "No se ha podido obtener el vencimiento. Por favor. intente nuevamente."; break;
		case 6412: this.msg = "No se ha podido agregar el registro de cancelacion. Por favor. intente nuevamente."; break;
		case 6413: this.msg = "La deuda ya ha sido cancelada"; break; 
		
		//Cheque
		case 6420: this.msg = "No se ha podido agregar el cheque. Por favor, intente nuevamente.";break;
		case 6421: this.msg = "No se han podido recuperar los datos del cheque solicitado. Por favor, intente nuevamente.";break;
		case 6422: this.msg = "No se han podido actualizar los datos del cheque. Por favor, intente nuevamente.";break;
		case 6423: this.msg = "No se ha podido eliminar el cheque. Por favor, intente nuevamente.";break;
		case 6424: this.msg = "No se ha podido recuperar la lista de cheques. Por favor, intente nuevamente.";break;
		
		//Boleta Deposito
		case 6430: this.msg = "No se ha podido agregar la boleta de depósito. Por favor, intente nuevamente.";break;
		case 6431: this.msg = "No se han podido recuperar los datos de la boleta de depósito. Por favor, intente nuevamente.";break;
		case 6432: this.msg = "No se han podido actualizar los datos de la boleta de depósito. Por favor, intente nuevamente.";break;
		case 6433: this.msg = "No se ha podido eliminar la boleta de depósito. Por favor, intente nuevamente.";break;
		case 6434: this.msg = "No se ha podido recuperar la lista de boletas de depósitos. Por favor, intente nuevamente.";break;
		
		//Comprobante de Retención
		case 6440: this.msg = "No se ha podido agregar el comprobante de retención. Por favor, intente nuevamente.";break;
		case 6441: this.msg = "No se han podido recuperar los datos del comprobante de retención. Por favor, intente nuevamente.";break;
		case 6442: this.msg = "No se han podido actualizar los datos del comprobante de retención. Por favor, intente nuevamente.";break;
		case 6443: this.msg = "No se ha podido eliminar el comprobante de retención. Por favor, intente nuevamente.";break;
		case 6444: this.msg = "No se ha podido recuperar la lista de comprobantes de retención. Por favor, intente nuevamente.";break;
		
		//CUENTA
		case 6450: this.msg = "No se ha podido agregar la cuenta. Por favor, intente nuevamente."; break;
		case 6451: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 6452: this.msg = "No se han podido actualizar los datos de la cuenta. Por favor, intente nuevamente."; break;
		case 6453: this.msg = "No se ha podido eliminar la cuenta. Por favor, intente nuevamente."; break;
		
		//TIPO CUENTA
		case 6460: this.msg = "No se ha podido agregar el tipo de cuenta. Por favor, intente nuevamente."; break;
		case 6461: this.msg = "No se han podido recuperar los datos solicitados. Por favor, intente nuevamente."; break;
		case 6462: this.msg = "No se han podido actualizar los datos del tipo de cuenta. Por favor, intente nuevamente."; break;
		case 6463: this.msg = "No se ha podido eliminar el tipo de cuenta. Por favor, intente nuevamente."; break;
		case 6464: this.msg = "No se ha podido recuperar la lista de tipos de cuentas. Por favor, intente nuevamente."; break;
		
		//PLAN DE CUENTA
		case 6470: this.msg ="No se ha podido agregar el plan de cuenta. Por favor, intente nuevamente."; break;
		case 6471: this.msg ="No se han podido recuperar los datos del plan de cuenta solicitado. Por favor, intente nuevamente."; break;
		case 6472: this.msg ="No se han podido actualizar los datos del plan de cuenta. Por favor, intente nuevamente."; break;
		case 6473: this.msg ="No se ha podido eliminar el plan de cuenta. Por favor, intente nuevamente."; break;
		case 6474: this.msg ="No se ha podido recuperar la lista de planes de cuenta. Por favor, intente nuevamente."; break;
		
		//TIPO BALANCE
		case 6480: this.msg ="No se ha podido agregar la plantilla de balance. Por favor, intente nuevamente."; break;
		case 6481: this.msg ="No se han podido recuperar los datos de la plantilla de balance solicitada. Por favor, intente nuevamente."; break;
		case 6482: this.msg ="No se han podido actualizar los datos de la plantilla de balance. Por favor, intente nuevamente.";break;
		case 6483: this.msg ="No se ha podido eliminar la plantilla de balance. Por favor, intente nuevamente."; break;
		case 6484: this.msg ="No se ha podido recuperar la lista de plantillas de balance. Por favor, intente nuevamente."; break;
		
		//Cuenta Bancaria
		case 6490: this.msg ="No se ha podido agregar la cuenta bancaria. Por favor, intente nuevamente."; break;
		case 6491: this.msg ="No se han podido recuperar los datos de la cuenta bancaria solicitada. Por favor, intente nuevamente."; break;
		case 6492: this.msg ="No se han podido actualizar la cuenta bancaria. Por favor, intente nuevamente."; break;
		case 6493: this.msg ="No se ha podido eliminar la cuenta bancaria. Por favor, intente nuevamente."; break;
		case 6494: this.msg ="No se ha podido recuperar la lista de cuentas bancarias. Por favor, intente nuevamente."; break;
		
		//INGRESO VARIO
		case 6500: this.msg ="No se ha podido agregar el ingreso vario. Por favor, intente nuevamente."; break;
		case 6501: this.msg ="No se ha podido recuperar el ingreso vario. Por favor, intente nuevamente."; break;
		case 6502: this.msg ="No se ha podido actualizar el ingreso vario. Por favor, intente nuevamente."; break;
		case 6503: this.msg ="No se ha podido eliminar el ingreso vario. Por favor, intente nuevamente."; break;
		case 6504: this.msg ="No se ha podido recuperar la lista de ingresos varios. Por favor, intente nuevamente."; break;
		
		//CONCEPTO INGRESO VARIO
		case 6510: this.msg ="No se ha podido agregar el concepto de ingreso vario. Por favor, intente nuevamente."; break;
		case 6511: this.msg ="No se ha podido recuperar el concepto de ingreso vario. Por favor, intente nuevamente."; break;
		case 6512: this.msg ="No se ha podido actualizar el concepto de ingreso vario. Por favor, intente nuevamente."; break;
		case 6513: this.msg ="No se ha podido eliminar el concepto de ingreso vario. Por favor, intente nuevamente."; break;
		case 6514: this.msg ="No se ha podido recuperar la lista de conceptos de ingresos varios. Por favor, intente nuevamente."; break;
		case 6515: this.msg ="No se ha podido eliminar el concepto de ingreso vario ya que se encuetra asociado a un ingreso vario."; break;
		case 6516: this.msg = "No se puede agregar un Concepto de Ingreso Vario con un Nombre ya existente"; break;
		//Presupuesto
		case 6520: this.msg ="No se ha podido agregar el presupuesto. Por favor, intente nuevamente."; break;
		case 6521: this.msg ="No se ha podido recuperar el presupuesto. Por favor, intente nuevamente."; break;
		case 6522: this.msg ="No se ha podido actualizar el presupuesto. Por favor, intente nuevamente."; break;
		case 6523: this.msg ="No se ha podido eliminar el presupuesto. Por favor, intente nuevamente."; break;
		case 6524: this.msg ="No se ha podido recuperar la lista de presupuestos. Por favor, intente nuevamente."; break;
		case 6525: this.msg ="No se ha podido eliminar la lista de lineas de Presupuesto"; break;
		
		//Debito
		case 6530: this.msg = "No se ha podido agregar el debito. Por favor, intente nuevamente.";break;
		case 6531: this.msg = "No se han podido recuperar los datos del debito solicitado. Por favor, intente nuevamente.";break;
		case 6532: this.msg = "No se han podido actualizar los datos del debito. Por favor, intente nuevamente.";break;
		case 6533: this.msg = "No se ha podido eliminar el debito. Por favor, intente nuevamente.";break;
		case 6534: this.msg = "No se ha podido recuperar la lista de debitos. Por favor, intente nuevamente.";break;
		
		//Generar
		case 6540: this.msg ="No se ha podido generar el balance. Por favor, intente nuevamente."; break;
		case 6541: this.msg ="No se ha podido generar el mayor. Por favor, intente nuevamente."; break;
		
		//SubdiarioCaja
		case 6550: this.msg ="No se ha podido agregar el Subdiario de Caja. Por favor, intente nuevamente."; break;
		case 6551: this.msg ="No se ha podido recuperar los del Subdiario de Caja solicitado. Por favor, intente nuevamente."; break;
		case 6552: this.msg ="No se ha podido actualizar el Subdiario de Caja. Por favor, intente nuevamente."; break;
		case 6553: this.msg ="No se ha podido eliminar el Subdiario de Caja. Por favor, intente nuevamente."; break;
		case 6554: this.msg ="No se ha podido recuperar la lista de Subdiarios de Caja. Por favor, intente nuevamente."; break;
		case 6555: this.msg ="No se ha podido generar el Subdiario de Caja. Por favor, intente nuevamente."; break;
		
		//Libro Diario
		case 6560: this.msg ="No se ha podido agregar el Libro Diario. Por favor, intente nuevamente."; break;
		case 6561: this.msg ="No se ha podido recuperar los datos del Libro Diario solicitado. Por favor, intente nuevamente."; break;
		case 6562: this.msg ="No se ha podido actualizar el Libro Diario. Por favor, intente nuevamente."; break;
		case 6563: this.msg ="No se ha podido eliminar el Libro Diario. Por favor, intente nuevamente."; break;
		case 6564: this.msg ="No se ha podido recuperar la lista de Libros Diarios. Por favor, intente nuevamente."; break;
		
		//Folio Libro Diario
		case 6571: this.msg ="No se ha podido recuperar los datos del Folio de Libro Diario solicitado. Por favor, intente nuevamente."; break;
		case 6574: this.msg ="No se ha podido recuperar la lista de Folios de Libros Diarios. Por favor, intente nuevamente."; break;
		
		//Asiento Contable 
		case 6580: this.msg ="No se ha podido agregar el Asiento Contable. Por favor, intente nuevamente."; break;
		case 6581: this.msg ="No se ha podido recuperar los datos del Asiento Contable solicitado. Por favor, intente nuevamente."; break;
		case 6582: this.msg ="No se ha podido actualizar el Asiento Contable. Por favor, intente nuevamente."; break;
		case 6583: this.msg ="No se ha podido eliminar el Asiento Contable. Por favor, intente nuevamente."; break;
		case 6584: this.msg ="No se ha podido recuperar la lista de Asientos Contables. Por favor, intente nuevamente."; break;
		case 6585: this.msg ="No se ha podido obtener los datos del Subdiario de Caja solicitado. Por favor, intente nuevamente."; break;
		
		//Asociación Cuenta Tipo Modificador
		case 6590: this.msg ="No se ha podido agregar el asociación Cuenta - Tipo de Modificador. Por favor, intente nuevamente."; break;
		case 6591: this.msg ="No se ha podido recuperar los datos de la asociación Cuenta - Tipo de Modificador. Por favor, intente nuevamente."; break;
		case 6592: this.msg ="No se ha podido actualizar la asociación Cuenta - Tipo de Modificador. Por favor, intente nuevamente."; break;
		case 6593: this.msg ="No se ha podido eliminar la asociación Cuenta - Tipo de Modificador. Por favor, intente nuevamente."; break;
		case 6594: this.msg ="No se ha podido recuperar la lista de asociaciónes Cuenta - Tipo de Modificador. Por favor, intente nuevamente."; break;
		case 6595: this.msg ="El modificador no posee asociacion a una cuenta para pagos atrasados. Comuniquese con el contador: " + (this.objetoRelacion1 != null ? this.objetoRelacion1.toString() : "");break;
		case 6596: this.msg ="El modificador esta asociada a una cuenta de pagos atrasados que no corresponde con el período actual: " + (this.objetoRelacion1 != null ? this.objetoRelacion1.toString() : "");break;
		
		//Asociacion Cuenta Interes Recargo
		case 6597: this.msg ="El interes no posee asociacion a una cuenta para pagos atrasados. Comuniquese con el contador: " + (this.objetoRelacion1 != null ? this.objetoRelacion1.toString() : "");break;
		case 6598: this.msg ="El interes esta asociada a una cuenta de pagos atrasados que no corresponde con el período actual: " + (this.objetoRelacion1 != null ? this.objetoRelacion1.toString() : "");break;
		
		//Asociación Cuenta Tipo Tasa
		case 6600: this.msg ="No se ha podido agregar la asociación Cuenta - Formula de Cálculo. Por favor, intente nuevamente."; break;
		case 6601: this.msg ="No se ha podido recuperar los datos de la asociación Cuenta - Formula de Cálculo. Por favor, intente nuevamente."; break;
		case 6602: this.msg ="No se ha podido actualizar la asociación Cuenta - Formula de Cálculo. Por favor, intente nuevamente."; break;
		case 6603: this.msg ="No se ha podido eliminar la asociación Cuenta - Formula de Cálculo. Por favor, intente nuevamente."; break;
		case 6604: this.msg ="No se ha podido recuperar la lista de asociaciónes Cuenta - Formula de Cálculo. Por favor, intente nuevamente."; break;
		
		//Orden Pago
		case 6610: this.msg ="No se ha podido agregar la orden de pago. Por favor, intente nuevamente."; break;
		case 6611: this.msg ="No se ha podido recuperar los datos de la orden de pago solicitada. Por favor, intente nuevamente."; break;
		case 6612: this.msg ="No se ha podido actualizar la orden de pago. Por favor, intente nuevamente."; break;
		case 6613: this.msg ="No se ha podido eliminar la orden de pago. Por favor, intente nuevamente."; break;
		case 6614: this.msg ="No se ha podido recuperar la lista de ordenes de pago. Por favor, intente nuevamente."; break;
		case 6615: this.msg ="No se ha podido recuperar los datos de la linea de orden de pago solicitada. Por favor, intente nuevamente."; break;
		
		//Mayor
		case 6620: this.msg ="No se ha podido agregar el Mayor. Por favor, intente nuevamente."; break;
		case 6621: this.msg ="No se ha podido recuperar los datos del mayor. Por favor, intente nuevamente."; break;
		case 6622: this.msg ="No se ha podido actualizar el mayor. Por favor, intente nuevamente."; break;
		case 6623: this.msg ="No se ha podido eliminar el mayor. Por favor, intente nuevamente."; break;
		case 6624: this.msg ="No se ha podido recuperar la lista de mayores. Por favor, intente nuevamente."; break;
		
		//Balance
		case 6630: this.msg ="No se ha podido agregar el balance. Por favor, intente nuevamente."; break;
		case 6631: this.msg ="No se ha podido recuperar los datos del balance. Por favor, intente nuevamente."; break;
		case 6632: this.msg ="No se ha podido recuperar la lista de balances. Por favor, intente nuevamente."; break;
		case 6633: this.msg ="No se ha podido eliminar el balance. Por favor, intente nuevamente."; break;
		
		//CuentaRfr
		case 6640: this.msg ="No se ha podido obtener la referencia a la cuenta. Por favor, intente nuevamente."; break;
		
		//Deuda (Pagable)
		case 6650: this.msg ="No se ha podido recuperar los datos de la deuda. Por favor, intente nuevamente."; break;
		case 6651: this.msg ="La deuda que intenta cobrar ya ha sido saldada."; break;

		//Asociacion Cuenta -Concepto Ingreso Vario
		case 6660: this.msg ="No se ha podido agregar el asociación Cuenta - Concepto de Ingreso Vario. Por favor, intente nuevamente."; break;
		case 6661: this.msg ="No se ha podido recuperar los datos de la asociación Cuenta - Concepto de Ingreso Vario. Por favor, intente nuevamente."; break;
		case 6662: this.msg ="No se ha podido actualizar la asociación Cuenta - Concepto de Ingreso Vario. Por favor, intente nuevamente."; break;
		case 6663: this.msg ="No se ha podido eliminar la asociación Cuenta - Concepto de Ingreso Vario. Por favor, intente nuevamente."; break;
		case 6664: this.msg ="No se ha podido recuperar la lista de asociaciónes Cuenta - Concepto de Ingreso Vario. Por favor, intente nuevamente."; break;
		
		//Asociación Cuenta Linea de Factura
		case 6670: this.msg ="No se ha podido agregar la asociación Cuenta - Linea de Factura. Por favor, intente nuevamente."; break;
		case 6671: this.msg ="No se ha podido recuperar los datos de la asociación Cuenta - Linea de Factura. Por favor, intente nuevamente."; break;
		case 6672: this.msg ="No se ha podido actualizar la asociación Cuenta - Linea de Factura. Por favor, intente nuevamente."; break;
		case 6673: this.msg ="No se ha podido eliminar la asociación Cuenta - Linea de Factura. Por favor, intente nuevamente."; break;
		case 6674: this.msg ="No se ha podido recuperar la lista de asociaciónes Cuenta - Linea de Factura. Por favor, intente nuevamente."; break;
		
		//ReimpresionTicket
		case 6680: this.msg="No se ha podido dejar constancia de la reimpresion del ticket. Por favor, intente nuevamente."; break;
		//Movimientos de Ingreso
		case 6690: this.msg ="No se ha podido recuperar los datos de los movimientos de ingreso. Por favor, intente nuevamente."; break;
		//Movimientos de Egreso
		case 6700: this.msg ="No se ha podido recuperar los datos de los movimientos de egreso. Por favor, intente nuevamente."; break;
		
		//ParametroRetencion
		case 6710: this.msg ="No se ha podido agregar el parámetro de retención. Por favor, intente nuevamente."; break;
		case 6711: this.msg ="No se ha podido modificar el parámetro de retención. Por favor, intente nuevamente."; break;
		case 6712: this.msg ="No se ha podido eliminar el parámetro de retención. Por favor, intente nuevamente."; break;
		case 6713: this.msg ="No se ha podido recuperar la lista de parámetro de retención. Por favor, intente nuevamente."; break;
		case 6714: this.msg ="No se ha podido recuperar los datos de la retención. Por favor, intente nuevamente."; break;
		
		//ComprobanteRetencion
		case 6720: this.msg ="No se ha podido agregar el comprobante de retención. Debe tener al menos un parámetro de retención."; break;
		case 6721: this.msg ="No se ha podido agregar el comprobante de retención. Debe tener un período asociado."; break;
		case 6722: this.msg ="No se ha podido agregar el comprobante de retención. No se encotraron órdenes de pago con el perídodo y el proveedor seleccionado."; break;
		case 6723: this.msg ="No se ha podido eliminar el comprobante de retención. No tiene una orden de pago asiciada. Comuniquese con el encargado de sistemas."; break;
		case 6724: this.msg ="No se ha podido eliminar el comprobante de retención. La orden de pago a la que fue asignada ya ha sido confirmada."; break;
		case 6725: this.msg ="No se ha podido agregar el comprobante de retención. Ya existe un comprobate de retención asignado a la última órden de pago."; break;
		case 6726: this.msg ="No se ha podido agregar el parámetro de retención. Por favor, intente nuevamente."; break;
		case 6727: this.msg ="No se ha podido eliminar el parámetro de retención. Por favor, intente nuevamente."; break;
		//Orden Pago devolucion
		case 6730: this.msg ="No se ha podido confirmar la orden de pago. Por favor, intente nuevamente."; break;
		
		//ParametroAsociacion
		case 6740: this.msg ="No se ha podido agregar el parámetro de asociación. Debe tener una cuenta asociada."; break;
		case 6741: this.msg ="No se ha podido agregar el parámetro de asociación. El porcentaje debe ser mayor cero."; break;
		case 6742: this.msg ="No se ha podido agregar el parámetro de asociación. Por favor, intente nuevamente."; break;
		case 6743: this.msg ="No se ha podido modificar el parámetro de asociación. Por favor, intente nuevamente."; break;
		case 6744: this.msg ="No se ha podido eliminar el parámetro de asociación. Por favor, intente nuevamente."; break;
		case 6745: this.msg ="No se ha podido recuperar los datos del parametro de la asociación. Por favor, intente nuevamente."; break;
		case 6746: this.msg ="No se ha podido recuperar la lista de parámetro de asociación. Por favor, intente nuevamente."; break;
		
		//AsociacionRefinanciacion
		case 6750: this.msg ="No se ha podido agregar la asociación refinanciación. Debe existir al menos un parametro de asociación."; break;
		case 6751: this.msg ="No se ha podido agregar la asociación refinanciación. El período no puede ser nulo."; break;
		case 6752: this.msg ="No se ha podido agregar la asociación refinanciación. Ya existe un asociación refinanciación en el año especificado."; break;
		case 6753: this.msg ="La suma de los porcentajes de los parametros de asociación debe ser igual a 100."; break;
		case 6754: this.msg ="No se ha podido recuperar los datos de asociación refinanciación. Por favor, intente nuevamente."; break;
		case 6755: this.msg ="No se ha podido eliminar la asociación refinanciación. Por favor, intente nuevamente."; break;
		case 6756: this.msg ="No se ha podido actualizar la asociación refinanciación. Por favor, intente nuevamente."; break;
		case 6757: this.msg ="El documento de refinanciación no posee una cuenta contable asociada. Comuníquese con el contador."; break;
		
		//OrdenCompra
		case 6760: this.msg = "El estado de la orden de compra no es Cursada."; break;
		case 6761: this.msg = "La cuenta no posee suficiente monto presupuestado para confirmar la orden de compra.";break;
		case 6762: this.msg = "La cuenta no esta asociada a un presupuesto valido"; break;

		//Asociación Cuenta Interés-Recargo
		case 6770: this.msg ="No se ha podido agregar la asociación de la Cuenta. Por favor, intente nuevamente."; break;
		case 6771: this.msg ="No se ha podido recuperar los datos de la asociación de la Cuenta. Por favor, intente nuevamente."; break;
		case 6772: this.msg ="No se ha podido actualizar la asociación de la Cuenta. Por favor, intente nuevamente."; break;
		case 6773: this.msg ="No se ha podido eliminar la asociación de la Cuenta. Por favor, intente nuevamente."; break;
		case 6774: this.msg ="No se ha podido recuperar la lista de asociaciónes de la Cuenta. Por favor, intente nuevamente."; break;
		
		//Reporte Contable
		case 6780: this.msg ="No se ha podido agregar el Reporte Contable. Por favor, intente nuevamente."; break;
		case 6781: this.msg ="No se ha podido actualizar el Reporte Contable. Por favor, intente nuevamente."; break;
		case 6782: this.msg ="No se ha podido recuperar la lista de Reporte Contable. Por favor, intente nuevamente."; break;
		case 6783: this.msg ="No se ha podido eliminar el Reporte Contable. Por favor, intente nuevamente."; break;
		case 6784: this.msg ="No se ha podido recuperar los datos del Reporte Contable. Por favor, intente nuevamente."; break;
		
		//TicketCaja
		case 6790: this.msg ="El importe de la deuda ha cambiado desde que se imprimió la liquidación."; break;
		/** 
		 * PERMISOS
		 */
		// Error general para todas las llamadas
		case 6900: this.msg = "No posee permisos suficientes para realizar la operación. Póngase en contacto con el administrador.";break;
		case 6901: this.msg = "No se ha podido setear la llave. Póngase en contacto con el administrador.";break;
		
		// Impresion
		case 6950: this.msg = "Uno o más parametros requeridos no han sido completados para la impresión del reporte."; break;
		case 6951: this.msg = "No se encontró el archivo del reporte."; break;
		case 6952: this.msg = "No se ha podido generar el reporte."; break;
		
		default: this.msg = "Ha ocurrido un error inesperado.";
		}
		
	}
	

	public Object getObjetoRelacion1(){
		return this.objetoRelacion1;
	}

	public Object getObjetoRelacion2(){
		return this.objetoRelacion2;
	}

	public List<Object> getListaObjetoRelacion1() {
		return listaObjetoRelacion1;
	}
	

}
