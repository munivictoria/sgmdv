package com.trascender.contabilidad.reporte.interfaz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.framework.util.THashMap;

public class InterfazModuloContable {
	
	THashMap<Cuenta> mapaImportes = new THashMap<Cuenta>();
	
	public InterfazModuloContable(List<MovimientoCajaIngreso> pListaMovimientosCajaIngreso){
		procesarMovimientosCajaIngreso(pListaMovimientosCajaIngreso);
	}
	
	private void procesarMovimientosCajaIngreso(List<MovimientoCajaIngreso> pListaMovimientos){
		for (MovimientoCajaIngreso cadaMovimientoCajaIngreso : pListaMovimientos){
			mapaImportes.add(cadaMovimientoCajaIngreso.getCuenta(), cadaMovimientoCajaIngreso.getImporte());
		}
	}
	
	public void imprimirAArchivo(File pFile) throws Exception{
		  FileWriter fstream = new FileWriter(pFile);
		  BufferedWriter out = new BufferedWriter(fstream);
		  for (Cuenta cadaCuenta : mapaImportes.keySet()){
				out.write("\t"+cadaCuenta.getCodigoImputacionCompleto()+"\t "+mapaImportes.get(cadaCuenta)+"\n");
			}
		  out.close();
	}
	
	public void imprimirEnPantalla(){
		for (Cuenta cadaCuenta : mapaImportes.keySet()){
			System.out.println(cadaCuenta +"\t $ "+mapaImportes.get(cadaCuenta));
		}
	}

}
