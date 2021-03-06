package com.example.mosaic.somguia10;

        import android.content.Intent;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText nombres, apellidos, carnet, dui,telefono,nit, email,nota;

    Button verificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        carnet = (EditText) findViewById(R.id.carnet);
        dui = (EditText) findViewById(R.id.dui);
        nit = (EditText) findViewById(R.id.nit);
        telefono= (EditText) findViewById(R.id.telefono);
        verificar= (Button) findViewById(R.id.verificar);
        email = (EditText) findViewById(R.id.email);
        nota = (EditText) findViewById(R.id.nota);


        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(

                        validateNameField(nombres) &&
                        validateNameField(apellidos) &&
                        validateCarnet(carnet) &&
                        validateDui(dui) &&
                        validateNIT(nit) &&
                        validateTelefono(telefono) &&
                        validateEmail(email) &&
                        validateNota(nota)


                    ){
                    Toast.makeText(MainActivity.this,"Sus campos han sido validados",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateCarnet(EditText editText){
        String regexString = "^[A-Za-z]{2}[0-9]{6}$";

        if(editText.getText().toString().matches(regexString)){
            okField(editText);
        }else {
            errorField(editText);
            editText.setError("Carnet Incorrecto");
            return false;
        }

        return true;
    }

    private boolean validateDui(EditText editText){
        //03532126-1
        String regexString = "^\\d{8}-\\d{1}$";
        if(editText.getText().length()!=0) {
            if(editText.getText().toString().matches(regexString)){
                okField(editText);
            }else {
                errorField(editText);
                editText.setError("DUI Incorrecto. ");
                return false;
            }
        }
        else
        {
            errorField(editText);
            editText.setError("Campo obligatorio");
            return false;

        }
        return true;
    }
    private boolean validateTelefono(EditText editText){
        //03532126-1
        String regexString = "^\\d{4}-\\d{4}$";
        if(editText.getText().length()!=0) {
            if(editText.getText().toString().matches(regexString)){
                okField(editText);
            }else {
                errorField(editText);
                editText.setError("telefono Incorrecto.");
                return false;
            }
        }
        else
        {
            errorField(editText);
            editText.setError("Campo vacio.");
            return false;
        }
        return true;
    }
    private boolean validateNIT(EditText editText){
        //03532126-1
        String regexString = "^\\d{4}-\\d{6}-\\d{3}-\\d{1}$";

        if(editText.getText().length()!=0) {
            if(editText.getText().toString().matches(regexString)){
                okField(editText);
            }else {
                errorField(editText);
                editText.setError("NIT Incorrecto. .");
                return false;
            }
        }
        else
        {
            errorField(editText);
            editText.setError("Campo vacio.");
            return false;

        }



        return true;
    }


    private boolean validateNameField(EditText editText){
        String regexString = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
        Pattern r = Pattern.compile(regexString);
        Matcher m = r.matcher(editText.getText());
        if(m.matches()){
            okField(editText);

        }else{
            errorField(editText);
            editText.setError("Caracteres no válidos");
            return false;
        }
        return true;
    }

    private boolean validateEmail(EditText editText){

        String regexString = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
        Pattern r = Pattern.compile(regexString);
        Matcher m = r.matcher(editText.getText());
        if(m.matches()){
            okField(editText);

        }else{
            errorField(editText);
            editText.setError("Correo invalido");
            return false;
        }
        return true;
    }
    private boolean validateNota(EditText editText){


        if(editText.getText().length()!=0) {

            int nota =  Integer.parseInt(editText.getText().toString());
            if(nota >5 && nota < 11)
            {

                okField(editText);

            }
            else
            {
                errorField(editText);
                editText.setError("Reprobado o nota invalida");
                return false;

            }
        }else
        {
            errorField(editText);
            editText.setError("Campo vacio ");
            return false;

        }

        return true;
    }



    void errorField(EditText editText){
        editText.setBackgroundColor(Color.rgb(255,235,238));
    }

    void okField(EditText editText){
        editText.setBackgroundColor(Color.rgb(224,242,241));
    }

    public void abrirFomulario(View view) {

        Intent i = new Intent(this,CamposEspeciales.class);
        startActivity(i);

    }
}

