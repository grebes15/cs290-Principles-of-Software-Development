#!/bin/sh

echo
echo "Compiling and mutating project"
echo "(ant -Dmutation=\"=../mml/tutorial.mml.bin\" clean compile)"
echo
../../ant -Dmutation="=../mml/tutorial.mml.bin" clean compile
echo
echo "Compiling tests"
echo "(ant compile.tests)"
echo
../../ant compile.tests
echo
echo "Run tests without mutation analysis"
echo "(ant test)"
../../ant test
echo
echo "Run tests with mutation analysis"
echo "(ant mutation.test)"
../../ant mutation.test
