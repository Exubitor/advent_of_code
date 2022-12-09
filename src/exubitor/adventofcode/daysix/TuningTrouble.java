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

    /**
     * @param string
     * @param range the range in which is checked
     * @return the index of the given strings first occurrence of no double character in the given range
     */
    private int getMarkerLocation(String string, int range){
        for (int i = 0; i < string.length()-range; i++) {
            if (checkIfNoDoubleCharacter(string.substring(i,i+range))){
                return i+range;
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

    public int getPacketMarker(){
        return getMarkerLocation(inputSignal, 4);
    }
    public int getMessageMarker(){
        return getMarkerLocation(inputSignal, 14);
    }

    public static void main(String[] args) {
        TuningTrouble tt = new TuningTrouble();
        System.out.println(tt.getPacketMarker());
        System.out.println(tt.getMessageMarker());
    }
}
