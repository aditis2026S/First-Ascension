import java.util.NoSuchElementException;

public class LinkedQueue<T>
{
    // ~ Fields ................................................................
    private Node<T> front;
    private Node<T> rear;
    private int size;

    // ~ Constructors ..........................................................
    public LinkedQueue()
    {
        front = null;
        rear = null;
        size = 0;

    }


    // ~Public Methods ........................................................
    public void enqueue(T data)
    {
        if (rear == null)
        {
            Node<T> tempNode = new Node<T>(data);
            rear = tempNode;
            front = rear;
        }
        else
        {
            Node<T> tempNode = new Node<T>(data);
            rear.setNext(tempNode);
            rear = tempNode;
        }
        size++;
    }


    public T dequeue()
    {
        if (size == 0)
        {
            throw new NoSuchElementException("The queue is empty");
        }

        if (size == 1)
        {
            T value = front.getData();
            front = null;
            rear = null;
            size--;
            return value;

        }
        T value = front.getData();
        front = front.getNext();
        size--;
        return value;

    }


    public T peek()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("The queue is empty");
        }
        
        return front.getData();

    }


    public boolean isEmpty()
    {
        return size == 0;

    }


    public void clear()
    {
        while (!isEmpty())
        {
            this.dequeue();
        }

    }


    public int size()
    {
        return size;
    }


    public boolean remove(T value)
    {
        if(front == null)
        {
            return false;
        }

        if (front.getData().equals(value))
        {
            this.dequeue();
            return true;
        }
        
        Node<T> searchNode = front.getNext();
        Node<T> beforeSearchNode = front;
        
        while (searchNode != null)
        {
            if (searchNode.getData().equals(value))
            {
                if (searchNode == rear)
                {
                    rear = beforeSearchNode;
                }
                beforeSearchNode.setNext(searchNode.getNext());
                size--;
                return true;
                
            }
            
                searchNode = searchNode.getNext();
                beforeSearchNode = beforeSearchNode.getNext();
            
        }
        return false;

    }
}
