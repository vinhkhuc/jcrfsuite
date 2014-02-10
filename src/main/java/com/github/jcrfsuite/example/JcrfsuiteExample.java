package com.github.jcrfsuite.example;

import java.io.IOException;

import com.github.jcrfsuite.CrfTrainer;

/**
 * This example shows how to use jcrfsuite to train and predict 
 * outcomes from sequential text.
 * 
 * @author vinkhu
 *
 */
public class JcrfsuiteExample {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: JcrfsuiteExample <train file> <model file>");
			System.exit(1);
		}
		
		String trainFile = args[0];
		String modelFile = args[1];
		CrfTrainer.train(trainFile, modelFile);
	}

}
