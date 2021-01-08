package studentskills.mytree;

import studentskills.mytree.Operations;

public interface ObserverI
{
    // For operation = Insert this is new node data
    // For operation = Modify this is data that needs to be modified.
    public void update(StudentRecord node, Operations operations);
}