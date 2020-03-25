package com.bolsadeideas.springboot.backend.apirest.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

  //id de la tabla ademas el identity me permite que se incremente automaticamente
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String nombre;
  private String apellido;
  private String email;
  @Column(name = "create_at")
  @Temporal(TemporalType.DATE)
  private Date createAt;

  //antes de que se haga un save incluya la fecha
  @PrePersist
  public void prePersist() {
    createAt = new Date();
  }

  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
