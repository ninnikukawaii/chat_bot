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

        assertSame(firstDataBaseManager, secondDataBaseManager);
    }

    @Test
    public void getUserFromDataBase() {
        User user = dataBaseManager.getUser(User.defaultId);

        User userFromDB = dataBaseManager.findUserById(User.defaultId);
        assertEquals(user, userFromDB);
    }

    @Test
    public void appendTwoEqualUser() {
        User firstUser = dataBaseManager.getUser(User.defaultId);
        User secondUser = dataBaseManager.getUser(User.defaultId);

        User userFromDB =  dataBaseManager.findUserById(User.defaultId);
        assertEquals(firstUser, secondUser);
        assertEquals(firstUser, userFromDB);
    }

    @Test
    public void getUserWhoIsNotDatabase() {
        Long firstId = 10L;
        Long secondId = 11L;

        dataBaseManager.getUser(firstId);

        User userFromDB = dataBaseManager.findUserById(secondId);

        assertNull(userFromDB);
    }

    @Test
    public void appendTwoUsers() {
        User firstUser = dataBaseManager.getUser(9L);
        User secondUser = dataBaseManager.getUser(10L);

        assertNotEquals(firstUser, secondUser);
    }

    @Test
    public void findUserWhoIsNotDatabase() {
        User userFromDB = dataBaseManager.findUserById(User.defaultId);

        assertNull(userFromDB);
    }

    @Test
    public void getUserInNewTransaction() throws DataBaseException {
        User user = dataBaseManager.getUser(User.defaultId);

        dataBaseManager.endTransaction();
        dataBaseManager.beginTransaction();

        User userFromDB = dataBaseManager.findUserById(User.defaultId);

        assertEquals(user, userFromDB);
    }

    @Test
    public void savingUserChange() throws DataBaseException {
        User user = dataBaseManager.getUser(User.defaultId);
        user.setState(UserState.DIALOG);

        dataBaseManager.endTransaction();
        dataBaseManager.beginTransaction();

        User userFromDB = dataBaseManager.findUserById(User.defaultId);

        assertEquals(UserState.DIALOG, userFromDB.getState());
    }

    @Test
    public void recreateUser() {
        User user = dataBaseManager.getUser(User.defaultId);
        user.setState(UserState.DIALOG);

        User recreatedUser = dataBaseManager.recreateUser(User.defaultId);
        User defaultUser = new User(User.defaultId);

        assertEquals(recreatedUser, defaultUser);
    }

    @Test
    public void savedQuestionIsCorrect() throws DataBaseException {
        User user = dataBaseManager.getUser(User.defaultId);

        Question question = new Question("question", "answer");
        user.setLastQuestion(question);

        dataBaseManager.endTransaction();
        dataBaseManager.beginTransaction();

        User userFromDB = dataBaseManager.findUserById(User.defaultId);
        
        assertEquals(userFromDB.getLastQuestion(), user.getLastQuestion());
    }
}
