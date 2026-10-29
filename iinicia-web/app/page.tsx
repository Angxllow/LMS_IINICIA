"use client";

import { useState } from "react";
import Image from "next/image"; 
import FormularioContacto from "./components/FormularioContacto";

const ofertas = [
  {categoria:"Cursos", titulo:"Aprende nuevas habilidades",descripcion:"Explora cursos diseñados para continuar aprendiendo a tu ritmo.",imagen:"https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=1100&q=85",tag:"Aprendizaje continuo",numero:"01"},
  {categoria:"Certificaciones",titulo:"Impulsa tu perfil",descripcion:"Descubre alternativas para fortalecer tu preparación profesional.",imagen:"https://images.unsplash.com/photo-1434030216411-0b793f4b4173?auto=format&fit=crop&w=1100&q=85",tag:"Desarrollo profesional",numero:"02"},
  {categoria:"Diplomados",titulo:"Especializa tu conocimiento",descripcion:"Conoce opciones de formación para profundizar en áreas de interés.",imagen:"https://images.unsplash.com/photo-1523240795612-9a054b0db644?auto=format&fit=crop&w=1100&q=85",tag:"Formación especializada",numero:"03"},
];

const dudas = [
  {q:"¿Ya puedo inscribirme desde esta página?",a:"Todavía no. Este portal es un prototipo visual. El registro real se implementará después, con validación, autenticación y autorización académica."},
  {q:"¿Mi Campus ya está conectado con Moodle?",a:"Aún no. El panel es una vista demostrativa. La conexión segura con Moodle y el inicio de sesión unificado forman parte de una siguiente fase."},
  {q:"¿Los programas que aparecen son oficiales?",a:"Las categorías están basadas en la estructura del proyecto. Antes de publicar, IINICIA deberá validar nombres de programas, requisitos, fechas y datos de contacto."},
];

function Arrow({diagonal=false}:{diagonal?:boolean}) {
  return <span aria-hidden="true">{diagonal ? "↗" : "→"}</span>;
}

