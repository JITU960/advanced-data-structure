package com.snapdeal.ims.entity;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        int length = word.length();
        TrieNode parent = root;
        TrieNode tn = root;
        int i = 0;
        for (; i < length; i++) {
            parent = tn;
            tn = tn.getChild(word.charAt(i));
            if(tn == null) {
                break;
            }
            if(i == length - 1) {
                tn.setEndOfWord(true);
            }
        }
        while (i < length) {
            if(i == length - 1) {
                parent = parent.addChild(word.charAt(i++), true);
            } else {
                parent = parent.addChild(word.charAt(i++), false);
            }
        }
    }

    public boolean searchPrefix(String word) {
        return search(word, false);
    }

    public boolean searchWord(String word) {
        return search(word, true);
    }

    private boolean search(String word, boolean searchWholeWord) {
        int length = word.length();
        TrieNode tn = root;
        for (int i = 0; i < length; i++) {
            tn = tn.getChild(word.charAt(i));
            if(tn == null) {
                return false;
            }
            if (searchWholeWord && i == length - 1 && tn.isEndOfWord() == false) {
                return false;
            }
        }
        return true;
    }
}

@Data
class TrieNode {
    private boolean isEndOfWord;
    private Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

    public TrieNode getChild(Character ch) {
        return children.get(ch);
    }

    public TrieNode addChild(Character ch, boolean isEndOfWord) {
        TrieNode tn = new TrieNode();
        tn.setEndOfWord(isEndOfWord);
        children.put(ch, tn);
        return tn;
    }
}


/*Complexity*/

