package com.example.prurbaproyecto

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ----------------------------------------------------
// TEMA IINICIA UNIVERSIDAD DIGITAL
// ----------------------------------------------------
val IiniciaDarkNavy = Color(0xFF0D1F3C)
val IiniciaCardNavy = Color(0xFF162C4E)
val IiniciaBlue = Color(0xFF2563EB)
val IiniciaTeal = Color(0xFF2DD4BF)
val IiniciaLightBlue = Color(0xFFE0F2FE)
val IiniciaWhite = Color(0xFFFFFFFF)
val IiniciaTextSecondary = Color(0xFF94A3B8)

val IiniciaColorScheme = darkColorScheme(
    primary = IiniciaTeal,
    onPrimary = IiniciaDarkNavy,
    primaryContainer = IiniciaBlue,
    onPrimaryContainer = IiniciaWhite,
    secondary = IiniciaBlue,
    onSecondary = IiniciaWhite,
    background = IiniciaDarkNavy,
    onBackground = IiniciaWhite,
    surface = IiniciaCardNavy,
    onSurface = IiniciaWhite,
    surfaceVariant = Color(0xFF1E3A60),
    onSurfaceVariant = IiniciaLightBlue
)

// ----------------------------------------------------
// LOGO COMPOSABLE IINICIA
// ----------------------------------------------------
@Composable
fun IiniciaLogoImage(
    modifier: Modifier = Modifier.size(70.dp)
) {
    Image(
        painter = painterResource(id = R.drawable.ic_iinicia_logo),
        contentDescription = "Logo IINICIA",
        modifier = modifier.clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun IiniciaLogoHeaderCard(
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
        border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IiniciaLogoImage(modifier = Modifier.size(54.dp))
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = "IINICIA",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        ),
                        color = IiniciaWhite
                    )
                    Text(
                        text = "UNIVERSIDAD DIGITAL",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        ),
                        color = IiniciaTextSecondary
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Aprende hoy. Transforma tu mañana.",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                ),
                color = IiniciaTeal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "APRENDE • CRECE",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                color = IiniciaLightBlue,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ----------------------------------------------------
// 1. MODELOS DE DATOS
// ----------------------------------------------------
enum class UserRole {
    ALUMNO, PROFESOR
}

data class Teacher(
    val name: String,
    val email: String,
    val role: String,
    val officeHours: String
)

data class Student(
    val id: String,
    val name: String,
    val email: String,
    val courseIds: List<String>
)

data class StudentTaskGrade(
    val studentId: String,
    val taskId: String,
    val grade: String,
    val isSubmitted: Boolean,
    val submissionDate: String,
    val submittedFileName: String? = null,
    val submittedFileUri: String? = null
)

data class Course(
    val id: String,
    val title: String,
    val category: String,
    val progress: Float,
    val description: String,
    val teacher: Teacher,
    val lessons: List<String>
)

data class Task(
    val id: String,
    val courseId: String,
    val courseTitle: String,
    val title: String,
    val dueDate: String,
    val isCompleted: Boolean = false,
    val grade: String? = null,
    val submittedFileName: String? = null,
    val submittedFileUri: String? = null,
    val submissionDate: String? = null
)

data class DocumentItem(
    val id: String,
    val courseId: String,
    val courseTitle: String,
    val title: String,
    val fileType: String,
    val fileSize: String,
    val fileUri: String? = null
)

enum class BottomTab {
    INICIO, ALUMNOS, TAREAS, DOCUMENTOS
}

data class DashboardUiState(
    val isLoggedIn: Boolean = false,
    val userRole: UserRole = UserRole.ALUMNO,
    val currentUserId: String = "s1",
    val userName: String = "",
    val isLoading: Boolean = false,
    val currentTab: BottomTab = BottomTab.INICIO,
    val selectedCourse: Course? = null,
    val selectedStudent: Student? = null,
    val courses: List<Course> = emptyList(),
    val students: List<Student> = emptyList(),
    val tasks: List<Task> = emptyList(),
    val studentGrades: List<StudentTaskGrade> = emptyList(),
    val documents: List<DocumentItem> = emptyList(),
    val errorMessage: String? = null
)

// ----------------------------------------------------
// 2. VIEWMODEL
// ----------------------------------------------------
class DashboardViewModel : ViewModel() {

    private val teacher1 = Teacher(
        name = "Prof. Roberto Gómez",
        email = "rgomez@iinicia.org",
        role = "Especialista en Android & Kotlin",
        officeHours = "Lun y Mié 15:00 - 17:00"
    )

    private val teacher2 = Teacher(
        name = "Dra. Sofía Martínez",
        email = "smartinez@iinicia.org",
        role = "Diseñadora Senior UI/UX",
        officeHours = "Mar y Jue 10:00 - 12:00"
    )

    private val teacher3 = Teacher(
        name = "Ing. Carlos Mendoza",
        email = "cmendoza@iinicia.org",
        role = "Arquitecto de Software Backend",
        officeHours = "Viernes 14:00 - 18:00"
    )

    private val sampleCourses = listOf(
        Course(
            id = "1",
            title = "Desarrollo Android con Kotlin",
            category = "IINICIA - Universidad Digital",
            progress = 0.75f,
            description = "Aprende a construir aplicaciones móviles modernas con Kotlin, Corrutinas y Clean Architecture.",
            teacher = teacher1,
            lessons = listOf("Fundamentos de Kotlin", "Layouts y Material 3", "State Management & ViewModels", "Persistencia con Room")
        ),
        Course(
            id = "2",
            title = "Diseño UI/UX con Jetpack Compose",
            category = "IINICIA - Universidad Digital",
            progress = 0.40f,
            description = "Domina el diseño declarativo de interfaces de usuario para Android usando Jetpack Compose.",
            teacher = teacher2,
            lessons = listOf("Introducción a Compose", "Modificadores y Layouts", "Animaciones y Temas", "Pruebas de UI")
        ),
        Course(
            id = "3",
            title = "Bases de Datos y Arquitectura Clean",
            category = "IINICIA - Universidad Digital",
            progress = 0.15f,
            description = "Estructura aplicaciones escalables usando principios SOLID, inyección de dependencias y patrones de datos.",
            teacher = teacher3,
            lessons = listOf("Principios SOLID", "Inyección con Hilt", "Patrón Repository", "Pruebas Unitarias")
        )
    )

    private val sampleStudents = listOf(
        Student("s1", "Juan Pérez", "juan.perez@iinicia.org", listOf("1", "2")),
        Student("s2", "María García", "maria.garcia@iinicia.org", listOf("1", "3")),
        Student("s3", "Carlos López", "carlos.lopez@iinicia.org", listOf("2", "3")),
        Student("s4", "Ana Torres", "ana.torres@iinicia.org", listOf("1"))
    )

    private val sampleTasks = listOf(
        Task("t1", "1", "Desarrollo Android con Kotlin", "Entregar proyecto de Layouts Compose", "22 Oct, 23:59", true, "10/10", "Proyecto_Layouts_JuanPerez.pdf", null, "21 Oct, 18:30"),
        Task("t2", "1", "Desarrollo Android con Kotlin", "Quiz de Corrutinas y Flows", "25 Oct, 18:00", false, null, null, null, null),
        Task("t3", "2", "Diseño UI/UX con Jetpack Compose", "Crear prototipo de pantalla Dashboard", "24 Oct, 20:00", true, "9.5/10", "Prototipo_Dashboard.zip", null, "23 Oct, 14:15"),
        Task("t4", "3", "Bases de Datos y Arquitectura Clean", "Implementar Patrón Repository con Room", "30 Oct, 23:59", false, null, null, null, null)
    )

    private val sampleGrades = listOf(
        StudentTaskGrade("s1", "t1", "10/10", true, "21 Oct, 18:30", "Proyecto_Layouts_JuanPerez.pdf", null),
        StudentTaskGrade("s1", "t2", "9.0/10", true, "24 Oct", "Quiz_Corrutinas.pdf", null),
        StudentTaskGrade("s1", "t3", "9.5/10", true, "23 Oct", "Prototipo_Dashboard.zip", null),
        StudentTaskGrade("s2", "t1", "8.5/10", true, "21 Oct, 19:10", "Layouts_Maria.pdf", null),
        StudentTaskGrade("s2", "t2", "Sin calificar", true, "25 Oct, 16:20", "Corrutinas_Maria.pdf", null),
        StudentTaskGrade("s2", "t4", "Sin entregar", false, "-", null, null),
        StudentTaskGrade("s3", "t3", "10/10", true, "23 Oct, 11:00", "Dashboard_Carlos.zip", null),
        StudentTaskGrade("s3", "t4", "9.0/10", true, "28 Oct, 15:45", "CleanArch_Carlos.pdf", null),
        StudentTaskGrade("s4", "t1", "7.0/10", true, "22 Oct, 20:00", "Layouts_Ana.pdf", null),
        StudentTaskGrade("s4", "t2", "Sin entregar", false, "-", null, null)
    )

    private val sampleDocs = listOf(
        DocumentItem("d1", "1", "Desarrollo Android con Kotlin", "Guía IINICIA Sintaxis Kotlin.pdf", "PDF", "2.4 MB", null),
        DocumentItem("d2", "1", "Desarrollo Android con Kotlin", "Manual IINICIA Arquitectura.pdf", "PDF", "4.1 MB", null),
        DocumentItem("d3", "2", "Diseño UI/UX con Jetpack Compose", "Guía IINICIA Estilos UI.pdf", "PDF", "5.8 MB", null),
        DocumentItem("d4", "3", "Bases de Datos y Arquitectura Clean", "Diagrama Clean Architecture.png", "Imagen", "1.2 MB", null)
    )

    private val _uiState = MutableStateFlow(
        DashboardUiState(
            isLoggedIn = false,
            courses = sampleCourses,
            students = sampleStudents,
            tasks = sampleTasks,
            studentGrades = sampleGrades,
            documents = sampleDocs
        )
    )
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun login(role: UserRole, name: String) {
        val matchedStudent = _uiState.value.students.find { it.name.equals(name, ignoreCase = true) }
        val studentId = matchedStudent?.id ?: "s1"

        _uiState.value = _uiState.value.copy(
            isLoggedIn = true,
            userRole = role,
            currentUserId = if (role == UserRole.ALUMNO) studentId else "teacher1",
            userName = if (name.isBlank()) (if (role == UserRole.ALUMNO) "Juan Pérez" else "Prof. Roberto Gómez") else name,
            currentTab = BottomTab.INICIO,
            selectedCourse = null,
            selectedStudent = null
        )
    }

    fun logout() {
        _uiState.value = _uiState.value.copy(
            isLoggedIn = false,
            selectedCourse = null,
            selectedStudent = null
        )
    }

    fun selectTab(tab: BottomTab) {
        _uiState.value = _uiState.value.copy(
            currentTab = tab,
            selectedCourse = null,
            selectedStudent = null
        )
    }

    fun selectCourse(course: Course?) {
        _uiState.value = _uiState.value.copy(selectedCourse = course, selectedStudent = null)
    }

    fun selectStudent(student: Student?) {
        _uiState.value = _uiState.value.copy(selectedStudent = student)
    }

    fun toggleTaskCompletion(taskId: String) {
        val updatedTasks = _uiState.value.tasks.map { task ->
            if (task.id == taskId) task.copy(isCompleted = !task.isCompleted) else task
        }
        _uiState.value = _uiState.value.copy(tasks = updatedTasks)
    }

    fun submitStudentTask(taskId: String, fileName: String, fileUriStr: String? = null) {
        val studentId = _uiState.value.currentUserId
        val submissionDateStr = "26 Oct, 08:00"

        val updatedTasks = _uiState.value.tasks.map { task ->
            if (task.id == taskId) task.copy(
                isCompleted = true,
                submittedFileName = fileName,
                submittedFileUri = fileUriStr,
                submissionDate = submissionDateStr
            ) else task
        }

        val existingGradeIndex = _uiState.value.studentGrades.indexOfFirst {
            it.taskId == taskId && it.studentId == studentId
        }

        val updatedGrades = if (existingGradeIndex != -1) {
            _uiState.value.studentGrades.mapIndexed { index, gradeRecord ->
                if (index == existingGradeIndex) {
                    gradeRecord.copy(
                        isSubmitted = true,
                        submissionDate = submissionDateStr,
                        submittedFileName = fileName,
                        submittedFileUri = fileUriStr
                    )
                } else gradeRecord
            }
        } else {
            _uiState.value.studentGrades + StudentTaskGrade(
                studentId = studentId,
                taskId = taskId,
                grade = "Sin calificar",
                isSubmitted = true,
                submissionDate = submissionDateStr,
                submittedFileName = fileName,
                submittedFileUri = fileUriStr
            )
        }

        _uiState.value = _uiState.value.copy(
            tasks = updatedTasks,
            studentGrades = updatedGrades
        )
    }

    fun addTask(courseId: String, courseTitle: String, title: String, dueDate: String) {
        val newTask = Task(
            id = "t_${System.currentTimeMillis()}",
            courseId = courseId,
            courseTitle = courseTitle,
            title = title,
            dueDate = dueDate,
            isCompleted = false,
            grade = null
        )
        _uiState.value = _uiState.value.copy(tasks = _uiState.value.tasks + newTask)
    }

    fun gradeTask(taskId: String, grade: String) {
        val updatedTasks = _uiState.value.tasks.map { task ->
            if (task.id == taskId) task.copy(grade = grade, isCompleted = true) else task
        }
        _uiState.value = _uiState.value.copy(tasks = updatedTasks)
    }

    fun updateStudentGrade(studentId: String, taskId: String, newGrade: String) {
        val existingIndex = _uiState.value.studentGrades.indexOfFirst {
            it.studentId == studentId && it.taskId == taskId
        }
        val updatedGrades = if (existingIndex != -1) {
            _uiState.value.studentGrades.mapIndexed { index, item ->
                if (index == existingIndex) item.copy(grade = newGrade, isSubmitted = true) else item
            }
        } else {
            _uiState.value.studentGrades + StudentTaskGrade(
                studentId = studentId,
                taskId = taskId,
                grade = newGrade,
                isSubmitted = true,
                submissionDate = "Hoy",
                submittedFileName = "Entrega_Adjunta.pdf"
            )
        }
        _uiState.value = _uiState.value.copy(studentGrades = updatedGrades)
    }

    fun addDocument(courseId: String, courseTitle: String, title: String, fileType: String, fileUriStr: String? = null) {
        val newDoc = DocumentItem(
            id = "d_${System.currentTimeMillis()}",
            courseId = courseId,
            courseTitle = courseTitle,
            title = title,
            fileType = fileType,
            fileSize = if (fileType.contains("Imagen", ignoreCase = true)) "Imagen adjunta" else "1.5 MB",
            fileUri = fileUriStr
        )
        _uiState.value = _uiState.value.copy(documents = _uiState.value.documents + newDoc)
    }
}

// ----------------------------------------------------
// 3. ACTIVIDAD PRINCIPAL
// ----------------------------------------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(colorScheme = IiniciaColorScheme) {
                Surface(modifier = Modifier.fillMaxSize(), color = IiniciaDarkNavy) {
                    val viewModel: DashboardViewModel = viewModel()
                    MainAppScreen(viewModel = viewModel)
                }
            }
        }
    }
}

