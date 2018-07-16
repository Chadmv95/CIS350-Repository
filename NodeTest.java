import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Brian Gilbert
 *
 */
public class NodeTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Tests the following methods:
     * Node.Node()
     * Node.setContent();
     * Node.getContent();
     */
    @Test
    public void testNode_1() {
        String val = "This is a test";
        Node node = new Node();
        node.setContent(val);
        assertTrue(  val.equals( node.getContent() )  );
    }

    /**
     * Tests the following methods:
     * Node.Node(String)
     * Node.getContent()
     */
    @Test
    public void testNode_2() {
        String val = "This is a test";
        Node node = new Node(val);
        assertTrue(  val.equals( node.getContent() )  );
    }

    /**
     * Tests the following methods:
     * Node.setChildren(Node, int)
     * Node.getChildren(int)
     */
    @Test
    public void testChildren_1() {
        Node parent = new Node("Parent");
        Node child = new Node("Child");
        parent.setChildren(child, 1);
        assertTrue(  child.equals( parent.getChildren(1) )  );
    }

	/**
	 * Tests the following methods when passed a null value:
	 * Node.setChildren(Node, int)
	 */
	@Test
	public void testChildren_2() {
		Node parent = new Node("Parent");
		assertFalse( parent.setChildren(null, 1) );
	}

	/**
	 * Tests the following methods when passed a null value:
	 * Node.setChildren(Node)
	 */
	@Test
	public void testChildren_3() {
		Node parent = new Node("Parent");
		assertFalse( parent.setChildren(null) );
	}

	/**
	 * Tests the following methods:
	 * Node.setChildren(Node, int)
	 * Node.setChildren(Node)
	 */
	@Test
	public void testChildren_4() {
		Node parent = new Node("Parent");
		parent.setChildren(new Node("Child 1"), 2);  // Put child 1 in position 2
		parent.setChildren(new Node("Child 2"));     // Child 2 should now end up in position 0
		parent.setChildren(new Node("Child 3"));     // And Child 3 should get position 1
		
		assertEquals( "Child 2", parent.getChildren(0).getContent() );
	}

	/**
	 * Tests the following methods:
	 * Node.setChildren(Node, int)
	 * Node.setChildren(Node)
	 */
	@Test
	public void testGetNumChildren_1() {
		Node parent = new Node("Parent");
		parent.setChildren(new Node("Child 1"));
		parent.setChildren(new Node("Child 2"));
		parent.setChildren(new Node("Child 3"));
		
		assertEquals( parent.getNumChildren(), 3 );
	}

}
