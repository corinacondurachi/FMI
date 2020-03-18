package Arrays;

import java.util.Arrays;

public class MyArrayList {
    private float[] array;
    private int maxCapacity;
    private int indexArray;

    public MyArrayList() {
        this.maxCapacity = 10;
        this.array = new float[10];
        this.indexArray = 0;
    }

    public MyArrayList(int capacity) {
        this.maxCapacity = capacity;
        this.array = new float[capacity];
        this.indexArray = 0;
    }

    public void add(float value) {
        if (indexArray >= maxCapacity) {
            float[] aux = new float[maxCapacity];
            for (int i = 0; i < maxCapacity; i++)
                aux[i] = this.array[i];
            this.maxCapacity = 2 * maxCapacity;
            this.array = new float[maxCapacity];
            for (int i = 0; i < maxCapacity/2; i++)
                this.array[i] = aux[i];
        }
        this.array[indexArray] = value;
        this.indexArray++;
    }

    public boolean contains(float value){
        for(int i = 0; i < indexArray; i++){
            if(this.array[i]==value)
                return true;
        }
        return false;
    }

    public void remove(int index) {
        if(index < 0 || index>=indexArray)
            System.out.println("Index invalid");
        else {
            for(int i = index;i < indexArray; i++)
                array[i] = array[i+1];
        }
        this.indexArray--;
    }

    public float get(int index) {
        if(index < 0 || index>=indexArray)
        {System.out.println("Index invalid");
         return -1;}
        else
            return this.array[index];
    }

    public int size() {
        return indexArray;
    }

    @Override
    public String toString() {
        String sir="";
        for(int i = 0; i < indexArray; i++)
            sir += this.array[i]+" ";
        return sir;
    }

}


