package net.explodingbush.ksoftapi;

import java.util.function.Consumer;

public interface KSoftAction<T> {

    T execute();

    void executeAsync();
    void executeAsync(Consumer<? super T> success);
    void executeAsync(Consumer<? super T> success, Consumer<? super Throwable> failure);

}
