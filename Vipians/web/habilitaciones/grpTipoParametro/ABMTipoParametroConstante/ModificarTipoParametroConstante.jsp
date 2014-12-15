<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.head1}" id="head1" title="Modificar Parámetro Constante">
                    <ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[
                        /**
                         * Genera el Nombre de Variable
                         */
                        function armarNombreVariable(tfOrigen, idTfDestino) {
                            valor = tfOrigen.value;
                            if (!isNaN(valor.substring(0,1))) valor = "VAR_" + valor;
                            separador = "_";
                            txtNomVar = document.getElementById(idTfDestino);
                            valor = valor.replace(/ /g,separador);
                            valor = valor.replace(/á/g,"a");
                            valor = valor.replace(/é/g,"e");
                            valor = valor.replace(/í/g,"i");
                            valor = valor.replace(/ó/g,"o");
                            valor = valor.replace(/ú/g,"u");
                            valor = valor.replace(/\./g,separador);
                            valor = valor.replace(/\\/g,separador);
                            valor = valor.replace(/\+/g,separador);
                            valor = valor.replace(/\-/g,separador);
                            valor = valor.replace(/\*/g,separador);
                            valor = valor.replace(/\//g,separador);
                            valor = valor.replace(/\"/g,separador);
                            valor = valor.replace(/\'/g,separador);
                            valor = valor.replace(/\#/g,separador);
                            valor = valor.replace(/\(/g,separador);
                            valor = valor.replace(/\)/g,separador);
                            valor = valor.replace(/\=/g,separador);
                            valor = valor.replace(/\&/g,separador);
                            valor = valor.replace(/\?/g,separador);
                            valor = valor.replace(/\¿/g,separador);
                            valor = valor.replace(/\%/g,separador);
                            txtNomVar.value = valor.toUpperCase();
                        }
                    ]]></script>
                    </ui:head>
                    <ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.body1}" focus="form1:tfNombre"
                             id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"  onKeyUp="eventoByEscape(event,'btnCancelar')">
                        <ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText
                                        binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.label4}"
                                                for="tfNombre" id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.tfNombre}"
                                                columns="40" id="tfNombre" onKeyUp="armarNombreVariable(this, 'form1:tfNombreVariable');" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.label1}"
                                                for="tfNombreVariable" id="label1" styleClass="label" text="Nombre de Variable"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.tfNombreVariable}"
                                                columns="40" id="tfNombreVariable" onBlur="armarNombreVariable(this, this.id);" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.label2}"
                                                for="tfValor" id="label2" styleClass="label" text="Valor"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.tfValor}"
                                                columns="15" id="tfValor" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.label3}"
                                                id="label3" styleClass="label" text="Tipo de Valor"/>
                                        </td>
                                        <td>
                                            <ui:radioButton
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.rbFijo}"
                                                id="rbFijo" name="rbgTipoValor"/>
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.label5}"
                                                for="rbFijo" id="label5" styleClass="label" text="Fijo"/>
                                            <br/>
                                            <ui:radioButton
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.rbPorcentual}"
                                                id="rbPorcentual" name="rbgTipoValor"/>
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.label6}"
                                                for="rbPorcentual" id="label6" styleClass="label" text="Porcentual"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
                                            <ui:button
                                                action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.btnGuardar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.btnGuardar}"
                                                id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.btnCancelar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.hidIdPagina}"
                                        id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.hidIdSubSesion}"
                                        id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ModificarTipoParametroConstante.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
