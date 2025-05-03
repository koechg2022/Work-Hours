package Data_Structures.Wrapper_Objects;

public class Qnode <DataType> extends Node<DataType>{
    
    protected Node<DataType> previous;

    
    public Qnode(){
        super();
    }

    public Qnode(DataType data){
        super(data);
        this.previous = null;
    }

    
    public Qnode(DataType data, int indx){
        super(data, indx);
        this.previous = null;
    }

    public Qnode(DataType data, Node<DataType> n, int indx){
        super(data, n, indx);
        this.previous = null;
    }

    public Qnode(DataType data, DataType n, int indx){
        super(data, n, indx);
        this.previous = null;
    }

    public void setPrevious(Node<DataType> prev){
        this.previous = prev;
    }

    /**
     * This method returns the previous {@link #Node}
     * attribute of this {@linkplain #Node}
     * @return : The {@link #previous} attribute
     */
    public Node<DataType> getPrevious(){
        return this.previous;
    }

}
