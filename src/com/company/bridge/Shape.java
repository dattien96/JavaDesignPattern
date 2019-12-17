package com.company.bridge;

/**
 * Bridge ở đây chính là has-a 1 obj là Color (là interface)
 */
public abstract class Shape {
    //Composition - implementor
    protected Color color;

    //constructor with implementor as input argument
    public Shape(Color c){
        this.color=c;
    }

    abstract public void applyColor();
}
