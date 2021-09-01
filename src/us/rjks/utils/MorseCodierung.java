package us.rjks.utils;

import java.util.StringTokenizer;

/**
 * Copyright Ⓒ Robert J. Kratz 2021
 * Created: 01.09.2021 / 17:13
 * Contact: https://link.rjks.us/support
 */

public class MorseCodierung {

    private BinaryTree<Character> morsebaum;

    public MorseCodierung() {
        this.morsebaum = new BinaryTree<Character>(' ');

        input('E',".");
        input('T',"-");

        input('A',".-");
        input('I',"-.");
        input('M',"--");
        input('N',"..");

        input('S',"...");
        input('O',"---");
        input('D',"-..");
        input('G',"--.");
        input('R',".-.");
        input('U',"..-");
        input('K',"-.-");
        input('W',".--");

        input('B',"-...");
        input('C',"-.-.");
        input('F',"..-.");
        input('H',"....");
        input('J',".---");
        input('L',".-..");
        input('P',".--.");
        input('Q',"--..");
        input('V',"...-");
        input('X',"-..-");
        input('Y',"-.--");
        input('Z',"--.-");
    }

    /**
     * Initialising morse tree
     * */
    private void input(char zeichen, String code) {
        BinaryTree<Character> current = this.morsebaum;
        while (code.length() > 0 ) {
            if  (code.charAt(0) == '.') {
                if (current.getLeftTree() == null)
                    current.setLeftTree( new BinaryTree<Character>());
                current = current.getLeftTree();
            } else {
                if (current.getRightTree() == null)
                    current.setRightTree( new BinaryTree<Character>());
                current = current.getRightTree();
            }
            code = code.substring(1);
        }
        current.setContent( zeichen );
    }

    /**
     * "..." -> 'S'
     * */
    public Character decodeChar(String code){
        BinaryTree<Character> current = this.morsebaum;
        while (code.length() > 0 && current != null) {
            if(code.charAt(0) == '.') current = current.getLeftTree();
            if(code.charAt(0) == '-') current = current.getRightTree();
            code = code.substring(1);
        }
        if (current != null) return current.getContent();
        else return new Character(' ');
    }

    /**
     * 'A' -> "._"  // inverse search, empty path = ""
     * */
    public String encodeChar(char input) {
        return search(this.morsebaum,"", input);
    }

    private String search(BinaryTree<Character> tree, String name, char input) {
        if(tree.isEmpty()) return "";
        if(tree.getContent().equals(input)) return name;

        return search(tree.getRightTree(), name + "-", input) +
                search(tree.getLeftTree(), name + ".", input);
    }

    public String encodeString(String text) {
        text = text.toUpperCase();
        String enter = "";
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) != ' ') {
                if (enter.isEmpty()) {
                    enter = encodeChar(text.charAt(i));
                } else {
                    enter = enter + " " + encodeChar(text.charAt(i));
                }
            } else {
                enter = enter + " ";
            }
        }
        return enter;
    }

    public String decodeString(String text) {

        StringTokenizer tokenizer = new StringTokenizer(text, " ");
        String element = "";
        while (tokenizer.hasMoreElements()) {
            element = element + decodeChar(tokenizer.nextToken());
        }
        return element;
    }
}