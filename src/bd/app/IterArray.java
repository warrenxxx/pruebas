/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author warren
 */
public final class IterArray {
    private final String nombre;
    private final int n;

    public IterArray(String nombre, int n) {
        this.nombre = nombre;
        this.n = n;
    }

    public String getNombre() {
        return nombre;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "IterArray{" + "nombre=" + nombre + ", n=" + n + '}';
    }
    
}
