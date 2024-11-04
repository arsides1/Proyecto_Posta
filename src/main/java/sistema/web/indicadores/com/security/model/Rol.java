package sistema.web.indicadores.com.security.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_roles")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RolID")
    private Integer usuarioID;

    @Column(name="Nombre",length = 100)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<RolPermiso> rolPermisos;

    @OneToMany(mappedBy = "rol")
    private List<UsuarioRol> usuarioRoles;
}
