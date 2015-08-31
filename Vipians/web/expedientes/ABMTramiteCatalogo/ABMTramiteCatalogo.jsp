<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="true">
													<ui:label binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.lblNombre}" for="tfNombre" id="lblNombre"
														styleClass="label" text="Nombre" />
												</td>
												<td>
													<ui:textField binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tfNombre}" columns="40" id="tfNombre"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="true">
													<ui:label binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.lblAvanzarFase}" for="cbAvanzarFase" id="lblAvanzarFase"
														styleClass="label" text="Requerido para avanzar de Fase" />
												</td>
												<td>
													<ui:checkbox binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.cbAvanzarFase}" id="cbAvanzarFase" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="true">
													<ui:label binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.lblReiniciaConFase}" for="cbReiniciaConFase"
														id="lblReiniciaConFase" styleClass="label" text="Reinicia con Fase" />
												</td>
												<td>
													<ui:checkbox binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.cbReiniciaConFase}" id="cbReiniciaConFase" />
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.stDocumentos}" id="stDocumentos"
														styleClass="label2" text="Documentos" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.table}" id="tableDC">
														<ui:tableRowGroup binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.tableRowGroup1}" id="tableRowGroup1"
															sourceData="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.objectListDataProvider}" sourceVar="currentRow">
															<ui:tableColumn align="center" binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.tableColumn1}"
																id="tableColumn1" valign="middle" width="10">
																<ui:radioButton binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.radioButton1}" id="radioButton1"
																	label="" name="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.nombreButtonGroup}"
																	selected="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.RBSelected}"
																	selectedValue="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.currentRow}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.tableColumn2}" headerText="Nombre"
																id="tableColumn2" sort="nombre" width="40">
																<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.staticText1}" id="staticText1"
																	text="#{currentRow.value['nombre']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.groupPanel1}" id="groupPanel1">
																<ui:button action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnAgregarDC_action}"
																	binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.btnAgregar}" id="btnAgregar" styleClass="button"
																	text="Agregar" />
																<a4j:commandButton action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnQuitarDC_action}"
																	binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.btnQuitar}" id="btnQuitar" value="Quitar"
																	styleClass="btnAjax" reRender="tableDC"
																	onmousedown="reemplazarClickConConfirmacionEnTabla('form1:tableDC', true, this, '');" />
																<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.staticText4}" escape="false"
																	id="staticText4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																<a4j:commandButton action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnQuitarTodosDC_action}"
																	binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableDC.btnQuitarTodos}" id="btnQuitarTodos"
																	value="Quitar todos" styleClass="btnAjax" reRender="tableDC"
																	onmousedown="reemplazarClickConConfirmacionEnTabla('form1:tableDC', false, this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.stEstadosTramite}" id="stEstadosTramite"
														styleClass="label2" text="Estados Trámite" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.table}"
														id="tableEstadoTramite">
														<ui:tableRowGroup binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.tableRowGroup1}" id="tableRowGroup2"
															sourceData="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.objectListDataProvider}" sourceVar="currentRow">
															<ui:tableColumn align="center" binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.tableColumn1}"
																id="tableColumnRbEstado" valign="middle" width="10">
																<ui:radioButton binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.radioButton1}" id="radioButton2"
																	label="" name="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.nombreButtonGroup}"
																	selected="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.RBSelected}"
																	selectedValue="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.currentRow}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.tableColumn2}" headerText="Nombre"
																id="tableColumnNombreEstado" sort="nombre" width="40">
																<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.staticText1}" id="staticText1"
																	text="#{currentRow.value['nombre']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.groupPanel1}" id="groupPanel1">
																<ui:button action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnAgregarET_action}"
																	binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.btnAgregar}" id="btnAgregarET" styleClass="button"
																	text="Agregar" />
																<a4j:commandButton action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnQuitarET_action}"
																	binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.btnQuitar}" id="btnQuitarET" value="Quitar"
																	styleClass="btnAjax" reRender="tableEstadoTramite"
																	onmousedown="reemplazarClickConConfirmacionEnTabla('form1:tableEstadoTramite', true, this, '');" />
																<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.staticText4}" escape="false"
																	id="staticText4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																<a4j:commandButton action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnQuitarTodosET_action}"
																	binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.tableET.btnQuitarTodos}" id="btnQuitarTodosET"
																	value="Quitar todos" styleClass="btnAjax" reRender="tableEstadoTramite"
																	onmousedown="reemplazarClickConConfirmacionEnTabla('form1:tableEstadoTramite', false, this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:messageGroup binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.messageGroup}" id="messageGroup"
														styleClass="grupoMsg" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnGuardar_action}"
											binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnCancelar_action}"
											binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>