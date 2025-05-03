package Data_Structures.Interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import Data_Structures.Data_Structures.*;
public interface QueInterface <Data>{
    
    /**
     * This method checks to see if the 
     * Que is empty.
     * @return : {@link #True} if the que is empty and false otherwise.
     */
    public boolean empty();

    /**
     * This method returns the size of the que.
     * @return : The total number of indexes in the queue.
     */
    public int getSize();

    /**
     * This method adds the new data {@link #newData}
     * to the Que.
     * @param newData : This is the data to be added to the queue.
     * @exception IllegalArgumentException if the parameter {@linkplain #newData} is NULL.
     */
    public void push(Data newData);

    /**
     * This method adds the new data {@link #newData}
     * to the Que at the {@link #index} passed in.
     * @param newData
     * @param index
     * @exception IllegalArgumentException if the parameter {@linkplain #newData} is NULL.
     * @exception IndexOutOfBoundsException if the parameter {@linkplain #indx} is less than 0.
     */
    public void push(Data newData, int indx);

    /**
     * This method checks what data is stored at the front of the que.
     * It does NOT delete the {@link #Node} though.
     * <ul>
     * <li> If the que IS NOT empty, the data at the front is returned.
     * <li> If the que IS empty, an exception is thrown.
     * </ul>
     * @return The data at the front of the que.
     * @exception NullPointerException if the {@link #Que} is empty.
     */
    public Data peek();

    /**
     * This method returns the data that is stored at the {@link #indx}
     * passed in. It does NOT delete the {@link #Node} at {@link #indx}
     * though.
     * @param indx The index in the stack to be accessed.
     * @return The Data that is at the index of the que being accessed.
     * @exception IndexOutOfBoundsException if the {@link #indx} is greater
     * {@link #size} or less than 0.
     */
    public Data peek(int indx);

    /**
     * This method checks what data is stored at the front of the que, removes it from the que,
     * and returns the data.
     * <ul>
     * <li> If the que IS NOT empty, the data at the front is returned.
     * <li> If the que IS empty, an exception is thrown.
     * </ul>
     * @return The data at the front of the que.
     * @exception NullPointerException if the {@link #Que} is empty.
     */
    public Data pull();

    /**
     * This method returns the data that is stored at the {@link #indx}
     * passed in, returns that data, and deletes it from the queue.
     * @param indx The index in the stack to be accessed.
     * @return The Data that is at the index of the que being accessed and deleted.
     * @exception IndexOutOfBoundsException if the {@link #indx} is greater
     * {@link #size} or less than 0.
     */
    public Data pull(int indx);

    /**
     * This method resets the que to empty.
     */
    public void reset();

    /**
     * This method reverses the order of the list. The 
     * last piece of data will become the first piece of
     * data, and the first bit of data will become the last
     * bit of data.
     */
    public void reverse();

    /**
     * This method saves the data inside of the que to the 
     * file passed in.
     * @param file The name of the file that the data is to be saved to.
     * @throws FileNotFoundException If the file passed in is not found.
     */
    public void saveToFile(File file) throws FileNotFoundException;

    /**
     * This method changes the data at the first index of the que to the {@link #newData} passed in
     * @throws NullPointerException If the que is empty.
     * @throws IllegalArgumentException If the data passed, {@link #newData} in is null.
     */
    public void change(Data newData) throws NullPointerException, IllegalArgumentException;

    /**
     * 
     * @param index The index of the data you want to change. This must be between 0 and the last index of the queue.
     * @throws IndexOutOfBoundsException Thrown if the index passed in is less than 0, or greater than the last index of the que. (size - 1)
     * @throws NullPointerException Thrown if the que is empty
     * @throws IllegalArgumentException If the new data passed in, {@link #newData}, is null.
     */
    public void changeAt(Data newData, int index) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException;

    /**
     * This method switches the first and last indexes of the data.
     * @throws NullPointerException Throws a null pointer exception if the que is empty.
     */
    public void switchData() throws NullPointerException;

    /**
     * This method switches the data at {@link firstIndex} with the data at the {@link otherIndex}.
     * @param firstIndex The index with the data you want to swap with the data at {@link otherIndex}.
     * @param otherIndex The index with the other data you want to swap with the {@link firstIndex}
     * @throws IndexOutOfBoundsException If {@link firstIndex} or {@link otherIndex} is less than 0 or greater than or equal to the size of the que.
     * @throws NullPointerException If the que is empty.
     */
    public void switchData(int firstIndex, int otherIndex) throws IndexOutOfBoundsException, NullPointerException;

    /**
     * This method takes a que and attaches it to the current que. 
     * @param extraQue The {@link Que} to be added to the {@link Que}. An Exception is thrown if this que is empty
     * @throws IllegalArgumentException Thrown if the {@link extraQue} is empty
     */
    public void attachQue(Que<Data> extraQue) throws IllegalArgumentException;

    /**
     * This method removes the data at the first index of the {@link Que}.
     * @throws NullPointerException If the {@link Que} is empty.
     */
    public void remove() throws NullPointerException;

    /**
     * This method removes the data at the {@link index} passed in.
     * The {@link index} must be between 0 and the ({@link size} - 1) index of the {@link Que}.
     * @param index The index with the data to be removed.
     * @throws NullPointerException If the {@link Que} is empty
     * @throws IndexOutOfBoundsException If the index is less than 0, or greater than the last index of the {@link Que} {@link size - 1}.
     */
    public void remove(int index) throws NullPointerException, IndexOutOfBoundsException;
}