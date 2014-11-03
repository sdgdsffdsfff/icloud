package com.easytrack.component.profile;

import com.easytrack.commons.cache.CacheObject;
import com.easytrack.commons.hierarchy.Hierarchy;
import com.easytrack.commons.hierarchy.Hierarchyable;
import com.easytrack.commons.object.BatchObject;
import com.easytrack.commons.util.CipherUtils;
import com.easytrack.commons.util.MessageUtils;
import com.easytrack.component.profile.ejb.ProfileAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class User extends BatchObject
  implements Serializable, CacheObject
{
  private static final long serialVersionUID = 2309966283096561207L;
  public static final int DELETED = -1;
  public static final int ACTIVE = 1;
  public static final int INACTIVE = 2;
  public static final int CHINESE = 1;
  public static final int ENGLISH = 2;
  public static final int NORMAL_USER = 0;
  public static final int SYSTEM_ADMIN = 1;
  public static final int COMPANY_ADMIN = 2;
  public static final int DEVICE = 3;
  public static final int IS_RESOURCE = 1;
  public static final int SYSTEM_ALERT = 1;
  public static final int EMAIL_ALERT = 2;
  private String name;
  private String password;
  private int type;
  private int status;
  private String title;
  private String firstName;
  private String lastName;
  private String displayName;
  private int resourceFlag;
  private String email;
  private String fax;
  private String mobile;
  private String tel;
  private String zip;
  private String jobNumber;
  private int sex;
  private int loginFailedTimes;
  private Date birthday;
  private Date lastLoginTime;
  private Date currentLoginTime;
  private Date passwordChangeTime;
  private int defaultRoleID;
  private int language;
  private int alertMethod;
  private int loginTimes;
  private long sessionTime;
  private String flag;
  private int resourceTeamID;
  private String resourceTeamIDs;
  private int departmentID;
  private String departmentIDs;
  private int[] selectedSystemRole;
  public static final int INNER_USER = 0;
  public static final int OUTER_USER = 1;
  private int sourceType;
  private String sourceID;
  private String sourceName;

  public Role[] getRoles()
    throws Exception
  {
    ProfileManager profileManager = ProfileAdapter.getInstance();
    return profileManager.getUserRoles(this);
  }

  public Object clone()
  {
    User user = (User)super.clone();
    if (this.selectedSystemRole != null) {
      int[] newSelecteSystemRole = new int[this.selectedSystemRole.length];
      for (int i = 0; i < this.selectedSystemRole.length; i++) {
        newSelecteSystemRole[i] = this.selectedSystemRole[i];
      }
      user.setSelectedSystemRole(newSelecteSystemRole);
    }
    return user;
  }

  public String getFullName()
  {
    if ((this.lastName != null) && (this.lastName.length() > 0)) {
      return this.lastName;
    }
    return this.firstName;
  }

  public static String getUserName(int companyID, String ids)
  {
    if ((ids != null) && (!"".equals(ids))) {
      try {
        String[] ss = ids.split(",");
        String sRet = "";
        ProfileManager manager = ProfileAdapter.getInstance();
        for (int j = 0; j < ss.length; j++) {
          User u = manager.getUser(companyID, Integer.parseInt(ss[j]));
          sRet = sRet + u.getDisplayName();
          if (j != ss.length - 1) {
            sRet = sRet + ", ";
          }
        }
        return sRet;
      } catch (Exception e) {
        e.printStackTrace();
        return "";
      }
    }
    return "";
  }

  public String getResourceTeamName()
  {
    if (this.resourceTeamID == 0)
      return "";
    try {
      ProfileManager manager = ProfileAdapter.getInstance();
      Hierarchy rbsHierarchy = manager.getRbsHierarchy(this.companyID);
      Role resourceTeam = (Role)rbsHierarchy.getObject(this.resourceTeamID);
      return resourceTeam.getName();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public String getDepartmentName()
  {
    if (this.departmentID == 0)
      return "";
    try
    {
      ProfileManager manager = ProfileAdapter.getInstance();
      Hierarchy obsHierarchy = manager.getObsHierarchy(this.companyID);
      Role department = (Role)obsHierarchy.getObject(this.departmentID);
      return department.getName();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public Company getCompany()
  {
    try
    {
      ProfileManager manager = ProfileAdapter.getInstance();
      return manager.getCompany(getCompanyID());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getAllowLoginName(Locale locale)
  {
    if (getStatus() == 1) {
      return MessageUtils.getMessage(locale, "yes");
    }
    return MessageUtils.getMessage(locale, "no");
  }

  public boolean isResourceManager(int resourceTeamID)
    throws Exception
  {
    Role[] roles = getRoles();
    ProfileManager profileManager = ProfileAdapter.getInstance();
    Hierarchy rbsHierarchy = profileManager.getRbsHierarchy(this.companyID);
    Role currentRole = new Role();
    currentRole.setId(resourceTeamID);
    Hierarchyable[] parents = rbsHierarchy.getParents(currentRole);
    for (Role role : roles) {
      if (role.getSubType() == 20) {
        if (role.getDepartmentID() == resourceTeamID) {
          return true;
        }
        for (Hierarchyable parent : parents) {
          if (role.getDepartmentID() == parent.getId()) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean hasRole(int roleID)
    throws Exception
  {
    Role[] roles = getRoles();
    for (int i = 0; (roles != null) && (i < roles.length); i++) {
      if ((roles[i].getId() == roleID) || (roles[i].getReferenceID() == roleID)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasRole(String roleID)
  {
    try
    {
      Role[] roles = getRoles();
      ProfileManager profileManager = ProfileAdapter.getInstance();
      if ((roleID != null) && (!"".equals(roleID))) {
        String[] roleIDs = roleID.split(",");
        for (int j = 0; j < roleIDs.length; j++) {
          if ((roleIDs[j] != null) && (!"".equals(roleIDs[j]))) {
            Role r = profileManager.getRole(getCompanyID(), Integer.parseInt(roleIDs[j]));
            if ((r.getType() == 3) && (r.getProjectID() == 0)) {
              for (int i = 0; (roles != null) && (i < roles.length); i++) {
                if (roles[i].getReferenceID() == r.getId())
                  return true;
              }
            }
            else if ((r.getType() == 3) && (r.getProjectID() != 0)) {
              for (int i = 0; (roles != null) && (i < roles.length); i++) {
                if (roles[i].getId() == r.getId())
                  return true;
              }
            }
            else if (r.getType() == 10) {
              for (int i = 0; (roles != null) && (i < roles.length); i++) {
                if (roles[i].getType() == 10)
                  return true;
              }
            }
            else {
              for (int i = 0; (roles != null) && (i < roles.length); i++)
                if (roles[i].getId() == r.getId())
                  return true;
            }
          }
        }
      }
      else
      {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return false;
  }

  public boolean hasRole(int projectID, int roleID)
    throws Exception
  {
    Role[] roles = getRoles();
    for (int i = 0; (roles != null) && (i < roles.length); i++) {
      if ((roles[i].getProjectID() == projectID) && (roles[i].getReferenceID() == roleID)) {
        return true;
      }
    }
    return false;
  }

  public static User getUser(int userID, List users)
  {
    if ((users != null) && (users.size() > 0)) {
      for (int i = 0; i < users.size(); i++) {
        User user = (User)users.get(i);
        if (user.getId() == userID) {
          return user;
        }
      }
    }
    return null;
  }

  public String getProjectRoleName(int projectID)
    throws Exception
  {
    String sName = "";
    Role[] roles = getRoles();
    for (int j = 0; j < roles.length; j++) {
      if (projectID == roles[j].getProjectID()) {
        sName = sName + roles[j].getName() + ",";
      }
    }
    if (sName.length() > 0) {
      sName = sName.substring(0, sName.length() - 1);
    }
    return sName;
  }

  public String getRoleName(int roleID)
    throws Exception
  {
    String sName = "";
    Role[] roles = getRoles();
    for (int j = 0; j < roles.length; j++) {
      if (roleID == roles[j].getId()) {
        return roles[j].getName();
      }
    }
    return sName;
  }

  public Role getCompanyAdmin()
    throws Exception
  {
    Role[] roles = getRoles();
    for (int i = 0; i < roles.length; i++) {
      if (roles[i].isCompanyAdmin()) {
        return roles[i];
      }
    }
    return null;
  }

  public boolean isCompanyAdmin()
    throws Exception
  {
    return getCompanyAdmin() != null;
  }

  public boolean isOwner(int owner)
    throws Exception
  {
    Role[] roles = getRoles();
    if (roles != null) {
      for (int i = 0; i < roles.length; i++) {
        if (roles[i].getId() == owner) {
          return true;
        }
      }
    }
    return false;
  }

  public Role getSystemAdmin()
    throws Exception
  {
    Role[] roles = getRoles();
    if (roles != null) {
      for (int i = 0; i < roles.length; i++) {
        if (roles[i].isSystemAdmin()) {
          return roles[i];
        }
      }
    }
    return null;
  }

  public boolean isSystemAdmin()
    throws Exception
  {
    return getSystemAdmin() != null;
  }

  public boolean isPublicRole()
    throws Exception
  {
    Role[] roles = getRoles();
    for (int i = 0; i < roles.length; i++) {
      if (roles[i].isPublicRole()) {
        return true;
      }
    }
    return false;
  }

  public boolean checkPassword(String passwd)
  {
    if (this.password.equalsIgnoreCase(CipherUtils.toMD5(passwd))) {
      return true;
    }
    return false;
  }

  public String getProjectDefaultRoleName(int projectID)
    throws Exception
  {
    Role[] roles = getRoles();
    StringBuffer sb = new StringBuffer();
    for (Role role : roles) {
      if ((role.getProjectID() == projectID) && (role.getType() == 3)) {
        sb.append(role.getName());
        sb.append(",");
      }
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  public List<Role> getResourceTeamManagers()
    throws Exception
  {
    List returnList = new ArrayList();
    for (Role role : getRoles()) {
      if ((role.getType() == 2) && (role.getSubType() == 20)) {
        returnList.add(role);
      }
    }
    return returnList;
  }

  public boolean isResource()
  {
    return (this.resourceFlag == 1) && (this.resourceTeamID != 0);
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public int getType()
  {
    return this.type;
  }

  public void setType(int type)
  {
    this.type = type;
  }

  public int getStatus()
  {
    return this.status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getFirstName()
  {
    return this.firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return this.lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getDisplayName()
  {
    return this.displayName;
  }

  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }

  public int getResourceFlag()
  {
    return this.resourceFlag;
  }

  public void setResourceFlag(int resourceFlag)
  {
    this.resourceFlag = resourceFlag;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getFax()
  {
    return this.fax;
  }

  public void setFax(String fax)
  {
    this.fax = fax;
  }

  public String getMobile()
  {
    return this.mobile;
  }

  public void setMobile(String mobile)
  {
    this.mobile = mobile;
  }

  public String getTel()
  {
    return this.tel;
  }

  public void setTel(String tel)
  {
    this.tel = tel;
  }

  public String getZip()
  {
    return this.zip;
  }

  public void setZip(String zip)
  {
    this.zip = zip;
  }

  public String getJobNumber()
  {
    return this.jobNumber;
  }

  public void setJobNumber(String jobNumber)
  {
    this.jobNumber = jobNumber;
  }

  public int getSex()
  {
    return this.sex;
  }

  public void setSex(int sex)
  {
    this.sex = sex;
  }

  public int getLoginFailedTimes()
  {
    return this.loginFailedTimes;
  }

  public void setLoginFailedTimes(int loginFailedTimes)
  {
    this.loginFailedTimes = loginFailedTimes;
  }

  public Date getBirthday()
  {
    return this.birthday;
  }

  public void setBirthday(Date birthday)
  {
    this.birthday = birthday;
  }

  public Date getLastLoginTime()
  {
    return this.lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime)
  {
    this.lastLoginTime = lastLoginTime;
  }

  public Date getCurrentLoginTime()
  {
    return this.currentLoginTime;
  }

  public void setCurrentLoginTime(Date currentLoginTime)
  {
    this.currentLoginTime = currentLoginTime;
  }

  public Date getPasswordChangeTime()
  {
    return this.passwordChangeTime;
  }

  public void setPasswordChangeTime(Date passwordChangeTime)
  {
    this.passwordChangeTime = passwordChangeTime;
  }

  public int getDefaultRoleID()
  {
    return this.defaultRoleID;
  }

  public void setDefaultRoleID(int defaultRoleID)
  {
    this.defaultRoleID = defaultRoleID;
  }

  public int getLanguage()
  {
    return this.language;
  }

  public void setLanguage(int language)
  {
    this.language = language;
  }

  public int getAlertMethod()
  {
    return this.alertMethod;
  }

  public void setAlertMethod(int alertMethod)
  {
    this.alertMethod = alertMethod;
  }

  public int getLoginTimes()
  {
    return this.loginTimes;
  }

  public void setLoginTimes(int loginTimes)
  {
    this.loginTimes = loginTimes;
  }

  public String getFlag()
  {
    return this.flag;
  }

  public void setFlag(String flag)
  {
    this.flag = flag;
  }

  public int getResourceTeamID()
  {
    return this.resourceTeamID;
  }

  public void setResourceTeamID(int resourceTeamID)
  {
    this.resourceTeamID = resourceTeamID;
  }

  public String getResourceTeamIDs()
  {
    return this.resourceTeamIDs;
  }

  public void setResourceTeamIDs(String resourceTeamIDs)
  {
    this.resourceTeamIDs = resourceTeamIDs;
  }

  public int getDepartmentID()
  {
    return this.departmentID;
  }

  public void setDepartmentID(int departmentID)
  {
    this.departmentID = departmentID;
  }

  public String getDepartmentIDs()
  {
    return this.departmentIDs;
  }

  public void setDepartmentIDs(String departmentIDs)
  {
    this.departmentIDs = departmentIDs;
  }

  public int[] getSelectedSystemRole()
  {
    return this.selectedSystemRole;
  }

  public void setSelectedSystemRole(int[] selectedSystemRole)
  {
    this.selectedSystemRole = selectedSystemRole;
  }

  public int getSourceType()
  {
    return this.sourceType;
  }

  public void setSourceType(int sourceType)
  {
    this.sourceType = sourceType;
  }

  public String getSourceID()
  {
    return this.sourceID;
  }

  public void setSourceID(String sourceID)
  {
    this.sourceID = sourceID;
  }

  public String getSourceName()
  {
    return this.sourceName;
  }

  public void setSourceName(String sourceName)
  {
    this.sourceName = sourceName;
  }

  public long getSessionTime()
  {
    return this.sessionTime;
  }

  public void setSessionTime(long sessionTime)
  {
    this.sessionTime = sessionTime;
  }
}