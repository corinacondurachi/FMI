public class Book {
    private String title, author, publisher;
    private int pageCount;

    public Book(String title, String author, String publisher, int pageCount) {
        if (pageCount <=0){
            throw new RuntimeException("number of pages must be a positive number");
        }
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    @Override
    public String toString() {
        return  "BOOK_TITLE: " + title.toUpperCase() + '\n' +
                "BOOK_AUTHOR: " + author + '\n' +
                "BOOK_PUBLISHER " + publisher.toLowerCase() ;
    }
}
