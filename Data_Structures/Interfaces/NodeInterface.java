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

package Data_Structures.Interfaces;

import Data_Structures.Wrapper_Objects.Node;


/**
 * <p>
 * This is a {@linkplain #Node} object.
 * </p>
 * <p>
 * Made of 3 attributes :
 * </p>
 * <ul>
 * <li> {@linkplain #index}
 * <li> {@linkplain #information}
 * <li> {@linkplain #next}
 * </ul>
 */
public interface NodeInterface <DataType>{
    
    /**
     * This method retrieves the index of this Node.
     * @return {@link #index}
     */
    public int getIndex();

    /**
     * This method sets the index of this {@link #Node}
     * to the passed in value of {@link #indx}
     * @param indx : This parameter is what this index is being set to.
     */
    public void setIndex(int indx);

    /**
     * This method sets the {@linkplain #information}
     * attribute to the value of {@linkplain #data}
     * 
     * @param data : This is the parameter of the data this node is to carry
     */
    public void setInformation(DataType data);

    /**
     * This method retrieves the {@linkplain #information}
     * from the current {@link #Node}
     * @return {@linkplain #information} attribute
     */
     public DataType getInformation();

    /**
     * This method sets the {@linkplain #next}
     * attribute to the value of {@linkplain #n}
     * @param n : The {@linkplain #Node} parameter
     * that this {@linkplain #Node}'s {@linkplain #next} attribute is set to.
     */
    public void setNext(Node<DataType> n);

    /**
     * This method returns the next {@link #Node}
     * attribute of this {@linkplain #Node}
     * @return : The {@link #next} attribute
     */
    public NodeInterface<DataType> getNext();
}