// ----------------------------------------------------
// 4. PANTALLA PRINCIPAL
// ----------------------------------------------------
@Composable
fun MainAppScreen(
    viewModel: DashboardViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    if (!state.isLoggedIn) {
        LoginScreen(onLogin = { role, name -> viewModel.login(role, name) })
    } else if (state.selectedStudent != null) {
        StudentGradesDetailScreen(
            student = state.selectedStudent!!,
            courses = state.courses.filter { state.selectedStudent!!.courseIds.contains(it.id) },
            tasks = state.tasks,
            studentGrades = state.studentGrades.filter { it.studentId == state.selectedStudent!!.id },
            onBackClick = { viewModel.selectStudent(null) },
            onUpdateGrade = { studentId, taskId, grade ->
                viewModel.updateStudentGrade(studentId, taskId, grade)
            }
        )
    } else if (state.selectedCourse != null) {
        CourseDetailScreen(
            course = state.selectedCourse!!,
            userRole = state.userRole,
            students = state.students.filter { it.courseIds.contains(state.selectedCourse!!.id) },
            tasks = state.tasks.filter { it.courseId == state.selectedCourse!!.id },
            documents = state.documents.filter { it.courseId == state.selectedCourse!!.id },
            studentGrades = state.studentGrades,
            onBackClick = { viewModel.selectCourse(null) },
            onSelectStudent = { viewModel.selectStudent(it) },
            onToggleTask = { viewModel.toggleTaskCompletion(it) },
            onSubmitTask = { taskId, fileName, fileUri -> viewModel.submitStudentTask(taskId, fileName, fileUri) },
            onGradeTask = { taskId, grade -> viewModel.gradeTask(taskId, grade) },
            onUpdateStudentGrade = { studentId, taskId, grade -> viewModel.updateStudentGrade(studentId, taskId, grade) }
        )
    } else {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    userRole = state.userRole,
                    currentTab = state.currentTab,
                    onTabSelected = { viewModel.selectTab(it) }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (state.currentTab) {
                    BottomTab.INICIO -> HomeScreen(
                        state = state,
                        onCourseClick = { viewModel.selectCourse(it) },
                        onLogout = { viewModel.logout() }
                    )
                    BottomTab.ALUMNOS -> StudentsScreen(
                        state = state,
                        onStudentClick = { viewModel.selectStudent(it) }
                    )
                    BottomTab.TAREAS -> TasksScreen(
                        state = state,
                        onToggleTask = { viewModel.toggleTaskCompletion(it) },
                        onSubmitTask = { taskId, fileName, fileUri -> viewModel.submitStudentTask(taskId, fileName, fileUri) },
                        onAddTask = { courseId, courseTitle, title, dueDate ->
                            viewModel.addTask(courseId, courseTitle, title, dueDate)
                        },
                        onGradeTask = { taskId, grade -> viewModel.gradeTask(taskId, grade) },
                        onUpdateStudentGrade = { studentId, taskId, grade -> viewModel.updateStudentGrade(studentId, taskId, grade) }
                    )
                    BottomTab.DOCUMENTOS -> DocumentsScreen(
                        state = state,
                        onAddDocument = { courseId, courseTitle, title, fileType, fileUri ->
                            viewModel.addDocument(courseId, courseTitle, title, fileType, fileUri)
                        }
                    )
                }
            }
        }
    }
}

