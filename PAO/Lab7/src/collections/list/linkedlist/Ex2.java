package collections.list.linkedlist;

import java.util.LinkedList;

public class Ex2 {
    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.offer("c"); //adauga la final
        list.offerFirst("rr"); //adauga in fata
        System.out.println(list);

        System.out.println(list.element()); //daca e vida NoSuchElementException reurneaza capul
        //new LinkedList<>().element();

        LinkedList<String> empty = new LinkedList<>();

        System.out.println(list.peek());
        System.out.println(list.poll());
        System.out.println(list);
        System.out.println(empty.poll());

        list.pop();
        System.out.println(list);

        //empty.pop(); eroare


    }
}
