package mySample;

import myInterface.IAggregable;
import myInterface.IDeeplyCloneable;

public class Letter implements IAggregable<Letter, String>, IDeeplyCloneable<Letter> {

    private String lChar;

    public Letter(){}

    public Letter(String l){
        lChar = l;
    }

    public String getlChar() {
        return lChar;
    }

    @Override
    public String aggregate(String intermediateResult) {
        if(intermediateResult == null){
            return lChar;
        }
        return intermediateResult + lChar;
    }

    @Override
    public Letter deepClone() {
        Letter letClone = new Letter();
        letClone.lChar = lChar;
        return  letClone;
    }
}
