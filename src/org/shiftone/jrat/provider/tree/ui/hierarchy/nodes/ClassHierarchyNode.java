package org.shiftone.jrat.provider.tree.ui.hierarchy.nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jeff@shiftone.org (Jeff Drost)
 */
public class ClassHierarchyNode extends HierarchyNode {

    private final List childMethods = new ArrayList();

    private int enteredMethods;
    private int existedMethods;
    private long totalDurationMs;

    public ClassHierarchyNode(String name) {
        super(name);
    }

    public void finalizeStatistics() {
        for (Iterator i = childMethods.iterator(); i.hasNext();) {
            MethodHierarchyNode child = (MethodHierarchyNode) i.next();
            totalDurationMs += child.getTotalDurationMs();

            if (child.getTotalMethods() > 0) {
                enteredMethods++;
                if (child.getTotalExits() > 0) {
                    existedMethods++;
                }
            }
        }
    }

    public void addMethod(MethodHierarchyNode methodNode) {
        childMethods.add(methodNode);
    }

    public List getChildren() {
        return childMethods;
    }

    public int getTotalMethods() {
        return childMethods.size();
    }

    public int getEnteredMethods() {
        return enteredMethods;
    }

    public int getExistedMethods() {
        return existedMethods;
    }

    public long getTotalDurationMs() {
        return totalDurationMs;
    }
}