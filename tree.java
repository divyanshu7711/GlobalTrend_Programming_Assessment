import java.util.ArrayList;
import java.util.List;

public class tree {

    private static class IntervalNode {
        int start;
        int end;
        int maxEnd;
        IntervalNode left;
        IntervalNode right;

        public IntervalNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.maxEnd = end;
            this.left = null;
            this.right = null;
        }
    }

    private IntervalNode root;

    public tree() {
        root = null;
    }

    // Insert a new interval [start, end] into the tree
    public void insertInterval(int start, int end) {
        root = insertInterval(root, start, end);
    }

    private IntervalNode insertInterval(IntervalNode node, int start, int end) {
        if (node == null) {
            return new IntervalNode(start, end);
        }

        if (start <= node.start) {
            node.left = insertInterval(node.left, start, end);
        } else {
            node.right = insertInterval(node.right, start, end);
        }

        // Update maxEnd
        if (node.maxEnd < end) {
            node.maxEnd = end;
        }

        return node;
    }

    // Delete an interval [start, end] from the tree
    public void deleteInterval(int start, int end) {
        root = deleteInterval(root, start, end);
    }

    private IntervalNode deleteInterval(IntervalNode node, int start, int end) {
        if (node == null) {
            return null;
        }

        if (start < node.start) {
            node.left = deleteInterval(node.left, start, end);
        } else if (start > node.start) {
            node.right = deleteInterval(node.right, start, end);
        } else { // node.start == start
            if (end == node.end) {
                // Found the node to delete
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    IntervalNode minNode = findMin(node.right);
                    node.start = minNode.start;
                    node.end = minNode.end;
                    node.right = deleteInterval(node.right, minNode.start, minNode.end);
                }
            } else { // end < node.end
                node.left = deleteInterval(node.left, start, end);
            }
        }

        // Update maxEnd
        if (node != null) {
            node.maxEnd = Math.max(node.end, Math.max(getMaxEnd(node.left), getMaxEnd(node.right)));
        }

        return node;
    }

    private IntervalNode findMin(IntervalNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int getMaxEnd(IntervalNode node) {
        return (node == null) ? Integer.MIN_VALUE : node.maxEnd;
    }

    // Find all intervals that overlap with [start, end]
    public List<int[]> findOverlappingIntervals(int start, int end) {
        List<int[]> result = new ArrayList<>();
        findOverlappingIntervals(root, start, end, result);
        return result;
    }

    private void findOverlappingIntervals(IntervalNode node, int start, int end, List<int[]> result) {
        if (node == null) {
            return;
        }

        if (node.start <= end && node.end >= start) {
            result.add(new int[]{node.start, node.end});
        }

        if (node.left != null && node.left.maxEnd >= start) {
            findOverlappingIntervals(node.left, start, end, result);
        }

        if (node.right != null && node.right.start <= end) {
            findOverlappingIntervals(node.right, start, end, result);
        }
    }

    public static void main(String[] args) {
        tree intervalTree = new tree();
        intervalTree.insertInterval(15, 20);
        intervalTree.insertInterval(10, 30);
        intervalTree.insertInterval(5, 12);
        intervalTree.insertInterval(25, 35);
        intervalTree.insertInterval(2, 7);

        System.out.println("Intervals overlapping with [14, 16]:");
        List<int[]> result1 = intervalTree.findOverlappingIntervals(14, 16);
        for (int[] interval : result1) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }

        intervalTree.deleteInterval(5, 12);

        System.out.println("\nAfter deleting interval [5, 12], intervals overlapping with [14, 16]:");
        List<int[]> result2 = intervalTree.findOverlappingIntervals(14, 16);
        for (int[] interval : result2) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
