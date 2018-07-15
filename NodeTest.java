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

}
