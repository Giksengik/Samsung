package com.example.weatherforecast;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class GisService extends Service {

    public static final String CHANNEL = "GIS_SERVICE";
    public static final String INFO = "INFO";

    @Override
    public void onCreate() {
        // сообщение о создании службы
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // сообщение о запуске службы
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();

        // создаем объект нашего AsyncTask (необходимо для работы с сетью)
        GisAsyncTask t = new GisAsyncTask();
        t.execute(); // запускаем AsyncTask

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //сообщение об остановке службы
        Toast.makeText(this, "Service stoped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //поток работы с сетью
    private class GisAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPostExecute(String aVoid) {
            Intent i = new Intent(CHANNEL); // интент для отправки ответа
            i.putExtra(INFO, aVoid); // добавляем в интент данные
            sendBroadcast(i); // рассылаем
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result;
            try {
                //загружаем данные
                URL url = new URL("http://icomms.ru/inf/meteo.php?tid=19");

                // ”оборачиваем” для удобства чтения
                Scanner in = new Scanner((InputStream) url.getContent());

                // читаем и добавляем имя JSON массива
                result = "{\"gis\":" + in.nextLine() + "}";
            } catch (Exception e) {
                result = e.toString();
            }
            return result;
        }
    }
}
