<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.page1}" id="page1">
            <ui:html binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.html1}" id="html1">
                <ui:head binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.head1}" id="head1" title="Consultar Liquidación">
                    <ui:link binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.body1}" focus="form1:tfNombre" id="body1"
                         onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="3">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblDatosObligacion}" id="lblDatosObligacion"
                                                      styleClass="label3" text="Datos de la Obligación"/>
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblTituloPeriodo}" id="lblTituloPeriodo"
                                                      styleClass="label2" text="Período"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfPeriodo}" columns="25" id="tfPeriodo"
                                                          maxLength="4" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblObligacion}" id="lblObligacion"
                                                      styleClass="label2" text="Obligación"/>                                          
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.taObligacion}" id="taObligacion" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                        </td>
                                    </tr>
                                     <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblParametros}" id="lblParametros"
                                                      styleClass="label2" text="Parámetros"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.taParametros}" id="taParametros" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblParametrosValuadosAlicuota}" id="lblParametrosValuadosAlicuota"
                                                      styleClass="label2" text="Parámetros Valuados Alicuota"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.taParametrosValuadosAlicuota}" id="taParametrosValuadosAlicuota" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblModificadores}" id="lblModificadores"
                                                      styleClass="label2" text="Modificadores"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.taModificadores}" id="taModificadores" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblVencimientos}" id="lblVencimientos" styleClass="label2"
                                                      text="Vencimientos"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.taVencimientos}" id="taVencimientos" styleClass="textField"
                                                         disabled="true" columns="70" rows="3"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblExencion}" id="lblExencion" styleClass="label2"
                                                      text="Exención"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.taExencion}" id="taExencion" styleClass="textField"
                                                         disabled="true" columns="70" rows="3"/>
                                        </td>
                                    </tr>
                                    <tr>
	                                    <td align="right" >
	                                    	<ui:label styleClass="label2" text="Fecha de notificación"/>
	                                    </td>
                                    	<td>
                                    		<ui:textField disabled="true" id="tfFechaNotificacion" styleClass="textFieldDisabled"
                                    			binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfFechaNotificacion}"/>
                                    	</td>
                                    </tr>
                                    <tr>
                                    	<td align="right" >
	                                    	<ui:label styleClass="label2" text="Fecha de apremio"/>
	                                    </td>
                                    	<td>
                                    		<ui:textField disabled="true" id="tfFechaApremio" styleClass="textFieldDisabled"
                                    			binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfFechaApremio}"/>
                                    	</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>                                   
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblDatosLiquidacion}" id="lblDatosLiquidacion"
                                                      styleClass="label3" text="Datos de la Liquidación"/>
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblCuotaLiquidada}" id="lblCuotaLiquidada" for="tfCuotaLiquidada"
                                                      styleClass="label" text="Cuota Liquidada"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfCuotaLiquidada}" disabled="true"
                                                          id="tfCuotaLiquidada" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblMonto}" id="lblMonto" for="tfMonto" styleClass="label2"
                                                      text="Monto "/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfMonto}" id="tfMonto" styleClass="textField"
                                                          disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblEstado}" id="lblEstado" styleClass="label2" for="tfEstado"
                                                      text="Estado"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfEstado}" id="tfEstado" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblFechaCancelacion}" id="lblFechaCancelacion" for="tfFechaCancelacion"
                                                      styleClass="label" text="Fecha de Cancelación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfFechaCancelacion}" id="tfFechaCancelacion" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr> 
                                         <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblUsuarioManual}" id="lblUsuarioManual" for="lblUsuarioManual"
                                                      styleClass="label" text="Usuario"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.tfUsuarioManual}" id="tfUsuarioManual" columns="50" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr> 
                                        <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.lblComentario}" id="lblComentario" for="lblComentario"
                                                      styleClass="label" text="Comentario"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.taComentario}" id="taComentario" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr> 
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.btnVolver_action}"
                                                       binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.hidIdPagina}" id="hidIdPagina" text="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.idPagina}"/>
                        <ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.idSubSesion}"/>
                        <ui:script binding="#{saic$ImprimirLiquidaciones$ConsultarLiquidaciones.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>