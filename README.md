This is a Java interface for [crfsuite](http://www.chokkan.org/software/crfsuite/),
a fast implementation of Conditional Random Fields, using SWIG and class
injection technique (the same technique used in [snappy-java](https://github.com/xerial/snappy-java)).
Jcrfsuite provides API for loading trained model into memory and do sequential tagging in memory.
Model training is done via command line interface.

The library is designed for building Java applications for fast text sequential tagging
such as Part-Of-Speech (POS) tagging, phrase chunking, Named-Entity Recognition (NER), etc.

Jcrfsuite can be dropped into any Java web applications and run without problem with JVM's class loader.

### Maven dependency
```xml
<dependency>
  <groupId>com.github.vinhkhuc</groupId>
  <artifactId>jcrfsuite</artifactId>
  <version>0.6.1</version>
</dependency>
```

### Building

```bash
git clone https://github.com/vinhkhuc/jcrfsuite
cd jcrfsuite
mvn clean package
```

### How to use

#### Model training
```java
import com.github.jcrfsuite.CrfTrainer;
...
String trainFile = "data/tweet-pos/train-oct27.txt";
String modelFile = "twitter-pos.model";
CrfTrainer.train(trainFile, modelFile);
```

#### Sequential tagging
```java
import com.github.jcrfsuite.CrfTagger;
import com.github.jcrfsuite.util.Pair;
...
String modelFile = "twitter-pos.model";
String testFile = "data/tweet-pos/test-daily547.txt";
CrfTagger crfTagger = new CrfTagger(modelFile);
List<List<Pair<String, Double>>> tagProbLists = crfTagger.tag(testFile);
```

### Example on Twitter Part-Of-Speech tagging
	
#### Training
To train a POS model from Twitter POS data, run

<pre>
java -cp target/jcrfsuite-*.jar com.github.jcrfsuite.example.Train data/tweet-pos/train-oct27.txt twitter-pos.model
</pre>
	
#### Tagging
To test the trained POS model against the test set, run

<pre>
java -cp target/jcrfsuite-*.jar com.github.jcrfsuite.example.Tag twitter-pos.model data/tweet-pos/test-daily547.txt
</pre>
	
The output should be as follows:

<pre>
Gold	Predict	Probability
........................
N       N       0.99
P       P       1.00
Z       ^       0.59
$       $       0.97
N       N       1.00
P       P       0.98
A       N       0.80
$       $       1.00
N       N       0.99
U       U       1.00

Accuracy = 92.99%
</pre>

Note that the accuracy might be slightly different than in the above output.

### License

Jcrfsuite is released under the Apache License 2.0. The original crfsuite is distributed under the BSD License.
