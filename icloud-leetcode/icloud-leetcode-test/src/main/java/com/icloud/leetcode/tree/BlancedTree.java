package com.icloud.leetcode.tree;

import com.icloud.leetcode.Symmetric.TreeNode;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年11月7日 下午1:42:39
 */
public class BlancedTree {
	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		int maxDepath = 0;
		if (root.left != null) {
			int tmp = maxDepth(root.left);
			if (tmp > maxDepath)
				maxDepath = tmp;
		}
		if (root.right != null) {
			int tmp = maxDepth(root.right);
			if (tmp > maxDepath)
				maxDepath = tmp;
		}
		return maxDepath + 1;
	}

	public boolean isBalanced(TreeNode root) {
		HeightEntity entity = new HeightEntity();
		entity.height = 0;
		return isBalancedAndValue(root, entity);
	}

	public static class HeightEntity {
		public int height;
	}

	public boolean isBalancedAndValue(TreeNode root, HeightEntity entity) {
		if (root == null) {
			entity.height = 0;
			return true;
		}
		HeightEntity left = new HeightEntity();
		left.height = 0;
		HeightEntity right = new HeightEntity();
		right.height = 0;

		if (isBalancedAndValue(root.left, left)
				&& isBalancedAndValue(root.right, right)) {
			int s = left.height - right.height;
			if (s <= 1 && s >= -1) {
				entity.height = 1 + ((left.height > right.height) ? left.height
						: right.height);
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(2);
		node.right = new TreeNode(3);
		node.right.right = new TreeNode(4);
		BlancedTree tree = new BlancedTree();
		boolean flag = tree.isBalanced(node);
		System.out.println("flag = " + flag);
	}
}
