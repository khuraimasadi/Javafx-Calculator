package application;
class Stack{
	static Node head=null;
	static class Node{
		String value;
		Node next;
		Node(String value){
			this.value=value;
			next=null;
		}	
}
	//isEmpty
	public static boolean isEmpty() {
		return head==null;
	}
	//push
	public static void push(String data) {
		Node node =new Node(data);
		if(head==null) {
			head=node;
			return;
		}
		node.next=head;
		head=node;
	}
	
	//bottomPush
		public static void bottomPush(String data) {
			if(isEmpty()) {
				push(data);
				return;
			}
			String top=pop();
			bottomPush( data);
			push(top);
		}
	//pop
	public static String pop() {
		
		if(head==null) {
		return "-1";
		}
		String top=head.value;
		head=head.next;
		return top;
	}
	
	//peek
	public static String peek() {
		
		if(head==null) {
			return "-1";
			}
		 String top=head.value;
		 return top;
	}
	//print
	
	public static void print() {
		while(!isEmpty()) {
			System.out.println(Stack.pop());
		}
	}
	//printPeek
	
			public static void printPeek() {
				printPeek1(head);
			}
	//printPeek1
	
		public static void printPeek1(Node temp) {
			if(temp.next==null) {
				System.out.println( temp.value);
				return;
			}
			String s= temp.value;
			System.out.println(s);
			printPeek1(temp.next);
			push(s);
		}
	
	
	//reversPrint
	
		public static void reversPrint() {
			if(isEmpty()) {
				return;
			}
			String top=pop();
			reversPrint() ;
			System.out.println(top);
			push(top);
		}
		
		//reverse
		
			public static void reverse() {
				if(isEmpty()) {
					return;
				}
				String top=pop();
				reverse() ;
				bottomPush(top);
			}
}


public class StackWithLinkedList {
public static void main(String[]args) {
	Stack.push("my");
	Stack.push("name");
	Stack.push("is");
	Stack.push("Abdullah");
	Stack.bottomPush("G");
	Stack.reverse();
	Stack.print();
	
}}
