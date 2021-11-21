package crud;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nombre")
    private String nombre;
    @Column(name = "observaciones")
    private String observaciones;

    @OneToMany(mappedBy = "equipos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugador> jugadores;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
