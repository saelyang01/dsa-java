package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.set.DisjointSet;

import java.util.PriorityQueue;


/** @author Jinho D. Choi ({@code jinho.choi@emory.edu}) */
public class MSTKruskal implements MST {
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(graph.getAllEdges());
        DisjointSet forest = new DisjointSet(graph.size());
        SpanningTree tree = new SpanningTree();

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            if (!forest.inSameSet(edge.getTarget(), edge.getSource())) {
                tree.addEdge(edge);

                // a spanning tree is found
                if (tree.size() + 1 == graph.size()) break;
                // merge forests
                forest.union(edge.getTarget(), edge.getSource());
            }
        }

        return tree;
    }
}