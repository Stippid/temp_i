package com.dao.itasset.dropdown;

import java.util.List;
import java.util.Map;

public interface DropdownDao {
	
	public List<Map<String, Object>> ComputingAssetListNew();
	public List<Map<String, Object>> ComputingMakeListNew();
	public List<Map<String, Object>> ComputingModelListNew();
	public List<Map<String, Object>> processor_typeListNew();
	public List<Map<String, Object>> ramListNew();
	public List<Map<String, Object>> hddListNew();
	public List<Map<String, Object>> OperatingsystemListNew();
	public List<Map<String, Object>> OfficeListNew();
	public List<Map<String, Object>> AntiVirusListNew();
	public List<Map<String, Object>> OSFirmwareListNew();
	public List<Map<String, Object>> DplyenvListNew();
	public List<Map<String, Object>> BudgetListNew();
	
public List<Map<String, Object>> peripheral_typeListNew();
	
	public List<Map<String, Object>> hwListNew();
	public List<Map<String, Object>> typeListNew();
	public List<Map<String, Object>> ups_capacityNew();
	public List<Map<String, Object>> paper_sizeNew();
	public List<Map<String, Object>> paper_connetivity();
	public List<Map<String, Object>> hardware_interfaceNew();
	public List<Map<String, Object>> type_connetivity();
	public List<Map<String, Object>> getnetwork_features();
	public List<Map<String, Object>> getethernet_portnew();
	public List<Map<String, Object>> getmanagement_layernew();
}
