
"use client";

import Image from "next/image";
import Link from "next/link";
import { useState, type FormEvent } from "react";
import "./iniciar-sesion.css";

export default function IniciarSesionPage() {
  const [mostrarClave, setMostrarClave] = useState(false);
  const [mensaje, setMensaje] = useState("");

  async function enviarFormulario(
    evento: FormEvent<HTMLFormElement>
  ) {
    evento.preventDefault();
    setMensaje("");

    const datos = new FormData(evento.currentTarget);

    const identificador = String(
      datos.get("identificador") ?? ""
    ).trim();

    const contrasena = String(datos.get("clave") ?? "");

    if (!identificador || !contrasena) {
      setMensaje("Completa todos los campos.");
      return;
    }

    try {
      const respuesta = await fetch(
        "http://localhost:3001/auth/login",
        {
          method: "POST",
          credentials: "include",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            identificador,
            contrasena,
          }),
        }
      );

      if (!respuesta.ok) {
        setMensaje(
          respuesta.status === 401
            ? "Usuario o contraseña incorrectos."
            : "No fue posible iniciar sesión."
        );
        return;
      }

      // Inicio de sesión correcto:
      // Enviar al usuario a su panel personalizado.
      window.location.assign("/panel");

    } catch {
      setMensaje(
        "No se pudo conectar con el servidor de IINICIA."
      );
    }
  }

  return (
    <main className="acceso_pagina">
      <div className="acceso_luz" aria-hidden="true" />

      <header className="acceso_encabezado">
        <Link
          href="/"
          className="acceso_logotipo"
          aria-label="Volver al inicio de IINICIA"
        >
          <Image
            src="/imagenes/Logo-con-Blanco-1.png"
            alt="Logotipo oficial de IINICIA"
            width={175}
            height={75}
            className="acceso_logoImagen"
            priority
          />
        </Link>

        <nav
          className="acceso_navegacion"
          aria-label="Navegación de cuenta"
        >
          <Link href="/">Inicio</Link>
          <Link href="/cursos">Cursos</Link>
          <Link
            href="/registro"
            className="acceso_navDestacado"
          >
            Registrarse
          </Link>
        </nav>
      </header>

      <div className="acceso_contenido">
        <section className="acceso_introduccion">
          <div className="acceso_etiqueta">
            <span className="acceso_punto" />
            IINICIA / TU CAMPUS DIGITAL
          </div>

          <h1>
            Tu espacio para <em>seguir creciendo.</em>
          </h1>

          <p>
            Tu comunidad, tus experiencias y tus
            oportunidades de aprendizaje, conectadas
            en un solo lugar.
          </p>

          <div className="acceso_linea" />

          <div className="acceso_ventajas">
            <div>
              <strong>01</strong>
              <span>Acceso personal</span>
            </div>

            <div>
              <strong>02</strong>
              <span>Formación conectada</span>
            </div>

            <div>
              <strong>03</strong>
              <span>Integración futura con Moodle</span>
            </div>
          </div>

          <p className="acceso_aclaracion">
            Autenticación de cuentas disponible.
            Los servicios académicos continúan
            en desarrollo.
          </p>
        </section>

        <section
          className="acceso_tarjeta"
          aria-labelledby="titulo-login"
        >
          <div className="acceso_superiorTarjeta">
            <span className="acceso_microTexto">
              ACCESO IINICIA / 02
            </span>

            <span className="acceso_estado">
              Acceso disponible
            </span>
          </div>

          <h2 id="titulo-login">
            Bienvenido de nuevo
          </h2>

          <p className="acceso_descripcion">
            Ingresa tus datos para acceder a tu
            panel personalizado.
          </p>

          <form
            onSubmit={enviarFormulario}
            className="acceso_formulario"
          >
            <label htmlFor="identificador">
              Usuario o correo electrónico
            </label>

            <input
              id="identificador"
              name="identificador"
              placeholder="Tu usuario o correo"
              autoComplete="username"
              required
            />

            <label htmlFor="clave">
              Contraseña
            </label>

            <div className="acceso_campoClave">
              <input
                id="clave"
                name="clave"
                type={mostrarClave ? "text" : "password"}
                placeholder="Tu contraseña"
                autoComplete="current-password"
                required
              />

              <button
                type="button"
                onClick={() =>
                  setMostrarClave(!mostrarClave)
                }
                aria-label={
                  mostrarClave
                    ? "Ocultar contraseña"
                    : "Mostrar contraseña"
                }
              >
                {mostrarClave ? "Ocultar" : "Ver"}
              </button>
            </div>

            <button
              className="acceso_principal"
              type="submit"
            >
              Iniciar sesión
              <span aria-hidden="true"> ↗</span>
            </button>

            {mensaje && (
              <p
                className="acceso_mensaje"
                role="status"
              >
                {mensaje}
              </p>
            )}
          </form>

          <p className="acceso_pieTarjeta">
            ¿Aún no tienes cuenta?{" "}
            <Link href="/registro">
              Regístrate aquí
            </Link>
          </p>
        </section>
      </div>

      <footer className="acceso_pie">
        © 2026 IINICIA · Plataforma Digital Universitaria
      </footer>
    </main>
  );
}
