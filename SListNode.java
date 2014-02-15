package wordquizz;

public class SListNode {
	/**
	 *  SListNode is a class used internally by the SList class.  An SList object
	 *  is a singly-linked list, and an SListNode is a node of a singly-linked
	 *  list.  Each SListNode has two references:  one to an object, and one to
	 *  the next node in the list.
	 *
	 *  @author Kathy Yelick and Jonathan Shewchuk
	 */
	  Object item;
	  SListNode next;
	  SListNode prev;

	  /**
	   *  SListNode() (with one parameter) constructs a list node referencing the
	   *  item "obj".
	   *  In my case I need only strings here.
	   */

	  SListNode(Object obj) {
	    item = obj;
	    next = null;
	    prev = null;
	  }

	  /**
	   *  SListNode() (with two parameters) constructs a list node referencing the
	   *  item "obj", whose next list node is to be "next".
	   */

	  SListNode(Object obj, SListNode next) {
	    item = obj;
	    this.next = next;
	    this.prev = next.prev;
	    next.prev = this;
	  }
}
