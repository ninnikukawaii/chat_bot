package logic;

import logic.handlers.MathHandler;
import logic.handlers.PhrasesHandler;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class MathHandlerTest {
    private static MathHandler mathHandler;

    @BeforeClass
    public static void initialize() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("resources/config.properties"));
        mathHandler = new MathHandler(props);
    }

    @Test
    public void IncorrectRequest() {
        List<String> answer = mathHandler.getResponse("Wrong request");
        assertThat(answer, hasItem(PhrasesHandler.getWolframIncorrectInputPhrase()));
    }

    @Test
    public void IncorrectRequestWithWolframError() {
        List<String> answer = mathHandler.getResponse("word response");
        assertThat(answer, hasItem(PhrasesHandler.getWolframErrorPhrase()));
    }

    @Test
    public void getAlternateForms() {
        List<String> answer = mathHandler.getResponse("приведение (x + 2)^2");
        assertThat(answer, hasItem("Другие формы:"));
        assertThat(answer, hasItem("x (x + 4) + 4\n"));
    }

    @Test
    public void getRoots(){
        List<String> answer = mathHandler.getResponse("корни (x + 2)^2");
        assertThat(answer, hasItem("Корни многочлена:"));
        assertThat(answer, hasItem("x = -2\n"));
    }

    @Test
    public void getTruthTable(){
        List<String> answer = mathHandler.getResponse("таблица x && y");
        assertThat(answer, hasItem("Таблица истинности:"));
        assertThat(answer, hasItem("x | y | x ∧ y\nT | T | T\nT | F | F\nF | T | F\nF | F | F\n"));
    }

    @Test
    public void getDNF() {
        List<String> answer = mathHandler.getResponse("ДНФ x && y && x");
        assertThat(answer, hasItem("Дизъюнктивная нормальная форма:"));
        assertThat(answer, hasItem("x ∧ y"));
    }

    @Test
    public void getCNF() {
        List<String> answer = mathHandler.getResponse("КНФ x && y && x");
        assertThat(answer, hasItem("Конъюнктивная нормальная форма:"));
        assertThat(answer, hasItem("x ∧ y"));
    }

    @Test
    public void getANF() {
        List<String> answer = mathHandler.getResponse("жегалкин x && y && x");
        assertThat(answer, hasItem("Многочлен Жегалкина:"));
        assertThat(answer, hasItem("x ∧ y"));
    }
}
