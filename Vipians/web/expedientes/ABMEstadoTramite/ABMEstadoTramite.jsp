<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="left" style="padding-left: 100px" nowrap="true">
										<ui:label binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
										<ui:textField binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
									<td  align="left" style="padding-right: 70px">
										<ui:checkbox binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.cbCierreTramite}" id="cbCierreTramite" styleClass="checkbox" />
										<ui:label binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.lblCierreTramite}" id="lblCierreTramite" styleClass="label" text="Cierre Tramite" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.btnGuardar_action}" binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.btnCancelar_action}" binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.hidIdPagina}" id="hidIdPagina" text="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.hidIdSubSesion}" id="hidIdSubSesion" text="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMEstadoTramite$ABMEstadoTramite.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
