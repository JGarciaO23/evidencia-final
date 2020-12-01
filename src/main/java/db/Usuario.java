/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


class Usuario {

    Integer IdUsuario;
    String Nombre;
    String Password;
    String Rol;

    public Integer getIdUsuario() {

        return IdUsuario;
    }

    public void setIdUsuario(Integer IdUsuario) {

        this. IdUsuario = IdUsuario;
    }

    public String getNombre() {

        return Nombre;
    }

    public void setNombre(String Nombre) {

        this.Nombre = Nombre;
    }

    public String getPassword() {

        return Password;
    }

    public void setPassword(String Password) {

        this.Password = Password;
    }

    public String getRol() {

        return Rol;
    }

    public void setRol(String Rol) {

        this.Rol = Rol;
    }

}
