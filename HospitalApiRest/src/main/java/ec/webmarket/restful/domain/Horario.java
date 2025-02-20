package ec.webmarket.restful.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horaInicio;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horafinal;

    @Column(nullable = false)
    private Boolean disponibilidad;

    @OneToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;
    
}
