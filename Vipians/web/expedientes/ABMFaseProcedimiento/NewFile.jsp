<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:ui="http://www.sun.com/web/ui"
	xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1"
		pageEncoding="UTF-8" />
	<f:view>
		<ui:tabSet
			id="tabSet1" mini="true" lite="true">
			<ui:tab id="one" 
				text="Arbol Procedimiento">
		         
			</ui:tab>
			<ui:tab id="two" 
				text="Información Básica">
		         <a4j:include  viewId="#{expedientes$ABMProcedimiento$ABMProcedimiento.tab1}" ></a4j:include>
			</ui:tab>
		</ui:tabSet>
	</f:view>
	</body>
	</html>