export default function Home() {
  const [menuAbierto,setMenuAbierto] = useState(false);
  const [filtro,setFiltro] = useState("Todos");
  const [dudaAbierta,setDudaAbierta] = useState<number|null>(0);
  const filtradas = filtro === "Todos" ? ofertas : ofertas.filter(o=>o.categoria===filtro);
  const cerrarMenu = () => setMenuAbierto(false);

  return <main id="inicio">
    <div className="announcement"><span className="status-dot"/> NUEVA EXPERIENCIA DIGITAL IINICIA <span className="announcement-separator">/</span> UN SOLO ESPACIO PARA APRENDER</div>
    <header className="site-header">
      <div className="header-inner container">
        <a href="#inicio" className="brand" onClick={cerrarMenu} aria-label="IINICIA - ir al inicio">

<Image
  src="/imagenes/Logo-con-Blanco-1.png"
  alt="Logotipo oficial de IINICIA"
  width={190}
  height={70}
  style={{
    maxWidth: "190px",
    width: "auto",
    height: "70px",
    objectFit: "contain",
  }}
  priority
/>
        </a>
        <button type="button" className="menu-toggle" aria-label={menuAbierto ? "Cerrar menú" : "Abrir menú"} aria-expanded={menuAbierto} aria-controls="menu-principal" onClick={()=>setMenuAbierto(!menuAbierto)}>{menuAbierto ? "✕" : "☰"}</button>
        <nav id="menu-principal" aria-label="Navegación principal" className={`nav ${menuAbierto ? "nav-open" : ""}`}>
          <a href="#inicio" onClick={cerrarMenu}>Inicio</a><a href="#universidad" onClick={cerrarMenu}>Universidad</a><a href="/cursos" onClick={cerrarMenu}>Oferta académica</a><a href="/certificaciones" onClick={cerrarMenu}>Certificaciones</a>
<a href="/diplomados" onClick={cerrarMenu}>Diplomados</a>
<a href="#campus" onClick={cerrarMenu}>Campus digital</a><a href="#admisiones" onClick={cerrarMenu}>Admisiones</a>
          <a className="nav-cta" href="#contacto" onClick={cerrarMenu}>Solicitar información <Arrow diagonal/></a>
        
<a href="/registro" onClick={cerrarMenu}>
  Registrarse
</a>

<a href="/iniciar-sesion" className="nav-cta" onClick={cerrarMenu}>
  Iniciar sesión
</a>

</nav>
      </div>
    </header>

    <section className="hero" aria-labelledby="hero-title">
      <div className="hero-photo"/><div className="hero-overlay"/><div className="hero-grid"/>
      <div className="container hero-content">
        <div className="hero-copy">
          <span className="eyebrow"><span className="eyebrow-line"/> PLATAFORMA DIGITAL UNIVERSITARIA</span>
          <h1 id="hero-title">El siguiente paso <span>de tu historia</span> comienza aquí<span className="heading-dot">.</span></h1>
          <p>Descubre una nueva experiencia para explorar tu formación, conectar con el conocimiento y construir lo que sigue.</p>
          <div className="hero-actions"><a className="btn btn-primary" href="#oferta">Explorar oferta académica <Arrow diagonal/></a><a className="btn btn-glass" href="#universidad"><span className="play-circle">↗</span> Conoce IINICIA</a></div>
          <div className="hero-meta"><span className="pulse-point"/> Un nuevo espacio para aprender <span className="hero-meta-rule"/> Diseño digital • 2026</div>
        </div>
        <div className="hero-aside" aria-hidden="true"><span>01 / 03</span><div className="hero-aside-line"/><span>INNOVAR ES AVANZAR</span></div>
      </div>
      <a href="#universidad" className="scroll-cue">DESLIZA PARA EXPLORAR <span>↓</span></a>
      <div className="hero-bottom-accent"/>
    </section>

    <section className="quick-links" aria-label="Secciones principales"><div className="container quick-grid">
      <a href="#oferta"><span className="quick-index">01 / DESCUBRE</span><strong>Oferta académica</strong><span className="quick-arrow">↗</span></a>
      <a href="#universidad"><span className="quick-index">02 / CONOCE</span><strong>Nuestra institución</strong><span className="quick-arrow">↗</span></a>
      <a href="#campus"><span className="quick-index">03 / CONECTA</span><strong>Campus digital</strong><span className="quick-arrow">↗</span></a>
    </div></section>

    <section id="universidad" className="intro section-pad"><div className="container intro-layout">
      <div className="intro-visual"><div className="intro-image" role="img" aria-label="Estudiantes reunidos en un entorno universitario"/><div className="image-frame"/><div className="visual-sticker"><span>+</span> Una nueva forma<br/>de aprender</div></div>
      <div className="intro-copy"><span className="section-kicker">01 — CONOCE IINICIA</span><h2>El conocimiento transforma. <em>La innovación conecta.</em></h2><p>Estamos construyendo una nueva experiencia digital universitaria para reunir información institucional, programas académicos y servicios en un entorno coherente y fácil de utilizar.</p><p>Un portal pensado desde el inicio para conectar el futuro campus Moodle, la gestión de cuentas y el acceso desde dispositivos móviles.</p><div className="intro-facts"><div><b>01</b><span>Una identidad<br/>digital</span></div><div><b>02</b><span>Web y campus<br/>conectados</span></div><div><b>03</b><span>Experiencia<br/>multidispositivo</span></div></div><a href="#campus" className="text-link">Explora la experiencia digital <Arrow diagonal/></a></div>
    </div></section>

    <section id="oferta" className="programs section-pad"><div className="container"><div className="section-head"><div><span className="section-kicker">02 — TU CAMINO EMPIEZA AQUÍ</span><h2>Explora nuevas <em>posibilidades.</em></h2></div><p>Descubre las principales categorías de formación contempladas para el portal institucional IINICIA.</p></div>
      <div className="filter-bar" role="group" aria-label="Filtrar oferta académica">{["Todos","Cursos","Certificaciones","Diplomados"].map(item=><button key={item} type="button" aria-pressed={filtro===item} onClick={()=>setFiltro(item)} className={`filter-button ${filtro===item?"filter-active":""}`}>{item}</button>)}</div>
      <div className="program-grid">{filtradas.map((item)=><article className="program-card" key={item.categoria}><div className="program-image" style={{backgroundImage:`linear-gradient(180deg, transparent 25%, rgba(4,18,41,.65)),url('${item.imagen}')`}} role="img" aria-label={`Imagen ilustrativa de ${item.categoria}`}><span className="program-number">{item.numero} / IINICIA</span><span className="program-photo-label">{item.tag}</span></div><div className="program-info"><span className="program-label">{item.categoria.toUpperCase()}</span><h3>{item.titulo}</h3><p>{item.descripcion}</p><a href="#contacto" aria-label={`Solicitar información sobre ${item.categoria}`}>Conocer más <span>↗</span></a></div></article>)}</div>
      <p className="disclaimer">* Las categorías son de referencia; IINICIA deberá confirmar los programas y su información oficial antes de publicar.</p>
    </div></section>

    <section className="manifesto"><div className="container manifesto-inner"><span className="manifesto-symbol">✳</span><div><span className="section-kicker">EDUCACIÓN + TECNOLOGÍA + FUTURO</span><h2>No se trata solamente de aprender. <span>Se trata de avanzar.</span></h2></div><a href="#admisiones" className="round-arrow" aria-label="Conocer proceso de admisión">↗</a></div></section>

    <section id="campus" className="campus section-pad"><div className="campus-glow"/><div className="container campus-layout"><div className="campus-copy"><span className="section-kicker">03 — MI IINICIA</span><h2>Tu experiencia universitaria, <em>en un solo lugar.</em></h2><p>Imaginamos un campus digital conectado donde puedas consultar tus cursos autorizados, tus próximas actividades y la información académica relevante.</p><div className="campus-benefits"><div><span>✓</span> Acceso con una sola identidad digital</div><div><span>✓</span> Cursos y actividades integrados con Moodle</div><div><span>✓</span> Experiencia compatible con móvil</div></div><span className="development-pill"><span className="status-dot"/> Vista conceptual · Integración en desarrollo</span></div>
      <div className="dashboard" aria-label="Ejemplo ilustrativo del futuro panel de estudiante"><div className="dash-bar"><div className="dash-logo">I<span>²</span></div><span>MI IINICIA <small>· DEMO</small></span><div className="dash-avatar">E</div></div><div className="dash-body"><aside className="dash-sidebar"><b>⌂</b><b>▦</b><b>◷</b><b>⚙</b></aside><div className="dash-main"><span className="dash-subtitle">PANEL DEL ESTUDIANTE</span><h3>¡Bienvenido de nuevo! 👋</h3><p>Todo tu aprendizaje, en un solo espacio.</p><div className="dash-stats"><div><span>CURSOS</span><strong>03</strong><small>Ejemplo</small></div><div><span>ACTIVIDADES</span><strong>08</strong><small>Ejemplo</small></div><div><span>AVISOS</span><strong>02</strong><small>Ejemplo</small></div></div><div className="dash-course"><span>CURSOS DEMOSTRATIVOS</span><div className="dash-course-row"><div className="course-icon">⌘</div><div><strong>Innovación y tecnología</strong><small>Progreso ilustrativo</small></div><b>65%</b></div><div className="progress-track"><span style={{width:"65%"}}/></div><div className="dash-course-row"><div className="course-icon course-icon-alt">✦</div><div><strong>Formación digital</strong><small>Progreso ilustrativo</small></div><b>40%</b></div><div className="progress-track"><span style={{width:"40%"}}/></div></div></div></div><div className="dash-footnote">DISEÑO CONCEPTUAL · SIN DATOS REALES</div></div>
    </div></section>

    <section id="admisiones" className="admissions section-pad"><div className="container"><div className="section-head admissions-head"><div><span className="section-kicker">04 — TU SIGUIENTE PASO</span><h2>Tu futuro tiene un <em>punto de partida.</em></h2></div><p>Así está pensado el proceso digital de admisión que desarrollaremos en las siguientes fases.</p></div><div className="steps-grid">{[{n:"01",title:"Descubre",body:"Explora los programas y encuentra el área que te interesa."},{n:"02",title:"Regístrate",body:"Crea una cuenta y completa tu solicitud en el portal."},{n:"03",title:"Autorízate",body:"La institución valida tu admisión y accesos académicos."},{n:"04",title:"Conéctate",body:"Consulta tus cursos desde Mi IINICIA y Moodle."}].map(s=><div className="step" key={s.n}><span className="step-number">{s.n}</span><span className="step-connector"/><h3>{s.title}</h3><p>{s.body}</p></div>)}</div><p className="admissions-note">El registro, las autorizaciones y el acceso al campus aún no están habilitados en esta demostración.</p></div></section>

    <section className="faq section-pad" aria-labelledby="faq-heading"><div className="container faq-layout"><div><span className="section-kicker">05 — PREGUNTAS FRECUENTES</span><h2 id="faq-heading">Lo que necesitas <em>saber.</em></h2><p>Respuestas sobre esta primera versión del nuevo portal digital.</p></div><div className="faq-list">{dudas.map((d,i)=><div key={d.q} className="faq-item"><button type="button" aria-expanded={dudaAbierta===i} onClick={()=>setDudaAbierta(dudaAbierta===i?null:i)}><span>{d.q}</span><span>{dudaAbierta===i?"−":"+"}</span></button>{dudaAbierta===i&&<p>{d.a}</p>}</div>)}</div></div></section>

    <section id="contacto" className="closing"><div className="closing-light"/><div className="container closing-inner"><span className="section-kicker">UN NUEVO CAPÍTULO PARA IINICIA</span><h2>El futuro comienza <em>con una idea.</em></h2><p>Explora el proyecto y conoce cómo se está construyendo la próxima experiencia digital de IINICIA.</p><a href="https://iinicia.org/" target="_blank" rel="noopener noreferrer" className="btn btn-white">Consultar sitio institucional actual <Arrow diagonal/></a><span className="closing-caption">Este prototipo no sustituye todavía al portal oficial ni procesa solicitudes.</span></div></section>

    
<FormularioContacto />
<footer className="footer"><div className="container footer-main"><div><a href="#inicio" className="footer-brand">IINICIA<span>.</span></a><p>Una nueva plataforma digital universitaria enfocada en el aprendizaje, la innovación y la conexión.</p></div><div><h3>Explora</h3><a href="#universidad">Universidad</a><a href="#oferta">Oferta académica</a><a href="/certificaciones" onClick={cerrarMenu}>Certificaciones</a><a href="#campus">Campus digital</a></div><div><h3>Información</h3><a href="#admisiones">Admisiones</a><a href="#contacto">Contacto</a><a href="https://iinicia.org/" target="_blank" rel="noopener noreferrer">Sitio institucional ↗</a></div><div><h3>Proyecto</h3><span>React + Next.js</span><span>Diseño responsive</span><span>Preparado para integración futura</span></div></div><div className="container footer-bottom"><span>© 2026 IINICIA. Prototipo de rediseño.</span><span>HECHO PARA EL FUTURO ✳</span></div></footer>
  </main>;
}
