package ua.edu.ucu.tries;

import java.util.ArrayList;

/**
 * Created by arsen on 08.01.17.
 */
class Node {
    private static int R = 26;
    private Integer val;
    private Node[] next = new Node[R];
    private int defaultLetterIndex = 97;

    Integer getVal() {
        return val;
    }

    Integer getSize(Node node) {
        int answer = 0;
        if (node.getVal() != null && node.getVal() > 0) {
            answer++;
        }
        int wordIndex = 0;
        while (wordIndex < R) {
            answer += getSize(node.next[wordIndex]);
        }
        return answer;
    }


    Node put(Node x, String key, int val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        if (x.next[c - defaultLetterIndex] == null) {
            x.next[c - defaultLetterIndex] = new Node();
        }
        x.next[c - defaultLetterIndex] = put(x.next[c - defaultLetterIndex], key, val, d + 1);
        return x;
    }

    Node get(Node node, String key, int depth) {
        if (node == null) return null;
        if (depth == key.length()) return node;
        char tmp = key.charAt(depth);
        return get(node.next[tmp - defaultLetterIndex], key, depth + 1);
    }

    void getWords(Node startNode, ArrayList<String> words, String tmp) {
        if (startNode != null) {
            if (startNode.getVal() != null && startNode.getVal() > 0) {
                words.add(tmp);
            }
            int wordIndex = 0;
            while (wordIndex < R) {
                getWords(startNode.next[wordIndex], words, tmp + (char) (wordIndex + defaultLetterIndex));
                wordIndex++;
            }
        }
    }


}
