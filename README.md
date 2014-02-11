Jcrfsuite is a Java interface of [crfsuite](http://www.chokkan.org/software/crfsuite/), a fast implementation of Conditional Random Fields, using SWIG and class injection technique (same technique used in [snappy-java](https://github.com/xerial/snappy-java) version 1.1.0).

### Example on how to use jcrfsuite for Twitter POS tagging

### 1) Build

mvn package

### 2) Train POS model

java -cp target/jcrfsuite-0.0.1.jar com.github.jcrfsuite.example.Train example/tweet-pos/train.txt twitter-pos.model

### 3) POS tagging

java -cp target/jcrfsuite-0.0.1.jar com.github.jcrfsuite.example.Tag twitter-pos.model example/tweet-pos/test.txt 

The output should be:

> Gold &nbsp;&nbsp;&nbsp;&nbsp; Predict &nbsp;&nbsp;&nbsp;&nbsp; Probability

> ........................

> O &nbsp;&nbsp;&nbsp;&nbsp; O &nbsp;&nbsp;&nbsp;&nbsp; 1.00

> V &nbsp;&nbsp;&nbsp;&nbsp; V &nbsp;&nbsp;&nbsp;&nbsp; 1.00

> D &nbsp;&nbsp;&nbsp;&nbsp; D &nbsp;&nbsp;&nbsp;&nbsp; 1.00

> ^ &nbsp;&nbsp;&nbsp;&nbsp; N &nbsp;&nbsp;&nbsp;&nbsp; 0.96

> & &nbsp;&nbsp;&nbsp;&nbsp; & &nbsp;&nbsp;&nbsp;&nbsp; 1.00

> L &nbsp;&nbsp;&nbsp;&nbsp; L &nbsp;&nbsp;&nbsp;&nbsp; 0.79

> P &nbsp;&nbsp;&nbsp;&nbsp; P &nbsp;&nbsp;&nbsp;&nbsp; 0.80

> ^ &nbsp;&nbsp;&nbsp;&nbsp; ^ &nbsp;&nbsp;&nbsp;&nbsp; 0.94

> , &nbsp;&nbsp;&nbsp;&nbsp; , &nbsp;&nbsp;&nbsp;&nbsp; 0.98

> Accuracy = 91.77%