<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.head1}" id="head1" title="Administración de Inspectores">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.label2}" for="tfNombre" id="label2"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.label1}" for="tfPersonaFisica" id="label1"
																styleClass="label" text="Persona" />
														</td>
														<td>
															<!-- <ui:textField binding="#{catastro$ABMManzana$AdminManzana.tfZona}" columns="40" disabled="true" id="tfZona" styleClass="textField"/>
                                                <ui:button action="#{catastro$ABMManzana$AdminManzana.btnSeleccionarZona_action}"
                                                           binding="#{catastro$ABMManzana$AdminManzana.btnSeleccionarZona}" escape="false" id="btnSeleccionarZona"
                                                           mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>-->
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.tfPersonaFisica}" columns="40" disabled="true"
																id="tfPersonaFisica" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersonaFisica" reRender="form1:tfPersonaFisica" title="Limpiar"
																binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnLimpiarPersonaFisica}"
																action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnLimpiarPersonaFisica_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnBuscar}"
												action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnReiniciar_action}"
												binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnCancelar_action}"
												binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnSeleccionar_action}"
														binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnAgregar_action}"
														binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnModificar_action}"
														binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnEliminar_action}"
														binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnConsultar_action}"
														binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.textoSeparador}" escape="false" id="stSeparador5" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnExportar_action}"
														binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMInspector$AdminInspector.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
