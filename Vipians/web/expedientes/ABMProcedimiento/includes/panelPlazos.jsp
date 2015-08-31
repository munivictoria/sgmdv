<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.sun.com/web/ui" prefix="ui"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<h:panelGrid styleClass="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; widht:100%">
	<h:panelGroup styleClass="div" layout="block">
		<h:outputText value="Plazo" />
	</h:panelGroup>
	<h:panelGrid columns="2">
		<ui:label binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.lblCantidadDias}" for="tfCantDias" id="lblCantDias" styleClass="label"
			text="Cantidad de Días" />
		<ui:textField binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.tfCantidadDias}" columns="40" id="tfCantDias"
			styleClass="textField" />
		<div></div>
		<ui:checkbox binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.chDiasCorridos}" id="chbClasificacionSecano" label="Días corridos"></ui:checkbox>
	</h:panelGrid>
</h:panelGrid>