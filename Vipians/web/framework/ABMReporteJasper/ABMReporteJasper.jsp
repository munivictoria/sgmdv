<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{framework$ABMReporteJasper$ABMReporteJasper.page1}" id="page1">
            <ui:html binding="#{framework$ABMReporteJasper$ABMReporteJasper.html1}" id="html1">
                <ui:head binding="#{framework$ABMReporteJasper$ABMReporteJasper.head1}" id="head1">
                    <ui:link binding="#{framework$ABMReporteJasper$ABMReporteJasper.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{framework$ABMReporteJasper$ABMReporteJasper.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{framework$ABMReporteJasper$ABMReporteJasper.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{framework$ABMReporteJasper$ABMReporteJasper.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{framework$ABMReporteJasper$ABMReporteJasper.head1.title}"/>
                                </caption>
                                <tbody>
								<tr>
									<td><br /></td>
								</tr>
									<tr>
										<td align="right" nowrap="nowrap">
											<ui:label binding="#{framework$ABMReporteJasper$ABMReporteJasper.lblNombre}" for="tfNombre" id="lblNombre"
											styleClass="label" text="Nombre" />
										</td>
										<td>
											<ui:textField binding="#{framework$ABMReporteJasper$ABMReporteJasper.tfNombre}" id="tfNombre" styleClass="textField" columns="40"/>
										</td>
									</tr> 
								<tr>
									<td align="right" nowrap="true">
									<ui:label binding="#{framework$ABMReporteJasper$ABMReporteJasper.lblNombreArchivo}"
											id="lblNombreArchivo" styleClass="label" text="Archivo" for="tfNombreArchivo"/>
										<td>
										<ui:textField	binding="#{framework$ABMReporteJasper$ABMReporteJasper.tfNombreArchivo}" id="tfNombreArchivo" styleClass="label subidora" style="padding-left:0pt !important;" columns="40"/>
										<ui:upload	binding="#{framework$ABMReporteJasper$ABMReporteJasper.upload}"	id="uploadReporte" styleClass="accionSubir" style="display:none"
											valueChangeListener="#{framework$ABMReporteJasper$ABMReporteJasper.procesarArchivo}"/>
										<ui:label binding="#{framework$ABMReporteJasper$ABMReporteJasper.lblAgregarArchivo}" id="lblAgregarArchivo" styleClass="buttonAgregar" style="height:15px; padding-top: 2px" onClick="procesarDatosLabelUpload()"/>
										<ui:label binding="#{framework$ABMReporteJasper$ABMReporteJasper.lblEliminarArchivo}" id="lblEliminarArchivo" onClick="borrarArchivo()"
										style="font-weight: bold; font-size: 10px; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; background-color: #EEE; background-image: url(/Vipians/resources/imagenes/botones/eraser13.png) ! important; padding: 2px 9px; cursor: pointer; background-repeat: no-repeat;" />
										<ui:hyperlink binding="#{framework$ABMReporteJasper$ABMReporteJasper.hyperLinkBajarArchivo}" id="hyperLinkBajarArchivo" onClick="descargarArchivoJasper()" actionListener="#{framework$ABMReporteJasper$ABMReporteJasper.procesarDescargarReporteJaper()}"	
										style="font-weight: bold; font-size: 10px; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; background-color: #EEE; background-image: url(/Vipians/resources/imagenes/botones/download-icon_small.png) ! important; padding: 2px 8px; cursor: pointer; background-repeat: no-repeat;" />
										</td>			
									</td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
							</tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{framework$ABMReporteJasper$ABMReporteJasper.btnGuardar_action}"
                                                binding="#{framework$ABMReporteJasper$ABMReporteJasper.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{framework$ABMReporteJasper$ABMReporteJasper.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{framework$ABMReporteJasper$ABMReporteJasper.btnCancelar_action}"
                                                binding="#{framework$ABMReporteJasper$ABMReporteJasper.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                                		<td colspan="4">
								<ui:messageGroup binding="#{framework$ABMReporteJasper$ABMReporteJasper.messageGroup}" id="messageGroup"
									styleClass="grupoMsg" />
							</td>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{framework$ABMReporteJasper$ABMReporteJasper.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMReporteJasper$ABMReporteJasper.idPagina}"/>
                        <ui:hiddenField binding="#{framework$ABMReporteJasper$ABMReporteJasper.hidIdSubSesion}" id="hidIdSubSesion" text="#{framework$ABMReporteJasper$ABMReporteJasper.idSubSesion}"/>
                        <ui:script binding="#{framework$ABMReporteJasper$ABMReporteJasper.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
       
       <script>
       <![CDATA[
                
       function procesarDatosLabelUpload(){
    	    var upload = document.getElementById("form1:uploadReporte");
    	    upload.click();
    	    upload.addEventListener('change', cambiarEstado, false);
    	}

        function cambiarEstado(evt){
    	
    	    var files = evt.target.files;
			for (var i = 0, f; f = files[i]; i++) {
				var reader = new FileReader();
    	        reader.onload = (function(theFile) {
    	            return function(e) {
    	                    nombre = f.name;
    	            };
    	        })(f);

    	        var lab = document.getElementById("form1:tfNombreArchivo");

    	        lab.value = "";
    	        lab.innerHTML = "";
    	        lab.value  = f.name;
    	        
    	        reader.readAsDataURL(f);
    	    }
    	}

       			function borrarArchivo(){
       			    var nameArchivo = document.getElementById("form1:tfNombreArchivo");
       			    nameArchivo.value = "";
       			}
       			
       			function descargarArchivoJasper(){
       			    var lab = document.getElementById("form1:hyperLinkBajarArchivo");

       			    if(lab.value != "") {
       					var titulo = titulo || "Descargando";
       			    	newWindow = window.open('/Vipians/faces/DescargarArchivoServlet', titulo);
       				}
       			}
       			
       			]]>
			</script>
        </ui:page>
    </f:view>
</jsp:root>
