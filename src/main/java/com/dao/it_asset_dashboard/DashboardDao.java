package com.dao.it_asset_dashboard;



public interface DashboardDao {
	public String getComputingData(String formation_code, String susno);
	public String getPeripheralData(String formation_code, String susno);
	public String getComputingGraphData(String formation_code, String susno);
	public String getComputingWarrentyDueGraphData(int year,String formation_code, String susno);
	public String getPeripheralWarrentyDueGraphData(int year,String formation_code, String susno);
	public String getComputingADNData(String formation_code, String susno);
	
	
	public String getaVehicleData(String formation_code, String susno);
	public String getbVehicleData(String formation_code, String susno);
	public String getcVehicleData(String formation_code, String susno);
	

}
