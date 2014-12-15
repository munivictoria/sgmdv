<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.tfNombre}" id="tfNombre" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.lblMarca}" for="ddMarca" id="lblMarcaVehiculo"
											styleClass="label" text="Marca" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.ddMarca}" id="ddMarca"
											items="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.ddMarcaDefaultOptions.options}" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnSeleccionarMarca_action}"
											binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnSeleccionarMarca}" escape="false" id="btnSeleccionarMarca"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarMarca" reRender="form1:ddMarca" title="Limpiar"
											binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnLimpiarMarca}"
											action="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnLimpiarMarca_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.lblMinimo}" for="tfMinimo" id="lblMinimo" styleClass="label"
											text="Mínimo" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.tfMinimo}" columns="40" id="tfMinimo"
											styleClass="textField" />
									</td>

									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.lblTipoVehiculo}" for="ddTipoVehiculo" id="lblTipoVehiculo"
											styleClass="label" text="Tipo Vehiculo" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.ddTipoVehiculo}" id="ddTipoVehiculo"
											items="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.ddTipoVehiculoDefaultOptions.options}" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnSeleccionarTipoVehiculo_action}"
											binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnSeleccionarTipoVehiculo}" escape="false"
											id="btnSeleccionarTipoVehiculo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarTipoVehiculo" reRender="form1:ddTipoVehiculo" title="Limpiar"
											binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnLimpiarTipoVehiculo}"
											action="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnLimpiarTipoVehiculo_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.lblDescripcion}" for="taDescripcion" id="lblDescripcion"
											styleClass="label" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.taDescripcion}" columns="40" id="taDescripcion"
											styleClass="textField" maxLength="200" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.panelAtributoDinamico}" id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnGuardar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnCancelar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
