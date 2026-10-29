
"use client";

import Image from "next/image";
import Link from "next/link";
import { useState, type FormEvent } from "react";
import "./registro.css";

export default function RegistroPage() {
  const [mostrarClave, setMostrarClave] = useState(false);
  const [mostrarConfirmacion, setMostrarConfirmacion] = useState(false);
  const [mensaje, setMensaje] = useState("");
  const [clave, setClave] = useState("");
  const [confirmacion, setConfirmacion] = useState("");
  const [enviando, setEnviando] = useState(false);

  async function enviarFormulario(evento: FormEvent<HTMLFormElement>) {
    evento.preventDefault();

    if (enviando) return;

    setMensaje("");

    if (clave !== confirmacion) {
      setMensaje("Las contraseñas no coinciden. Revisa los campos.");
      return;
    }

    const formulario = evento.currentTarget;
    const datos = new FormData(formulario);

    const nombre_usuario = String(datos.get("usuario") ?? "").trim();
    const correo = String(datos.get("correo") ?? "").trim();

    if (!nombre_usuario || !correo || !clave) {
      setMensaje("Completa todos los campos.");
      return;
    }

    setEnviando(true);

    try {
      const respuesta = await fetch(
        "http://localhost:3001/usuarios/registro",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            nombre_usuario,
            correo,
            contrasena: clave,
          }),
        }
      );

      const resultado: {
        mensaje?: string | string[];
      } = await respuesta.json();

      if (!respuesta.ok) {
        const error = Array.isArray(resultado.mensaje)
          ? resultado.mensaje.join(". ")
          : resultado.mensaje;

        setMensaje(error || "No se pudo crear la cuenta.");
        return;
      }

      setMensaje(
        "¡Cuenta registrada correctamente! Tus datos se guardaron en IINICIA."
      );

      formulario.reset();
      setClave("");
      setConfirmacion("");

    } catch {
      setMensaje(
        "No fue posible conectar con el servidor. Verifica que NestJS esté funcionando."
      );
    } finally {
      setEnviando(false);
    }
  }

  return (
    <main className="registro_pagina">
      <div className="registro_luz" aria-hidden="true" />

      <header className="registro_encabezado">
        <Link
          href="/"
          className="registro_logotipo"
          aria-label="Volver al inicio de IINICIA"
        >
          <Image
            src="/imagenes/Logo-con-Blanco-1.png"
            alt="Logotipo oficial de IINICIA"
            width={175}
            height={75}
            className="registro_logoImagen"
            priority
          />
        </Link>

        <nav
          className="registro_navegacion"
          aria-label="Navegación de cuenta"
        >
          <Link href="/">Inicio</Link>
          <Link href="/cursos">Cursos</Link>
          <Link
            href="/iniciar-sesion"
            className="registro_navDestacado"
          >
            Iniciar sesión
          </Link>
        </nav>
      </header>

      <div className="registro_contenido">
        <section className="registro_introduccion">
          <div className="registro_etiqueta">
            <span className="registro_punto" />
            IINICIA / EXPERIENCIA DIGITAL
          </div>

          <h1>
            Tu futuro empieza <em>con una cuenta.</em>
          </h1>

          <p>
            Un espacio digital pensado para conectar aprendizaje,
            formación e innovación. Crea tu cuenta y comienza
            a formar parte de IINICIA.
          </p>

          <div className="registro_linea" />

          <div className="registro_ventajas">
            <div>
              <strong>01</strong>
              <span>Explora programas académicos</span>
            </div>

            <div>
              <strong>02</strong>
              <span>Conecta con tu comunidad</span>
            </div>

            <div>
              <strong>03</strong>
              <span>Accede a tu futuro campus</span>
            </div>
          </div>

          <p className="registro_aclaracion">
            El registro de cuentas está habilitado.
            El inicio de sesión y el acceso al campus
            continúan en desarrollo.
          </p>
        </section>

        <section
          className="registro_tarjeta"
          aria-labelledby="titulo-registro"
        >
          <div className="registro_superiorTarjeta">
            <span className="registro_microTexto">
              ACCESO IINICIA / 01
            </span>

            <span className="registro_estado">
              Registro disponible
            </span>
          </div>

          <h2 id="titulo-registro">Crea tu perfil</h2>

          <p className="registro_descripcion">
            Regístrate para crear tu cuenta en IINICIA.
          </p>

          <form
            onSubmit={enviarFormulario}
            className="registro_formulario"
          >
            <label htmlFor="usuario">
              Nombre de usuario
            </label>

            <input
              id="usuario"
              name="usuario"
              placeholder="Ej. usuario123"
              autoComplete="username"
              required
              minLength={3}
              maxLength={100}
              disabled={enviando}
            />

            <label htmlFor="correo">
              Correo electrónico
            </label>

            <input
              id="correo"
              name="correo"
              type="email"
              placeholder="tu@correo.com"
              autoComplete="email"
              required
              maxLength={150}
              disabled={enviando}
            />

            <label htmlFor="clave">
              Contraseña
            </label>

            <div className="registro_campoClave">
              <input
                id="clave"
                name="clave"
                type={mostrarClave ? "text" : "password"}
                placeholder="Mínimo 8 caracteres"
                autoComplete="new-password"
                minLength={8}
                value={clave}
                onChange={(evento) =>
                  setClave(evento.target.value)
                }
                required
                disabled={enviando}
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

            <label htmlFor="confirmacion">
              Confirmar contraseña
            </label>

            <div className="registro_campoClave">
              <input
                id="confirmacion"
                name="confirmacion"
                type={
                  mostrarConfirmacion
                    ? "text"
                    : "password"
                }
                placeholder="Repite tu contraseña"
                autoComplete="new-password"
                minLength={8}
                value={confirmacion}
                onChange={(evento) =>
                  setConfirmacion(evento.target.value)
                }
                required
                disabled={enviando}
              />

              <button
                type="button"
                onClick={() =>
                  setMostrarConfirmacion(
                    !mostrarConfirmacion
                  )
                }
                aria-label={
                  mostrarConfirmacion
                    ? "Ocultar confirmación"
                    : "Mostrar confirmación"
                }
              >
                {mostrarConfirmacion
                  ? "Ocultar"
                  : "Ver"}
              </button>
            </div>

            <button
              className="registro_principal"
              type="submit"
              disabled={enviando}
            >
              {enviando
                ? "Creando cuenta..."
                : "Crear cuenta"}

              <span aria-hidden="true"> ↗</span>
            </button>

            {mensaje && (
              <p
                className="registro_mensaje"
                role="status"
                aria-live="polite"
              >
                {mensaje}
              </p>
            )}
          </form>

          <p className="registro_pieTarjeta">
            ¿Ya tienes una cuenta?{" "}
            <Link href="/iniciar-sesion">
              Inicia sesión
            </Link>
          </p>
        </section>
      </div>

      <footer className="registro_pie">
        © 2026 IINICIA · Plataforma digital en desarrollo
      </footer>
    </main>
  );
}
