package com.example.ejemplolistview.models;

/*En este espacio se esta creando la clase para luego generar varios objetos
de acuerdo a la necesidad de variables que deseo para insertar en la BD, para consultar,
para actualizar o para eliminar.
 */

//Seleccionar la variable, refactor, encapsulate field para generar los métodos get y set.

// Generar el método toString con Generate y toString()
/* Generar tres constructores con Generate / Constructor. Un constructor vacío (select none),
    otro con título y contenido, y otro con las tres variables. Se seleccionan 3 para tener
    todas las opciones disponibles de acuerdo a las variables que quiere utilizar al inicializar
    un objeto. Tres formas diferentes de crear objetos a partir de la misma clase.
 */

public class NotaModel {
    private int _id;
    private String Titulo;
    private String Contenido;

    public NotaModel() {
    }

    public NotaModel(String titulo, String contenido) {
        Titulo = titulo;
        Contenido = contenido;
    }

    public NotaModel(int _id, String titulo, String contenido) {
        this._id = _id;
        Titulo = titulo;
        Contenido = contenido;
    }

    @Override
    public String toString() {
        return "NotaModel{" +
                "_id=" + _id +
                ", Titulo='" + Titulo + '\'' +
                ", Contenido='" + Contenido + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }
}
