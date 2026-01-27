package com.example.targetsavings.ui.components

import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.sp
import com.example.targetsavings.screens.DepositMethod
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    prefixText: String? = null, // 👈 optional prefix
    trailingIcon: (@Composable (() -> Unit))? = null // <- optional trailing icon

) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholderText) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        trailingIcon = trailingIcon, // <- pass it to the actual TextField
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFC6C6C6),
            unfocusedBorderColor = Color(0xFFC6C6C6),
            cursorColor = Color(0xFF272935)
        ),
        leadingIcon = null,
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        prefix = prefixText?.let {
            {
                Text(
                    text = it,
                    color = Color(0xFF272935),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropdown(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Select a category",
    leadingIcon: @Composable (() -> Unit)? = null // optional icon at the start of the field
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = {}, // read-only
            readOnly = true,
            placeholder = { Text(placeholder) },
            leadingIcon = leadingIcon, // <-- apply the optional leading icon here
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFC6C6C6),
                unfocusedBorderColor = Color(0xFFC6C6C6),
                cursorColor = Color(0xFF272935)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.White
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category, color = Color.Black) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputField(
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    placeholderText: String = "Select date",
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Format date as dd/MM/yyyy
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    // Show DatePickerDialog when clicked
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            onDateSelected(dateFormatter.format(calendar.time))
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = selectedDate,
        onValueChange = { }, // read-only
        placeholder = { Text(placeholderText) },
        readOnly = true, // prevent typing
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select Date",
                    tint = Color(0xFF7AC143)
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() }, // open dialog when clicked anywhere
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFC6C6C6),
            unfocusedBorderColor = Color(0xFFC6C6C6),
            cursorColor = Color(0xFF272935)
        ),
        textStyle = LocalTextStyle.current.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    )
}
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF7AC143), // default green
    textColor: Color = Color.White,
    height: Dp = 50.dp,
    cornerRadius: Dp = 12.dp,
    leadingIcon: ImageVector? = null,
    outlined: Boolean = false, // new flag for borders / transparent
    borderColor: Color = Color.Gray // color of the border if outlined
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (outlined) Color.Transparent else backgroundColor
        ),
        shape = RoundedCornerShape(cornerRadius),
        border = if (outlined) BorderStroke(1.dp, borderColor) else null
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = text,
                tint = textColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun AppButtonTwo(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    textColor: Color = Color(0xFF363636),
    height: Dp = 40.dp,
    cornerRadius: Dp = 5.dp,
    outlined: Boolean = false,
    borderColor: Color = Color(0xFFE0E0E0),
    fontWeight: FontWeight = FontWeight.Normal
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .wrapContentWidth()
            .height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor, // background for selected button
            contentColor = textColor
        ),
        shape = RoundedCornerShape(cornerRadius),
        border = if (outlined) BorderStroke(1.dp, borderColor) else null
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun DepositMethodSelector(
    selectedMethod: DepositMethod,
    onMethodSelected: (DepositMethod) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedColor = Color(0xFF7AC143)
    val unselectedColor = Color(0xFF555454)

    Column(modifier = modifier) {

        Text(
            text = "Fund From:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFC6C6C6)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // Account
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onMethodSelected(DepositMethod.ACCOUNT) }
                    .padding(4.dp)
            ) {
                val isSelected = selectedMethod == DepositMethod.ACCOUNT

                RadioButton(
                    selected = isSelected,
                    onClick = { onMethodSelected(DepositMethod.ACCOUNT) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = selectedColor,
                        unselectedColor = unselectedColor
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Account",
                    color = if (isSelected) selectedColor else unselectedColor,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }

            // M-Pesa
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onMethodSelected(DepositMethod.MPESA) }
                    .padding(4.dp)
            ) {
                val isSelected = selectedMethod == DepositMethod.MPESA

                RadioButton(
                    selected = isSelected,
                    onClick = { onMethodSelected(DepositMethod.MPESA) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = selectedColor,
                        unselectedColor = unselectedColor
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "M-Pesa",
                    color = if (isSelected) selectedColor else unselectedColor,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}


