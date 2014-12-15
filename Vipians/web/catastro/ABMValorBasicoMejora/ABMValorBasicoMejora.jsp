<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.page1}" id="page1">
			<ui:html binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.html1}" id="html1">
			<ui:head binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.head1}" id="head1">
				<ui:link binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.body1}" focus="form1:tfAnioVigencia" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.label2}" for="tfAnioVigencia" id="label2"
											styleClass="label" text="Año de Vigencia" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.tfAnioVigencia}" columns="10" id="tfAnioVigencia"
											maxLength="4" styleClass="textField" onKeyPress="return ValidarNum(event)" />
										<ui:staticText binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.staticText1}" escape="false" id="staticText1"
											styleClass="label" text="&amp;nbsp;[aaaa]" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.label1}" for="tfCategoria" id="label1" styleClass="label"
											text="Categoría" />
									</td>
									<td colspan="3" nowrap="nowrap">
										<ui:textField binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.tfCategoria}" columns="40" disabled="true"
											id="tfCategoria" styleClass="textField" />
										<ui:button action="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.btnSeleccionarCategoria_action}"
											binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.btnSeleccionarCategoria}" escape="false"
											id="btnSeleccionarCategoria" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.label3}" for="tfValor" id="label3" styleClass="label"
											text="Valor" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.tfValor}" columns="10" id="tfValor"
											onKeyPress="return ValidarFloat(event,this)" styleClass="textField" />
										<ui:staticText binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.staticText2}" escape="false" id="staticText2"
											styleClass="label" text="&amp;nbsp;[$]" />
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
									<ui:label binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.messageGroup1}" id="messageGroup1"
										styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.btnGuardar_action}"
											binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.btnCancelar_action}"
											binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.idSubSesion}" />
					<ui:script binding="#{catastro$ABMValorBasicoMejora$ABMValorBasicoMejora.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
