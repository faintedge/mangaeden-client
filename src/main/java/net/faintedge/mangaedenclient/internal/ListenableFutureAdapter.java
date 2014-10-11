package net.faintedge.mangaedenclient.internal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
 
import com.google.common.util.concurrent.ListenableFuture;
 
public class ListenableFutureAdapter<V> implements ListenableFuture<V>  {
  
	private final com.ning.http.client.ListenableFuture<V> ningListenableFuture;

  public ListenableFutureAdapter(final com.ning.http.client.ListenableFuture<V> ningListenableFuture) {
		this.ningListenableFuture = ningListenableFuture;
	}
	
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return this.ningListenableFuture.cancel(mayInterruptIfRunning);
  }
	
	@Override
  public V get()
  		throws InterruptedException, ExecutionException {
    return this.ningListenableFuture.get();
  }
  
	@Override
	public V get(final long timeout, final TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
    return this.ningListenableFuture.get(timeout, unit);
  }
	
	@Override
	public boolean isCancelled() {
	  return this.ningListenableFuture.isCancelled();
  }
	
	@Override
	public boolean isDone() {
	  return this.ningListenableFuture.isDone();
  }
	
	@Override
	public void addListener(final Runnable runnable, final Executor executor) {
		this.ningListenableFuture.addListener(runnable, executor);
  }
}