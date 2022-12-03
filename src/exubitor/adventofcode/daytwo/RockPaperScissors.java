package exubitor.adventofcode.daytwo;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A is enemy rock<br>
 * B is enemy paper<br>
 * C is enemy scissors<br>
 * <br>
 * X is player rock<br>
 * Y is player paper<br>
 * Z is player scissors<br>
 * <br>
 * Part Two:<br>
 * X is player needs to lose<br>
 * Y is player needs to tie<br>
 * Z is player needs to win
 */
public class RockPaperScissors {

    private final List<String> inputAsStrings;
    private final List<Character> enemyChoices;
    private final List<Character> playerChoices;

    public RockPaperScissors() {
        File input = new File("src/exubitor/adventofcode/daytwo/resource/input.txt");
        inputAsStrings = MyFileInput.parseInputToStringList(input);
        enemyChoices = new ArrayList<>();
        playerChoices = new ArrayList<>();
        parseEnemyChoices();
        parsePlayerChoices();
    }

    private void parseEnemyChoices() {
        for (String s : inputAsStrings) {
            enemyChoices.add(s.charAt(0));
        }
    }

    private void parsePlayerChoices() {
        for (String s : inputAsStrings) {
            playerChoices.add(s.charAt(2));
        }
    }

    private int getTotalScoreForUsingShapesPartOne() {
        int totalScoreForUsingShapes = 0;
        for (char c : playerChoices) {
            switch (c) {
                case 'X' -> totalScoreForUsingShapes += 1;
                case 'Y' -> totalScoreForUsingShapes += 2;
                case 'Z' -> totalScoreForUsingShapes += 3;
            }
        }
        return totalScoreForUsingShapes;
    }

    private int getTotalScoreForOutcomeOfRoundPartOne() {
        int totalScoreForOutcomeOfRound = 0;
        for (int i = 0; i < inputAsStrings.size(); i++) {
            totalScoreForOutcomeOfRound += getScoreForOutcomeOfOneRound(enemyChoices.get(i), playerChoices.get(i));
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
        final int LETTER_SHIFT_FROM_A_TO_X = 23;
        playerChoice = (char) (playerChoice - LETTER_SHIFT_FROM_A_TO_X);

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
        return getTotalScoreForUsingShapesPartOne() + getTotalScoreForOutcomeOfRoundPartOne();
    }

    public static void main(String[] args) {
        RockPaperScissors rps = new RockPaperScissors();
        System.out.println(rps.getTotalScorePartOne());
    }
}
