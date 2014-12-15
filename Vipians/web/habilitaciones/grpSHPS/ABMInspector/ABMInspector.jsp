<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.label4}" for="tfNombre" id="label4" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.label1}" for="tfPersona" id="label1" styleClass="label"
											text="Persona" />
									</td>
									<td nowrap="true">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.tfPersona}" columns="40" disabled="true"
											id="tfPersona" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnSeleccionarPersona_action}"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnSeleccionarPersona}" escape="false" id="btnSeleccionarPersona"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnLimpiarPersona}"
											action="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="true">
										<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnGuardar_action}"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnCancelar_action}"
											binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.btnCancelar}" id="btnCancelar" styleClass="button"
											/>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMInspector$ABMInspector.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
