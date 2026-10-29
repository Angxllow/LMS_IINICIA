
"use client";

import Image from "next/image";
import Link from "next/link";
import { useEffect, useState } from "react";

type Usuario = {
  id: number;
  nombre_usuario: string;
  correo: string;
  rol: string;
  creado_en: string;
};

type RespuestaUsuarios = {
  mensaje: string;
  total_mostrados: number;
  usuarios: Usuario[];
};

const colores = {
  fondo: "#081a30",
  tarjeta: "#122b48",
  borde: "#315476",
  turquesa: "#49d8df",
  texto: "#ffffff",
  secundario: "#b9cee2",
};

export default function AdministracionPage() {
  const [datos, setDatos] =
    useState<RespuestaUsuarios | null>(null);


  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState("");
  const [busqueda, setBusqueda] = useState("");
  
const [filtroRol, setFiltroRol] = useState("todos");

  

const usuariosFiltrados = (datos?.usuarios ?? []).filter(
  (usuario) => {
    const texto = busqueda.toLowerCase().trim();

    const coincideBusqueda =
      usuario.nombre_usuario.toLowerCase().includes(texto) ||
      usuario.correo.toLowerCase().includes(texto) ||
      usuario.rol.toLowerCase().includes(texto);

    const coincideRol =
      filtroRol === "todos" || usuario.rol === filtroRol;

    return coincideBusqueda && coincideRol;
  }
);



  useEffect(() => {
    const controlador = new AbortController();

    async function consultarUsuarios() {
      try {
        const respuesta = await fetch(
          "http://localhost:3001/administracion/usuarios",
          {
            method: "GET",
            credentials: "include",
            cache: "no-store",
            signal: controlador.signal,
          }
        );

        if (respuesta.status === 401) {
          throw new Error(
            "Tu sesión ha expirado. Inicia sesión nuevamente."
          );
        }

        if (respuesta.status === 403) {
          throw new Error(
            "Acceso denegado. Esta sección es exclusiva para administradores."
          );
        }

        if (!respuesta.ok) {
          throw new Error(
            "No fue posible consultar los usuarios."
          );
        }

        const resultado: RespuestaUsuarios =
          await respuesta.json();

        if (!controlador.signal.aborted) {
          setDatos(resultado);
        }
      } catch (problema) {
        if (!controlador.signal.aborted) {
          setError(
            problema instanceof Error
              ? problema.message
              : "Ocurrió un error inesperado."
          );
        }
      } finally {
        if (!controlador.signal.aborted) {
          setCargando(false);
        }
      }
    }

    consultarUsuarios();

    return () => controlador.abort();
  }, []);

  function mostrarFecha(fecha: string) {
    const valor = new Date(fecha);

    return Number.isNaN(valor.getTime())
      ? "Sin fecha"
      : valor.toLocaleDateString("es-MX", {
          day: "2-digit",
          month: "short",
          year: "numeric",
        });
  }

  return (
    <main
      style={{
        minHeight: "100vh",
        background: colores.fondo,
        color: colores.texto,
        padding: "30px 5% 65px",
        fontFamily: "Arial, sans-serif",
      }}
    >
      <div
        style={{
          maxWidth: 1250,
          margin: "0 auto",
        }}
      >
        {/* ENCABEZADO */}

        <header
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            flexWrap: "wrap",
            gap: 20,
            marginBottom: 35,
          }}
        >
          <Link href="/" aria-label="Inicio IINICIA">
            <Image
              src="/imagenes/Logo-con-Blanco-1.png"
              alt="Logo oficial IINICIA"
              width={170}
              height={73}
              style={{
                width: "auto",
                height: 65,
              }}
              priority
            />
          </Link>

          <Link
            href="/panel"
            style={{
              color: colores.turquesa,
              textDecoration: "none",
              fontWeight: "bold",
            }}
          >
            ← Volver a mi panel
          </Link>
        </header>

        {/* BIENVENIDA ADMINISTRATIVA */}

        <section
          style={{
            background:
              "linear-gradient(120deg, #102b50, #237cae)",
            padding: "40px 35px",
            borderRadius: 22,
            border: "1px solid #366a95",
            marginBottom: 28,
          }}
        >
          <p
            style={{
              color: colores.turquesa,
              fontSize: 13,
              fontWeight: "bold",
              letterSpacing: 2,
            }}
          >
            IINICIA / ADMINISTRACIÓN
          </p>

          <h1
            style={{
              fontSize: "clamp(28px, 4vw, 44px)",
              margin: "15px 0",
            }}
          >
            Gestión de usuarios
          </h1>

          <p
            style={{
              color: "#d3e4f2",
              lineHeight: 1.7,
            }}
          >
            Consulta los usuarios registrados,
            sus correos electrónicos,
            roles y fechas de registro.
          </p>
        </section>

        {/* CARGANDO */}

        {cargando && (
          <p role="status">
            Consultando usuarios de IINICIA...
          </p>
        )}

        {/* ERROR O ACCESO DENEGADO */}

        {!cargando && error && (
          <section
            role="alert"
            style={{
              background: colores.tarjeta,
              border: "1px solid #b86a6a",
              borderRadius: 18,
              padding: 30,
            }}
          >
            <h2>No se pudo acceder</h2>
            <p>{error}</p>

            
          
<Link
  href={
    error.startsWith("Acceso denegado")
      ? "/panel"
      : "/iniciar-sesion"
  }
  style={{ color: colores.turquesa }}
>
  {error.startsWith("Acceso denegado")
    ? "← Volver a mi panel"
    : "Ir a iniciar sesión"}
</Link>

          </section>
        )}

        {/* TABLA DE USUARIOS */}

        {!cargando && !error && datos && (
          <>
            <section
              style={{
                background: colores.tarjeta,
                padding: 28,
                borderRadius: 18,
                border: `1px solid ${colores.borde}`,
                marginBottom: 25,
              }}
            >
              <p
                style={{
                  color: colores.secundario,
                  marginBottom: 10,
                }}
              >
                Usuarios mostrados
              </p>

              <h2
                style={{
                  fontSize: 42,
                  color: colores.turquesa,
                  margin: 0,
                }}
              >
               
              {usuariosFiltrados.length}

              </h2>

              <p style={{ color: colores.secundario }}>
                Registros consultados desde MySQL
              </p>
            </section>

            <section
              style={{
                background: colores.tarjeta,
                border: `1px solid ${colores.borde}`,
                borderRadius: 18,
                padding: 25,
              }}
            >
              <h2 style={{ marginBottom: 25 }}>
                Usuarios registrados
              </h2>
              
<label
  htmlFor="buscar-usuarios"
  style={{
    display: "block",
    marginBottom: 10,
    color: colores.secundario,
    fontWeight: "bold",
  }}
>
  Buscar usuario
</label>

<input
  id="buscar-usuarios"
  type="search"
  placeholder="Buscar por nombre, correo o rol..."
  value={busqueda}
  onChange={(evento) => setBusqueda(evento.target.value)}
  style={{
    width: "100%",
    padding: "16px",
    marginBottom: 25,
    borderRadius: 12,
    background: "#0b2038",
    border: "1px solid #49d8df",
    color: "#ffffff",
    fontSize: 16,
    outlineOffset: 3,
    boxSizing: "border-box",
  }}
/>

<label
  htmlFor="filtro-rol"
  style={{
    display: "block",
    marginBottom: 10,
    color: colores.secundario,
    fontWeight: "bold",
  }}
>
  Filtrar por rol
</label>

<select
  id="filtro-rol"
  value={filtroRol}
  onChange={(evento) => setFiltroRol(evento.target.value)}
  style={{
    width: "100%",
    padding: "16px",
    marginBottom: 25,
    borderRadius: 12,
    background: "#0b2038",
    border: "1px solid #49d8df",
    color: "#ffffff",
    fontSize: 16,
    cursor: "pointer",
    boxSizing: "border-box",
  }}
>
  <option value="todos">Todos los roles</option>
  <option value="administrador">Administradores</option>
  <option value="profesor">Profesores</option>
  <option value="estudiante">Estudiantes</option>
  <option value="coordinador">Coordinadores</option>
  <option value="aspirante">Aspirantes</option>
</select>


              <div style={{ overflowX: "auto" }}>
                <table
                  style={{
                    width: "100%",
                    borderCollapse: "collapse",
                    textAlign: "left",
                    minWidth: 680,
                  }}
                >
                  <thead>
                    <tr
                      style={{
                        color: colores.turquesa,
                        borderBottom:
                          "1px solid #406080",
                      }}
                    >
                      <th style={{ padding: 14 }}>ID</th>
                      <th style={{ padding: 14 }}>Usuario</th>
                      <th style={{ padding: 14 }}>Correo</th>
                      <th style={{ padding: 14 }}>Rol</th>
                      <th style={{ padding: 14 }}>Registro</th>
                    </tr>
                  </thead>

                  <tbody>
                   
                    {usuariosFiltrados.map((usuario) => (

                      <tr
                        key={usuario.id}
                        style={{
                          borderBottom:
                            "1px solid #284665",
                        }}
                      >
                        <td style={{ padding: 14 }}>
                          {usuario.id}
                        </td>

                        <td style={{ padding: 14 }}>
                          {usuario.nombre_usuario}
                        </td>

                        <td
                          style={{
                            padding: 14,
                            overflowWrap: "anywhere",
                          }}
                        >
                          {usuario.correo}
                        </td>

                        <td style={{ padding: 14 }}>
                          <span
                            style={{
                              display: "inline-block",
                              background: "#204968",
                              color: colores.turquesa,
                              borderRadius: 20,
                              padding: "6px 12px",
                              textTransform: "capitalize",
                            }}
                          >
                            {usuario.rol}
                          </span>
                        </td>

                        <td style={{ padding: 14 }}>
                          {mostrarFecha(usuario.creado_en)}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>

{usuariosFiltrados.length === 0 && (
  <p
    role="status"
    style={{
      textAlign: "center",
      color: colores.secundario,
      padding: "25px",
      fontSize: 16,
    }}
  >
    No se encontraron usuarios con esa búsqueda.
  </p>
)}

              </div>
            </section>
          </>
        )}

        <footer
          style={{
            textAlign: "center",
            color: "#91aac2",
            marginTop: 55,
          }}
        >
          © 2026 IINICIA · Plataforma Digital Universitaria
        </footer>
      </div>
    </main>
  );
}
