/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        backtrack(root, targetSum, currentPath, result);
        return result;
    }

    private void backtrack(TreeNode node, long remaining, List<Integer> currentPath,
                            List<List<Integer>> result) {
        if (node == null) return;

        currentPath.add(node.val);
        remaining -= node.val;

        
        if (node.left == null && node.right == null) {
            if (remaining == 0) {
                result.add(new ArrayList<>(currentPath)); 
            }
        } else {
            backtrack(node.left, remaining, currentPath, result);
            backtrack(node.right, remaining, currentPath, result);
        }

       
        currentPath.remove(currentPath.size() - 1);
    }
}