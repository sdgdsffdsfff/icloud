/*     */ package com.easytrack.commons.hierarchy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Hierarchy
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6714923846602940014L;
/*  15 */   private Comparator<Hierarchyable> comparator = null;
/*     */ 
/*  19 */   private boolean sort = false;
/*     */ 
/*  24 */   private Composite rootNode = null;
/*     */ 
/*  26 */   private HashMap<Integer, Composite> items = new HashMap();
/*     */ 
/*     */   public Hierarchy() {
/*     */   }
/*     */ 
/*     */   public Hierarchy(boolean sort) {
/*  32 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   public Hierarchy(Comparator comparator)
/*     */   {
/*  42 */     this.comparator = comparator;
/*     */   }
/*     */ 
/*     */   public Hierarchy(Comparator<Hierarchyable> comparator, boolean sort)
/*     */   {
/*  52 */     this.comparator = comparator;
/*  53 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   public boolean isSort()
/*     */   {
/*  62 */     return this.sort;
/*     */   }
/*     */ 
/*     */   public void setSort(boolean sort)
/*     */   {
/*  72 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   public Comparator<Hierarchyable> getComparator()
/*     */   {
/*  81 */     return this.comparator;
/*     */   }
/*     */ 
/*     */   public void setComparator(Comparator<Hierarchyable> comparator)
/*     */   {
/*  91 */     this.comparator = comparator;
/*     */   }
/*     */ 
/*     */   public Hierarchyable getRootNode()
/*     */   {
/* 100 */     if (this.rootNode != null) {
/* 101 */       return this.rootNode.getNode();
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   public void setRootNode(Hierarchyable rootNode)
/*     */   {
/* 113 */     this.rootNode = new Composite(rootNode);
/* 114 */     this.items.put(Integer.valueOf(rootNode.getId()), this.rootNode);
/*     */   }
/*     */ 
/*     */   public synchronized void addObject(Hierarchyable object)
/*     */   {
/* 124 */     addNode(object);
/*     */   }
/*     */ 
/*     */   public synchronized void addObject(Hierarchyable[] objectArray)
/*     */   {
/* 134 */     for (Hierarchyable object : objectArray)
/* 135 */       addNode(object);
/*     */   }
/*     */ 
/*     */   public synchronized void addObject(List<Hierarchyable> objectArray)
/*     */   {
/* 146 */     for (Hierarchyable object : objectArray)
/* 147 */       addNode(object);
/*     */   }
/*     */ 
/*     */   private void addNode(Hierarchyable object)
/*     */   {
/* 152 */     Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 153 */     if (item == null)
/* 154 */       item = new Composite(object);
/*     */     else {
/* 156 */       item.setNode(object);
/*     */     }
/* 158 */     this.items.put(Integer.valueOf(object.getId()), item);
/* 159 */     Composite parent = (Composite)this.items.get(Integer.valueOf(object.getParentID()));
/* 160 */     if ((parent == null) && (object.getParentID() != this.rootNode.getId())) {
/* 161 */       parent = new Composite(object);
/*     */     }
/* 163 */     if (parent != null) {
/* 164 */       if (!parent.getChilds().contains(item)) {
/* 165 */         parent.getChilds().add(item);
/*     */       }
/* 167 */       this.items.put(Integer.valueOf(object.getParentID()), parent);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized boolean move(Hierarchyable movedNode, Hierarchyable toNode)
/*     */   {
/* 181 */     boolean result = false;
/* 182 */     if (this.rootNode != null) {
/* 183 */       Composite movedObj = (Composite)this.items.get(Integer.valueOf(movedNode.getId()));
/* 184 */       Composite toObj = (Composite)this.items.get(Integer.valueOf(toNode.getId()));
/* 185 */       if ((movedObj != null) && (toObj != null)) {
/* 186 */         movedObj.setParentID(toNode.getId());
/* 187 */         toObj.getChilds().add(movedObj);
/* 188 */         Composite parent = (Composite)this.items.get(Integer.valueOf(movedObj.getParentID()));
/* 189 */         if (parent != null) {
/* 190 */           parent.getChilds().remove(movedObj);
/*     */         }
/* 192 */         result = true;
/*     */       }
/*     */     }
/* 195 */     return result;
/*     */   }
/*     */ 
/*     */   public synchronized void removeObject(Hierarchyable object)
/*     */   {
/* 205 */     if (this.rootNode != null) {
/* 206 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 207 */       Composite parent = (Composite)this.items.get(Integer.valueOf(object.getParentID()));
/* 208 */       if (parent != null) {
/* 209 */         parent.getChilds().remove(item);
/*     */       }
/* 211 */       this.items.remove(Integer.valueOf(object.getId()));
/*     */     }
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public Hierarchyable[] getAllHierarchy()
/*     */   {
/* 223 */     List list = getAllTreeNodes();
/* 224 */     Hierarchyable[] resultArry = new Hierarchyable[list.size()];
/* 225 */     list.toArray(resultArry);
/* 226 */     return resultArry;
/*     */   }
/*     */ 
/*     */   public List<Hierarchyable> getAllTreeNodes()
/*     */   {
/* 236 */     ArrayList list = new ArrayList();
/* 237 */     if (this.rootNode != null) {
/* 238 */       list.add(this.rootNode.getNode());
/* 239 */       list.addAll(getTreeNodes());
/*     */     }
/* 241 */     return list;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public Hierarchyable[] getHierarchy()
/*     */   {
/* 252 */     List list = getTreeNodes();
/* 253 */     Hierarchyable[] resultArry = new Hierarchyable[list.size()];
/* 254 */     list.toArray(resultArry);
/* 255 */     return resultArry;
/*     */   }
/*     */ 
/*     */   public List<Hierarchyable> getTreeNodes()
/*     */   {
/* 265 */     if (this.rootNode != null) {
/* 266 */       return getAllChildren(this.rootNode.getNode());
/*     */     }
/* 268 */     return null;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public Hierarchyable[] getChilds(Hierarchyable object)
/*     */   {
/* 281 */     List list = getAllChildren(object);
/* 282 */     Hierarchyable[] resultArry = new Hierarchyable[list.size()];
/* 283 */     list.toArray(resultArry);
/* 284 */     return resultArry;
/*     */   }
/*     */ 
/*     */   public List<Hierarchyable> getAllChildren(Hierarchyable object)
/*     */   {
/* 295 */     ArrayList list = new ArrayList();
/* 296 */     if (this.rootNode != null) {
/* 297 */       getChilds(list, object);
/*     */     }
/* 299 */     return list;
/*     */   }
/*     */ 
/*     */   private void getChilds(List<Hierarchyable> list, Hierarchyable item) {
/* 303 */     List childs = getDirectChildren(item);
/* 304 */     for (Hierarchyable child : childs) {
/* 305 */       list.add(child);
/* 306 */       if (getChildCount(child) > 0)
/* 307 */         getChilds(list, child);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public Hierarchyable[] getDirectChilds(Hierarchyable object)
/*     */   {
/* 322 */     List list = getDirectChildren(object);
/* 323 */     Hierarchyable[] resultArry = new Hierarchyable[list.size()];
/* 324 */     list.toArray(resultArry);
/* 325 */     return resultArry;
/*     */   }
/*     */ 
/*     */   public List<Hierarchyable> getDirectChildren(Hierarchyable object)
/*     */   {
/* 337 */     ArrayList list = new ArrayList();
/* 338 */     if (this.rootNode != null) {
/* 339 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 340 */       if (item != null) {
/* 341 */         List childs = item.getChilds();
/* 342 */         for (Composite child : childs) {
/* 343 */           list.add(child.getNode());
/*     */         }
/* 345 */         if (this.comparator != null) {
/* 346 */           Collections.sort(list, this.comparator);
/*     */         }
/*     */       }
/*     */     }
/* 350 */     return list;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public Hierarchyable[] getDirectChilds(Hierarchyable object, Comparator comparator)
/*     */   {
/* 365 */     List list = getDirectChildren(object, comparator);
/* 366 */     Hierarchyable[] resultArry = new Hierarchyable[list.size()];
/* 367 */     list.toArray(resultArry);
/* 368 */     return resultArry;
/*     */   }
/*     */ 
/*     */   public List<Hierarchyable> getDirectChildren(Hierarchyable object, Comparator<Hierarchyable> comparator)
/*     */   {
/* 381 */     ArrayList list = new ArrayList();
/* 382 */     if (this.rootNode != null) {
/* 383 */       Composite item = null;
/* 384 */       item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 385 */       if (item != null) {
/* 386 */         List childs = item.getChilds();
/* 387 */         for (Composite child : childs) {
/* 388 */           list.add(child.getNode());
/*     */         }
/* 390 */         if (comparator != null) {
/* 391 */           Collections.sort(list, comparator);
/*     */         }
/*     */       }
/*     */     }
/* 395 */     return list;
/*     */   }
/*     */ 
/*     */   public Hierarchyable getLastChild(Hierarchyable object)
/*     */   {
/* 406 */     List children = getAllChildren(object);
/* 407 */     if (children.size() == 0) {
/* 408 */       return object;
/*     */     }
/* 410 */     return (Hierarchyable)children.get(children.size() - 1);
/*     */   }
/*     */ 
/*     */   public int getChildCount(Hierarchyable object)
/*     */   {
/* 421 */     int result = 0;
/* 422 */     if (this.rootNode != null) {
/* 423 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 424 */       if (item != null) {
/* 425 */         result = item.getChildCount();
/*     */       }
/*     */     }
/* 428 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean isFirstChild(Hierarchyable object)
/*     */   {
/* 439 */     if (this.rootNode != null) {
/* 440 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 441 */       if (item != null) {
/* 442 */         Composite parent = (Composite)this.items.get(Integer.valueOf(item.getParentID()));
/* 443 */         if ((parent != null) && 
/* 444 */           (parent.getChildCount() > 0)) {
/* 445 */           Composite child = (Composite)parent.getChilds().get(0);
/* 446 */           if ((child != null) && (child.getId() == item.getId())) {
/* 447 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 453 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isLastChild(Hierarchyable object)
/*     */   {
/* 464 */     if (this.rootNode != null) {
/* 465 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 466 */       if (item != null) {
/* 467 */         Composite parent = (Composite)this.items.get(Integer.valueOf(item.getParentID()));
/* 468 */         if ((parent != null) && 
/* 469 */           (parent.getChildCount() > 0)) {
/* 470 */           Composite child = (Composite)parent.getChilds().get(parent.getChildCount() - 1);
/* 471 */           if ((child != null) && (child.getId() == item.getId())) {
/* 472 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 478 */     return false;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public Hierarchyable[] getParents(Hierarchyable object)
/*     */   {
/* 490 */     List list = getAllParent(object);
/* 491 */     Hierarchyable[] resultArry = new Hierarchyable[list.size()];
/* 492 */     list.toArray(resultArry);
/* 493 */     return resultArry;
/*     */   }
/*     */ 
/*     */   public List<Hierarchyable> getAllParent(Hierarchyable object)
/*     */   {
/* 505 */     ArrayList list = new ArrayList();
/* 506 */     if (this.rootNode != null) {
/* 507 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 508 */       if (item != null) {
/* 509 */         Composite parent = (Composite)this.items.get(Integer.valueOf(item.getParentID()));
/* 510 */         while (parent != null) {
/* 511 */           list.add(parent.getNode());
/* 512 */           if (parent.getId() == this.rootNode.getId()) {
/*     */             break;
/*     */           }
/* 515 */           parent = (Composite)this.items.get(Integer.valueOf(parent.getParentID()));
/*     */         }
/*     */       }
/*     */     }
/* 519 */     return list;
/*     */   }
/*     */ 
/*     */   public Hierarchyable getParents(Hierarchyable object, int level)
/*     */   {
/* 532 */     Hierarchyable node = null;
/* 533 */     int levels = 0;
/* 534 */     if (level == 0) {
/* 535 */       return object;
/*     */     }
/* 537 */     if (this.rootNode != null) {
/* 538 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 539 */       if (item != null) {
/* 540 */         Composite parent = (Composite)this.items.get(Integer.valueOf(item.getParentID()));
/* 541 */         while (parent != null) {
/* 542 */           levels++;
/* 543 */           if (levels == level) {
/* 544 */             return parent.getNode();
/*     */           }
/* 546 */           if (parent.getId() == this.rootNode.getId()) {
/*     */             break;
/*     */           }
/* 549 */           parent = (Composite)this.items.get(Integer.valueOf(parent.getParentID()));
/*     */         }
/*     */       }
/*     */     }
/* 553 */     return node;
/*     */   }
/*     */ 
/*     */   public int getLevel(Hierarchyable object)
/*     */   {
/* 564 */     int result = 0;
/* 565 */     if (this.rootNode != null) {
/* 566 */       Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 567 */       if (item != null) {
/* 568 */         Composite parent = (Composite)this.items.get(Integer.valueOf(item.getParentID()));
/* 569 */         while ((parent != null) && 
/* 570 */           (parent.getId() != this.rootNode.getId()))
/*     */         {
/* 573 */           result++;
/* 574 */           parent = (Composite)this.items.get(Integer.valueOf(parent.getParentID()));
/*     */         }
/*     */       }
/*     */     }
/* 578 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean contains(Hierarchyable object)
/*     */   {
/* 589 */     boolean result = false;
/* 590 */     Composite item = (Composite)this.items.get(Integer.valueOf(object.getId()));
/* 591 */     if (item != null) {
/* 592 */       result = true;
/*     */     }
/* 594 */     return result;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 601 */     this.rootNode = null;
/* 602 */     this.items.clear();
/*     */   }
/*     */ 
/*     */   public Hierarchyable getObject(Hierarchyable object)
/*     */   {
/* 613 */     return getObject(object.getId());
/*     */   }
/*     */ 
/*     */   public Hierarchyable getObject(int id)
/*     */   {
/* 624 */     Hierarchyable result = null;
/* 625 */     Composite item = (Composite)this.items.get(Integer.valueOf(id));
/* 626 */     if (item != null) {
/* 627 */       result = item.getNode();
/*     */     }
/* 629 */     return result;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.hierarchy.Hierarchy
 * JD-Core Version:    0.6.2
 */