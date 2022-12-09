package exubitor.adventofcode.daysix;

import exubitor.adventofcode.util.MyFileInput;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class TuningTrouble {

    private String inputSignal;

    public TuningTrouble(){
        inputSignal= MyFileInput.parseInputToStringList(new File("src/exubitor/adventofcode/daysix/resource/input.txt")).get(0);
    }

    public int getMarker(){
        for (int i = 0; i < inputSignal.length()-4; i++) {
            if (checkIfNoDoubleCharacter(inputSignal.substring(i,i+4))){
                return i+4;
            }
        }
        throw new RuntimeException("Couldn't find marker");
    }

    public int getMessageMarker(){
        for (int i = 0; i < inputSignal.length()-14; i++) {
            if (checkIfNoDoubleCharacter(inputSignal.substring(i,i+14))){
                return i+14;
            }
        }
        throw new RuntimeException("Couldn't find marker");
    }

    private boolean checkIfNoDoubleCharacter(String string){
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            if (!set.add(string.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TuningTrouble tt = new TuningTrouble();
        System.out.println(tt.getMarker());
        System.out.println(tt.getMessageMarker());
    }
}
