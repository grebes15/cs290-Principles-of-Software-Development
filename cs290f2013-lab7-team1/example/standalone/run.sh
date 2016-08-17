#!/bin/sh

echo "- Running Major without mutation"
echo "  (javac triangle/Triangle.java)"
../../javac triangle/Triangle.java
echo
echo "- Running Major with mutation"
echo "  (javac -XMutator=\"../mml/tutorial.mml.bin\" triangle/Triangle.java)"
../../javac -XMutator="../mml/tutorial.mml.bin" triangle/Triangle.java
echo
echo "- Compiling test case (config.jar has to be on the classpath!)"
echo "  (javac -cp .:../../config/config.jar TriangleTest.java)"
../../javac -Xlint:unchecked -cp .:../../config/config.jar TriangleTest.java
echo
echo "- Executing test case (config.jar has to be on the classpath!)"
echo "  (java -cp .:../../config/config.jar TriangleTest)"
echo
java -cp .:../../config/config.jar TriangleTest
