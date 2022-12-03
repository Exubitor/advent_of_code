package exubitor.adventofcode.dayone;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalorieCounting {
    private final List<String> inputAsStrings;
    private final List<List<Integer>> elfInventories;
    private final List<Integer> elfInventoriesCalorieSums;

    public CalorieCounting() {
        File input = new File("src/exubitor/adventofcode/dayone/resource/input.txt");
        inputAsStrings = MyFileInput.parseInputToStringList(input);
        elfInventories = new ArrayList<>();
        parseElfInventories();
        elfInventoriesCalorieSums = new ArrayList<>();
        addSumOfCaloriesForEachElfInventory();
        Collections.sort(elfInventoriesCalorieSums);
    }

    private void parseElfInventories() {
        List<Integer> currentElfInventory = new ArrayList<>();
        for (String s : inputAsStrings) {
            if (s.equals("")) {
                elfInventories.add(currentElfInventory);
                currentElfInventory = new ArrayList<>();
            } else {
                currentElfInventory.add(Integer.parseInt(s));
            }
        }
        elfInventories.add(currentElfInventory); //adds the last elfInventory to the list
    }

    private void addSumOfCaloriesForEachElfInventory() {
        for (List<Integer> elfInventory : elfInventories) {
            int sumOfCaloriesInInventory = 0;
            for (int calorieCount : elfInventory) {
                sumOfCaloriesInInventory += calorieCount;
            }
            elfInventoriesCalorieSums.add(sumOfCaloriesInInventory);
        }
    }

    public int getHighestCalorieCount() {
        //being sorted in the constructor
        return elfInventoriesCalorieSums.get(elfInventoriesCalorieSums.size() - 1);
    }

    public int getSumOfHighest3CalorieCounts() {
        int highestCalorieCount = getHighestCalorieCount();
        int secondHighestCalorieCount = elfInventoriesCalorieSums.get(elfInventoriesCalorieSums.size() - 2);
        int thirdHighestCalorieCount = elfInventoriesCalorieSums.get(elfInventoriesCalorieSums.size() - 3);
        return highestCalorieCount + secondHighestCalorieCount + thirdHighestCalorieCount;
    }

    public static void main(String[] args) {
        CalorieCounting cc = new CalorieCounting();
        System.out.println(cc.getHighestCalorieCount());
        System.out.println(cc.getSumOfHighest3CalorieCounts());
    }
}
