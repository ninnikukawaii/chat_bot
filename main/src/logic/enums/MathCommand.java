package logic.enums;

import com.wolfram.alpha.WAQueryResult;
import logic.handlers.MathHandler;

import java.util.List;

public enum MathCommand {
    ALTERNATE_FORMS("приведение", "alternate form", "Другие формы:", MathHandler::getAlternateForms),
    ROOTS("корни", "root", "Корни многочлена:", MathHandler::getRoots),
    TRUTH_TABLE("таблица", "truth table", "Таблица истинности:", MathHandler::getTruthTable),
    MINIMAL_FORMS("к", "minimal forms", "", (queryResult) -> null),
    DNF("днф", "DNF", "Дизъюнктивная нормальная форма:", MathHandler::getDNF),
    CNF("кнф", "CNF", "Конъюнктивная нормальная форма:", MathHandler::getCNF),
    ANF("жегалкин", "ANF", "Многочлен Жегалкина:", MathHandler::getANF);

    private final String name;
    private final String podName;
    private final String capture;
    private final CommandProcessor commandProcessor;

    MathCommand(String name, String podName, String capture, CommandProcessor commandProcessor) {
        this.name = name;
        this.podName = podName;
        this.capture = capture;
        this.commandProcessor = commandProcessor;
    }

    public static MathCommand parse(String name) {
        name = name.toLowerCase();
        MathCommand[] values = values();

        for (MathCommand command: values){
            if (command.name.equals(name)) {
                return command;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public String getPodName() {
        return podName;
    }

    public String getCapture() {
        return capture;
    }

    public List<String> processCommand(WAQueryResult queryResult) {
        return commandProcessor.processCommand(queryResult);
    }

    interface CommandProcessor {
        List<String> processCommand(WAQueryResult queryResult);
    }
}
