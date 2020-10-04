package com.example.ejemplolistview.models;

/*En la clase NotaModel se crea el modelo con la estructura de las variables que van a permitir la
interacción con la base datos. En este caso insertar, extraer, modificar o eliminar.
 */

//Par generar getter y setter se debe seleccionar la variable, clic derecho, refactor, encapsulate field y marcar las variables a las cuales se les van a generar los métodos.

// Generar el método toString con Generate y toString().
/* Generar tres constructores con Generate / Constructor. Un constructor vacío (select none),
    otro con título y contenido, y otro con las tres variables. Se seleccionan 3 para tener
    todas las opciones disponibles de acuerdo a las variables que quiere utilizar al inicializar
    un objeto. Tres formas diferentes de crear objetos a partir de la misma clase.
 */

public class NotaModel {
    private int _id;
    private String Titulo;
    private String Contenido;

    //Constructor vacío
    public NotaModel() {
    }

    //Constructor con dos variables
    public NotaModel(String titulo, String contenido) {
        Titulo = titulo;
        Contenido = contenido;
    }

    //Constructor con todas las variables del modelo
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
