package furhatos.app.fullduplexdog.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state

val Sleeping: State = state {
    /** attend nobody, and wait for user to enter and attend and then go to greteing once user enter */
    onEntry {
        furhat.gesture(GesturesLib.PerformFallAsleepPersist)
    }

    /** then go to greeting which will start the actual conversation. */

    onUserEnter {
        furhat.gesture(GesturesLib.PerformWakeUpWithHeadShake)
        furhat.attend(it)
        goto(Greeting)
    }
}
