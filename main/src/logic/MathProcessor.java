package logic;

import com.wolfram.alpha.*;
import logic.enums.MathCommand;
import logic.handlers.PhrasesHandler;
import logic.interfaces.Processor;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class MathProcessor implements Processor {
    private WAEngine engine;

    public MathProcessor(Properties props) {
        engine = new WAEngine();
        engine.setAppID(props.getProperty("wolframAppID"));
        engine.addFormat("plaintext");
    }

    @Override
    public List<String> requestProcessing(User user) {
        return null;
    }

    public String getResponse(String request) {
        String[] parts = request.split(" ", 2);

        if (parts.length != 2) {
            return PhrasesHandler.getWolframIncorrectInputPhrase();
        }

        WAQuery query = engine.createQuery();
        query.setInput(parts[1]);

        WAQueryResult queryResult;
        try {
            queryResult = engine.performQuery(query);
        } catch (WAException e) {
            return PhrasesHandler.getWolframErrorPhrase();
        }

        if (queryResult.isError()){
            return PhrasesHandler.getWolframErrorPhrase();
        }
        else if (!queryResult.isSuccess()) {
            return PhrasesHandler.getWolframIncorrectInputPhrase();
        }

        Optional<String> response = Optional.empty();
        MathCommand command = MathCommand.parse(parts[0]);
        if (command != null) {
            response = Optional.ofNullable(command.processCommand(queryResult));
        }

        return response.orElseGet(PhrasesHandler::getWolframIncorrectInputPhrase);
    }

    public static String getAlternateForms(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.ALTERNATE_FORMS.getPodName()),
                MathCommand.ALTERNATE_FORMS.getCapture());
    }

    public static String getRoots(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.ROOTS.getPodName()),
                MathCommand.ROOTS.getCapture());
    }

    public static String getTruthTable(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.TRUTH_TABLE.getPodName()),
                MathCommand.TRUTH_TABLE.getCapture());
    }

    public static String getDNF(WAQueryResult queryResult) {
        return getNF(queryResult, MathCommand.DNF.getPodName(), MathCommand.DNF.getCapture());
    }

    public static String getCNF(WAQueryResult queryResult) {
        return getNF(queryResult, MathCommand.CNF.getPodName(), MathCommand.CNF.getCapture());
    }

    public static String getANF(WAQueryResult queryResult) {
        return getNF(queryResult, MathCommand.ANF.getPodName(), MathCommand.ANF.getCapture());
    }

    private static String getNF(WAQueryResult queryResult, String name, String capture) {
        String[] minimalForms = getMinimalForms(queryResult);

        if (minimalForms != null) {
            for (String line: minimalForms) {
                if (line.contains(name)) {
                    return addCapture(line.replace(name + " | ", ""), capture);
                }
            }
        }

        return null;
    }

    private static String[] getMinimalForms(WAQueryResult queryResult) {
        String text = getPodText(queryResult, MathCommand.MINIMAL_FORMS.getPodName());
        if (text != null) {
            return text.split("\n");
        }
        return null;
    }

    private static String getPodText(WAQueryResult queryResult, String podName) {
        for (WAPod pod : queryResult.getPods()) {
            if (pod.getTitle().equals(podName)) {
                StringBuilder answer = new StringBuilder();
                for (WASubpod subpod : pod.getSubpods()) {
                    for (Object element : subpod.getContents()) {
                        if (element instanceof WAPlainText) {
                            answer.append(((WAPlainText) element).getText());
                            answer.append("\n");
                        }
                    }
                }
                return answer.toString();
            }
        }
        return null;
    }

    private static String addCapture(String response, String capture) {
        if (response != null) {
            return capture + "\n" + response;
        }
        return null;
    }
}
