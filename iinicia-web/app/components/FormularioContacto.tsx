
"use client";

import { useState, type FormEvent } from "react";

import "./FormularioContacto.css";

const styles = {
  contenedor: "contenedor",
  encabezado: "encabezado",
  etiqueta: "etiqueta",
  formulario: "formulario",
  campo: "campo",
  boton: "boton",
  estado: "estado",
  aviso: "aviso",
};


export default function FormularioContacto() {
  const [mensaje, setMensaje] = useState("");

  function revisarFormulario(evento: FormEvent<HTMLFormElement>) {
    evento.preventDefault();

    setMensaje(
      "Formulario validado correctamente. Esta es una demostración; todavía no se ha enviado ninguna solicitud."
    );
  }

  return (
    <section className={styles.contenedor}>
      <div className={styles.encabezado}>
        <span className={styles.etiqueta}>
          CONTACTO / IINICIA
        </span>

        <h2>Solicita información</h2>

        <p>
          ¿Te interesa alguno de nuestros programas?
          Completa el formulario para conocer cómo
          funcionará la atención digital de IINICIA.
        </p>
      </div>

      <form
        className={styles.formulario}
        onSubmit={revisarFormulario}
      >
        <div className={styles.campo}>
          <label htmlFor="contacto-nombre">
            Nombre completo
          </label>
          <input
            id="contacto-nombre"
            type="text"
            placeholder="Escribe tu nombre"
            autoComplete="name"
            required
          />
        </div>

        <div className={styles.campo}>
          <label htmlFor="contacto-correo">
            Correo electrónico
          </label>
          <input
            id="contacto-correo"
            type="email"
            placeholder="ejemplo@correo.com"
            autoComplete="email"
            required
          />
        </div>

        <div className={styles.campo}>
          <label htmlFor="contacto-programa">
            Programa de interés
          </label>
          <select
            id="contacto-programa"
            defaultValue=""
            required
          >
            <option value="" disabled>
              Selecciona una opción
            </option>
            <option value="cursos">Cursos</option>
            <option value="certificaciones">
              Certificaciones
            </option>
            <option value="diplomados">
              Diplomados
            </option>
            <option value="otro">Otro programa</option>
          </select>
        </div>

        <div className={styles.campo}>
          <label htmlFor="contacto-mensaje">
            Mensaje o consulta
          </label>
          <textarea
            id="contacto-mensaje"
            placeholder="¿En qué podemos ayudarte?"
            rows={5}
            required
          />
        </div>

        <button className={styles.boton} type="submit">
          Revisar solicitud ↗
        </button>

        {mensaje && (
          <p className={styles.estado} role="status">
            {mensaje}
          </p>
        )}

        <p className={styles.aviso}>
          Formulario demostrativo. No envía ni almacena
          información personal.
        </p>
      </form>
    </section>
  );
}
