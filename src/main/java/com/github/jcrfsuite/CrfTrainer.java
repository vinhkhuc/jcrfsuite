package com.github.jcrfsuite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import third_party.org.chokkan.crfsuite.Attribute;
import third_party.org.chokkan.crfsuite.Item;
import third_party.org.chokkan.crfsuite.ItemSequence;
import third_party.org.chokkan.crfsuite.StringList;
import third_party.org.chokkan.crfsuite.Trainer;

import com.github.jcrfsuite.util.CrfSuiteLoader;
import com.github.jcrfsuite.util.Pair;

public class CrfTrainer {
	
	private static final String DEFAULT_ALGORITHM = "lbfgs";
	private static final String DEFAULT_GRAPHICAL_MODEL_TYPE = "crf1d";

	static {
		try {
			CrfSuiteLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Load data in CRFsuite format.
	 * 
	 * @param fileName
	 *			The filename of the file containing the data.
	 * @return The sequences paired with the expected values.
	 * @throws IOException
	 */
	public static Pair<List<ItemSequence>, List<StringList>> loadTrainingInstances(
			String fileName) throws IOException 
	{
		List<ItemSequence> xseqs = new ArrayList<ItemSequence>();
		List<StringList> yseqs = new ArrayList<StringList>();
		
		ItemSequence xseq = new ItemSequence();
		StringList yseq = new StringList();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					String[] fields = line.split("\t");
					// add label
					yseq.add(fields[0]);
					// add item which is a list of attributes
					Item item = new Item();
					for (int i = 1; i < fields.length; i++) {
						String field = fields[i];
						String[] colonSplit = field.split(":", 2);
						if (colonSplit.length == 2) {
							try {
								// See if the feature has a scaling value.
								double val = Double.valueOf(colonSplit[1]);
								item.add(new Attribute(colonSplit[0], val));
							} catch (NumberFormatException e) {
								// There was no scaling value.
								item.add(new Attribute(field));
							}
						} else {
							item.add(new Attribute(field));
						}
					}
					xseq.add(item);

				} else {
					xseqs.add(xseq);
					yseqs.add(yseq);
					xseq = new ItemSequence();
					yseq = new StringList();
				}
			}
			if (!xseq.isEmpty()) {
				// add the last one
				xseqs.add(xseq);
				yseqs.add(yseq);
			}
		}
		
		return new Pair<List<ItemSequence>, List<StringList>>(xseqs, yseqs);
	}
	
	/**
	 * Trains the CRF Suite using data from a given file.
	 */
	public static void train(String fileName, String modelFile) throws IOException {
		train(fileName, modelFile, DEFAULT_ALGORITHM, DEFAULT_GRAPHICAL_MODEL_TYPE);
	}

	/**
	 * Trains the CRF Suite using data from a given file.
	 */
	public static void train(String fileName, String modelFile,
			String algorithm, String graphicalModelType,
			Pair<String, String>... parameters) throws IOException {
		
		Pair<List<ItemSequence>, List<StringList>> trainingData
			= loadTrainingInstances(fileName);
		
		List<ItemSequence> xseqs = trainingData.first;
		List<StringList> yseqs = trainingData.second;
		train(xseqs, yseqs, modelFile, algorithm, graphicalModelType, parameters);
	}

	/**
	 * Train CRF Suite with annotated item sequences.
	 */
	public static void train(List<ItemSequence> xseqs, List<StringList> yseqs, 
			String modelFile) 
	{
		train(xseqs, yseqs, modelFile, DEFAULT_ALGORITHM, DEFAULT_GRAPHICAL_MODEL_TYPE);
	}

	/**
	 * Train CRF Suite with annotated item sequences.
	 */
	public static void train(List<ItemSequence> xseqs, List<StringList> yseqs, 
			String modelFile, String algorithm, String graphicalModelType,
			Pair<String, String>... parameters) 
	{
		Trainer trainer = new Trainer();
		// Add training data into the trainer
		int n = xseqs.size();
		for (int i = 0; i < n; i++) {
			// Use group id = 0 but the API doesn't say what it is used for :(
			trainer.append(xseqs.get(i), yseqs.get(i), 0); 
		}
		
		// Algorithm type: lbfgs, l2sgd, averaged-perceptron, passive-aggressive, arow
		trainer.select(algorithm, graphicalModelType);

		// TODO: Allow to specify c1, c2, epsilon, delta, num_memories
//		trainer.set("c1", "0.25");
//		trainer.set("c2", "0.1");
//		trainer.set("epsilon", "0.0000001");
//		trainer.set("delta", "0.0000001");
//		trainer.set("num_memories", "6");

		// set parameters
		if (parameters != null) {
			for (Pair<String, String> attribute : parameters) {
				trainer.set(attribute.first, attribute.second);
			}
		}

		// List parameters and their values
		StringList params = trainer.params();
		for (int i = 0; i < params.size(); i++) {
			String param = params.get(i);
			System.out.printf("%s, %s, %s\n",
					param, trainer.get(param), trainer.help(param));
		}
		
		// Start training without hold-outs. trainer.message() 
		// will be called to report the training process
		trainer.train(modelFile, -1);			
	}
}