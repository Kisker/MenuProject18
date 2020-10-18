package net.learn2develop.menuproject18;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<String> drawerItems;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        setupToolbar();
        setupDrawer();
    }
    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }
    //Ovo nam pokazuje nazive lista data Drawera, kada kliknemo na sandwich
    private void fillData(){
        drawerItems = new ArrayList<>();
        drawerItems.add("Kategorije");
        drawerItems.add("Prikaz jela");
        drawerItems.add("Podesavanje App-a");
        drawerItems.add("About");
    }
    //Ovo nam pokazuje Drawer tittle listu i uvek setup ide na ovakav nacin. Kada kliknemo na jednu specifikaciju, on nam otvori novi list
    //Pozive za R.id moramo da uradimo u main_activity.xml
    private void setupDrawer(){
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerItems));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Kada kliknemo na sam naziv sadrzaja, sledeca metoda ce nam dozvoliti ili nece da udjemo u novi fragment.
            //U slucaju da, na primer kliknemo Toast, a pritom nismo definisali tu metodu, na ekranu ce nam se prikazati
            //Unknown. Zato u ovom slucaju koristimo switch/case metodu, odmah u okviru setupDrawer metode!
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = "Unknown";
                switch (i) {
                    case 0:
                        title = "Kategorije";
                        showKategoriju();
                        break;
                    case 1:
                        title = "Prikaz jela";
                        showJelo();
                        break;
                    case 2:
                        title = "Podesavanja";
                        showPrefs();
                        break;
                    case 3:
                        title = "About";
                        showAbout();
                        break;
                    default:
                        break;
                }
                //Kada nadjemo i kliknemo na odredjeni sadrzaj, fijoka i sandwich se zatvaraju
                setTitle(title);
                drawerLayout.closeDrawer(drawerList);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
    }

    private void showKategoriju(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ProbaFragment fragmentPrimer = new ProbaFragment();
        fragmentPrimer.setContent("Dobrodosli na kategoriju menija. Molimo vas da na meniju pritisnete: " + "\nPrikaz Jela " + "\nPodesavanja " + "\nAbout");
        transaction.replace(R.id.root, fragmentPrimer);
        transaction.commit();
    }

    private void showJelo(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        JeloFragment fragmentPrimer1 = new JeloFragment();
        fragmentPrimer1.setContent("1. Gulas " + "\n" + "Domaci gulas sa Zlatibora" + "\n" + "2. Govedja supa " + "\n" + "Domaca supa sa Zlatibora");
        transaction.replace(R.id.root, fragmentPrimer1);
        transaction.commit();
    }

    private void showPrefs(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PrefsFragment fragment = new PrefsFragment();
        transaction.replace(R.id.root, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showAbout(){
        Toast.makeText(MainActivity.this, "Hurricane " + "\n" + "by Sveto", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Toast.makeText(this, "Akcija dodavanja.", Toast.LENGTH_SHORT).show();
                //showPrefs();
                break;
            case R.id.action_update:
                Toast.makeText(this, "Akcija izmene.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete:
                Toast.makeText(this, "Akcija brisanja.", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //Ova poslednja metoda ide zajedno za Izvrsavanje akcija. Bez ove metode, nase akcije ne bi bile vidljive!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}