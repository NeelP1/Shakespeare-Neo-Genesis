Shakespeare Genetic Algorithm
=============================

Background
----------
"The infinite monkey theorem states that a monkey hitting keys at random on a typewriter keyboard for an infinite amount of time will almost surely type a given text, such as the complete works of William Shakespeare."
- http://en.wikipedia.org/wiki/Infinite_monkey_theorem

"In the field of artificial intelligence, a genetic algorithm (GA) is a search heuristic that mimics the process of natural selection. This heuristic (also sometimes called a metaheuristic) is routinely used to generate useful solutions to optimization and search problems.[1] Genetic algorithms belong to the larger class of evolutionary algorithms (EA), which generate solutions to optimization problems using techniques inspired by natural evolution, such as inheritance, mutation, selection, and crossover."
- http://en.wikipedia.org/wiki/Genetic_algorithm


ShakespeareGA program
---------------------
The programs objective is to use the genetic algorithm technique in order to reach the solution. The solution will be different each time as the program uses psuedo-artificial intelligence in order to find the solution. Genetic algorithms will perform optimization quicker than a brute force method.

The program has two options to choose from:
1. Give a string for program to replicate
2. Produces English sentences using a genetic algorithm with 1000 most common words

Build
=====

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "ShakespeareGA.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.


Run
===

Arguments
---------
args[0] - 1 (option 1)
		- 2 (option 2)
args[1] - filename of target solution (option 1)
		- length of string (option 2)
args[2] - maximum number of generations
