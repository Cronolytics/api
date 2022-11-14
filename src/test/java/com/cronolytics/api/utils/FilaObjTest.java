package com.cronolytics.api.utils.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilaObjTest {
    @Test
    public void isFullTrueQuandoFilaPreenchida() {
        FilaObj filaTest = new FilaObj(5);
        filaTest.insert("a");
        assertFalse(filaTest.isFull());
        filaTest.insert(1);
        assertFalse(filaTest.isFull());
        filaTest.insert(-1);
        assertFalse(filaTest.isFull());
        filaTest.insert(0);
        assertFalse(filaTest.isFull());
        filaTest.insert(0.5);
        assertTrue(filaTest.isFull());
    }

    @Test
    public void isEmptyQuandoFilaVazia() {
        FilaObj filaTest = new FilaObj(8);
        assertTrue(filaTest.isEmpty());
        filaTest.insert(1);
        filaTest.insert(-5);
        filaTest.insert("c");
        filaTest.insert("d");
        filaTest.insert("e");
        filaTest.insert("f");
        filaTest.insert("g");
        assertFalse(filaTest.isEmpty());
        filaTest.poll();
        assertFalse(filaTest.isEmpty());
        filaTest.poll();
        assertFalse(filaTest.isEmpty());
        filaTest.poll();
        assertFalse(filaTest.isEmpty());
        filaTest.poll();
        assertFalse(filaTest.isEmpty());
        filaTest.poll();
        assertFalse(filaTest.isEmpty());
        filaTest.poll();
        assertFalse(filaTest.isEmpty());
        filaTest.poll();
        assertTrue(filaTest.isEmpty());
    }

    @Test
    public void peekQuandoFilaRetornaA() {
        FilaObj filaTest = new FilaObj(3);
        filaTest.insert("a");
        filaTest.insert("b");
        filaTest.insert("c");
        assertEquals("a", filaTest.peek());
    }

    @Test
    public void pollQuandoFilaRetornaA() {
        FilaObj filaTest = new FilaObj(3);
        filaTest.insert("a");
        filaTest.insert("b");
        filaTest.insert("b");
        assertEquals(3, filaTest.getTamanho());
        assertEquals("a", filaTest.poll());
    }

    @Test
    public void pollQuandoFilaRetornaC() {
        FilaObj filaTest = new FilaObj(3);
        filaTest.insert("a");
        filaTest.insert("b");
        filaTest.insert("c");
        assertEquals("a", filaTest.poll());
        assertEquals("b", filaTest.poll());
    }

    @Test
    public void insertLancaIllegalStateExceptionQuandoFilaCheia() {
        FilaObj filaTest = new FilaObj(6);
        filaTest.insert("a");
        filaTest.insert("b");
        filaTest.insert("c");
        filaTest.insert("d");
        filaTest.insert("e");
        filaTest.insert("f");
        assertThrows(IllegalStateException.class, () -> filaTest.insert("g"));
    }

    @Test
    public void insertQuandoFilaRetornaA() {
        FilaObj filaTest = new FilaObj(6);
        filaTest.insert("a");
        assertEquals("a", filaTest.peek());
        filaTest.insert("b");
        assertEquals("a", filaTest.peek());
        filaTest.insert("c");
        assertEquals("a", filaTest.peek());
        filaTest.insert("d");
        assertEquals("a", filaTest.peek());
    }
}