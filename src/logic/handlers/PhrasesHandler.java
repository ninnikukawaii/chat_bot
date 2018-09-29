package logic.handlers;

public class PhrasesHandler {
    public static String getEndPhrase() {
        return "Приятно было пообщаться";
    }

    public static String getStartPhrase() {
        return "Доброго времени суток!";
    }

    public static String getHelpCommand() {
        return "Помощь";
    }

    public static String getExitCommand() {
        return "Выход";
    }

    public static String getQuizCommand() {
        return "Викторина";
    }

    public static String getStartQuizPhrase() {
        return "Давайте сыграем в викторину! Если есть вопросы, то скажите \"Помощь\"";
    }

    public static String getQuestionOnQuiz(String qiestion) {
        return qiestion;
    }

    public static String getCorrectAnswerPhrase() {
        return "Верно!";
    }

    public static String getIncorrectAnswerPhrase() {
        return "Неверно! Попробуйте ещё раз!";
    }

    public static String getCorrectAnswerInQuiz(String answer) {
        return "Правильный ответ был: " + answer;
    }

    public static String getUnknownPhrase() {
        return "Простите, я вас не понимаю... Посмотрите раздел \"помощь\"";
    }

    public static String getEndQuizPhrase() {
        return "Что же, викторина окончена!";
    }

    public static String getQuizHelp() {
        return "Это викторина! Я задаюм вам вопросы, ваша задача дать правильный ответ. Если вы не знаете ответ, то " +
                "просто скажите \"Сдаюсь\" и я назову верный вариант. Скажите \"Повтори вопрос\", если забыли его." +
                "\nЧтобы выйти из викторины скажите \"Выход\"";
    }

    public static String getHelp() {
        return "Помощь - вызвать справку\nВыход - выйти из чата\nВикторина - начать викторину";
    }

    public static String getGiveUpCommand() {
        return "Сдаюсь";
    }

    public static String getRepeatQuestionCommand() {
        return "Повтори вопрос";
    }
}
