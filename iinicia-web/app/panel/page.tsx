
"use client";

import Image from "next/image";
import Link from "next/link";
import { useEffect, useState, type CSSProperties } from "react";
import { useRouter } from "next/navigation";

type Perfil = {
  id: number;
  nombre_usuario: string;
  correo: string;
  rol: string;
};

const estilos: Record<string, CSSProperties> = {
  pagina: {
    minHeight: "100vh",
    background: "linear-gradient(135deg, #061426, #102b50)",
    color: "#ffffff",
    padding: "28px 5% 60px",
    fontFamily: "Arial, sans-serif",
  },

  contenedor: {
    maxWidth: 1200,
    margin: "0 auto",
  },

  encabezado: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    flexWrap: "wrap",
    gap: 20,
    marginBottom: 45,
  },

  bienvenida: {
    background: "linear-gradient(120deg, #102b50, #237cae)",
    border: "1px solid #366a95",
    padding: "45px 35px",
    borderRadius: 22,
    marginBottom: 28,
  },

  columnas: {
    display: "grid",
    gridTemplateColumns:
      "repeat(auto-fit, minmax(min(100%, 320px), 1fr))",
    gap: 24,
  },

  tarjeta: {
    background: "#122b48",
    border: "1px solid #315476",
    borderRadius: 20,
    padding: 30,
  },

  etiqueta: {
    color: "#65e5ee",
    fontSize: 13,
    fontWeight: 700,
    letterSpacing: 2,
  },

  texto: {
    color: "#b9cee2",
    lineHeight: 1.8,
  },

  boton: {
    marginTop: 30,
    padding: "15px 28px",
    border: "none",
    borderRadius: 12,
    background: "#49d8df",
    color: "#08233b",
    fontWeight: 700,
    cursor: "pointer",
  },
};

function obtenerServicioPorRol(rol: string): string {
  switch (rol) {
    case "administrador":
      return (
        "Panel administrativo: gestión de usuarios, " +
        "profesores, cursos, permisos y servicios " +
        "institucionales. Funciones en desarrollo."
      );

    case "profesor":
      return (
        "Espacio docente: administración de clases, " +
        "materiales académicos, estudiantes y " +
        "evaluaciones. Funciones en desarrollo."
      );

    case "estudiante":
      return (
        "Campus estudiantil: consulta de cursos, " +
        "inscripciones, materiales académicos y " +
        "calificaciones. Funciones en desarrollo."
      );

    case "coordinador":
      return (
        "Coordinación académica: seguimiento de " +
        "programas educativos, profesores, alumnos " +
        "y actividades institucionales. " +
        "Funciones en desarrollo."
      );

    case "aspirante":
      return (
        "Espacio para aspirantes: información de " +
        "admisiones, programas académicos, requisitos " +
        "y seguimiento de solicitudes. " +
        "Funciones en desarrollo."
      );

    default:
      return (
        "Los servicios académicos estarán " +
        "disponibles próximamente."
      );
  }
}

