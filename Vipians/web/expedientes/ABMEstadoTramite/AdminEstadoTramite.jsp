<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.head1}" id="head1" title="Administración Estado Trámite">
				<ui:link binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.head1.title}" />
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
											<ui:panelGroup binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.ddEstado}" id="ddEstado"
																items="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.ddEstadoDefaultOptions.options}" styleClass="textField" />
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
											<a4j:commandButton binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnBuscar}"
												action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnReiniciar_action}"
												binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnCancelar_action}"
												binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnSeleccionar_action}"
														binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnAgregar_action}"
														binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnModificar_action}"
														binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnEliminar_action}"
														binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnConsultar_action}"
														binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnActivar_action}"
														binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnActivar}" id="btnActivar" styleClass="button"
														text="Recuperar Estado Trámite" />
													<ui:staticText binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnExportar_action}"
														binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMEstadoTramite$AdminEstadoTramite.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
