package com.exercise.product;

public interface Query <Input, Output> {
    Output execute(Input input);
}
