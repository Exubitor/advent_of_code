package exubitor.adventofcode.dayfour;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CampCleanup {
    private final List<String> inputAsStrings;
    private final List<Integer[]> firstPartOfPairs;
    private final List<Integer[]> secondPartOfPairs;

    public CampCleanup() {
        inputAsStrings = MyFileInput.parseInputToStringList(new File("src/exubitor/adventofcode/dayfour/resource/input.txt"));
        firstPartOfPairs = new ArrayList<>();
        secondPartOfPairs = new ArrayList<>();
        parsePairs();
    }

    private void parsePairs() {
        for (String s : inputAsStrings) {
            String[] parts = s.split(",");
            addTaskToPartOfPair(parts[0], firstPartOfPairs);
            addTaskToPartOfPair(parts[1], secondPartOfPairs);
        }
    }

    private void addTaskToPartOfPair(String elfTask, List<Integer[]> partOfPairs) {
        String[] range = elfTask.split("-");
        Integer[] intArray = new Integer[2];
        intArray[0] = Integer.parseInt(range[0]);
        intArray[1] = Integer.parseInt(range[1]);
        partOfPairs.add(intArray);
    }

    private boolean determineOneTaskCoversTheOtherEntirely(Integer[] firstPartOfPair, Integer[] secondPartOfPair) {
        Set<Integer> set = new HashSet<>();
        if (firstPartCoversMoreRange(firstPartOfPair, secondPartOfPair)) {
            //adding ints of larger task group
            for (int i = firstPartOfPair[0]; i <= firstPartOfPair[1]; i++) {
                set.add(i);
            }
            for (int i = secondPartOfPair[0]; i <= secondPartOfPair[1]; i++) {
                if (set.add(i)) { //means the second elf has at least one int that isn't covered by the first one
                    return false;
                }
            }
            return true;
        } else {
            //adding ints of larger task group
            for (int i = secondPartOfPair[0]; i <= secondPartOfPair[1]; i++) {
                set.add(i);
            }
            for (int i = firstPartOfPair[0]; i <= firstPartOfPair[1]; i++) {
                if (set.add(i)) { //means the first elf has at least one int that isn't covered by the second one
                    return false;
                }
            }
            return true;
        }
    }

    private boolean determineOneTaskOverlapsWithTheOther(Integer[] firstPartOfPair, Integer[] secondPartOfPair) {
        Set<Integer> set = new HashSet<>();
        for (int i = firstPartOfPair[0]; i <= firstPartOfPair[1]; i++) {
            set.add(i);
        }
        for (int i = secondPartOfPair[0]; i <= secondPartOfPair[1]; i++) {
            if (!set.add(i)) { //means the second elf has at least one int that overlaps with the first one
                return true;
            }
        }
        return false;
    }

    private boolean firstPartCoversMoreRange(Integer[] firstPartOfPair, Integer[] secondPartOfPair) {
        List<Integer> rangeOfFirstPart = new ArrayList<>();
        List<Integer> rangeOfSecondPart = new ArrayList<>();
        for (int i = firstPartOfPair[0]; i <= firstPartOfPair[1]; i++) {
            rangeOfFirstPart.add(i);
        }
        for (int i = secondPartOfPair[0]; i <= secondPartOfPair[1]; i++) {
            rangeOfSecondPart.add(i);
        }
        return rangeOfFirstPart.size() > rangeOfSecondPart.size();
    }

    public int getAmountOfRedundantElfs() {
        int amountOfRedundantElfs = 0;
        for (int i = 0; i < inputAsStrings.size(); i++) {
            if (determineOneTaskCoversTheOtherEntirely(firstPartOfPairs.get(i), secondPartOfPairs.get(i))) {
                amountOfRedundantElfs++;
            }
        }

        return amountOfRedundantElfs;
    }

    public int getAmountOfTaskOverlaps() {
        int amountOfTaskOverlaps = 0;
        for (int i = 0; i < inputAsStrings.size(); i++) {
            if (determineOneTaskOverlapsWithTheOther(firstPartOfPairs.get(i), secondPartOfPairs.get(i))) {
                amountOfTaskOverlaps++;
            }
        }

        return amountOfTaskOverlaps;
    }

    public static void main(String[] args) {
        CampCleanup cc = new CampCleanup();
        System.out.println(cc.getAmountOfRedundantElfs());
        System.out.println(cc.getAmountOfTaskOverlaps());
    }
}