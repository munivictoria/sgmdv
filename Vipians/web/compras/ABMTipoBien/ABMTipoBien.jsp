<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMTipoBien$ABMTipoBien.page1}" id="page1">
			<ui:html binding="#{compras$ABMTipoBien$ABMTipoBien.html1}" id="html1">
			<ui:head binding="#{compras$ABMTipoBien$ABMTipoBien.head1}" id="head1">
				<ui:link binding="#{compras$ABMTipoBien$ABMTipoBien.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMTipoBien$ABMTipoBien.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMTipoBien$ABMTipoBien.form1}" id="form1">
					<div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMTipoBien$ABMTipoBien.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMTipoBien$ABMTipoBien.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMTipoBien$ABMTipoBien.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMTipoBien$ABMTipoBien.tfNombre}" id="tfNombre" styleClass="textField" tabIndex="1"/>
									</td>
									<td align="right">
										<ui:label binding="#{compras$ABMTipoBien$ABMTipoBien.lblDescripcion}" id="lblDescripcion" styleClass="label" for="taDescripcion"
											text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMTipoBien$ABMTipoBien.taDescripcion}" id="taDescripcion" styleClass="textField" tabIndex="2"/>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMTipoBien$ABMTipoBien.lbCodigoImputacion}" for="tfCodigoImputacion" id="lbCodigoImputacion"
											styleClass="label" text="Código de Imputación" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMTipoBien$ABMTipoBien.tfCodigoImputacion}" columns="20" id="tfCodigoImputacion"
											styleClass="textField" tabIndex="3"/>
									</td>
								</tr>
								<td>
									<br />
								</td>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMTipoBien$ABMTipoBien.btnGuardar_action}" binding="#{compras$ABMTipoBien$ABMTipoBien.btnGuardar}"
											id="btnGuardar" styleClass="button" tabIndex="5"/>
										<ui:staticText binding="#{compras$ABMTipoBien$ABMTipoBien.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMTipoBien$ABMTipoBien.btnCancelar_action}" binding="#{compras$ABMTipoBien$ABMTipoBien.btnCancelar}"
											id="btnCancelar" styleClass="button" tabIndex="6"/>
									</td>
								</tr>
							</tfoot>
							<tr>
								<td colspan="4">
									<br />
								</td>
							</tr>
							<tr>
								<td align="right">
									<ui:label binding="#{compras$ABMTipoBien$ABMTipoBien.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{compras$ABMTipoBien$ABMTipoBien.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" tabIndex="4"/>
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{compras$ABMTipoBien$ABMTipoBien.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<td colspan="4">
								<ui:messageGroup binding="#{compras$ABMTipoBien$ABMTipoBien.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
							</td>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMTipoBien$ABMTipoBien.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMTipoBien$ABMTipoBien.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMTipoBien$ABMTipoBien.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMTipoBien$ABMTipoBien.idSubSesion}" />
					<ui:script binding="#{compras$ABMTipoBien$ABMTipoBien.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>