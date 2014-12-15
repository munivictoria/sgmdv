<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.head1}" id="head1"
				title="Modificar Línea de Factura de Proveedor">
				<ui:link binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.body1}" focus="form1:btnSeleccionarBienProvisto"
				id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="amarillo">
								<caption>
									<ui:staticText binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.head1.title}" />
								</caption>
								<tr>
									<td></td>
								</tr>
								<tbody>
									<tr>
										<td align="right" nowrap="true">
											<ui:label binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.lblProveedor}" for="tfProveedor"
												id="lblProveedor" styleClass="label" text="Proveedor" />
										</td>
										<td>
											<ui:textField binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.tfProveedor}" columns="40" disabled="true"
												id="tfProveedor" styleClass="textField" />
										</td>
									</tr>
									<tr>
										<td align="right" nowrap="true">
											<ui:label binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.lblBienProvisto}" id="lblBienProvisto"
												styleClass="label" text="Bien" />
										</td>
										<td>
											<ui:textField binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.tfBienProvisto}" columns="40" disabled="true"
												id="tfBienProvisto" styleClass="textField" />
											<ui:button action="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnSeleccionarBienProvisto_action}"
												binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnSeleccionarBienProvisto}" escape="false"
												id="btnSeleccionarBienProvisto" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
											<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBienProvisto"
												binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnLimpiarBien}"
												action="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax" />
										</td>
									</tr>
									<tr>
										<td align="right" nowrap="nowrap">
											<ui:label binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.lblCuenta}" id="lblCuenta" styleClass="label"
												text="Cuenta" />
										</td>
										<td>
											<ui:textField binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.tfCuenta}" columns="40" disabled="true"
												id="tfCuenta" styleClass="textField" />
											<ui:button action="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnSeleccionarCuenta_action}"
												binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnSeleccionarCuenta}" escape="false"
												id="btnSeleccionarCuenta" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
											<a4j:commandButton id="btnLimpiarCuenta" reRender="form1:tfCuenta"
												binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnLimpiarCuenta}"
												action="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnLimpiarCuenta_action}" styleClass="buttonLimpiarAjax" />
										</td>
									</tr>
									<tr>
										<td align="right" nowrap="nowrap">
											<ui:label binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.lblMonto}" id="lblMonto" styleClass="label"
												text="Monto Unitario" />
										</td>
										<td>
											<ui:textField binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.tfMonto}" columns="15" id="tfMonto"
												styleClass="textField" />
											<ui:label binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.lblCantidad}" id="lblCantidad" styleClass="label"
												text="Cantidad" />
											<ui:textField binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.tfCantidad}" columns="10" id="tfCantidad"
												styleClass="textField" />
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<ui:button action="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnAgregarLinea_action}"
												binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnAgregarLinea}" id="btnAgregarLinea" styleClass="button"
												text="Agregar Línea" />
											<ui:button action="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnAceptar_action}"
												binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnAceptar}" id="btnAceptar" styleClass="button"
												text="Aceptar" />
											<ui:staticText binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.staticText9}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnCancelar_action}"
												binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
						<div>
							<ui:messageGroup binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.messageGroup}" id="messageGroup"
								showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
						</div>
					</div>
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{compras$ABMFacturaProveedor$ModificarLineaFacturaProveedor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
