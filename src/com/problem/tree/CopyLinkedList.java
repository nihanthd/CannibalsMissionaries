package com.problem.tree;

import java.util.HashMap;
import java.util.LinkedList;

public class CopyLinkedList {
	/**
	 * This method copies elements of a source linked list into destination linked list
	 * @param queueHashMap 
	 */
	public static void copyLinkedListForGraphConstruction(LinkedList<TreeNode> destinationList,
			LinkedList<TreeNode> sourceList, HashMap<String, TreeNode> queueHashMap)
	{
		String key = null;
		for (TreeNode tempNode : sourceList) {
			key = Utils.generateKey(tempNode);
			if(tempNode.isValidState && !queueHashMap.containsKey(key))
			{
				destinationList.add(tempNode);
				queueHashMap.put(key, tempNode);
			}
			System.out.print(tempNode.cannibalsOnShore0 + " - " + tempNode.missionariesOnShore0 + " - " + tempNode.cannibalsOnShore1 + " - " + tempNode.missionariesOnShore1 + " - " + tempNode.onWhichShoreTheBoatIs +"    |||   ");
		}
		System.out.println("");
	}

	public static void copyLinkedListForGraphTraversal(
			LinkedList<TreeNode> destinationList, LinkedList<TreeNode> sourceList,
			HashMap<String, TreeNode> queueHashMap) {
		String key = null;
		boolean nodesAdded = false;
		for (TreeNode tempNode : sourceList) {
			key = Utils.generateKey(tempNode);
			if(tempNode.isValidState && !queueHashMap.containsKey(key))
			{
				destinationList.add(tempNode);
				queueHashMap.put(key, tempNode);
				nodesAdded = true;
				System.out.print(tempNode.cannibalsOnShore0 + " - " + tempNode.missionariesOnShore0 + " - " + tempNode.cannibalsOnShore1 + " - " + tempNode.missionariesOnShore1 + " - " + tempNode.onWhichShoreTheBoatIs +"    |||   ");
			}
		}
		if(nodesAdded)
		{
			System.out.println("");
		}
	}
}
