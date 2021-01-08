# csx42-summer-2020-assign4-sagrawa4

Due Date: Wednesday, July 22, 2020
Submission Date: Wednesday, July 22 , 2020
Slack Days Used for This Project: 0 Day
Slack Days Remaining: 3 Days
Author(s): Shruti Agrawal
e-mail(s): sagrawa4@binghamton.edu

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [arrayvisitors/src](./arrayvisitors/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile arrayvisitors/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile arrayvisitors/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile arrayvisitors/src/build.xml run -Dinput1="input1.txt" -Dinput2="input2.txt" -Dcommonintsout="commonintsout.txt" -Dmissingintsout="missingintsout.txt" -Ddebug="debug.txt"

```
Note: Arguments accept the absolute path of the files.

## Description:

DataStructure used:

Array : Implemneted Abstract Data type representing the array.The ADT includes API for add,get and length.
ArrayList : Another ADT implemented representing ArrayList which maintains MyArrayObjects. The API included in arrayList are add and get.

Operations performed on MyArray : extend Array()=>TimeComplexity : O(n), where n is length of the array.
Operations performed on MyArrayList : add()=> Time Complexity : O(n)
                                    get()=>Time Complexity : O(1)



## Citation:


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [07/22/2020]

