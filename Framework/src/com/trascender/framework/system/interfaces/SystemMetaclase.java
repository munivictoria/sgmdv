package com.trascender.framework.system.interfaces;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;

import com.trascender.framework.util.Metaclase;

@Local
public interface SystemMetaclase {
	
	public static final String JNDI_NAME = "ejb/SystemMetaclase";
	
	public Map<String, Metaclase> getMapaMetaclases();

}
