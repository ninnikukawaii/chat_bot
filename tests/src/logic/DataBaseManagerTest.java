package logic;

import logic.enums.UserState;
import logic.exception.DataBaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class DataBaseManagerTest {
    private DataBaseManager dataBaseManager;

    @Before
    public void setUp() {
        dataBaseManager = new DataBaseManager("testUnit");
        dataBaseManager.beginTransaction();
    }

    @After
    public void tearDown() throws DataBaseException {
        dataBaseManager.endTransaction();
        dataBaseManager.close();
    }

    @Test
    public void getSecondDataBaseManager() {
        DataBaseManager firstDataBaseManager = DataBaseManager.getInstance();
        DataBaseManager secondDataBaseManager = DataBaseManager.getInstance();

        assertEquals(firstDataBaseManager, secondDataBaseManager);
    }

    @Test
    public void appendNewUser() {
        dataBaseManager.createNewUser(User.defaultId);
    }

    @Test
    public void getUserFromDataBase() {
        User user = dataBaseManager.createNewUser(User.defaultId);

        User userFromDB = dataBaseManager.getUserById(User.defaultId);
        assertEquals(user, userFromDB);
    }

    @Test
    public void appendTwoEqualUser() {
        User firstUser = dataBaseManager.createNewUser(User.defaultId);
        User secondUser = dataBaseManager.createNewUser(User.defaultId);

        User userFromDB =  dataBaseManager.getUserById(User.defaultId);
        assertEquals(firstUser, secondUser);
        assertEquals(firstUser, userFromDB);
    }

    @Test
    public void getUserWhoIsNotDatabase() {
        Long firstId = 10L;
        Long secondId = 11L;

        dataBaseManager.createNewUser(firstId);

        User userFromDB = dataBaseManager.getUserById(secondId);

        assertNull(userFromDB);
    }

    @Test
    public void appendTwoUsers() {
        User firstUser = dataBaseManager.createNewUser(9L);
        User secondUser = dataBaseManager.createNewUser(10L);

        assertNotEquals(firstUser, secondUser);
    }

    @Test
    public void getTwoUser() {
        Long firstId = 10L;
        Long secondId = 11L;

        User firstUser = dataBaseManager.createNewUser(firstId);
        User secondUser = dataBaseManager.createNewUser(secondId);

        User firstUserFromDB = dataBaseManager.getUserById(firstId);
        User secondUserFromDB = dataBaseManager.getUserById(secondId);

        assertNotNull(firstUserFromDB);
        assertNotNull(secondId);

        assertEquals(firstUser, firstUserFromDB);
        assertEquals(secondUser, secondUserFromDB);

        assertNotEquals(firstUserFromDB, secondUserFromDB);
    }

    @Test
    public void updateUser() {
        User user = dataBaseManager.createNewUser(User.defaultId);

        user.setState(UserState.QUIZ);
        dataBaseManager.updateDataAboutUser(user);

        User userFromDB = dataBaseManager.getUserById(User.defaultId);
        assertEquals(user.getState(), userFromDB.getState());
    }

    @Test
    public void savedQuestionIsCorrect() {
        User user = dataBaseManager.createNewUser(User.defaultId);

        Question question = new Question("question", "answer");
        user.setLastQuestion(question);
        dataBaseManager.updateDataAboutUser(user);

        User userFromDB = dataBaseManager.getUserById(User.defaultId);
        
        assertEquals(userFromDB.getLastQuestion(), user.getLastQuestion());
    }
}