// ----------------------------------------------------
// 5. PANTALLA DE INICIO DE SESIÓN CON CONEXIÓN RETROFIT
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogin: (UserRole, String) -> Unit
) {
    val context = LocalContext.current
    var selectedRole by remember { mutableStateOf(UserRole.ALUMNO) }
    var nameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    // VARIABLES PARA LA CONEXIÓN A NESTJS
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("IINICIA - Universidad Digital", color = IiniciaWhite, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = IiniciaDarkNavy)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                IiniciaLogoHeaderCard()
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                    border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = "Selecciona tu Perfil:", style = MaterialTheme.typography.labelLarge, color = IiniciaTeal)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { selectedRole = UserRole.ALUMNO }
                            ) {
                                RadioButton(
                                    selected = selectedRole == UserRole.ALUMNO,
                                    onClick = { selectedRole = UserRole.ALUMNO },
                                    colors = RadioButtonDefaults.colors(selectedColor = IiniciaTeal)
                                )
                                Text("Alumno", fontWeight = FontWeight.Bold, color = IiniciaWhite)
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { selectedRole = UserRole.PROFESOR }
                            ) {
                                RadioButton(
                                    selected = selectedRole == UserRole.PROFESOR,
                                    onClick = { selectedRole = UserRole.PROFESOR },
                                    colors = RadioButtonDefaults.colors(selectedColor = IiniciaTeal)
                                )
                                Text("Profesor", fontWeight = FontWeight.Bold, color = IiniciaWhite)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("Nombre Completo (Opcional)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IiniciaTeal,
                        unfocusedBorderColor = IiniciaTextSecondary
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    label = { Text("Correo Institucional (@iinicia.org)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IiniciaTeal,
                        unfocusedBorderColor = IiniciaTextSecondary
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = passwordInput,
                    onValueChange = { passwordInput = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IiniciaTeal,
                        unfocusedBorderColor = IiniciaTextSecondary
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(20.dp))

                // BOTÓN ACTUALIZADO CON CONEXIÓN AL BACKEND
                Button(
                    onClick = {
                        if (emailInput.isNotBlank() && passwordInput.isNotBlank()) {
                            isLoading = true
                            coroutineScope.launch {
                                try {
                                    // 1. Envía los datos reales escritos a tu computadora por IP local
                                    val request = LoginRequest(identificador = emailInput, contrasena = passwordInput)
                                    val respuesta = RetrofitClient.apiService.login(request)

                                    if (respuesta.isSuccessful) {
                                        // 2. Si NestJS devuelve un Token exitoso, permite entrar
                                        val token = respuesta.body()?.access_token
                                        Toast.makeText(context, "¡Conexión Exitosa con Servidor!", Toast.LENGTH_SHORT).show()

                                        // Avanzamos al Dashboard mostrando los datos del usuario
                                        onLogin(selectedRole, nameInput.ifBlank { emailInput })
                                    } else {
                                        // 3. Si las credenciales son incorrectas
                                        Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                                    }
                                } catch (e: Exception) {
                                    // 4. Si la app no encuentra tu computadora en la red Wi-Fi
                                    Toast.makeText(context, "Error de red: Verifica que tu servidor esté corriendo", Toast.LENGTH_LONG).show()
                                } finally {
                                    isLoading = false
                                }
                            }
                        } else {
                            Toast.makeText(context, "Por favor ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text(
                        text = if (isLoading) "Conectando al servidor..." else if (selectedRole == UserRole.ALUMNO) "Entrar como Alumno IINICIA" else "Entrar como Profesor IINICIA",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { openMoodleWebSSO(context, "https://iinicia.org/") },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, IiniciaTeal),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = IiniciaTeal)
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Abrir Moodle LMS Web (https://iinicia.org/)")
                }
            }
        }
    }
}

// ----------------------------------------------------
// 6. BARRA NAVEGACIÓN INFERIOR IINICIA
// ----------------------------------------------------
@Composable
fun BottomNavigationBar(
    userRole: UserRole,
    currentTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit
) {
    NavigationBar(containerColor = IiniciaCardNavy) {
        NavigationBarItem(
            selected = currentTab == BottomTab.INICIO,
            onClick = { onTabSelected(BottomTab.INICIO) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = IiniciaTeal,
                selectedTextColor = IiniciaTeal,
                indicatorColor = IiniciaBlue
            )
        )
        if (userRole == UserRole.PROFESOR) {
            NavigationBarItem(
                selected = currentTab == BottomTab.ALUMNOS,
                onClick = { onTabSelected(BottomTab.ALUMNOS) },
                icon = { Icon(Icons.Default.Person, contentDescription = "Alumnos") },
                label = { Text("Alumnos") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = IiniciaTeal,
                    selectedTextColor = IiniciaTeal,
                    indicatorColor = IiniciaBlue
                )
            )
        }
        NavigationBarItem(
            selected = currentTab == BottomTab.TAREAS,
            onClick = { onTabSelected(BottomTab.TAREAS) },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Tareas") },
            label = { Text("Tareas") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = IiniciaTeal,
                selectedTextColor = IiniciaTeal,
                indicatorColor = IiniciaBlue
            )
        )
        NavigationBarItem(
            selected = currentTab == BottomTab.DOCUMENTOS,
            onClick = { onTabSelected(BottomTab.DOCUMENTOS) },
            icon = { Icon(Icons.Default.Info, contentDescription = "Documentos") },
            label = { Text("Documentos") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = IiniciaTeal,
                selectedTextColor = IiniciaTeal,
                indicatorColor = IiniciaBlue
            )
        )
    }
}

// ----------------------------------------------------
// 7. PESTAÑA INICIO
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: DashboardUiState,
    onCourseClick: (Course) -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IiniciaLogoImage(modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("IINICIA - Universidad Digital", color = IiniciaWhite, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar Sesión", tint = IiniciaWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = IiniciaDarkNavy)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Hola, ${state.userName}",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = IiniciaWhite
                        )
                        Text(
                            text = if (state.userRole == UserRole.ALUMNO) "Perfil de Estudiante IINICIA" else "Perfil Docente IINICIA",
                            style = MaterialTheme.typography.bodySmall,
                            color = IiniciaTeal
                        )
                    }
                    OutlinedButton(
                        onClick = onLogout,
                        border = BorderStroke(1.dp, IiniciaTeal),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = IiniciaTeal)
                    ) {
                        Text("Salir")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { openMoodleWebSSO(context, "https://iinicia.org/") },
                    colors = CardDefaults.cardColors(containerColor = IiniciaBlue),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Moodle LMS Web iinicia.org",
                            tint = IiniciaWhite,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Portal Moodle LMS (https://iinicia.org/)",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = IiniciaWhite
                            )
                            Text(
                                text = "Acceso directo a la plataforma web Moodle oficial de IINICIA",
                                style = MaterialTheme.typography.bodySmall,
                                color = IiniciaLightBlue
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Abrir Moodle",
                            tint = IiniciaTeal
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = if (state.userRole == UserRole.ALUMNO) "Mis Cursos Activos" else "Listado de Cursos Impartidos",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = IiniciaTeal
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.courses) { course ->
                CourseCard(
                    course = course,
                    userRole = state.userRole,
                    studentCount = state.students.count { it.courseIds.contains(course.id) },
                    onClick = { onCourseClick(course) }
                )
            }
        }
    }
}

