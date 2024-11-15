package org.acgproject.gerencimentodeestoque.utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Restricoes {

    public static void setTextFieldInteger(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldString(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            // Verifica se o novo valor contém apenas letras (A-Z e a-z)
            if (newValue != null && !newValue.matches("[a-zA-Z]*")) {
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldMaxLength(TextField txt, int max) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue.length() > max) {
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldDouble(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
                txt.setText(oldValue);
            }
        });
    }

    public static Boolean validarEmail(String email) {
        String padraoEmail = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(padraoEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //valida se a data digitada se encontra no formato dd/MM/yyyy
    public static void setDatePickerValidation(DatePicker datePicker) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String text) {
                try {
                    return text != null && !text.isEmpty() ? LocalDate.parse(text, dateFormatter) : null;
                } catch (DateTimeParseException e) {
                    return null;
                }
            }
        });


        TextField editor = datePicker.getEditor();


        editor.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,2}/?\\d{0,2}/?\\d{0,4}")) {
                editor.setText(oldValue);
            }
        });


        datePicker.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                try {
                    LocalDate.parse(editor.getText(), dateFormatter);
                } catch (DateTimeParseException e) {
                    editor.setText("");
                }
            }
        });
    }
}
