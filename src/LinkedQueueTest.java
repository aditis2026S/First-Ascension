import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedQueueTest
{
    // ~ Fields ................................................................
    private LinkedQueue<String> strQueue;
    private LinkedQueue<Integer> intQueue;

    // ~ Constructors ..........................................................
    @BeforeEach
    public void setUp()
    {
        strQueue = new LinkedQueue<String>();
        intQueue = new LinkedQueue<Integer>();

        intQueue.enqueue(2);
        intQueue.enqueue(5);
        intQueue.enqueue(10);
    }


    // ~Public Methods ........................................................
    @Test
    public void testEnqueue()
    {
        assertTrue(strQueue.isEmpty());
        strQueue.enqueue("hello");
        assertEquals(strQueue.peek(), "hello");
        assertEquals(1, strQueue.size());

        strQueue.enqueue("world");
        assertEquals("hello", strQueue.peek());
        assertEquals(2, strQueue.size());

    }

    @Test
    public void testDequeue()
    {
        assertEquals(intQueue.peek(), 2);
        assertEquals(intQueue.dequeue(), 2);
        assertEquals(intQueue.peek(), 5);
    }

    @Test
    public void testDequeueEmpty()
    {
        try
        {
            strQueue.dequeue();
            fail("Expected NoSuchElementException");
        }
        catch (java.util.NoSuchElementException e)
        {
            assertEquals("The queue is empty", e.getMessage());
        }
    }

    @Test
    public void testPeek()
    {
        assertEquals(intQueue.peek(), 2);

    }

    @Test
    public void testPeekEmpty()
    {
        try
        {
            strQueue.peek();
            fail("Expected NoSuchElementException");
        }
        catch (java.util.NoSuchElementException e)
        {
            assertEquals("The queue is empty", e.getMessage());
        }
    }

    @Test
    public void testIsEmpty()
    {
        assertTrue(strQueue.isEmpty());
        assertEquals(0, strQueue.size());
        assertFalse(intQueue.isEmpty());
        assertEquals(3, intQueue.size());
    }

    @Test
    public void testClear()
    {
        strQueue.clear();
        intQueue.clear();

        assertTrue(intQueue.isEmpty());
        assertTrue(strQueue.isEmpty());
        assertEquals(0, intQueue.size());
    }

    @Test
    public void testRemoveFront()
    {
        assertTrue(intQueue.remove(2));
        assertEquals(2, intQueue.size());
        assertEquals(5, (int)intQueue.peek());

    }

    @Test
    public void testRemoveMiddle()
    {
        assertTrue(intQueue.remove(5));
        assertEquals(2, intQueue.size());
        assertEquals(2, (int)intQueue.dequeue());
        assertEquals(10, (int)intQueue.dequeue());
    }

    @Test
    public void testRemoveRear()
    {
        assertTrue(intQueue.remove(10));
        assertEquals(2, intQueue.size());

        intQueue.enqueue(7);
        assertEquals(2, (int)intQueue.dequeue());
        assertEquals(5, (int)intQueue.dequeue());
        assertEquals(7, (int)intQueue.dequeue());
    }

    @Test
    public void testRemoveNotFound()
    {
        assertFalse(intQueue.remove(99));
        assertEquals(3, intQueue.size());

    }

    @Test
    public void testRemoveEmpty()
    {
        assertFalse(strQueue.remove("nothing"));
    }

    @Test
    public void testRemoveOnlyElement()
    {
        strQueue.enqueue("solo");
        assertTrue(strQueue.remove("solo"));
        assertTrue(strQueue.isEmpty());
        strQueue.enqueue("again");

        assertEquals("again", strQueue.peek());
    }
}
