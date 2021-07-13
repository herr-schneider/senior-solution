package activityTracker;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ActivityTrackerDao {

    @Autowired
    private ModelMapper modelMapper;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void createActivity() {
        entityManager.getTransaction().begin();
        Activity activity = new Activity();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public ActivityDto findActivityByID(long id) {
        Activity activity = entityManager.find(Activity.class, id);
        entityManager.close();
        entityManagerFactory.close();
        return modelMapper.map(activity, ActivityDto.class);
    }

    public void deleteByID(long id) {
        entityManager.getTransaction().begin();
        Activity activity = entityManager.find(Activity.class, id);
        entityManager.remove(activity);
        entityManager.getTransaction().commit();
    }

    public List<Activity> findAllActivity(String type) {
        entityManager.getTransaction().begin();
        List<Activity> result = entityManager.createQuery("select a from Activity a",
                Activity.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return result;
    }
}
