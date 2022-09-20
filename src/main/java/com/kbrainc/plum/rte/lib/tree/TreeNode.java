package com.kbrainc.plum.rte.lib.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * TreeNode 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.lib.tree
 * - TreeNode.java
 * </pre> 
 *
 * @ClassName : TreeNode
 * @Description : TreeNode 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public class TreeNode<T> {

    /** 트리노드의 아이템. */
    private T data;
    /** 부모 트리노드. */
    private TreeNode<T> parent;
    /** 자식 트리노드 목록. */
    private List<TreeNode<T>> children;

    /**
     * Desc : Constructor of TreeNode.java class
     */
    public TreeNode() {
        super();
        children = new ArrayList<TreeNode<T>>();
    }

    /**
     * Desc : Constructor of TreeNode.java class
     * 
     * @param data 트리노드의 아이템
     */
    public TreeNode(T data) {
        this();
        setData(data);
    }

    /**
     * @Title : getData
     * @Description : 트리노드의 아이템 객체를 반환한다.
     * @return T 트리노드의 아이템
     */
    public T getData() {
        return this.data;
    }

    /**
     * @Title : setData
     * @Description : 트리노드에 아이템 객체를 셋팅한다.
     * @param data 트리노드의 아이템
     * @return void
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @Title : getParent
     * @Description : 부모 트리노드 객체를 반환한다.
     * @return TreeNode<T> 부모 트리노드 객체
     */
    public TreeNode<T> getParent() {
        return parent;
    }

    /**
     * @Title : setParent
     * @Description : 부모트리노드를 셋팅한다.
     * @param parent 부모트리 노드 객체
     * @return void
     */
    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    /**
     * @Title : getChildren
     * @Description : 자식 트리노드 목록을 반환한다.
     * @return List<TreeNode<T>> 자식 트리노드 목록
     */
    public List<TreeNode<T>> getChildren() {
        return this.children;
    }

    /**
     * @Title : getNumberOfChildren
     * @Description : 자식 트리노드의 갯수를 반환한다.
     * @return int 자식 트리노드의 갯수
     */
    public int getNumberOfChildren() {
        return getChildren().size();
    }

    /**
     * @Title : hasChildren
     * @Description : 자식 트리노드의 존재여부를 리턴한다.
     * @return boolean 자식 트리노드의 존재여부
     */
    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    /**
     * @Title : setChildren
     * @Description : 자식 트리노드 목록을 셋팅한다.
     * @param children 자식 트리노드 목록
     * @return void
     */
    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    /**
     * @Title : addChild
     * @Description : 자식 트리노드 목록에 자식노드를 추가한다.
     * @param child 자식노드
     * @return void
     */
    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    /**
     * @Title : addChildAt
     * @Description : 자식 트리노드 목록의 특정 위치에 자식노드를 추가한다.
     * @param index 위치값
     * @param child 자식노드
     * @throws IndexOutOfBoundsException
     * @return void
     */
    public void addChildAt(int index, TreeNode<T> child) throws IndexOutOfBoundsException {
        children.add(index, child);
    }

    /**
     * @Title : removeChildren
     * @Description : 자식 트리노드 목록을 제거한다.
     * @return void
     */
    public void removeChildren() {
        this.children = new ArrayList<TreeNode<T>>();
    }

    /**
     * @Title : removeChildAt
     * @Description : 자식 트리노드 목록에서 틀정 위치의 자식노드를 제거한다.
     * @param index 위치값
     * @throws IndexOutOfBoundsException
     * @return void
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    /**
     * @Title : getChildAt
     * @Description : 자식 트리노드 목록의 특정 위치의 자식노드를 리턴한다.
     * @param index 위치값
     * @throws IndexOutOfBoundsException
     * @return TreeNode<T> 트리노드
     */
    public TreeNode<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    /**
     * @Title : toString
     * @Description : 트리노드의 아이템의 toString()메서드를 호출한다.
     * @return String 트리노드의 아이템의 toString()호출 반환값
     */
    public String toString() {
        return getData().toString();
    }

    /**
     * @Title : isEqual
     * @Description : 트리노드의 아이템과 동일한 참조의 노드인지 확인한다.
     * @param node 비교대상 트리노드
     * @return boolean 동일 노드 여부
     */
    public boolean isEqual(TreeNode<T> node) {
        return node.getData().equals(getData());
    }

    /**
     * @Title : getHashCode
     * @Description : 트리노드의 아이템의 hashCode()메서드를 호출한다.
     * @return int 해쉬코드값
     */
    public int getHashCode() {
        return getData().hashCode();
    }
}