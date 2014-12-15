<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.head1}" id="head1" title="Agregar Permiso">
				<ui:link binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.label1}" for="tfPadre" id="label1"
											styleClass="label" text="Agregar a" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.tfPadre}" columns="40" disabled="true"
											id="tfPadre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.label4}" for="tfNombre" id="label4"
											styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.label2}" for="tfPosibleAutorizador" id="label2"
											styleClass="label" text="Posible Autorizador" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.tfPosibleAutorizador}" columns="40"
											id="tfPosibleAutorizador" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.label3}" for="tfRol" id="label3"
											styleClass="label" text="Rol" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.tfRol}" columns="40" disabled="true"
											id="tfRol" styleClass="textField" />
										<ui:button action="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnSeleccionarRol_action}"
											binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnSeleccionarRol}" escape="false" id="btnSeleccionarRol"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarRol" reRender="form1:tfRol"
											binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnLimpiarRol}"
											action="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnLimpiarRol_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnGuardar_action}"
											binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Aceptar" />
										<ui:staticText binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnCancelar_action}"
											binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMPlantillaPermiso$AgregarPlantillaPermiso.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
