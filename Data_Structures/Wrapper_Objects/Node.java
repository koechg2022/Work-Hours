/**
 * @author Geoffrey Koech
 * @since Thursday 22:13, April 15th, 2021
 * 
 * @date 14/4/2021
 * 
 * @version 0.0
 * 
 * This is the super class for the Node objects 
 * that used in any of the mono-uni-directional
 * Data structures.
 */

package Data_Structures.Wrapper_Objects;
import Data_Structures.Interfaces.NodeInterface;

/**
 * <p>
 * This is a {@linkplain #Node} object.
 * </p>
 * <p>
 * Made of 3 attributes :
 * </p>
 * <ul>
 * <li> {@linkplain #index}
 * <li> {@linkplain} {@link #information}
 * <li> {@linkplain #next}
 * </ul>
 */
public class Node <DataType> implements NodeInterface<DataType>{
    /**
     * This is the first attribute.
     * {@link #information} is a Generic type.
     */
    protected DataType information;

    /**
     * This is the second attribute.
     * {@link #index} is an int.
     */
    protected int index;

    /**
     * This is the third attribute.
     * {@link #next} is the next {@link #Node}.
     */
    protected Node<DataType> next;

    /** How this Constructor builds a {@linkplain #Node}:
    * </p>
    * <ul>
    *  <li>Defined Attributes: NONE</li>
    *  <li>Undefined Attributes: {@link #next}, {@link #index}, {@link #information}</li>
    * </ul>
    * 
    * This Constructor creates a Node
    * with the {@linkplain #information} attribute set to
    * the {@linkplain #data} parameter
    */
    protected Node(){
        //empty
    }

    /** How this Constructor builds a {@linkplain #Node}:
    * </p>
    * <ul>
    *  <li>Defined Attributes: {@link #information}</li>
    *  <li>Undefined Attributes: {@link #next}, {@link #index}.</li>
    * </ul>
    * 
    * This Constructor creates a Node
    * with the {@linkplain #information} attribute set to
    * the {@linkplain #data} parameter
    * 
    * 
    * 
    * @param data will be stored in the {@linkplain #information} attribute.
    */
    protected Node(DataType data){
        this.information = data;
        this.index = 0;
    }

    /**
     * 
     * How this Constructor builds a {@linkplain #Node}:
     * </p>
     * <ul>
     *  <li>Defined Attributes : {@link #information}, {@link #index}.</li>
     *  <li>Undefined Attributes : {@link #next}.</li>
     * </ul>
     * 
     * This Constructor creates a Node
     * with the {@linkplain #information} attribute set to
     * the parameter {@linkplain #data}
     * and the index attribute to {@linkplain #indx}.
     * 
     * @param data will be stored in the {@linkplain #information} attribute.
     * @param indx will be stored in the {@linkplain #index} attribute.
     * 
     */
    protected Node(DataType data, int indx){
        this.information = data;
        this.index = indx;
    }

    /**
     * 
     * How this Constructor builds a {@linkplain #Node}:
     * </p>
     * <ul>
     *  <li>Defined Attributes : {@link #information}, {@link #index}, {@link #n}.</li>
     *  <li>Undefined Attributes : NONE.</li>
     * </ul>
     * 
     * This Constructor creates a Node
     * with the {@linkplain #information} attribute set to
     * the parameter {@linkplain #data}, 
     * the {@linkplain #n} attribute will be set to the
     * parameter {@link #n}
     * and the index attribute to {@linkplain #indx}.
     * 
     * @param data will be stored in the {@linkplain #information} attribute.
     * @param indx will be stored in the {@linkplain #index} attribute.
     * @param n will be used to set the {@linkplain #next} attribute.
     */
    protected Node(DataType data, Node<DataType> n, int indx){
        this.information = data;
        this.index = indx;
        this.next = n;
        next.index = indx + 1;
    }


    /**
     * 
     * How this Constructor builds a {@linkplain #Node}:
     * </p>
     * <ul>
     *  <li>Defined Attributes : {@link #information}, {@link #index}, {@link #n}.</li>
     *  <li>Undefined Attributes : NONE</li>
     * </ul>
     * 
     * This Constructor creates a Node
     * with the {@linkplain #information} attribute set to
     * the parameter {@linkplain #data}
     * and the index attribute to {@linkplain #indx}.
     * 
     * @param data will be stored in the {@linkplain #information} attribute.
     * @param indx will be stored in the {@linkplain #index} attribute.
     * @param n will be used to set the {@linkplain #next} attribute.
     * 
     */
    protected Node(DataType data, DataType n, int indx){
        this.information = data;
        this.index = indx;
        this.next.information = n;
        this.next.index = indx + 1;
    }

    @Override
    public int getIndex(){
        return this.index;
    }

    @Override
    public void setIndex(int indx) {
        this.index = indx;
    }

    @Override
    public void setInformation(DataType data) {
        this.information = data;
    }

    @Override
    public DataType getInformation() {
        return this.information;
    }

    @Override
    public void setNext(Node<DataType> n) {
        this.next = (Node<DataType>) n;
    }

    @Override
    public Node<DataType> getNext() {
        return this.next;
    }
}
