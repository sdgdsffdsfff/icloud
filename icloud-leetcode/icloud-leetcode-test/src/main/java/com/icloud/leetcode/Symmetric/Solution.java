package com.icloud.leetcode.Symmetric;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月22日 下午5:35:34
 */
// confused what "{1,#,2,3}" means? > read more on how binary tree is serialized
// on OJ.
public class Solution {
	public boolean compara(TreeNode leftNode, TreeNode rightNode) {
		if (leftNode == null && rightNode == null)
			return true;
		if (leftNode == null || rightNode == null)
			return false;
		if (leftNode.val == rightNode.val) {
			if (compara(leftNode.left, rightNode.right)
					&& compara(leftNode.right, rightNode.left)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return compara(root.left, root.right);
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val == q.val) {
			if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		// solution.isSymmetric(null);
		solution.isSameTree(null, null);
	}
}
