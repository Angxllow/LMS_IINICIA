"use client";

import { useMemo, useState } from "react";
import Image from "next/image";
import Link from "next/link";
import "./certificaciones.css";

const styles = {
  ancho: "iiniciaCert-ancho",
  aviso: "iiniciaCert-aviso",
  botonMenu: "iiniciaCert-botonMenu",
  botonNav: "iiniciaCert-botonNav",
  botonPrincipal: "iiniciaCert-botonPrincipal",
  botonSecundario: "iiniciaCert-botonSecundario",
  catalogoEncabezado: "iiniciaCert-catalogoEncabezado",
  categoriaTarjeta: "iiniciaCert-categoriaTarjeta",
  contador: "iiniciaCert-contador",
  contenedorNav: "iiniciaCert-contenedorNav",
  encabezado: "iiniciaCert-encabezado",
  enlaceTarjeta: "iiniciaCert-enlaceTarjeta",
  etiqueta: "iiniciaCert-etiqueta",
  etiquetaImagen: "iiniciaCert-etiquetaImagen",
  filtro: "iiniciaCert-filtro",
  filtroActivo: "iiniciaCert-filtroActivo",
  filtros: "iiniciaCert-filtros",
  finalInterior: "iiniciaCert-finalInterior",
  fraseSuperior: "iiniciaCert-fraseSuperior",
  hero: "iiniciaCert-hero",
  heroBotones: "iiniciaCert-heroBotones",
  heroCuadricula: "iiniciaCert-heroCuadricula",
  heroFaja: "iiniciaCert-heroFaja",
  heroImagen: "iiniciaCert-heroImagen",
  heroInterior: "iiniciaCert-heroInterior",
  heroLuces: "iiniciaCert-heroLuces",
  heroNumeral: "iiniciaCert-heroNumeral",
  heroTexto: "iiniciaCert-heroTexto",
  imagenLogo: "iiniciaCert-imagenLogo",
  introInterior: "iiniciaCert-introInterior",
  introduccion: "iiniciaCert-introduccion",
  lineaCian: "iiniciaCert-lineaCian",
  logo: "iiniciaCert-logo",
  navAbierta: "iiniciaCert-navAbierta",
  navegacion: "iiniciaCert-navegacion",
  numeroTarjeta: "iiniciaCert-numeroTarjeta",
  pagina: "iiniciaCert-pagina",
  pie: "iiniciaCert-pie",
  pieInterior: "iiniciaCert-pieInterior",
  punto: "iiniciaCert-punto",
  rejilla: "iiniciaCert-rejilla",
  seccionCatalogo: "iiniciaCert-seccionCatalogo",
  seccionFinal: "iiniciaCert-seccionFinal",
  sobretitulo: "iiniciaCert-sobretitulo",
  tarjeta: "iiniciaCert-tarjeta",
  tarjetaContenido: "iiniciaCert-tarjetaContenido",
  tarjetaImagen: "iiniciaCert-tarjetaImagen",
} as const;

type Categoria = "Todos" | "Tecnología" | "Gestión" | "Docencia";

type Certificacion = {
  nombre: string;
  categoria: Exclude<Categoria, "Todos">;
  descripcion: string;
  imagen: string;
  etiqueta: string;
  numero: string;
};

