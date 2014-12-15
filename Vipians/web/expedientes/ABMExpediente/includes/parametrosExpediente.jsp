<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.sun.com/web/ui" prefix="ui"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<ui:panelGroup
	binding="#{expedientes$ABMExpediente$AdminExpediente.pgParametros}"
	id="pgParametros">
	<table>

		<tr>
			<td align="right" nowrap="nowrap"><ui:label
					binding="#{expedientes$ABMExpediente$AdminExpediente.lblAsunto}"
					for="tfAsunto" id="lblAsunto" styleClass="label" text="Asunto" /></td>
			<td><ui:textField
					binding="#{expedientes$ABMExpediente$AdminExpediente.tfAsunto}"
					columns="40" id="tfAsunto" styleClass="textField" /></td>
		</tr>
		<tr>
			<td align="right" nowrap="nowrap"><ui:label
					binding="#{expedientes$ABMExpediente$AdminExpediente.lblNroRegistro}"
					for="tfNroRegistro" id="lblNroRegistro" styleClass="label"
					text="NroRegistro" /></td>
			<td><ui:textField
					binding="#{expedientes$ABMExpediente$AdminExpediente.tfNroRegistro}"
					columns="40" id="tfNroRegistro" styleClass="textField" /></td>
		</tr>

		<tr>
			<td align="right" nowrap="nowrap"><ui:label
					binding="#{expedientes$ABMExpediente$AdminExpediente.lblFechaRegistro}"
					id="lbFechaRegistro" styleClass="label" text="Fecha de Registro" />
			</td>
			<td><ui:textField
					binding="#{expedientes$ABMExpediente$AdminExpediente.tfFechaRegistro}"
					id="tfFechaRegistro" styleClass="textField"
					onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" /></td>
		</tr>

		<tr>
			<td align="right" nowrap="nowrap"><ui:label
					binding="#{expedientes$ABMExpediente$AdminExpediente.lblPersonaSeleccionada}"
					for="tfInteresado" id="label2" styleClass="label" text="Interesado" /></td>
			<td colspan="3" nowrap="nowrap"><ui:textField
					binding="#{expedientes$ABMExpediente$AdminExpediente.tfPersonaSeleccionada}"
					columns="40" id="tfInteresado" styleClass="textField"
					disabled="true" /> <ui:button
					action="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaFisica_action}"
					binding="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaFisica}"
					escape="false" id="btnSeleccionarPersonaFisica" mini="true"
					styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
				<ui:button
					action="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaJuridica_action}"
					binding="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaJuridica}"
					escape="false" id="btnSeleccionarPersonaJuridica" mini="true"
					styleClass="button" text="PJ"
					toolTip="Seleccionar Persona Jurídica" /> <a4j:commandButton
					action="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarPersona_action}"
					binding="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarPersona}"
					id="btnLimpiarPersona" styleClass="buttonLimpiarAjax"
					reRender="form1:incParametrosExpediente:tfInteresado" /></td>
		</tr>
		<tr>
			<td align="left" nowrap="nowrap"><ui:label
					binding="#{expedientes$ABMExpediente$AdminExpediente.labelProcedimiento}"
					for="ddProcedimiento" id="labelProcedimiento" styleClass="label"
					text="Procedimiento" /></td>
			<td><ui:dropDown
					binding="#{expedientes$ABMExpediente$AdminExpediente.ddProcedimiento}"
					id="ddProcedimiento"
					items="#{expedientes$ABMExpediente$AdminExpediente.ddProcedimientoOptions.options}"
					styleClass="textField" /> <ui:button
					action="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarProcedimiento_action}"
					binding="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarProcedimiento}"
					escape="false" id="btnSeleccionarProcedimiento" mini="true"
					styleClass="buttonSeleccionar" text="&amp;nbsp;"
					toolTip="Seleccionar" /> <a4j:commandButton
					id="btnLimpiarProcedimiento" reRender="form1:incParametrosExpediente:ddProcedimiento"
					binding="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarProcedimiento}"
					action="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarProcedimiento_action}"
					styleClass="buttonLimpiarAjax" /></td>
		</tr>
		<tr>

		</tr>
	</table>
</ui:panelGroup>