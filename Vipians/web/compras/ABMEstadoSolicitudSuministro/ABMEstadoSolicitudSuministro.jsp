<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.head1}" id="head1">
				<ui:link binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.form1}" id="form1">
					<div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.lblNombre}" for="tfNombre" id="lblNombre"
											styleClass="label" text="Nombre" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.tfNombre}" id="tfNombre"
											styleClass="textField" />
									</td>
									<td align="right">
										<ui:label binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.lblDescripcion}" id="lblDescripcion"
											styleClass="label" for="taDescripcion" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.taDescripcion}" id="taDescripcion"
											styleClass="textField" />
									</td>
								</tr>
								<td>
									<br />
								</td>
								<tr>
									<td align="left" nowrap="nowrap" colspan="4" style="padding-left: 120px">
										<table class="verde" style="border-width: 0px; padding: 1px 1px; width: 50%;">
											<tr>
												<td>
													<ui:checkbox binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.cbEstadoFinal}" id="cbEstadoFinal" />
													<ui:label text="Es Estado Final" for="cbEstadoFinal" id="lblEstadoFinal" style="font-size: 10pt" />
													<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:checkbox binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.cbEstadoInicial}"
														id="cbEstadoInicial" />
													<ui:label text="Es Estado Inicial" for="cbEstadoInicial" id="lblEstadoInicial" style="font-size: 10pt" />
													<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:checkbox binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.cbEsModificable}"
														id="cbEsModificable" />
													<ui:label text="Es Modificable" for="cbEsModificable" id="lblEsModificable" style="font-size: 10pt" />
												</td>
											</tr>
											<tr>
												<td>
													<ui:checkbox binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.cbUsadoEnContratacion}"
														id="cbUsadoEnContratacion" />
													<ui:label text="Es Usado en Contratación" for="cbUsadoEnContratacion" id="lblUsadoEnContratacion" style="font-size: 10pt" />
													<ui:staticText escape="false" id="stSeparador4" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:checkbox binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.cbUsable}" id="cbUsable" />
													<ui:label text="Usable en movimientos" for="cbUsable" id="lblUsable" style="font-size: 10pt" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.btnGuardar_action}"
											binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.btnCancelar_action}"
											binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
							<tr>
								<td colspan="4">
									<br />
								</td>
							</tr>
						<!--	<tr>
								<td align="right">
									<ui:label binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr> -->
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<td colspan="4">
								<ui:messageGroup binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.messageGroup}" id="messageGroup"
									styleClass="grupoMsg" />
							</td>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>