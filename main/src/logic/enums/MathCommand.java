package logic.enums;

import com.wolfram.alpha.WAQueryResult;
import logic.MathProcessor;

public enum MathCommand {
    ALTERNATE_FORMS("приведение", "Alternate forms", MathProcessor::getAlternateForms),
    TRUTH_TABLE("таблица", "Truth table", MathProcessor::getTruthTable),
    DNF("днф", "Minimal forms", MathProcessor::getDNF),
    CNF("днф", "Minimal forms", MathProcessor::getDNF), //надо допилить
    ANF("днф", "Minimal forms", MathProcessor::getDNF); //надо допилить

    private final String name;
    private final String podName;
    private final CommandProcessor commandProcessor;

    MathCommand(String name, String podName, CommandProcessor commandProcessor) {
        this.name = name;
        this.podName = podName;
        this.commandProcessor = commandProcessor;
    }

    public static MathCommand parse(String name) {
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

    interface CommandProcessor {
        String processCommand(WAQueryResult queryResult);
    }
}
