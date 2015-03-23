package test;

import javafx.stage.FileChooser;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.lang.ProcessBuilder;
import java.nio.file.Path;

public class streamModel {
    private streamView view;
    private String charset = "UTF-8";
    private String playlistUrl;
    private String playlist;
    public String ffmpegPath = "\\working";
    private int bandwidth;

    public  void addView(streamView newView) {
        view = newView;
    }

    public void prep(String url) {
        view.setProgress(50);
        try {
            url = url.substring(0, url.indexOf('m')) + "media/media_load_hls_mp4.php" + url.split("php")[1];
            String doc = get(url);
            playlistUrl = "http" + doc.split("m3u8")[0].split("http")[doc.split("m3u8")[0].split("http").length - 1] + "m3u8";
            playlist = get(playlistUrl);
            System.out.println(playlist);
            bandwidth = Integer.parseInt(playlist.split("BANDWIDTH=")[1].split(",")[0]);
            System.out.println(bandwidth);
            final String command = "-i " + playlistUrl + " -c copy \"" + "temp.ts\" -y";
            view.setProgress(75);
            if (JOptionPane.showConfirmDialog(null,"Download" + playlistUrl + "?","Confirm Download",0) == 0) {
                view.setProgress(60);
                Runnable runner = new Runnable()
                {
                    public void run() {
                        String c = command;
                        download(c);
                        save();
                    }
                };
                Thread t = new Thread(runner, "Code Executer");
                t.start();
            }
        }
        catch (Exception e) {
            showError(e, "Video not found");
        }
    }

    public void download(String command){
        view.setProgress(20);
        ffmpegPath = "C:\\Users\\mgtlake\\Downloads\\ffmpeg-20150312-git-3bedc99-win64-static\\ffmpeg-20150312-git-3bedc99-win64-static\\bin\\";
        String path = ffmpegPath + "ffmpeg.exe";
        File file = new File(path);
        if (! file.exists()) {
            System.out.println("The file " + path + " does not exist");
        }
        try {
            Process p = Runtime.getRuntime().exec(path + " " + command);
            BufferedReader is = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line;
            while ((line = is.readLine()) != null) {
                try {
                    int size = Integer.parseInt(line.split("size=")[1].split("kB")[0].trim());
                    double percent = 100 * size / (double) bandwidth;
                    System.out.println(String.valueOf(percent));
                    view.setProgress((int) percent);
                }
                catch (Exception e) {
                    System.out.println(line);
                }
            }
        }
        catch (IOException e) {
            showError(e, "FFmpeg not found");
        }
    }

    public String get(String url) {
        InputStream response = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        }
        catch (MalformedURLException e) {
            System.out.println(e.toString());
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        String strResponse = convertStreamToString(response);
        return strResponse;
    }

    public void save() {
        File file = new File("temp.ts");
        File destination = new File("");
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Save Video");

        int userSelection = fc.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            destination = fc.getSelectedFile();
            System.out.println("Save as file: " + destination.getAbsolutePath());
        }

        try {
            java.nio.file.Files.move(file.toPath(),destination.toPath());
        }
        catch (IOException e) {
            showError(e,"Error saving video");
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public void showError(Exception e, String message) {
        System.out.println(e.toString());
        JPanel p = new JPanel();
        JLabel label = new JLabel(message);
        label.setFont(new Font("sansSerif", Font.PLAIN, 18));
        JLabel details = new JLabel(e.toString());
        details.setFont(new Font("sansSerif", Font.PLAIN, 14));
        p.add(label);
        p.add(details);
        JOptionPane.showMessageDialog(null,p,"Error",JOptionPane.ERROR_MESSAGE);
    }
}