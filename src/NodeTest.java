import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeTest
{
    // ~ Fields ................................................................
    private Node<String> strNode;
    private Node<Integer> intNode;

    // ~ Constructors ..........................................................
    @BeforeEach
    public void setUp()
    {
        strNode = new Node<String>("hello");
        intNode = new Node<Integer>(5);
    }


    // ~Public Methods ........................................................
    @Test
    public void testGetData()
    {
        assertEquals("hello", strNode.getData());
        assertEquals(5, intNode.getData());
    }


    @Test
    public void testSetData()
    {
        strNode.setData("goodbye");
        assertEquals("goodbye", strNode.getData());

        intNode.setData(42);
        assertEquals(42, intNode.getData());
    }


    @Test
    public void testSetDataNull()
    {
        strNode.setData(null);
        assertNull(strNode.getData());
    }


    @Test
    public void testGetNext()
    {
        assertNull(strNode.getNext());

        Node<String> next = new Node<String>("next");
        strNode.setNext(next);
        assertSame(next, strNode.getNext());
    }


    @Test
    public void testSetNext()
    {
        Node<Integer> second = new Node<Integer>(10);
        Node<Integer> third = new Node<Integer>(20);

        intNode.setNext(second);
        assertSame(second, intNode.getNext());

        // replacing an existing next
        intNode.setNext(third);
        assertSame(third, intNode.getNext());
    }


    @Test
    public void testSetNextNull()
    {
        Node<Integer> second = new Node<Integer>(10);
        intNode.setNext(second);
        assertNotNull(intNode.getNext());

        intNode.setNext(null);
        assertNull(intNode.getNext());
    }


    @Test
    public void testChain()
    {
        Node<Integer> a = new Node<Integer>(1);
        Node<Integer> b = new Node<Integer>(2);
        Node<Integer> c = new Node<Integer>(3);

        a.setNext(b);
        b.setNext(c);

        assertEquals(1, a.getData());
        assertEquals(2, a.getNext().getData());
        assertEquals(3, a.getNext().getNext().getData());
        assertNull(a.getNext().getNext().getNext());
    }
}
