package sistema.web.indicadores.com.security.model;
import jakarta.persistence.*;
import lombok.Data;
import sistema.web.indicadores.com.model.dto.TipoEstado;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_permisos")
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PermisoID")
    private Integer permisoID;

    @Column(name="Nombre",length = 100, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "IdEstado")
    private TipoEstado estado;

    @OneToMany(mappedBy = "permiso")
    private List<RolPermiso> rolPermisos;
}
