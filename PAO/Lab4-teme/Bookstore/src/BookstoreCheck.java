public class BookstoreCheck {

    static boolean duplicate(Bookstore store, Book book){
        int count = 0;
        for(int i=0; i < store.getIndex(); i++){
            if(store.get(i) == book){
                count++;
                if(count == 2)
                    return true;
            }
        }
    return false;
    }

    static Book thicker (Book book1, Book book2){
        if (book1.getPageCount() > book2.getPageCount())
            return book1;
        else
            return book2;
    }
}
