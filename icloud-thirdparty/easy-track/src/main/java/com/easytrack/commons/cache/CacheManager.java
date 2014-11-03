package com.easytrack.commons.cache;

import com.easytrack.commons.config.Config;
import com.easytrack.commons.db.PageInfo;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.cache.Cache;
import org.jboss.cache.CacheFactory;
import org.jboss.cache.DefaultCacheFactory;
import org.jboss.cache.Fqn;
import org.jboss.cache.Node;
import org.jboss.cache.config.Configuration;
import org.jboss.cache.config.RuntimeConfig;

public class CacheManager
{
  public static final String userCacheMgr = "User";
  public static final String UserProfileCacheMgr = "UserProfile";
  public static final String UserProfileCacheMgr_byUserID = "UserProfileByUserID";
  public static final String companyCacheMgr = "Company";
  public static final String roleCacheMgr = "Role";
  public static final String userRoleCacheMgr = "userRole";
  public static final String roleHierarchyMgr = "RoleHierarchy";
  public static final String sysPropertyCacheMgr = "SysProperty";
  public static final String UserMessagesCacheMgr = "UserMessages";
  public static final String AddressCacheMgr = "Address";
  public static final String codeTableCacheMgr = "CodeTable";
  public static final String WorkflowCacheMgr = "WorkflowCacheMgr";
  public static final String FormSchemaCacheMgr = "FormSchema";
  public static final String PortfolioCacheMgr = "Portfolio";
  public static final String ProjectCacheMgr = "Project";
  public static final String CalendarCacheMgr = "Calendar";
  public static final String lifeTemplateCacheMgr = "lifeTemplate";
  public static final String lifeTemplatePhaseCacheMgr = "lifeTemplatePhase";
  public static final String AvailableHourCacheMgr = "AvailableHour";
  public static final String companyFunctionCacheMgr = "CompanyFunction";
  public static final String roleFunctionDefaultCacheMgr = "roleFunctionDefault";
  public static final String roleFunctionCacheMgr = "roleFunction";
  public static final String functionCacheMgr = "Function";
  public static final String objectPermissionCacheMgr = "ObjectPermission";
  public static final String folderCacheMgr = "Folder";
  public static final String registeredSchemaCacheMgr = "RegisteredSchema";
  public static final String CalendarRelationCacheMgr = "CalendarRelation";
  public static final String ObjectTypeCacheMgr = "ObjectType";
  public static final String DataTypeCacheMgr = "DataType";
  public static final String SystemParameterCacheMgr = "SystemParameter";
  public static final String OperatorCacheMgr = "Operator";
  public static final String EventTypeCacheMgr = "EventType";
  public static final String PriceCacheMgr = "price";
  public static final String ObjectRealtionCacheMgr = "ObjectRelation";
  public static final String ObjectRealtionConfigCacheMgr = "ObjectRelationConfig";
  public static final String UIStateCacheMgr = "UIStateCacheMgr";
  public static final String SequenceCacheMgr = "SequenceCacheMgr";
  public static final String QueryCacheMgr = "QueryCacheMgr";
  public static final String demandCacheMgr = "demandCacheMgr";
  private static HashMap cacheMgrMap = new HashMap();
  public static final String groupCacheMgr = "groupCacheMgr";
  private static boolean threadStart = false;

  private Node map = null;

  private static Cache cache = null;

  private static CacheFactory factory = new DefaultCacheFactory();

  public static boolean hasCache = true;

  private CacheManager(String key)
  {
    if (cache == null) {
      File f = new File(Config.getHomeDir() + "/config/jboss-cache.xml");
      if (f.exists())
        try {
          FileInputStream in = new FileInputStream(f);
          cache = factory.createCache(in);
        } catch (Exception e) {
          e.printStackTrace();
          cache = factory.createCache();
        }
      else {
        cache = factory.createCache();
      }
    }
    cache.getConfiguration().getRuntimeConfig().setTransactionManager(null);
    Fqn child = Fqn.fromString(key);
    this.map = cache.getRoot().addChild(child);
  }

  public static final boolean hasCache()
  {
    return hasCache;
  }

