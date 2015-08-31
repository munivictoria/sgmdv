<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.head1.title}" />
								</caption>
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
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.ddEstado}" id="ddEstado"
																items="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td>
															<ui:label binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.lblTipo}" for="ddTipo" id="lblTipo"
																styleClass="label" text="Tipo" />
														</td>
														<td>
															<ui:dropDown binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.ddTipo}" id="ddTipo"
																items="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.ddTipoOptions.options}" styleClass="textField" />
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
											<a4j:commandButton binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnBuscar}"
												action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnReiniciar_action}"
												binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnCancelar_action}"
												binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<ui:messageGroup binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.messageGroup}" id="messageGroup"
									showDetail="true" showSummary="false" />
							</div>
							<div class="divGeneral">
								<table class="general">
									<tr>
										<td>
											<ui:panelGroup binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.pgSeleccionTipo}" id="pgSeleccionTipo">
												<ui:label binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.lblSeleccionTipo}" for="ddSeleccionTipo"
													id="lblSeleccionTipo" styleClass="label" text="Tipo de Documento" />
												<ui:staticText escape="false" id="stSeleccionTipo" styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
												<ui:dropDown binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.ddSeleccionTipo}" id="ddSeleccionTipo"
													items="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.ddSeleccionTipoOptions.options}" styleClass="textField" />
											</ui:panelGroup>
										</td>
									</tr>
									<tr>
										<td>
											<ui:table binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.paginatedTable}" id="table1">
												<f:facet name="actionsTop">
													<ui:panelGroup binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.groupPanel1}" id="groupPanel1" style="">
														<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnSeleccionar_action}"
															binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.stSeparadorSeleccionar}" escape="false"
															id="stSeparador2" />
														<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnAgregar_action}"
															binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnAgregar}" id="btnAgregar" styleClass="button"
															text="Agregar" />
														<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnModificar_action}"
															binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnEliminar_action}"
															binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnEliminar}" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.stSeparadorAccion}" escape="false"
															id="stSeparador3" />
														<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnConsultar_action}"
															binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnActivar_action}"
															binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnActivar}" id="btnActivar" styleClass="button"
															text="Recuperar Documento" />
														<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.stSeparador4}" escape="false"
															id="stSeparador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnExportar_action}"
															binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</f:facet>
											</ui:table>
										</td>
									</tr>
									<tr>
										<td align="left" colspan="2">
											<ui:label binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.lblEncontrados}" id="lblEncontrados"
												styleClass="label2" text="Registros Encontrados: " />
											<ui:staticText binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.stCantidadRegistros}" id="stCantidadRegistros"
												styleClass="staticText" />
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>