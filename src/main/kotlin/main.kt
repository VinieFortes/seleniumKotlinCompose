import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.Key.Companion.B
import androidx.compose.ui.input.key.Key.Companion.F4
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.v1.KeyStroke
import androidx.compose.ui.window.v1.Menu
import androidx.compose.ui.window.v1.MenuBar
import androidx.compose.ui.window.v1.MenuItem
import org.jetbrains.skija.FontStyle
import java.awt.SystemColor.text
import androidx.compose.foundation.lazy.items


fun main() = Window ("Elogiador de Mito",
    size = IntSize(400,300),
    menuBar = MenuBar(Menu("Sobre o mito", MenuItem("Mito",
        shortcut = KeyStroke(B))
    ), Menu("Jovem Pan"), Menu("Como xingar petista"))
) {
    App()

}


@Composable
fun Appp() {
    var textEdit by rememberSaveable { mutableStateOf("") }
    var texto by rememberSaveable { mutableStateOf("") }
    Box(

        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            textView(texto)
            editText(textEdit)
            btn(textEdit,texto)
            // SimpleList()
            mito()
        }
    }
}

@Composable
fun btn(texto1: String, texto2: String) {
    var textEdit by rememberSaveable { mutableStateOf("") }
    var texto by rememberSaveable { mutableStateOf("") }
    var oi by rememberSaveable{ mutableStateOf(texto2)}
    val tim by rememberSaveable{ mutableStateOf(texto1)}
    Button(onClick = {
        texto = textEdit
    }) {
        Text("alterar valor")
    }
}

@Composable
fun editText(txtEdit: String) {
    var edit by rememberSaveable{ mutableStateOf(txtEdit)}
    TextField(
        value = edit,
        onValueChange = {
            edit = it
        },
        maxLines = 1,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun textView(txt: String) {
    Text(
        txt,
        modifier = Modifier.padding(4.dp),
        fontSize = 15.sp,
        fontStyle = androidx.compose.ui.text.font.FontStyle.Normal
    )
}
@Composable
fun mito() {
    Image(bitmap = imageFromResource("icon.jpeg"),"image", modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(12.dp)))
}

