package cg.boal.riplocker;

import android.content.Context;
import android.telephony.SmsManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.phoneNumber;

/**
 * Created by Cgboal on 19/04/2017.
 */

public class FileHelper {
    private Context c;
    private String p;
    private List<Song> s;
    private File f;
    private Playlist pO;
    private List<Song> sO;
    private Parser parse;

    public FileHelper(Context c, String p) {
        this.c = c;
        this.p = p;
        this.pO = new Playlist();
        f = new File(c.getFilesDir(), p);
        String s = read();
        if (!s.equals("")) {
            this.parse = new Parser(s);
            this.pO.setName(parse.parseName());
            this.sO = parse.parseSongs();
        }

    }

    public FileHelper(Context c, String p, List<Song> s){
        this.p = p;
        this.c = c;
        this.s = s;
        f = new File(c.getFilesDir(), p);


    }

    public Playlist getPlaylist() { return this.pO;}
    public List<Song> getSongs() { return this.sO;}


    private String prettify() {
        StringBuilder sb = new StringBuilder();
        sb.append(p + "\n");
        for (int i = 0; i < this.s.size(); i++) {
            Song song = this.s.get(i);
             sb.append(song.getTitle().trim() + ":" + song.getArtist().trim() + "\n");
        }
        return sb.toString();
    }

    public void write() {
        String output = this.prettify();
        try {
            FileOutputStream fo = new FileOutputStream(this.f);
            fo.write(output.getBytes());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private String read() {
        try {
            FileInputStream fis = new FileInputStream(this.f);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String l;
            while ((l = bufferedReader.readLine()) != null) {
                sb.append(l).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    public void send(String number) {
        String message = this.prettify();
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number, null, message, null, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }






}

class Parser {
    private String in;
    public Parser(String s) {
        this.in = s;
    }

    public String parseName() {
        return this.in.split("\n")[0];
    }
    public List<Song> parseSongs() {
        List<Song> songs = new ArrayList<Song>();
        String[] parts = this.in.split("\n");
        for (int i = 1; i < parts.length; i++) {
            String[] ln = parts[i].split(":");
            Song song = new Song();
            song.setTitle(ln[0].trim());
            song.setArtist(ln[1].trim());
            songs.add(song);
        }

        return songs;
    }

}