@Composable
fun CourseCard(
    course: Course,
    userRole: UserRole,
    studentCount: Int,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
        border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = course.category, style = MaterialTheme.typography.labelSmall, color = IiniciaTeal, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = course.title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (userRole == UserRole.PROFESOR) "$studentCount Alumnos inscritos" else "Prof. ${course.teacher.name}",
                style = MaterialTheme.typography.bodyMedium,
                color = IiniciaTextSecondary
            )
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { course.progress },
                modifier = Modifier.fillMaxWidth(),
                color = IiniciaTeal,
                trackColor = IiniciaDarkNavy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${(course.progress * 100).toInt()}% completado",
                style = MaterialTheme.typography.bodySmall,
                color = IiniciaLightBlue
            )
        }
    }
}

// ----------------------------------------------------
// 8. PESTAÑA ALUMNOS (PARA PROFESORES IINICIA)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(
    state: DashboardUiState,
    onStudentClick: (Student) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alumnos IINICIA", color = IiniciaWhite, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = IiniciaDarkNavy)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    text = "Alumnos Registrados en tus Cursos",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = IiniciaTeal
                )
                Text(
                    text = "Haz clic en un alumno para revisar sus entregas y calificar",
                    style = MaterialTheme.typography.bodySmall,
                    color = IiniciaTextSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.students) { student ->
                val enrolledCourseNames = state.courses
                    .filter { student.courseIds.contains(it.id) }
                    .joinToString(", ") { it.title }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onStudentClick(student) },
                    colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                    border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(IiniciaBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = IiniciaWhite
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = student.name, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                            Text(text = student.email, style = MaterialTheme.typography.bodySmall, color = IiniciaTextSecondary)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Cursos: $enrolledCourseNames",
                                style = MaterialTheme.typography.labelSmall,
                                color = IiniciaTeal
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Calificaciones",
                            tint = IiniciaTeal
                        )
                    }
                }
            }
        }
    }
}

