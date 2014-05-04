package com.problem.solution;

import java.util.HashMap;
import java.util.LinkedList;

import com.problem.tree.CopyLinkedList;
import com.problem.tree.TreeNode;
import com.problem.tree.Utils;



public class CannibalsMissionaries {

	public static HashMap<String, TreeNode> statesMap = new HashMap<String, TreeNode>(); 
	public static int maxMissionaries = 3;
	public static int maxCannibals= 3;
	
	public static void main(String[] args) {
		//code for constructing the tree dynamically.
		LinkedList<TreeNode> queueOfStatesToBeProcessed = new LinkedList<TreeNode>();
		//for maintaining unique elements in the queue.
		HashMap<String, TreeNode> hashMapForStatesProcessed = new HashMap<String, TreeNode>(); 
		
		TreeNode root = new TreeNode(maxMissionaries, maxCannibals);
		
		queueOfStatesToBeProcessed.add(root);
		String key = Utils.generateKey(root);
		hashMapForStatesProcessed.put(key, root);
		statesMap.put(key, root);
		System.out.println("States while search space construction : ");
		for(;!queueOfStatesToBeProcessed.isEmpty();)
		{
			TreeNode nodeToBeProcessed = queueOfStatesToBeProcessed.get(0);
			LinkedList<TreeNode> adjacentNodes = null;
			
			if(nodeToBeProcessed.onWhichShoreTheBoatIs == 0)
			{
				adjacentNodes = processFromShore0toShore1(nodeToBeProcessed);
			}
			else if(nodeToBeProcessed.onWhichShoreTheBoatIs == 1)
			{
				adjacentNodes = processFromShore1toShore0(nodeToBeProcessed);
			}
			
			if(adjacentNodes != null && adjacentNodes.size() > 0)
			{
				CopyLinkedList.copyLinkedListForGraphConstruction(queueOfStatesToBeProcessed, adjacentNodes, hashMapForStatesProcessed);
				nodeToBeProcessed.adjacentNodes = adjacentNodes;
			}
			queueOfStatesToBeProcessed.remove(0);
		}
		System.out.println("States while search space traversal using BFS");
		breadthFirstSearchOfSearchSpace(root);
	}
	
	/**
	 * This method does a breadth first search of the search space generated to find the solution
	 * @param root
	 */
	private static void breadthFirstSearchOfSearchSpace(TreeNode root) {
		
		HashMap<String, TreeNode> hashMapForStatesProcessed = new HashMap<String, TreeNode>(); 
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		String key = Utils.generateKey(root);
		hashMapForStatesProcessed.put(key, root);
		TreeNode nodeUnderProcess = null;
		boolean isSolutionPresent = false;
		//code for breadth first search of search space
		while(!queue.isEmpty())
		{
			nodeUnderProcess = queue.get(0);
			if(isSolutionNode(nodeUnderProcess))
			{
				isSolutionPresent = true;
				break;
			}
			if(nodeUnderProcess.adjacentNodes != null)
			{
				CopyLinkedList.copyLinkedListForGraphTraversal(queue, nodeUnderProcess.adjacentNodes, hashMapForStatesProcessed);
			}
			queue.remove(0);
		}
		if(isSolutionPresent)
		{
			System.out.println("Cannibals and Missionaries reached the other shore.");
		}
		else
		{
			System.out.println("Cannibals and Missionaries didnt reach the other shore.");
		}
	}

	/**
	 * This method returns true if the given node is the goal node or else false
	 * @param tempNode
	 * @return
	 */
	private static boolean isSolutionNode(TreeNode tempNode) {
		if(tempNode.onWhichShoreTheBoatIs == 1 && 
				tempNode.cannibalsOnShore0 == 0 && tempNode.cannibalsOnShore1 == maxCannibals
				&& tempNode.missionariesOnShore0 == 0 && tempNode.missionariesOnShore1 == maxMissionaries)
		{
			return true;
		}
		return false;
	}

