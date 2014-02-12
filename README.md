Jcrfsuite is a Java interface of [crfsuite](http://www.chokkan.org/software/crfsuite/), a fast implementation of Conditional Random Fields, using SWIG and class injection technique (same technique used in [snappy-java](https://github.com/xerial/snappy-java) version 1.1.0).

### Example on how to use jcrfsuite for Twitter POS tagging

### 1) Build

To build, you need to install Maven, then run

<pre>
mvn package
</pre>

### 2) Training
To train a POS model from Twitter POS data, run

<pre>
java -cp target/jcrfsuite-0.1.jar com.github.jcrfsuite.example.Train example/tweet-pos/train.txt twitter-pos.model
</pre>

### 3) Tagging
To test the trained POS model against the test set, run

<pre>
java -cp target/jcrfsuite-0.1.jar com.github.jcrfsuite.example.Tag twitter-pos.model example/tweet-pos/test.txt 
</pre>

The output should be as follows:

<pre>
Gold	Predict	Probability
........................
O		O		1.00
V		V		1.00
D		D		1.00
^		N		0.96
&		&		1.00
L		L		0.79
P		P		0.80
^		^		0.94
,		,		0.98

Accuracy = 91.77%
</pre>

Note that the accuracy might be slightly different than in the above output.

