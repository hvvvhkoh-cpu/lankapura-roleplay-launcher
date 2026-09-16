package com.lankapura.roleplay;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER = "51.79.254.10:7655";
    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + .5f); }

    private TextView tv(String text, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(text); t.setTextSize(size); t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private LinearLayout box(int drawable) {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(dp(16), dp(14), dp(16), dp(14));
        l.setBackgroundResource(drawable);
        return l;
    }

    private Button actionButton(String text, int bg) {
        Button b = new Button(this);
        b.setText(text); b.setTextSize(17); b.setTextColor(Color.WHITE);
        b.setAllCaps(false); b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setGravity(Gravity.CENTER);
        b.setBackgroundResource(bg);
        return b;
    }

    private TextView tile(String icon, String title, String sub) {
        TextView t = tv(icon + "  " + title + "\n" + sub, 13, Color.WHITE, true);
        t.setPadding(dp(8), dp(8), dp(8), dp(8));
        t.setBackgroundResource(com.lankapura.roleplay.R.drawable.bg_tile);
        return t;
    }

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.rgb(7,14,24));
        getWindow().setNavigationBarColor(Color.rgb(7,14,24));

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.rgb(7,14,24));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(12), dp(22), dp(18));
        scroll.addView(root);

        TextView brand = tv("LANKAPURA ROLEPLAY", 29, Color.WHITE, false);
        brand.setGravity(Gravity.CENTER);
        root.addView(brand, new LinearLayout.LayoutParams(-1, dp(48)));

        TextView sub = tv("SA-MP ROLEPLAY LAUNCHER", 15, Color.rgb(156,176,199), false);
        sub.setGravity(Gravity.CENTER);
        root.addView(sub, new LinearLayout.LayoutParams(-1, dp(28)));

        Space gap1 = new Space(this); root.addView(gap1, new LinearLayout.LayoutParams(1, dp(10)));

        LinearLayout server = box(R.drawable.bg_panel);
        TextView st = tv("SERVER", 20, Color.WHITE, false); st.setGravity(Gravity.CENTER);
        server.addView(st, new LinearLayout.LayoutParams(-1, dp(32)));
        TextView ip = tv(SERVER, 25, Color.WHITE, false); ip.setGravity(Gravity.CENTER);
        server.addView(ip, new LinearLayout.LayoutParams(-1, dp(40)));

        TextView status = tv("●  SERVER ONLINE", 15, Color.rgb(22,229,139), true);
        status.setGravity(Gravity.CENTER);
        server.addView(status, new LinearLayout.LayoutParams(-1, dp(28)));
        root.addView(server, new LinearLayout.LayoutParams(-1, dp(150)));

        Space gap2 = new Space(this); root.addView(gap2, new LinearLayout.LayoutParams(1, dp(18)));

        Button play = actionButton("▶   PLAY NOW", R.drawable.bg_button);
        play.setOnClickListener(v -> copyServer());
        root.addView(play, new LinearLayout.LayoutParams(-1, dp(62)));

        Space gap3 = new Space(this); root.addView(gap3, new LinearLayout.LayoutParams(1, dp(12)));

        Button copy = actionButton("▣   COPY SERVER IP", R.drawable.bg_outline_button);
        copy.setTextColor(Color.WHITE); copy.setOnClickListener(v -> copyServer());
        root.addView(copy, new LinearLayout.LayoutParams(-1, dp(58)));

        TextView info = tv("Tap PLAY NOW to copy the server address.\nA compatible Android SA-MP client is required to actually join the server.", 13, Color.rgb(156,176,199), false);
        info.setGravity(Gravity.CENTER); info.setPadding(0, dp(16), 0, dp(16));
        root.addView(info, new LinearLayout.LayoutParams(-1, dp(76)));

        TextView community = tv("────────  COMMUNITY  ────────", 17, Color.WHITE, true);
        community.setGravity(Gravity.CENTER); root.addView(community, new LinearLayout.LayoutParams(-1, dp(42)));

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(2); grid.setRowCount(2);
        String[][] data = {{"♥","GET HELP","Need assistance?"},{"◉","WHATSAPP","Join our community"},{"!","REPORT PLAYER","Report rule breakers"},{"↓","RESUME DOWNLOAD","Continue your download"}};
        for (String[] x : data) {
            TextView t = tile(x[0], x[1], x[2]);
            GridLayout.LayoutParams gp = new GridLayout.LayoutParams();
            gp.width = 0; gp.height = dp(82); gp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            gp.setMargins(dp(5), dp(5), dp(5), dp(5));
            grid.addView(t, gp);
        }
        root.addView(grid, new LinearLayout.LayoutParams(-1, dp(184)));

        Space gap4 = new Space(this); root.addView(gap4, new LinearLayout.LayoutParams(1, dp(10)));

        LinearLayout dl = box(R.drawable.bg_panel);
        TextView d1 = tv("Downloading:  SA-MP DATA", 14, Color.rgb(156,176,199), true);
        dl.addView(d1, new LinearLayout.LayoutParams(-1, dp(30)));
        TextView d2 = tv("Ready to download game files", 13, Color.WHITE, false);
        dl.addView(d2, new LinearLayout.LayoutParams(-1, dp(26)));
        ProgressBar p = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        p.setMax(100); p.setProgress(0); p.setProgressDrawable(getResources().getDrawable(R.drawable.progress_fill));
        dl.addView(p, new LinearLayout.LayoutParams(-1, dp(10)));
        TextView d3 = tv("Total Progress: 0%", 13, Color.rgb(18,168,255), true);
        d3.setGravity(Gravity.CENTER);
        dl.addView(d3, new LinearLayout.LayoutParams(-1, dp(32)));
        root.addView(dl, new LinearLayout.LayoutParams(-1, dp(112)));

        Space bottom = new Space(this); root.addView(bottom, new LinearLayout.LayoutParams(1, dp(18)));
        TextView nav = tv("⌂  Home                         ▶  Play                         ⚙  Settings", 14, Color.rgb(156,176,199), true);
        nav.setGravity(Gravity.CENTER); nav.setPadding(0, dp(12), 0, dp(12));
        root.addView(nav, new LinearLayout.LayoutParams(-1, dp(54)));

        setContentView(scroll);
    }

    private void copyServer() {
        ClipboardManager cm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("LANKAPURA SERVER", SERVER));
        Toast.makeText(this, "Server IP copied: " + SERVER, Toast.LENGTH_SHORT).show();
    }
}
