package com.example.project1a;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class GameActivity extends AppCompatActivity{

    TextView tv_hints;
    TextView tv_timer;
    TextView tv_result;
    TextView tv_roundnumber;
    StopWatch sw;
    String hint = "";
    String word = "";
    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    int roundNumber = 0;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tv_hints = (TextView) findViewById(R.id.hints);
        tv_timer = (TextView)findViewById(R.id.timer_view);
        tv_result = (TextView)findViewById(R.id.tv_result);
        tv_roundnumber = (TextView)findViewById(R.id.tv_roundnum);
        sw = new StopWatch(120000,1000, tv_timer);

        et1 = (EditText)findViewById(R.id.char1);
        et2 = (EditText)findViewById(R.id.char2);
        et3 = (EditText)findViewById(R.id.char3);
        et4 = (EditText)findViewById(R.id.char4);
        et5 = (EditText)findViewById(R.id.char5);
        et6 = (EditText)findViewById(R.id.char6);
        et7 = (EditText)findViewById(R.id.char7);
        et8 = (EditText)findViewById(R.id.char8);

    }

    public void btnStartGame(View view){
        //get definition
        if(view.getId() == R.id.btn_gethint) {
            //Place hint in textView
            processData();

            sw.start();
            roundNumber++;
            tv_roundnumber.setText("Round: " + roundNumber);
        }

    }

    public void btnStopGame(View view){
        String answer = assembleWord();

        if(view.getId() == R.id.btn_submit) {
            sw.cancel();
            if(word.equalsIgnoreCase(answer))
            tv_result.setText(word + " Yes!");
            else
                tv_result.setText(R.string.incorrect_answer);
        }
    }

//    Assemble Word
    private String assembleWord(){
        StringBuilder sb = new StringBuilder();
        String result;
//       Convert input to strings
        String LetterA = et1.getText().toString();
        String LetterB = et2.getText().toString();
        String LetterC = et3.getText().toString();
        String LetterD = et4.getText().toString();
        String LetterE = et5.getText().toString();
        String LetterF = et6.getText().toString();
        String LetterG = et7.getText().toString();
        String LetterH = et8.getText().toString();
//      Combine letters into a word use a for loop here
        sb.append(LetterA);
        sb.append(LetterB);
        sb.append(LetterC);
        sb.append(LetterD);
        sb.append(LetterE);
        sb.append(LetterF);
        sb.append(LetterG);
        sb.append(LetterH);

//        remove whitespace
        int j = 0;
        for(int i = 0; i < sb.length(); i++){
            if(!Character.isWhitespace(sb.charAt(i))){
                sb.setCharAt(j++, sb.charAt(i));
            }
        }
        sb.delete(j,sb.length());
//      convert sb to a string and return it to caller
        result = sb.toString();
        return result;


    }
    public void btnResetChars(View view){
        if(view.getId() == R.id.btn_reset){
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            et5.setText("");
            et6.setText("");
            et7.setText("");
            et8.setText("");

            tv_result.setText("");

        }

    }
    //this method will handle the xml parsing
//    Adding xml data to arrayList()
    private void processData(){
        AssetManager assetManager = getBaseContext().getAssets();
        try{
            InputStream is = assetManager.open("musicdict.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            SaxWordHandler myWordHandler = new SaxWordHandler();
            xr.setContentHandler(myWordHandler);
            InputSource inputSource = new InputSource(is);
            xr.parse(inputSource);

            //Get dta as list
            List<MusicDictionary> data = myWordHandler.readDataFromXML();
              Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;
            for ( MusicDictionary mu : data ) {
                if (randomNumber == mu.getId()) {
                    hint = mu.getDef();
                    word = mu.getWord();
                }
            }
            tv_hints.setText(hint);

        }catch (Exception e){
            e.printStackTrace();
        }

    }//end processXML

}
