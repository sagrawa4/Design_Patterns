package studentskills.mytree;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

/* This class implements interfaces SubjectI and ObserverI and Cloneable
* and implements registerObserver and notifyObservers methods
* @author Shruti Agrawal
*/
public class StudentRecord implements SubjectI, ObserverI, Cloneable
{
    private int bNumber;
    private String firstName;
    private String lastName;
    private double gpa;
    private String major;
    private StudentRecord left = null;
    private StudentRecord right = null;
    private ArrayList<ObserverI> observers;
    private Set<String> skills =new TreeSet();

    // We should use set to store all the skills 
    // so we have unique values.

    public StudentRecord(int bNumberInput, String firstNameInput, String lastNameInput,double gpaInput,String majorInput,Set<String> skillInput)
    {
        this.bNumber = bNumberInput;
        this.firstName = firstNameInput;
        this.lastName = lastNameInput;
        this.gpa = gpaInput;
        this.major = majorInput;
        this.skills = skillInput;
        observers= new ArrayList<ObserverI>();
    }

    public ArrayList<ObserverI> getAllObservers()
    {
        return observers;
    }

    public Set<String> getSkills()
    {
        return skills;
    }

    public boolean addSkill(String skill)
    {
        return skills.add(skill);
    }

     public boolean removeSkill(String skill)
    {
        return skills.remove(skill);
    }

    public void setSkills(Set<String> skills)
    {
        this.skills= skills;
    }
    // https://dzone.com/articles/shallow-and-deep-java-cloning
    // Does this copy the observers array list as well ?
    // Does this copy the skills set as well ?
    @Override
    public StudentRecord clone() throws CloneNotSupportedException {
        StudentRecord deepCopy = new StudentRecord(this.bNumber, this.firstName, this.lastName,this.gpa,this.major,this.skills);
        return deepCopy;
    }

    public int getBnumber()
    {
        return bNumber;
    }

    public void setBnumber(int bNumberInput)
    {
        bNumber=bNumberInput;
    }

    public void setFirstName(String inputFirstName)
    {
        firstName = inputFirstName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setLastName(String inputLastName)
    {
        lastName = inputLastName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public double getGpa()
    {
        return gpa;
    }

    public void setGpa(double gpaInput)
    {
        gpa=gpaInput;
    }

     public String getMajor()
    {
        return major;
    }

    public void setMajor(String majorInput)
    {
        major=majorInput;
    }

    public StudentRecord getLeft()
    {
        return this.left;
    }

    public StudentRecord getRight()
    {
        return this.right;
    }

    public void setLeft(StudentRecord node)
    {
        this.left = node;
    }

    public void setRight(StudentRecord node)
    {
        this.right = node;
    }

    public String toString()
    {
        String outputString = "";

        outputString += bNumber + ":" + firstName + ":" + lastName + ":" + gpa + ":" + major + ":" +  skills.toString();
        //System.out.println("Observers are " + observers);    
        return outputString;
    }

    /* Subject Interface method... */
    public void registerObserver(ObserverI newObserver)
    {
        observers.add(newObserver);
    }
    
    public void notifyObservers(StudentRecord updateNode, Operations operation)
    {
        for(int i = 0; i< observers.size(); i++)
        {
            observers.get(i).update(updateNode, operation);
        }
    }

    /* Observer Interface method...*/
    public void update(StudentRecord updateNode, Operations operation)
    {
        /* If it is an INSERT OR MODIFY we change firstName, lastName, 
        * gpa, major, skills to input Node skills.
        * There is no custome logic required for insert or modify at this 
        * level.
        */
        if(operation == Operations.INSERT || operation == Operations.MODIFY)
        {
            if(this.bNumber != updateNode.getBnumber())
            {
                throw new RuntimeException("bNumbers for clone nodes cannot be differnet.");
            }
            this.firstName = updateNode.getFirstName();
            this.lastName = updateNode.getLastName();
            this.gpa = updateNode.getGpa();
            this.major = updateNode.getMajor();
            for (String skill : updateNode.getSkills()) {
                this.addSkill(skill);
            }
        }
    }
    
}