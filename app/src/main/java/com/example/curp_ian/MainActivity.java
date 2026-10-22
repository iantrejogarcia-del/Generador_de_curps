package com.example.curp_ian;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    EditText txtnom,txtapater,txtamater;
    TextView rescurp;
    Button gencurp;
    RadioGroup rdgroupsexo;
    RadioButton rdh,rdm;
    Spinner Spent;
    String sexo;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        rdgroupsexo=findViewById(R.id.rdgsexo);
        rdgroupsexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                sexo= radioButton.getText().toString().substring(0, 1);
            }
        });
        txtnom=findViewById(R.id.txtnombre);
        txtapater=findViewById(R.id.txtapaterno);
        txtamater=findViewById(R.id.txtamaterno);
        rescurp=findViewById(R.id.resulcurp);
        gencurp=findViewById(R.id.btngencurp);
        rdh=findViewById(R.id.rdhom);
        rdm=findViewById(R.id.rdmuj);
         Spent=findViewById(R.id.spent);
        initDatePicker();
        dateButton = findViewById(R.id.btncal);
        dateButton.setText(obtenerfecha());

    }
    public void CURP(View view){


        String nombre=txtnom.getText().toString();
        String Apaterno=txtapater.getText().toString();
        String Amaterno=txtamater.getText().toString();

        if (nombre.isEmpty() || Apaterno.isEmpty() || Amaterno.isEmpty() || sexo == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Te faltan campos por llenar")
                    .setTitle("Advertencia")
                    .setPositiveButton("Aceptar", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{

        char primLApaterno = Apaterno.charAt(0);
        char primVocal = PrimeraVocal(Apaterno.substring(1));
        char primLAmaterno = Amaterno.charAt(0);
        char primLnombre = nombre.charAt(0);
        char apconso =Primeraconsonante(Apaterno.substring(1));
        char amconso =Primeraconsonante(Amaterno.substring(1));
        char Nconso =Primeraconsonante(nombre.substring(1));
        String ano="";
        String mes="";
        String dia="";
        ano = (String) dateButton.getText().subSequence(9,11);
        mes=(String) dateButton.getText().subSequence(0,4);
        mes =extraermes (mes);
        dia = (String) dateButton.getText().subSequence(4,6);

        String Edo="";
        Edo=entidad();


        String resulcurp = String.valueOf(primLApaterno) +
                primVocal+
                primLAmaterno +
                primLnombre+ano+mes+dia+sexo+Edo+apconso+amconso+Nconso+"00";/*Me falto el mes no sale */
        rescurp.setText(resulcurp);
        }
    }
    private char PrimeraVocal(String texto) {
        char[] vocales = {'A', 'E', 'I', 'O', 'U'};
        for (char v : texto.toUpperCase().toCharArray()) {
            for (char vocal : vocales) {
                if (v == vocal) {
                    return v;
                }
            }
        }
        return '-';
    }
    private char Primeraconsonante(String texto) {
        char[] consonantes = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'};
        for (char v : texto.toUpperCase().toCharArray()) {
            for (char consonante : consonantes) {
                if (v == consonante) {
                    return v;
                }
            }
        }
        return '-';
    }

    public String obtenerfecha()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return fechaCadena(day, month, year);
    }

    public void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = fechaCadena(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    public String fechaCadena(int day, int month, int year) {
        String formatodedia = (day < 10) ? "0" + day : String.valueOf(day);
        return formatomes(month) + " " + formatodedia + " " + year;
    }

    public String formatomes(int month)
    {
        if(month == 1)
            return "ENE";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "ABR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AGO";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DIC";

        return "JAN";
    }
    public void vercalendario(View view)
    {
        datePickerDialog.show();
    }

    public String extraermes(String mes)
    {
        String val="";
        if(mes.equals("ENE"))
            val= "01";
        else if(mes.equals("FEB"))
            val= "02";
        else if(mes.equals("MAR"))
            val= "03";
        else if(mes.equals("ABR"))
            val= "04";
        else if(mes.equals("MAY"))
            val= "05";
        else if(mes.equals("JUN"))
            val= "06";
        else if(mes.equals("JUL"))
            val= "07";
        else if(mes.equals("AGO"))
            val= "08";
        else if(mes.equals("SEP"))
            val= "09";
        else if(mes.equals("OCT"))
            val= "10";
        else if(mes.equals("NOV"))
            val= "11";
        else if(mes.equals("DIC"))
            val= "12";
        return val;
    }
    public String entidad(){
        String selec=Spent.getSelectedItem().toString();
        String valorr="";
            switch (selec){
                case "AGUASCALIENTES":
                    valorr="AS";
                    break;
                case "BAJA CALIFORNIA":
                    valorr="BC";
                    break;
                case "BAJA CALIFORNIA SUR":
                    valorr = "BS";
                    break;
                case "CAMPECHE":
                    valorr = "CC";
                    break;
                case "COAHUILA":
                    valorr = "CL";
                    break;
                case "COLIMA":
                    valorr = "CM";
                    break;
                case "CHIAPAS":
                    valorr = "CS";
                    break;
                case "CHIHUAHUA":
                    valorr = "CH";
                    break;
                case "DISTRITO FEDERAL":
                    valorr = "DF";
                    break;
                case "DURANGO":
                    valorr = "DG";
                    break;
                case "GUANAJUATO":
                    valorr = "GT";
                    break;
                case "GUERRERO":
                    valorr = "GR";
                    break;
                case "HIDALGO":
                    valorr = "HG";
                    break;
                case "JALISCO":
                    valorr = "JC";
                    break;
                case "MÉXICO":
                    valorr = "MC";
                    break;
                case "MICHOACÁN":
                    valorr = "MN";
                    break;
                case "MORELOS":
                    valorr = "MS";
                    break;
                case "NAYARIT":
                    valorr = "NT";
                    break;
                case "NUEVO LEÓN":
                    valorr = "NL";
                    break;
                case "OAXACA":
                    valorr = "OC";
                    break;
                case "PUEBLA":
                    valorr = "PL";
                    break;
                case "QUERÉTARO":
                    valorr = "QT";
                    break;
                case "QUINTANA ROO":
                    valorr = "QR";
                    break;
                case "SAN LUIS POTOSÍ":
                    valorr = "SP";
                    break;
                case "SINALOA":
                    valorr = "SL";
                    break;
                case "SONORA":
                    valorr = "SR";
                    break;
                case "TABASCO":
                    valorr = "TC";
                    break;
                case "TAMAULIPAS":
                    valorr = "TS";
                    break;
                case "TLAXCALA":
                    valorr = "TL";
                    break;
                case "VERACRUZ":
                    valorr = "VZ";
                    break;
                case "YUCATÁN":
                    valorr = "YN";
                    break;
                case "ZACATECAS":
                    valorr = "ZS";
                    break;
                case "NACIDO EN EL EXTRANJERO":
                    valorr = "NE";
                    break;
            }
        return valorr;
    }

}
