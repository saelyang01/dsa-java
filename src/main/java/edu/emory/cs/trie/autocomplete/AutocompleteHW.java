package edu.emory.cs.trie.autocomplete;

import edu.emory.cs.trie.TrieNode;

import java.util.*;



public class AutocompleteHW extends Autocomplete<List<String>> {
    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
    }

    @Override
    public List<String> getCandidates(String prefix) {
        TrieNode<List<String>> node = find(prefix);
        if(node == null) {
            return new ArrayList<>();
        }

        List<String> selected = node.getValue();
        List<String> candidates = new ArrayList<>();
        getAllChildren(prefix, candidates);
        List<String> result = new ArrayList<>();
        if(selected != null) {
            while(result.size() < getMax() && selected.size() > 0) {
                result.add(selected.remove(0));
            }
        }
        if(result.size() == getMax()) {
            return result;
        }
        candidates.sort((o1, o2) -> {
            if (o1.length()!=o2.length()) {
                return o1.length()-o2.length();
            }
            return o1.compareTo(o2);
        });
        while(result.size() < getMax() && candidates.size() > 0) {
            String el = candidates.remove(0);
            if(!result.contains(el)) {
                result.add(el);
            }
        }
        return result;
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        TrieNode<List<String>> node = find(prefix);
        if(node == null) {
            return;
        }
        List<String> selected = node.getValue();
        if(selected == null) {
            selected = new ArrayList<>();
        }
        selected.add(0, candidate);
        node.setValue(selected);
    }

    /**
     * get all candidates starts with the same prefix
     * @param prefix
     * @param candidates
     */
    private void getAllChildren(String prefix, List<String> candidates) {
        TrieNode<List<String>> node = find(prefix);
        if(node == null) {
            return;
        }
        if(node.isEndState()) {
            candidates.add(prefix);
        }
        if(node.hasChildren()) {
            Map<Character, TrieNode<List<String>>> nodes= node.getChildrenMap();
            for(Character c : nodes.keySet()) {
                getAllChildren(prefix + c, candidates);
            }
        }
    }
}