// Información ilustrativa. Sustituir por cursos verificados y autorizados de IINICIA.
const certificaciones: Certificacion[] = [
  {
    nombre: "Competencias digitales",
    categoria: "Tecnología",
    descripcion: "Ejemplo de formación centrada en herramientas digitales y buenas prácticas de trabajo.",
    imagen: "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Herramientas digitales",
    numero: "01",
  },
  {
    nombre: "Gestión de proyectos",
    categoria: "Gestión",
    descripcion: "Una propuesta visual para presentar organización, planificación y seguimiento de proyectos.",
    imagen: "https://images.unsplash.com/photo-1552664730-d307ca884978?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Planificación",
    numero: "02",
  },
  {
    nombre: "Diseño de experiencias de aprendizaje",
    categoria: "Docencia",
    descripcion: "Ejemplo de contenidos relacionados con recursos didácticos y aprendizaje activo.",
    imagen: "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Innovación educativa",
    numero: "03",
  },
  {
    nombre: "Fundamentos de ciberseguridad",
    categoria: "Tecnología",
    descripcion: "Un modelo ilustrativo para presentar conocimientos básicos de seguridad digital.",
    imagen: "https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Seguridad digital",
    numero: "04",
  },
  {
    nombre: "Liderazgo colaborativo",
    categoria: "Gestión",
    descripcion: "Una muestra de formación orientada a la comunicación y el trabajo en equipo.",
    imagen: "https://images.unsplash.com/photo-1522071820081-009f0129c71c?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Trabajo en equipo",
    numero: "05",
  },
  {
    nombre: "Evaluación de competencias",
    categoria: "Docencia",
    descripcion: "Categoría de ejemplo para procesos de evaluación y retroalimentación educativa.",
    imagen: "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Formación académica",
    numero: "06",
  },
];

const categorias: Categoria[] = ["Todos", "Tecnología", "Gestión", "Docencia"];

