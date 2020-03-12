/**
 * 
 */
package com.merchant.guide.galaxy.solution;

import static com.merchant.guide.galaxy.solution.InputOutputListConstants.*;
/**
 * @author Somnath Paramanick
 *
 */
public class ShowOutput {

	/**
	 * Both the input and output values are stored in a linked list. The function formats the output based on the question input   
	 */
	public static void processOutput() {		
		for (int i = 0; i < inputValues.size(); i++) {
            StringBuilder result = new StringBuilder();

            String question = inputValues.get(i);
            String output = outputValues.get(i);

            if(output == INVALID_INPUT) {
                result.append(INVALID_INPUT);
            }
            else{
                if(question.toLowerCase().startsWith(HOW_MUCH_IS)){
                    result.append(question.substring(HOW_MUCH_IS.length(), question.length()-1).trim());
                    result.append(IS);
                    result.append(Double.valueOf(output).intValue());
                }

                else if(question.toLowerCase().startsWith(HOW_MANY_CREDITS_IS)){
                    result.append(question.substring(HOW_MANY_CREDITS_IS.length(), question.length()-1).trim());
                    result.append(SPACE+IS+SPACE);
                    result.append(Double.valueOf(output).intValue());
                    result.append(SPACE+CREDITS);
                }
                else
                    result.append(INVALID_INPUT);
            }

            System.out.println(result.toString());
        }
	}

}
