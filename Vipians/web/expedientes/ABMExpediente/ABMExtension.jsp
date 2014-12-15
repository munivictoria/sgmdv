<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMExpediente$ABMExtension.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMExpediente$ABMExtension.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMExpediente$ABMExtension.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMExpediente$ABMExtension.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMExpediente$ABMExtension.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMExpediente$ABMExtension.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMExpediente$ABMExtension.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMExpediente$ABMExtension.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMExtension.lblnombre}" id="lblnombre" styleClass="label" style="font-size: 10pt"/>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="left" style="padding-left: 100px" nowrap="true">
										<ui:label binding="#{expedientes$ABMExpediente$ABMExtension.lblCantidadDias}" for="tfCantidadDias" id="lblCantidadDias" styleClass="label" text="Cantidad Días" />
									</td>
									<td>
									<ui:textField binding="#{expedientes$ABMExpediente$ABMExtension.tfCantidadDias}" columns="5" id="tfCantidadDias" styleClass="textField" onKeyPress="return ValidarNum(event,this)" />
									</td>
							
								</tr>
							<tr>
									<td align="left" style="padding-left: 100px" nowrap="true" >
										<ui:label binding="#{expedientes$ABMExpediente$ABMExtension.lblComentario}" id="lblComentario" styleClass="label" text="Comentario" />
									</td>
									<td>
										<ui:textArea binding="#{expedientes$ABMExpediente$ABMExtension.taComentario}" id="taComentario" styleClass="textArea" columns="60"/>
									</td>
							</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{expedientes$ABMExpediente$ABMExtension.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMExpediente$ABMExtension.btnGuardar_action}" binding="#{expedientes$ABMExpediente$ABMExtension.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMExpediente$ABMExtension.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMExpediente$ABMExtension.btnCancelar_action}" binding="#{expedientes$ABMExpediente$ABMExtension.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMExtension.hidIdPagina}" id="hidIdPagina" text="#{expedientes$ABMExpediente$ABMExtension.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMExtension.hidIdSubSesion}" id="hidIdSubSesion" text="#{expedientes$ABMExpediente$ABMExtension.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMExpediente$ABMExtension.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
