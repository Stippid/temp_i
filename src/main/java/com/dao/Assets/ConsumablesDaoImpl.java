package com.dao.Assets;

import javax.sql.DataSource;

public class ConsumablesDaoImpl implements ConsumablesDao {
	 private DataSource dataSource;
     public void setDataSource(DataSource dataSource) {
       this.dataSource = dataSource;
     }
}
