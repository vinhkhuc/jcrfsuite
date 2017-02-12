package com.github.jcrfsuite.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the example.
 * 
 * @author Justin Harris (github.com/juharris)
 */
public class TrainTagExampleTest {

	private static final Path TRAINING_FOLDER = Paths.get(System.getProperty("user.dir"))
			.resolve("src")
			.resolve("test")
			.resolve("resources")
			.resolve("com")
			.resolve("github")
			.resolve("jcrfsuite")
			.resolve("trainer");

	private static final Path MODEL_PATH = TRAINING_FOLDER.resolve("twitter-pos.model");

	@Before
	public void setUpTest() throws IOException {
		// Delete the model to make sure we make a new one.
		Files.deleteIfExists(MODEL_PATH);
	}

	@After
	public void teardownTest() throws IOException {
		// Delete the model to make sure we make a new one next time and to make extra sure it's not commited.
		Files.deleteIfExists(MODEL_PATH);
	}

	@Test
	public void testMain() throws IOException {
		Train.main(new String[] {
				"data/tweet-pos/train-oct27.txt",
				MODEL_PATH.toString()
		});
		Tag.main(new String[] {
				MODEL_PATH.toString(),
				"data/tweet-pos/test-daily547.txt"
		});
	}
}
