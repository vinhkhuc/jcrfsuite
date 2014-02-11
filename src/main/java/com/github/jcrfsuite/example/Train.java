package com.github.jcrfsuite.example;

import java.io.IOException;

import com.github.jcrfsuite.CrfTrainer;

/**
 * This example shows how to use jcrfsuite to train a POS model
 * 
 * @author vinkhu
 *
 */
public class Train {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: " + Train.class.getCanonicalName() + " <train file> <model file>");
			System.exit(1);
		}
		
		String trainFile = args[0];
		String modelFile = args[1];
		CrfTrainer.train(trainFile, modelFile);
	}

}
