public class Bookstore {
    private int capacity;
    private Book[] books;
    private int index;

    public Bookstore(int capacity) {
        this.capacity = capacity;
        this.books = new Book[capacity];
        this.index = 0;
    }

    public void add(Book book){
        if(index >= capacity){
            throw new RuntimeException("Capacity is full");
        }
        books[index++] = book;
    }

    public Book get (int i){
        if(i > index){
            throw new RuntimeException("Book not found");
        }
        return books [i];
    }

    public int find (Book book){
        for(int i=0; i < index; i++){
            if(books[i].equals(book))
                return i;
        }
        return -1;
    }

    public int getIndex() {
        return index;
    }

    public void remove (Book book){
        int bookindex = find(book);
        for (int i = bookindex; i < index-1; i++){
            books[i] = books[i+1];
        }
        index--;
    }

}
