/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author vungu
 */
public class Node <E>{
    E data;
    Node next;
    Node prev;

    public Node(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
