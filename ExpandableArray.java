public class ExpandableArray<E> {
    private E[] data;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ExpandableArray(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public ExpandableArray() {
        this(10);
    }

    private void resize() {
        capacity *= 2;
        @SuppressWarnings("unchecked")
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void insert(E elem) {
        if (size == capacity) resize();
        data[size++] = elem;
    }

    public void insert(E elem, int location) {
        if (location < 0 || location > size) throw new IndexOutOfBoundsException("Invalid insert location " + location);
        if (size == capacity) resize();
        for (int n = size; n > location; n--) {
            data[n] = data[n - 1];
        }
        data[location] = elem;
        size++;
    }

    public void set(E elem, int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid set index " + index);
        this.data[index] = elem;
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid get index " + index);
        return this.data[index];
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        // Simple Bubble Sort using compareTo()
        if (size < 2) return;
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

    public String toString() {
        if (size == 0) return "";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(data[i]);
            if (i < size - 1) s.append(", ");
        }
        return s.toString();
    }
}
