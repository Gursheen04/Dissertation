package furhatos.app.fullduplexdog.flow.main

import furhatos.app.fullduplexdog.flow.Parent
import furhatos.app.fullduplexdog.setting.FullDuplexDog
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.*
import furhatos.nlu.common.DontKnow
import furhatos.nlu.common.Maybe
import furhatos.nlu.common.Yes
import furhatos.nlu.common.No



/** this file is made with questions that the furhat is gonna ask the user */
val Description: State = state(Parent) {
    onEntry {
        furhat.ask {
            +"Do you like robots?"
        }
    }

    onResponse<Yes> {
        furhat.say("How Nice!!")
        users.current.FullDuplexDog = true
        goto(Ending)
    }
    onResponse<No> {
        furhat.say("That's a shame")
        users.current.FullDuplexDog = false
        goto(Ending)
    }
    onResponse<DontKnow> {
        furhat.say("That's okay")
        goto(Ending)

    }
    onResponse<Maybe>{
        furhat.say("I'm going to take that as a yes!")
        users.current.FullDuplexDog = true
        goto(Ending)
    }
    onNoResponse {
        reentry() /** not bothering the user by saying I didnt hear you, just reentry into state */
    }
    /** User says something unexpected */
    onResponse {
        reentry()
    }

}