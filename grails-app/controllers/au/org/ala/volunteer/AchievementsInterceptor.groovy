package au.org.ala.volunteer

import groovy.transform.CompileStatic

@CompileStatic
class AchievementsInterceptor {

    DomainUpdateService domainUpdateService

    AchievementsInterceptor() {
        matchAll()
    }

    boolean before() {
        log.debug('AchievementsInterceptor before')
        true
    }

    boolean after() {
        log.debug("task debouncing")

        def taskSet = GormEventDebouncer.taskSet

        if (taskSet) {
            try {
                domainUpdateService.onTasksUpdated(taskSet)
            } catch (Exception e) {
                log.error("Exception while performing post request actions", e)
            }
        }
        true
    }

    void afterView() {
        // no-op
        log.debug("After view")
    }
}
