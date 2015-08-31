<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.sun.com/web/ui" prefix="ui"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<script>
	function clickButton(id) {
		var idButton = id.replace("incTFNodo:P_", "");
		document.getElementById(idButton).click();
	}
</script>
<h:panelGrid styleClass="tablaInterna"
	style="-moz-border-radius: 0px 0px 5px 5px; width: 455px; height: 415px; background-color: rgb(242, 242, 236);">
	<ui:panelGroup styleClass="div" block="true" visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}">
		<ui:label text="NODO" binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.lTitulo}" />
	</ui:panelGroup>
	<ui:panelGroup block="true" visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}">
		<h:panelGrid columns="2">
			<ui:label for="NTFtfNombre" id="NTFlblNombre" styleClass="label" text="Nombre" />
			<ui:textField columns="40" id="NTFtfNombre" styleClass="textField" disabled="true"
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.tNombre}" />
		</h:panelGrid>
	</ui:panelGroup>
	<ui:panelGroup styleClass="div" visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}"
		style="background-color: rgb(231, 230, 230)" block="true">
		<ui:label text="Plazo" />
	</ui:panelGroup>
	<ui:panelGroup block="true" visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}">
		<h:panelGrid columns="5">
			<ui:label for="NTFtfCantDias" id="NTFlblCantDias" styleClass="label" text="Cantidad de Días" />
			<ui:textField columns="10" id="NTFtfCantDias" styleClass="textField"
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelPlazo.tfCantidadDias}" />
			<ui:checkbox id="NTFchbClasificacionSecano" label="Días corridos"
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelPlazo.chDiasCorridos}" />
		</h:panelGrid>
	</ui:panelGroup>
	<ui:panelGroup styleClass="div" block="true" visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}"
		style="background-color: rgb(231, 230, 230)">
		<ui:label text="Responsables" />
	</ui:panelGroup>
	<ui:panelGroup block="true" visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}">
		<h:panelGrid columns="2" columnClasses="col,col">
			<ui:panelGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.gpAreas1}" id="P_NTFgroupPanelAreas1"
				style="white-space: nowrap">
				<ui:button onClick="clickButton(this.id);" reset="true" id="P_NTFbtnAgregar" styleClass="button" text="Agregar" />
				<ui:button onClick="clickButton(this.id);" reset="true" id="P_NTFbtnQuitar" text="Quitar" styleClass="button" />
				<ui:staticText escape="false" id="P_NTFstaticText4" text="|" />
				<ui:button onClick="clickButton(this.id);" reset="true" styleClass="button" id="P_NTFbtnQuitarTodos" text="Quitar Todos" />
			</ui:panelGroup>
			<ui:panelGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.gpUsuarios1}" id="P_NTFgroupPanelUsuarios1"
				style="white-space: nowrap">
				<ui:button onClick="clickButton(this.id);" reset="true" id="P_NTFbtnAgregarUsuario" styleClass="button" text="Agregar" />
				<ui:button onClick="clickButton(this.id);" reset="true" styleClass="button" id="P_NTFbtnQuitarUsuario" text="Quitar" />
				<ui:staticText escape="false" id="P_NTFstaticTextUsuario4" text="|" />
				<ui:button onClick="clickButton(this.id);" reset="true" id="P_NTFbtnQuitarTodosUsuarios" text="Quitar todos" styleClass="button" />
			</ui:panelGroup>
			<ui:table augmentTitle="false" id="NTFtableAreas"
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.table}" style="width: 100px;">
				<ui:tableRowGroup id="NTFtableRowGroup1"
					sourceData="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.objectListDataProvider}"
					binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.tableRowGroup1}"
					sourceVar="currentRow">
					<ui:tableColumn binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.tcAreas1}" align="center" id="NTFtableColumn1"
						valign="middle" width="7">
						<ui:radioButton id="NTFradioButton1" label=""
							name="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.nombreButtonGroup}"
							binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.radioButton1}"
							selected="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.RBSelected}"
							selectedValue="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableAreas.currentRow}" />
					</ui:tableColumn>
					<ui:tableColumn headerText="Area" id="NTFtableColumn2">
						<ui:staticText id="NTFstaticText2" text="#{currentRow.value['area'].nombre}" />
					</ui:tableColumn>
					<ui:tableColumn headerText="Responsabilidad" id="NTFtcResponsabilidadArea">
						<ui:dropDown id="ddResponsabilidadArea" styleClass="textField"
							binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.ddResponsabilidadArea}"
							items="#{expedientes$ABMProcedimiento$ABMProcedimiento.optionsResponsabilidad}" selected="#{currentRow.value['responsabilidad']}"
							converter="EnumConverter" immediate="false" />
					</ui:tableColumn>
				</ui:tableRowGroup>
			</ui:table>
			<ui:table augmentTitle="false"
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.table}" id="tableUsuarios"
				style="width: 100px;">
				<ui:tableRowGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.tableRowGroup1}"
					id="tableRowGroupUsuario1"
					sourceData="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.objectListDataProvider}"
					sourceVar="currentRow">
					<ui:tableColumn align="center"
						binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.tableColumn1}"
						id="tableColumnUsuario1" valign="middle" width="7">
						<ui:radioButton id="radioButtonUsuario1" label=""
							name="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.nombreButtonGroup}"
							binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.radioButton1}"
							selected="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.RBSelected}"
							selectedValue="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.currentRow}" />
					</ui:tableColumn>
					<ui:tableColumn binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.tableColumn2}"
						headerText="Usuario" id="tableColumnUsuario2">
						<ui:staticText
							binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuarios.staticTextNombre}"
							id="staticTextusuario2" text="#{currentRow.value['usuario'].user}" />
					</ui:tableColumn>
					<ui:tableColumn headerText="Responsabilidad" id="NTFtcResponsabilidadUsuario">
						<ui:dropDown id="ddResponsabilidadUsuario" styleClass="textField"
							binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.ddResponsabilidadUsuario}"
							items="#{expedientes$ABMProcedimiento$ABMProcedimiento.optionsResponsabilidad}" selected="#{currentRow.value['responsabilidad']}"
							converter="EnumConverter" immediate="false" />
					</ui:tableColumn>
				</ui:tableRowGroup>
			</ui:table>
		</h:panelGrid>
	</ui:panelGroup>
	<ui:panelGroup styleClass="div" block="true" visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}"
		style="background-color: rgb(231, 230, 230)">
		<ui:label text="Usuarios extensores" />
	</ui:panelGroup>
	<ui:panelGroup binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.gpUsuariosExtensores}"
		visible="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.renderedNodo}" id="P_NTFgroupPanelUsuariosExtensores"
		style="white-space: nowrap">
		<ui:button onClick="clickButton(this.id);" reset="true" id="P_NTFbtnAgregarUsuariosExtensores" styleClass="button" text="Agregar" />
		<ui:button onClick="clickButton(this.id);" reset="true" styleClass="button" id="P_NTFbtnQuitarUsuarioExtensores" text="Quitar" />
		<ui:staticText escape="false" id="P_NTFstaticTextUsuarioExtensores" text="|" />
		<ui:button onClick="clickButton(this.id);" reset="true" id="P_NTFbtnQuitarTodosUsuariosExtensores" text="Quitar todos" styleClass="button" />
		<ui:table augmentTitle="false"
			binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.table}"
			id="tableUsuariosExtensores" style="width: 100px;">
			<ui:tableRowGroup
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.tableRowGroup1}"
				id="tableRowGroupUsuarioExtensores"
				sourceData="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.objectListDataProvider}"
				sourceVar="currentRow">
				<ui:tableColumn align="center"
					binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.tableColumn1}"
					id="tableColumnRadioUsuarioExtensores" valign="middle" width="7">
					<ui:radioButton id="radioButtonUsuarioExtensores" label=""
						name="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.nombreButtonGroup}"
						binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.radioButton1}"
						selected="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.RBSelected}"
						selectedValue="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.currentRow}" />
				</ui:tableColumn>
				<ui:tableColumn
					binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.tableColumn2}"
					headerText="Usuario" id="tableColumnUsuarioExtensores">
					<ui:staticText
						binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.staticTextNombre}"
						id="staticTextusuarioExtensores" text="#{currentRow.value['usuario'].user}" />
				</ui:tableColumn>
				<ui:tableColumn headerText="Dias Maximos" id="diasMaximos">
					<ui:textField text="#{currentRow.value['cantidadDias']}"
						binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelEditNodo.panelResponsable.tableUsuariosExtensores.tfCantidadDiasMaximo}"
						id="tfCantidadDiasMaximo" />
				</ui:tableColumn>
			</ui:tableRowGroup>
		</ui:table>
	</ui:panelGroup>
</h:panelGrid>