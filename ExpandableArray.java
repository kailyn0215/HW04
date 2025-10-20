
/**
 * A generic dynamic array that can grow in size as elements are added.
 * 
 * @param <E> the type of elements stored in this array
 */
public class ExpandableArray<E> {
    private E[] data;
    private int size;
    private int capacity;

    /**
     * Constructs an ExpandableArray with specified initial capacity.
     * 
     * @param capacity the initial capacity of the array
     */    
    @SuppressWarnings("unchecked")
    public ExpandableArray(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * Constructs an ExpandableArray with the default initial capacity of 10.
     */    
    public ExpandableArray() {
        this(10);
    }

    /**
     * Resizes the array to double its current capacity.
     */
    private void resize() {
        capacity *= 2;
        @SuppressWarnings("unchecked")
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Inserts an element at the end of the array.
     * 
     * @param elem the element to be inserted
     */    
    public void insert(E elem) {
        if (size == capacity) {
            resize();
        }
        data[size++] = elem;
    }

    /**
     * Inserts an element at the specified location.
     * 
     * @param elem the element to be inserted
     * @param location the position where the element should be inserted
     * @throws IndexOutOfBoundsException if location is invalid
     */
    public void insert(E elem, int location) {
        if (location < 0 || location > size) { throw new IndexOutOfBoundsException
            ("Invalid insert location " + location);
        }
        if (size == capacity) {
            resize();            
        }
        for (int n = size; n > location; n--) {
            data[n] = data[n - 1];
        }
        data[location] = elem;
        size++;
    }

    /**
     * Sets the element at the specified index.
     * 
     * @param elem the element to set
     * @param index the index of the element to replace
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void set(E elem, int index) {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException
            ("Invalid set index " + index);
        }
        this.data[index] = elem;
    }

    /**
     * Gets the element at the specified index.
     * 
     * @param index the index of the element to get
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public E get(int index) {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException 
            ("Invalid get index " + index); 
        }
        return this.data[index];
    }

    /**
     * Gets the current number of elements in the array.
     * 
     * @return the number of elements
     */
    public int size() {
        return size;
    }


    /**
     * Sorts the elements of the array using a simple bubble sort.
     * Elements must implement Comparable.
     */
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size < 2) {
            return; 
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                Comparable<E> a = (Comparable<E>) data[j];
                E b = data[j + 1];
                if (a.compareTo(b) > 0) {
                    E temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Returns a string representation of the array elements.
     * 
     * @return a string of elements separated by commas
     */
    public String toString() {
        if (size == 0) {
            return "";           
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(data[i]);
            if (i < size - 1) {
                s.append(", ");
            }
        }
        return s.toString();
    }
}
