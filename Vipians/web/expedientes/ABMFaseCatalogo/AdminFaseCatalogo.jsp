<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.head1}" id="head1" title="Administración Fase de Catálogo">
				<ui:link binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.head1.title}" />
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
											<ui:panelGroup binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.ddEstado}" id="ddEstado"
																items="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnBuscar}"
												action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnReiniciar_action}"
												binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnCancelar_action}"
												binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<ui:messageGroup binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnSeleccionar_action}"
														binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnAgregar_action}"
														binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnModificar_action}"
														binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnEliminar_action}"
														binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnConsultar_action}"
														binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnActivar_action}"
														binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnActivar}" id="btnActivar" styleClass="button"
														text="Recuperar Fase" />
													<ui:staticText binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnExportar_action}"
														binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
