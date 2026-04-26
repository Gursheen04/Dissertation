package furhatos.app.fullduplexdog.flow.main


import furhat.libraries.standard.GesturesLib
import furhatos.app.fullduplexdog.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Thanks
import furhatos.records.Location

val Ending: State = state(Parent) {
    onEntry {
        furhat.say{
            +"That was all I wanted to ask you"
            +"Have a nice day"
            +GesturesLib.PerformBigSmile1
            behavior { furhat.attend(Location.DOWN) }
        }
        furhat.listen()
    }

    onResponse<Thanks> {
        furhat.say {
            + "It was nice talking to you"
        }
    }
    onResponse {
        furhat.say {
            + "It was nice talking to you"
            + "See you Later"
        }
    }

    /** after 15 seconds, user expected to leave interaction and another user to stat a new convo with the robot */
    onTime(15000) {
        goto(Sleeping)
    }
}