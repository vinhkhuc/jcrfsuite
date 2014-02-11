package com.github.jcrfsuite.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.github.jcrfsuite.CrfTagger;
import com.github.jcrfsuite.util.Pair;

/**
 * This example shows how to use jcrfsuite to do POS tagging
 * 
 * @author vinkhu
 *
 */
public class Tag {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: " + Tag.class.getCanonicalName() + " <model file> <test file>");
			System.exit(1);
		}
		String modelFile = args[0];
		String testFile = args[1];
		
		// POS tag
		CrfTagger.loadModel(modelFile);
		List<List<Pair<String, Double>>> tagProbLists = CrfTagger.tag(testFile);
		
		// Compute accuracy
		int total = 0;
		int correct = 0;
		System.out.println("Gold\tPredict\tProbability");
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		String line;
		for (List<Pair<String, Double>> tagProbs: tagProbLists) {
			for (Pair<String, Double> tagProb: tagProbs) {
				String prediction = tagProb.first;
				Double prob = tagProb.second;
				
				line = br.readLine();
				if (line.length() == 0) {
					// End of the sentence, will get word from the next sentence
					line = br.readLine(); 
				}
				String gold = line.split("\t")[0];
				
				System.out.format("%s\t%s\t%.2f\n", gold, prediction, prob);
				total++;
				if (gold.equals(prediction)) {
					correct++;
				}
			}
			System.out.println();
		}
		br.close();
		
		System.out.format("Accuracy = %.2f%%\n", 100. * correct / total);
	}
}
