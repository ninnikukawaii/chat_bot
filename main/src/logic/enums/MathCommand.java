package logic.enums;

import com.wolfram.alpha.WAQueryResult;
import logic.MathProcessor;

public enum MathCommand {
    ALTERNATE_FORMS("приведение", "Alternate forms", "Другие формы:", MathProcessor::getAlternateForms),
    TRUTH_TABLE("таблица", "Truth table", "Таблица истинности:", MathProcessor::getTruthTable),
    MINIMAL_FORMS("к", "Minimal forms", "", (queryResult) -> null),
    DNF("днф", "DNF", "Дизъюнктивная нормальная форма:", MathProcessor::getDNF),
    CNF("кнф", "CNF", "Конъюнктивная нормальная форма:", MathProcessor::getCNF),
    ANF("жегалкин", "ANF", "Многочлен Жегалкина:", MathProcessor::getANF);

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

    public String processCommand(WAQueryResult queryResult) {
        return commandProcessor.processCommand(queryResult);
    }

    interface CommandProcessor {
        String processCommand(WAQueryResult queryResult);
    }
}