export default function PanelPage() {
  const router = useRouter();

  const [usuario, setUsuario] = useState<Perfil | null>(null);
  const [cargando, setCargando] = useState(true);
  const [cerrando, setCerrando] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    let activo = true;

    async function cargarPerfil() {
      try {
        const respuesta = await fetch(
          "http://localhost:3001/auth/perfil",
          {
            method: "GET",
            credentials: "include",
            cache: "no-store",
          }
        );

        if (respuesta.status === 401) {
          router.replace("/iniciar-sesion");
          return;
        }

        if (!respuesta.ok) {
          throw new Error("No se pudo consultar el perfil");
        }

        const datos: Perfil = await respuesta.json();

        if (activo) {
          setUsuario(datos);
        }
      } catch {
        if (activo) {
          setError("No fue posible conectar con IINICIA.");
        }
      } finally {
        if (activo) {
          setCargando(false);
        }
      }
    }

    cargarPerfil();

    return () => {
      activo = false;
    };
  }, [router]);

  async function cerrarSesion() {
    setCerrando(true);
    setError("");

    try {
      const respuesta = await fetch(
        "http://localhost:3001/auth/logout",
        {
          method: "POST",
          credentials: "include",
        }
      );

      if (!respuesta.ok) {
        throw new Error("No se pudo cerrar sesión");
      }

      setUsuario(null);
      router.replace("/iniciar-sesion");
    } catch {
      setError(
        "No se pudo cerrar sesión. Inténtalo nuevamente."
      );
    } finally {
      setCerrando(false);
    }
  }

  if (cargando) {
    return (
      <main style={estilos.pagina}>
        <p>Comprobando tu sesión de IINICIA...</p>
      </main>
    );
  }

  if (error && !usuario) {
    return (
      <main style={estilos.pagina}>
        <h2>No pudimos cargar tu perfil</h2>
        <p>{error}</p>

        <Link href="/iniciar-sesion">
          Volver a iniciar sesión
        </Link>
      </main>
    );
  }

  if (!usuario) {
    return null;
  }

  return (
    <main style={estilos.pagina}>
      <div style={estilos.contenedor}>

        {/* ENCABEZADO OFICIAL IINICIA */}

        <header style={estilos.encabezado}>
          <Link href="/" aria-label="Inicio de IINICIA">
            <Image
              src="/imagenes/Logo-con-Blanco-1.png"
              alt="Logotipo oficial IINICIA"
              width={170}
              height={73}
              style={{
                width: "auto",
                height: 65,
              }}
              priority
            />
          </Link>

          <span style={estilos.etiqueta}>
            PLATAFORMA DIGITAL UNIVERSITARIA
          </span>
        </header>

        {/* BIENVENIDA PERSONALIZADA */}

        <section style={estilos.bienvenida}>
          <p style={estilos.etiqueta}>
            IINICIA / ÁREA ACADÉMICA
          </p>

          <h1
            style={{
              fontSize: "clamp(28px, 4vw, 45px)",
              margin: "16px 0",
            }}
          >
            ¡Bienvenido, {usuario.nombre_usuario}!
          </h1>

          <p style={estilos.texto}>
            Tu sesión está activa. Desde aquí podrás
            acceder a los servicios académicos
            correspondientes a tu perfil en IINICIA.
          </p>
        </section>

        {/* INFORMACIÓN DEL USUARIO */}

        <div style={estilos.columnas}>
          <section style={estilos.tarjeta}>
            <h2>Mi perfil</h2>

            <p style={estilos.etiqueta}>
              NOMBRE DE USUARIO
            </p>

            <p>{usuario.nombre_usuario}</p>

            <p style={estilos.etiqueta}>
              CORREO ELECTRÓNICO
            </p>

            <p style={{ overflowWrap: "anywhere" }}>
              {usuario.correo}
            </p>

            <p style={estilos.etiqueta}>
              ROL
            </p>

            <p
              style={{
                textTransform: "capitalize",
              }}
            >
              {usuario.rol}
            </p>
          </section>

          {/* SERVICIOS SEGÚN EL ROL */}

          <section style={estilos.tarjeta}>
            <h2>Mis servicios académicos</h2>

            <p style={estilos.texto}>
              {obtenerServicioPorRol(usuario.rol)}
            </p>

            <div
              style={{
                padding: 18,
                borderRadius: 12,
                background: "#1c3c5f",
                color: "#b9cee2",
                marginTop: 20,
              }}
            >
              Integración con Moodle:
              pendiente de configuración.
            </div>
          </section>
        </div>

{/* ACCESO EXCLUSIVO PARA ADMINISTRADORES */}

{usuario.rol === "administrador" && (
  <div style={{ marginTop: 30 }}>
    <Link
      href="/administracion"
      style={{
        display: "inline-block",
        padding: "16px 28px",
        borderRadius: 12,
        background: "#237cae",
        color: "#ffffff",
        fontWeight: 700,
        textDecoration: "none",
        border: "1px solid #49d8df",
      }}
    >
      Administrar usuarios →
    </Link>
  </div>
)}

        {/* CERRAR SESIÓN */}

        <button
          type="button"
          style={{
            ...estilos.boton,
            opacity: cerrando ? 0.6 : 1,
          }}
          onClick={cerrarSesion}
          disabled={cerrando}
        >
          {cerrando
            ? "Cerrando sesión..."
            : "Cerrar sesión"}
        </button>

        {error && (
          <p
            role="alert"
            style={{ color: "#ffb4b4" }}
          >
            {error}
          </p>
        )}

        {/* PIE DE PÁGINA */}

        <footer
          style={{
            marginTop: 60,
            color: "#91aac2",
            textAlign: "center",
          }}
        >
          © 2026 IINICIA · Plataforma Digital Universitaria
        </footer>

      </div>
    </main>
  );
}
