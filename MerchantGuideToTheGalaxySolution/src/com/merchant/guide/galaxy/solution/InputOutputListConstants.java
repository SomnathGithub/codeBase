package com.merchant.guide.galaxy.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Somnath Paramanick
 *
 */
public class InputOutputListConstants {
	//this will contain the questions  
    public static final List<String> inputValues = new ArrayList<>();
    //this contains the output of the questions
    public static final List<String> outputValues = new ArrayList<>();
    //String constants
    public static final String HOW_MUCH_IS = "how much is";
    public static final String HOW_MANY_CREDITS_IS = "how many credits is";
    public static final String IS = "is";
    public static final String CREDITS = "credits";
    public static final String SPACE = " ";
    public static final String INVALID_INPUT = "I have no idea what you are talking about";
    // THIS MAP CONTAINS GALACTIC OBJECTS AND THEIR ROMAN NUMERALS {glob,I}
    public static Map<String, RomanNumerals> galacticToRomanMap = new HashMap<>();
    // THIS MAP CONTAINS OBJECTS AND THEIR CREDIT UNITS {Silver,17}
    public static Map<String, Double> objectsToCreditUnitsMap = new HashMap<>();
    //ENUM FOR THE ROMAN NUMERALS AND THEIR DISPLAY TEXTS AND VALUES
    public enum RomanNumerals{
        i("i", 1), v("v", 5), x("x", 10), l("l", 50), c("c", 100), d("d", 500), m("m", 1000);

        private int value;
        private String displayText;
        RomanNumerals(String displayText, int value){
            this.displayText = displayText;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getDisplayText() {
            return displayText;
        }


    }
    static Character ThreeTimesRepeatedCharactersArr[] = {'i','x','c','m'};
    static Character NotToBeSubtractedArr[] = {'v','l','d','m'};
	public static Set<Character> ThreeTimesRepeatedCharacters = new HashSet<Character>(); 
	public static Set<Character> NotToBeSubtracted = new HashSet<Character>(); 
	static {
		Collections.addAll(ThreeTimesRepeatedCharacters = 
                new HashSet<Character>(Arrays.asList(ThreeTimesRepeatedCharactersArr))); 
		Collections.addAll(NotToBeSubtracted = 
                new HashSet<Character>(Arrays.asList(NotToBeSubtractedArr)));
	}
}
