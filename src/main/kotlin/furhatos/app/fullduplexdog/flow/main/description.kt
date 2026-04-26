package furhatos.app.fullduplexdog.flow.main

import furhatos.app.fullduplexdog.flow.Parent
import furhatos.app.fullduplexdog.nlu.BoyIntent
import furhatos.app.fullduplexdog.nlu.DogIntent
import furhatos.app.fullduplexdog.nlu.ObjectIntent
import furhatos.app.fullduplexdog.nlu.ParkIntent
import furhatos.app.fullduplexdog.nlu.StopIntent
import furhatos.app.fullduplexdog.nlu.WeatherIntent
import furhatos.app.fullduplexdog.setting.FullDuplexDog
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.*
import furhatos.app.fullduplexdog.utils.Transcript
import furhatos.nlu.Intent


/** this file is made with questions that the furhat is gonna ask the user */
val Description: State = state(Parent) {

    //var timer: Job? = null
    var timerRunning = true

    onEntry {
        // Start the 90 second timer in background
        timerRunning = true
        Transcript.log("SYSTEM", "Entered Description state")

        parallel {
            delay(90000)
            if (timerRunning) {
                furhat.say("Thank you for your description")
                Transcript.log("ROBOT", "Thank you for your description")
                goto(Ending)
            }
        }

        furhat.say("Please begin describing the picture.")
        Transcript.log("ROBOT", "Please begin describing the picture.")

        reentry()

        //delay(3000)
        //furhat.listen()
    }

    onReentry {
        delay(300)
        furhat.listen()
    }

    // --- STOP INTENT ---
    onResponse<StopIntent> {
        timerRunning = false
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "StopIntent")

        furhat.say("Thank you for describing the picture to me.")
        Transcript.log("ROBOT", "Thank you for describing the picture to me.")
        goto(Ending)
    }

    // --- WEATHER ---
    onResponse<WeatherIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "WeatherIntent")

        furhat.say("Oh, rainy weather makes me feel a bit gloomy too.")
        Transcript.log("ROBOT", "Oh, rainy weather makes me feel a bit gloomy too.")
        //furhat.listen()
        reentry()
    }

    // --- PARK ---
    onResponse<ParkIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "ParkIntent")

        furhat.say("Parks are lovely places. I like exploring them!")
        Transcript.log("ROBOT", "Parks are lovely places. I like exploring them!")
        //furhat.listen()
        reentry()
    }

    // --- BOY ---
    onResponse<BoyIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "BoyIntent")

        furhat.say("The boy seems happy. I like happy humans!")
        Transcript.log("ROBOT", "The boy seems happy. I like happy humans!")
        //furhat.listen()
        reentry()
    }

    // --- DOG ---
    onResponse<DogIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "DogIntent+Other")

        //furhat.say("A dog? Oh! I love dogs! They’re my favourite.")
        //Transcript.log("ROBOT", "A dog? Oh! I love dogs! They’re my favourite.")
        furhat.say("Woof!")
        Transcript.log("ROBOT", "Woof!")
        reentry()
    }

    // --- OBJECTS ---
    onResponse<ObjectIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "ObjectIntent")

        furhat.say("Interesting! Keep going, I'm listening.")
        Transcript.log("ROBOT", "Interesting! Keep going, I'm listening.")
        //furhat.listen()
        reentry()
    }

    // --- ANY OTHER SPEECH that doesn't match the intent---
    onResponse {
        // No reaction, just keep listening
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "None")
        furhat.say("Okay, go on.")
        Transcript.log("ROBOT", "Okay, go on.")
        //furhat.listen()
        reentry()
    }


    // --- SILENCE ---
    onNoResponse {
        //furhat.listen()
        reentry()
    }

    onExit {
        timerRunning = false
        Transcript.log("SYSTEM", "Exiting Description state")
    }


}




