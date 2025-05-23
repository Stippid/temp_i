package com.dao.helpDesk;


import java.util.Date;
import java.util.List;
import java.util.Map;


import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;

public interface HelpDAO {


	public List<Map<String, Object>> getCompletedTicketCount();
	public List<Map<String, Object>> getcreateTicketCount();
	public List<Map<String, Object>> getpendingTicketCount();
	public List<Map<String, Object>> getassignTicketCount();
	public List<Map<String, Object>> getinprogressTicketCount();
	
	public List<Map<String, Object>> getCompletedTicketCountJdbc(String qry);
	
	public HD_TB_BISAG_TICKET_GENERATE getUploadScreenShotByid(int id);
	
	public List<Map<String, Object>> getAllCodeListJdbc(String ticket, String ticket_status, String from_date,
			String to_date, String help_topic, String userId, String roleid, String module, String label1);
	public HD_TB_BISAG_TICKET_GENERATE gethelpbyId(int id);
	
	public List<Map<String, Object>> getSearchMercuryList1(String msg, String valid_upto);
	public Boolean getmsgExist(String msg, Date valid_upto);
}