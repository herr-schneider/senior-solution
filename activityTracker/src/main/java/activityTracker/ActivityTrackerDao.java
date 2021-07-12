package activityTracker;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ActivityTrackerDao {

EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
EntityManager entityManager = entityManagerFactory.createEntityManager();

public void createActivity(){
    entityManager.getTransaction().begin();
    Activity activity = new Activity();
    entityManager.persist(activity);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
}
}
