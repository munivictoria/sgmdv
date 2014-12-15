<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.head1}" id="head1"
				title="Administración de Consumos Básicos">
				<ui:link binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.body1}" focus="form1:btnBuscar" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td colspan="2">
											<div>
												Haga clic en <b>Buscar</b> para ver los Consumos Básicos existentes.
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnBuscar}"
												action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnReiniciar_action}"
												binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnCancelar_action}"
												binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.paginatedTable}" id="table1" width="359">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnSeleccionar_action}"
														binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.stSeparadorSeleccionar}" escape="false"
														id="staticText6" />
													<ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnAgregar_action}"
														binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnModificar_action}"
														binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnEliminar_action}"
														binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnConsultar_action}"
														binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.staticText9}" escape="false"
														id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnExportar_action}"
														binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnBuscar').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
