import java.util.Scanner;

public class BookstoreTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Number of books:");
        int numBooks = scanner.nextInt();

        Bookstore store = new Bookstore(20);

        for(int i = 0; i < numBooks; i++){
            String title = scanner.next();
            String author = scanner.next();
            String publisher = scanner.next();
            int pageCount = scanner.nextInt();
            store.add(new Book(title, author, publisher, pageCount));

        }
        scanner.close();

        Book first = store.get(0);
        store.add(first);
        System.out.println("Duplicate: " + BookstoreCheck.duplicate(store,first));

        Book second = store.get(1);
        System.out.println("Thicker book: " + BookstoreCheck.thicker(second,first));

        System.out.println("Books: ");
        for(int i = 0; i < store.getIndex(); i++){
            System.out.println(store.get(i));
        }

    }
}
