package com.lankapura.roleplay;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER = "51.79.254.10:7655";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(28, 60, 28, 28);
        root.setBackgroundColor(Color.rgb(7,17,31));

        TextView title = text("LANKAPURA ROLEPLAY", 30, Color.WHITE);
        title.setGravity(Gravity.CENTER);
        root.addView(title, lp());

        TextView subtitle = text("SA-MP ROLEPLAY LAUNCHER", 13, Color.rgb(155,176,199));
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle, lp());

        TextView server = text("SERVER\n" + SERVER, 18, Color.WHITE);
        server.setGravity(Gravity.CENTER);
        server.setPadding(20, 35, 20, 35);
        server.setBackgroundColor(Color.rgb(13,30,51));
        root.addView(server, lp());

        Button play = new Button(this);
        play.setText("PLAY NOW");
        play.setTextSize(18);
        play.setTextColor(Color.WHITE);
        play.setBackgroundColor(Color.rgb(22,135,255));
        play.setOnClickListener(v -> copyServer());
        LinearLayout.LayoutParams bp = lp();
        bp.setMargins(0, 35, 0, 15);
        root.addView(play, bp);

        Button copy = new Button(this);
        copy.setText("COPY SERVER IP");
        copy.setOnClickListener(v -> copyServer());
        root.addView(copy, lp());

        TextView note = text(
            "Tap PLAY NOW to copy the server address.\n" +
            "A compatible Android SA-MP client is required to actually join the server.",
            13, Color.rgb(155,176,199));
        note.setGravity(Gravity.CENTER);
        note.setPadding(10, 30, 10, 10);
        root.addView(note, lp());

        setContentView(root);
    }

    private void copyServer() {
        ClipboardManager cm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("LANKAPURA ROLEPLAY", SERVER));
        Toast.makeText(this, "Server IP copied: " + SERVER, Toast.LENGTH_SHORT).show();
    }

    private TextView text(String s, float size, int color) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        return t;
    }

    private LinearLayout.LayoutParams lp() {
        return new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
