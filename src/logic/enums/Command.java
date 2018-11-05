package logic.enums;

import logic.User;
import logic.handlers.PhrasesHandler;
import logic.interfaces.Processing;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public enum Command {
=======
public enum Command implements Serializable, Processing {
>>>>>>> bf913f8e3ea0c807f01c14c45a92d1e63af6d88d
    START (PhrasesHandler.getStartCommand()) {
        @Override
        public List<String> applicationProcessing(String request, User user) {
            List<String> answer = new ArrayList<>();

            user.setState(UserState.DIALOG);

            answer.add(PhrasesHandler.getStartPhrase());
            return answer;
        }
    },
    HELP (PhrasesHandler.getHelpCommand()) {
        @Override
        public List<String> applicationProcessing(String request, User user) {
            List<String> answer = new ArrayList<>();

            answer.add(user.getState().getHelp());

            return answer;
        }
    },
    EXIT (PhrasesHandler.getExitCommand()) {
        @Override
        public List<String> applicationProcessing(String request, User user) {
            List<String> answer = new ArrayList<>();

            if (user.getState() == UserState.DIALOG) {
                user.setState(UserState.EXIT);

                answer.add(PhrasesHandler.getEndPhrase());
            }
            else if (user.getState() == UserState.QUIZ) {
                user.setState(UserState.DIALOG);

                answer.add(PhrasesHandler.getEndQuizPhrase());
            }
            return answer;
        }
    },
    QUIZ (PhrasesHandler.getQuizCommand()) {
        @Override
        public List<String> applicationProcessing(String request, User user) {
            List<String> answer = new ArrayList<>();

            if (user.getState() == UserState.DIALOG) {
                user.setState(UserState.QUIZ);

                answer.add(PhrasesHandler.getStartQuizPhrase());
                user.setNewQuestion(true);
            }
            return answer;
        }
    },
    GIVE_UP (PhrasesHandler.getGiveUpCommand()) {
        @Override
        public List<String> applicationProcessing(String request, User user) {
            List<String> answer = new ArrayList<>();

            if (user.getState() == UserState.QUIZ) {
                answer.add(PhrasesHandler.getCorrectAnswerInQuiz(user.getLastQuestion().getAnswer()));
                user.setNewQuestion(true);
            }
            return answer;
        }
    },
    REPEAT_QUESTION (PhrasesHandler.getRepeatQuestionCommand()) {
        @Override
        public List<String> applicationProcessing(String request, User user) {
            List<String> answer = new ArrayList<>();

            if (user.getState() == UserState.QUIZ) {
                answer.add(user.getLastQuestion().getQuestion());
            }
            return answer;
        }
    },
    END_DIALOG (PhrasesHandler.getEndDialogCommand()) {
        @Override
        public List<String> applicationProcessing(String request, User user) {
            List<String> answer = new ArrayList<>();

            user.setState(UserState.EXIT);
            answer.add(PhrasesHandler.getEndPhrase());
            return answer;
        }
    };

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command valueByString(String value){
        value = value.toLowerCase();
        for (Command command : Command.values()) {
            if (value.equalsIgnoreCase(command.toString().toLowerCase())) {
                return command;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public abstract List<String> applicationProcessing(String request, User user);
}
