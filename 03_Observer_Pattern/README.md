# csx42-summer-2020-assign3-sagrawa4
# CSX42: Assignment 3
**Name:Shruti Agrawal**

Due Date: Friday, June 10, 2020
Submission Date: Saturday, June 11 , 2020
Slack Days Used for This Project: 1 Day
Slack Days Remaining: 3 Days
Author(s): Shruti Agrawal
e-mail(s): sagrawa4@binghamton.edu

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [studentskills/src](./studentskills/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Doutput1="output1.txt" -Doutput2="output2.txt" -Doutput3="output3.txt" -Derror="error.txt" -Ddebug="debug.txt"

```
Note: Arguments accept the absolute path of the files.


## Description:
Applied the design principles(Observer Pattern) to develop a software which is a Replica System for Student Records.

The tree datasturcture used by me for the assignment is a BST(Binary searxh tree).It is because searching a node in BST is quicker and easy. Also TreeSet datasturcture is used to store skills as it wont allow duplication of values.

Time complexity of BST: For Average case :  Insert: O(logn) Search: O(logn).
For (Worst case). :   Insert: O(n) Search: O(n) where n is height of tree

## Citation:

//https://www.gontu.org/how-to-insert-node-in-binary-search-tree/#tabs-4960-0-0
//https://dzone.com/articles/shallow-and-deep-java-cloning 

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [06/11/2020]


