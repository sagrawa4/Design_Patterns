# csx42-summer-2020-assign5-sagrawa4

**Name:Shruti Agrawal**

Due Date: Wednesday, August 5, 2020
Submission Date: Saturday, August 8, 2020
Slack Days Used for This Project: 3 Day
Slack Days Remaining: 0
Author(s): Shruti Agrawal
e-mail(s): sagrawa4@binghamton.edu

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Dmisspelled="misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="debug.txt"

```
Note: Arguments accept the absolute path of the files.


## Description:

The Datastructure used to store the words from the input file is ArrayList as Arraylist are good to do fast index search and insert at a given position.so basis the given project description we were required to replace the old string word with a new word basis the diferrent decorator. Time Complexity for search in an ArrayList : O(n) Worst Case(where n is the size of arraylist)
Insertion in Arraylist ime complexity : O(n) Worst Case(where n is the size of arraylist)


## Citation:

//https://www.geeksforgeeks.org/count-occurrences-elements-list-java/

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [08/08/2020]

