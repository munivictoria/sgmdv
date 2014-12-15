<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.page1}" id="page1">
			<ui:html binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.html1}" id="html1">
			<ui:head binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.head1}" id="head1" title="Administración de Municipalidades">
				<ui:link binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMMunicipalidad$AdminMunicipalidad.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.label4}" for="tfNombre" id="label4" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
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
											<a4j:commandButton binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnBuscar}"
												action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnReiniciar_action}"
												binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnCancelar_action}"
												binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnSeleccionar_action}"
														binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.stSeparadorSeleccionar}" escape="false"
														id="staticText6" />
													<ui:button action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnAgregar_action}"
														binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnAgregar}" disabled="true" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnModificar_action}"
														binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnEliminar_action}"
														binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnEliminar}" disabled="true" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.stSeparadorAccion}" escape="false" id="staticText8" />
													<ui:button action="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnConsultar_action}"
														binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMMunicipalidad$AdminMunicipalidad.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMMunicipalidad$AdminMunicipalidad.idSubSesion}" />
					<ui:script binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMMunicipalidad$AdminMunicipalidad.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
