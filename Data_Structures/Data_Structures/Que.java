package Data_Structures.Data_Structures;

import Data_Structures.Interfaces.QueInterface;
import Data_Structures.Wrapper_Objects.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class Que <Data> implements QueInterface<Data>{

    protected Qnode<Data> front;
    protected Qnode<Data> back;
    protected int size;

    @Override
    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void push(Data newData) {
        if(newData == null){
            throw new IllegalArgumentException("The data passed in is null");
        }
        Qnode<Data> newNode = new Qnode<Data>(newData);
        if(this.size == 0){
            newNode.setIndex(0);
            this.front = newNode;
            this.back = newNode;
        }
        else if(this.size == 1){
            this.back = newNode;
            this.back.setIndex(this.size);
            this.front.setNext(this.back);
            this.back.setPrevious(this.front);
        }
        else {
            this.back.setNext(newNode);
            newNode.setPrevious(this.back);
            newNode.setIndex(this.size);
            this.back = (Qnode<Data>) this.back.getNext();
        }
        this.size = this.size + 1;
    }

    @Override
    public void push(Data newData, int indx) {
        if(newData == null){
            throw new IllegalArgumentException("The data passed in is null");
        }
        if(indx < 0){
            throw new IndexOutOfBoundsException("The index passed in cannot be less than 0");
        }

        if((this.size == 0) || (indx >= this.size)){
            this.push(newData);
        }

        else {
            this.nodeAdder(this.front, newData, 0, indx);
            this.front = this.nodeShifter(this.front, this.front.getIndex(), 0);
            this.back = this.nodeShifter(this.front, this.front.getIndex(), this.size);
            this.size = this.size + 1;
        }
    }

    @Override
    public Data peek() {
        return this.front.getInformation();
    }

    @Override
    public Data peek(int indx) {
        Qnode<Data> theAnswer;
        if(indx == 0){
            theAnswer = this.front;
        }
        else if(indx == this.size - 1){
            theAnswer = this.back;
        }
        else {
            theAnswer = this.nodeShifter(this.front, 0, indx);
        }
        return theAnswer.getInformation();
    }

    @Override
    public Data pull() {
        if(this.size == 0){
            throw new NullPointerException("The que is empty");
        }
        Data theAnswer = this.front.getInformation();
        if(this.size == 1){
            this.size = 0;
            this.front = null;
            this.back = null;
        }
        if(this.size > 1){
            this.front = (Qnode<Data>) this.front.getNext();
            this.front.setPrevious(null);
            this.indexUpdater(this.front, 0, this.size - 2);
            this.size = this.size - 1;
        }
        return theAnswer;
    }

    @Override
    public Data pull(int indx) {
        Qnode<Data> theNode = this.nodeShifter(this.front, 0, indx);
        Data theAnswer = theNode.getInformation();
        nodeRemover(theNode, theNode.getIndex(), indx);
        return theAnswer;
    }

    @Override
    public void reset(){
        this.size = 0;
        this.front = null;
        this.back = null;
    }

    @Override
    public void reverse(){
        this.reverser(this.front, this.back);
    }

    @Override
    public void saveToFile(File file) throws FileNotFoundException{
        if (file.exists() == false){
            throw new FileNotFoundException("The file passed in was not found.");
        }
        PrintStream writer = new PrintStream(file);
        Qnode<Data> thisNode = this.front;
        while (thisNode != null){
            writer.println(thisNode.getInformation());
            thisNode = (Qnode<Data>) thisNode.getNext();
        }
        writer.close();
    }

    @Override
    public void change(Data newData){
        if (newData == null){
            throw new IllegalArgumentException("The data passed in is null");
        }
        if (this.size == 0){
            throw new NullPointerException("The que is empty. There is no data to be changed");
        }
        this.front.setInformation(newData);
    }

    @Override
    public void changeAt(Data newData, int index){
        if (newData == null){
            throw new IllegalArgumentException("The data passed in is null");
        }
        if (this.size == 0){
            throw new NullPointerException("The que is empty. There is no data to be changed at any index");
        }
        if ((index < 0) || (index >= this.size)){
            String message = " less than 0";
            if (index >= this.size){
                message = "The index passed in, " + Integer.toString(index) + " cannot be greater than" + this.size;
            }
            else {
                message = "The index passed in, " + Integer.toString(index) + " cannot be" + message;
            }
            throw new IndexOutOfBoundsException(message);
        }
        if (index == 0){
            this.change(newData);
        }
        else {
            if (index > this.size / 2){
                this.changeData(index, this.back, newData);
            }
            else{
                this.changeData(index, this.front, newData);
            }
        }
    }

    @Override
    public void switchData() throws NullPointerException {
        
        if(this.size == 0){
            throw new NullPointerException("The que is empty");
        }
        Data frontData = this.front.getInformation();
        Data backData = this.back.getInformation();
        this.change(backData);
        this.changeAt(frontData, this.getSize() - 1);
    }

    @Override
    public void switchData(int firstIndex, int otherIndex) throws IndexOutOfBoundsException, NullPointerException{

        if (((firstIndex < 0) || (otherIndex < 0)) || ((firstIndex >= this.size) || (otherIndex >= this.size))){
            throw new IndexOutOfBoundsException("You passed in an illegal Index : " + "firstIndex : " + firstIndex + ", otherIndex : " + otherIndex + ".\nBoth must be greater than 0 or less than the last index");
        }

        if (this.size == 0){
            throw new NullPointerException("The que is empty");
        }
        if ((firstIndex == 0) && (otherIndex == this.getSize() - 1)){
            this.switchData();
        }
        else {
            Data firstData = this.peek(firstIndex);
            Data otherData = this.peek(otherIndex);
            this.changeAt(otherData, firstIndex);
            this.changeAt(firstData, otherIndex);
        }
    }

    @Override
    public void attachQue(Que<Data> extraQue) throws IllegalArgumentException{
        if(extraQue == null){
            throw new IllegalArgumentException("The que passed in is null");
        }
        if(this.size == 0){
            while(extraQue.empty() == false){
                this.push(extraQue.pull());
            }
        }
        else{
            this.addToEnd(extraQue);
        }
    }

    @Override
    public void remove() throws NullPointerException{
        if(this.size == 0){
            throw new NullPointerException("The Que is empty");
        }
        if(this.size == 1){
            this.reset();
        }
        else {
            this.front = (Qnode<Data>) this.front.getNext();
            this.front.setPrevious(null);
            this.indexUpdater(this.front, 0, this.size - 2);
            this.size = this.size - 1;
        }
    }

    @Override
    public void remove(int index) throws NullPointerException, IndexOutOfBoundsException{
        if(this.size == 0){
            throw new NullPointerException("The Que is empty");
        }
        if((index < 0) || (index > this.size - 1)){
            throw new IndexOutOfBoundsException("The index " + index + " cannot be less than 0 or greater than " + this.size);
        }
        if(index == 0){
            this.remove();
        }
        else if(index == this.size - 1){
            if(this.size == 1){
                this.remove();
            }
            else {
                this.back =(Qnode<Data>) this.back.getPrevious();
                this.back.setNext(null);
            }
            this.size = this.size - 1;
        }
        else {
            this.nodeRemover(this.front, 0, index);
            this.indexUpdater(this.front, 0, this.size - 2);
            this.size = this.size - 1;
        }
    }

    private void addToEnd(Que<Data> otherQue){
        if(otherQue.empty()){
            return;
        }
        else {
            this.push(otherQue.pull());
            this.addToEnd(otherQue);
        }
    }

    private void changeData(int target, Qnode<Data> thisNode, Data newData){
        if (target == thisNode.getIndex()){
            thisNode.setInformation(newData);
        }
        else {
            if (target > thisNode.getIndex()){
                this.changeData(target, (Qnode<Data>) thisNode.getNext(), newData);
            }
            else {
                this.changeData(target, (Qnode<Data>) thisNode.getPrevious(), newData);
            }
        }
    }

    private void reverser(Node<Data> curNode, Node<Data> newFront){
        if(((Qnode<Data>) curNode).getPrevious() == null){
            newFront.setNext(curNode);
            newFront.setIndex(this.size - 1 - curNode.getIndex());
            ((Qnode<Data>) newFront.getNext()).setPrevious(newFront);
            newFront.getNext().setIndex(newFront.getIndex() + 1);
            this.back = (Qnode<Data>)newFront.getNext();
            return;
        }
        else {
            newFront.setNext(curNode);
            ((Qnode<Data>) newFront.getNext()).setPrevious(newFront);
            newFront.setIndex(this.size - curNode.getIndex() - 1);
            reverser(curNode.getNext(), ((Qnode<Data>) newFront).getPrevious());
        }
    }

    private void indexUpdater(Node<Data> curNode, int startIndx, int endIndx){
        if(startIndx == endIndx){
            curNode.setIndex(startIndx);
        }
        else {
            if(startIndx < endIndx){
                curNode.setIndex(startIndx);
                this.indexUpdater(curNode.getNext(), startIndx + 1, endIndx);
            }
            else {
                curNode.setIndex(startIndx);
                this.indexUpdater(((Qnode<Data>) curNode).getPrevious(), startIndx - 1, endIndx);
            }
        }
    }

    private void nodeAdder(Qnode<Data> curNode, Data newData, int curIndx, int target){
        if(curIndx == target){

            Qnode<Data> newNode = new Qnode<>(newData);
            newNode.setIndex(curIndx);
            newNode.setNext(curNode);
            if (curNode.getPrevious() != null){
                newNode.setPrevious(curNode.getPrevious());
                newNode.getPrevious().setNext(newNode);
            }
            curNode.setPrevious(newNode);
            this.indexUpdater(newNode, curIndx, this.size);
        }
        else {
            nodeAdder((Qnode<Data>)curNode.getNext(), newData, curIndx + 1, target);
        }
    }

    private Qnode<Data> nodeShifter(Node<Data> curNode, int curIndx, int targetIndx){
        if(curIndx == targetIndx){
            return (Qnode<Data>)curNode;
        }
        else {
            if(curIndx < targetIndx){
                return nodeShifter(curNode.getNext(), curIndx + 1, targetIndx);
            }
            else {
                return nodeShifter(((Qnode<Data>) curNode).getPrevious(), curIndx - 1, targetIndx);
            }
        }
    }

    private void nodeRemover(Node<Data> curNode, int curIndx, int target){
        if(curIndx == target){
            if(curNode.getNext() == null){
                curNode = ((Qnode<Data>) curNode).getPrevious();
                curNode.setNext(null);
                this.back = (Qnode<Data>) curNode;
            }
            else if(((Qnode<Data>) curNode).getPrevious() == null){
                curNode = curNode.getNext();
                ((Qnode<Data>) curNode).setPrevious(null);
                this.indexUpdater(curNode, 0, this.size - 2);
            }
            else {
                curNode = ((Qnode<Data>) curNode).getPrevious();
                curNode.setNext(curNode.getNext().getNext());
                ((Qnode<Data>) curNode.getNext()).setPrevious(curNode);
                this.indexUpdater(curNode, curNode.getIndex(), this.size - 2);
            }
            this.size = this.size - 1;
        }
        else {
            if(curIndx < target){
                this.nodeRemover(curNode.getNext(), curIndx + 1, target);
            }
            else {
                this.nodeRemover(((Qnode<Data>) curNode).getPrevious(), curIndx - 1, target);
            }
        }
    }
    
}
