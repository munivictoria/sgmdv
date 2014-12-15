<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMPais$ABMPais.page1}" id="page1">
			<ui:html binding="#{framework$ABMPais$ABMPais.html1}" id="html1">
			<ui:head binding="#{framework$ABMPais$ABMPais.head1}" id="head1">
				<ui:link binding="#{framework$ABMPais$ABMPais.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMPais$ABMPais.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMPais$ABMPais.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMPais$ABMPais.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMPais$ABMPais.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMPais$ABMPais.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPais$ABMPais.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMPais$ABMPais.label5}" for="tfAbreviatura" id="label5" styleClass="label" text="Abreviatura" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPais$ABMPais.tfAbreviatura}" columns="10" id="tfAbreviatura" maxLength="10"
											styleClass="textField" />
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
									<ui:label binding="#{framework$ABMPais$ABMPais.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{framework$ABMPais$ABMPais.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{framework$ABMPais$ABMPais.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{framework$ABMPais$ABMPais.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{framework$ABMPais$ABMPais.btnGuardar_action}" binding="#{framework$ABMPais$ABMPais.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMPais$ABMPais.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMPais$ABMPais.btnCancelar_action}" binding="#{framework$ABMPais$ABMPais.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMPais$ABMPais.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMPais$ABMPais.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMPais$ABMPais.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMPais$ABMPais.idSubSesion}" />
					<ui:script binding="#{framework$ABMPais$ABMPais.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
