# CSX42: Assignment 2
**Name:Shruti Agrawal**

Due Date: Wednesday, June 24, 2020
Submission Date: Thursday, June 25 , 2020
Slack Days Used for This Project: 1 Day
Slack Days Remaining: 4 Days
Author(s): Shruti Agrawal
e-mail(s): sagrawa4@binghamton.edu

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [06/25/2020]


