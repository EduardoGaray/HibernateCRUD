package crud;

import javax.persistence.*;

@Entity
@Table(name="Jugadores")
public class PartidoH {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="nombre")
    private String nombre;
    @Column(name="primerapellido")
    private String apellido;
    @Column(name="segundoapellido")
    private String apellido2;
    @Column(name="equipo")
    private String equipo;
    @Column(name="goles")
    private int goles;

    public PartidoH(String nombre, String apellido, String apellido2, String equipo, int goles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.equipo = equipo;
        this.goles = goles;
    }

    public PartidoH(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }
}
