package com.kbrainc.plum.rte.lib.tree;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * TreeItem 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.lib.tree
 * - TreeItem.java
 * </pre> 
 *
 * @ClassName : TreeItem
 * @Description : TreeItem 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Getter
@Setter
public class TreeItem {
    
    /** 트리의 루트노드의 key값. */
    public final static String ROOT_ID = "0";
    /** key값. */
    private String key = null;
    /** 타이틀. */
    private String title = null;
    /** 부모key값. */
    private String pkey = null;
    /** 툴팁. */
    private String tooltip = null;
    /** 여분파라미터1. */
    private String ext1 = null;
    /** 여분파라미터2. */
    private String ext2 = null;
    /** 여분파라미터3. */
    private String ext3 = null;
    /** 여분파라미터4. */
    private String ext4 = null;
    /** 여분파라미터5. */
    private String ext5 = null;
    /** 여분파라미터6. */
    private String ext6 = null;
    /** 여분파라미터7. */
    private String ext7 = null;
    /** 여분파라미터8. */
    private String ext8 = null;
    /** 여분파라미터9. */
    private String ext9 = null;
    /** 여분파라미터10. */
    private String ext10 = null;
    
    /** 여분파라미터10. */
    private String use_yn = null;
    
    private String menuicon = "";
    private boolean select = false;
    private boolean isFolder = false;
    
    private List<TreeItem> children;


    /**
    * Desc : Constructor of TreeItem.java class
    */
    public TreeItem() {
        this.children = new ArrayList<TreeItem>();
    }
    
    /**
     * .
     * Desc : Constructor of TreeItem.java class
     * @param pkey :
     * @param key :
     * @param title :
     */
    public TreeItem(String pkey, String key, String title) {
        this();
        this.pkey = pkey;
        this.key = key;
        this.title = title;
    }
    
    
    public boolean isFolder() {
        return isFolder;
    }

    /** .
    * @Title        : setExt1 
    * @Description    : ext1값을 셋팅한다.
    * @param ext1 ext1값
    */ 
    public void setExt1(String ext1) {
        this.ext1 = ext1;
        if ("Y".equals(ext1)) {
            this.select = true;
        }
    }

    /** .
    * @Title        : setExt2 
    * @Description    : ext2값을 셋팅한다.
    * @param ext2 ext2값
    */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
        this.menuicon = ext2;
    }

}