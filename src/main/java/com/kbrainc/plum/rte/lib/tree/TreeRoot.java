package com.kbrainc.plum.rte.lib.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * TreeRoot 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.lib.tree
 * - TreeRoot.java
 * </pre> 
 *
 * @ClassName : TreeRoot
 * @Description : TreeRoot 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public class TreeRoot<T> {

    /** root트리노드. */
    private TreeNode<T> root;

    /**
     * Desc : Constructor of TreeRoot.java class
     */
    public TreeRoot() {
        super();
    }

    /**
     * @Title : getRoot
     * @Description : root트리노드 리턴
     * @return TreeNode<T> 트리노드
     */
    public TreeNode<T> getRoot() {
        return this.root;
    }

    /**
     * @Title : setRoot
     * @Description : root트리노드를 셋팅한다.
     * @param root root트리노드
     * @return void
     */
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * @Title : toString
     * @Description : toString()메서드를 재정의 하여 트리노드 목록의 객체 정보 문자열을 반환한다.
     * @return String 객체 내용 출력 문자열
     */
    public String toString() {
        String stringRepresentation = "";
        if (root != null) {
            stringRepresentation = ToStringBuilder.reflectionToString(toList(), ToStringStyle.DEFAULT_STYLE);
        }
        return stringRepresentation;
    }

    /**
     * @Title : toList
     * @Description : 트리노드 목록을 리턴한다.
     * @return List<TreeNode<T>> 트리노드 목록
     */
    public List<TreeNode<T>> toList() {
        List<TreeNode<T>> list = new ArrayList<TreeNode<T>>();
        visit(root, list);
        return list;
    }

    /**
     * @Title : visit
     * @Description : 트리노드 목록을 생성한다.
     * @param element 트리노드
     * @param list    트리노드 목록
     * @return void
     */
    private void visit(TreeNode<T> element, List<TreeNode<T>> list) {
        if (element != null) {
            list.add(element);
            for (TreeNode<T> data : element.getChildren()) {
                visit(data, list);
            }
        }
    }
}