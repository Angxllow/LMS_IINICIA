"use client";

import { useMemo, useState } from "react";
import Image from "next/image";
import Link from "next/link";
import "./diplomados.css";

const styles = {
  ancho: "iiniciaDip-ancho",
  aviso: "iiniciaDip-aviso",
  botonMenu: "iiniciaDip-botonMenu",
  botonNav: "iiniciaDip-botonNav",
  botonPrincipal: "iiniciaDip-botonPrincipal",
  botonSecundario: "iiniciaDip-botonSecundario",
  catalogoEncabezado: "iiniciaDip-catalogoEncabezado",
  categoriaTarjeta: "iiniciaDip-categoriaTarjeta",
  contador: "iiniciaDip-contador",
  contenedorNav: "iiniciaDip-contenedorNav",
  encabezado: "iiniciaDip-encabezado",
  enlaceTarjeta: "iiniciaDip-enlaceTarjeta",
  etiqueta: "iiniciaDip-etiqueta",
  etiquetaImagen: "iiniciaDip-etiquetaImagen",
  filtro: "iiniciaDip-filtro",
  filtroActivo: "iiniciaDip-filtroActivo",
  filtros: "iiniciaDip-filtros",
  finalInterior: "iiniciaDip-finalInterior",
  fraseSuperior: "iiniciaDip-fraseSuperior",
  hero: "iiniciaDip-hero",
  heroBotones: "iiniciaDip-heroBotones",
  heroCuadricula: "iiniciaDip-heroCuadricula",
  heroFaja: "iiniciaDip-heroFaja",
  heroImagen: "iiniciaDip-heroImagen",
  heroInterior: "iiniciaDip-heroInterior",
  heroLuces: "iiniciaDip-heroLuces",
  heroNumeral: "iiniciaDip-heroNumeral",
  heroTexto: "iiniciaDip-heroTexto",
  imagenLogo: "iiniciaDip-imagenLogo",
  introInterior: "iiniciaDip-introInterior",
  introduccion: "iiniciaDip-introduccion",
  lineaCian: "iiniciaDip-lineaCian",
  logo: "iiniciaDip-logo",
  navAbierta: "iiniciaDip-navAbierta",
  navegacion: "iiniciaDip-navegacion",
  numeroTarjeta: "iiniciaDip-numeroTarjeta",
  pagina: "iiniciaDip-pagina",
  pie: "iiniciaDip-pie",
  pieInterior: "iiniciaDip-pieInterior",
  punto: "iiniciaDip-punto",
  rejilla: "iiniciaDip-rejilla",
  seccionCatalogo: "iiniciaDip-seccionCatalogo",
  seccionFinal: "iiniciaDip-seccionFinal",
  sobretitulo: "iiniciaDip-sobretitulo",
  tarjeta: "iiniciaDip-tarjeta",
  tarjetaContenido: "iiniciaDip-tarjetaContenido",
  tarjetaImagen: "iiniciaDip-tarjetaImagen",
} as const;

type Categoria = "Todos" | "Tecnología" | "Gestión" | "Educación" | "Innovación";

type Diplomado = {
  nombre: string;
  categoria: Exclude<Categoria, "Todos">;
  descripcion: string;
  imagen: string;
  etiqueta: string;
  numero: string;
};

// Ejemplos de diseño, NO oferta académica, matrículas ni diplomados oficiales.
const diplomados: Diplomado[] = [
  {
    nombre: "Transformación digital",
    categoria: "Tecnología",
    descripcion: "Una propuesta ilustrativa para explorar tecnologías y procesos de transformación organizacional.",
    imagen: "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Futuro digital",
    numero: "01",
  },
  {
    nombre: "Dirección estratégica",
    categoria: "Gestión",
    descripcion: "Ejemplo visual de una ruta de formación en liderazgo, análisis y toma de decisiones.",
    imagen: "https://images.unsplash.com/photo-1552664730-d307ca884978?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Liderazgo",
    numero: "02",
  },
  {
    nombre: "Innovación educativa",
    categoria: "Educación",
    descripcion: "Una muestra de contenidos para diseñar experiencias de enseñanza y aprendizaje activo.",
    imagen: "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Aprendizaje",
    numero: "03",
  },
  {
    nombre: "Análisis de datos aplicado",
    categoria: "Tecnología",
    descripcion: "Ejemplo de un trayecto formativo relacionado con datos, visualización y pensamiento analítico.",
    imagen: "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Inteligencia de datos",
    numero: "04",
  },
  {
    nombre: "Creatividad e innovación",
    categoria: "Innovación",
    descripcion: "Un espacio ilustrativo para descubrir procesos creativos y desarrollo de nuevas ideas.",
    imagen: "https://images.unsplash.com/photo-1522071820081-009f0129c71c?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Nuevas ideas",
    numero: "05",
  },
  {
    nombre: "Gestión de proyectos",
    categoria: "Gestión",
    descripcion: "Ejemplo de formación para planear, organizar y colaborar en proyectos profesionales.",
    imagen: "https://images.unsplash.com/photo-1521737711867-e3b97375f902?auto=format&fit=crop&w=1050&q=85",
    etiqueta: "Trabajo en equipo",
    numero: "06",
  },
];

const categorias: Categoria[] = ["Todos", "Tecnología", "Gestión", "Educación", "Innovación"];

