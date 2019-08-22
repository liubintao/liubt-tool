package com.robust.tools.kit.concurrent.threadpool;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.robust.tools.kit.base.annotation.NotNull;
import com.robust.tools.kit.base.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程池工具类
 * 1、优雅关闭线程池的(via Guava)
 * 2、创建可自定义线程名的ThreadFactory(via Guava)
 * 3、防止第三方Runnable未捕获异常导致线程跑飞
 * @Author: robust
 * @CreateDate: 2019/8/1 11:38
 * @Version: 1.0
 */
public class ThreadPoolUtil {

    /**
     * 按照ExecutorService JavaDoc示例代码编写的Graceful Shutdown方法.
     * <p>
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * <p>
     * 如果1/2超时时间后, 则调用shutdownNow,取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * <p>
     * 如果1/2超时后仍然超時，則強制退出.
     * <p>
     * 另对在shutdown时线程本身被调用中断做了处理.
     * <p>
     * 返回线程最后是否被中断.
     *
     * @param threadPool
     * @param shutdownTimeoutMills
     * @return
     * @see MoreExecutors#shutdownAndAwaitTermination(ExecutorService, long, TimeUnit)
     */
    public static boolean gracefulShutdown(@Nullable ExecutorService threadPool, int shutdownTimeoutMills) {
        return gracefulShutdown(threadPool, shutdownTimeoutMills, TimeUnit.MILLISECONDS);
    }

    public static boolean gracefulShutdown(@Nullable ExecutorService threadPool, int shutdownTimeout,
                                           TimeUnit timeUnit) {
        return threadPool == null ||
                MoreExecutors.shutdownAndAwaitTermination(threadPool, shutdownTimeout, timeUnit);
    }

    /**
     * 创建ThreadFactory, 使得创建的线程有自己的名字而不是默认的"pool-x-thread-y"
     *
     * @param threadNamePrefix
     * @return
     * @see ThreadFactoryBuilder#build()
     */
    public static ThreadFactory buildThreadFactory(@NotNull String threadNamePrefix) {
        return new ThreadFactoryBuilder().setNameFormat(threadNamePrefix + "-%d").build();
    }

    /**
     * 可设定是否daemon,daemon线程在主线程执行完毕时,不会阻塞应用不退出,而非daemon线程则会阻塞.
     *
     * @param threadNamePrefix
     * @param daemon
     * @return
     */
    public static ThreadFactory buildThreadFactory(@NotNull String threadNamePrefix, @NotNull boolean daemon) {
        return new ThreadFactoryBuilder().setNameFormat(threadNamePrefix + "-%d").setDaemon(daemon).build();
    }

    /**
     * 防止用户没有捕捉异常导致中断了线程池中的线程, 使得SchedulerService无法继续执行.
     * 在无法控制第三方包的Runnable实现时，调用本函数进行包裹.
     *
     * @param runnable 被wrap的runnable
     * @return wrap后的safe runnable
     */
    public static Runnable safeRunnable(@NotNull Runnable runnable) {
        return new SafeRunnable(runnable);
    }

    /**
     * 保证不会有Exception抛出到线程池的Runnable包裹类，防止用户没有捕捉异常导致中断了线程池中的线程, 使得SchedulerService无法执行. 在无法控制第三方包的Runnalbe实现时，使用本类进行包裹.
     */
    @Slf4j
    private static class SafeRunnable implements Runnable {
        private Runnable runnable;

        public SafeRunnable(Runnable runnable) {
            Validate.notNull(runnable);
            this.runnable = runnable;
        }

        @Override
        public void run() {
            try {
                runnable.run();
            } catch (Throwable e) {
                // catch any exception, because the scheduled thread will break if the exception thrown to outside.
                log.error("Unexpected error occurred in task", e);
            }
        }
    }
}
