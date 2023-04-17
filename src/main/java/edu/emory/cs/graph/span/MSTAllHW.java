package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jinho D. Choi
 */
public class MSTAllHW implements MSTAll {
    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph) {

        // find a MST by kruskal method, and find the weight of MST
        SpanningTree minTree = new MSTKruskal().getMinimumSpanningTree(graph);
        double minWeight = minTree.getTotalWeight();

        // get all spanning tree of the graph
        SpanningTree tree = new SpanningTree();
        List<SpanningTree> trees = new ArrayList<>();
        Set<Integer> visited = new HashSet<>(0);
        int start = 0;
        visited.add(start);

        if (tree.size() + 1 == graph.size()) {
            trees.add(tree);
        }
        getAllTree(graph, tree, trees, visited);

        // return all spanning tree has the same weight with MST(that means these trees are all MST)
        return trees.stream().filter(t -> t.getTotalWeight() == minWeight).collect(Collectors.toList());
    }

    /**
     * recursive function to get all spanning tree of a graph (brute force manner)
     *
     * @param g       the graph
     * @param tree    not yet fully generated spanning tree, used to generate more trees
     * @param trees   all spanning tree found
     * @param visited the set of visited vertex
     */
    private void getAllTree(Graph g, SpanningTree tree, List<SpanningTree> trees, Set<Integer> visited) {

        // get all edges point to vertex in visited
        List<Edge> edges = new ArrayList<>();
        for (int v : visited) {
            edges.addAll(g.getIncomingEdges(v));
        }

        // for each edge, if the edge's source is not visited, generate a new tree by adding the edge to old tree
        // if the new tree is a spanning tree, and if there is no logic same tree in trees, add it to list
        // or create a new visited set by adding edge's source to old set, and calling getAllTree use new tree and new set
        for (Edge edge : edges) {
            if (!visited.contains(edge.getSource())) {
                SpanningTree newTree = new SpanningTree(tree);
                newTree.addEdge(edge);
                // find a spanning tree, add it to trees
                if (newTree.size() + 1 == g.size()) {
                    Optional<SpanningTree> optionalSpanningTree = trees.stream().filter(t -> isSameTree(t, newTree)).findAny();
                    if (optionalSpanningTree.isEmpty()) {
                        trees.add(newTree);
                    }
                    continue;
                }
                Set<Integer> newVisited = new HashSet<>(visited);
                newVisited.add(edge.getSource());
                // if the tree is not a spanning tree, continue adding vertex
                getAllTree(g, newTree, trees, newVisited);
            }
        }
    }


    /**
     * check if two SpanningTree are logic same
     *
     * @param t1 tree one
     * @param t2 tree two
     * @return true if two tress are logic  same
     */
    private boolean isSameTree(SpanningTree t1, SpanningTree t2) {
        // first check two trees have the same weight
        if (t1.getTotalWeight() != t2.getTotalWeight()) {
            return false;
        }

        // then check two trees have the same number of edges
        if (t1.getEdges().size() != t2.getEdges().size()) {
            return false;
        }

        // finally, check if two trees has the same set of edges
        for (Edge e : t1.getEdges()) {
            boolean hasSameEdge = false;
            for (Edge e2 : t2.getEdges()) {
                if (e2.getWeight() == e.getWeight()) {
                    if (e2.getSource() == e.getSource() && e2.getTarget() == e.getTarget()) {
                        hasSameEdge = true;
                        break;
                    }

                    if (e2.getSource() == e.getTarget() && e2.getTarget() == e.getSource()) {
                        hasSameEdge = true;
                        break;
                    }
                }
            }

            if (!hasSameEdge) {
                return false;
            }
        }

        return true;
    }

}
