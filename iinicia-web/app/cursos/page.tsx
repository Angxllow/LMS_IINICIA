"use client";

import { useMemo, useState } from "react";
import Image from "next/image";
import Link from "next/link";
import "./cursos.css";

const styles = {
  ancho: "iiniciaCursos-ancho",
  aviso: "iiniciaCursos-aviso",
  botonMenu: "iiniciaCursos-botonMenu",
  botonNav: "iiniciaCursos-botonNav",
  botonPrincipal: "iiniciaCursos-botonPrincipal",
  botonSecundario: "iiniciaCursos-botonSecundario",
  catalogoEncabezado: "iiniciaCursos-catalogoEncabezado",
  categoriaTarjeta: "iiniciaCursos-categoriaTarjeta",
  contador: "iiniciaCursos-contador",
  contenedorNav: "iiniciaCursos-contenedorNav",
  encabezado: "iiniciaCursos-encabezado",
  enlaceTarjeta: "iiniciaCursos-enlaceTarjeta",
  etiqueta: "iiniciaCursos-etiqueta",
  etiquetaImagen: "iiniciaCursos-etiquetaImagen",
  filtro: "iiniciaCursos-filtro",
  filtroActivo: "iiniciaCursos-filtroActivo",
  filtros: "iiniciaCursos-filtros",
  finalInterior: "iiniciaCursos-finalInterior",
  fraseSuperior: "iiniciaCursos-fraseSuperior",
  hero: "iiniciaCursos-hero",
  heroBotones: "iiniciaCursos-heroBotones",
  heroCuadricula: "iiniciaCursos-heroCuadricula",
  heroFaja: "iiniciaCursos-heroFaja",
  heroImagen: "iiniciaCursos-heroImagen",
  heroInterior: "iiniciaCursos-heroInterior",
  heroLuces: "iiniciaCursos-heroLuces",
  heroNumeral: "iiniciaCursos-heroNumeral",
  heroTexto: "iiniciaCursos-heroTexto",
  imagenLogo: "iiniciaCursos-imagenLogo",
  introInterior: "iiniciaCursos-introInterior",
  introduccion: "iiniciaCursos-introduccion",
  lineaCian: "iiniciaCursos-lineaCian",
  logo: "iiniciaCursos-logo",
  navAbierta: "iiniciaCursos-navAbierta",
  navegacion: "iiniciaCursos-navegacion",
  numeroTarjeta: "iiniciaCursos-numeroTarjeta",
  pagina: "iiniciaCursos-pagina",
  pie: "iiniciaCursos-pie",
  pieInterior: "iiniciaCursos-pieInterior",
  punto: "iiniciaCursos-punto",
  rejilla: "iiniciaCursos-rejilla",
  seccionCatalogo: "iiniciaCursos-seccionCatalogo",
  seccionFinal: "iiniciaCursos-seccionFinal",
  sobretitulo: "iiniciaCursos-sobretitulo",
  tarjeta: "iiniciaCursos-tarjeta",
  tarjetaContenido: "iiniciaCursos-tarjetaContenido",
  tarjetaImagen: "iiniciaCursos-tarjetaImagen",
} as const;

type Categoria = "Todos" | "Tecnología" | "Profesional" | "Investigación";

type Curso = {
  nombre: string;
  categoria: Exclude<Categoria, "Todos">;
  descripcion: string;
  imagen: string;
  etiqueta: string;
  numero: string;
};

// Información ilustrativa. Sustituir por cursos verificados y autorizados de IINICIA.
const cursos: Curso[] = [
  {
    nombre: "Herramientas digitales",
    categoria: "Tecnología",
    descripcion: "Un ejemplo de cómo presentar formación en competencias tecnológicas.",
    imagen: "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Competencias digitales",
    numero: "01",
  },
  {
    nombre: "Comunicación efectiva",
    categoria: "Profesional",
    descripcion: "Un espacio visual para desarrollar habilidades de comunicación.",
    imagen: "https://images.unsplash.com/photo-1521737711867-e3b97375f902?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Desarrollo personal",
    numero: "02",
  },
  {
    nombre: "Innovación y creatividad",
    categoria: "Tecnología",
    descripcion: "Una muestra de futuros contenidos sobre ideas, diseño e innovación.",
    imagen: "https://images.unsplash.com/photo-1552664730-d307ca884978?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Innovación",
    numero: "03",
  },
  {
    nombre: "Metodología de investigación",
    categoria: "Investigación",
    descripcion: "Categoría de ejemplo para cursos de análisis y trabajo académico.",
    imagen: "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Formación académica",
    numero: "04",
  },
  {
    nombre: "Colaboración y liderazgo",
    categoria: "Profesional",
    descripcion: "Presentación ilustrativa de contenidos para el entorno profesional.",
    imagen: "https://images.unsplash.com/photo-1522071820081-009f0129c71c?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Trabajo en equipo",
    numero: "05",
  },
  {
    nombre: "Análisis de información",
    categoria: "Investigación",
    descripcion: "Una tarjeta de muestra para herramientas de análisis académico.",
    imagen: "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Pensamiento analítico",
    numero: "06",
  },
];

const categorias: Categoria[] = ["Todos", "Tecnología", "Profesional", "Investigación"];

