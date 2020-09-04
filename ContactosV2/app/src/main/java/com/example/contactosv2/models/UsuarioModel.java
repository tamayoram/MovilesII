package com.example.contactosv2.models;

import java.io.Serializable;

public class UsuarioModel implements Serializable {
    private int _id;
    private String _nombre;
    private String _usuario;
    private String _contrasena;

    public UsuarioModel() {
    }

    public UsuarioModel(String _nombre, String _usuario, String _contrasena) {
        this._nombre = _nombre;
        this._usuario = _usuario;
        this._contrasena = _contrasena;
    }

    public UsuarioModel(int _id, String _nombre, String _usuario, String _contrasena) {
        this._id = _id;
        this._nombre = _nombre;
        this._usuario = _usuario;
        this._contrasena = _contrasena;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_usuario() {
        return _usuario;
    }

    public void set_usuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String get_contrasena() {
        return _contrasena;
    }

    public void set_contrasena(String _contrasena) {
        this._contrasena = _contrasena;
    }
}
