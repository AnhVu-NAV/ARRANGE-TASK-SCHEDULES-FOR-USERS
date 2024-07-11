/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.util.EmptyStackException;

/**
 *
 * @author AnhVu
 */
public class ArrayStack <E>{
    E arr[];
    int size;
    int top;
    int cap;

    public ArrayStack(int cap) {
        this.arr =(E[]) new Object[cap];
        this.size = 0;
        this.top = -1;
        this.cap = cap;
    }
    
    public boolean isFull(){
        return this.size == cap;
    }
    
    public boolean isEmpty(){
        return this.size == -1;
    }
    
    public void look (){
        for (int i = 0; i < this.size; i++) {
            System.out.print(arr[top-i] + " ");
        }
    }
    
    public void push(E data){
        if(isFull()){
            throw new ArrayStoreException("Stack is full, cannot push");
        }
        this.top++;
        this.arr[top]=data;
        this.size++;
    }
    
    public E pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        E data = arr[top];
        this.arr[top] = null;
        this.top--;
        this.size--;
        return data;
    }
    
    public E peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return arr[top];
    }
    
    public void clear(){
        this.arr =(E[]) new Object[cap];
        this.size = 0;
        this.top = -1;
    }
    
}
