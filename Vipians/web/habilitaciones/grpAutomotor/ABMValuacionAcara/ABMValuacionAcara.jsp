<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfModelo", "modeloVehiculo", nombreBean, "setModeloVehiculoAutocompletar");
					}

					function focusearTfModeloVehiculo() {
						$("#form1\\:tfModelo").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.body1}" focus="form1:ddModelo" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.lblModelo}" for="tfModelo" id="lblModelo"
											styleClass="label" text="Modelo" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.tfModelo}" columns="40" id="tfModelo"
											styleClass="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.hayModelo ? 'textFieldDisabled' : 'textField'}"
											disabled="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.hayModelo}" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnSeleccionarModelo_action}"
											binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnSeleccionarModelo}" escape="false"
											id="btnSeleccionarModelo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarModelo" reRender="form1:tfModelo" title="Limpiar"
											binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnLimpiarModelo}"
											action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnLimpiarModelo_action}"
											styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfModeloVehiculo();" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.lblAnio}" for="tfAnio" id="lblAnio"
											styleClass="label" text="Año" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.tfAnio}" id="tfAnio"
											styleClass="textField" columns="4" maxLength="4" onKeyPress="return ValidarNum(event,this)" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.lblMoneda}" for="ddMoneda" id="lblMoneda"
											styleClass="label" text="Moneda" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.ddMoneda}" id="ddMoneda"
											items="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.ddMonedaDefaultOptions.options}" styleClass="textField" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.lblValor}" for="tfValor" id="lblValor"
											styleClass="label" text="Valor" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.tfValor}" id="tfValor"
											styleClass="textField" onKeyPress="return ValidarNum(event,this)" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.lblFechaBaja}" id="lblFechaBaja"
											styleClass="label" text="Fecha de Baja" for="tfFechaBaja" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.tfFechaBaja}" id="tfFechaBaja"
											styleClass="textField" maxLength="10" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.panelAtributoDinamico}"
														id="panelAtributoDinamico">
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnGuardar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnCancelar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
