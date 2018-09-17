package logic.handlers;

import logic.Question;
import logic.QuestionsData;
import logic.User;
import logic.enums.Command;
import logic.enums.UserState;

public class RequestHandler {
    private QuestionsData mQuestionData;

    public RequestHandler(QuestionsData questionsData){
        mQuestionData = questionsData;
    }

    public Command tryCommandRecognition(String usersRequest) {
        if (usersRequest.equals(PhrasesHandler.getExitPhrase())) {
            return Command.exit;
        }
        else if (usersRequest.equals(PhrasesHandler.getHelpPhrase())) {
            return Command.help;
        }
        else if (usersRequest.equals(PhrasesHandler.getQuizPhrase())) {
            return Command.quiz;
        }
        return Command.none;
    }

    public String getAnswerByCommandAndRequest(Command command, String request, User user) {
        if (command == Command.exit) {
            user.setState(UserState.exit);
            return PhrasesHandler.getEndPhrase();
        }
        else if (command == Command.help) {
            user.setState(UserState.start);
            return PhrasesHandler.getHelp();
        }
        else if (command == Command.quiz) {
            user.setState(UserState.quiz);
            String answer = PhrasesHandler.getStartQuizPhrase();

            answer += "\n" + getQuestion(user);
            return answer;
        }
        else if (command == Command.none && user.getState() == UserState.quiz) {
            String answer;
            if (request.equals(user.getLastQuestion().getAnswer())) {
                return PhrasesHandler.getCorrectAnswerPhrase() + '\n' + getQuestion(user);
            }
            else {
                return PhrasesHandler.getUncorrectAnswerPhrase();
            }
        }
        return PhrasesHandler.getUnknowPhrase();
    }

    private String getQuestion(User user) {
        Question question = mQuestionData.getQuestion();
        user.setLastQuestion(question);
        return PhrasesHandler.getQuestionOnQuiz(question.getQuestion());
    }
}
