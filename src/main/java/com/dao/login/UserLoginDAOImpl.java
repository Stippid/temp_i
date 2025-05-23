package com.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Role;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.UserLogin;
import com.models.UserRole;
import com.models.Helpdesk.HD_MARQUEE;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class UserLoginDAOImpl implements UserLoginDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	//@Override
	@Override
	public void save(UserLogin login) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(login);
		tx.commit();
		session.close();
	}

	//@Override
	@Override
	public void update(UserLogin login) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(login);
		tx.commit();
		session.close();
	}

	//@Override
	@Override
	public void delete(UserLogin login) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(login);
		tx.commit();
		session.close();
	}

	//@Override
	@Override
	public UserLogin findByRoleId(int userId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from UserLogin where userId=:id");
		q.setParameter("id", userId);
		UserLogin list = (UserLogin) q.list().get(0);
		tx.commit();
		session.close();
		return list;
	}

	//@Override
	@Override
	@SuppressWarnings("unchecked")
	public List<UserLogin> list() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from UserLogin");
		List<UserLogin> list = q.list();
		tx.commit();
		session.close();
		return list;
	}

	//@Override
	@Override
	@SuppressWarnings("unchecked")
	public List<UserLogin> list(int start, int end) {
		String hql="from UserLogin";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setFirstResult(start);
		q.setMaxResults(end);
		List<UserLogin> list = q.list();
		tx.commit();
		session.close();
		return list;
	}

	//@Override
	@Override
	@SuppressWarnings("unchecked")
	public Boolean authenticate(String userName, String password)
	{
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery("FROM UserLogin U where U.userName=:userName and U.password =:password and U.enabled=1");
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			users = query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}
		if(users.size()>0)
		{
			/*Log log = new Log();
			log.setActivityId(Long.valueOf(1));
			log.setInformation(username + " logged in");
			log.setActivityBy(users.get(0).getId());
			logdao.addLog(log);*/
			return true;
		}
		else
		{
			return false;
		}
	}

	//@Override
	@Override
	@SuppressWarnings("unchecked")
	public UserLogin findUser(String userName, String password)
	{
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery("FROM UserLogin U where U.userName=:userName and U.password =:password and U.enabled=1");
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			users = query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if(users.size()>0)
		{
			return users.get(0);
		}
		else
		{
			return null;
		}
	}
	//@Override
	@Override
	@SuppressWarnings("unchecked")
	public UserLogin findUser(String userName)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM UserLogin U where U.userName=:userName");
		q.setParameter("userName", userName);
		List<UserLogin> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	//@Override
	@Override
	public DataSet<UserLogin> findUsersWithDatatablesCriterias(DatatablesCriterias criterias) {
		List<UserLogin> users = findUsersCriterias(criterias);
		Long count = getTotalCount();
		Long countFiltered = getFilteredCount(criterias);
		return new DataSet<UserLogin>(users, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<UserLogin> findUsersCriterias(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("From UserLogin u");

		/**
		 * Step 1: global and individual column filtering
		 */
		queryBuilder.append(getFilterQuery(criterias));

		/**
		 * Step 2: sorting
		 */
		if (criterias.hasOneSortedColumn()) {

			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				orderParams.add("u." + columnDef.getName() + " " + columnDef.getSortDirection());
			}

			Iterator<String> itr2 = orderParams.iterator();
			while (itr2.hasNext()) {
				queryBuilder.append(itr2.next());
				if (itr2.hasNext()) {
					queryBuilder.append(" , ");
				}
			}
		}
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());

		/**
		 * Step 3: paging
		 */
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		List<UserLogin> list = q.list();
		tx.commit();
		session.close();

		return list;
	}

	private static StringBuilder getFilterQuery(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();

		/**
		 * Step 1.1: global filtering
		 */
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			queryBuilder.append(" WHERE ");

			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					paramList.add(" LOWER(u." + columnDef.getName()
					+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
				}
			}

			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" OR ");
				}
			}
		}

		/**
		 * Step 1.2: individual column filtering
		 */
		if (criterias.hasOneFilterableColumn() && criterias.hasOneFilteredColumn()) {
			paramList = new ArrayList<String>();

			if(!queryBuilder.toString().contains("WHERE")){
				queryBuilder.append(" WHERE ");
			}
			else{
				queryBuilder.append(" AND ");
			}

			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable()){
					if(StringUtils.isNotBlank(columnDef.getSearch())) {
						paramList.add(" LOWER(u." + columnDef.getName()
						+ ") LIKE '%?%'".replace("?", columnDef.getSearch().toLowerCase()));
					}
				}
			}

			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" AND ");
				}
			}
		}

		return queryBuilder;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCount(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("From UserLogin u");
		queryBuilder.append(getFilterQuery(criterias));

		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());

		/**
		 *  paging
		 */
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		List<UserLogin> list = q.list();
		tx.commit();
		session.close();

		return Long.parseLong(String.valueOf(list.size()));
	}

	private Long getTotalCount() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(u) FROM UserLogin u");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	//@Override
	@Override
	public int getUserId(String userName) {
		return findUser(userName).getUserId();
	}

	@Override
	public String getDist_Code(int userid) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT u.dist_code FROM UserLogin u where userId =:userid ");
		q.setParameter("userid", userid);
		String  distCode = (String) q.list().get(0);
		tx.commit();
		session.close();
		return distCode;
	}

	@Override
	public int getst_Code(int userid) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT u.statecode FROM UserLogin u where userId =:userid ");
		q.setParameter("userid", userid);
		int stCode = (Integer) q.list().get(0);
		tx.commit();
		session.close();
		return stCode;
	}

	@Override
	public void register(UserLogin userLogin) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.flush();
		Transaction tx = session.beginTransaction();
		int id=(Integer)session.save(userLogin);

		UserRole userRole = new UserRole();
		userRole.setUserId(userLogin.getUserId());

		session.save(userRole);

		userLogin.setUserName(userLogin.getUserName());
		userLogin.setPassword(userLogin.getPassword());
		userLogin.setAccountNonExpired(1);
		userLogin.setAccountNonLocked(1);
		userLogin.setCredentialsNonExpired(1);
		userLogin.setEnabled(1);

		session.update(userLogin);

		tx.commit();
		session.close();

		userLogin.setUserId(id);

	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean isUserExist(String userName) {
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery("FROM UserLogin U where lower(U.userName)=:userName");
			query.setParameter("userName", userName.toLowerCase());
			users = query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean isEmailExist(String email) {
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery("FROM UserLogin U where lower(U.email)=:email");
			query.setParameter("email", email);
			users = query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean isMobileExist(String mobile)
	{
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery("FROM UserLogin U where U.telephone=:mobile");
			query.setParameter("mobile", mobile);
			users = query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getLevel_Type(int userId)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select U.level_type FROM UserLogin U where U.userId =:userId");
		q.setParameter("userId", userId);
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public String gethealthType(int userId)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select U.health_type FROM UserLogin U where U.userId =:userId");
		q.setParameter("userId", userId);
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public String getnameHE(int userId)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select U.name_healthcare FROM UserLogin U where U.userId =:userId");
		q.setParameter("userId", userId);
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String gettype_HE(int userId)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select U.health_type_name FROM UserLogin U where U.userId =:userId");
		q.setParameter("userId", userId);
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	@Override
	public String getMobileno(int userid) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT u.telephone FROM UserLogin u where userId =:userid");
		q.setParameter("userid", userid);
		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public String getEmailid(int userid) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT u.email FROM UserLogin u where userId =:userid ");
		q.setParameter("userid", userid);
		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getOwnership(int userId)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select U.ownership FROM UserLogin U where U.userId =:userId");
		q.setParameter("userId", userId);
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getAllUserLoginData(int userId)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM UserLogin U where U.userId =:userId");
		q.setParameter("userId", userId);
		List<String> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public int getRoleIdByUserId(int userId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT u.roleId FROM UserRole u where userId =:userId ");
		q.setParameter("userId", userId);
		int stCode = (Integer) q.list().get(0);
		tx.commit();
		session.close();
		return stCode;
	}

	@Override
	public int ChangeStatusToPendingInFmcrOfAVeh() {
		String hql4="update TB_TMS_CENSUS_RETURN  SET status = '0'";
		Session session4 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx4 = session4.beginTransaction();
		Query que4= session4.createQuery(hql4);
		que4.executeUpdate();
		tx4.commit();
		session4.close();
		return 0;
	}

	@Override
	public String getRoleUrl(String role) {
		return findRole_url(role).getRole_url();
	}
	@Override
	public String getRoleType(String role) {
		return findRole_url(role).getRole_type();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Role findRole_url(String role)
	{
		String hql="FROM Role U where U.role=:role";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setParameter("role", role);
		List<Role> list = q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TB_LDAP_MODULE_MASTER> getModulelist(int roleid,Boolean medical)
	{
		String hql ="";
		if(medical == true) {
			hql ="from TB_LDAP_MODULE_MASTER b where  b.id > 0 and b.id in (select a.module.id from TB_LDAP_ROLEMASTER a where a.roleid=:roleid and  a.module.id != 0 ) order by b.module_name";
		}else {
			hql ="from TB_LDAP_MODULE_MASTER b where b.id > 0 and module_name != 'MEDICAL' and b.id in (select a.module.id from TB_LDAP_ROLEMASTER a where a.roleid=:roleid and  a.module.id != 0 ) order by b.module_name";
		}
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setParameter("roleid", roleid);
		List<TB_LDAP_MODULE_MASTER> list = q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TB_LDAP_SUBMODULE_MASTER> getSubModulelist(int  moduleid,int roleid)
	{
		String hql ="from TB_LDAP_SUBMODULE_MASTER b where b.module.id =:moduleid and b.id in (select a.sub_module.id from TB_LDAP_ROLEMASTER a where a.roleid=:roleid) order by b.seq_id ";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setInteger("roleid", roleid);
		q.setInteger("moduleid", moduleid);
		List<TB_LDAP_SUBMODULE_MASTER> list = q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TB_LDAP_SCREEN_MASTER> getScreenlist(int  moduleid,int submoduleid,int roleid)
	{
		String hql ="from TB_LDAP_SCREEN_MASTER b where b.id in (select a.screen.id from TB_LDAP_ROLEMASTER a where a.roleid=:roleid and  a.module.id =:moduleid and a.sub_module.id =:submoduleid) order by b.id";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setInteger("roleid", roleid);
		q.setInteger("moduleid", moduleid);
		q.setInteger("submoduleid", submoduleid);
		List<TB_LDAP_SCREEN_MASTER> list = q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public  List<Map<String, Object>>  getSearchMercuryList1(String msg,String valid_upto) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;

				if(msg != "" && msg !=null ) {
					qry += " and msg = ?";
				}
				if(valid_upto !="" && valid_upto !=null) {
					qry += " and valid_upto = ?";
				}


				q="select distinct msg,valid_upto,id from tb_hd_mercuries WHERE valid_upto >= now() and valid_upto <= valid_upto and status='1' "+ qry  ;
				stmt=conn.prepareStatement(q);
				int j=1;
				if(msg != "" && msg !=null ) {
					stmt.setString(j, msg);
					j += 1;
				}
				if(valid_upto !="" && valid_upto !=null) {
					qry += " and created_on::timestamp::date < '"+valid_upto+"'";
					stmt.setString(j, valid_upto);

				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}


					String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getString("id")+",'"+rs.getString("msg")+"','"+rs.getDate("valid_upto")+"')}else{ return false;}\"";
					String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
					String f = "";

					f += updateButton;

					columns.put(metaData.getColumnLabel(3), f);
					list.add(columns);
				}
				rs.close();
				stmt.close();
				conn.close();
			}catch (SQLException e) {
				//throw new RuntimeException(e);
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return list;
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public Boolean getmsgExist(String msg, Date valid_upto) {
		String hql="from HD_MARQUEE where msg=:msg and valid_upto=:valid_upto ";
		List<HD_MARQUEE> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("msg", msg);
			query.setParameter("valid_upto", valid_upto);
			users = query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<String> getLayoutlist()
	{
		String hql ="select t.msg from HD_MARQUEE t WHERE t.valid_upto >= now() AND t.valid_upto <=valid_upto";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		tx.commit();
		session.close();
		return list;
	}

}