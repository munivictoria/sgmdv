<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMArea$ABMArea.page1}" id="page1">
			<ui:html binding="#{framework$ABMArea$ABMArea.html1}" id="html1">
			<ui:head binding="#{framework$ABMArea$ABMArea.head1}" id="head1">
				<ui:link binding="#{framework$ABMArea$ABMArea.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMArea$ABMArea.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				 onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMArea$ABMArea.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMArea$ABMArea.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMArea$ABMArea.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMArea$ABMArea.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMArea$ABMArea.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMArea$ABMArea.label5}" for="taDescripcion" id="label5" styleClass="label" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMArea$ABMArea.taDescripcion}" columns="40" id="taDescripcion" rows="5" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMArea$ABMArea.label6}" for="ddSecretaria" id="label2" styleClass="label" text="Secretaría" />
									</td>
									<td nowrap="true">
										<ui:dropDown binding="#{framework$ABMArea$ABMArea.ddSecretaria}" id="ddSecretaria"
											items="#{framework$ABMArea$ABMArea.ddSecretariaOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{framework$ABMArea$ABMArea.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMArea$ABMArea.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMArea$ABMArea.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMArea$ABMArea.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMArea$ABMArea.btnGuardar_action}" binding="#{framework$ABMArea$ABMArea.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMArea$ABMArea.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMArea$ABMArea.btnCancelar_action}" binding="#{framework$ABMArea$ABMArea.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMArea$ABMArea.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMArea$ABMArea.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMArea$ABMArea.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMArea$ABMArea.idSubSesion}" />
					<ui:script binding="#{framework$ABMArea$ABMArea.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
