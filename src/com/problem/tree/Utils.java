package com.problem.tree;

public class Utils {

	public static String generateKey(TreeNode treeNode) {
		return treeNode.cannibalsOnShore0 + "-" + treeNode.missionariesOnShore0 + "-" + 
				treeNode.cannibalsOnShore1 + "-" + treeNode.missionariesOnShore1 + "-" + 
				treeNode.onWhichShoreTheBoatIs;
	}

	public static String generateKey(int nextcannibalsOnShore0,
			int nextmissionariesOnShore0, int nextcannibalsOnShore1,
			int nextmissionariesOnShore1, int onWhichShoreTheBoatIs) {
		
		return nextcannibalsOnShore0 + "-" + nextmissionariesOnShore0 + "-" + nextcannibalsOnShore1 + "-" +
		nextmissionariesOnShore1 + "-" + onWhichShoreTheBoatIs;
	}

}
