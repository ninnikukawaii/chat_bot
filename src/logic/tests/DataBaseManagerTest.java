package logic.tests;

import logic.DataBaseManager;
import logic.Question;
import logic.User;
import logic.enums.UserState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class DataBaseManagerTest {
    private DataBaseManager dataBaseManager;

    @Before
    public void setUp() {
        dataBaseManager = DataBaseManager.getInstance("testUnit");
    }

    @After
    public void tearDown() {
        dataBaseManager.reinitialization();
    }

    @Test
    public void getSecondDataBaseManager() {
        DataBaseManager secondDataBaseManager = DataBaseManager.getInstance("testUnit");

        assertEquals(dataBaseManager, secondDataBaseManager);
    }

    @Test
    public void appendNewUser() {
        User user = new User();
        dataBaseManager.createNewUser(user);
    }

    @Test
    public void getUserFromDataBase() {
        User user = new User();
        dataBaseManager.createNewUser(user);

        User userFromDB = dataBaseManager.getUserById(User.defaultId);
        assertEquals(user, userFromDB);
    }

    @Test
    public void appendTwoEqualUser() {
        User user = new User();

        dataBaseManager.createNewUser(user);
        dataBaseManager.createNewUser(user);

        User userFromDB =  dataBaseManager.getUserById(User.defaultId);
        assertEquals(user, userFromDB);
    }

    @Test
    public void getUserWhoIsNotDatabase() {
        Long firstId = 10L;
        Long secondId = 11L;

        User user = new User(firstId);
        dataBaseManager.createNewUser(user);

        User userFromDB = dataBaseManager.getUserById(secondId);

        assertNull(userFromDB);
    }

    @Test
    public void appendTwoUsers() {
        User firstUser = new User(9L);
        User secondUser = new User(10L);

        dataBaseManager.createNewUser(firstUser);
        dataBaseManager.createNewUser(secondUser);
    }

    @Test
    public void getTwoUser() {
        Long firstId = 10L;
        Long secondId = 11L;

        User firstUser = new User(firstId);
        User secondUser = new User(secondId);

        dataBaseManager.createNewUser(firstUser);
        dataBaseManager.createNewUser(secondUser);

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
        User user = new User();
        dataBaseManager.createNewUser(user);

        user.setState(UserState.QUIZ);
        dataBaseManager.updateDataAboutUser(user);

        User userFromDB = dataBaseManager.getUserById(User.defaultId);
        assertEquals(user.getState(), userFromDB.getState());
    }
}
