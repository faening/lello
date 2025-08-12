package io.github.faening.lello.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow50

@Composable
fun LelloOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "",
    maxLength: Int = 40,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    showCounter: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    isEmail: Boolean = false,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val hasEmailError = isEmail && value.isNotEmpty() && !value.contains("@")

    Column(modifier = modifier) {
        if (!label.isNullOrBlank()) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = Dimension.Small),
                fontWeight = FontWeight.ExtraBold,
            )
        }
        Box(
            modifier = Modifier
                .padding(bottom = Dimension.Small, end = Dimension.Small)
        ) {
            // Fake Shadow
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = Dimension.Small, y = Dimension.Small)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = Dimension.ALPHA_DISABLED),
                        shape = RoundedCornerShape(Dimension.Small)
                    )
            )

            OutlinedTextField(
                value = value,
                onValueChange = { if (it.length <= maxLength) onValueChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Yellow50,
                        shape = RoundedCornerShape(Dimension.Small)
                    )
                    .border(
                        width = 1.5.dp,
                        color = if (hasEmailError) Color.Red else MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(Dimension.Small)
                    ),
                placeholder = { Text(placeholder) },
                shape = RoundedCornerShape(Dimension.Small),
                singleLine = singleLine,
                enabled = enabled,
                textStyle = MaterialTheme.typography.bodyLarge,
                leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = null) } },
                trailingIcon = when {
                    isPassword -> {
                        {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (passwordVisibility) "Hide password" else "Show password",
                                modifier = Modifier.clickable { passwordVisibility = !passwordVisibility }
                            )
                        }
                    }
                    trailingIcon != null -> {
                        { Icon(trailingIcon, contentDescription = null) }
                    }
                    else -> null
                },
                visualTransformation = if (isPassword && !passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
            )
        }

        // Validação de email
        if (hasEmailError) {
            Text(
                text = "Email deve conter @",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red,
                modifier = Modifier.padding(start = Dimension.Small, top = 4.dp)
            )
        }

        if (showCounter) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${value.length}/$maxLength",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Preview(
    name = "Light",
    group = "Unselected",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun LelloTextFieldPreview() {
    LelloTheme {
        LelloOutlinedTextField(
            value = "This is a sample text area",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 40,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    name = "Password Field",
    group = "TextField",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun LelloPasswordTextFieldPreview() {
    LelloTheme {
        LelloOutlinedTextField(
            value = "password123",
            onValueChange = {},
            label = "Password",
            placeholder = "Enter your password",
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    name = "Email Field",
    group = "TextField",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun LelloEmailTextFieldPreview() {
    LelloTheme {
        LelloOutlinedTextField(
            value = "emailexample.com",
            onValueChange = {},
            label = "Email",
            placeholder = "Enter your email",
            isEmail = true,
            maxLength = 40,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