export default function CursosPage() {
  const [categoria, setCategoria] = useState<Categoria>("Todos");
  const [menuAbierto, setMenuAbierto] = useState(false);
  const visibles = useMemo(
    () => cursos.filter((curso) => categoria === "Todos" || curso.categoria === categoria),
    [categoria]
  );

  return (
    <main className={styles.pagina}>
      <div className={styles.fraseSuperior}>
        <span className={styles.punto} /> IINICIA / EXPERIENCIA DIGITAL DE APRENDIZAJE
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
          <nav className={`${styles.navegacion} ${menuAbierto ? styles.navAbierta : ""}`} aria-label="Menú de cursos">
            <Link href="/" onClick={() => setMenuAbierto(false)}>Inicio</Link>
            <Link href="/#universidad" onClick={() => setMenuAbierto(false)}>Universidad</Link>
            <a href="#catalogo" onClick={() => setMenuAbierto(false)}>Cursos</a>
            <Link href="/#campus" onClick={() => setMenuAbierto(false)}>Mi Campus</Link>
            <Link href="/#contacto" className={styles.botonNav} onClick={() => setMenuAbierto(false)}>
              Conocer IINICIA <span aria-hidden="true">↗</span>
            </Link>
          </nav>
        </div>
      </header>

      <section className={styles.hero} aria-labelledby="cursos-titulo">
        <div className={styles.heroImagen} />
        <div className={styles.heroLuces} />
        <div className={styles.heroCuadricula} />
        <div className={styles.heroInterior}>
          <div className={styles.heroTexto}>
            <span className={styles.etiqueta}><span className={styles.lineaCian} /> FORMACIÓN CONTINUA / IINICIA</span>
            <h1 id="cursos-titulo">Aprende hoy.<br /><em>Crea lo que sigue.</em></h1>
            <p>Una nueva forma de explorar posibilidades de aprendizaje con una experiencia digital moderna, clara y conectada.</p>
            <div className={styles.heroBotones}>
              <a href="#catalogo" className={styles.botonPrincipal}>Explorar cursos <span aria-hidden="true">↗</span></a>
              <Link href="/" className={styles.botonSecundario}>Volver a IINICIA <span aria-hidden="true">→</span></Link>
            </div>
          </div>
          <div className={styles.heroNumeral} aria-hidden="true"><strong>01</strong><span>/ CAMPUS DIGITAL</span></div>
        </div>
        <div className={styles.heroFaja}>UNA EXPERIENCIA PARA SEGUIR APRENDIENDO <span>✳</span> TECNOLOGÍA • EDUCACIÓN • FUTURO</div>
      </section>

      <section className={styles.introduccion}>
        <div className={styles.introInterior}>
          <div><span className={styles.sobretitulo}>01 — EXPLORA TU POTENCIAL</span><h2>Tu curiosidad puede <em>llevarte más lejos.</em></h2></div>
          <p>Esta sección muestra cómo se organizará el catálogo de cursos de la nueva plataforma. Sus categorías y títulos son demostrativos y se sustituirán con la oferta aprobada por IINICIA.</p>
        </div>
      </section>

      <section id="catalogo" className={styles.seccionCatalogo} aria-labelledby="catalogo-titulo">
        <div className={styles.ancho}>
          <div className={styles.catalogoEncabezado}>
            <div><span className={styles.sobretitulo}>02 — CATÁLOGO DEMOSTRATIVO</span><h2 id="catalogo-titulo">Explora nuevas <em>posibilidades.</em></h2></div>
            <div className={styles.contador}><span>{String(visibles.length).padStart(2,"0")}</span> ÁREAS DE EJEMPLO</div>
          </div>
          <div className={styles.filtros} role="group" aria-label="Filtrar categorías del catálogo">
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
            {visibles.map((curso) => (
              <article className={styles.tarjeta} key={curso.numero}>
                <div
                  className={styles.tarjetaImagen}
                  style={{ backgroundImage: `linear-gradient(180deg, rgba(4,17,36,.03), rgba(4,17,36,.68)), url("${curso.imagen}")` }}
                  role="img"
                  aria-label={`Imagen ilustrativa: ${curso.nombre}`}
                >
                  <span className={styles.numeroTarjeta}>{curso.numero} / IINICIA</span>
                  <span className={styles.etiquetaImagen}>{curso.etiqueta}</span>
                </div>
                <div className={styles.tarjetaContenido}>
                  <span className={styles.categoriaTarjeta}>{curso.categoria.toUpperCase()}</span>
                  <h3>{curso.nombre}</h3>
                  <p>{curso.descripcion}</p>
                  <Link href="/#contacto" className={styles.enlaceTarjeta}>
                    Conocer la plataforma <span aria-hidden="true">↗</span>
                  </Link>
                </div>
              </article>
            ))}
          </div>
          <div className={styles.aviso}>
            <strong>Nota:</strong> Los títulos y las imágenes son ejemplos de diseño. No indican cursos abiertos, precios, fechas ni inscripciones disponibles.
          </div>
        </div>
      </section>

      <section className={styles.seccionFinal}>
        <div className={styles.finalInterior}>
          <span className={styles.etiqueta}>MI IINICIA / PRÓXIMAMENTE</span>
          <h2>Tu aprendizaje,<br /><em>en un solo lugar.</em></h2>
          <p>La integración futura con Moodle permitirá visualizar cursos y actividades autorizadas desde el portal y la aplicación Android.</p>
          <Link href="/#campus" className={styles.botonPrincipal}>Conoce el campus digital <span aria-hidden="true">↗</span></Link>
        </div>
      </section>

      <footer className={styles.pie}>
        <div className={styles.pieInterior}><span>IINICIA · PROTOTIPO DE PLATAFORMA DIGITAL</span><Link href="/">Volver al inicio ↑</Link></div>
      </footer>
    </main>
  );
}
