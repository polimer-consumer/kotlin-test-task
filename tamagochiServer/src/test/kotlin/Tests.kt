import org.jetbrains.academy.test.system.core.models.classes.ConstructorGetter
import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.test.task.tamagotchi.game.GameService
import org.jetbrains.kotlin.test.task.tamagotchi.models.Command
import org.jetbrains.kotlin.test.task.tamagotchi.models.Mode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun commandFieldTest() {
        val clazz = gameServiceTestClass.checkBaseDefinition()

        val instance = clazz.getConstructor().newInstance()
        val field = clazz.declaredFields.find { it.name == commandsTestVariable.name }
            ?: error("Can not find the field ${commandsTestVariable.name}")
        field.isAccessible = true
        val commandsVariable = field.get(instance)
        assert((commandsVariable as ArrayDeque<*>).isEmpty()) { "The commands storage must be empty after initialization" }
    }

    @Test
    fun gameServiceTestClassTest() {
        val clazz = gameServiceTestClass.checkBaseDefinition()
        gameServiceCompanionTestClass.checkBaseDefinition()

        gameServiceTestClass.checkFieldsDefinition(clazz)
        gameServiceTestClass.checkConstructors(
            clazz,
            listOf(
                ConstructorGetter(),
            )
        )
    }

    @Test
    fun addCommandTestMethodTest() {
        val invokeData =
            TestMethodInvokeData(
                gameServiceTestClass,
                addCommandTestMethod,
            )

        repeat(16) {
            assert(
                gameServiceTestClass.invokeMethodWithArgs(
                    args = arrayOf(Command.Eat),
                    invokeData = invokeData,
                ) as Boolean
            ) { "The method ${addCommandTestMethod.name} must return true when command storage is not full" }
        }

        assert(
            !(gameServiceTestClass.invokeMethodWithArgs(
                args = arrayOf(Command.Eat),
                invokeData = invokeData,
            ) as Boolean)
        ) { "The method ${addCommandTestMethod.name} must return false when command storage full" }
    }

    @Test
    fun getCommandTestMethodTest() {
        val gameService = GameService()
        Command.entries.toTypedArray().map { gameService.addCommand(it) }
        val expected = listOf(Command.Eat, Command.Play, Command.Clean, Command.Sleep)
        val modes = listOf(Mode.Queue, Mode.Stack, Mode.Stack, Mode.Queue)

        for (i in 0..3) {
            val command = gameService.getCommand(modes[i])
            assertEquals(expected[i], command, "Incorrect getCommand method result in mode ${modes[i].name}")
        }

        assertNull(gameService.getCommand(Mode.Queue), "getCommand in mode Queue must return Null on empty storage")
        assertNull(gameService.getCommand(Mode.Stack), "getCommand in mode Stack must return Null on empty storage")
    }
}
