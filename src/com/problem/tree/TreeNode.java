package com.problem.tree;

import java.util.LinkedList;

public class TreeNode {
	public int missionariesOnShore0;
	public int missionariesOnShore1;
	public int cannibalsOnShore0;
	public int cannibalsOnShore1;
	// to know on which shore the boat is and can be 0 or 1
	public int onWhichShoreTheBoatIs;
	//to know whether that state is valid or not.
	public boolean isValidState;
	//the list of adjacent nodes.
	public LinkedList<TreeNode> adjacentNodes;
	// knowing the parent state
	public TreeNode parentNode;
	
	//default tree node
	public TreeNode()
	{
		missionariesOnShore0 = 3;
		missionariesOnShore1 = 0;
		cannibalsOnShore0 = 3;
		cannibalsOnShore1 = 0;
		adjacentNodes = new LinkedList<TreeNode>();
		onWhichShoreTheBoatIs = 0;
		isValidState = true;
		parentNode = null;
	}
	
	/**
	 * overridden tree node
	 * @param missionariesOnShore0
	 * @param cannibalsOnShore0
	 * @param missionariesOnShore1
	 * @param cannibalsOnShore1
	 * @param onWhichShoreTheBoatIs
	 * @param isValidState
	 */
	public TreeNode(int missionariesOnShore0, int cannibalsOnShore0, int missionariesOnShore1,
			int cannibalsOnShore1, int onWhichShoreTheBoatIs, boolean isValidState, TreeNode parentNode)
	{
		this.missionariesOnShore0 = missionariesOnShore0;
		this.missionariesOnShore1 = missionariesOnShore1;
		this.cannibalsOnShore0 = cannibalsOnShore0;
		this.cannibalsOnShore1 = cannibalsOnShore1;
		adjacentNodes = new LinkedList<TreeNode>();
		this.onWhichShoreTheBoatIs = onWhichShoreTheBoatIs;
		this.isValidState = isValidState;
		this.parentNode = parentNode;
	}

	public TreeNode(int maxMissionaries, int maxCannibals) {
		missionariesOnShore0 = maxMissionaries;
		missionariesOnShore1 = 0;
		cannibalsOnShore0 = maxCannibals;
		cannibalsOnShore1 = 0;
		adjacentNodes = new LinkedList<TreeNode>();
		onWhichShoreTheBoatIs = 0;
		isValidState = true;
		parentNode = null;
	}
	
}