<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.link1}" id="link1" url="/resources/stylesheet.css" />
				<f:loadBundle basename="muni.expedientes.includesURL" var="url" />
				<script>
					<![CDATA[
						function seleccionarNodoRaiz() {
							//  selecciona el nado raiz despues de elimina cualquier otro nodo y a la vez controla que  
							// 	no se limpie el mensaje que se esta mostrando.
							var componenteRaiz = document.getElementById("form1:incTreeProcedimiento:trProcedimiento:tnRaizText");
							var child = componenteRaiz.childNodes[0];
							var componenteMensaje = document.getElementById("form1:messageGroup");
							if (componenteMensaje == null){
								child.click();
							}
						}
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.form1}" id="form1">
					<div class="formularioABM">
						<TABLE border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMProcedimiento$ABMProcedimiento.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td>
										<table class="verde" style="border: 0px; padding: 5px 1px; width: 100%; border-radius: 10px; box-shadow: 0px -2px 10px #BBB;">
											<tr>
												<td align="right" nowrap="true">
													<ui:label binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
														text="Nombre" />
												</td>
												<td>
													<ui:textField binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.tfNombre}" columns="40" id="tfNombre"
														styleClass="textField" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 1px; padding-right: 1px;" valign="top">
										<TABLE class="tablaInterna" width="100%" style="-moz-border-radius: 0px 0px 5px 5px;">
											<tbody>
												<tr>
													<td colspan="2">
														<div class="div">
															<ui:label binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.lblResponsable}" for="hResponsable"
																id="lblResponsable" styleClass="label" style="font-weight:bold" text="Responsables" />
															<ui:hiddenField binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.hResponsable}" id="hResponsable" />
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<ui:panelGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.groupPanel1}"
															id="groupPanelAreas1">
															<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnAgregarArea_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.btnAgregar}" id="btnAgregar"
																styleClass="button" text="Agregar" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnQuitarArea_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.btnQuitar}" id="btnQuitar"
																value="Quitar" styleClass="btnAjax" reRender="tableAreas" onmousedown="reemplazarClickConConfirmacion(this, '');" />
															<ui:staticText binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.staticText4}"
																escape="false" id="staticText4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnQuitarTodasAreas_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.btnQuitarTodos}" id="btnQuitarTodos"
																value="Quitar todos" styleClass="btnAjax" reRender="tableAreas"
																onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
														</ui:panelGroup>
													</td>
													<td>
														<ui:panelGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableUsuarios.groupPanel1}"
															id="groupPanelUsuarios1">
															<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnAgregarUsuario_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableUsuarios.btnAgregar}" id="btnAgregarUsuario"
																styleClass="button" text="Agregar" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnQuitarUsuario_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableUsuarios.btnQuitar}" id="btnQuitarUsuario"
																value="Quitar" styleClass="btnAjax" reRender="tableUsuarios" onmousedown="reemplazarClickConConfirmacion(this, '');" />
															<ui:staticText binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableUsuarios.staticText4}"
																escape="false" id="staticTextUsuario4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnQuitarTodosUsuarios_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableUsuarios.btnQuitarTodos}"
																id="btnQuitarTodosUsuarios" value="Quitar todos" styleClass="btnAjax" reRender="tableUsuarios"
																onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
														</ui:panelGroup>
													</td>
												</tr>
												<tr>
													<td valign="top">
														<a4j:include id="incTableAreasResponsables" viewId="#{url.tableAreasResponsables}" />
													</td>
													<td valign="top">
														<a4j:include id="incTableUsuariosResponsables" viewId="#{url.tableUsuariosResponsables}" />
													</td>
												</tr>
											</tbody>
										</TABLE>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 1px; padding-right: 1px;" valign="top">
										<h:panelGrid styleClass="tablaInterna" width="100%" style="-moz-border-radius: 0px 0px 5px 5px;">
											<h:panelGroup styleClass="div" layout="block">
												<h:outputText value="Plazo" />
											</h:panelGroup>
											<h:panelGrid columns="3">
												<ui:label binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelPlazo.lblCantidadDias}" for="tfCantDias"
													id="lblCantDias" styleClass="label" text="Cantidad de Días" />
												<ui:textField binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelPlazo.tfCantidadDias}" columns="4" id="tfCantDias"
													styleClass="textField" />
												<ui:checkbox binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelPlazo.chDiasCorridos}" id="chbClasificacionSecano"
													label="Días corridos"></ui:checkbox>
											</h:panelGrid>
										</h:panelGrid>
									</td>
								</tr>
								<tr>
									<td>
										<TABLE style="background-color: rgb(221, 221, 221);">
											<tbody>
												<tr>
													<td colspan="2">
														<ui:label binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.labelFases}" id="label2"
															styleClass="label2" text="Estructura de Procedimiento" />
													</td>
												</tr>
												<tr>
													<td colspan="3" style="background-color: rgb(242, 242, 236);">
														<ui:panelGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.gpBotones}" id="gpBotones">
															<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnAgregarFase_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.btnAgregarFase}" id="btnAgregarFase"
																styleClass="button" text="Agregar Fase" />
															<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnAgregarFaseEspecial_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.btnAgregarFaseEspecial}" id="btnAgregarFaseEspecial"
																styleClass="button" text="Agregar Fase Especial" />
															<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnAgregarTramite_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.btnAgregarTramite}" id="btnAgregarTramite"
																styleClass="button" text="Agregar Tramite" />
															<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnAgregarDocumento_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.btnAgregarDocumento}" id="btnAgregarDocumento"
																styleClass="button" text="Agregar Documento" />
															<ui:staticText binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.staticText1}" escape="false"
																id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
															<a4j:commandButton reRender="form1:incTreeProcedimiento:trProcedimiento, form1:btnQuitarElemento"
																action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnQuitarElemento_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.btnQuitarElemento}" id="btnQuitarElemento"
																styleClass="btnAjax" value="Quitar Elemento" oncomplete="seleccionarNodoRaiz()" onmousedown="reemplazarClickConConfirmacion(this, '');" />
														</ui:panelGroup>
													</td>
												</tr>
												<tr>
													<td align="left" valign="top" style="min-width: 350px">
														<a4j:include id="incTreeProcedimiento" viewId="#{url.treeProcedimiento}">
														</a4j:include>
													</td>
													<td>
														<h:panelGrid cellspacing="1" cellpadding="0">
															<a4j:commandButton style="height: 40px;" styleClass="buttonPosicionAjax buttonFirst"
																action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnFirstNodo_action}" id="id_1"
																rendered="#{expedientes$ABMProcedimiento$ABMProcedimiento.renderPanelOrden}"
																reRender="form1:incTreeProcedimiento:trProcedimiento" />
															<a4j:commandButton style="height: 40px;" styleClass="buttonPosicionAjax buttonPrevious"
																action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnPreviousNodo_action}" id="id_2"
																rendered="#{expedientes$ABMProcedimiento$ABMProcedimiento.renderPanelOrden}"
																reRender="form1:incTreeProcedimiento:trProcedimiento" />
															<a4j:commandButton style="height: 40px;" styleClass="buttonPosicionAjax buttonNext"
																action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnNextNodo_action}" id="id_3"
																rendered="#{expedientes$ABMProcedimiento$ABMProcedimiento.renderPanelOrden}"
																reRender="form1:incTreeProcedimiento:trProcedimiento" />
															<a4j:commandButton style="height: 40px;" styleClass="buttonPosicionAjax buttonLast"
																action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnLastNodo_action}" id="id_4"
																rendered="#{expedientes$ABMProcedimiento$ABMProcedimiento.renderPanelOrden}"
																reRender="form1:incTreeProcedimiento:trProcedimiento" />
														</h:panelGrid>
													</td>
													<td valign="top">
														<a4j:include id="incTFNodo" viewId="#{url.panelNodoTF}"></a4j:include>
													</td>
												</tr>
												<tr>
													<td>
														<ui:panelGroup visible="false"
															binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.groupPanel1}"
															id="NTFgroupPanelAreas1">
															<ui:button action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnAgregarArea_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.btnAgregar}"
																id="NTFbtnAgregar" styleClass="button" text="Agregar" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnQuitarArea_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.btnQuitar}"
																id="NTFbtnQuitar" value="Quitar" styleClass="btnAjax" reRender="NTFtableAreas"
																onmousedown="reemplazarClickConConfirmacion(this, '');" />
															<ui:staticText
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.staticText4}"
																escape="false" id="NTFstaticText4" text="|" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnQuitarTodasAreas_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.btnQuitarTodos}"
																id="NTFbtnQuitarTodos" value="Quitar todos" styleClass="btnAjax" reRender="NTFtableAreas"
																onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
														</ui:panelGroup>
														<ui:panelGroup visible="false"
															binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.groupPanel1}"
															id="NTFgroupPanelUsuarios1">
															<ui:button action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnAgregarUsuario_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.btnAgregar}"
																id="NTFbtnAgregarUsuario" styleClass="button" text="Agregar" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnQuitarUsuario_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.btnQuitar}"
																id="NTFbtnQuitarUsuario" value="Quitar" styleClass="btnAjax" reRender="tableUsuarios"
																onmousedown="reemplazarClickConConfirmacion(this, '');" />
															<ui:staticText
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.staticText4}"
																escape="false" id="NTFstaticTextUsuario4" text="|" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnQuitarTodosUsuarios_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.btnQuitarTodos}"
																id="NTFbtnQuitarTodosUsuarios" value="Quitar todos" styleClass="btnAjax" reRender="tableUsuarios"
																onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
														</ui:panelGroup>
														
														
														
														<ui:panelGroup visible="false"
															binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.groupPanel1}"
															id="NTFgroupPanelUsuariosExtensores">
															<ui:button action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnAgregarUsuarioExtensores_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.btnAgregar}"
																id="NTFbtnAgregarUsuariosExtensores" styleClass="button" text="Agregar" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnQuitarUsuarioExtensores_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.btnQuitar}"
																id="NTFbtnQuitarUsuarioExtensores" value="Quitar" styleClass="btnAjax" reRender="tableUsuariosExtensores"
																onmousedown="reemplazarClickConConfirmacion(this, '');" />
															<ui:staticText
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.staticText4}"
																escape="false" id="NTFstaticTextUsuarioExtensores" text="|" />
															<a4j:commandButton action="#{expedientes$ABMProcedimiento$EditarNodoBean.btnQuitarTodosUsuariosExtensores_action}"
																binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.btnQuitarTodos}"
																id="NTFbtnQuitarTodosUsuariosExtensores" value="Quitar todos" styleClass="btnAjax" reRender="tableUsuariosExtensores"
																onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
														</ui:panelGroup>
													</td>
												</tr>
											</tbody>
										</TABLE>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<a4j:outputPanel ajaxRendered="true">
											<ui:messageGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
										</a4j:outputPanel>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnGuardar_action}"
											binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnCancelar_action}"
											binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</TABLE>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMProcedimiento$ABMProcedimiento.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMProcedimiento$ABMProcedimiento.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>