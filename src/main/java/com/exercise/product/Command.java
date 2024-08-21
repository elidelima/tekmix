package com.exercise.product;

public interface Command<E, T> {
    T execute(E entity);
}
