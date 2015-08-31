<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.sun.com/web/ui" prefix="ui"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<ui:tree style="overflow : auto;  height: 400px" binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento}"
	clientSide="true" id="trProcedimiento" styleClass="tablaInterna">
	<ui:treeNode binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.trProcedimiento.tnRaiz}" expanded="true" id="tnRaiz" text="Raíz">
		<f:facet name="image">
			<ui:image url="/resources/imagenes/arboles/recurso.png"></ui:image>
		</f:facet>
	</ui:treeNode>
</ui:tree>