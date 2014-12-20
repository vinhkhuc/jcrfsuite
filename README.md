Jcrfsuite is a Java interface of [crfsuite](http://www.chokkan.org/software/crfsuite/), a fast implementation of Conditional Random Fields, using SWIG and class injection technique (same technique used in [snappy-java](https://github.com/xerial/snappy-java) version 1.1.0).

### Example on Twitter Part-of-Speech (POS) tagging

### 1) Build

To build, you need to install Maven, then run

<pre>
mvn clean package
</pre>
	
### 2) Training
To train a POS model from Twitter POS data, run

<pre>
java -cp target/jcrfsuite-*.jar com.github.jcrfsuite.example.Train example/tweet-pos/train-oct27.txt twitter-pos.model
</pre>
	
### 3) Tagging
To test the trained POS model against the test set, run

<pre>
java -cp target/jcrfsuite-*.jar com.github.jcrfsuite.example.Tag twitter-pos.model example/tweet-pos/test-daily547.txt
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

