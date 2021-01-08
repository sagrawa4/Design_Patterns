package studentskills.mytree;

import studentskills.mytree.Operations;

public interface SubjectI
{
    public void registerObserver(ObserverI o);
    
    // We do not have unregisterObserverMethod as we are not deleting 
    // nodes from the tree.

    // Do we need to create interface for Message ? l
    // like Message -> StudentRecord
    public void notifyObservers(StudentRecord node, Operations operations);
}