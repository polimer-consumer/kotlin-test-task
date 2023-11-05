import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.VariableMutability
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val addCommandTestMethod = TestMethod(
    name = "addCommand",
    arguments = listOf(
        TestVariable(
            name = "command",
            javaType = "Command",
        ),
    ),
    returnType = TestKotlinType("Boolean"),
    returnTypeJava = "Boolean",
    visibility = Visibility.PUBLIC,
)

internal val getCommandTestMethod = TestMethod(
    name = "getCommand",
    returnType = TestKotlinType("org.jetbrains.kotlin.test.task.tamagotchi.models.Command?"),
    returnTypeJava = "org.jetbrains.kotlin.test.task.tamagotchi.models.Command",
    visibility = Visibility.PUBLIC,
)

internal val getAllCommandsTestMethod = TestMethod(
    name = "getAllCommands",
    returnType = TestKotlinType(
        "ArrayDeque",
        abbreviation = "org.jetbrains.kotlin.test.task.tamagotchi.models.Command"
    ),
    returnTypeJava = "ArrayDeque",
    visibility = Visibility.PUBLIC,
)

internal val commandsTestVariable = TestVariable(
    name = "commands",
    javaType = "ArrayDeque",
    visibility = Visibility.PRIVATE,
    mutability = VariableMutability.VAL,
)

internal val gameServiceTestClass = TestClass(
    name = "GameService",
    classPackage = "org.jetbrains.kotlin.test.task.tamagotchi.game",
    isDataClass = false,
    declaredFields = listOf(
        commandsTestVariable,
        TestVariable(
            name = "MAX_CAPACITY",
            javaType = "Int",
            visibility = Visibility.PRIVATE,
            mutability = VariableMutability.VAL,
            isConst = true,
            isStatic = true,
        )
    ),
    customMethods = listOf(
        addCommandTestMethod,
        getCommandTestMethod,
        getAllCommandsTestMethod,
    )
)

internal val gameServiceCompanionTestClass = TestClass(
    name = "GameService\$Companion",
    classPackage = "org.jetbrains.kotlin.test.task.tamagotchi.game",
)
