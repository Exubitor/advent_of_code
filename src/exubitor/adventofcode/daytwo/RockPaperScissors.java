package exubitor.adventofcode.daytwo;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A = rock<br>
 * B = paper<br>
 * C = scissors<br>
 * <br>
 * Part Two:<br>
 * X means player needs to lose<br>
 * Y means player needs to tie<br>
 * Z means player needs to win
 */
public class RockPaperScissors {

    private final List<String> inputAsStrings;
    private final List<Character> enemyChoices;
    private final List<Character> playerChoices;
    private final List<Character> playerChoicesPartTwo;
    private final static int LETTER_SHIFT_FROM_A_TO_X = 23;

    public RockPaperScissors() {
        File input = new File("src/exubitor/adventofcode/daytwo/resource/input.txt");
        inputAsStrings = MyFileInput.parseInputToStringList(input);
        enemyChoices = new ArrayList<>();
        playerChoices = new ArrayList<>();
        parseEnemyChoices();
        parsePlayerChoices();
        playerChoicesPartTwo = new ArrayList<>();
        parsePlayerChoicesPartTwo();
    }

    private void parseEnemyChoices() {
        for (String s : inputAsStrings) {
            enemyChoices.add(s.charAt(0));
        }
    }

    private void parsePlayerChoices() {
        for (String s : inputAsStrings) {
            playerChoices.add((char) (s.charAt(2) - LETTER_SHIFT_FROM_A_TO_X));
        }
    }

    private void parsePlayerChoicesPartTwo() {
        for (String s : inputAsStrings) {
            if (s.charAt(2) == 'X') {
                playerChoicesPartTwo.add(getCharacterThatLosesAgainst(s.charAt(0)));
            }
            if (s.charAt(2) == 'Y') {
                playerChoicesPartTwo.add((s.charAt(0)));
            }
            if (s.charAt(2) == 'Z') {
                playerChoicesPartTwo.add(getCharacterThatWinsAgainst(s.charAt(0)));
            }
        }
    }

    private char getCharacterThatWinsAgainst(char choice) {
        switch (choice) {
            case 'A':
                return 'B';
            case 'B':
                return 'C';
            case 'C':
                return 'A';
            default:
                return (char) 0;
        }
    }

    private char getCharacterThatLosesAgainst(char choice) {
        switch (choice) {
            case 'A':
                return 'C';
            case 'B':
                return 'A';
            case 'C':
                return 'B';
            default:
                return (char) 0;
        }
    }

    private int getTotalScoreForUsingShapes(List<Character> choices) {
        int totalScoreForUsingShapes = 0;
        for (char c : choices) {
            switch (c) {
                case 'A' -> totalScoreForUsingShapes += 1;
                case 'B' -> totalScoreForUsingShapes += 2;
                case 'C' -> totalScoreForUsingShapes += 3;
            }
        }
        return totalScoreForUsingShapes;
    }

    private int getTotalScoreForOutcomeOfRounds(List<Character> choices) {
        int totalScoreForOutcomeOfRound = 0;
        for (int i = 0; i < inputAsStrings.size(); i++) {
            totalScoreForOutcomeOfRound += getScoreForOutcomeOfOneRound(enemyChoices.get(i), choices.get(i));
        }
        return totalScoreForOutcomeOfRound;
    }

    /**
     * @return 0 if the player loses<br>
     * 3 if it's a draw<br>
     * 6 if the player wins<br>
     * -1 if something went wrong
     */
    private int getScoreForOutcomeOfOneRound(char enemyChoice, char playerChoice) {
        if (enemyChoice == playerChoice) {
            return 3; //draw
        }
        if (enemyChoice == 'A' && playerChoice == 'B') {
            return 6; //rock vs. paper
        }
        if (enemyChoice == 'A' && playerChoice == 'C') {
            return 0; //rock vs. scissors
        }
        if (enemyChoice == 'B' && playerChoice == 'A') {
            return 0; //paper vs. rock
        }
        if (enemyChoice == 'B' && playerChoice == 'C') {
            return 6; //paper vs. scissors
        }
        if (enemyChoice == 'C' && playerChoice == 'A') {
            return 6; //scissors vs. rock
        }
        if (enemyChoice == 'C' && playerChoice == 'B') {
            return 0; //scissors vs. paper
        }
        return -1;
    }

    public int getTotalScorePartOne() {
        return getTotalScoreForUsingShapes(playerChoices) + getTotalScoreForOutcomeOfRounds(playerChoices);
    }

    public int getTotalScorePartTwo() {
        return getTotalScoreForUsingShapes(playerChoicesPartTwo) + getTotalScoreForOutcomeOfRounds(playerChoicesPartTwo);
    }

    public static void main(String[] args) {
        RockPaperScissors rps = new RockPaperScissors();
        System.out.println(rps.getTotalScorePartOne());
        System.out.println(rps.getTotalScorePartTwo());
    }
}
