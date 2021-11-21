package crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Controlador {

    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.crud.jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Equipo equipo = new Equipo();
        equipo.setNombre("Universidad de Las Palmas de Gran Canaria");

        Jugador jugador = new Jugador();
        jugador.setNombre("Ana");

        Jugador jugador2 = new Jugador();
        jugador2.setNombre("Jonathan");

        equipo.setJugadores(List.of(jugador, jugador2));

        entityManager.persist(equipo);
        entityManager.getTransaction().commit();
    }


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
