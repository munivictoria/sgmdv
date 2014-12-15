<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.head1}" id="head1"
				title="Agregar Inspección Vehicular">
				<ui:link binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.body1}" focus="form1:tfFecha"
				id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.label1}"
											for="tfTransporteVehicular" id="label1" styleClass="label" text="Transporte Vehicular" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.tfTransporteVehicular}"
											columns="40" disabled="true" id="tfTransporteVehicular" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.label4}" for="tfInspector"
											id="label4" styleClass="label" text="Inspector" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.tfInspector}"
											columns="40" disabled="true" id="tfInspector" styleClass="textField" />
										<ui:button
											action="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnSeleccionarInspector_action}"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnSeleccionarInspector}"
											escape="false" id="btnSeleccionarInspector" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarInspector" reRender="form1:tfInspector"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnLimpiarInspector}"
											action="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnLimpiarInspector_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.label2}" for="tfFecha"
											id="label2" styleClass="label" text="Fecha" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.tfFecha}" id="tfFecha"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.label5}" for="ddEstado"
											id="label5" styleClass="label" text="Estado" />
									</td>
									<td>
										<ui:dropDown binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.ddEstado}" id="ddEstado"
											items="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.ddEstadoDefaultOptions.options}"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.label3}" for="taDescripcion"
											id="label3" styleClass="label" text="Descripción" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.taDescripcion}"
											columns="40" id="taDescripcion" rows="5" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnGuardar_action}"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnGuardar}" id="btnGuardar"
											styleClass="button" text="Aceptar" />
										<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.stSeparador}"
											escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnCancelar_action}"
											binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.btnCancelar}" id="btnCancelar"
											styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$AgregarInspeccionVehicular.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
