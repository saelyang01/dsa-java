package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;

import java.util.*;

/** @author Jinho D. Choi */
public class NetworkFlowQuiz {
    /**
     * Using depth-first traverse.
     * @param graph  a directed graph.
     * @param source the ource vertex.
     * @param target the target vertex.
     * @return a set of all augmenting paths between the specific source and target vertices in the graph.
     */
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Set<Subgraph> results = new HashSet<>();
        Deque<Edge> path = new LinkedList<>();
        Set<Integer> expanded = new HashSet<>();
        getAugmentingPaths(graph.getOutgoingEdges(), source, target, results, path, expanded);
        return results;
    }

    /**
     * recursively add paths to results
     * @param outgoingEdges the adjacent list of the graph
     * @param source the ource vertex.
     * @param target the target vertex.
     * @param results the list to store the subgraphs
     * @param path the list to current path
     * @param expanded visited status of the graph
     */
    private void getAugmentingPaths(List<Deque<Edge>> outgoingEdges, int source, int target, Set<Subgraph> results,
                                    Deque<Edge> path, Set<Integer> expanded) {
        if (source == target) {
            Subgraph subgraph = new Subgraph();
            for (Edge e : path) {
                subgraph.addEdge(e);
            }
            results.add(subgraph);
            return;
        }
        expanded.add(source);
        for (Edge e : outgoingEdges.get(source)) {
            int t = e.getTarget();
            if (expanded.contains(t) || e.getWeight() <= 0) {
                continue;
            }
            path.addLast(e);
            getAugmentingPaths(outgoingEdges, t, target, results, path, expanded);
            path.removeLast();
        }
        expanded.remove(source);
    }
}

