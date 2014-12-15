package com.trascender.framework.system.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

import com.trascender.framework.system.interfaces.SystemMetaclase;
import com.trascender.framework.util.Metaclase;

@Stateful(name = "ejb/SystemMetaclase")
public class SystemMetaclaseBean implements SystemMetaclase{
	
	public Map<String, Metaclase> mapaMetaclases = new HashMap<String, Metaclase>();

	public Map<String, Metaclase> getMapaMetaclases() {
		return mapaMetaclases;
	}

}
