<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.page1}" id="page1">
			<ui:html binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.html1}" id="html1">
			<ui:head binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.head1}" id="head1"
				title="Administración de Conceptos de Ingresos Varios">
				<ui:link binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td></td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.label1}" for="tfNombre" id="label1" style=""
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnBuscar}"
												action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnReiniciar_action}"
												binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnCancelar_action}"
												binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.paginatedTable}" styleClass="tablaPaginada"
											id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnSeleccionar_action}"
														binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnAgregar_action}"
														binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnModificar_action}"
														binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnEliminar_action}"
														binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnConsultar_action}"
														binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.textoSeparador}" escape="false"
														id="stSeparador4" />
													<ui:button action="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnExportar_action}"
														binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.hidIdPagina}" id="hidIdPagina"
						text="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.idPagina}" />
					<ui:hiddenField binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.idSubSesion}" />
					<ui:script binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
