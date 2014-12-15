<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Login.page1}" id="page1">
            <ui:html binding="#{Login.html1}" id="html1">
                <ui:head binding="#{Login.head1}" id="head1" title="Vipians - Ingreso">
                    <ui:link binding="#{Login.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <link href="/Vipians/resources/muni.ico" rel="shortcut icon"/>
                    <script><![CDATA[
                    
                    function disableIngresar(state, id) {
                        var elem = document.getElementById(id);
                        if (elem != null) {
                            elem.disabled = state;
                            if (state) {
                                elem.className = 'button Btn2Dis';
                                elem.value = 'Ingresando...';
                            }
                            else {
                                elem.className = 'button Btn2';
                                elem.value = 'Ingresar';
                            }
                        }
                    }
                    
                    function disableAndSubmitButton(button) {
                        button.className = 'button Btn2Dis'; // the LH styleClass for disabled buttons.
                        //button.disabled = true; // si esta disabled no ejecuta la accion
                        button.onMouseOver = null;
                        button.onMouseOut = null;
                        button.onBlur = null;
                        button.onFocus = null;
                        button.value = 'Ingresando...';
                        //button.form.submit(); 
                        return true; 
                    }
                    ]]></script>
                </ui:head>
                <ui:body binding="#{Login.body1}" focus="form1:tfUser" id="body1" imageURL="/resources/fondo_pagina.png" style="background-color: rgb(222, 222, 222); -rave-layout: grid">
                    <ui:form binding="#{Login.form1}" id="form1">
                        <div class="contenedorLogin">
                            <div class="topLogin">
                                <ui:staticText binding="#{Login.staticText1}" escape="false" id="staticText1"
                                    style="color: #cc0000; font-size: 24px; font-weight: bold" styleClass="" text="Vipians®: Sistema de Gestión de Municipalidades"/>
                            </div>
                            <div class="midLogin">
                                <ui:panelLayout binding="#{Login.lpPanel1}" id="lpPanel1" style="background-image: url(./resources/login/bg_log.png); height: 100%; position: relative; width: 100%; -rave-layout: grid">
                                    <ui:panelLayout binding="#{Login.lpIngreso}" id="lpIngreso" style=" height: 100px; left: 24px; top: 24px; position: absolute; width: 230px">
                                        <ui:label binding="#{Login.lblUser}" id="lblUser"
                                            style="color: rgb(255, 255, 255); font-size: 10px; font-weight: bold; left: 34px; top: 12px; position: absolute"
                                            styleClass="label" text="Usuario:"/>
                                        <ui:textField binding="#{Login.tfUser}" columns="18" id="tfUser"
                                            style="font-size: 12px; left: 96px; top: 8px; position: absolute;  width: 135px;" styleClass="textField"/>
                                        <ui:label binding="#{Login.lblPassword}" id="lblPassword"
                                            style="color: rgb(255, 255, 255); font-size: 10px; font-weight: bold; left: 12px; top: 44px; position: absolute"
                                            styleClass="label" text="Contraseña:"/>
                                        <ui:passwordField binding="#{Login.pfPassword}" columns="18" id="pfPassword"
                                            style="font-size: 12px; left: 96px; top: 40px; position: absolute;  width: 135px;" styleClass="textField"/>
                                        <ui:button action="#{Login.btnIngresar_action}" binding="#{Login.btnIngresar}" id="btnIngresar"
                                            onClick="disableAndSubmitButton(this);" style="left: 95px; top: 72px; position: absolute; width: 104px"
                                            styleClass="button" text="Ingresar"/>
                                    </ui:panelLayout>
                                    <ui:panelLayout binding="#{Login.lpFalloIngreso}" id="lpFalloIngreso" rendered="false" style="height: 100px; left: 24px; top: 24px; position: absolute; width: 230px">
                                        <ui:staticText binding="#{Login.stMensaje}" id="stMensaje"
                                            style="font-weight: bold; height: 58px; left: 4px; top: 8px; position: absolute; width: 223px" text="Nombre de usuario o contraseña inválidos. Póngase en contacto con el administrador."/>
                                        <ui:button action="#{Login.btnVolver_action}" binding="#{Login.btnVolver}" id="btnVolver"
                                            style="left: 87px; top: 74px; position: absolute" styleClass="button" text="Volver"/>
                                    </ui:panelLayout>
                                    <ui:hyperlink binding="#{Login.hyperlink1}" id="hyperlink1"
                                        style="height: 36px; left: 12px; top: 277px; position: absolute; width: 150px" target="_blank" url="http://www.trascender.com.ar"/>
                                </ui:panelLayout>
                            </div>
                            <div class="botLogin">
                                <ui:staticText binding="#{Login.staticText2}" escape="false" id="staticText2" style="font-size:10px" text="&lt;b&gt;Trascender S.R.L. - Todos los Derechos Reservados&lt;/b&gt;"/>
                            </div>
                            <div class="justify">
                                <ui:messageGroup binding="#{Login.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                <ui:staticText binding="#{Login.staticText3}" escape="false" id="staticText3" style="font-size: 11px; font-family: Verdana;" text="&lt;br/&gt;&#xa;&lt;b&gt;Vipians®&lt;/b&gt;&#xa;Bienvenidos a Vipians, software para Municipios y Ayuntamientos orientado a la especialización y productividad. Hemos hecho un gran esfuerzo por poner a su alcance lo mejor en tecnología de presentación y acceso a datos. Creemos con certeza que este  producto satisfará sus necesidades, así como ayudará a mejorar la calidad de los servicios que se prestan al contribuyente. El modelo de software libre nos ha acercado a estas posibilidades, nos ha dado una visión, un horizonte. Queremos compartirlo con ustedes.&#xa;&lt;br/&gt;&lt;br/&gt;&#xa;&lt;b&gt;¿Qué significa el nombre?&lt;/b&gt; Vipians es un nombre de una familia etrusca que vivió aproximadamente en el siglo III A.C. Las inscripciones halladas en las tumbas comentaban su generosidad y hospitalidad. El patronímico era Sethre, Velthur el matronímico. La familia de la madre tenía por nombre Meclasia, y su matronímico era Thanchvilu."/>
                            </div>
                        </div>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
