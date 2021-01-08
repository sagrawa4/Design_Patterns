package studentskills.mytree;

/* This class declares the attributes used in modify file and define them*/
// TODO: Write Getter and Setters.
public class ModifyRecord
{
    public int replicaID;
    public int bNumber;
    public String oldValue;
    public String newValue;

    /* Constructor
    * @param int,int,String,String
    * @return nothing
    */
    public ModifyRecord(int replicaID, int bNumber, String oldValue, String newValue){
        this.replicaID = replicaID;
        this.bNumber = bNumber;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
};
