package sistema.web.indicadores.com.security.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_rolespermisos")
public class RolPermiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdRolPermiso")
    private Integer idRolPermiso;

    @ManyToOne
    @JoinColumn(name = "RolID")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "PermisoID")
    private Permiso permiso;
}
