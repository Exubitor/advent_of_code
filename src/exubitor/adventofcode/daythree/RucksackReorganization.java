package exubitor.adventofcode.daythree;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RucksackReorganization {
    private final List<String> rucksacks;
    //because 'A' is 65 and is worth 1 point
    private final static int ASCII_SHIFT_BY_64_CHARS = 64;
    private final static int AMOUNT_OF_LETTERS_IN_THE_ENGLISH_ALPHABET = 26;

    public RucksackReorganization() {
        rucksacks = MyFileInput.parseInputToStringList(new File("src/exubitor/adventofcode/daythree/resource/input.txt"));
    }

    private char getCharThatIsInBothCompartments(String compartment1, String compartment2) {
        for (int i = 0; i < compartment1.length(); i++) {
            for (int j = 0; j < compartment2.length(); j++) {
                if (compartment1.charAt(i) == compartment2.charAt(j)) {
                    return compartment1.charAt(i);
                }
            }
        }
        throw new RuntimeException("No common character in both compartments");
    }

    private int getPriorityForChar(char c) {
        if (Character.isUpperCase(c)) {
            return c - ASCII_SHIFT_BY_64_CHARS + AMOUNT_OF_LETTERS_IN_THE_ENGLISH_ALPHABET;
        } else {
            return Character.toUpperCase(c) - ASCII_SHIFT_BY_64_CHARS;
        }
    }

    public int getTotalSumOfPriorities() {
        int totalSumOfPriorities = 0;
        for (String s : rucksacks) {
            totalSumOfPriorities += getPriorityForChar(getCharThatIsInBothCompartments(s.substring(0, s.length() / 2), s.substring(s.length() / 2)));
        }
        return totalSumOfPriorities;
    }

    public static void main(String[] args) {
        RucksackReorganization rr = new RucksackReorganization();
        System.out.println(rr.getTotalSumOfPriorities());
    }
}