// ----------------------------------------------------
// 9. DETALLE Y CALIFICACIONES DEL ALUMNO
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentGradesDetailScreen(
    student: Student,
    courses: List<Course>,
    tasks: List<Task>,
    studentGrades: List<StudentTaskGrade>,
    onBackClick: () -> Unit,
    onUpdateGrade: (String, String, String) -> Unit
) {
    val context = LocalContext.current
    var selectedTaskIdToGrade by remember { mutableStateOf<String?>(null) }
    var selectedTaskTitleToGrade by remember { mutableStateOf("") }
    var gradeInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Boletín: ${student.name}", color = IiniciaWhite, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = IiniciaWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = IiniciaDarkNavy)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                    border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = student.name, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                        Text(text = student.email, style = MaterialTheme.typography.bodyMedium, color = IiniciaTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Historial IINICIA y Calificaciones de Tareas",
                            style = MaterialTheme.typography.labelLarge,
                            color = IiniciaTeal
                        )
                    }
                }
            }

            courses.forEach { course ->
                item {
                    Text(
                        text = "Curso: ${course.title}",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = IiniciaTeal
                    )
                }

                val courseTasks = tasks.filter { it.courseId == course.id }
                if (courseTasks.isEmpty()) {
                    item {
                        Text("No hay tareas asignadas a este curso.", style = MaterialTheme.typography.bodySmall, color = IiniciaTextSecondary)
                    }
                } else {
                    items(courseTasks) { task ->
                        val studentGradeRecord = studentGrades.find { it.taskId == task.id }
                        val gradeText = studentGradeRecord?.grade ?: "Sin calificar"
                        val isSubmitted = studentGradeRecord?.isSubmitted == true
                        val submittedFile = studentGradeRecord?.submittedFileName ?: "Sin archivo adjunto"

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                            border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(text = task.title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                                        Text(
                                            text = if (isSubmitted) "Estado: Entregado (${studentGradeRecord?.submissionDate ?: "Reciente"})" else "Estado: Sin entregar",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = if (isSubmitted) IiniciaTeal else Color.Red
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Nota: $gradeText",
                                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                            color = IiniciaTeal
                                        )
                                    }
                                    Button(
                                        onClick = {
                                            selectedTaskIdToGrade = task.id
                                            selectedTaskTitleToGrade = task.title
                                            gradeInput = if (gradeText != "Sin calificar" && gradeText != "Sin entregar") gradeText else "10/10"
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                                    ) {
                                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Calificar")
                                    }
                                }

                                if (isSubmitted && studentGradeRecord != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = IiniciaDarkNavy),
                                        border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.5f)),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "📄 $submittedFile",
                                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                                color = IiniciaLightBlue,
                                                modifier = Modifier.weight(1f)
                                            )
                                            Button(
                                                onClick = {
                                                    openSubmittedFile(context, studentGradeRecord.submittedFileUri, submittedFile)
                                                },
                                                colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite),
                                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                            ) {
                                                Text("👁️ Abrir Archivo", fontSize = 11.sp)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (selectedTaskIdToGrade != null) {
        AlertDialog(
            onDismissRequest = { selectedTaskIdToGrade = null },
            title = { Text("Calificar a ${student.name}") },
            text = {
                Column {
                    Text("Tarea: $selectedTaskTitleToGrade")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = gradeInput,
                        onValueChange = { gradeInput = it },
                        label = { Text("Calificación (Ej: 10/10, 8.5)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (gradeInput.isNotBlank()) {
                            onUpdateGrade(student.id, selectedTaskIdToGrade!!, gradeInput)
                            selectedTaskIdToGrade = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Guardar Nota")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { selectedTaskIdToGrade = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// ----------------------------------------------------
// 10. PESTAÑA TAREAS
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    state: DashboardUiState,
    onToggleTask: (String) -> Unit,
    onSubmitTask: (String, String, String?) -> Unit,
    onAddTask: (String, String, String, String) -> Unit,
    onGradeTask: (String, String) -> Unit,
    onUpdateStudentGrade: (String, String, String) -> Unit
) {
    val context = LocalContext.current
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var selectedTaskToSubmit by remember { mutableStateOf<Task?>(null) }
    var selectedTaskForTeacherSubmissions by remember { mutableStateOf<Task?>(null) }
    var fileNameInput by remember { mutableStateOf("") }
    var selectedFileUriStr by remember { mutableStateOf<String?>(null) }
    var selectedStudentForGrading by remember { mutableStateOf<Student?>(null) }
    var gradeInputForStudent by remember { mutableStateOf("") }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedFileUriStr = uri.toString()
            val extractedName = getFileNameFromUri(context, uri)
            if (!extractedName.isNullOrBlank()) {
                fileNameInput = extractedName
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.userRole == UserRole.PROFESOR) "Gestión de Tareas IINICIA" else "Mis Tareas IINICIA", color = IiniciaWhite, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = IiniciaDarkNavy)
            )
        },
        floatingActionButton = {
            if (state.userRole == UserRole.PROFESOR) {
                FloatingActionButton(onClick = { showAddTaskDialog = true }, containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy) {
                    Icon(Icons.Default.Add, contentDescription = "Nueva Tarea")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.tasks) { task ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                    border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (state.userRole == UserRole.ALUMNO) {
                                Checkbox(
                                    checked = task.isCompleted,
                                    onCheckedChange = { onToggleTask(task.id) },
                                    colors = CheckboxDefaults.colors(checkedColor = IiniciaTeal)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = task.title,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = IiniciaWhite
                                )
                                Text(
                                    text = task.courseTitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = IiniciaTeal
                                )
                                Text(
                                    text = "Fecha límite: ${task.dueDate}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = IiniciaTextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        if (state.userRole == UserRole.ALUMNO) {
                            if (task.submittedFileName != null) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = IiniciaDarkNavy),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openSubmittedFile(context, task.submittedFileUri, task.submittedFileName)
                                        }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = IiniciaTeal,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = "Entregado (${task.submissionDate})",
                                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                                color = IiniciaTeal
                                            )
                                            Text(
                                                text = "📄 Archivo: ${task.submittedFileName} (Clic para abrir)",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = IiniciaWhite
                                            )
                                        }
                                    }
                                }
                            } else {
                                Text(
                                    text = "Estado: Pendiente de entrega",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Red
                                )
                            }

                            if (task.grade != null) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Calificación: ${task.grade}",
                                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                    color = IiniciaTeal
                                )
                            }
                        } else if (state.userRole == UserRole.PROFESOR) {
                            val submittedCount = state.studentGrades.count { it.taskId == task.id && it.isSubmitted }
                            val totalStudentsInCourse = state.students.count { it.courseIds.contains(task.courseId) }

                            Text(
                                text = "Entregas recibidas: $submittedCount de $totalStudentsInCourse alumnos",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                color = IiniciaTeal
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            if (state.userRole == UserRole.ALUMNO) {
                                Button(
                                    onClick = {
                                        selectedTaskToSubmit = task
                                        fileNameInput = task.submittedFileName ?: ""
                                        selectedFileUriStr = task.submittedFileUri
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                                ) {
                                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(if (task.submittedFileName != null) "Re-entregar" else "Subir y Entregar")
                                }
                            } else if (state.userRole == UserRole.PROFESOR) {
                                Button(
                                    onClick = {
                                        selectedTaskForTeacherSubmissions = task
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                                ) {
                                    Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("📄 Ver Entregas de Alumnos")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (selectedTaskForTeacherSubmissions != null) {
        val currentTask = selectedTaskForTeacherSubmissions!!
        val enrolledStudents = state.students.filter { it.courseIds.contains(currentTask.courseId) }

        AlertDialog(
            onDismissRequest = { selectedTaskForTeacherSubmissions = null },
            title = {
                Column {
                    Text("Revisión de Entregas", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                    Text(currentTask.title, style = MaterialTheme.typography.bodySmall, color = IiniciaTeal)
                }
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(enrolledStudents) { student ->
                        val gradeRecord = state.studentGrades.find { it.studentId == student.id && it.taskId == currentTask.id }
                        val isSubmitted = gradeRecord?.isSubmitted == true
                        val fileName = gradeRecord?.submittedFileName ?: "Sin archivo"

                        Card(
                            colors = CardDefaults.cardColors(containerColor = IiniciaDarkNavy),
                            border = BorderStroke(1.dp, if (isSubmitted) IiniciaTeal else Color.Red.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(student.name, fontWeight = FontWeight.Bold, color = IiniciaWhite)
                                        Text(student.email, style = MaterialTheme.typography.labelSmall, color = IiniciaTextSecondary)
                                    }
                                    Text(
                                        text = if (isSubmitted) "✅ Entregado" else "❌ Pendiente",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = if (isSubmitted) IiniciaTeal else Color.Red
                                    )
                                }

                                if (isSubmitted) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "📄 $fileName",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = IiniciaLightBlue,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Button(
                                            onClick = {
                                                openSubmittedFile(context, gradeRecord?.submittedFileUri, fileName)
                                            },
                                            colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite),
                                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                        ) {
                                            Text("👁️ Abrir Archivo", fontSize = 11.sp)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Nota: ${gradeRecord?.grade ?: "Sin calificar"}",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = IiniciaTeal
                                    )
                                    OutlinedButton(
                                        onClick = {
                                            selectedStudentForGrading = student
                                            gradeInputForStudent = gradeRecord?.grade ?: "10/10"
                                        },
                                        border = BorderStroke(1.dp, IiniciaTeal),
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text("✏️ Calificar", fontSize = 11.sp, color = IiniciaTeal)
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedTaskForTeacherSubmissions = null },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Cerrar")
                }
            }
        )
    }

    if (selectedStudentForGrading != null && selectedTaskForTeacherSubmissions != null) {
        AlertDialog(
            onDismissRequest = { selectedStudentForGrading = null },
            title = { Text("Calificar a ${selectedStudentForGrading!!.name}") },
            text = {
                Column {
                    Text("Tarea: ${selectedTaskForTeacherSubmissions!!.title}")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = gradeInputForStudent,
                        onValueChange = { gradeInputForStudent = it },
                        label = { Text("Calificación (Ej: 10/10, 8.5)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (gradeInputForStudent.isNotBlank()) {
                            onUpdateStudentGrade(selectedStudentForGrading!!.id, selectedTaskForTeacherSubmissions!!.id, gradeInputForStudent)
                            selectedStudentForGrading = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Guardar Nota")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { selectedStudentForGrading = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (selectedTaskToSubmit != null) {
        AlertDialog(
            onDismissRequest = { selectedTaskToSubmit = null },
            title = { Text("Entregar Tarea: ${selectedTaskToSubmit!!.title}") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Selecciona un archivo directamente desde tu dispositivo:")

                    Button(
                        onClick = { filePickerLauncher.launch("*/*") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("📁 Abrir Almacenamiento del Dispositivo")
                    }

                    if (fileNameInput.isNotBlank()) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                            border = BorderStroke(1.dp, IiniciaTeal),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Archivo Adjunto Seleccionado:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = IiniciaTeal
                                )
                                Text(
                                    text = "📄 $fileNameInput",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    color = IiniciaWhite
                                )
                            }
                        }
                    }

                    OutlinedTextField(
                        value = fileNameInput,
                        onValueChange = { fileNameInput = it },
                        label = { Text("Nombre del Archivo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (fileNameInput.isNotBlank()) {
                            onSubmitTask(selectedTaskToSubmit!!.id, fileNameInput, selectedFileUriStr)
                            Toast.makeText(context, "¡Tarea entregada!", Toast.LENGTH_LONG).show()
                            selectedTaskToSubmit = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Confirmar Entrega")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { selectedTaskToSubmit = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showAddTaskDialog) {
        var taskTitle by remember { mutableStateOf("") }
        var selectedCourse by remember { mutableStateOf(state.courses.firstOrNull()) }
        var dueDateInput by remember { mutableStateOf("30 Nov, 23:59") }

        AlertDialog(
            onDismissRequest = { showAddTaskDialog = false },
            title = { Text("Crear Nueva Tarea IINICIA") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        label = { Text("Título de la Tarea") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = dueDateInput,
                        onValueChange = { dueDateInput = it },
                        label = { Text("Fecha Límite de Entrega") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text("Seleccionar Curso:", style = MaterialTheme.typography.labelLarge)
                    state.courses.forEach { course ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedCourse = course }
                        ) {
                            RadioButton(
                                selected = selectedCourse?.id == course.id,
                                onClick = { selectedCourse = course },
                                colors = RadioButtonDefaults.colors(selectedColor = IiniciaTeal)
                            )
                            Text(course.title, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (taskTitle.isNotBlank() && selectedCourse != null) {
                            onAddTask(selectedCourse!!.id, selectedCourse!!.title, taskTitle, dueDateInput)
                            showAddTaskDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Guardar Tarea")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showAddTaskDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// ----------------------------------------------------
// 11. PESTAÑA DOCUMENTOS (EL PROFESOR PUEDE SUBIR IMÁGENES O ARCHIVOS)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentsScreen(
    state: DashboardUiState,
    onAddDocument: (String, String, String, String, String?) -> Unit
) {
    val context = LocalContext.current
    var showAddDocDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Documentos y Guías IINICIA", color = IiniciaWhite, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = IiniciaDarkNavy)
            )
        },
        floatingActionButton = {
            if (state.userRole == UserRole.PROFESOR) {
                FloatingActionButton(onClick = { showAddDocDialog = true }, containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy) {
                    Icon(Icons.Default.Add, contentDescription = "Subir Documento o Imagen")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.documents) { doc ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            openSubmittedFile(context, doc.fileUri, doc.title)
                        },
                    colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                    border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Documento/Imagen",
                            tint = IiniciaTeal,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = doc.title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                            Text(
                                text = "${doc.courseTitle}\n${doc.fileSize} (${doc.fileType})",
                                style = MaterialTheme.typography.bodySmall,
                                color = IiniciaTextSecondary
                            )
                        }
                        Button(
                            onClick = { openSubmittedFile(context, doc.fileUri, doc.title) },
                            colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text("👁️ Ver", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }

    if (showAddDocDialog) {
        var docTitle by remember { mutableStateOf("") }
        var selectedCourse by remember { mutableStateOf(state.courses.firstOrNull()) }
        var selectedDocUriStr by remember { mutableStateOf<String?>(null) }

        val docPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                selectedDocUriStr = uri.toString()
                val extractedName = getFileNameFromUri(context, uri)
                if (!extractedName.isNullOrBlank()) {
                    docTitle = extractedName
                }
            }
        }

        AlertDialog(
            onDismissRequest = { showAddDocDialog = false },
            title = { Text("Subir Archivo o Imagen como Profesor") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Selecciona una imagen o documento desde tu almacenamiento:")

                    Button(
                        onClick = { docPickerLauncher.launch("*/*") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("📁 Seleccionar Imagen o Archivo")
                    }

                    if (docTitle.isNotBlank()) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                            border = BorderStroke(1.dp, IiniciaTeal),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text("Archivo / Imagen Seleccionada:", style = MaterialTheme.typography.labelSmall, color = IiniciaTeal)
                                Text("🖼️ $docTitle", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                            }
                        }
                    }

                    OutlinedTextField(
                        value = docTitle,
                        onValueChange = { docTitle = it },
                        label = { Text("Título / Nombre del Material") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Text("Asignar al Curso:", style = MaterialTheme.typography.labelLarge)
                    state.courses.forEach { course ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedCourse = course }
                        ) {
                            RadioButton(
                                selected = selectedCourse?.id == course.id,
                                onClick = { selectedCourse = course },
                                colors = RadioButtonDefaults.colors(selectedColor = IiniciaTeal)
                            )
                            Text(course.title, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (docTitle.isNotBlank() && selectedCourse != null) {
                            val isImage = docTitle.endsWith(".png", true) || docTitle.endsWith(".jpg", true) || docTitle.endsWith(".jpeg", true) || docTitle.endsWith(".webp", true)
                            val type = if (isImage) "Imagen" else "Documento PDF"
                            onAddDocument(selectedCourse!!.id, selectedCourse!!.title, docTitle, type, selectedDocUriStr)
                            Toast.makeText(context, "¡Material publicado con éxito para los alumnos!", Toast.LENGTH_LONG).show()
                            showAddDocDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Subir y Publicar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showAddDocDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// ----------------------------------------------------
// 12. DETALLE DEL CURSO
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    course: Course,
    userRole: UserRole,
    students: List<Student>,
    tasks: List<Task>,
    documents: List<DocumentItem>,
    studentGrades: List<StudentTaskGrade>,
    onBackClick: () -> Unit,
    onSelectStudent: (Student) -> Unit,
    onToggleTask: (String) -> Unit,
    onSubmitTask: (String, String, String?) -> Unit,
    onGradeTask: (String, String) -> Unit,
    onUpdateStudentGrade: (String, String, String) -> Unit
) {
    val context = LocalContext.current
    var showMessageDialog by remember { mutableStateOf(false) }
    var messageText by remember { mutableStateOf("") }
    var selectedTaskToSubmit by remember { mutableStateOf<Task?>(null) }
    var selectedTaskForTeacherSubmissions by remember { mutableStateOf<Task?>(null) }
    var fileNameInput by remember { mutableStateOf("") }
    var selectedFileUriStr by remember { mutableStateOf<String?>(null) }
    var selectedStudentForGrading by remember { mutableStateOf<Student?>(null) }
    var gradeInputForStudent by remember { mutableStateOf("") }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedFileUriStr = uri.toString()
            val name = getFileNameFromUri(context, uri)
            if (!name.isNullOrBlank()) {
                fileNameInput = name
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(course.title, color = IiniciaWhite, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = IiniciaWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = IiniciaDarkNavy)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                    border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = course.category,
                            style = MaterialTheme.typography.labelMedium,
                            color = IiniciaTeal,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = course.title,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = IiniciaWhite
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = course.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = IiniciaTextSecondary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        LinearProgressIndicator(
                            progress = { course.progress },
                            modifier = Modifier.fillMaxWidth(),
                            color = IiniciaTeal,
                            trackColor = IiniciaDarkNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Progreso del curso: ${(course.progress * 100).toInt()}%",
                            style = MaterialTheme.typography.labelSmall,
                            color = IiniciaLightBlue
                        )
                    }
                }
            }

            if (userRole == UserRole.PROFESOR) {
                item {
                    Text(text = "Alumnos Inscritos (${students.size})", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = IiniciaTeal)
                }
                items(students) { student ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectStudent(student) },
                        colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                        border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = null,
                                tint = IiniciaTeal
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = student.name, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                                Text(text = student.email, style = MaterialTheme.typography.bodySmall, color = IiniciaTextSecondary)
                            }
                            OutlinedButton(
                                onClick = { onSelectStudent(student) },
                                border = BorderStroke(1.dp, IiniciaTeal),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = IiniciaTeal)
                            ) {
                                Text("Ver Notas")
                            }
                        }
                    }
                }
            }

            item {
                Text(text = "Contacto con el Profesor", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = IiniciaTeal)
                Spacer(modifier = Modifier.height(8.dp))
                TeacherContactCard(
                    teacher = course.teacher,
                    onEmailClick = {
                        sendEmail(context, course.teacher.email, "Consulta sobre el curso ${course.title}")
                    },
                    onMessageClick = {
                        showMessageDialog = true
                    }
                )
            }

            item {
                Text(text = "Módulos y Lecciones", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = IiniciaTeal)
            }
            items(course.lessons) { lesson ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                    border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = IiniciaTeal
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = lesson, style = MaterialTheme.typography.bodyLarge, color = IiniciaWhite)
                    }
                }
            }

            if (tasks.isNotEmpty()) {
                item {
                    Text(text = "Tareas del Curso", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = IiniciaTeal)
                }
                items(tasks) { task ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                        border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (userRole == UserRole.ALUMNO) {
                                    Checkbox(
                                        checked = task.isCompleted,
                                        onCheckedChange = { onToggleTask(task.id) },
                                        colors = CheckboxDefaults.colors(checkedColor = IiniciaTeal)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = task.title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                                    Text(text = "Entrega: ${task.dueDate}", style = MaterialTheme.typography.bodySmall, color = IiniciaTextSecondary)
                                    if (userRole == UserRole.ALUMNO && task.submittedFileName != null) {
                                        Text(
                                            text = "📄 Entregado: ${task.submittedFileName} (Clic para ver)",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = IiniciaTeal,
                                            modifier = Modifier.clickable {
                                                openSubmittedFile(context, task.submittedFileUri, task.submittedFileName)
                                            }
                                        )
                                    }
                                    if (userRole == UserRole.ALUMNO && task.grade != null) {
                                        Text(
                                            text = "Calificación: ${task.grade}",
                                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                            color = IiniciaTeal
                                        )
                                    }
                                    if (userRole == UserRole.PROFESOR) {
                                        val submittedCount = studentGrades.count { it.taskId == task.id && it.isSubmitted }
                                        Text(
                                            text = "Entregas recibidas: $submittedCount de ${students.size} alumnos",
                                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                            color = IiniciaTeal
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                if (userRole == UserRole.ALUMNO) {
                                    Button(
                                        onClick = {
                                            selectedTaskToSubmit = task
                                            fileNameInput = task.submittedFileName ?: ""
                                            selectedFileUriStr = task.submittedFileUri
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                                    ) {
                                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(if (task.submittedFileName != null) "Re-entregar" else "Entregar Tarea")
                                    }
                                } else if (userRole == UserRole.PROFESOR) {
                                    Button(
                                        onClick = {
                                            selectedTaskForTeacherSubmissions = task
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                                    ) {
                                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("📄 Ver Entregas")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (documents.isNotEmpty()) {
                item {
                    Text(text = "Materiales y Documentos", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = IiniciaTeal)
                }
                items(documents) { doc ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                openSubmittedFile(context, doc.fileUri, doc.title)
                            },
                        colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                        border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = IiniciaTeal,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = doc.title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                                Text(text = "${doc.fileSize} (${doc.fileType})", style = MaterialTheme.typography.bodySmall, color = IiniciaTextSecondary)
                            }
                            Button(
                                onClick = { openSubmittedFile(context, doc.fileUri, doc.title) },
                                colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text("👁️ Ver", fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }
    }

    if (selectedTaskForTeacherSubmissions != null) {
        val currentTask = selectedTaskForTeacherSubmissions!!

        AlertDialog(
            onDismissRequest = { selectedTaskForTeacherSubmissions = null },
            title = {
                Column {
                    Text("Revisión de Entregas", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                    Text(currentTask.title, style = MaterialTheme.typography.bodySmall, color = IiniciaTeal)
                }
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(students) { student ->
                        val gradeRecord = studentGrades.find { it.studentId == student.id && it.taskId == currentTask.id }
                        val isSubmitted = gradeRecord?.isSubmitted == true
                        val fileName = gradeRecord?.submittedFileName ?: "Sin archivo"

                        Card(
                            colors = CardDefaults.cardColors(containerColor = IiniciaDarkNavy),
                            border = BorderStroke(1.dp, if (isSubmitted) IiniciaTeal else Color.Red.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(student.name, fontWeight = FontWeight.Bold, color = IiniciaWhite)
                                        Text(student.email, style = MaterialTheme.typography.labelSmall, color = IiniciaTextSecondary)
                                    }
                                    Text(
                                        text = if (isSubmitted) "✅ Entregado" else "❌ Pendiente",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = if (isSubmitted) IiniciaTeal else Color.Red
                                    )
                                }

                                if (isSubmitted) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "📄 $fileName",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = IiniciaLightBlue,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Button(
                                            onClick = {
                                                openSubmittedFile(context, gradeRecord?.submittedFileUri, fileName)
                                            },
                                            colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite),
                                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                        ) {
                                            Text("👁️ Abrir Archivo", fontSize = 11.sp)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Nota: ${gradeRecord?.grade ?: "Sin calificar"}",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = IiniciaTeal
                                    )
                                    OutlinedButton(
                                        onClick = {
                                            selectedStudentForGrading = student
                                            gradeInputForStudent = gradeRecord?.grade ?: "10/10"
                                        },
                                        border = BorderStroke(1.dp, IiniciaTeal),
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text("✏️ Calificar", fontSize = 11.sp, color = IiniciaTeal)
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedTaskForTeacherSubmissions = null },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Cerrar")
                }
            }
        )
    }

    if (selectedStudentForGrading != null && selectedTaskForTeacherSubmissions != null) {
        AlertDialog(
            onDismissRequest = { selectedStudentForGrading = null },
            title = { Text("Calificar a ${selectedStudentForGrading!!.name}") },
            text = {
                Column {
                    Text("Tarea: ${selectedTaskForTeacherSubmissions!!.title}")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = gradeInputForStudent,
                        onValueChange = { gradeInputForStudent = it },
                        label = { Text("Calificación (Ej: 10/10, 8.5)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (gradeInputForStudent.isNotBlank()) {
                            onUpdateStudentGrade(selectedStudentForGrading!!.id, selectedTaskForTeacherSubmissions!!.id, gradeInputForStudent)
                            selectedStudentForGrading = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Guardar Nota")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { selectedStudentForGrading = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (selectedTaskToSubmit != null) {
        AlertDialog(
            onDismissRequest = { selectedTaskToSubmit = null },
            title = { Text("Entregar Tarea: ${selectedTaskToSubmit!!.title}") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Selecciona un archivo directamente desde tu dispositivo:")

                    Button(
                        onClick = { filePickerLauncher.launch("*/*") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = IiniciaBlue, contentColor = IiniciaWhite)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("📁 Abrir Almacenamiento del Dispositivo")
                    }

                    if (fileNameInput.isNotBlank()) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
                            border = BorderStroke(1.dp, IiniciaTeal),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Archivo Adjunto Seleccionado:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = IiniciaTeal
                                )
                                Text(
                                    text = "📄 $fileNameInput",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    color = IiniciaWhite
                                )
                            }
                        }
                    }

                    OutlinedTextField(
                        value = fileNameInput,
                        onValueChange = { fileNameInput = it },
                        label = { Text("Nombre del Archivo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (fileNameInput.isNotBlank()) {
                            onSubmitTask(selectedTaskToSubmit!!.id, fileNameInput, selectedFileUriStr)
                            Toast.makeText(context, "¡Tarea entregada!", Toast.LENGTH_LONG).show()
                            selectedTaskToSubmit = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Confirmar Entrega")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { selectedTaskToSubmit = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showMessageDialog) {
        AlertDialog(
            onDismissRequest = { showMessageDialog = false },
            title = { Text("Mensaje a ${course.teacher.name}") },
            text = {
                Column {
                    Text("Escribe tu consulta o duda sobre el curso:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ej: Tengo una duda sobre la tarea...") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            Toast.makeText(
                                context,
                                "Mensaje enviado a ${course.teacher.name}",
                                Toast.LENGTH_LONG
                            ).show()
                            messageText = ""
                            showMessageDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Text("Enviar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showMessageDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// ----------------------------------------------------
// 13. TARJETA DOCENTE IINICIA
// ----------------------------------------------------
@Composable
fun TeacherContactCard(
    teacher: Teacher,
    onEmailClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = IiniciaCardNavy),
        border = BorderStroke(1.dp, IiniciaTeal.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(IiniciaBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = IiniciaWhite
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = teacher.name, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = IiniciaWhite)
                    Text(text = teacher.role, style = MaterialTheme.typography.bodySmall, color = IiniciaTeal)
                    Text(text = "Atención: ${teacher.officeHours}", style = MaterialTheme.typography.labelSmall, color = IiniciaTextSecondary)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onEmailClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = IiniciaTeal, contentColor = IiniciaDarkNavy)
                ) {
                    Icon(Icons.Default.Email, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Correo")
                }
                OutlinedButton(
                    onClick = onMessageClick,
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(1.dp, IiniciaTeal),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = IiniciaTeal)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Mensaje")
                }
            }
        }
    }
}

// ----------------------------------------------------
// FUNCIONES AUXILIARES PARA ARCHIVOS Y SSO
// ----------------------------------------------------
fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var fileName: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex != -1) {
                fileName = it.getString(nameIndex)
            }
        }
    }
    return fileName ?: uri.lastPathSegment
}

fun openSubmittedFile(context: Context, fileUriString: String?, fileName: String) {
    if (!fileUriString.isNullOrBlank()) {
        try {
            val uri = Uri.parse(fileUriString)
            val mimeType = context.contentResolver.getType(uri) ?: "*/*"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, mimeType)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(intent)
            return
        } catch (_: Exception) {
            // fallback
        }
    }
    Toast.makeText(context, "Abriendo archivo: $fileName", Toast.LENGTH_SHORT).show()
}

fun sendEmail(context: Context, email: String, subject: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
        putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    try {
        context.startActivity(Intent.createChooser(intent, "Enviar correo al profesor..."))
    } catch (_: Exception) {
        Toast.makeText(context, "Correo del profesor: $email", Toast.LENGTH_LONG).show()
    }
}

fun openMoodleWebSSO(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        context.startActivity(Intent.createChooser(intent, "Conectando con Portal Moodle..."))
    } catch (_: Exception) {
        Toast.makeText(context, "Conectando con Portal Moodle LMS...", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    MaterialTheme(colorScheme = IiniciaColorScheme) {
        MainAppScreen()
    }
}