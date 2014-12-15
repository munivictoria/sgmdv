<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.page1}" id="page1">
			<ui:html binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.html1}" id="html1">
			<ui:head binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.head1}" id="head1">
				<ui:link binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.body1}" focus="form1:tfParcela" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMRegistroMejora$ABMRegistroMejora.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="left" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.label5}" for="tfSuperficie" id="label5" styleClass="label"
											text="Superficie" />
										<ui:textField binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.tfSuperficie}" id="tfSuperficie" styleClass="textField" />
										<ui:staticText binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.staticText1}" escape="false" id="staticText1"
											styleClass="label" text=" [m&lt;sup&gt;2&lt;/sup&gt;]" />
									</td>
									<td align="left" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.label2}" for="tfAnioConstruccion" id="label2" styleClass="label"
											text="Año de Construcción" />
										<ui:textField binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.tfAnioConstruccion}" id="tfAnioConstruccion" maxLength="4"
											styleClass="textField" />
										<ui:staticText binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.staticText2}" escape="false" id="staticText2"
											styleClass="label" text="&amp;nbsp;[aaaa]" />
									</td>
								</tr>
								<tr>
									<td align="left" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.label6}" for="tfCategoria" id="label6" styleClass="label"
											text="Categoría" />
										<ui:textField binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.tfCategoria}" columns="40" disabled="true" id="tfCategoria"
											styleClass="textField" />
										<ui:button action="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnSeleccionarCategoria_action}"
											binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnSeleccionarCategoria}" escape="false" id="btnSeleccionarCategoria"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCategoria" reRender="form1:tfCategoria"
											binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnLimpiarCategoria}"
											action="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnLimpiarCategoria_action}" styleClass="buttonLimpiarAjax" />
									</td>
									<td align="left" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.label3}" for="ddEstadoMejora" id="label3" styleClass="label"
											text="Estado de Mejora" />
										<ui:dropDown binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.ddEstadoMejora}" id="ddEstadoMejora"
											items="#{catastro$ABMRegistroMejora$ABMRegistroMejora.ddEstadoMejoraDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td><br></br></td>
								</tr>
								<tr>
									<td colspan="4" style="padding-left: 20px; padding-right: 1px;" valign="top">
										<div class="div" style="width: 280px; height: 15px;">Declaración Jurada</div>
									</td>
								</tr>
								<tr>
										<td colspan="4" style="padding-left: 20px" >
											<ui:label binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.lblNumeroDJ}" for="tfNumeroDJ" id="lblNumeroDJ" styleClass="label" 
													text="Número"/>
												<ui:textField binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.tfNumeroDJ}" id="tfNumeroDJ" styleClass="textField" columns="10"/>
											<ui:label binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.lblFechaInscripcionDJ}" for="tfFechaInscripcionDJ" id="lblFechaInscripcionDJ"
													styleClass="label" text="Fecha de Inscripción" />
											<ui:textField binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.tfFechaInscripcionDJ}" id="tfFechaInscripcionDJ"
													styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" columns="10"/>
										</td>
								</tr>
								<tr>
									<td><br></br></td>
								</tr>
								<tr>
									<td colspan="4" style="padding-left: 20px; padding-right: 1px;" valign="top">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.panelAtributoDinamico}" id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnGuardar_action}"
											binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnCancelar_action}"
											binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
							<tr>
								<td align="center">
										<h:panelGrid columns="4">
											<ui:label binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
											<ui:textArea binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
										</h:panelGrid>
									</td>
								</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMRegistroMejora$ABMRegistroMejora.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMRegistroMejora$ABMRegistroMejora.idSubSesion}" />
					<ui:script binding="#{catastro$ABMRegistroMejora$ABMRegistroMejora.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
