<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.head1}" id="head1" title="Agregar Código de Servicio de OySP">
                    <ui:link binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[
                            
                            function cambiarEstado() {
                           
                                Medido = document.getElementById("form1:cbMedido");
                                Unidad = document.getElementById("form1:ddUnidadMedida");
                                BaseConsumo = document.getElementById("form1:tfBaseConsumo");
                                ValorExcedente = document.getElementById("form1:tfValorPorExcedente");
                                
                                
                                if (Medido.checked == true){
                                    Unidad.disabled = false;
                                    BaseConsumo.disabled = false;
                                    ValorExcedente.disabled = false;
                                }else  {
                                    Unidad.disabled = true;
                                    BaseConsumo.value = "";
                                    BaseConsumo.disabled = true;
                                    ValorExcedente.value = "";
                                    ValorExcedente.disabled = true;
                                }
                            }
                           ]]></script>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.body1}" focus="form1:tfCodigo" id="body1"
                         onLoad="parent.footer.location.reload();cambiarEstado();Init();"  style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label4}" for="tfCodigo" id="label4"
                                                styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.tfCodigo}" columns="10"
                                                id="tfCodigo" onKeyPress="return ValidarNum(event,this)" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label8}" for="tfCoeficienteCodigo" id="label8"
                                                 styleClass="label" text="Coeficiente Código de Servicio"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.tfCoeficienteCodigo}" columns="10"
                                                id="tfCoeficienteCodigo" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.lblCoeficienteValorTerreno}"
                                            for="tfCoeficienteValorTerreno" id="lblCoeficienteValorTerreno" styleClass="label" text="Coeficiente Valor de Terreno"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.tfCoeficienteValorTerreno}" columns="10"
                                            id="tfCoeficienteValorTerreno" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.lblCoeficienteValorEdificado}"
                                            for="tfCoeficienteValorEdificado" id="lblCoeficienteValorEdificado" styleClass="label" text="Coeficiente Valor Edificado"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.tfCoeficienteValorEdificado}" columns="10"
                                            id="tfCoeficienteValorEdificado" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label5}" for="taNombre" id="label5"
                                                styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.taNombre}" columns="40"
                                                id="taNombre" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label2}" for="tfValor" id="label2"
                                                styleClass="label" text="Valor Mínimo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.tfValor}" onKeyPress="return ValidarFloat(event,this)" columns="10" id="tfValor" styleClass="textField"/>
                                            <ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[$]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label for="ddPeriodicidad" id="lblPeriodicidad"
                                            styleClass="label" text="Periodicidad"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:dropDown binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.ddPeriodicidad}" id="ddPeriodicidad"
                                                items="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.ddPeriodicidadDefaultOptions.options}" styleClass="TextField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label3}" for="cbMedido" id="label3"
                                                styleClass="label" text="Medido"/>
                                        </td>
                                        <td>
                                            <ui:checkbox binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.cbMedido}" onClick="cambiarEstado()" id="cbMedido" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label7}" for="ddUnidadMedida"
                                                id="label7" styleClass="label" text="Unidad de Medida"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.ddUnidadMedida}" id="ddUnidadMedida"
                                            items="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.ddUnidadMedidaDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label1}" for="tfBaseConsumo"
                                                id="label1" styleClass="label" text="Base de Consumo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.tfBaseConsumo}" columns="10"
                                                id="tfBaseConsumo" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.label6}" for="tfValorPorExcedente"
                                                id="label6" styleClass="label" text="Valor del Excedente"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.tfValorPorExcedente}" columns="10"
                                                id="tfValorPorExcedente" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                            <ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.staticText2}" escape="false"
                                                id="staticText2" styleClass="label" text="&amp;nbsp;[$]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.lblVolcadoEfluentesIndustriales}" for="cbVolcadoEfluentesIndustriales" id="lblVolcadoEfluentesIndustriales"
                                                styleClass="label" text="Volcado Efluentes Industriales"/>
                                        </td>
                                        <td>
                                            <ui:checkbox binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.cbVolcadoEfluentesIndustriales}" id="cbVolcadoEfluentesIndustriales" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.btnGuardar_action}"
                                                binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.btnGuardar}" id="btnGuardar"
                                                styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.btnCancelar_action}"
                                                binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.idSubSesion}"/>
                    <ui:script binding="#{habilitaciones$grpOSP$ABMServicioOSP$AgregarServicioOSP.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
