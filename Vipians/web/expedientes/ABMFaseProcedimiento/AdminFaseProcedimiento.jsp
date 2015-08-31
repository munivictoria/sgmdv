<?xml version="1.0" encoding="UTF-8"?>

<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->

<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.head1}" id="head1"
				title="Administración Fase de Procedimiento">
				<ui:link binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.head1.title}" />
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
											<ui:panelGroup binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
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
											<a4j:commandButton binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnBuscar}"
												action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnReiniciar_action}"
												binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnCancelar_action}"
												binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<ui:messageGroup binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.messageGroup}" id="messageGroup"
									showDetail="true" showSummary="false" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnSeleccionar_action}"
														binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnAgregar_action}"
														binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnModificar_action}"
														binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnEliminar_action}"
														binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnConsultar_action}"
														binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.stSeparador4}" escape="false"
														id="stSeparador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnExportar_action}"
														binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>