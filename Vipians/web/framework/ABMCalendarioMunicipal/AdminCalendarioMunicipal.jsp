<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.page1}" id="page1">
			<ui:html binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.html1}" id="html1">
			<ui:head binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.head1}" id="head1" title="Administración de Calendarios">
				<ui:link binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.lblNombre}" for="tfNombre" id="label2"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.tfNombre}" columns="30" id="tfNombre"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.lblAnio}" for="tfAnio" id="lblAnio"
																styleClass="label" text="Año" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.tfAnio}" columns="8" id="tfAnio"
																styleClass="textField" maxLength="4" onKeyPress="return ValidarNum(event,this)" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.lblTipoObligacion}" for="ddTipoObligacion"
																id="lblTipoObligacion" styleClass="label" text="Tipo de Obligación" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.ddTipoObligacion}" id="ddTipoObligacion"
																items="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.ddTipoObligacionDefaultOptions.options}"
																valueChangeListener="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.valueChangeEvent(event)}"
																styleClass="textField">
																<a4j:support event="onChange" reRender="form1:ddPlan" />
															</ui:dropDown>
														</td>
														<td>
															<ui:label binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.lblPlan}" for="ddPlan" id="lblPlan"
																styleClass="label" text="Plan" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.ddPlan}" id="ddPlan"
																items="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.ddPlanDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnBuscar}"
												action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnReiniciar_action}"
												binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnCancelar_action}"
												binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.paginatedTable}" id="table1"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnSeleccionar_action}"
														binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnAgregar_action}"
														binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnModificar_action}"
														binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnEliminar_action}"
														binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnConsultar_action}"
														binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.textoSeparador}" escape="false"
														id="stSeparador6" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnClonar_action}"
														binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnClonar}" id="btnClonar" styleClass="button"
														text="Clonar" />
													<ui:staticText text="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.textoSeparador}" escape="false"
														id="stSeparador4" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnExportar_action}"
														binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.idSubSesion}" />
					<ui:script binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>