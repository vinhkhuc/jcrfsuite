Jcrfsuite is a Java interface of crfsuite (http://www.chokkan.org/software/crfsuite/) using SWIG and class injection technique (same as the one used in snappy-java version 1.1.0).

Example on how to train jcrfsuite for Twitter POS tagging

mvn package

java -cp target/jcrfsuite-0.0.1.jar com.github.jcrfsuite.example.JcrfsuiteExample example/tweet-pos/pos.txt twitter-pos.model

POS tagging 
... (will be added)