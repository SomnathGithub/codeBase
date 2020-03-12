/**
 * 
 */
package com.merchant.guide.galaxy.solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.merchant.guide.galaxy.solution.InputOutputListConstants.RomanNumerals;

import static com.merchant.guide.galaxy.solution.InputOutputListConstants.*;

/**
 * @author Somnath Paramanick
 *
 */
public class ProcessInput {

	/**
	 * @param filePath
	 * @throws Exception
	 */
	public void processInputFile(String filePath) throws Exception {

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line;

			while ((line = br.readLine()) != null) {
				processInputValues(line.trim());
			}

		} catch (IOException e) {
			System.out.println("File not found exception " + e);
		}
	}

	/**
	 * @param lineValue
	 */
	private void processInputValues(String lineValue) {
		String lineValueStr = lineValue.toLowerCase();
		String[] inputs = lineValueStr.split("\\s+");
		if (lineValueStr.startsWith(HOW_MANY_CREDITS_IS)) {
			inputValues.add(lineValue);
			outputValues
					.add(String.valueOf(generateLineCreditValues(Arrays.copyOfRange(inputs, 4, inputs.length - 1))));
		}
		// how much is pish tegj glob glob ?
		else if (lineValueStr.startsWith(HOW_MUCH_IS)) {
			inputValues.add(lineValue);
			outputValues.add(String
					.valueOf(generateLineGalacticUnitToNumericValue(Arrays.copyOfRange(inputs, 3, inputs.length - 1))));
		}
		// For an input "glob is I"
		else if (lineValueStr.contains(InputOutputListConstants.IS)
				&& !lineValueStr.contains(InputOutputListConstants.CREDITS))
			mapInterGalacticToRoman(inputs);
		// glob glob Silver is 34 Credits
		else if (lineValueStr.contains("is") && lineValueStr.contains(CREDITS))
			mapObjectToCreditUnits(inputs);
		// how many Credits is glob prok Silver ?
		else {
			inputValues.add(lineValue);
			outputValues.add(INVALID_INPUT);
		}
	}

	/**
	 * @param sample input glob is I a map is created with the intergalactic units
	 *               and its corresponding roman units
	 */
	private void mapInterGalacticToRoman(String[] inputs) {
		try {
			galacticToRomanMap.put(inputs[0], RomanNumerals.valueOf(inputs[2]));
		} catch (IllegalArgumentException e) {
			System.out.println("This type of Roman is not defined, exiting the program " + e);
		}
	}

	/**
	 * @param inputs sample input glob glob Silver is 34 Credits
	 * @return if the validation is successful then a map is created with the object
	 *         and its corresponding credit units
	 */
	private void mapObjectToCreditUnits(String[] inputs) {
		StringBuilder romanNumeralStr = new StringBuilder();
		int i;
		for (i = 0; i < inputs.length; i++) {
			RomanNumerals romanNumerals = galacticToRomanMap.get(inputs[i]);
			if (romanNumerals != null) {
				romanNumeralStr.append(romanNumerals.getDisplayText());
				// romanNumeral = ii
			} else
				break;
		}

		int valueDerived = RomanNumeralsValidator.validateRomanNumerals(romanNumeralStr.toString());
		String objectName = inputs[i];
		int credits = Integer.parseInt(inputs[i + 2]);

		objectsToCreditUnitsMap.put(objectName, (double) credits / valueDerived);
		// 2 Silver == 34 Units
		// 1 Silver ==17
	}

	/**
	 * @param sample input how many Credits is glob prok Silver ?
	 * @return the value for that line
	 */
	private Double generateLineCreditValues(String[] inputs) {
		int numericValue = generateGalacticUnitToNumericValue(Arrays.copyOfRange(inputs, 0, inputs.length - 1));

		if (numericValue == -1)
			return (double) -1;

		return numericValue * objectsToCreditUnitsMap.get(inputs[inputs.length - 1]);
	}

	/**
	 * @param sample input how much is pish tegj glob glob ?
	 * @return the value for that line
	 */
	private int generateLineGalacticUnitToNumericValue(String[] inputs) {
		try {
			String romanNumeralStr = generateRomanFromGalacticUnit(inputs);

			if (romanNumeralStr == null)
				return -1;

			return RomanNumeralsValidator.validateRomanNumerals(romanNumeralStr.toString());
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * @param inputs Roman Value MCMXLIV
	 * @return the value for the Roman Value after validation
	 */
	private int generateGalacticUnitToNumericValue(String[] inputs) {
		try {
			String romanNumeral = generateRomanFromGalacticUnit(inputs);

			if (romanNumeral == null)
				return -1;

			return RomanNumeralsValidator.validateRomanNumerals(romanNumeral.toString());
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * @param inputs Galactic Units glob prok
	 * @return Roman Value
	 */
	private String generateRomanFromGalacticUnit(String[] inputs) {
		StringBuilder romanNumeralStr = new StringBuilder();
		int i;
		for (i = 0; i < inputs.length; i++) {
			RomanNumerals romanNumerals = galacticToRomanMap.get(inputs[i]);
			if (romanNumerals != null) {
				romanNumeralStr.append(romanNumerals.getDisplayText());
			} else
				break;
		}

		if (i != inputs.length)
			return null;

		return romanNumeralStr.toString();
	}

}
