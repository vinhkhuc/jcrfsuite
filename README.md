This is a Java interface for [crfsuite](http://www.chokkan.org/software/crfsuite/), a fast implementation of Conditional Random Fields, using SWIG and class injection technique (the same technique used in [snappy-java](https://github.com/xerial/snappy-java))

Jcrfsuite can be dropped into any Java web applications and run without problem with JVM's class loader.

### Maven dependency
```xml
<dependency>
  <groupId>com.github.vinhkhuc</groupId>
  <artifactId>jcrfsuite</artifactId>
  <version>0.6</version>
</dependency>
```

### License

Jcrfsuite is released under the Apache License 2.0. The original crfsuite is distributed under the BSD License.

### Example on Twitter Part-of-Speech (POS) tagging
	
##### 1) Training
To train a POS model from Twitter POS data, run

<pre>
java -cp target/jcrfsuite-*.jar com.github.jcrfsuite.example.Train example/tweet-pos/train-oct27.txt twitter-pos.model
</pre>
	
##### 2) Tagging
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

