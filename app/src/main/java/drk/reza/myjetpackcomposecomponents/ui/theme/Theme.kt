package drk.reza.myjetpackcomposecomponents.ui.theme

import android.app.Activity
import android.os.Build
import android.provider.CalendarContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyJetpackComposeComponentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


val CalendarContract.Colors.composeThemeColor: Color
    @Composable get() = Color(0xFF4285f4)

val CalendarContract.Colors.inputBackground: Color
    @Composable get() = TailwindCSSColor.Gray100

val CalendarContract.Colors.onInputBackground: Color
    @Composable get() = TailwindCSSColor.Gray900

val CalendarContract.Colors.errorInputBackground: Color
    @Composable get() = TailwindCSSColor.Red500.copy(.1f)

object AppColor {
//    val ExampleColor = Color(0xff123456)
}

object FlatColor {
    // Color from: https://flatuicolors.com/palette/defo
    val FlatAwesomeGreen1 = Color(0xFF1abc9c)
    val FlatAwesomeGreen2 = Color(0xFF16a085)
    val FlatGreen1 = Color(0xFF2ecc71)
    val FlatGreen2 = Color(0xFF27ae60)
    val FlatBlue1 = Color(0xFF3498db)
    val FlatBlue2 = Color(0xFF2980b9)
    val FlatPink1 = Color(0xFF9b59b6)
    val FlatGreyDark1 = Color(0xFF34495e)
    val FlatGreyDark2 = Color(0xFF2c3e50)
    val FlatGreyNormal1 = Color(0xFF95a5a6)
    val FlatGreyNormal2 = Color(0xFF7f8c8d)
    val FlatGreyLight1 = Color(0xFFecf0f1)
    val FlatGreyLight2 = Color(0xFFbdc3c7)
    val FlatRed1 = Color(0xFFe74c3c)
    val FlatRed2 = Color(0xFFc0392b)
    val FlatOrange1 = Color(0xFFe67e22)
    val FlatOrange2 = Color(0xFFd35400)
    val FlatYellow1 = Color(0xFFf1c40f)
    val FlatYellow2 = Color(0xFFf39c12)
}

object TailwindCSSColor {
    // Color from: https://tailwindcss.com/docs/customizing-colors

    // colors.trueGray
    val Gray50 = Color(0xFFFAFAFA)
    val Gray100 = Color(0xFFF5F5F5)
    val Gray200 = Color(0xFFE5E5E5)
    val Gray300 = Color(0xFFD4D4D4)
    val Gray400 = Color(0xFFA3A3A3)
    val Gray500 = Color(0xFF737373)
    val Gray600 = Color(0xFF525252)
    val Gray700 = Color(0xFF404040)
    val Gray800 = Color(0xFF262626)
    val Gray900 = Color(0xFF171717)

    // colors.red
    val Red500 = Color(0xFFEF4444)
    val Red700 = Color(0xFFB91C1C)
    val Red900 = Color(0xFF7F1D1D)

    // colors.amber
    val Yellow500 = Color(0xFFF59E0B)
    val Yellow700 = Color(0xFFB45309)
    val Yellow900 = Color(0xFF78350F)

    // colors.emerald
    val Green500 = Color(0xFF10B981)
    val Green700 = Color(0xFF047857)
    val Green900 = Color(0xFF064E3B)

    // colors.blue
    val Blue500 = Color(0xFF3B82F6)
    val Blue700 = Color(0xFF1D4ED8)
    val Blue900 = Color(0xFF1E3A8A)

    // colors.indigo
    val Indigo500 = Color(0xFF6366F1)
    val Indigo700 = Color(0xFF4338CA)
    val Indigo900 = Color(0xFF312E81)

    // colors.violet
    val Purple500 = Color(0xFF8B5CF6)
    val Purple700 = Color(0xFF6D28D9)
    val Purple900 = Color(0xFF4C1D95)

    // colors.pink
    val Pink500 = Color(0xFFEC4899)
    val Pink700 = Color(0xFFBE185D)
    val Pink900 = Color(0xFF831843)
}
