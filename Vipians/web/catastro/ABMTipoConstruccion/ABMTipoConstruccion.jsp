<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.page1}" id="page1">
			<ui:html binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.html1}" id="html1">
			<ui:head binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.head1}" id="head1">
				<ui:link binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.label4}" for="tfNombre" id="label4" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.label5}" for="txDescripcion" id="label5" styleClass="label"
											text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.txDescripcion}" columns="40" id="txDescripcion" rows="5"
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
									<ui:label binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.messageGroup1}" id="messageGroup1"
										styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.btnGuardar_action}"
											binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.btnCancelar_action}"
											binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.idSubSesion}" />
					<ui:script binding="#{catastro$ABMTipoConstruccion$ABMTipoConstruccion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
