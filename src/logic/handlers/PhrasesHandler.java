package logic.handlers;

public class PhrasesHandler {
    public static String getEndPhrase() {
        return "Приятно было общаться";
    }

    public static String getStartPhrase() {
        return "Доброго времени суток!";
    }

    public static String getHelpPhrase() {
        return "помощь";
    }

    public static String getExitPhrase() {
        return "выход";
    }

    public static String getQuizPhrase() {
        return "викторина";
    }

    public static String getStartQuizPhrase() {
        return "Давайте сыграем в викторину!";
    }

    public static String getQuestionOnQuiz(String qiestion) {
        return qiestion;
    }

    public static String getCorrectAnswerPhrase() {
        return "Верно!";
    }

    public static String getUncorrectAnswerPhrase() {
        return "Неверно! Попробуйте ещё раз!";
    }

    public static String getUnknowPhrase() {
        return "Простите, я вас не понимаю... Посмотрите раздел \"помощь\"";
    }

    public static String getHelp() {
        return "Помощь - вызвать справку\nВыход - выйти из чата\nВикторина - начать викторину";
    }
}
