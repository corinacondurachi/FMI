public class VirtualCoffee {

    public static void prepareCup(Cup cup) {
        cup.wash();
    }

    //static polymorphism
    //dynamic polymorphism
    public static void main(String[] args) {

        Cup cup = new Cup();
        prepareCup(cup);

        //dynamic polymorphism
        Cup coffeeCup = new CoffeeCup();
        prepareCup(coffeeCup);

        Cup teaCup = new TeaCup();
        prepareCup(teaCup);


    }
}
