package usonsonate.com.juego_xo;

import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CheckBox> listaControles;
    private Boolean CurrentValue = false;
    private RadioButton rdbtnValorX, rdbtnValorO;
    private String Mensaje = "El ganador es:";
    private Button btnIniciar, btnRestart;
    private Boolean IsPlaying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaControles = new ArrayList<>();
        listaControles.add( (CheckBox) findViewById(R.id.chk1));
        listaControles.add( (CheckBox) findViewById(R.id.chk2));
        listaControles.add( (CheckBox) findViewById(R.id.chk3));
        listaControles.add( (CheckBox) findViewById(R.id.chk4));
        listaControles.add( (CheckBox) findViewById(R.id.chk5));
        listaControles.add( (CheckBox) findViewById(R.id.chk6));
        listaControles.add( (CheckBox) findViewById(R.id.chk7));
        listaControles.add( (CheckBox) findViewById(R.id.chk8));
        listaControles.add( (CheckBox) findViewById(R.id.chk9));
        rdbtnValorO = findViewById(R.id.rdbtnO);
        rdbtnValorX = findViewById(R.id.rdbtnX);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnRestart = findViewById(R.id.btnRestart);

        btnRestart.setVisibility(View.INVISIBLE);
        InitCheckboxes();
        DeshabilitarCheckBoxes();
        IsPlaying = false;

    }

    private void ValidarGanador(){
        Boolean Ganador = false;

        if ((listaControles.get(0).getText() == listaControles.get(1).getText()) &&(listaControles.get(1).getText() == listaControles.get(2).getText())){
            if (listaControles.get(0).getText().equals("X") || listaControles.get(0).getText().equals("O") ){
                Ganador = true;
            }

        }

        if ((listaControles.get(3).getText() == listaControles.get(4).getText()) &&(listaControles.get(4).getText() == listaControles.get(5).getText())) {
            if (listaControles.get(3).getText().equals("X") || listaControles.get(3).getText().equals("O") ){
                Ganador = true;
            }

        }

        if ((listaControles.get(6).getText() == listaControles.get(7).getText()) &&(listaControles.get(7).getText() == listaControles.get(8).getText())){
            if (listaControles.get(6).getText().equals("X") || listaControles.get(6).getText().equals("O") ){
                Ganador = true;
            }

        }

        if ((listaControles.get(1).getText() == listaControles.get(4).getText()) &&(listaControles.get(4).getText() == listaControles.get(7).getText())){
            if (listaControles.get(1).getText().equals("X") || listaControles.get(1).getText().equals("O") ){
                Ganador = true;
            }
        }

        if ((listaControles.get(2).getText() == listaControles.get(5).getText()) &&(listaControles.get(5).getText() == listaControles.get(8).getText())){
            if (listaControles.get(2).getText().equals("X") || listaControles.get(2).getText().equals("O") ){
                Ganador = true;
            }
        }

        if ((listaControles.get(0).getText() == listaControles.get(4).getText()) &&(listaControles.get(4).getText() == listaControles.get(8).getText())){
            if (listaControles.get(0).getText().equals("X") || listaControles.get(0).getText().equals("O") ){
                Ganador = true;
            }
        }

        if ((listaControles.get(2).getText() == listaControles.get(4).getText()) &&(listaControles.get(4).getText() == listaControles.get(6).getText())){
            if (listaControles.get(2).getText().equals("X") || listaControles.get(2).getText().equals("O") ){
                Ganador = true;
            }
        }

        if ((listaControles.get(0).getText() == listaControles.get(3).getText()) &&(listaControles.get(3).getText() == listaControles.get(6).getText())){
            if (listaControles.get(3).getText().equals("X") || listaControles.get(3).getText().equals("O") ){
                Ganador = true;
            }
        }

        if (Ganador){
            if (CurrentValue){
                Mensaje += " X";
            }else{
                Mensaje += " O";
            }
            IsPlaying = false;
            Toast.makeText(this, Mensaje, Toast.LENGTH_LONG ).show();
            Reiniciar();
        }

        if(Empate() == 9){
            IsPlaying = false;
            Toast.makeText(this, "No hay ganador, se declara empate.", Toast.LENGTH_LONG ).show();
            Reiniciar();
        }
    }

    private Integer Empate(){
        Integer i = 0;

        for (final CheckBox chk:listaControles){
            if (chk.isChecked()){
                i++;
            }
        }

        return i;
    }

    private void Reiniciar(){
        DeshabilitarCheckBoxes();
        Mensaje = "El ganador es:";

    }

    public void OnClickIniciar(View v) {
        if (rdbtnValorX.isChecked() == false && rdbtnValorO.isChecked() == false){
           Toast.makeText(this, "Necesita seleccionar una opción para iniciar.", Toast.LENGTH_LONG).show();
        }else{
            IsPlaying = true;
            HabilitarCheckBoxes();
            for (final CheckBox chk:listaControles){
                chk.setText("");
            }
        }

    }

    public void OnClickRestart(View v){
        IsPlaying = false;
        Reiniciar();
    }

    private void InitCheckboxes(){

        for (final CheckBox chk:listaControles){
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (IsPlaying){
                        if(CurrentValue){
                            chk.setText("X");
                        }else if(!CurrentValue){
                            chk.setText("O");
                        }
                        ValidarGanador();
                        CurrentValue = !CurrentValue;
                    }
                }
            });
        }
    }

    private void DeshabilitarCheckBoxes(){
        for (final CheckBox chk:listaControles){
            chk.setEnabled(false);
            chk.setChecked(false);
        }
        rdbtnValorO.setEnabled(true);
        rdbtnValorX.setEnabled(true);
        btnRestart.setVisibility(View.INVISIBLE);
        btnIniciar.setVisibility(View.VISIBLE);
    }

    private void HabilitarCheckBoxes(){
        for (final CheckBox chk:listaControles){
            chk.setEnabled(true);
        }
        rdbtnValorO.setEnabled(false);
        rdbtnValorX.setEnabled(false);
        btnRestart.setVisibility(View.VISIBLE);
        btnIniciar.setVisibility(View.INVISIBLE);
        if (rdbtnValorX.isChecked()){
            CurrentValue = true;
        }else{
            CurrentValue = false;
        }
    }


}
