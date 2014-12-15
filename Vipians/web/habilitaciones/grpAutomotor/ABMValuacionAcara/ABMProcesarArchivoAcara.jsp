<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.head1.title}"/>
                                </caption>
                                <tbody>
								<tr>
									<td><br /></td>
								</tr>
								
								<tr>
									<td align="right" nowrap="true"><ui:label for="uploadArchivoAcara"
											binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.lblNombreArchivo}"
											id="lblNombreArchivo" styleClass="label" text="Archivo" /></td>
									<td><ui:upload
											binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.upload}"
											id="uploadArchivoAcara"
											valueChangeListener="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.procesarArchivo}">
										</ui:upload></td>
								</tr>
								
								<tr>
									<td colspan="4"><ui:messageGroup
											binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.messageGroup}"
											id="messageGroup" styleClass="grupoMsg" /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

							</tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.btnGuardar_action}"
                                                binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.btnCancelar_action}"
                                                binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
