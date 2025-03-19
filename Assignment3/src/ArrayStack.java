/*
CS 1027B Assignment 2
Name: Felix Ma
Student Number: 251413859
Email: fma56@uwo.ca
Created: March 19, 2025
*/

public class ArrayStack<T> implements StackADT<T> {
	//Instance Variables
	private T[] array;
	private int top;
	
	//Constructors
	public ArrayStack() {
		this(10); //Initialize with capacity of 10
	}
	public ArrayStack(int initCapacity) {
		this.array = (T[]) new Object[initCapacity];
		this.top = initCapacity - 1;
	}
	
	//Methods
	//Push
	@Override
	public void push(T element) {
		//If full
		if(top == -1) {
			expandCapacity();
		}
		array[top] = element;
		top--;
	}
	
	//Pop
	@Override
	public T pop() throws CollectionException {
		//If empty
		if(this.isEmpty()) {
			throw new CollectionException("Stack is empty");
		}
		//Update top
		top++;
		//Remove and return top of stack
		T result = array[top];
		array[top] = null;
		return result;
	}
	
	//Peek
	@Override
	public T peek() throws CollectionException {
		//If empty
		if(isEmpty()) {
			throw new CollectionException("Stack is empty");
		}
		return array[top+1];
	}
	
	//Checks if empty
	@Override
	public boolean isEmpty() {
		if(top == array.length-1) {
			return true;
		}
		return false;
	}
	
	//Gets the size of ArrayStack
	@Override
	public int size() {
		return array.length - 1 -top;
	}
	
	//Gets array length
	public int getCapacity() {
		return array.length;
	}
	
	//Gets top value
	public int getTop() {
		return this.top;
	}
	
	//Converts to String
	public String toString() {
		//Is empty
		if(this.isEmpty()) {
			return "Empty stack.";
		}
		String result = "";
		for(int i = top + 1 ; i < array.length; i++) {
			result+=array[i];
			if(i < array.length-1) {
				result+=", ";
			}
		}
		return result;
	}
	//Expands Array Capacity
	private void expandCapacity() {
		int currCapacity = getCapacity();
		int newCapacity = 0;
		
		if(top == -1) {
			if(this.size() <= 15) {
				newCapacity = currCapacity*2;
			}else {
				newCapacity = currCapacity+10;
			}
		}
		
		T[] newArray = (T[]) new Object[newCapacity];
		int newTop = newCapacity - 1;
		
		//Adds the elements to the new array
		for(int i = array.length - 1; i > top ; i--) {
			newArray[newTop] = array[i];
			newTop--;
		}
		
		//Update the instance variables
		array = newArray;
		top = newTop;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
