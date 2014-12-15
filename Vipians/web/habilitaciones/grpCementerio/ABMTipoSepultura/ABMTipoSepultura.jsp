<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1"
		pageEncoding="UTF-8" />
	<f:view>
		<ui:page
			binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.page1}"
			id="page1">
			<ui:html
				binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.html1}"
				id="html1">
			<ui:head
				binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.head1}"
				id="head1">
				<ui:link
					binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.link1}"
					id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body
				binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.body1}"
				focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init();"
				onKeyPress="eventoByEnter(event,'btnGuardar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form
					binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.form1}"
					id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText
									binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.stTitulo}"
									id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"><ui:label
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.lbCodigo}"
											for="tfCodigo" id="lbCodigo" styleClass="label" text="Código" />
									</td>
									<td><ui:textField
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.tfCodigo}"
											columns="10" id="tfCodigo" styleClass="textField" /></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"><ui:label
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.lbDescripcion}"
											for="taDescripcion" id="lbDescripcion" styleClass="label"
											text="Descripción" /></td>
									<td><ui:textArea
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.taDescripcion}"
											columns="40" id="taDescripcion" styleClass="textArea" /></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"><ui:label
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.lbMinimo}"
											for="tfMinimo" id="lbMinimo" styleClass="label" text="Mínimo" />
									</td>
									<td><ui:textField
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.tfMinimo}"
											columns="10" id="tfMinimo" styleClass="textField"
											onKeyPress="return ValidarFloat(event,this)" /></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"><ui:label
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.lbValor}"
											for="tfValor" id="lbValor" styleClass="label" text="Valor" />
									</td>
									<td><ui:textField
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.tfValor}"
											columns="10" id="tfValor" styleClass="textField"
											onKeyPress="return ValidarFloat(event,this)" /></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"><ui:label
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.lbPorcentual}"
											for="cPorcentual" id="lbPorcentual" styleClass="label"
											text="Porcentual" /></td>
									<td><ui:checkbox
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.cPorcentual}"
											id="cPorcentual" /></td>
								</tr>
								<tr><td></td></tr>
								<tr>
                                        <td colspan="4">         
                                            <div  class="div" style="width: 290px; height: 15px;"> Atributos Dinámicos </div>
                                            <table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
                                                <tr>
                                                    <td colspan="4">         
                                                        <ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.panelAtributoDinamico}" id="panelAtributoDinamico">
                                                            <!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
                                                        </ui:panelGroup>
                                                    </td>
                                                </tr> 
                                            </table>
                                        </td>
                                    </tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="4"><ui:messageGroup
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" /></td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap"><ui:button
											action="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.btnGuardar_action}"
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.btnGuardar}"
											id="btnGuardar" styleClass="button" /> <ui:staticText
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.stSeparador}"
											escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" /> <ui:button
											action="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.btnCancelar_action}"
											binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.btnCancelar}"
											id="btnCancelar" styleClass="button" /></td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField
						binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.hidIdPagina}"
						id="hidIdPagina"
						text="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.idPagina}" />
					<ui:hiddenField
						binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.hidIdSubSesion}"
						id="hidIdSubSesion"
						text="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.idSubSesion}" />
					<ui:script
						binding="#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
