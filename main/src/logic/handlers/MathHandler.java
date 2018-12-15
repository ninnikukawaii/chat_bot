package logic.handlers;

import com.wolfram.alpha.*;
import logic.enums.MathCommand;

import java.util.*;

public class MathHandler {
    private WAEngine engine;

    public MathHandler(Properties props) {
        engine = new WAEngine();
        engine.setAppID(props.getProperty("wolframAppID"));
        engine.addFormat("plaintext");
    }

    public List<String> getResponse(String request) {
        String[] parts = request.split(" ", 2);

        if (parts.length != 2) {
            return Collections.singletonList(PhrasesHandler.getWolframIncorrectInputPhrase());
        }

        WAQuery query = engine.createQuery();
        query.setInput(parts[1]);

        WAQueryResult queryResult;
        try {
            queryResult = engine.performQuery(query);
        } catch (WAException e) {
            return Collections.singletonList(PhrasesHandler.getWolframErrorPhrase());
        }

        if (queryResult.isError()){
            return Collections.singletonList(PhrasesHandler.getWolframErrorPhrase());
        }
        else if (!queryResult.isSuccess()) {
            return Collections.singletonList(PhrasesHandler.getWolframIncorrectInputPhrase());
        }

        Optional<List<String>> response = Optional.empty();
        MathCommand command = MathCommand.parse(parts[0]);
        if (command != null) {
            response = Optional.ofNullable(command.processCommand(queryResult));
        }

        return response.orElseGet(() -> Collections.singletonList(
                PhrasesHandler.getWolframIncorrectInputPhrase()));
    }

    public static List<String> getAlternateForms(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.ALTERNATE_FORMS.getPodName()),
                MathCommand.ALTERNATE_FORMS.getCapture());
    }

    public static List<String> getRoots(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.ROOTS.getPodName()),
                MathCommand.ROOTS.getCapture());
    }

    public static List<String> getTruthTable(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.TRUTH_TABLE.getPodName()),
                MathCommand.TRUTH_TABLE.getCapture());
    }

    public static List<String> getDNF(WAQueryResult queryResult) {
        return getNF(queryResult, MathCommand.DNF.getPodName(), MathCommand.DNF.getCapture());
    }

    public static List<String> getCNF(WAQueryResult queryResult) {
        return getNF(queryResult, MathCommand.CNF.getPodName(), MathCommand.CNF.getCapture());
    }

    public static List<String> getANF(WAQueryResult queryResult) {
        return getNF(queryResult, MathCommand.ANF.getPodName(), MathCommand.ANF.getCapture());
    }

    private static List<String> getNF(WAQueryResult queryResult, String name, String capture) {
        List<String> minimalForms = getMinimalForms(queryResult);

        if (minimalForms != null) {
            for (String line: minimalForms) {
                if (line.contains(name)) {
                    return addCapture(Collections.singletonList(
                            line.replace(name + " | ", "")), capture);
                }
            }
        }

        return null;
    }

    private static List<String> getMinimalForms(WAQueryResult queryResult) {
        List<String> text = getPodText(queryResult, MathCommand.MINIMAL_FORMS.getPodName());
        if (text == null) {
            return null;
        }

        List<String> result = new ArrayList<>();
        for (String line: text) {
            result.addAll(Arrays.asList(line.split("\n")));
        }
        return result;
    }

    private static List<String> getPodText(WAQueryResult queryResult, String podName) {
        for (WAPod pod : queryResult.getPods()) {
            if (pod.getTitle().toLowerCase().contains(podName)) {
                List<String> answer = new ArrayList<>();
                for (WASubpod subpod : pod.getSubpods()) {
                    for (Object element : subpod.getContents()) {
                        if (element instanceof WAPlainText) {
                            answer.add(((WAPlainText) element).getText() + "\n");
                        }
                    }
                }
                return answer;
            }
        }
        return null;
    }

    private static List<String> addCapture(List<String> response, String capture) {
        if (response != null) {
            return new ArrayList<String>() {{add(capture); addAll(response);}};
        }
        return null;
    }
}
