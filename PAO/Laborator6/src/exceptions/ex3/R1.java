package exceptions.ex3;

public class R1 implements AutoCloseable {


    //    public R1(){
//        throw RuntimeException();
//    }
    @Override
    public void close() throws Exception {
        throw new RuntimeException("in close method");
    }


}
