package com.example.mosaic.somguia10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Url;

import java.util.List;


public class CamposEspeciales extends AppCompatActivity implements Validator.ValidationListener {


    @Email(message = "El correo electrónico no es válido")
    EditText email;


    @NotEmpty
    @Min(value = 6, message = "Nota minima para aprobar 6")
    @Max(value = 10, message = "Nota maxima para aprobar 10")
    EditText nota;

    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS,
            message = "El password debe contener mínimo 6 caracteres, símbolos, valores numéricos y letras")
    EditText password;


    @NotEmpty
    @ConfirmPassword(message = "El password no coincide")
    private EditText confirmPasswordEditText;

    @NotEmpty
    @Url//Mostrará el mensaje origin
    EditText url;

    @NotEmpty
    EditText otrosDatos;

    @NotEmpty
    @Min(value = 1, message = "Edad mínima 1")
    @Max(value = 120, message = "Edad máxima 120")
    EditText edad;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campos_especiales);

        validator = new Validator(this);
        validator.setValidationListener(this);

        email = (EditText) findViewById(R.id.emailSari);
        nota = (EditText) findViewById(R.id.notaSari);
        password = (EditText) findViewById(R.id.password);

        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPassword);

        url = (EditText) findViewById(R.id.url);

        otrosDatos = (EditText) findViewById(R.id.otrosDatos);

        edad = (EditText) findViewById(R.id.edad);
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Campos Validados.... Puedes proceder", Toast.LENGTH_SHORT).show();
    }


    //Este método no cambiará casi Nunca
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    //Invocar la validación

    public void validar(View view) {
        validator.validate();
    }
}
