package models.comunicaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Telegram {
    public static boolean enviaMensajeTelegram(String mensaje, String API, String CHATID){
        String direccion;
        String fijo = "https://api.telegram.org/" + API + "/sendMessage?chat_id=" + CHATID + "&text=";
        direccion =  fijo+mensaje;
        URL url;
        boolean dev;
        dev = false;
        try{
            url = new URL(direccion);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            dev = true;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return dev;

    }
}
