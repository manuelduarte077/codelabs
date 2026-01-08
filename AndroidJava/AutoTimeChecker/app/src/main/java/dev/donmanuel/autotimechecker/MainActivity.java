package dev.donmanuel.autotimechecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private TextView tvTimezoneStatus;
    private Button btnOpenSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvTimezoneStatus = findViewById(R.id.tvTimezoneStatus);
        btnOpenSettings = findViewById(R.id.btnOpenSettings);

        // 1) Verificamos al iniciar
        updateUi();

        // 2) Botón para abrir Ajustes → Fecha y hora
        btnOpenSettings.setOnClickListener(v -> openDateSettings());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 3) Al volver desde Ajustes, re-verificamos
        updateUi();
    }

    private void updateUi() {
        boolean autoTime = isAutoTimeEnabled();
        boolean autoTz = isAutoTimezoneEnabled();

        tvStatus.setText(autoTime
                ? "✅ Hora automática activada"
                : "❌ Hora automática desactivada");

        tvTimezoneStatus.setText(autoTz
                ? "🕓 Zona horaria automática activada"
                : "🌐 Zona horaria automática desactivada");

        // Muestra el botón solo si algo está desactivado
        btnOpenSettings.setVisibility((autoTime && autoTz) ? View.GONE : View.VISIBLE);

        // (Opcional) Si quieres un diálogo cuando esté desactivado:
        if (!autoTime || !autoTz) {
            showEnableDialog(!autoTime, !autoTz);
        }
    }

    private boolean isAutoTimeEnabled() {
        try {
            int v = Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME);
            return v == 1;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
    }

    private boolean isAutoTimezoneEnabled() {
        try {
            int v = Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME_ZONE);
            return v == 1;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
    }

    private void openDateSettings() {
        try {
            // Pantalla específica de Fecha y hora
            Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Fallback genérico por si algún OEM (raro) no expone esa activity
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }
    }

    // Opcional: Diálogo amable para guiar al usuario
    private void showEnableDialog(boolean needAutoTime, boolean needAutoTz) {
        StringBuilder msg = new StringBuilder("Para continuar, activa en Ajustes:\n");
        if (needAutoTime) msg.append("• Fecha y hora automáticas\n");
        if (needAutoTz) msg.append("• Zona horaria automática\n");

        new AlertDialog.Builder(this)
                .setTitle("Configuración requerida")
                .setMessage(msg.toString())
                .setPositiveButton("Abrir Ajustes", (d, w) -> openDateSettings())
                .setNegativeButton("Ahora no", null)
                .show();
    }
}