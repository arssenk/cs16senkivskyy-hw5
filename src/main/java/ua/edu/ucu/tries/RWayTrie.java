package ua.edu.ucu.tries;

import java.util.ArrayList;

public class RWayTrie implements Trie {
    Node root;

    public RWayTrie() {
        root = new Node();
    }

    private Integer addOverrided(String key, Integer value) {

        Node node = root.put(root, key, value, 0);
        if (node == null) return null;
        return node.getVal();
    }

    @Override
    public void add(Tuple t) {
        addOverrided(t.getTerm(), t.getWeight());
    }

    @Override
    public boolean contains(String word) {
        return checkContains(word) != null && checkContains(word) == word.length();
    }

    @Override
    public boolean delete(String word) {
        return checkContains(word)!= null && checkContains(word) == word.length() && addOverrided(word, 0) == 0;
    }

    @Override
    public Iterable<String> words() {
        ArrayList<String> wordsArray = new ArrayList<>();
        root.getWords(root, wordsArray, "");
        return wordsArray;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        ArrayList<String> wordsArray = new ArrayList<>();
        root.getWords(root.get(root, s, 0), wordsArray, s);
        return wordsArray;

    }

    @Override
    public int size() {
        return root.getSize(root);
    }
    private Integer checkContains (String  key){
        Node node = root.get(root, key, 0);
        if (node == null) {
            return null;
        }
        return node.getVal();
    }


}
