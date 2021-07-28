import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.lang.Thread.sleep
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var textoEmail: String = "Digite seu email:"
var textoSenha: String = "Digite sua senha:"

fun main() = Window(
    "Bot Senai",
    size = IntSize(400, 350)
) {
    App()
}

@Composable
fun App() {
    val textEmail = remember { mutableStateOf(TextFieldValue()) }
    val textSenha = remember { mutableStateOf(TextFieldValue()) }
    val horaIncio = remember { mutableStateOf(TextFieldValue()) }
    val minIncio = remember { mutableStateOf(TextFieldValue()) }

    val hour = DateTimeFormatter.ofPattern("HH")
    val minutes = DateTimeFormatter.ofPattern("mm")
    var hora = hour.format(LocalDateTime.now())
    var min = minutes.format(LocalDateTime.now())

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            textView(textoEmail)
            TextField(value = textEmail.value,
                maxLines = 1,
                singleLine = true,
                onValueChange = { textEmail.value = it }
            )

            textView(textoSenha)
            TextField(value = textSenha.value,
                onValueChange = { textSenha.value = it },
                maxLines = 1,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)){
                Text("Inicio da Aula:")

                TextField(
                    value = horaIncio.value,
                    maxLines = 1,
                    modifier = Modifier.size(55.dp,48.dp),
                    singleLine = true,
                    onValueChange = { horaIncio.value = it }
                )
                Text("h")

                TextField(
                    value = minIncio.value,
                    maxLines = 1,
                    modifier = Modifier.size(55.dp,48.dp),
                    singleLine = true,
                    onValueChange = { minIncio.value = it }
                )
                Text("min")
            }
                Button(onClick = {
                    StateLoading(horaIncio.value.text, minIncio.value.text, textEmail.value.text, textSenha.value.text)
                    AppManager.windows[0].minimize()

            }, modifier = Modifier.padding(10.dp)){
                Text("Rodar o bot")
            }

        }
    }
}

fun StateLoading(horaIncio: String, minInicio: String, Email: String, Senha: String) = Window("Bot Senai",size = IntSize(250, 150)){

    var hour = DateTimeFormatter.ofPattern("HH")
    var minutes = DateTimeFormatter.ofPattern("mm")
    var hora = hour.format(LocalDateTime.now())
    var min = minutes.format(LocalDateTime.now())
    print("teste")

    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Text("Aguarde a aula começar em: ${horaIncio}h ${minInicio}min", modifier = Modifier.padding(10.dp))
        CircularProgressIndicator()
    }

    while(hora <= horaIncio && min < minInicio){
        sleep(5000)
        hour = DateTimeFormatter.ofPattern("HH")
        minutes = DateTimeFormatter.ofPattern("mm")
        hora = hour.format(LocalDateTime.now())
        min = minutes.format(LocalDateTime.now())
    }
        selenium(Email, Senha)
}

fun whileinicio(email: String, senha: String, horaIncio: String, minIncio: String) {

    var hour = DateTimeFormatter.ofPattern("HH")
    var minutes = DateTimeFormatter.ofPattern("mm")
    var hora = hour.format(LocalDateTime.now())
    var min = minutes.format(LocalDateTime.now())

    while(hora <= horaIncio && min < minIncio){
        sleep(5000)
        hour = DateTimeFormatter.ofPattern("HH")
        minutes = DateTimeFormatter.ofPattern("mm")
        hora = hour.format(LocalDateTime.now())
        min = minutes.format(LocalDateTime.now())
    }
    selenium(email, senha)
}

fun selenium(email: String, senha: String) {
    System.setProperty("webdriver.chrome.driver", "/home/vinicius/Documentos/chromedriver")
    val options = ChromeOptions()
    options.addArguments("--disable-extensions", "--no-sandbox", "--disable-infobars")
    val prefs: MutableMap<String, Any> = HashMap()
    prefs["profile.default_content_setting_values.notifications"] = 2
    prefs["profile.content_settings.exceptions.media_stream_camera"] = 2
    prefs["profile.content_settings.exceptions.media_stream_mic"] = 2
    options.setExperimentalOption("prefs", prefs)
    val driver = ChromeDriver(options)

    driver.get("https://classroom.google.com/u/0/h")
    sleep(5000)
    driver.findElementByXPath("//*[@id=\"identifierId\"]").sendKeys(email, Keys.ENTER)
    sleep(5000)
    driver.findElementByXPath("//*[@id=\"password\"]/div[1]/div/div[1]/input").sendKeys(senha, Keys.ENTER)
    sleep(5000)
    driver.get("https://meet.google.com/kbk-gnhj-roj?authuser=0")
    sleep(5000)
    driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "d"))
    driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "e"))
    sleep(5000)
    driver.findElementByXPath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div[9]/div[3]/div/div/div[4]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/span/span").click()

}