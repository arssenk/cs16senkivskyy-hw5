package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author andrii
 */
class PrefixMatches {

    private Trie trie;

    PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    int load(String... strings) {
        int loadsize = 0;
        for (String item : strings) {
            for (String item_1 : item.split(" ")) {
                if (item_1.length() > 2) {
                    trie.add(new Tuple(item_1, item_1.length()));
                    loadsize++;
                }
            }
        }
        return loadsize;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);

    }

    Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) {
            return null;
        }
        Iterable<String> words = trie.wordsWithPrefix(pref);
        ArrayList<String> words_1 = (ArrayList<String>) words;
        Collections.sort(words_1, new MyComparator(words.iterator().next()));

        int len = words_1.get(0).length();
        ArrayList<String> answer = new ArrayList<>();
        int i = 0;
        for (String word : words) {
            if (word.length() != len) {
                i++;
                len = word.length();
            }
            if (i < k) {
                answer.add(word);
            } else {
                return answer;
            }


        }
        return answer;
    }

    public int size() {
        return trie.size();
    }
}

class MyComparator implements java.util.Comparator<String> {

    private int referenceLength;

    MyComparator(String reference) {
        super();
        this.referenceLength = reference.length();
    }

    public int compare(String s1, String s2) {
        int dist1 = Math.abs(s1.length() - referenceLength);
        int dist2 = Math.abs(s2.length() - referenceLength);

        return dist1 - dist2;
    }
}