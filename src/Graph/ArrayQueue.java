/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author AnhVu
 */
public class ArrayQueue <E> {
    E a[];
    int size;
    int cap;
    int fornt, back;

    public ArrayQueue(int cap) {
        this.cap = cap;
        this.fornt = -1;
        this.back = -1;
        this.a = (E[]) new Object[cap];
        this.size = 0;
    }
    
    public boolean isFull(){
        return this.size == this.cap;
    }
    
    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public void clear(){
        this.fornt = -1;
        this.back = -1;
        this.a = (E[]) new Object[cap];
        this.size = 0;
    }
    
    public void enqueue(E data){
        if(this.isFull()){
            System.out.println("QUEUE IS FULL");
            return;
        }
        if(this.isEmpty()){
            this.fornt = this.back = 0;
            this.a[this.back] =  data;
            this.size ++;
            return;
        }
        this.back = (this.back+1)%this.cap;
        this.a[this.back] = data;
        this.size++;
    }
    
    public E dequeue(){
        if(this.isEmpty()){
            System.out.println("QUEUE IS EMPTY!");
            return null;
        }
        if(this.size == 1){
            E value = this.a[this.fornt];
            this.clear();
            return value;
        }
        E value = this.a[this.fornt];
        this.a[this.fornt] = null;
        this.fornt = (this.fornt+1)%this.cap;
        this.size--;
        return value;
    }
    
    public E front(){
        if(this.isEmpty()){
            System.out.println("QUEUE IS EMPTY!");
            return null;
        }
        E value = this.a[this.fornt];
        return value;
    }
    
    public void traverse(){
        for (int i = 0; i < this.size; i++) {
            System.out.print (this.a[(this.fornt+i)%this.cap] + " ");
        }
    }
}
