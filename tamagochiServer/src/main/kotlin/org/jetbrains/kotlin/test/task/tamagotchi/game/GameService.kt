package org.jetbrains.kotlin.test.task.tamagotchi.game

import org.jetbrains.kotlin.test.task.tamagotchi.models.Command
import org.jetbrains.kotlin.test.task.tamagotchi.models.Mode
import org.springframework.stereotype.Service

@Service
class GameService {
    private val commands: ArrayDeque<Command> = ArrayDeque(MAX_CAPACITY)
    private var mode = Mode.Queue

    companion object {
        private const val MAX_CAPACITY = 16
    }

    fun addCommand(command: Command): Boolean {
        if (commands.size < MAX_CAPACITY) {
            commands.addLast(command)
            return true
        }
        return false
    }

    fun getCommand(): Command? {
        return if (commands.isNotEmpty()) {
            if (mode == Mode.Queue) {
                commands.removeFirst()
            } else {
                commands.removeLast()
            }
        } else {
            null
        }
    }

    fun setMode(mode: Mode) {
        this.mode = mode
    }

    fun getAllCommands(): ArrayDeque<Command> {
        return commands
    }
}
