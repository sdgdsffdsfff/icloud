package com.easytrack.commons.db;

public abstract interface SequenceTable
{
  public abstract String[] getTables();

  public abstract String[] getOIDTables();

  public abstract String[] getFormValueTables();

  public abstract boolean isGlobeUnique();
}

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.SequenceTable
 * JD-Core Version:    0.6.2
 */