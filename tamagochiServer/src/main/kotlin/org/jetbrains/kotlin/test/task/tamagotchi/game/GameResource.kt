@file:Suppress("UnusedParameter")

package org.jetbrains.kotlin.test.task.tamagotchi.game

import org.jetbrains.kotlin.test.task.tamagotchi.models.Command
import org.jetbrains.kotlin.test.task.tamagotchi.models.Mode
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/command/")
class GameResource(val service: GameService) {
    @CrossOrigin
    @PostMapping("/get")
    fun getCommand(@RequestBody mode: String): Command? {
        service.setMode(Mode.valueOf(mode.replace("\"", "")))
        return service.getCommand()
    }

    @CrossOrigin
    @PostMapping("/add")
    fun addCommand(@RequestBody command: Int): Boolean =
        service.addCommand(enumValues<Command>().first { it.ordinal == command })

    @CrossOrigin
    @GetMapping("/all")
    fun getAllCommands(): ArrayDeque<Command> = service.getAllCommands()
}
