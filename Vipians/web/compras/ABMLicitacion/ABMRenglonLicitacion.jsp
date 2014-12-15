<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.page1}" id="page1">
			<ui:html binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.html1}" id="html1">
			<ui:head binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.head1}" id="head1">
				<ui:link binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.body1}" focus="form1:tfFechaDesde" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMLicitacion$ABMRenglonLicitacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.label1}" for="tfNombre" id="label1" styleClass="label"
											text="Nombre" />
									</td>
									<td nowrap="true">
										<ui:textField binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.tfNombre}" columns="30" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.label2}" for="taDescripcion" id="label2" styleClass="label"
											text="Descripción" />
									</td>
									<td nowrap="true">
										<ui:textArea binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.taDescripcion}" id="taDescripcion" maxLength="10"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.label5}" for="tfUnidadMedida" id="label5" styleClass="label"
											text="Unidad de Medida" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.tfUnidadMedida}" columns="40" id="tfUnidadMedida"
											disabled="true" styleClass="textField" />
										<ui:button action="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnSeleccionarUnidadMedida_action}"
											binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnSeleccionarUnidadMedida}" escape="false" id="btnSeleccionarUnidadMedida"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarUnidadMedida" reRender="form1:tfUnidadMedida"
											binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnLimpiarUnidadMedida}"
											action="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnLimpiarUnidadMedida_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.label3}" for="tfCantidad" id="label3" styleClass="label"
											text="Cantidad" />
									</td>
									<td nowrap="true">
										<ui:textField binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.tfCantidad}" id="tfCantidad"
											onKeyPress="return ValidarFloat(event,this)" maxLength="10" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="true">
										<ui:button action="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnGuardar_action}"
											binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnCancelar_action}"
											binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMLicitacion$ABMRenglonLicitacion.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMLicitacion$ABMRenglonLicitacion.idSubSesion}" />
					<ui:script binding="#{compras$ABMLicitacion$ABMRenglonLicitacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
