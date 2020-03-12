package com.merchant.guide.galaxy.solution;

import static com.merchant.guide.galaxy.solution.InputOutputListConstants.*;

/**
 * @author Somnath Paramanick
 *
 */
public class RomanNumeralsValidator {
	//i("i", 1), v("v", 5), x("x", 10), l("l", 50), c("c", 100), d("d", 500), m("m", 1000);
	
	public static int validateRomanNumerals(String romanLiteral) {
		char[] charArray = romanLiteral.toCharArray();
        char previousRomanChar = ' ';

        int characterRepeatCount = 1;
        int total = 0;
        int previousRomanCharacterOrdinal = Integer.MAX_VALUE; 
        int currentRomanCharacterOrdinal;

        for(int i = 0; i<charArray.length; i++){
            char currentChar = charArray[i];
            int currentRomanCharNumericValue = RomanNumerals.valueOf(String.valueOf(currentChar)).getValue();

            //ordinal of i = 0, v = 1 x = 2 l = 3 c = 4 d = 5 m = 6
            if(previousRomanChar != ' '){
                previousRomanCharacterOrdinal = RomanNumerals.valueOf(String.valueOf(previousRomanChar)).ordinal();                
            }
            currentRomanCharacterOrdinal = RomanNumerals.valueOf(String.valueOf(currentChar)).ordinal();
            
            //The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. characterRepeatCount is incremented when the current character gets a match with the previous one.
            if(currentChar == previousRomanChar && ++characterRepeatCount < 4 && ThreeTimesRepeatedCharacters.contains(currentChar)){
                total += currentRomanCharNumericValue;
            }
            else if(characterRepeatCount > 3){
                total = -1;
            }
            //The symbols repeat itself which are not in the set of three times repeated characters
            else if(currentChar == previousRomanChar && !ThreeTimesRepeatedCharacters.contains(currentChar)){
                total = -1;
            }
            //Only one small-value symbol may be subtracted from any large-value symbol. eg. MCMXLIV 
            //I = 1 V = 5 X = 10 L = 50 C = 100 D = 500 M = 1000
            //"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted from "D" and "M" only. 
            else if(previousRomanCharacterOrdinal < currentRomanCharacterOrdinal  && !NotToBeSubtracted.contains(previousRomanChar)){
            	//in case of C as a previous character it is 100
                int previousRomanCharNumericValue = RomanNumerals.valueOf(String.valueOf(previousRomanChar)).getValue();
                //in case of C as a previous character ordinal is 4 and the current character ordinal is 6 
                if(previousRomanCharacterOrdinal + 2 >= currentRomanCharacterOrdinal){
                    total += currentRomanCharNumericValue - (2 * previousRomanCharNumericValue); 
                    characterRepeatCount = 1;
                }
                else{
                    total = -1;
                }
            }
            //"V", "L", and "D" can never be subtracted. eg. VMDC
            else if(previousRomanCharacterOrdinal < currentRomanCharacterOrdinal  && NotToBeSubtracted.contains(previousRomanChar)){
                total = -1;
            }
            else{
                characterRepeatCount = 1;
                total += currentRomanCharNumericValue;
            }
            previousRomanChar = currentChar;
            if(total == -1)
                break;
        }
        return total;
	}

}
