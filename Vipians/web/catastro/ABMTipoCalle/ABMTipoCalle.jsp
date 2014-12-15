<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMTipoCalle$ABMTipoCalle.page1}" id="page1">
			<ui:html binding="#{catastro$ABMTipoCalle$ABMTipoCalle.html1}" id="html1">
			<ui:head binding="#{catastro$ABMTipoCalle$ABMTipoCalle.head1}" id="head1">
				<ui:link binding="#{catastro$ABMTipoCalle$ABMTipoCalle.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMTipoCalle$ABMTipoCalle.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMTipoCalle$ABMTipoCalle.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMTipoCalle$ABMTipoCalle.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMTipoCalle$ABMTipoCalle.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMTipoCalle$ABMTipoCalle.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMTipoCalle$ABMTipoCalle.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMTipoCalle$ABMTipoCalle.label5}" for="taDescripcion" id="label5" styleClass="label"
											text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMTipoCalle$ABMTipoCalle.taDescripcion}" columns="40" id="taDescripcion" rows="5"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{catastro$ABMTipoCalle$ABMTipoCalle.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMTipoCalle$ABMTipoCalle.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMTipoCalle$ABMTipoCalle.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{catastro$ABMTipoCalle$ABMTipoCalle.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 21px">
										<ui:button action="#{catastro$ABMTipoCalle$ABMTipoCalle.btnGuardar_action}"
											binding="#{catastro$ABMTipoCalle$ABMTipoCalle.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMTipoCalle$ABMTipoCalle.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMTipoCalle$ABMTipoCalle.btnCancelar_action}"
											binding="#{catastro$ABMTipoCalle$ABMTipoCalle.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMTipoCalle$ABMTipoCalle.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMTipoCalle$ABMTipoCalle.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMTipoCalle$ABMTipoCalle.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMTipoCalle$ABMTipoCalle.idSubSesion}" />
					<ui:script binding="#{catastro$ABMTipoCalle$ABMTipoCalle.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
