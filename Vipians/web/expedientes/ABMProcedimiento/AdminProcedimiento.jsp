<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{expedientes$ABMProcedimiento$AdminProcedimiento.head1.title}" />
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
											<ui:panelGroup binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.ddEstado}" id="ddEstado"
																items="#{expedientes$ABMProcedimiento$AdminProcedimiento.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
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
											<a4j:commandButton binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnBuscar}"
												action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnReiniciar_action}"
												binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnCancelar_action}"
												binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<ui:messageGroup binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" />
							</div>
							<div class="divGeneral">
								<table class="general">
									<tr>
										<td>
											<ui:table binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.paginatedTable}" id="table1">
												<f:facet name="actionsTop">
													<ui:panelGroup binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.groupPanel1}" id="groupPanel1" style="">
														<ui:button action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnSeleccionar_action}"
															binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.stSeparadorSeleccionar}" escape="false"
															id="stSeparador2" />
														<ui:button action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnAgregar_action}"
															binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnModificar_action}"
															binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnEliminar_action}"
															binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
														<ui:staticText binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.stSeparadorAccion}" escape="false" id="stSeparador3" />
														<ui:button action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnConsultar_action}"
															binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.stSeparador4}" escape="false" id="stSeparador4"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnExportar_action}"
															binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
															onClick="return exportarReporte()" />
													</ui:panelGroup>
												</f:facet>
											</ui:table>
										</td>
									</tr>
									<tr>
										<td align="left" colspan="2">
											<ui:label binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.lblEncontrados}" id="lblEncontrados" styleClass="label2"
												text="Registros Encontrados: " />
											<ui:staticText binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.stCantidadRegistros}" id="stCantidadRegistros"
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
					<ui:hiddenField binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMProcedimiento$AdminProcedimiento.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMProcedimiento$AdminProcedimiento.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMProcedimiento$AdminProcedimiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>