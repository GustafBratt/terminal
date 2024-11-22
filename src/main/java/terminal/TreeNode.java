package terminal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeNode {
    public String name;
    public List<TreeNode> children = new ArrayList<>();

    public TreeNode(String name, Collection<TreeNode> children) {
        this.name = name;
        this.children.addAll(children);
    }

    public void addChild(TreeNode node) {
        children.add(node);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
