package logic.handlers;

public class PhrasesHandler {
    public static String getEndPhrase() {
        return "Приятно было пообщаться";
    }

    public static String getStartPhrase() {
        return "Доброго времени суток!";
    }

    public static String getHelpCommand() {
        return "/help";
    }

    public static String getExitCommand() {
        return "Выход";
    }

    public static String getQuizCommand() {
        return "Викторина";
    }

    public static String getEndDialogCommand() {
        return "/exit";
    }

    public static String getStartCommand() {
        return "/start";
    }

    public static String getMathModCommand() {
        return "Математический режим";
    }

    public static String getStartQuizPhrase() {
        return "Давайте сыграем в викторину! Если есть вопросы, то скажите: " + getHelpCommand();
    }

    public static String getQuestionOnQuiz(String question) {
        return question;
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
        return "Это викторина! Я задаю вам вопросы, ваша задача дать правильный ответ. Если вы не знаете ответ, то " +
                "просто скажите \"Сдаюсь\" и я назову верный вариант. Скажите \"Повтори вопрос\", если забыли его." +
                "\nЧтобы выйти из викторины скажите \"Выход\"";
    }

    public static String getDialogHelp() {
        return String.format("%s - вызвать справку\n%s - выйти из чата\n%s - начать викторину\n%s - выйти из чата в любой момент",
                getHelpCommand(), getExitCommand(), getQuizCommand(), getEndDialogCommand());
    }

    public static String getGiveUpCommand() {
        return "Сдаюсь";
    }

    public static String getRepeatQuestionCommand() {
        return "Повтори вопрос";
    }

    public static String getNoneCommand() {
        return "NONE";
    }

    public static String getStartHelp() {
        return "Для начала диалога введите: \"" + getStartCommand() + "\" (без кавычек)";
    }

    public static String getMathModeHelp() {
        return "ЗДЕСЬ ДОЛЖЕН БЫТЬ HELP ПО MATH_MODE STATE";
    }

    public static String getMathModePhrase() {
        return "Вы перешли в математический режим. Подробнее о режиме: " + getHelpCommand();
    }

    public static String getEndMathModePhrase() {
        return "Ждём вас в математическом режиме снова!";
    }
}
