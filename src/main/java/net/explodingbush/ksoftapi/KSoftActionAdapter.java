package net.explodingbush.ksoftapi;

import java.util.concurrent.*;
import java.util.function.Consumer;

public abstract class KSoftActionAdapter<T> implements KSoftAction<T> {


    /**
     * Executes the provided request asynchronously. This will not return any consumer.
     */
    @Override
    public void executeAsync() {
       CompletableFuture.runAsync(this::execute);
    }

    /**
     * Executes the provided request asynchronously. <br />
     * This will ignore all called exceptions.
     * <br />
     * <p><b>This method is asynchronous</b>
     * <br />
     *
     * @param success
     * The success callback that will be called at at a convenient time for the wrapper. (this can be null)
     *
     */
    @Override
    public void executeAsync(Consumer<? super T> success) {
        CompletableFuture<T> apple = CompletableFuture.supplyAsync(this::execute);
        success.accept(apple.join());
    }
    /**
     * Executes the provided request asynchronously.
     *
     * <br />
     * <p><b>This method is asynchronous</b>
     * <br />
     *
     * @param success
     * The success callback that will be called at at a convenient time for the wrapper. (this can be null)
     * @param  failure
     * The failure callback that will be called if the execution encounters an exception.
     *
     */
    @Override
    public void executeAsync(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        CompletableFuture<T> apple = CompletableFuture.supplyAsync(this::execute);
        try {
            success.accept(apple.join());
        } catch (Exception e) {
            failure.accept(e);
        }
    }
}
