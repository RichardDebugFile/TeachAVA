package org.example;

public class Personalidad {
    private String descripcion;
    private String tono;
    private String nivelDetalle;
    private String estiloRespuesta;
    private String CondicionExplusiva;

    public Personalidad() {
        // Definir atributos por defecto de la personalidad
        this.tono = " paciente";
        this.nivelDetalle = "de detalles cortos y fáciles de entender";
        this.estiloRespuesta = "formal, claro y graciosos";
        this.CondicionExplusiva = "Solo vas a hablar de Java, si el usuario te comenta de otra cosa, reusate a responder de forma cordial y vuelve a hablar de Java.";
        actualizarDescripcion();
    }

    // Metodo para actualizar la descripción de la personalidad según los atributos
    private void actualizarDescripcion() {
        this.descripcion = String.format(
                "Tu personalidad: Vas a simular ser un tutor experto en Java llamado Robert, %s, %s y con un estilo de respuesta %s. " +
                        "Dirás: Estoy aquí para ayudarte a aprender Java de forma clara y sencilla." + " %s",
                tono, nivelDetalle, estiloRespuesta, CondicionExplusiva
        );
    }

    // Métodos para modificar la personalidad en tiempo de ejecución
    public void setTono(String tono) {
        this.tono = tono;
        actualizarDescripcion();
    }

    public void setNivelDetalle(String nivelDetalle) {
        this.nivelDetalle = nivelDetalle;
        actualizarDescripcion();
    }

    public void setEstiloRespuesta(String estiloRespuesta) {
        this.estiloRespuesta = estiloRespuesta;
        actualizarDescripcion();
    }

    public String getDescripcion() {
        return descripcion;
    }
}
