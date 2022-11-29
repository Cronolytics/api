package com.cronolytics.api.utils;

public class PilhaObj<T> {

    private T[] pilha;
    private int topo;

    public PilhaObj(int capacidade) {
        topo = -1;
        pilha = (T[]) new Object[capacidade];
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T info) {
        if(isFull()) {
            throw new IllegalStateException("Pilha está cheia");
        }

        pilha[++topo] = info;
    }

    public T pop() {
        return isEmpty() ? null : pilha[topo--];
    }

    public T peek() {
        return isEmpty() ? null : pilha[topo];
    }

    public void exibe() {
        if(isEmpty()) {
            System.out.println("Pilha Vazia");
            return;
        }

        for(int i = topo; i > -1; i--) {
            System.out.println(pilha[i]);
        }
    }

    public T[] getPilha() {
        return pilha;
    }

    public int getTopo() {
        return topo;
    }

    public int getTamanho() {
        return this.pilha.length;
    }
}
