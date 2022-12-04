package exubitor.adventofcode.daythree;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RucksackReorganization {
    private final List<String> rucksacks;
    //because 'A' is 65 and is worth 1 point
    private final static int ASCII_SHIFT_BY_64_CHARS = 64;
    private final static int AMOUNT_OF_LETTERS_IN_THE_ENGLISH_ALPHABET = 26;
    private final List<List<String>> groups;

    public RucksackReorganization() {
        rucksacks = MyFileInput.parseInputToStringList(new File("src/exubitor/adventofcode/daythree/resource/input.txt"));
        groups = new ArrayList<>();
        parseRucksacksInGroupsOfThree();
    }

    private void parseRucksacksInGroupsOfThree() {
        List<String> currentGroup = new ArrayList<>();
        for (int i = 0; i < rucksacks.size(); i += 3) {
            for (int j = 0; j < 3; j++) {
                currentGroup.add(rucksacks.get(i + j));
            }
            groups.add(currentGroup);
            currentGroup = new ArrayList<>();
        }
    }

    private char getCharThatIsInAllThreeBackpacks(String bp1, String bp2, String bp3) {
        for (int i = 0; i < bp1.length(); i++) {
            for (int j = 0; j < bp2.length(); j++) {
                for (int k = 0; k < bp3.length(); k++) {
                    if (bp1.charAt(i) == bp2.charAt(j) && bp1.charAt(i) == bp3.charAt(k)) {
                        return bp1.charAt(i);
                    }
                }
            }
        }
        throw new RuntimeException("No common character in all three backpacks");
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

    public int getTotalSumOfPrioritiesPartTwo() {
        int totalSumOfPriorities = 0;
        for (List<String> group : groups) {
            totalSumOfPriorities += getPriorityForChar(getCharThatIsInAllThreeBackpacks(group.get(0), group.get(1), group.get(2)));
        }
        return totalSumOfPriorities;
    }

    public static void main(String[] args) {
        RucksackReorganization rr = new RucksackReorganization();
        System.out.println(rr.getTotalSumOfPriorities());
        System.out.println(rr.getTotalSumOfPrioritiesPartTwo());
    }
}
