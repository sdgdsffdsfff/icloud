package com.easytrack.commons.db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract interface IBaseDAO
{
  public abstract void setConnection(Connection paramConnection);

  public abstract Connection getConnection();

  public abstract void initSequence(int paramInt)
    throws Exception;

  public abstract String getSequence(String paramString, int paramInt)
    throws SQLException;

  public abstract String[] getSequence(String paramString, int paramInt1, int paramInt2)
    throws SQLException;
}

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.IBaseDAO
 * JD-Core Version:    0.6.2
 */