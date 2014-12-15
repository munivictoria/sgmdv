<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCategoria$ABMCategoria.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCategoria$ABMCategoria.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCategoria$ABMCategoria.head1}" id="head1">
				<ui:link binding="#{catastro$ABMCategoria$ABMCategoria.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCategoria$ABMCategoria.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMCategoria$ABMCategoria.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMCategoria$ABMCategoria.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMCategoria$ABMCategoria.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCategoria$ABMCategoria.label4}" for="tfCodigo" id="label4" styleClass="label" text="Código" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMCategoria$ABMCategoria.tfCodigo}" onKeyPress="return ValidarNum(event)" id="tfCodigo"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCategoria$ABMCategoria.label5}" for="tfNombre" id="label5" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMCategoria$ABMCategoria.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{catastro$ABMCategoria$ABMCategoria.label1}" for="tfTipoConstruccion" id="label1" styleClass="label"
											text="Tipo de Construcción" />
									</td>
									<td colspan="3" nowrap="true">
										<ui:textField binding="#{catastro$ABMCategoria$ABMCategoria.tfTipoConstruccion}" columns="40" disabled="true"
											id="tfTipoConstruccion" styleClass="textField" />
										<ui:button action="#{catastro$ABMCategoria$ABMCategoria.btnSeleccionarTipoConstruccion_action}"
											binding="#{catastro$ABMCategoria$ABMCategoria.btnSeleccionarTipoConstruccion}" escape="false" id="btnSeleccionarTipoConstruccion"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
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
									<ui:label binding="#{catastro$ABMCategoria$ABMCategoria.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{catastro$ABMCategoria$ABMCategoria.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMCategoria$ABMCategoria.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMCategoria$ABMCategoria.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{catastro$ABMCategoria$ABMCategoria.btnGuardar_action}"
											binding="#{catastro$ABMCategoria$ABMCategoria.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMCategoria$ABMCategoria.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMCategoria$ABMCategoria.btnCancelar_action}"
											binding="#{catastro$ABMCategoria$ABMCategoria.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMCategoria$ABMCategoria.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMCategoria$ABMCategoria.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCategoria$ABMCategoria.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCategoria$ABMCategoria.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCategoria$ABMCategoria.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
