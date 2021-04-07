# 361-Project-2

* Author: Bethany Hull and Marie Phelan
* Class: CS361 Section 1
* Semester: Spring 2021

## Overview

This program creates a deterministic finite automata that is equivalent to a given nondeterministic finite automata. The program takes in the final states, states that are not final or start states, and the start state on three separate lines. It then takes in the transitions. Finally, it takes strings to check if they are a valid part of that NFA's language. After converting the NFA to a DFA, the program prints the 5-tuple and a "no" or "yes" depending on if each string is a valid string in the language or not.

## Compiling and Using

This program requires a .txt file formatted in the following way:
> names of the final states separated by white space
> name of states that are neither final nor initial
> name of the start state
> transitions formatted as start state, transition, end state without spaces . Ex. a0b 
> all additional lines are strings to be tested

Compile and run the program with the following commands from the main project folder:
```bash
javac fa/dfa/NFADriver.java
java fa.dfa.NFADriver <.txt file path>
```

## Discussion

This project was definitely more complex than the last one. We were able to create the NFA relatively easily.
Creating the eClosure method was more complex but not too difficult. We ran into the most trouble when writing
the getDFA() method and fixing bugs. We realized that parts of the implementation for the NFA would have to be 
changed to properly get the DFA. One small issue that caused a lot of problems was that the HashMap of transitions
had the values as NFAState rather than a set of NFAStates. Since maps cannot have one key for multiple values, this
caused problems. This was an easy fix though, and it made the getTo() method much simpler. The getDFA() method was
difficult because it involved a while loop, multiple for loops, and several if statements. This made it difficult to 
keep organized. While writing the getDFA() method, we knew we would need to check if multiple sets of states
were equivalent but in a different order. We eventually converted our Linked Hash Sets to Tree Sets to keep them ordered. 
We figured out the build path, so we were able to run the program in Eclipse (since in the last project we were only
able to run on the command line). Overall, this project went well. We realized that we often made things more complicated
than they needed to be. We also found that it helped a lot to take breaks after a certain amount of time working on the 
same errors. There were several times that we made big breakthroughs when we spent a bit of time away. 

## Testing

To test our project, we compared the test file outputs to those found in the project 
outline. We also used test cases given in Piazza for additional testing. 
We also had many print statements to figure out where things were going wrong.

## Sources used

Java API Documentation (https://docs.oracle.com/javase/7/docs/api/)

Geeks for Geeks (https://www.geeksforgeeks.org)

Stack Overflow (https://stackoverflow.com/)
