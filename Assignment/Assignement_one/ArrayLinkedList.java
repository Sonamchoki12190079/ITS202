import java.util.*;
public class ArrayLinkedList
{
	//initialized and declared the variable
	double full_1by4 =0.25;
	double full_3by4 = 0.75;
	int demension = 4;
	static int x;
	int delete;
	int first = 0;
	static double length;
	int arr[];
	public ArrayLinkedList()
	{
		arr = new int[demension];
	}
	//This function will add new element in the array
	public void add(int element)
	{
		arr[first] = element;
		first++;
	}
	//This function will delete  the last element of the array
	public void pop()
	{
		first--;
		delete = arr[first];
		arr[first] = 0;
		int count = 0;
		for(int i = 0; i < arr[i]; i++) {
			count++;
		}
		x = count;
		length = (double)x/demension;
		System.out.println("The delete element is: " +delete);
	}
	//this function will resize the size of array to double if array is full by 75% and half if 25% full
	public void resize()
	{
		if(length == full_1by4 ) {
			int newArray[] = new int[(demension/2)*2];
			for(int i = 0; i < arr.length; i++) {
				newArray[i] = arr[i];
			}
			arr = newArray;
			demension = demension/2;
			for(int y : newArray) {
				System.out.print(y + " ");
				}	
		}
		else if(length == full_3by4) {
			int newArray[] = new int[demension*2];
			for(int i = 0; i < arr.length; i++) {
				newArray[i] = arr[i];
			}
			demension= demension * 2;
			arr = newArray;
			for(int j : newArray) {
			System.out.print(j + "\n");
			}
			System.out.println();
		}
		else {
			System.out.println("ArrayIndexOutOfBoundRange");
		}
	}
	public int size()
	{
		return demension;
	}
	public String toString()
	{
		String s = Arrays.toString(arr);
		return s;
	}
	public static void main(String[] args) {
		ArrayLinkedList s = new ArrayLinkedList();
		s.add(1);
		s.add(2);
		s.add(4);

		s.add(8);
//		
		s.pop();//removing the last element of the array
		s.resize();
		System.out.println("A toString number is " +s.toString());
		System.out.println("The number of elements in the new Array is " +x);
		System.out.println("The Size of new Array is " +s.size());

		assert(s.size() == 4);
		System.out.println("All the Function are working");

	}
}