export default function DiplomadosPage() {
  const [categoria, setCategoria] = useState<Categoria>("Todos");
  const [menuAbierto, setMenuAbierto] = useState(false);
  const visibles = useMemo(
    () => diplomados.filter((diplomado) => categoria === "Todos" || diplomado.categoria === categoria),
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
          <nav className={`${styles.navegacion} ${menuAbierto ? styles.navAbierta : ""}`} aria-label="Menú de diplomados">
            <Link href="/" onClick={() => setMenuAbierto(false)}>Inicio</Link>
            <Link href="/cursos" onClick={() => setMenuAbierto(false)}>Cursos</Link>
            <Link href="/certificaciones" onClick={() => setMenuAbierto(false)}>Certificaciones</Link>
            <a href="#catalogo" onClick={() => setMenuAbierto(false)}>Diplomados</a>
            <Link href="/registro" onClick={() => setMenuAbierto(false)}>Registrarse</Link>
            <Link href="/iniciar-sesion" className={styles.botonNav} onClick={() => setMenuAbierto(false)}>
              Iniciar sesión <span aria-hidden="true">↗</span>
            </Link>
          </nav>
        </div>
      </header>

      <section className={styles.hero} aria-labelledby="diplomados-titulo">
        <div className={styles.heroImagen} />
        <div className={styles.heroLuces} />
        <div className={styles.heroCuadricula} />
        <div className={styles.heroInterior}>
          <div className={styles.heroTexto}>
            <span className={styles.etiqueta}><span className={styles.lineaCian} /> DIPLOMADOS / IINICIA</span>
            <h1 id="diplomados-titulo">Amplía tus horizontes.<br /><em>Construye tu futuro.</em></h1>
            <p>Descubre una experiencia diseñada para presentar trayectorias formativas de mayor profundidad, conectar ideas y potenciar tu crecimiento profesional.</p>
            <div className={styles.heroBotones}>
              <a href="#catalogo" className={styles.botonPrincipal}>Explorar diplomados <span aria-hidden="true">↗</span></a>
              <Link href="/" className={styles.botonSecundario}>Volver a IINICIA <span aria-hidden="true">→</span></Link>
            </div>
          </div>
          <div className={styles.heroNumeral} aria-hidden="true"><strong>03</strong><span>/ FORMACIÓN ESPECIALIZADA</span></div>
        </div>
        <div className={styles.heroFaja}>APRENDE • CONECTA • TRANSFORMA <span>✳</span> TECNOLOGÍA • GESTIÓN • EDUCACIÓN</div>
      </section>

      <section className={styles.introduccion}>
        <div className={styles.introInterior}>
          <div><span className={styles.sobretitulo}>01 — APRENDE A OTRO NIVEL</span><h2>Más conocimiento.<br /><em>Más posibilidades.</em></h2></div>
          <p>Esta página presenta una propuesta visual para el catálogo futuro de diplomados de IINICIA. Los nombres, categorías e imágenes son ejemplos de diseño, no representan programas oficiales, acreditaciones ni convocatorias abiertas.</p>
        </div>
      </section>

      <section id="catalogo" className={styles.seccionCatalogo} aria-labelledby="catalogo-titulo">
        <div className={styles.ancho}>
          <div className={styles.catalogoEncabezado}>
            <div><span className={styles.sobretitulo}>02 — CATÁLOGO DEMOSTRATIVO</span><h2 id="catalogo-titulo">Descubre nuevas <em>oportunidades.</em></h2></div>
            <div className={styles.contador}><span>{String(visibles.length).padStart(2,"0")}</span> EJEMPLOS VISIBLES</div>
          </div>
          <div className={styles.filtros} role="group" aria-label="Filtrar categorías de diplomados">
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
            {visibles.map((diplomado) => (
              <article className={styles.tarjeta} key={diplomado.numero}>
                <div
                  className={styles.tarjetaImagen}
                  style={{ backgroundImage: `linear-gradient(180deg, rgba(4,17,36,.03), rgba(4,17,36,.68)), url("${diplomado.imagen}")` }}
                  role="img"
                  aria-label={`Imagen ilustrativa: ${diplomado.nombre}`}
                >
                  <span className={styles.numeroTarjeta}>{diplomado.numero} / IINICIA</span>
                  <span className={styles.etiquetaImagen}>{diplomado.etiqueta}</span>
                </div>
                <div className={styles.tarjetaContenido}>
                  <span className={styles.categoriaTarjeta}>{diplomado.categoria.toUpperCase()}</span>
                  <h3>{diplomado.nombre}</h3>
                  <p>{diplomado.descripcion}</p>
                  <Link href="/#contacto" className={styles.enlaceTarjeta}>
                    Conocer la plataforma <span aria-hidden="true">↗</span>
                  </Link>
                </div>
              </article>
            ))}
          </div>
          <div className={styles.aviso}>
            <strong>Nota:</strong> Los diplomados, sus títulos e imágenes son contenido de demostración. No representan programas aprobados, inscripciones abiertas, constancias, acreditaciones, fechas ni precios oficiales de IINICIA.
          </div>
        </div>
      </section>

      <section className={styles.seccionFinal}>
        <div className={styles.finalInterior}>
          <span className={styles.etiqueta}>MI IINICIA / PRÓXIMAMENTE</span>
          <h2>El siguiente nivel de formación,<br /><em>en un solo lugar.</em></h2>
          <p>Al integrar Moodle y el backend, el campus podrá mostrar únicamente los programas e inscripciones autorizados para cada estudiante.</p>
          <Link href="/#campus" className={styles.botonPrincipal}>Conoce IINICIA <span aria-hidden="true">↗</span></Link>
        </div>
      </section>

      <footer className={styles.pie}>
        <div className={styles.pieInterior}><span>IINICIA · PROTOTIPO DE PLATAFORMA DIGITAL</span><Link href="/">Volver al inicio ↑</Link></div>
      </footer>
    </main>
  );
}
