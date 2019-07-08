package net.explodingbush.ksoftapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public abstract class KSoftActionAdapter<T> implements KSoftAction<T> {

	private static ExecutorService executor = Executors.newCachedThreadPool();
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
    /**
     * Executes the provided request asynchronously. This will not return any consumer.
     */
    @Override
    public void executeAsync() {
       this.executeAsync((result) -> {});
    }

    /**
     * Executes the provided request asynchronously.
     * This will ignore all called exceptions.
     *
     * <p><b>This method is asynchronous</b>
     *
     * @param success
     * The success callback that will be called at at a convenient time for the wrapper. (this can be null)
     *
     */
    @Override
    public void executeAsync(Consumer<? super T> success) {
    	this.executeAsync(success, (e) -> {
    		LOG.error("An exception occured while making a request");
    		LOG.error(e.toString());
    		Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).forEach(LOG::error);
    	});
    }
    /**
     * Executes the provided request asynchronously.
     *
     * <p><b>This method is asynchronous</b>
     *
     * @param success
     * The success callback that will be called at at a convenient time for the wrapper. (this can be null)
     * @param  failure
     * The failure callback that will be called if the execution encounters an exception.
     *
     */
    @Override
    public void executeAsync(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        executor.execute(() -> {
        	try {
        		T result = this.execute();
        		try {
        			success.accept(result);
        		}
        		catch(Exception e) {
            		LOG.error("An exception occured in the success callback");
            		LOG.error(e.toString());
            		Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).forEach(LOG::error);
        		}
        	}
        	catch(Exception e) {
        		try {
        			failure.accept(e);
        		}
        		catch(Exception err) {
            		LOG.error("An exception occured in the failure callback");
            		LOG.error(err.toString());
            		Arrays.stream(err.getStackTrace()).map(StackTraceElement::toString).forEach(LOG::error);
        		}
        	}
        });
    }
}
