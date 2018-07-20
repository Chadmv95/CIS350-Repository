package brainstorm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Brian Gilbert
 * 
 * This class contains the JUnit tests for the Node class.
 * 
 */
public class NodeTest {

    /**
     * This test creates a node, then verifies its contents.
     * 
     * Tests the following methods:
     * Node.Node()
     * Node.setContent();
     * Node.getContent();
     */
    @Test
    public void testNode1() {
        String val = "This is a test";
        Node node = new Node();
        node.setContent(val);
        assertTrue(val.equals(node.getContent()));
    }

    /**
     * This test creates a node, then verifies its contents.
     * 
     * Tests the following methods:
     * Node.Node(String, String)
     * Node.getName()
     */
    @Test
    public void testNode2() {
        String val = "This is a test";
        Node node = new Node(val, "");
        assertTrue(val.equals(node.getName()));
    }

    /**
     * This test creates a parent and a child, then checks that
     * the relationship was formed properly.
     * 
     * Tests the following methods:
     * Node.setChildren(Node, int)
     * Node.getChildren(int)
     */
    @Test
    public void testChildren1() {
        Node parent = new Node("Parent", "");
        Node child = new Node("Child", "");
        parent.setChildren(child, 1);
        assertTrue(child.equals(parent.getChildren(1)));
    }

	/**
	 * This tests that setChildren returns false when given a
	 * null value as the child to set.
	 * 
	 * Tests the following methods when passed a null value:
	 * Node.setChildren(Node, int)
	 */
	@Test
	public void testChildren2() {
		Node parent = new Node("Parent", "");
		assertFalse(parent.setChildren(null, 1));
	}

	/**
	 * This tests that setChildren returns false when given a
     * null value as the child to set.
     * 
	 * Tests the following methods when passed a null value:
	 * Node.setChildren(Node)
	 */
	@Test
	public void testChildren3() {
		Node parent = new Node("Parent", "");
		assertFalse(parent.setChildren(null));
	}

	/**
	 * This tests that setChildren functions allow arbitrary
	 * placement in any order as desired.
     * 
	 * Tests the following methods:
	 * Node.setChildren(Node, int)
	 * Node.setChildren(Node)
	 */
	@Test
	public void testChildren4() {
		Node parent = new Node("Parent", "");
		// Put child 1 in position 2
		parent.setChildren(new Node("Child 1", ""), 2);
		// Child 2 should now end up in position 0
		parent.setChildren(new Node("Child 2", ""));
		// And Child 3 should get position 1
		parent.setChildren(new Node("Child 3", ""));
		
		assertEquals("Child 2", parent.getChildren(0).getName());
	}

	/**
     * This test makes sure that the getNumChildren function
     * is working properly.
     * 
	 * Tests the following methods:
	 * Node.setChildren(Node, int)
	 * Node.setChildren(Node)
	 */
	@Test
	public void testGetNumChildren1() {
		Node parent = new Node("Parent", "");
		parent.setChildren(new Node("Child 1", ""));
		parent.setChildren(new Node("Child 2", ""));
		parent.setChildren(new Node("Child 3", ""));
		
		assertEquals(parent.getNumChildren(), 3);
	}

}
