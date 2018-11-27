package logic;

import logic.exception.DataBaseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataBaseManager {
    private static volatile DataBaseManager instance;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    private static final String defaultPersistenceUnitName = "chat-bot";

    DataBaseManager(String persistenceUnitName) {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void beginTransaction() {
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    public void endTransaction() throws DataBaseException {
        if (entityTransaction == null) {
            throw new DataBaseException("Transaction don't began");
        }
        entityTransaction.commit();
        entityTransaction = null;
    }

    public User getNewUser(Long id) {
        User user = findUserById(id);

        if (user == null) {
            user = new User(id);
            entityManager.persist(user);
        }

        return user;
    }

    public User findUserById(Long id) {

        return entityManager.find(User.class, id);
    }

    public User recreateUser(Long id) {
        User user = new User(id);
        return entityManager.merge(user);
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public static DataBaseManager getInstance() {
        if (instance == null) {
            synchronized (DataBaseManager.class) {
                if (instance == null) {
                    instance = new DataBaseManager(defaultPersistenceUnitName);
                }
            }
        }
        return instance;
    }
}
