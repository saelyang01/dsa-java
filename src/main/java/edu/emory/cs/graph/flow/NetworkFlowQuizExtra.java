package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;

import java.util.*;

/** @author Jinho D. Choi */
public class NetworkFlowQuizExtra {
    /**
     * Using breadth-first traverse.
     * @param graph  a directed graph.
     * @param source the ource vertex.
     * @param target the target vertex.
     * @return a set of all augmenting paths between the specific source and target vertices in the graph.
     */
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Set<Subgraph> results = new HashSet<>();

        List<Deque<Edge>> edges = graph.getOutgoingEdges();

        // Create a queue which stores
        // the paths
        Queue<List<Edge>> queue = new LinkedList<>();
        for (Edge e: edges.get(source)) {

            List<Edge> temp = new LinkedList<>();
            temp.add(e);
            queue.offer(temp);
        }



        while (!queue.isEmpty())
        {
            List<Edge> front = queue.poll();
            int last = front.get(front.size() - 1).getTarget();

            if (last == target) {
                Subgraph subgraph = new Subgraph();
                for (Edge e : front) {
                    subgraph.addEdge(e);
                }
                results.add(subgraph);
                continue;
            }

            // expand last
            for (Edge e: edges.get(last)) {
                boolean repeated = false;
                for (Edge prev: front) {
                    if (prev.getSource() == e.getTarget() || prev.getTarget() == e.getTarget()) {
                        repeated = true;
                        break;
                    }
                }
                if (repeated || e.getWeight() <= 0){
                    continue;
                }
                List<Edge> temp = new LinkedList<>(front);
                temp.add(e);
                queue.offer(temp);
            }
        }

        return results;
    }


}
