<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.page1}" id="page1">
			<ui:html binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.html1}" id="html1">
			<ui:head binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.head1}" id="head1">
				<ui:link binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.label4}" for="tfCodigo" id="label4" styleClass="label"
											text="Número" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.tfCodigo}" id="tfCodigo" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.label5}" for="tfFechaInscripcion" id="label5"
											styleClass="label" text="Fecha de Inscripción" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.tfFechaInscripcion}" id="tfFechaInscripcion"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
							</tbody>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.messageGroup1}" id="messageGroup1"
										styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.btnGuardar_action}"
											binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.btnCancelar_action}"
											binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.idSubSesion}" />
					<ui:script binding="#{catastro$ABMDeclaracionJurada$ABMDeclaracionJurada.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
