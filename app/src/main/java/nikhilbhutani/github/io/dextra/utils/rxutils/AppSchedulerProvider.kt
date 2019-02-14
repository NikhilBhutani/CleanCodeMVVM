package nikhilbhutani.github.io.dextra.utils.rxutils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class AppSchedulerProvider : SchedulerProvider {

    /**
     *   Schedulers.computation( ) - meant for computational work such as event-loops and callback processing;
     *     do not use this scheduler for I/O (use Schedulers.io( ) instead); the number of threads, by default,
     *     is equal to the number of processors
     */
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }


    /**
     * meant for I/O-bound work such as asynchronous performance of blocking I/O,
     * this scheduler is backed by a thread-pool that will grow as needed; for ordinary computational work,
     * switch to Schedulers.computation( ); Schedulers.io( ) by default is a CachedThreadScheduler,
     * which is something like a new thread scheduler with thread caching
     */
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    /**
     *
    This scheduler is provided by rxAndroidLibrary. This is used to bring back the execution to the main thread so that UI
    modification can be made. This usually used in ObserveOn method.
     *
     */
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }


    /**
     *  It spawns the new thread for each active observable. This can be used to offload time consuming operation on the main thread
     *  onto other thread. Since it creates a new thread, you need to take care because creating a new thread is costly operation and
     *  can have drastic effect in the mobile environment if the number of observables are high enough.
     */
    override fun newThread(): Scheduler {
        return Schedulers.newThread()
    }

    /**
     *  This scheduler is backed by just a single thread. So no matter how many observables are there, it will run only on that one
     *  thread. It can be thought as a replacement to your main thread.
     */
    override fun single(): Scheduler {
        return Schedulers.single()
    }


    /**
     *  This scheduler runs the code on the current thread. If you have code running on the main thread, this scheduler will add code
     *   block on the queue of main thread. Trampoline schedulers may come handy when we have more than one observable and we want
     *   them to execute in order.
     */
    override fun trampoline(): Scheduler {
        return Schedulers.trampoline()
    }


    /**
     *  This is more of custom IO scheduler. We can create custom pool of threads by specifying the size of the pool. This can be used
     *  where the number of observables could be huge for IO Schedulers. Like here we have taken 10.
     */
    override fun executor(): Scheduler {
        val executor = Executors.newFixedThreadPool(10)
        val pooledScheduler = Schedulers.from(executor)
        return pooledScheduler
    }


}