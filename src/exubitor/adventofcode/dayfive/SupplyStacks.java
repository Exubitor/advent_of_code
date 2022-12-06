package exubitor.adventofcode.dayfive;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class SupplyStacks {

    private final List<String> inputAsString;
    private final List<Stack<Character>> stacksList;
    private final static int AMOUNT_OF_STACKS = 9; //hardcoded
    private final List<Integer[]> craneOrders;

    public SupplyStacks() {
        inputAsString = MyFileInput.parseInputToStringList(new File("src/exubitor/adventofcode/dayfive/resource/input.txt"));
        stacksList = new ArrayList<>();
        parseStacksFromInputString();
        craneOrders = new ArrayList<>();
        parseCraneOrdersFromInputString();
        doAllTheCraneOrdersMultipleCratesAtATime();
    }

    private void parseCraneOrdersFromInputString() {
        for (int i = getHighestStackAmount() + 2; i < inputAsString.size(); i++) { //+2 because orders start 2 lines below in the input file
            String[] currentLineSplit = inputAsString.get(i).split(" ");
            Integer[] craneOrderParsed = new Integer[3];
            craneOrderParsed[0] = Integer.parseInt(currentLineSplit[1]);
            craneOrderParsed[1] = Integer.parseInt(currentLineSplit[3]);
            craneOrderParsed[2] = Integer.parseInt(currentLineSplit[5]);
            craneOrders.add(craneOrderParsed);
        }
    }

    private void parseStacksFromInputString() {
        int highestStack = getHighestStackAmount();
        for (int i = 0; i < AMOUNT_OF_STACKS; i++) {
            Stack<Character> currentStack = new Stack<>();
            for (int j = 0; j < highestStack; j++) {
                String currentLine = inputAsString.get(j);
                if ((currentLine.length() >= 4 * i) && currentLine.charAt(4 * i + 1) != ' ') {
                    currentStack.add(currentLine.charAt(4 * i + 1)); //4*i+1 is the pattern in which the chars are in the input file
                }
            }
            Collections.reverse(currentStack);
            stacksList.add(currentStack);
        }
    }

    private int getHighestStackAmount() {
        for (int i = 0; i < inputAsString.size(); i++) {
            if (inputAsString.get(i).charAt(1) == '1') {
                return i;
            }
        }
        throw new RuntimeException("Error while getting highest stack amount");
    }

    private void doAllTheCraneOrders() {
        for (Integer[] currentOrder : craneOrders) {
            int currentGivingStackNumber = currentOrder[1] - 1;
            int currentReceivingStackNumber = currentOrder[2] - 1;
            int currentAmountOfCratesToBeMoved = currentOrder[0];
            doOneCraneOrder(currentGivingStackNumber, currentAmountOfCratesToBeMoved, currentReceivingStackNumber);
        }
    }

    private void doAllTheCraneOrdersMultipleCratesAtATime() {
        for (Integer[] currentOrder : craneOrders) {
            int currentGivingStackNumber = currentOrder[1] - 1;
            int currentReceivingStackNumber = currentOrder[2] - 1;
            int currentAmountOfCratesToBeMoved = currentOrder[0];
            doOneCraneOrderMultipleCratesAtATime(currentGivingStackNumber, currentAmountOfCratesToBeMoved, currentReceivingStackNumber);
        }
    }

    private void doOneCraneOrder(int givingStackNumber, int cratesToBeMoved, int receivingStackNumber) {
        Stack<Character> givingStack = stacksList.get(givingStackNumber);
        Stack<Character> receivingStack = stacksList.get(receivingStackNumber);
        for (int i = 0; i < cratesToBeMoved; i++) {
            receivingStack.add(givingStack.pop());
        }
    }

    private void doOneCraneOrderMultipleCratesAtATime(int givingStackNumber, int cratesToBeMoved, int receivingStackNumber) {
        Stack<Character> givingStack = stacksList.get(givingStackNumber);
        Stack<Character> receivingStack = stacksList.get(receivingStackNumber);
        for (int i = cratesToBeMoved; i > 0; i--) {
            receivingStack.add(givingStack.get(givingStack.size() - i));
        }
        for (int i = 0; i < cratesToBeMoved; i++) {
            givingStack.pop();
        }
    }

    public void printCratesOnTop() {
        for (Stack<Character> currentStack : stacksList) {
            System.out.print(currentStack.get(currentStack.size() - 1));
        }
    }

    public static void main(String[] args) {
        SupplyStacks ss = new SupplyStacks();
        ss.printCratesOnTop();
    }
}
