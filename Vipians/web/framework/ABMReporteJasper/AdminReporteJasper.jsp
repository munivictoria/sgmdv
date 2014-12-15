<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMReporteJasper$AdminReporteJasper.page1}" id="page1">
			<ui:html binding="#{framework$ABMReporteJasper$AdminReporteJasper.html1}" id="html1">
			<ui:head binding="#{framework$ABMReporteJasper$AdminReporteJasper.head1}" id="head1" title="Administración Reportes">
				<ui:link binding="#{framework$ABMReporteJasper$AdminReporteJasper.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMReporteJasper$AdminReporteJasper.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMReporteJasper$AdminReporteJasper.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdSubSesion hidIdPagina | btnCancelar">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMReporteJasper$AdminReporteJasper.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMReporteJasper$AdminReporteJasper.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMReporteJasper$AdminReporteJasper.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMReporteJasper$AdminReporteJasper.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMReporteJasper$AdminReporteJasper.tfNombre}" id="tfNombre" styleClass="textField" />
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
											<a4j:commandButton binding="#{framework$ABMReporteJasper$AdminReporteJasper.btnBuscar}"
												action="#{framework$ABMReporteJasper$AdminReporteJasper.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMReporteJasper$AdminReporteJasper.btnReiniciar_action}"
												binding="#{framework$ABMReporteJasper$AdminReporteJasper.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{framework$ABMReporteJasper$AdminReporteJasper.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMReporteJasper$AdminReporteJasper.btnCancelar_action}"
												binding="#{framework$ABMReporteJasper$AdminReporteJasper.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMReporteJasper$AdminReporteJasper.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMReporteJasper$AdminReporteJasper.paginatedTable}" id="table1" style="align:center"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMReporteJasper$AdminReporteJasper.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMReporteJasper$AdminReporteJasper.btnSeleccionar_action}"
														binding="#{framework$ABMReporteJasper$AdminReporteJasper.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMReporteJasper$AdminReporteJasper.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMReporteJasper$AdminReporteJasper.btnAgregar_action}"
														binding="#{framework$ABMReporteJasper$AdminReporteJasper.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMReporteJasper$AdminReporteJasper.btnModificar_action}"
														binding="#{framework$ABMReporteJasper$AdminReporteJasper.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:staticText binding="#{framework$ABMReporteJasper$AdminReporteJasper.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMReporteJasper$AdminReporteJasper.btnConsultar_action}"
														binding="#{framework$ABMReporteJasper$AdminReporteJasper.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{framework$ABMReporteJasper$AdminReporteJasper.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_4" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMReporteJasper$AdminReporteJasper.paginatedTable.botonOrdenamiento}" />
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
					<ui:hiddenField binding="#{framework$ABMReporteJasper$AdminReporteJasper.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMReporteJasper$AdminReporteJasper.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMReporteJasper$AdminReporteJasper.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMReporteJasper$AdminReporteJasper.idSubSesion}" />
					<ui:script binding="#{framework$ABMReporteJasper$AdminReporteJasper.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<ui:script binding="#{framework$ABMReporteJasper$AdminReporteJasper.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