	/**
	 * Finding possible combinations for which the boat can carry
	 * cannibals or missionaries from shore 0 to shore 1
	 * @param node
	 * @return
	 */
	public static LinkedList<TreeNode> processFromShore0toShore1(TreeNode node)
	{
		//to know what would be the next state and decide whether it is a valid state or not.
		int nextmissionariesOnShore0 = 0;
		int nextmissionariesOnShore1 = 0;
		int nextcannibalsOnShore0 = 0;
		int nextcannibalsOnShore1 = 0;
		//these can even include invalid states
		LinkedList<TreeNode> nextPossibleStates = new LinkedList<TreeNode>();
		String key = null;
		//decreasing two cannibals from shore 0 i.e. sending two cannibals from shore 0 to shore 1
		if(node.cannibalsOnShore0 >= 2)
		{
			nextcannibalsOnShore0 = node.cannibalsOnShore0 - 2;
			nextcannibalsOnShore1 = node.cannibalsOnShore1 + 2;
			nextmissionariesOnShore0 = node.missionariesOnShore0;
			nextmissionariesOnShore1 = node.missionariesOnShore1;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 1);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 1, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}

		// decreasing a cannibal and a missionary from shore 0
		if(node.cannibalsOnShore0 >= 1 && node.missionariesOnShore0 >= 1)
		{
			nextcannibalsOnShore0 = node.cannibalsOnShore0 - 1;
			nextcannibalsOnShore1 = node.cannibalsOnShore1 + 1;
			nextmissionariesOnShore0 = node.missionariesOnShore0 - 1;
			nextmissionariesOnShore1 = node.missionariesOnShore1 + 1;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 1);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 1, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}
		
		//decreasing two missionaries from shore 0
		if(node.missionariesOnShore0 >= 2)
		{
			nextcannibalsOnShore0 = node.cannibalsOnShore0;
			nextcannibalsOnShore1 = node.cannibalsOnShore1;
			nextmissionariesOnShore0 = node.missionariesOnShore0 - 2;
			nextmissionariesOnShore1 = node.missionariesOnShore1 + 2;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 1);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 1, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}
		return nextPossibleStates;
	}
	

	/**
	 * Finding possible combinations for which the boat can carry
	 * cannibals or missionaries from shore 1 to shore 0
	 * @param node
	 * @return
	 */
	public static LinkedList<TreeNode> processFromShore1toShore0(TreeNode node)
	{
		if(node.cannibalsOnShore1 == maxCannibals && node.missionariesOnShore1 == maxMissionaries)
		{
			return new LinkedList<TreeNode>();
		}
		//to know what would be the next state and decide whether it is a valid state or not.
		String key = null;
		int nextmissionariesOnShore0 = 0;
		int nextmissionariesOnShore1 = 0;
		int nextcannibalsOnShore0 = 0;
		int nextcannibalsOnShore1 = 0;
		//these can even include invalid states
		LinkedList<TreeNode> nextPossibleStates = new LinkedList<TreeNode>();
		
		//decreasing two cannibals from shore 1 i.e. sending two cannibals from shore 1 to shore 0
		if(node.cannibalsOnShore1 >= 2)
		{
			nextcannibalsOnShore1 = node.cannibalsOnShore1 - 2;
			nextcannibalsOnShore0 = node.cannibalsOnShore0 + 2;
			nextmissionariesOnShore1 = node.missionariesOnShore1;
			nextmissionariesOnShore0 = node.missionariesOnShore0;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 0);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 0, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}
		
		// decreasing a cannibal and a missionary from shore 1
		if(node.cannibalsOnShore1 >= 1 && node.missionariesOnShore1 >= 1)
		{
			nextcannibalsOnShore1 = node.cannibalsOnShore1 - 1;
			nextcannibalsOnShore0 = node.cannibalsOnShore0 + 1;
			nextmissionariesOnShore1 = node.missionariesOnShore1 - 1;
			nextmissionariesOnShore0 = node.missionariesOnShore0 + 1;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 0);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 0, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}
		
		//decreasing two missionaries from shore 1
		if(node.missionariesOnShore1 >= 2)
		{
			nextcannibalsOnShore1 = node.cannibalsOnShore1;
			nextcannibalsOnShore0 = node.cannibalsOnShore0;
			nextmissionariesOnShore1 = node.missionariesOnShore1 - 2;
			nextmissionariesOnShore0 = node.missionariesOnShore0 + 2;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 0);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 0, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}
		
		// decreasing one cannibal from shore 1
		if(node.cannibalsOnShore1 >= 1)
		{
			nextcannibalsOnShore1 = node.cannibalsOnShore1 - 1;
			nextcannibalsOnShore0 = node.cannibalsOnShore0 + 1;
			nextmissionariesOnShore1 = node.missionariesOnShore1;
			nextmissionariesOnShore0 = node.missionariesOnShore0;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 0);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 0, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}
		
		//decreasing one missionary from shore 1
		if(node.missionariesOnShore1 >= 1)
		{
			nextcannibalsOnShore1 = node.cannibalsOnShore1;
			nextcannibalsOnShore0 = node.cannibalsOnShore0;
			nextmissionariesOnShore1 = node.missionariesOnShore1 - 1;
			nextmissionariesOnShore0 = node.missionariesOnShore0 + 1;
			key = Utils.generateKey(nextcannibalsOnShore0, nextmissionariesOnShore0, nextcannibalsOnShore1, nextmissionariesOnShore1, 0);
			boolean isValidState = isNextStateValid(nextmissionariesOnShore0, nextcannibalsOnShore0,nextmissionariesOnShore1, nextcannibalsOnShore1, node.parentNode);
			if(isValidState && statesMap.containsKey(key))
			{
				nextPossibleStates.add(statesMap.get(key));
			}
			else
			{
				generateState(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
						nextcannibalsOnShore1, 0, isValidState, node, statesMap, nextPossibleStates, key);
			}
		}
		return nextPossibleStates;
	}
	
	/**
	 * This method generates the next state and adds it to the statesMap
	 * @param nextmissionariesOnShore0
	 * @param nextcannibalsOnShore0
	 * @param nextmissionariesOnShore1
	 * @param nextcannibalsOnShore1
	 * @param onWhichShoreTheBoatWillBe
	 * @param isValidState
	 * @param node
	 * @param statesMap2
	 * @param nextPossibleStates
	 * @param key 
	 */
	private static void generateState(int nextmissionariesOnShore0,
			int nextcannibalsOnShore0, int nextmissionariesOnShore1,
			int nextcannibalsOnShore1, int onWhichShoreTheBoatWillBe, boolean isValidState,
			TreeNode node, HashMap<String, TreeNode> statesMap2, LinkedList<TreeNode> nextPossibleStates, String key) {
		TreeNode nextNode = new TreeNode(nextmissionariesOnShore0, nextcannibalsOnShore0, nextmissionariesOnShore1,
				nextcannibalsOnShore1, onWhichShoreTheBoatWillBe, isValidState, node);
		nextPossibleStates.add(nextNode);
		statesMap.put(key, nextNode);
	}

	/**
	 * This method returns whether the next possible state is valid or not
	 * @param nextmissionariesOnShore0
	 * @param nextcannibalsOnShore0
	 * @param nextmissionariesOnShore1
	 * @param nextcannibalsOnShore1
	 * @param parentNode
	 * @return
	 */
	public static boolean isNextStateValid(int nextmissionariesOnShore0, 
			int nextcannibalsOnShore0, int nextmissionariesOnShore1, 
			int nextcannibalsOnShore1, TreeNode parentNode)
	{
		//condition for making sure that this particular state is not returning back to its parent state.
		if(parentNode!= null && parentNode.missionariesOnShore0 == nextmissionariesOnShore0 && 
				parentNode.missionariesOnShore1 == nextmissionariesOnShore1 &&
				parentNode.cannibalsOnShore0 == nextcannibalsOnShore0 && 
				parentNode.cannibalsOnShore1 == nextcannibalsOnShore1)
		{
			return false;
		}
		//except for the start state there shouldn't be 3 cannibals and 3 missionaries on shore 0
		if(nextmissionariesOnShore0 == maxMissionaries && nextcannibalsOnShore0  == maxCannibals)
		{
			return false;
		}
		//if missionaries and cannibals are returning back to the shore 0.
		if(nextmissionariesOnShore1 == 0 && nextcannibalsOnShore1  == 0)
		{
			return false;
		}
		//if more than expected cannibals i.e. 3 are there on any shore
		if(nextmissionariesOnShore0 > maxMissionaries || nextcannibalsOnShore0  > maxCannibals 
				|| nextmissionariesOnShore1  > maxMissionaries || nextcannibalsOnShore1  > maxCannibals)
		{
			return false;
		}
		//condition for making sure that cannibals don't out number missionaries on shore 0
		if(nextmissionariesOnShore0 > 0 && nextcannibalsOnShore0 > nextmissionariesOnShore0)
		{
			return false;
		}
		//condition for making sure that cannibals don't out number missionaries on shore 1
		if(nextmissionariesOnShore1 > 0 && nextcannibalsOnShore1 > nextmissionariesOnShore1)
		{
			return false;
		}
		return true;
	}
}
