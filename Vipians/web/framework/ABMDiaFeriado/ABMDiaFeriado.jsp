<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.page1}" id="page1">
			<ui:html binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.html1}" id="html1">
			<ui:head binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.head1}" id="head1">
				<ui:link binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();" 
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMDiaFeriado$ABMDiaFeriado.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.label1}" for="tfFecha" id="label1" styleClass="label" text="Fecha" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.tfFecha}" id="tfFecha" styleClass="textField"
											onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.staticText1}" escape="false" id="staticText1"
                                                styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{framework$ABMDiaFeriado$ABMDiaFeriado.btnGuardar_action}"
											binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMDiaFeriado$ABMDiaFeriado.btnCancelar_action}"
											binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMDiaFeriado$ABMDiaFeriado.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMDiaFeriado$ABMDiaFeriado.idSubSesion}" />
					<ui:script binding="#{framework$ABMDiaFeriado$ABMDiaFeriado.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
