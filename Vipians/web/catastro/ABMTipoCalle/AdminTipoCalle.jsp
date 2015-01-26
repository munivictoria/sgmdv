<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMTipoCalle$AdminTipoCalle.page1}" id="page1">
			<ui:html binding="#{catastro$ABMTipoCalle$AdminTipoCalle.html1}" id="html1">
			<ui:head binding="#{catastro$ABMTipoCalle$AdminTipoCalle.head1}" id="head1" title="Administración de Tipos de Calle">
				<ui:link binding="#{catastro$ABMTipoCalle$AdminTipoCalle.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMTipoCalle$AdminTipoCalle.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMTipoCalle$AdminTipoCalle.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMTipoCalle$AdminTipoCalle.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMTipoCalle$AdminTipoCalle.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMTipoCalle$AdminTipoCalle.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMTipoCalle$AdminTipoCalle.label1}" id="label1" style="" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMTipoCalle$AdminTipoCalle.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMTipoCalle$AdminTipoCalle.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMTipoCalle$AdminTipoCalle.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnBuscar}"
												action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnReiniciar_action}"
												binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMTipoCalle$AdminTipoCalle.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnCancelar_action}"
												binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMTipoCalle$AdminTipoCalle.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{catastro$ABMTipoCalle$AdminTipoCalle.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMTipoCalle$AdminTipoCalle.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnSeleccionar_action}"
														binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMTipoCalle$AdminTipoCalle.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnAgregar_action}"
														binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnModificar_action}"
														binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnEliminar_action}"
														binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMTipoCalle$AdminTipoCalle.stSeparadorAccion}" escape="false" id="staticText8"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnConsultar_action}"
														binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMTipoCalle$AdminTipoCalle.textoSeparador}" escape="false" id="staticText9"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMTipoCalle$AdminTipoCalle.btnExportar_action}"
														binding="#{catastro$ABMTipoCalle$AdminTipoCalle.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMTipoCalle$AdminTipoCalle.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMTipoCalle$AdminTipoCalle.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{catastro$ABMTipoCalle$AdminTipoCalle.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMTipoCalle$AdminTipoCalle.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMTipoCalle$AdminTipoCalle.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMTipoCalle$AdminTipoCalle.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMTipoCalle$AdminTipoCalle.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMTipoCalle$AdminTipoCalle.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMTipoCalle$AdminTipoCalle.idSubSesion}" />
					<ui:script binding="#{catastro$ABMTipoCalle$AdminTipoCalle.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMTipoCalle$AdminTipoCalle.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
