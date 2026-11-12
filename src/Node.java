public class Node<T>
{
    //~ Fields ................................................................
    private T data;
    private Node<T> next;
    
    
    //~ Constructors ..........................................................
    public Node(T data)
    {
        this.data = data;
    }
    
    public Node(T data, Node<T> next)
    {
        this.data = data;
        this.next = next;
    }
    //~Public  Methods ........................................................

    public T getData()
    {
        return data;
    }
    
    public void setData(T newData)
    {
        this.data = newData;
    }
    
    public Node<T> getNext()
    {
        return next;
    }
    
    public void setNext(Node<T> newNode)
    {
        this.next = newNode;
    }
}
