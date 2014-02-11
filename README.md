Jcrfsuite is a Java interface of [crfsuite](http://www.chokkan.org/software/crfsuite/) using SWIG and class injection technique (same as the one used in snappy-java version 1.1.0).

### Example on how to use jcrfsuite for Twitter POS tagging

### 1) Build

mvn package

### 2) Train POS model

java -cp target/jcrfsuite-0.0.1.jar com.github.jcrfsuite.example.Train example/tweet-pos/train.txt twitter-pos.model

### 3) POS tagging

java -cp target/jcrfsuite-0.0.1.jar com.github.jcrfsuite.example.Tag twitter-pos.model example/tweet-pos/test.txt 

The output should be:

> Gold	Predict	Probability

> O       O       1.00

> V       V       1.00

> D       D       1.00

> ^       N       0.96

> &       &       1.00

> L       L       0.79

> P       P       0.80

> ^       ^       0.94

> ,       ,       0.98

> Accuracy = 91.77%