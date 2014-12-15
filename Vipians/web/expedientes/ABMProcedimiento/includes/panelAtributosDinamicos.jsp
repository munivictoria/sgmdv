<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.sun.com/web/ui" prefix="ui"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>


<h:panelGrid styleClass="tablaInterna" width="100%"
	style="-moz-border-radius: 0px 0px 5px 5px;">
	<h:panelGroup styleClass="div" layout="block">
		<h:outputText value="Atributos Dinámicos" />
	</h:panelGroup>
	<ui:panelGroup
		binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelAtributoDinamico}"
		id="panelAtributoDinamico">
		<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
	</ui:panelGroup>
</h:panelGrid>

