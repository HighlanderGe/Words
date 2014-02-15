package wordquizz;
/* SList.java */

/**
 *  The SList class is a singly-linked implementation of the linked list
 *  abstraction.  SLists are mutable data structures, which can grow at either
 *  end.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 **/

public class SList {
	private SListNode head;
	private SListNode tail;
	private int size;
	
	/**
	   *  SList() constructs an empty list.
	   **/

	  public SList() {
	    size = 0;
	    head = null;
	    tail = null;
	  }

	  /**
	   *  isEmpty() indicates whether the list is empty.
	   *  @return true if the list is empty, false otherwise.
	   **/

	  public boolean isEmpty() {
	    return size == 0;
	  }

	  /**
	   *  length() returns the length of this list.
	   *  @return the length of this list.
	   **/

	  public int length() {
	    return size;
	  }
	  
	  /**
	   *  insertFront() inserts item "obj" at the beginning of this list.
	   *  @param obj the item to be inserted.
	   **/

	  public void insertFront(Object obj) {
	    SListNode tempNode = new SListNode(obj);
	    if(size == 0){
	      head = tempNode;
	      tail = tempNode;
	     } else {
	      tempNode.next = head;
	      head.prev = tempNode;
	      head = tempNode;
	      }
	    size++;
	  }

	  /**
	   *  insertEnd() inserts item "obj" at the end of this list.
	   *  @param obj the item to be inserted.
	   **/

	  public void insertEnd(Object obj) {
	    SListNode tempNode = new SListNode(obj);
	    if(size == 0){
	      head = tempNode;
	      tail = tempNode;
	     } else {
	      tempNode.prev = tail;
	      tail.next = tempNode;
	      tail = tempNode;
	     }
	    size++;
	  }

	  /**
	   *  nth() returns the item at the specified position.  If position < 1 or
	   *  position > this.length(), null is returned.  Otherwise, the item at
	   *  position "position" is returned.  The list does not change.
	   *  @param position the desired position, from 1 to length(), in the list.
	   *  @return the item at the given position in the list.
	   **/

	  public Object nth(int position) {
	    SListNode currentNode;

	    if ((position < 1) || (head == null)) {
	      return null;
	    } else {
	      currentNode = head;
	      while (position > 1) {
	        currentNode = currentNode.next;
	        if (currentNode == null) {
	          return null;
	        }
	        position--;
	      }
	      return currentNode.item;
	    }
	  } 

	  /**
	   *  toString() converts the list to a String.
	   *  @return a String representation of the list.
	   **/

	  public String toString() {
	    Object obj;
	    String result = "[  ";

	    SListNode cur = head;

	    while (cur != null) {
	      obj = cur.item;
	      result = result + obj.toString() + "  ";
	      cur = cur.next;
	    }
	    result = result + "]";
	    return result;
	  }

}
