/*
 * The License
 *
 * Copyright 2014 Agendoo.me.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Agendoo.me.
 */
package br.com.valecard.context.event;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.data.rest.core.event.RepositoryEvent;

/**
 *
 * @author Marcos
 */
public class ChoiceTaskExecutorApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    private Executor taskExecutor;

    /**
     * Create a new SimpleApplicationEventMulticaster.
     */
    public ChoiceTaskExecutorApplicationEventMulticaster() {
    }

    /**
     * Create a new SimpleApplicationEventMulticaster for the given BeanFactory.
     */
    public ChoiceTaskExecutorApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    /**
     * Set the TaskExecutor to execute application listeners with.
     * <p>
     * Default is a SyncTaskExecutor, executing the listeners synchronously in
     * the calling thread.
     * <p>
     * Consider specifying an asynchronous TaskExecutor here to not block the
     * caller until all listeners have been executed. However, note that
     * asynchronous execution will not participate in the caller's thread
     * context (class loader, transaction association) unless the TaskExecutor
     * explicitly supports this.
     *
     * @see org.springframework.core.task.SyncTaskExecutor
     * @see org.springframework.core.task.SimpleAsyncTaskExecutor
     */
    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    /**
     * Return the current TaskExecutor for this multicaster.
     */
    protected Executor getTaskExecutor() {
        return this.taskExecutor;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void multicastEvent(final ApplicationEvent event) {
        Executor executor = getTaskExecutor();
        if (event instanceof RepositoryEvent) {
            executor = new SyncTaskExecutor();
        }
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            if (executor != null) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        listener.onApplicationEvent(event);
                    }
                });
            } else {
                listener.onApplicationEvent(event);
            }
        }

    }

}
