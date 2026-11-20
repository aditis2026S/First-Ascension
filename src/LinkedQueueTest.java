<<<<<<< Updated upstream
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
=======
import student.TestCase;
import java.util.NoSuchElementException;

/**
 * Tests for the LinkedQueue class.
 */
public class LinkedQueueTest extends TestCase {

    private LinkedQueue<String> queue;

    /**
     * Creates an empty queue before each test.
     */
    public void setUp() {
        queue = new LinkedQueue<String>();
    }

    /**
     * Tests that a new queue is empty.
     */
    public void testConstructor() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests adding one item.
     */
    public void testEnqueue() {
        queue.enqueue("Jester");

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals("Jester", queue.peek());
    }

    /**
     * Tests adding multiple items.
     */
    public void testEnqueueMultiple() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");
        queue.enqueue("Cat");

        assertEquals(3, queue.size());
        assertEquals("Jester", queue.peek());
    }

    /**
     * Tests removing the front item.
     */
    public void testDequeue() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");

        assertEquals("Jester", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("Blob", queue.peek());
    }

    /**
     * Tests removing the only item.
     */
    public void testDequeueLastItem() {
        queue.enqueue("Jester");

        assertEquals("Jester", queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests that dequeue throws an exception on an empty queue.
     */
    public void testDequeueEmpty() {
        try {
            queue.dequeue();
            fail("Expected NoSuchElementException.");
        }
        catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests that peek throws an exception on an empty queue.
     */
    public void testPeekEmpty() {
        try {
            queue.peek();
            fail("Expected NoSuchElementException.");
        }
        catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests that the queue follows FIFO order.
     */
    public void testFIFO() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");
        queue.enqueue("Cat");

        assertEquals("Jester", queue.dequeue());
        assertEquals("Blob", queue.dequeue());
        assertEquals("Cat", queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    /**
     * Tests peeking without removing an item.
     */
    public void testPeek() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");

        assertEquals("Jester", queue.peek());
        assertEquals(2, queue.size());
    }

    /**
     * Tests clearing the queue.
     */
    public void testClear() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");

        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests removing an item from the front.
     */
    public void testRemoveFront() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");

        assertTrue(queue.remove("Jester"));
        assertEquals("Blob", queue.peek());
        assertEquals(1, queue.size());
    }

    /**
     * Tests removing an item from the middle.
     */
    public void testRemoveMiddle() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");
        queue.enqueue("Cat");

        assertTrue(queue.remove("Blob"));
        assertEquals(2, queue.size());
        assertEquals("Jester", queue.peek());
        assertEquals("Cat", queue.dequeue());
    }

    /**
     * Tests removing the rear item.
     */
    public void testRemoveRear() {
        queue.enqueue("Jester");
        queue.enqueue("Blob");

        assertTrue(queue.remove("Blob"));
        assertEquals(1, queue.size());
        assertEquals("Jester", queue.peek());

        queue.enqueue("Cat");
        assertEquals("Cat", queue.dequeue());
    }

    /**
     * Tests removing an item that does not exist.
     */
    public void testRemoveNonexistent() {
        queue.enqueue("Jester");

        assertFalse(queue.remove("Blob"));
        assertEquals(1, queue.size());
    }

    /**
     * Tests removing from an empty queue.
     */
    public void testRemoveEmpty() {
        assertFalse(queue.remove("Jester"));
        assertTrue(queue.isEmpty());
>>>>>>> Stashed changes
    }
}
