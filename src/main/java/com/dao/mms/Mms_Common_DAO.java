package com.dao.mms;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public interface Mms_Common_DAO {
	
	//FINAL METHODS START
	public String getRegdTransName(String nPara);
	public List<String> getMMSPRFtListBySearch(String nParaValue);
	public List<String> getPrfListBySUSNo(String nParaValue);
	public List<String> getCensusNoBySUSNo(String nSusNo,String nPrfCode);
	public List<String> getMMSDistinctMlccsList(String nParaValue,String nPara);
	
	/*public List<String> getMMSUnitListBySearch(String nParaValue);*/
	public List<String> getMMSUnitListBySearch(String nParaValue, HttpSession s1);
	
	public List<String> getMMSDepotListBySearch(String nParaValue);
	public List<String> getMMSDistinctHirarName(String nHirar, String nCnd, String codelevel);
	public List<String> getMMSHirarNameBySUS(String FindWhat, String nSUSNo);
	public List<String> getUHValue(String nSUSNo,String nPRF,String nHldType,String nEqptType,String nPara);
	public List<String> getUEValue(String nSUSNo,String nPRF,String nWE,String nPara);
	public List<String> getMMSMlccsList(String census,String nPara);
	public List<String> getMMSEncList(List<String> l,HttpSession s1);
	public List<String> getMMSMaxDt(String sus);
	//FINAL METHODS END
	public ArrayList<ArrayList<String>> getMMSPRFtListBySearch2(String nParaValue);
	public List<String> getExtendDate();
	public List<String> getExtendDateOnChange(String d);
	public String RegdDataTransfer(String regnSeqno, String nFSUSNo,String nFHldType,String nTSUSNo,String nTHldType,String nPara, String nFileName, String TFileName);
	//public ArrayList<ArrayList<String>> getMMSRData(int userid);
	public ArrayList<ArrayList<String>> getMMSRData(HttpSession sessionA);
	public ArrayList<ArrayList<String>> getMMSRAccess(int userid);
	
	public List<String> getActiveUnitNameFromSusNo_Without_Enc(String sus_no,HttpSession sessionA);
	 
	 
	public ArrayList<ArrayList<String>> getUnitDetailsList();
	 
	 
	 ///////////////////
	 public List<String> getTypeofHldgBySUSNo_frmtbl(String nParaValue);
}