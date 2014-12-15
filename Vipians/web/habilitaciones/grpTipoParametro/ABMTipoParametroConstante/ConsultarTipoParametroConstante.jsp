<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.head1}" id="head1" title="Consultar Parámetro Constante">
                    <ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.link1}" id="link1" url="/resources/stylesheet.css"/>
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
                    <ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.body1}" focus="form1:tfNombre"
                             id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                        <ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText
                                        binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.head1.title}"/>
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
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.label4}"
                                                for="tfNombre" id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.tfNombre}"
                                                columns="40" disabled="true" id="tfNombre" onKeyUp="armarNombreVariable(this, 'form1:tfNombreVariable');" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.label1}"
                                                for="tfNombreVariable" id="label1" styleClass="label" text="Nombre de Variable"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.tfNombreVariable}"
                                                columns="40" disabled="true" id="tfNombreVariable" onBlur="armarNombreVariable(this, this.id);" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.label2}"
                                                for="tfValor" id="label2" styleClass="label" text="Valor"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.tfValor}"
                                                columns="15" disabled="true" id="tfValor" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.label3}"
                                                id="label3" styleClass="label" text="Tipo de Valor"/>
                                        </td>
                                        <td>
                                            <ui:radioButton
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.rbFijo}"
                                                disabled="true" id="rbFijo" name="rbgTipoValor"/>
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.label5}"
                                                for="rbFijo" id="label5" styleClass="label" text="Fijo"/>
                                            <br/>
                                            <ui:radioButton
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.rbPorcentual}"
                                                disabled="true" id="rbPorcentual" name="rbgTipoValor"/>
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.label6}"
                                                for="rbPorcentual" id="label6" styleClass="label" text="Porcentual"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
                                            <ui:button
                                                action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.btnVolver_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.btnVolver}"
                                                id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.hidIdPagina}"
                                        id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.hidIdSubSesion}"
                                        id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ConsultarTipoParametroConstante.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
