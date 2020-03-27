package ex1;

public class C1 implements Interface1, Interface2 {

    @Override
     public void m1(){
        //System.out.println("m1");
        Interface2.super.m1(); //pot alege una anume
    }

}
