package com.dao.orbat;

import java.util.ArrayList;
import java.util.List;

public interface AllMethodsDAO {
	
	public ArrayList<List<String>> getCorpsList(String fcode);
	public ArrayList<ArrayList<String>> getLOC_NRS_TypeLOC_TrnType(String locCode);
	public int getSusVersion(String sus_no);
	List<String> check_sus_hierarchy(String sus_no);
	 public ArrayList<List<String>> getCorpsListadm(String fcode);
		public ArrayList<List<String>> getCorpsListop(String fcode);
}
