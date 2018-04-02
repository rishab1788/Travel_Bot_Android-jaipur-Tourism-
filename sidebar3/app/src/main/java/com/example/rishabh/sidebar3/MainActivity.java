package com.example.rishabh.sidebar3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
 import java.util.Locale;
 import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,AIListener, TextToSpeech.OnInitListener {
    AIService aiService;
    TextView t ;

    AudioManager am;
    TextToSpeech tts;
    private int MY_DATA_CHECK_CODE = 0;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("TOURISM");
        setSupportActionBar(toolbar);


t=(TextView) findViewById(R.id.botapi);
        tts = new TextToSpeech(this, this);
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        //

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        int permission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            makeRequest();
        }
        final AIConfiguration config = new AIConfiguration("1dc74448f3f647da9f7ac8907348d834",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiService = AIService.getService(this, config);
        aiService.setListener(this);










        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    displaySelectedScreen(R.id.nav_menu1);

    }


    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private  void displaySelectedScreen(int id)
    {
        android.support.v4.app.Fragment fragment=null;

        switch (id )
        {
            case R.id.nav_menu1:

                menu1 cameraFragmenta=new menu1();
                FragmentManager manager=getSupportFragmentManager(  );

                manager.beginTransaction().replace(R.id.content_main,cameraFragmenta).commit();
                break;

            case R.id.nav_menu2:
                menu2 me2=new menu2();
                FragmentManager m2=getSupportFragmentManager(  );

                m2.beginTransaction().replace(R.id.content_main,me2).commit();
                break;
            case R.id.nav_menu3:

                menu3 me3=new menu3();
                FragmentManager m3=getSupportFragmentManager();

                m3.beginTransaction().replace(R.id.content_main,me3).commit();
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
displaySelectedScreen(id);
        return true;

    }

    @Override
    public void onResult(AIResponse result) {
        Result result1 = result.getResult();

        t.setText(result1.getResolvedQuery()+ "\n" + result1.getFulfillment().getSpeech());
        tts.speak(result1.getFulfillment().getSpeech(), TextToSpeech.QUEUE_FLUSH, null);

       if (result1.getResolvedQuery().toLowerCase().equals("hawa mahal review"))
       {
           Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g304555-d317345-Reviews-Hawa_Mahal_Palace_of_Wind-Jaipur_Jaipur_District_Rajasthan.html"));
           startActivity(intent);
       }
       if(result1.getResolvedQuery().toLowerCase().equals("city palace review"))
       {
           Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g304555-d324468-Reviews-City_Palace_of_Jaipur-Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
        }
        if (result1.getResolvedQuery().toLowerCase().equals("amer fort review"))
        {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g1096234-d319875-Reviews-Amber_Fort-Amer_Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
        }

        if(result1.getResolvedQuery().toLowerCase().equals("birla mandir review"))
        {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g304555-d320081-Reviews-Birla_Mandir_Temple-Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
         }

        if(result1.getResolvedQuery().toLowerCase().equals("ram ghar jheel review"))
        {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g304555-d4421781-Reviews-Ramgarh_Lake-Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
        }
        if(result1.getResolvedQuery().toLowerCase().equals("jal mahal review"))
        {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g304555-d324664-Reviews-Jal_Mahal-Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
        }

        if(result1.getResolvedQuery().toLowerCase().equals("Dolls museum review"))
        {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g304555-d13093467-Reviews-Dolls_Museum-Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
        }
        if(result1.getResolvedQuery().toLowerCase().equals("sheesh mahal review"))
        {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/ShowUserReviews-g304555-d7963801-r272704119-Sheesh_Mahal-Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
        }
        if(result1.getResolvedQuery().toLowerCase().equals("albert hall museum review") )
        {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/ShowUserReviews-g304555-d7963801-r272704119-Sheesh_Mahal-Jaipur_Jaipur_District_Rajasthan.html"));
            startActivity(intent);
        }


     }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    @Override
    public void onInit(int  initStatus) {
        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {

            if (tts.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
            {  // tts.setLanguage(Locale.US);
                tts.setLanguage( new Locale( "hin", "IND", "variant" ) );            }

        } else if (initStatus == TextToSpeech.ERROR) {
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
            } else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                } else {
                }
                return;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();  };

     public void buttonCliked(final View view) {
        aiService.startListening();


    }









}
