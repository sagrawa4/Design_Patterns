package studentskills.mytree;

import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;

/* This class builds each tree recursively.*/
public class TreeHelper
{
    
    private TreeI primaryTree = new BST();
    private TreeI replicaTree1 = new BST();
    private TreeI replicaTree2 = new BST();
    
    public TreeHelper(){}

    public void insertTree(StudentRecord node) throws CloneNotSupportedException
    {
        // First we check if input node exist in primary tree
        StudentRecord existingNodeInPrimaryTree = primaryTree.findNode(node.getBnumber());

        // If node does not exists we have to insert.
        if(existingNodeInPrimaryTree == null)
        {   
            //  1. Create 2 clone nodes
            StudentRecord primaryNode = node;
            StudentRecord replicaNode1 = node.clone();
            StudentRecord replicaNode2 = node.clone();
    
            //  2. Register correct observers for each of the nodes

            // replicaNode1 and replicaNode2 are observers for primaryNode
            primaryNode.registerObserver(replicaNode1);
            primaryNode.registerObserver(replicaNode2);

            // primaryNode and replicaNode2 are observers for replicaNode1
            replicaNode1.registerObserver(primaryNode);
            replicaNode1.registerObserver(replicaNode2);

            // primaryNode and replicaNode1 are observers for replicaNode2
            replicaNode2.registerObserver(primaryNode);
            replicaNode2.registerObserver(replicaNode1);
                    
            // All nodes observers are correctly registered, now we have to insert
            // these nodes in tree
            primaryTree.insertNode(primaryNode);
            replicaTree1.insertNode(replicaNode1);
            replicaTree2.insertNode(replicaNode2);
        }
        else 
        {
            // If node already exists then we have to override the value. 
            existingNodeInPrimaryTree.setFirstName(node.getFirstName());
            existingNodeInPrimaryTree.setLastName(node.getLastName());
            existingNodeInPrimaryTree.setGpa(node.getGpa());
            existingNodeInPrimaryTree.setMajor(node.getMajor());

            // Note that for skills, insert should only add to the existing set, 
            // and not delete any existing skill.
            for (String skill : node.getSkills()) {
                existingNodeInPrimaryTree.addSkill(skill);
            }
            existingNodeInPrimaryTree.notifyObservers(existingNodeInPrimaryTree, Operations.INSERT);
        }
    }

    public void modifyTree(ModifyRecord node) throws CloneNotSupportedException
    {
        // Read the replicateID from input node and then
        // search for node bNumber in respective tree replica        
        StudentRecord existingNode = null;
        if(node.replicaID == 0)
        {
            
            existingNode = primaryTree.findNode(node.bNumber);
            
        }
        else if(node.replicaID == 1)
        {
            existingNode = replicaTree1.findNode(node.bNumber);
        }
        else if(node.replicaID == 2)
        {
            existingNode = replicaTree2.findNode(node.bNumber);
        }

        // Cannot find request node in tree, so we will skip.
        if(existingNode == null)
        {
            MyLogger.writeMessage("ERROR! Requested node not found" , MyLogger.DebugLevel.TREE_HELPER);
            System.out.println("ERROR! Requested node not found");
            return;
        }
        
        //// Node found, now we will find attribute and then replace it
        if(existingNode.getFirstName().equals(node.oldValue))
        {
            existingNode.setFirstName(node.newValue);
        }
        if(existingNode.getLastName().equals(node.oldValue))
        {
            
            existingNode.setLastName(node.newValue);
        }
        if(existingNode.getMajor().equals(node.oldValue))
        {
            existingNode.setMajor(node.newValue);
        }
        if(existingNode.getSkills().contains(node.oldValue))
        {
            existingNode.removeSkill(node.oldValue);
            existingNode.addSkill(node.newValue);
        }
        existingNode.notifyObservers(existingNode, Operations.MODIFY);
    }

    public String toStringPrimaryTree()
    {
        return primaryTree.toString();
    }

    public String toStringReplicaTree1()
    {
        return replicaTree1.toString();
    }

    public String toStringReplicaTree2()
    {
        return replicaTree2.toString();
    }  
}