export default function CertificacionesPage() {
  const [categoria, setCategoria] = useState<Categoria>("Todos");
  const [menuAbierto, setMenuAbierto] = useState(false);
  const visibles = useMemo(
    () => certificaciones.filter((certificacion) => categoria === "Todos" || certificacion.categoria === categoria),
    [categoria]
  );

  return (
    <main className={styles.pagina}>
      <div className={styles.fraseSuperior}>
        <span className={styles.punto} /> IINICIA / IMPULSA TUS COMPETENCIAS
      </div>

      <header className={styles.encabezado}>
        <div className={styles.contenedorNav}>
          <Link className={styles.logo} href="/" aria-label="Volver al inicio de IINICIA">
            <Image
              src="/imagenes/Logo-con-Blanco-1.png"
              alt="Logotipo de IINICIA"
              width={185}
              height={72}
              priority
              className={styles.imagenLogo}
            />
          </Link>
          <button
            type="button"
            className={styles.botonMenu}
            aria-expanded={menuAbierto}
            aria-label={menuAbierto ? "Cerrar menú" : "Abrir menú"}
            onClick={() => setMenuAbierto((actual) => !actual)}
          >
            {menuAbierto ? "✕" : "☰"}
          </button>
          <nav className={`${styles.navegacion} ${menuAbierto ? styles.navAbierta : ""}`} aria-label="Menú de certificaciones">
            <Link href="/" onClick={() => setMenuAbierto(false)}>Inicio</Link>
            <Link href="/cursos" onClick={() => setMenuAbierto(false)}>Cursos</Link>
            <a href="#catalogo" onClick={() => setMenuAbierto(false)}>Certificaciones</a>
            <Link href="/#universidad" onClick={() => setMenuAbierto(false)}>Universidad</Link>
            <Link href="/#campus" onClick={() => setMenuAbierto(false)}>Mi Campus</Link>
            <Link href="/#contacto" className={styles.botonNav} onClick={() => setMenuAbierto(false)}>
              Conocer IINICIA <span aria-hidden="true">↗</span>
            </Link>
          </nav>
        </div>
      </header>

      <section className={styles.hero} aria-labelledby="certificaciones-titulo">
        <div className={styles.heroImagen} />
        <div className={styles.heroLuces} />
        <div className={styles.heroCuadricula} />
        <div className={styles.heroInterior}>
          <div className={styles.heroTexto}>
            <span className={styles.etiqueta}><span className={styles.lineaCian} /> CERTIFICACIONES / IINICIA</span>
            <h1 id="certificaciones-titulo">Desarrolla tu talento.<br /><em>Hazlo visible.</em></h1>
            <p>Explora un espacio digital pensado para presentar rutas de especialización y fortalecer tu desarrollo profesional.</p>
            <div className={styles.heroBotones}>
              <a href="#catalogo" className={styles.botonPrincipal}>Explorar certificaciones <span aria-hidden="true">↗</span></a>
              <Link href="/" className={styles.botonSecundario}>Volver a IINICIA <span aria-hidden="true">→</span></Link>
            </div>
          </div>
          <div className={styles.heroNumeral} aria-hidden="true"><strong>02</strong><span>/ DESARROLLO PROFESIONAL</span></div>
        </div>
        <div className={styles.heroFaja}>DESARROLLA NUEVAS COMPETENCIAS <span>✳</span> TECNOLOGÍA • GESTIÓN • DOCENCIA</div>
      </section>

      <section className={styles.introduccion}>
        <div className={styles.introInterior}>
          <div><span className={styles.sobretitulo}>01 — PROYECTA TU CRECIMIENTO</span><h2>Tu preparación puede <em>abrir nuevas puertas.</em></h2></div>
          <p>Esta sección es una demostración de cómo podrían presentarse las certificaciones en la nueva plataforma. Las categorías y los nombres son ejemplos, no acreditaciones ni programas oficiales de IINICIA.</p>
        </div>
      </section>

      <section id="catalogo" className={styles.seccionCatalogo} aria-labelledby="catalogo-titulo">
        <div className={styles.ancho}>
          <div className={styles.catalogoEncabezado}>
            <div><span className={styles.sobretitulo}>02 — CATÁLOGO DEMOSTRATIVO</span><h2 id="catalogo-titulo">Explora nuevas <em>competencias.</em></h2></div>
            <div className={styles.contador}><span>{String(visibles.length).padStart(2,"0")}</span> EJEMPLOS VISIBLES</div>
          </div>
          <div className={styles.filtros} role="group" aria-label="Filtrar categorías de certificaciones">
            {categorias.map((opcion) => (
              <button
                key={opcion}
                type="button"
                className={`${styles.filtro} ${categoria === opcion ? styles.filtroActivo : ""}`}
                aria-pressed={categoria === opcion}
                onClick={() => setCategoria(opcion)}
              >
                {opcion}
              </button>
            ))}
          </div>
          <div className={styles.rejilla} aria-live="polite">
            {visibles.map((certificacion) => (
              <article className={styles.tarjeta} key={certificacion.numero}>
                <div
                  className={styles.tarjetaImagen}
                  style={{ backgroundImage: `linear-gradient(180deg, rgba(4,17,36,.03), rgba(4,17,36,.68)), url("${certificacion.imagen}")` }}
                  role="img"
                  aria-label={`Imagen ilustrativa: ${certificacion.nombre}`}
                >
                  <span className={styles.numeroTarjeta}>{certificacion.numero} / IINICIA</span>
                  <span className={styles.etiquetaImagen}>{certificacion.etiqueta}</span>
                </div>
                <div className={styles.tarjetaContenido}>
                  <span className={styles.categoriaTarjeta}>{certificacion.categoria.toUpperCase()}</span>
                  <h3>{certificacion.nombre}</h3>
                  <p>{certificacion.descripcion}</p>
                  <Link href="/#contacto" className={styles.enlaceTarjeta}>
                    Conocer la plataforma <span aria-hidden="true">↗</span>
                  </Link>
                </div>
              </article>
            ))}
          </div>
          <div className={styles.aviso}>
            <strong>Nota:</strong> Las certificaciones, sus títulos e imágenes son contenido de demostración. No representan títulos acreditados, convocatorias, precios, fechas ni certificaciones disponibles de IINICIA.
          </div>
        </div>
      </section>

      <section className={styles.seccionFinal}>
        <div className={styles.finalInterior}>
          <span className={styles.etiqueta}>MI IINICIA / PRÓXIMAMENTE</span>
          <h2>Tu crecimiento profesional,<br /><em>en un solo lugar.</em></h2>
          <p>Más adelante, la integración segura con Moodle podrá mostrar los cursos e inscripciones autorizados de cada estudiante.</p>
          <Link href="/#campus" className={styles.botonPrincipal}>Conoce el campus digital <span aria-hidden="true">↗</span></Link>
        </div>
      </section>

      <footer className={styles.pie}>
        <div className={styles.pieInterior}><span>IINICIA · PROTOTIPO DE PLATAFORMA DIGITAL</span><Link href="/">Volver al inicio ↑</Link></div>
      </footer>
    </main>
  );
}
