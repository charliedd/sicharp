package com.sicharp.lexicalCategories;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigIntTest {
    BigInt bigInt = new BigInt();
    Integero integero = new Integero();

    @Test
    public void integer(){
        assertEquals(bigInt.belongsToThisCategory("2.2"),true);

    }

    @Test
    public void intetegero(){
        assertEquals(integero.belongsToThisCategory("3"),true);
    }

    @Test
    public void intetegero2(){
        assertEquals(integero.belongsToThisCategory("2.0"),true);
    }
}