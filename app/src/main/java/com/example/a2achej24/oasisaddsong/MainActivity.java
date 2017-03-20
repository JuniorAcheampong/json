package com.example.a2achej24.oasisaddsong;

import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.os.AsyncTask;
        import java.io.IOException;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.io.InputStream;
        import java.io.BufferedReader;
        import java.io.InputStreamReader;

public class MainActivity extends Activity implements View.OnClickListener{

    class MyTask extends AsyncTask<Void,Void,String>
    {
        public String doInBackground(Void... unused)
        {
            HttpURLConnection conn = null;
            try
            {
                URL url = new URL("http://www.free-map.org.uk/course/mad/ws/hits.php?artist=Oasis");
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                if(conn.getResponseCode() == 200)
                {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String result = "", line;
                    while((line = br.readLine()) !=null)
                        result += line;
                    return result;
                }
                else
                    return "HTTP ERROR: " + conn.getResponseCode();


            }
            catch(IOException e)
            {
                return e.toString();
            }
            finally
            {
                if(conn!=null)
                    conn.disconnect();
            }
        }

        public void onPostExecute(String result)
        {
            EditText name = (EditText)findViewById(R.id.name);
            name.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        MyTask t = new MyTask();
        t.execute();
    }
}


