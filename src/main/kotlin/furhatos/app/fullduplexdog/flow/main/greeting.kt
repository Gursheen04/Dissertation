package furhatos.app.fullduplexdog.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.fullduplexdog.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.raise
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Greeting

val Greeting: State = state (Parent) {
    onEntry {
        furhat.say {
            random {
                +"Hello!"
                +"Hello there!"
                +"Hi there!"
            }
            +GesturesLib.PerformSmile1 /** Multimodal interactionw ith speech and gestures */
        }
        furhat.listen()

    }

    /** user returned the greeting Hi */
    onResponse<Greeting> {
        var canIAskYouSomething = furhat.askYN("Can I ask you soemthing?")
        if (canIAskYouSomething) {
            goto(Description)
        } else {
            furhat.say("Sorry to bother you")
            goto(Sleeping)
        }
    }

    /** user did not return the greeting */
    onNoResponse {
        raise(Greeting())
    }

    /** user said soemthing something else and the furhat continues */
    onResponse {
        furhat.say("anyway...")
        raise(Greeting())
    }

}

