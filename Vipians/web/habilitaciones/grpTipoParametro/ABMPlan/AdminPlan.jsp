<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.head1}" id="head1" title="Administración de Planes">
				<ui:link binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.head1.title}" />
								</caption>
								<tbody>
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
											<ui:panelGroup binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.tfNombre}" columns="20" disabled="false"
																id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.lblTipoObligacion}" id="lblTipoObligacion"
																styleClass="label" text="Tipo Obligación" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.ddTipoObligacion}" id="ddTipoObligacion"
																items="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.ddTipoObligacionDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnBuscar}"
												action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnReiniciar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnCancelar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 102px">
										<ui:table binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnSeleccionar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.stSeparadorSeleccionar}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnAgregar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnModificar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnEliminar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.stSeparadorAccion}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnConsultar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumero').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
