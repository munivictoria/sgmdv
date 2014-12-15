<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.head1}" id="head1" title="Agregar Clausura Temporaria">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.body1}" focus="form1:tfFechaAlta" id="body1"
				onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 22px">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.label2}" for="tfFechaAlta" id="label2"
											styleClass="label" text="Fecha de Alta" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.tfFechaAlta}" id="tfFechaAlta"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.staticText2}" escape="false"
                                                id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.label3}" for="tfFechaBaja" id="label3"
											styleClass="label" text="Fecha de Baja" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.tfFechaBaja}" id="tfFechaBaja"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 18px">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.label5}" for="tfRol" id="label5"
											styleClass="label" text="Rol" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.tfRol}" columns="40" disabled="true"
											id="tfRol" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnSeleccionarRol_action}"
											binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnSeleccionarRol}" escape="false" id="btnSeleccionarRol"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarRol" reRender="form1:tfRol"
											binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnLimpiarRol}"
											action="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnLimpiarRol_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.label4}" for="taDescripcion" id="label4"
											styleClass="label" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.taDescripcion}" columns="40" id="taDescripcion"
											rows="4" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnGuardar_action}"
											binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Guardar" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnCancelar_action}"
											binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMClausuraSHPS$AgregarClausuraSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
