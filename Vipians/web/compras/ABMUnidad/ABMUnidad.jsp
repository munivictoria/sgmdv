<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMUnidad$ABMUnidad.page1}" id="page1">
			<ui:html binding="#{compras$ABMUnidad$ABMUnidad.html1}" id="html1">
			<ui:head binding="#{compras$ABMUnidad$ABMUnidad.head1}" id="head1">
				<ui:link binding="#{compras$ABMUnidad$ABMUnidad.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMUnidad$ABMUnidad.body1}" focus="form1:tfDescripcion" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMUnidad$ABMUnidad.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMUnidad$ABMUnidad.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMUnidad$ABMUnidad.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMUnidad$ABMUnidad.lblDescripcion}" for="tfDescripcion" id="lblDescripcion" styleClass="label"
											text="Descripción" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMUnidad$ABMUnidad.tfDescripcion}" columns="40" id="tfDescripcion" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{compras$ABMUnidad$ABMUnidad.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMUnidad$ABMUnidad.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMUnidad$ABMUnidad.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMUnidad$ABMUnidad.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{compras$ABMUnidad$ABMUnidad.btnGuardar_action}" binding="#{compras$ABMUnidad$ABMUnidad.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMUnidad$ABMUnidad.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMUnidad$ABMUnidad.btnCancelar_action}" binding="#{compras$ABMUnidad$ABMUnidad.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMUnidad$ABMUnidad.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMUnidad$ABMUnidad.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMUnidad$ABMUnidad.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMUnidad$ABMUnidad.idSubSesion}" />
					<ui:script binding="#{compras$ABMUnidad$ABMUnidad.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
