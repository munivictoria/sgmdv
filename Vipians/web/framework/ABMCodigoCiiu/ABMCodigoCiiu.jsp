<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.page1}" id="page1">
			<ui:html binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.html1}" id="html1">
			<ui:head binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.head1}" id="head1">
				<ui:link binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.body1}" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="ddSeccion" id="lblSeccion" styleClass="label" text="Sección" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.ddSeccion}" id="ddSeccion"
											items="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.ddSeccionDefaultOptions.options}" styleClass="textField"
											valueChangeListener="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.valueChangeEvent(event)}">
											<a4j:support event="onChange" reRender="form1:ddGrupo" />
										</ui:dropDown>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="ddGrupo" id="lblGrupo" styleClass="label" text="Grupo" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.ddGrupo}" id="ddGrupo"
											items="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.ddGrupoDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.lblCodigo}" for="tfCodigo" id="lblCodigo" styleClass="label"
											text="Código" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.tfCodigo}" columns="40" id="tfCodigo"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.lblDescripcion}" for="tfDescripcion" id="lblDescripcion"
											styleClass="label" text="Descripción" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.tfDescripcion}" columns="40" id="tfDescripcion"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.btnGuardar_action}"
											binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.btnCancelar_action}"
											binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.idSubSesion}" />
					<ui:script binding="#{framework$ABMCodigoCiiu$ABMCodigoCiiu.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>