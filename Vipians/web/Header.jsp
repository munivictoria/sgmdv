<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Header.page1}" id="page1">
            <ui:html binding="#{Header.html1}" id="html1">
                <ui:head binding="#{Header.head1}" id="head1" title="Vipians">
                    <ui:link binding="#{Header.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <style>
                        #muniLetGrande{
                            margin-top:5px; 
                            margin-right:10px; 
                            font-family: Verdana; 
                            font-size:12px;
                            text-align: center;
                        }
                        #time{
                            clear:both;
                            margin-right:10px;
                            font-family: Verdana;
                            font-size:10px;
                            text-align: center;
                        }
                        #esquinaDerechasuperior{
                            float:right;
                            margin-right: 10px;
                        }
                        #image1{
                                float:left;
                                height:75px;
                                width:45%;
                                height: 100%;
                        }
                        #logoMunicipalidad{
                                float:left;
                                margin-left:10px;
                                width:50px;
                                height:100%;
                                margin-left:4%
                        }
                    </style>
                    <script><![CDATA[
                       function displayDate() {
                            var this_month = new makeArray(12);
                            this_month[0]  = "Enero";
                            this_month[1]  = "Febrero";
                            this_month[2]  = "Marzo";
                            this_month[3]  = "Abril";
                            this_month[4]  = "Mayo";
                            this_month[5]  = "Junio";
                            this_month[6]  = "Julio";
                            this_month[7]  = "Agosto";
                            this_month[8]  = "Septiembre";
                            this_month[9]  = "Octubre";
                            this_month[10] = "Noviembre";
                            this_month[11] = "Diciembre";

                            var this_day_e = new makeArray(7);
                            this_day_e[0]  = "Domingo";
                            this_day_e[1]  = "Lunes";
                            this_day_e[2]  = "Martes";
                            this_day_e[3]  = "Miércoles";
                            this_day_e[4]  = "Jueves";
                            this_day_e[5]  = "Viernes";
                            this_day_e[6]  = "Sábado";

                            var today = new Date();
                            var day   = today.getDate();
                            var month = today.getMonth();
                            var year  = today.getYear();
                            var dia = today.getDay();
                            if(year < 1000) {
                                year += 1900;
                            }
                            return( " " + this_day_e[dia] + ", " + day + " de " + this_month[month] + " de " + year);
                        }

                        function startTime()
                        {
                            var today=new Date()
                            var h=today.getHours()
                            var m=today.getMinutes()
                            var s=today.getSeconds()
                            m=checkTime(m)
                            s=checkTime(s)
                            // con segundos
                            //document.getElementById('time').innerHTML=h+":"+m+":"+s+" Hs.<br/>"+displayDate()
                            // sin segundos
                            document.getElementById('time').innerHTML=displayDate()+"<br>"+h+":"+m+" Hs.";
                            t=setTimeout('startTime()',500)
                        }

                        function checkTime(i)
                        {
                            if (i<10) {i="0" + i}
                            return i
                        }
                        function makeArray(n){
                            this.length = n;
                            var i = 1;
                            for(i=1;i<n;i++){
                                this[i]=0;
                            }
                            return this;
                        }]]>
                  </script>
                </ui:head>
                <ui:body binding="#{Header.body1}" id="body1" onLoad="startTime();" style="-rave-layout: grid">
                    <ui:form binding="#{Header.form1}" id="form1" style="border-bottom:1px solid #000; height:59px">
                        <div align="left" id="imagen" style="float:left; height: 100%;">
                            <ui:image binding="#{Header.image1}" id="image1" url="/resources/imagenes/cabecera.png" style="height: 100%;"/>
                        </div>
                    <div id ="esquinaDerechasuperior" align="right">
                        <table>
                            <tr>
                                <td nowrap="nowrap">
                                    <div id="muniLetGrande"><b><ui:staticText text="#{ApplicationBean1.municipalidad.nombre}"/></b></div>
                                    <div id="time"></div>
                                </td>
                                <td>
                                    <ui:image url ="/faces/ImageServlet" id="logoMunicipalidad" 
                                              style="height: 50px;" binding="#{Header.image2}"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    </ui:form>
                </ui:body>
            </ui:html>
               
        </ui:page>
    </f:view>
</jsp:root>