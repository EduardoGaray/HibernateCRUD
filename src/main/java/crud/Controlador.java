package crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controlador {


    public void insertar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.crud.jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.crud.jpa");
        PartidoH nuevojugador = new PartidoH("Pepote","Cipote","Chapote","Bullas",99);

        entityManager.persist(nuevojugador);

        entityManager.getTransaction().commit();
    }

    public void eliminar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.crud.jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Integer key = Integer.valueOf(10);
        PartidoH p = entityManager.find(PartidoH.class, key);
        entityManager.remove(p);

        entityManager.getTransaction().commit();
    }

    public void modificar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.crud.jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Integer key = Integer.valueOf(11);
        PartidoH p = entityManager.find(PartidoH.class, key);
        p.setNombre("Chinaso");

        entityManager.getTransaction().commit();
    }

    public void mostrar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.crud.jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Integer key = Integer.valueOf(11);
        PartidoH p = entityManager.find(PartidoH.class, key);
        System.out.println(p.getNombre());
        System.out.printf(p.getEquipo());

        entityManager.getTransaction().commit();
    }
}
