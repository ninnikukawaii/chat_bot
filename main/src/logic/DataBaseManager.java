package logic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataBaseManager {
    private static volatile DataBaseManager instance;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private String persistenceUnitName;

    DataBaseManager(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
        initialization();
    }

    private void initialization() {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void createNewUser(User user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        if (!entityManager.contains(user)) {
            entityManager.persist(user);
        }

        entityTransaction.commit();
    }

    public User getUserById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User result = entityManager.find(User.class, id);

        entityTransaction.commit();
        return result;
    }

    public void updateDataAboutUser(User user) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.merge(user);

        entityTransaction.commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void reinitialization() {
        close();
        initialization();
    }

    public static DataBaseManager getInstance(String persistenceUnitName) {
        if (instance == null) {
            synchronized (DataBaseManager.class) {
                if (instance == null) {
                    instance = new DataBaseManager(persistenceUnitName);
                }
            }
        }
        return instance;
    }
}
