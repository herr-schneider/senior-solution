package employeesspringjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ParkingPlaceDao {

private EntityManagerFactory entityManagerFactory;

    public ParkingPlaceDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveParking(ParkingPlace parkingPlace){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(parkingPlace);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void findAParkingPlace(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.find(ParkingPlace.class, id);
        entityManager.close();
    }

    public ParkingPlace findParkingPlace(int num){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ParkingPlace result = entityManager.createQuery("select p from ParkingPlace p where p.number = :num", ParkingPlace.class)
                .setParameter("num", num)
                .getSingleResult();
        entityManager.close();
        return result;
    }
}
