package studentskills.mytree;

import java.util.ArrayList;

//https://www.gontu.org/how-to-insert-node-in-binary-search-tree/#tabs-4960-0-0

public class BST implements TreeI
{
    private StudentRecord root;

    public BST()
    {
        root = null;
    }

    public StudentRecord findNode(int Bnumber)
    {
        return findNodeRec(root, Bnumber);
    }
    
    public StudentRecord findNodeRec(StudentRecord node,int Bnumber)
    {
        if(null==node)
        {
            return null;
        }
        if(node.getBnumber() == Bnumber)
        {
            return node;
        }
        else if(Bnumber<node.getBnumber())
        {
            return findNodeRec(node.getLeft(),Bnumber);
        }
        else 

         return findNodeRec(node.getRight(),Bnumber);
    }
    
    
    public void insertNode(StudentRecord currentNode) 
    {    
        root = insertRec(root, currentNode);   
    }
 
    public StudentRecord insertRec(StudentRecord root, StudentRecord currentNode) 
    {
        if (root == null) 
        {
            root = currentNode;
            return root;
        }
        else if (currentNode.getBnumber() < root.getBnumber()) 
        { 
            root.setLeft(insertRec(root.getLeft(), currentNode));
        } 
        else if (currentNode.getBnumber() > root.getBnumber()) 
        {
            root.setRight(insertRec(root.getRight(), currentNode));
        } 
        else 
        {
            System.out.println("Node with the same value already exists in the BST");
        }
        return root;
    }

    public String toString()
    {
        ArrayList<String> output = new ArrayList<String>();
        toStringNodes(root, output);
        String result = String.join(" ", output);
        return result;
    }

    public void toStringNodes(StudentRecord node, ArrayList<String> output)
    {
        if(node==null)
        {
            return;
        }
        toStringNodes(node.getLeft(), output);
        output.add(node.toString() + "\n");
        toStringNodes(node.getRight(), output);  
    }
}

