package com.github.jcrfsuite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.jcrfsuite.util.CrfSuiteLoader;
import com.github.jcrfsuite.util.Pair;

import third_party.org.chokkan.crfsuite.Attribute;
import third_party.org.chokkan.crfsuite.Item;
import third_party.org.chokkan.crfsuite.ItemSequence;
import third_party.org.chokkan.crfsuite.StringList;
import third_party.org.chokkan.crfsuite.Tagger;

/**
 * An instance of a tagger using CRFsuite.
 */
public class CrfTagger {
	
	static {
		try {
			CrfSuiteLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private final Tagger tagger = new Tagger();

	/**
	 * Create a tagger using a model file.
	 * 
	 * @param modelFile The file containing the model for this tagger.
	 */
	public CrfTagger(String modelFile){
	    tagger.open(modelFile);
	}
	
	protected static List<ItemSequence> loadTaggingInstances(String fileName) throws IOException 
	{
		List<ItemSequence> xseqs = new ArrayList<ItemSequence>();
		ItemSequence xseq = new ItemSequence();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					String[] fields = line.split("\t");
					Item item = new Item();
					for (int i = 1; i < fields.length; i++) { // field 0 is a label
						item.add(new Attribute(fields[i]));
					}
					xseq.add(item);
				} else { // end of sequence
					xseqs.add(xseq);
					xseq = new ItemSequence();
				}
			}
		}
		return xseqs;
	}
	
	/**
	 * Tag an item sequence.
	 */
	public List<Pair<String, Double>> tag(ItemSequence xseq) {
		
		List<Pair<String, Double>> predicted = 
				new ArrayList<Pair<String, Double>>();
		
		tagger.set(xseq);
		StringList labels = tagger.viterbi();
		for (int i = 0; i < labels.size(); i++) {
			String label = labels.get(i);
			predicted.add(new Pair<String, Double>(
					label, tagger.marginal(label, i)));
		}
		
		return predicted;
	}
	
	/**
	 * Tag text in file.
	 */
	public List<List<Pair<String, Double>>> tag(String fileName) throws IOException {
		
		List<List<Pair<String, Double>>> taggedSentences = 
				new ArrayList<List<Pair<String, Double>>>();
		
		for (ItemSequence xseq: loadTaggingInstances(fileName)) {
			taggedSentences.add(tag(xseq));
		}
		
		return taggedSentences;
	}
}