<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.head1}" id="head1"
				title="Modificar Línea de Solicitud de Suministro">
				<ui:link binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.body1}" focus="form1:tfArea" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.form1}" id="form1">
					<div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.head1.title}" />
							</caption>
							<tbody>

								<tr>
									<td></td>
								</tr>

								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.lblBien}" id="lblBien" styleClass="label"
											text="Bien Asociado" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.tfBien}" columns="40" disabled="true"
											id="tfBien" styleClass="textField" />
										<ui:button action="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnSeleccionarBien_action}"
											binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnSeleccionarBien}" escape="false"
											id="btnSeleccionarBien" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBien"
											binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnLimpiarBien}"
											action="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<!--<tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.lblOrdenCompra}" id="lblOrdenCompra"
                                                          styleClass="label" text="Orden de Compra"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.tfOrdenCompra}" id="tfOrdenCompra"
                                                              disabled="true" styleClass="textField" columns="40"/>
                                                <ui:button action="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.btnSeleccionarOrden_action}"
                                                           binding="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.btnSeleccionarOrden}" escape="false"
                                                           id="btnSeleccionarOrden" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                                <ui:button action="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.btnLimpiarOrden_action}"
                                                           binding="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.btnLimpiarOrden}" id="btnLimpiarOrden"
                                                           escape="false" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                            </td>
                                        </tr>-->
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.lblCantidad}" id="lblCantidad"
											styleClass="label" text="Cantidad" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.tfCantidad}" columns="5" id="tfCantidad"
											onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>

								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnGuardar_action}"
											binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Guardar" />
										<ui:staticText binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnCancelar_action}"
											binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMSolicitudSuministro$ModificarLineaSolicitudSuministro.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
