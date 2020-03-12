package com.merchant.guide.galaxy.solution;


/**
 * @author Somnath Paramanick
 *
 */
public class StartProgram {

	public static void main(String[] args) {
		String filePath = "E:\\Temp\\input.txt";

        if (args.length != 0)
            filePath = args[0];
        try{
            ProcessInput processInput = new ProcessInput();
            processInput.processInputFile(filePath);
            ShowOutput.processOutput();
        }
        catch(Exception e){
            System.out.println("Invalid Input " + e);
        }

	}

}
