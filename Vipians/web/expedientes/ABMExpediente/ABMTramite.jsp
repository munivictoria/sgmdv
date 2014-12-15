<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMExpediente$ABMTramite.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMExpediente$ABMTramite.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMExpediente$ABMTramite.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMExpediente$ABMTramite.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					$(document).ready(function() {
						$('#form1').attr("autocomplete", "off");
					});

					function seleccionarTodos() {
						$checks = $("#form1").find("input[id *= 'cbPresentado']");
						for(var i = 0; i < $checks.length; i++) {
							$("#" + $checks.eq(i).attr("id").replace(/:/g, "\\:")).prop('checked', true);
						}
					}
					
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{expedientes$ABMExpediente$ABMTramite.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMExpediente$ABMTramite.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMExpediente$ABMTramite.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMExpediente$ABMTramite.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.lblResponsable}" id="lblResponsable" styleClass="label"
											text="Responsable" />
									</td>
									<td colspan="3">
										<ui:panelGroup binding="#{expedientes$ABMExpediente$ABMTramite.panelResponsable}" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField disabled="true" binding="#{expedientes$ABMExpediente$ABMTramite.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.lblEstado}" for="ddEstado" id="label10" styleClass="label" text="Estado" />
									</td>
									<td colspan="4" nowrap="nowrap">
										<ui:dropDown binding="#{expedientes$ABMExpediente$ABMTramite.ddEstado}"
											items="#{expedientes$ABMExpediente$ABMTramite.ddEstadoDefaultOptions.options}" id="ddEstado" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="4" align="left" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.lblComentarios}" for="tfComentarios" id="lblComentarios"
											styleClass="label" text="Comentarios" />
									</td>
								</tr>
								<tr>
									<td colspan="4" align="left" nowrap="true">
										<ui:textArea binding="#{expedientes$ABMExpediente$ABMTramite.taComentarios}" columns="90" id="taComentarios" styleClass="textArea" />
									</td>
								</tr>
								<tr>
									<td colspan="4" align="left" nowrap="true">
										<ui:label for="lbHitos" id="lblHitos" styleClass="label" text="Historial de Tramitación" />
									</td>
								</tr>
								<tr>
									<td colspan="4" align="left" nowrap="true">
										<ui:listbox binding="#{expedientes$ABMExpediente$ABMTramite.lbHitos}" id="lbHitos"
											items="#{expedientes$ABMExpediente$ABMTramite.lbHitosDefaultOptions.options}" rows="5" style="width:750px" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.pgPlazo}">
											<h:panelGrid columns="10" width="100%">
												<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.lblFechaInicioPlazo}" for="tfFechaInicioPlazo"
													id="lblFechaInicioPlazo" styleClass="label" text="Inicio Plazo" />
												<ui:textField disabled="true" columns="11"
													binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.tfFechaInicioPlazo}" styleClass="textField"
													id="tfFechaInicioPlazo" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
												<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.lblCantidadDias}" for="tfCantDias"
													id="lblCantDias" styleClass="label" text="Días De Plazo" />
												<ui:textField disabled="true" binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.tfCantidadDias}" columns="3"
													id="tfCantDias" styleClass="textField" />
												<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.lblCantidadDiasRestantes}"
													for="tfCantDiasRestantes" id="lblCantDiasRestantes" styleClass="label" text="Días Restantes" />
												<ui:textField disabled="true" binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.tfCantidadDiasRestantes}"
													columns="3" id="tfCantDiasRestantes" styleClass="textField" />
												<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.lblFechaFinPlazo}" for="tfFechaFinPlazo"
													id="lblFechaFinPlazo" styleClass="label" text="Fin Plazo" />
												<ui:textField disabled="true" columns="11"
													binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.tfFechaFinPlazo}" styleClass="textField"
													id="tfFechaFinPlazo" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
												<ui:checkbox binding="#{expedientes$ABMExpediente$ABMTramite.panelPlazoExpediente.chDiasCorridos}" disabled="true"
													id="chbClasificacionSecano" label="días corridos"></ui:checkbox>
											</h:panelGrid>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.titulo}" id="tituloDocumentos" styleClass="label2"
											text="Documentos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.table}" id="tableDocumentos">
											<ui:tableRowGroup binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.objectListDataProvider}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.tableColumn1}" id="tableColumn1"
													valign="middle" width="10" rendered="false">
													<ui:radioButton binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.radioButton1}" id="radioButton1" label=""
														name="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.nombreButtonGroup}"
														selectedValue="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.tableColumn2}" headerText="Nombre"
													id="tableColumn2" sort="plantilla" width="40">
													<ui:staticText binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.staticText1}" id="staticText1"
														text="#{currentRow.value['plantilla']}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Presentado" sort="presentado" id="tableColumnPresentado" width="10">
													<ui:checkbox binding="#{expedientes$ABMExpediente$ABMTramite.cbPresentado}" selected="#{currentRow.value['presentado']}"
														id="cbPresentado" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Observación" sort="observacion" id="tableColumnObservacion" width="10">
													<ui:textArea binding="#{expedientes$ABMExpediente$ABMTramite.taObservacion}" id="taObservacion"
														text="#{currentRow.value['observacion']}" styleClass="textField" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.groupPanel1}" id="groupPanel1">
													<a4j:commandButton binding="#{expedientes$ABMExpediente$ABMTramite.btnSeleccionarTodosDocumentosPresentados}"
														id="btnSeleccionarTodosDocumentosPresentados" styleClass="btnAjax" value="Marcar todos"
														oncomplete="cargarComportamientoJQuery();" onclick="seleccionarTodos();"/>
													<ui:button action="#{expedientes$ABMExpediente$ABMTramite.btnModificarDocumento_action}"
														binding="#{expedientes$ABMExpediente$ABMTramite.tableDocumentos.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{expedientes$ABMExpediente$ABMTramite.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMExpediente$ABMTramite.btnGuardar_action}"
											binding="#{expedientes$ABMExpediente$ABMTramite.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMExpediente$ABMTramite.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMExpediente$ABMTramite.btnCancelar_action}"
											binding="#{expedientes$ABMExpediente$ABMTramite.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMTramite.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMExpediente$ABMTramite.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMTramite.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMExpediente$ABMTramite.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMExpediente$ABMTramite.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
