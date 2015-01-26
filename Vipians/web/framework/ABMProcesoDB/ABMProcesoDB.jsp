<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMProcesoDB$ABMProcesoDB.page1}" id="page1">
			<ui:html binding="#{framework$ABMProcesoDB$ABMProcesoDB.html1}" id="html1">
			<ui:head binding="#{framework$ABMProcesoDB$ABMProcesoDB.head1}" id="head1">
				<ui:link binding="#{framework$ABMProcesoDB$ABMProcesoDB.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMProcesoDB$ABMProcesoDB.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMProcesoDB$ABMProcesoDB.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMProcesoDB$ABMProcesoDB.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMProcesoDB$ABMProcesoDB.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMProcesoDB$ABMProcesoDB.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label for="tfNombreProceso" id="label5" styleClass="label" text="Nombre Proceso" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMProcesoDB$ABMProcesoDB.tfNombreProceso}" columns="10" id="tfNombreProceso" maxLength="10"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label for="taDescripcion" id="lblDescripcion" styleClass="label" text="Descripcion" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMProcesoDB$ABMProcesoDB.taDescripcion}" columns="40" id="taDescripcion" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label for="taParametros" id="lblParametros" styleClass="label" text="Parametros" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMProcesoDB$ABMProcesoDB.taParametros}" columns="40" id="taParametros" styleClass="textField" />
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
									<ui:label binding="#{framework$ABMProcesoDB$ABMProcesoDB.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{framework$ABMProcesoDB$ABMProcesoDB.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{framework$ABMProcesoDB$ABMProcesoDB.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{framework$ABMProcesoDB$ABMProcesoDB.messageGroup}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{framework$ABMProcesoDB$ABMProcesoDB.btnGuardar_action}" binding="#{framework$ABMProcesoDB$ABMProcesoDB.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMProcesoDB$ABMProcesoDB.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMProcesoDB$ABMProcesoDB.btnCancelar_action}" binding="#{framework$ABMProcesoDB$ABMProcesoDB.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMProcesoDB$ABMProcesoDB.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMProcesoDB$ABMProcesoDB.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMProcesoDB$ABMProcesoDB.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMProcesoDB$ABMProcesoDB.idSubSesion}" />
					<ui:script binding="#{framework$ABMProcesoDB$ABMProcesoDB.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
