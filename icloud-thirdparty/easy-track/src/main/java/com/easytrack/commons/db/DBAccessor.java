package com.easytrack.commons.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract interface DBAccessor
{
  public abstract void setSql(String paramString);

  public abstract void setUpdateValues(List paramList);

  public abstract int executeUpdate()
    throws SQLException;

  public abstract int[] executeBatchUpdate()
    throws SQLException;

  public abstract void setRowCountPerPage(int paramInt);

  public abstract void setConditionValues(List paramList);

  public abstract DBRowSet getPageData(int paramInt)
    throws SQLException;

  public abstract DBRowSet getBatchPageData(int paramInt)
    throws SQLException;

  public abstract String getSequence(String paramString, int paramInt)
    throws SQLException;

  public abstract String[] getSequence(String paramString, int paramInt1, int paramInt2)
    throws SQLException;

  public abstract Connection getConnection();

  public abstract void setConnection(Connection paramConnection);
}

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessor
 * JD-Core Version:    0.6.2
 */