  public static CacheManager getCacheManager(String key)
  {
    if (!threadStart) {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            while (true)
            {
              HashMap map = CacheManager.cacheMgrMap;
              Set c = map.keySet();
              Iterator iterator = c.iterator();
              while (iterator.hasNext()) {
                iterator.next();
              }
              Thread.sleep(20000L);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }).start();
      threadStart = true;
    }
    Object obj = cacheMgrMap.get(key);
    if (obj == null) {
      CacheManager cacheMgr = new CacheManager(key);
      cacheMgrMap.put(key, cacheMgr);
      obj = cacheMgr;
    }
    return (CacheManager)obj;
  }

  public static void removeCacheManager(String key)
  {
    cacheMgrMap.remove(key);
  }

  public synchronized Object addObject(Object key, Object value)
  {
    return this.map.put(key, value);
  }

  public synchronized void addObject(Map m)
  {
    this.map.putAll(m);
  }

  public Object getObject(Object key)
  {
    Object object = this.map.get(key);

    return object;
  }

  public Object getObject(Object key, boolean needClone)
  {
    Object object = this.map.get(key);
    if (needClone) {
      return ((CacheObject)object).clone();
    }
    return object;
  }

  public synchronized Object updateObject(Object key, Object value)
  {
    return this.map.put(key, value);
  }

  public synchronized Object removeObject(Object key)
  {
    return this.map.remove(key);
  }

  public synchronized void removeObject(Comparator filter, Object filterRefObj)
  {
    Object[] keys = this.map.getData().keySet().toArray();
    for (int i = keys.length - 1; i >= 0; i--) {
      Object obj = keys[i];
      if (filter.compare(obj, filterRefObj) == 0)
        this.map.remove(obj);
    }
  }

  public synchronized void removeObjectByValue(Comparator filter, Object filterRefObj)
  {
    Object[] keys = this.map.getData().keySet().toArray();
    for (int i = keys.length - 1; i >= 0; i--) {
      Object key = keys[i];
      Object value = this.map.getData().get(key);
      if (filter.compare(value, filterRefObj) == 0)
        this.map.remove(key);
    }
  }

  public synchronized void clear()
  {
    this.map.clearData();
  }

  public boolean containsKey(Object key)
  {
    return this.map.getData().containsKey(key);
  }

  public boolean containsValue(Object value)
  {
    return this.map.getData().containsValue(value);
  }

  public boolean isEmpty()
  {
    return this.map.getData().isEmpty();
  }

  public int size()
  {
    return this.map.getData().size();
  }

  public Object[] getObjectArray()
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      list.add(obj);
    }
    return list.toArray();
  }

  public List getObjectList()
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      list.add(obj);
    }
    return list;
  }

  public Object[] getObjectArray(Comparator filter, Object filterRefObj)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (filter.compare(obj, filterRefObj) == 0) {
        list.add(obj);
      }
    }
    return list.toArray();
  }

  public Object[] getObjectArrayByKey(Comparator filter, Object filterRefObj)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().keySet();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (filter.compare(obj, filterRefObj) == 0) {
        list.add(getObject(obj));
      }
    }
    return list.toArray();
  }

  public List getObjectList(Comparator filter, Object filterRefObj)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (filter.compare(obj, filterRefObj) == 0) {
        list.add(obj);
      }
    }
    return list;
  }

  public List getObjectList(Comparator filter, Object filterRefObj, Comparator orderBy)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (filter.compare(obj, filterRefObj) == 0) {
        list.add(obj);
      }
    }
    Collections.sort(list, orderBy);
    return list;
  }

  public Object[] getObjectArray(Comparator orderBy)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      list.add(obj);
    }
    Collections.sort(list, orderBy);
    return list.toArray();
  }

  public Object[] getObjectArray(Comparator filter, Object filterRefObj, Comparator orderBy)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (filter.compare(obj, filterRefObj) == 0) {
        list.add(obj);
      }
    }
    Collections.sort(list, orderBy);
    return list.toArray();
  }

  public PageInfo getPageInfo(PageInfo pageInfo)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      list.add(obj);
    }
    return processPageInfo(list, pageInfo);
  }

  public PageInfo getPageInfo(Comparator filter, Object filterRefObj, PageInfo pageInfo)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (filter.compare(obj, filterRefObj) == 0) {
        list.add(obj);
      }
    }
    return processPageInfo(list, pageInfo);
  }

  public PageInfo getPageInfo(Comparator orderBy, PageInfo pageInfo)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      list.add(obj);
    }
    Collections.sort(list, orderBy);
    return processPageInfo(list, pageInfo);
  }

  public PageInfo getPageInfo(Comparator filter, Object filterRefObj, Comparator orderBy, PageInfo pageInfo)
  {
    LinkedList list = new LinkedList();
    Collection collection = this.map.getData().values();
    Iterator iterator = collection.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (filter.compare(obj, filterRefObj) == 0) {
        list.add(obj);
      }
    }
    Collections.sort(list, orderBy);
    return processPageInfo(list, pageInfo);
  }

  private PageInfo processPageInfo(LinkedList list, PageInfo pageInfo)
  {
    int pageSize = pageInfo.getPageSize();
    int currentPage = pageInfo.getCurrentPage();

    if ((pageSize <= 0) || (currentPage <= 0)) {
      pageInfo.setItems(list);
      pageInfo.setRowCount(list.size());
      pageInfo.setPaged(false);
      return pageInfo;
    }

    if ((pageSize <= 0) || (currentPage <= 0) || ((currentPage - 1) * pageSize > list.size())) {
      pageInfo.setItems(new ArrayList());
      pageInfo.setRowCount(0);
      pageInfo.setPaged(false);
      return pageInfo;
    }

    int pageCount = list.size() / pageSize + 1;
    int startRowNo = (currentPage - 1) * pageSize;

    pageInfo.setPageCount(pageCount);
    pageInfo.setTotalRows(list.size());

    if (currentPage * pageSize <= list.size())
      pageInfo.setRowCount(pageSize);
    else {
      pageInfo.setRowCount(list.size() - startRowNo);
    }
    pageInfo.setItems(list.subList(startRowNo, startRowNo + pageInfo.getRowCount()));
    return pageInfo;
  }
}