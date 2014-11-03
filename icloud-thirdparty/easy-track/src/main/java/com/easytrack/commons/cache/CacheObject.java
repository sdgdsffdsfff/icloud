package com.easytrack.commons.cache;

public abstract interface CacheObject extends Cloneable
{
  public abstract Object clone();

  public abstract boolean isDeleted();

  public abstract void setDeleted(boolean paramBoolean);

  public abstract boolean isModified();

  public abstract void setModified(boolean paramBoolean);

  public abstract boolean isNew();

  public abstract void setNew(boolean paramBoolean);
}

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.cache.CacheObject
 * JD-Core Version:    0.6.2
 */