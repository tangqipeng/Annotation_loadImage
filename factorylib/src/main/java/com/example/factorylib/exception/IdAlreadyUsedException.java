package com.example.factorylib.exception;


import com.example.factorylib.FactoryAnnotatedClass;

public class IdAlreadyUsedException extends RuntimeException {
    public IdAlreadyUsedException(FactoryAnnotatedClass factoryAnnotatedClass) {

    }
}
