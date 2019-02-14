package nikhilbhutani.github.io.dextra.utils.rxutils

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
    fun newThread(): Scheduler
    fun single(): Scheduler
    fun trampoline(): Scheduler
    fun executor(): Scheduler

}