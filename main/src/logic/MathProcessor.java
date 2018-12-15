package logic;

import com.wolfram.alpha.*;
import logic.enums.MathCommand;
import logic.interfaces.Processor;

import java.util.List;
import java.util.Properties;

public class MathProcessor implements Processor {
    private WAEngine engine;
    private final String format = "plaintext";

    public MathProcessor(Properties props) {
        engine = new WAEngine();
        engine.setAppID(props.getProperty("wolframAppID"));
        engine.addFormat(format);
    }

    @Override
    public List<String> requestProcessing(User user) {
        return null;
    }

    public String getResponse(String request) throws WAException {
        WAQuery query = engine.createQuery();
        query.setInput(request);

        WAQueryResult queryResult = engine.performQuery(query);

        if (queryResult.isError()){
            return "Ошибка соединения";
        }
        else if (!queryResult.isSuccess()) {
            return "Ошибка обработки";
        }

        return getTruthTable(queryResult);
    }

    public static String getAlternateForms(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.ALTERNATE_FORMS.getPodName()),
                "Другие формы:");
    }

    public static String getTruthTable(WAQueryResult queryResult) {
        return addCapture(getPodText(queryResult, MathCommand.TRUTH_TABLE.getPodName()),
                "Таблица истинности:");
    }

    public static String getDNF(WAQueryResult queryResult) {
        String[] minimalForms = getMinimalForms(queryResult);

        if (minimalForms != null) {
            for (String line: minimalForms) {
                if (line.contains("DNF")) {
                    return addCapture(line.replace("DNF | ", ""),
                            "Дизъюнктивная нормальная форма:");
                }
            }
        }

        return null;
    }

    private static String[] getMinimalForms(WAQueryResult queryResult) {
        String text = getPodText(queryResult, "Minimal forms");
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
