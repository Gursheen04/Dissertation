package furhatos.app.fullduplexdog

import furhatos.app.fullduplexdog.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class FullduplexdogSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
