package crud;

import javax.persistence.*;

@Entity
@Table(name = "jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "equipos_id")
    private Equipo equipos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo getEquipos() {
        return equipos;
    }

    public void setEquipos(Equipo equipo) {
        this.equipos = equipo;
    }
}
