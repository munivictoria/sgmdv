<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMTipoBien$AdminTipoBien.page1}" id="page1">
			<ui:html binding="#{compras$ABMTipoBien$AdminTipoBien.html1}" id="html1">
			<ui:head binding="#{compras$ABMTipoBien$AdminTipoBien.head1}" id="head1" title="Categoría Bien">
				<ui:link binding="#{compras$ABMTipoBien$AdminTipoBien.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMTipoBien$AdminTipoBien.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMTipoBien$AdminTipoBien.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMTipoBien$AdminTipoBien.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMTipoBien$AdminTipoBien.head1.title}" />
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
											<ui:panelGroup binding="#{compras$ABMTipoBien$AdminTipoBien.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td nowrap="nowrap" align="right">
															<ui:label binding="#{compras$ABMTipoBien$AdminTipoBien.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMTipoBien$AdminTipoBien.tfNombre}" columns="20" disabled="false" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMTipoBien$AdminTipoBien.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMTipoBien$AdminTipoBien.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMTipoBien$AdminTipoBien.btnBuscar}"
												action="#{compras$ABMTipoBien$AdminTipoBien.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMTipoBien$AdminTipoBien.btnReiniciar_action}"
												binding="#{compras$ABMTipoBien$AdminTipoBien.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{compras$ABMTipoBien$AdminTipoBien.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMTipoBien$AdminTipoBien.btnCancelar_action}"
												binding="#{compras$ABMTipoBien$AdminTipoBien.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMTipoBien$AdminTipoBien.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 102px">
										<ui:table binding="#{compras$ABMTipoBien$AdminTipoBien.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMTipoBien$AdminTipoBien.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMTipoBien$AdminTipoBien.btnSeleccionar_action}"
														binding="#{compras$ABMTipoBien$AdminTipoBien.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMTipoBien$AdminTipoBien.stSeparadorSeleccionar}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMTipoBien$AdminTipoBien.btnAgregar_action}"
														binding="#{compras$ABMTipoBien$AdminTipoBien.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMTipoBien$AdminTipoBien.btnModificar_action}"
														binding="#{compras$ABMTipoBien$AdminTipoBien.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:staticText binding="#{compras$ABMTipoBien$AdminTipoBien.stSeparadorAccion}" escape="false" id="stSeparador2" />
													<ui:button action="#{compras$ABMTipoBien$AdminTipoBien.btnEliminar_action}"
														binding="#{compras$ABMTipoBien$AdminTipoBien.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText text="#{compras$ABMTipoBien$AdminTipoBien.textoSeparador}" escape="false" id="stSeparador5" />
													<ui:button action="#{compras$ABMTipoBien$AdminTipoBien.btnConsultar_action}"
														binding="#{compras$ABMTipoBien$AdminTipoBien.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{compras$ABMTipoBien$AdminTipoBien.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMTipoBien$AdminTipoBien.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMTipoBien$AdminTipoBien.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMTipoBien$AdminTipoBien.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMTipoBien$AdminTipoBien.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMTipoBien$AdminTipoBien.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMTipoBien$AdminTipoBien.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMTipoBien$AdminTipoBien.idSubSesion}" />
					<ui:script binding="#{compras$ABMTipoBien$AdminTipoBien.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
