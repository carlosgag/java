package com.etraveli.cardcost.persistence;

public interface DB<T, S> {
    T get(S s);

    void post(T t);

    void update(T t);

    void delete(S s);
}
