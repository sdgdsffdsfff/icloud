package com.easytrack.commons.hierarchy;

import java.io.Serializable;

public abstract interface Hierarchyable extends Serializable
{
  public abstract int getId();

  public abstract int getParentID();

  public abstract void setParentID(int paramInt);

  public abstract String getName();
}

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.hierarchy.Hierarchyable
 * JD-Core Version:    0.6.2
 */