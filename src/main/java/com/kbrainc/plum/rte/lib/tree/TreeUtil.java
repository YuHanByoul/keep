package com.kbrainc.plum.rte.lib.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * TreeUtil 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.lib.tree
 * - TreeUtil.java
 * </pre> 
 *
 * @ClassName : TreeUtil
 * @Description : TreeUtil 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class TreeUtil {

    /**
     * .
     *
     * @Title       : reformatTreeList 
     * @Description : TODO
     * @param list
     * @return List<TreeItem>
     */
    public static List<TreeItem> reformatTreeList(List<TreeItem> list) {

        if (list.size() <= 1) {
            return list;
        }

        for (TreeItem item : list) {
            if (!TreeItem.ROOT_ID.equals(item.getKey())) {
                TreeItem parent = getParent(list, item);
                if (parent != null) {
                    parent.getChildren().add(item);
                    parent.setFolder(true);
                }
            }
        }
        List<TreeItem> item = new ArrayList<TreeItem>();
        item.add(list.get(0));
        return item;
    }

    private static TreeItem getParent(List<TreeItem> list, TreeItem item) {
        if (TreeItem.ROOT_ID.equals(item.getKey())) {
            return null;
        }

        for (TreeItem i : list) {
            if (i.getKey().equals(item.getPkey())) {
                return i;
            }
        }

        return null;
    }
}