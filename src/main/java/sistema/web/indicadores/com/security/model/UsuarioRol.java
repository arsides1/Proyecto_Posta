package sistema.web.indicadores.com.security.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_usuariosroles")
public class UsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioroles;

    @ManyToOne
    @JoinColumn(name = "UsuarioID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "RolID")
    private Rol rol;
}
