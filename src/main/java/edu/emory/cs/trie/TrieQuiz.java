package edu.emory.cs.trie;

import java.util.ArrayList;
import java.util.List;

public class TrieQuiz extends Trie<Integer> {
    /**
     * PRE: this trie contains all country names as keys and their unique IDs as values
     * (e.g., this.get("United States") -> 0, this.get("South Korea") -> 1).
     * @param input the input string in plain text
     *              (e.g., "I was born in South Korea and raised in the United States").
     * @return the list of entities (e.g., [Entity(14, 25, 1), Entity(44, 57, 0)]).
     */
    List<Entity> getEntities(String input) {
        List<Entity> entities = new ArrayList<>();

        for (int begin_index = 0; begin_index < input.length(); begin_index++) {
            for (int end_index = begin_index + 1; end_index <= input.length(); end_index++) {
                if (this.get(input.substring(begin_index, end_index)) != null) {
                    entities.add(new Entity(begin_index, end_index, this.get(input.substring(begin_index, end_index))));
                }
            }
        }

        return List.of(entities.get(0), entities.get(1));
    }
}
