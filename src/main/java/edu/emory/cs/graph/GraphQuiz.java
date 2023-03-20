package edu.emory.cs.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** @author Jinho D. Choi */
public class GraphQuiz extends Graph {
    public GraphQuiz(int size) {
        super(size);
    }

    public GraphQuiz(Graph g) {
        super(g);
    }

    /**
     * Find the number of cycleNums in the graph
     * @return the cycle number
     */
    public int numberOfCycles() {
        int[] cycleNums = new int[1];
        cycleNums[0] = 0;

        List<Integer> foundList = new ArrayList<>();
        List<Deque<Edge>> outgoingEdges = getOutgoingEdges();
        for (int i = 0; i < size(); i++) {
            for (Deque<Edge> vertexQueue: outgoingEdges) {
                for (Edge edge : vertexQueue) {
                    if (edge.getSource() < i || edge.getTarget() < i) {
                        vertexQueue.remove(edge);
                    }
                }
            }
            cycleDFS(i, i, outgoingEdges, foundList, cycleNums);
        }
        return cycleNums[0];
    }

    /**
     * DFS traverse the graph for cycle
     * @param headVertex the begin vertex node
     * @param currVertex the current vertex node
     * @param outgoingEdges the out going edge list
     * @param foundList the list of vertex found right nows
     */
    private void cycleDFS(int headVertex, int currVertex,
                          List<Deque<Edge>> outgoingEdges,
                          List<Integer> foundList, int[] cycleNums){

        foundList.add(currVertex);
        Deque<Edge> edgeQueue = outgoingEdges.get(currVertex);
        for (Edge edge: edgeQueue) {
            if (edge.getTarget() == headVertex) {
                cycleNums[0] += 1;
            }
            else if (!foundList.contains(edge.getTarget())) {
                cycleDFS(headVertex, edge.getTarget(), outgoingEdges, foundList, cycleNums);
            }
        }
        foundList.remove(foundList.size() - 1);
    }
}