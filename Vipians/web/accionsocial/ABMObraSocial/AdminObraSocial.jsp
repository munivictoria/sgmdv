<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui"  xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMObraSocial$AdminObraSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMObraSocial$AdminObraSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMObraSocial$AdminObraSocial.head1}" id="head1" title="Administración de Obra Social">
                    <ui:link binding="#{accionsocial$ABMObraSocial$AdminObraSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMObraSocial$AdminObraSocial.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();  changeStyleAlIngresar();" style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMObraSocial$AdminObraSocial.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <table border="0" class="azul">
                                    <caption>
                                        <ui:staticText binding="#{accionsocial$ABMObraSocial$AdminObraSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMObraSocial$AdminObraSocial.head1.title}"/>
                                    </caption>                                    
                                    <tbody>
                                        <tr>
                                            <td colspan="2"></td>
                                        </tr>
                                        <tr>
                                            <td align="center" nowrap="nowrap">
                                                <ui:staticText escape="false" id="stFiltrarPor"
                                                               styleClass="textoFiltrarPor" text="Filtrar por"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <hr/>
                                            </td>

                                        </tr>
                                        <tr><td align="center">
                                                <ui:panelGroup binding="#{accionsocial$ABMObraSocial$AdminObraSocial.pgParametros}" id="pgParametros">
                                                    <table>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{accionsocial$ABMObraSocial$AdminObraSocial.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{accionsocial$ABMObraSocial$AdminObraSocial.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                            </td>
                                        </tr>
                                            </table>
                                        </ui:panelGroup>
                                        </td></tr>
                                    </tbody>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tfoot>
                                        <tr>
                                            <td align="right" colspan="2">
                                                <a4j:commandButton binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnBuscar}" action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnBuscar_action}"
                                                                   id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()"/>
                                                <a4j:commandButton action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnReiniciar_action}"
                                                           binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"/>

                                                <ui:staticText binding="#{accionsocial$ABMObraSocial$AdminObraSocial.staticText2}" escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnCancelar_action}"
                                                           binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                                <!--</div>
                                -->
                                <div>
                                    <ui:messageGroup binding="#{accionsocial$ABMObraSocial$AdminObraSocial.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"/>
                                </div>
                                <table  class="general">    
                                    <tr>
                                        <td>
                                            <ui:table binding="#{accionsocial$ABMObraSocial$AdminObraSocial.customTable}"
                                                      id="table1" style="align: center">
                                                <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{accionsocial$ABMObraSocial$AdminObraSocial.groupPanel1}" id="groupPanel1" style="">
                                                        <ui:button action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnSeleccionar_action}"
                                                                binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar"/>
                                                        <ui:staticText binding="#{accionsocial$ABMObraSocial$AdminObraSocial.staticText6}" escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                        <ui:button action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnAgregar_action}"
                                                                binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar"/>
                                                        <ui:button action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnModificar_action}"
                                                                binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnModificar}" id="btnModificar" styleClass="button" text="Modificar"/>
                                                        <ui:button action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnEliminar_action}"
                                                                binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                                        <ui:staticText binding="#{accionsocial$ABMObraSocial$AdminObraSocial.staticText8}" escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>                                   
                                                        <ui:button action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnConsultar_action}"
                                                                binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar"/>
                                                        <ui:staticText binding="#{accionsocial$ABMObraSocial$AdminObraSocial.staticText10}" escape="false" id="staticText10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                        <ui:button action="#{accionsocial$ABMObraSocial$AdminObraSocial.btnExportar_action}" binding="#{accionsocial$ABMObraSocial$AdminObraSocial.btnExportar}" id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                            </ui:table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <script>
                    document.getElementById('form1:tfNombre').focus();
                        </script>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$AdminObraSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMObraSocial$AdminObraSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$AdminObraSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMObraSocial$AdminObraSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMObraSocial$AdminObraSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
