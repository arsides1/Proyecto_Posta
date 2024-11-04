package sistema.web.indicadores.com.security.model;

import jakarta.persistence.*;
import lombok.Data;
import sistema.web.indicadores.com.model.dto.TipoDocumentoDTO;
import sistema.web.indicadores.com.model.dto.TipoEstado;
import sistema.web.indicadores.com.model.dto.TipoSexoDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="UsuarioID")
    private Integer usuarioID;

    @Column(name= "Nombre", length = 100)
    private String nombre;

    @Column(name ="Apellidos", length= 50)
    private String apellidos;

    @Column(name ="CorreoElectronico", length = 100)
    private String correoElectronico;

    @Column(name="FechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name ="Contrasena", length = 255)
    private String contrasena;

    @Column(name="FechaRegistro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "IdTipoSexo")
    private TipoSexoDTO tipoSexo;

    @ManyToOne
    @JoinColumn(name = "IdTipoDocumento")
    private TipoDocumentoDTO tipoDocumento;

    @Column(length = 15)
    private String numeroDocumento;

    @ManyToOne
    @JoinColumn(name = "IdEstado")
    private TipoEstado estado;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRol> usuarioRoles;
}
