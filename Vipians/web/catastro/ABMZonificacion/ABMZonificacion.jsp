<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMZonificacion$ABMZonificacion.page1}" id="page1">
			<ui:html binding="#{catastro$ABMZonificacion$ABMZonificacion.html1}" id="html1">
			<ui:head binding="#{catastro$ABMZonificacion$ABMZonificacion.head1}" id="head1">
				<ui:link binding="#{catastro$ABMZonificacion$ABMZonificacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMZonificacion$ABMZonificacion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMZonificacion$ABMZonificacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMZonificacion$ABMZonificacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMZonificacion$ABMZonificacion.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMZonificacion$ABMZonificacion.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMZonificacion$ABMZonificacion.tfNombre}" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
							</tbody>
							<tr>
								<td colspan="4">
									<br />
								</td>
							</tr>
							<tr>
								<td align="right">
									<ui:label binding="#{catastro$ABMZonificacion$ABMZonificacion.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{catastro$ABMZonificacion$ABMZonificacion.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMZonificacion$ABMZonificacion.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMZonificacion$ABMZonificacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{catastro$ABMZonificacion$ABMZonificacion.btnGuardar_action}"
											binding="#{catastro$ABMZonificacion$ABMZonificacion.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMZonificacion$ABMZonificacion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMZonificacion$ABMZonificacion.btnCancelar_action}"
											binding="#{catastro$ABMZonificacion$ABMZonificacion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMZonificacion$ABMZonificacion.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMZonificacion$ABMZonificacion.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMZonificacion$ABMZonificacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMZonificacion$ABMZonificacion.idSubSesion}" />
					<ui:script binding="#{catastro$ABMZonificacion$ABMZonificacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
