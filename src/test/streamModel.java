package test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
//import org.apache.commons.io.*;

public class streamModel {
    private streamView view;
    private String charset = "UTF-8";
    private String playlistUrl;
    private String playlist;
    public String workingPath = "working\\";
    private int bandwidth;

    public  void addView(streamView newView) {
        view = newView;
    }

    public void init() {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);

        JPanel p = new JPanel();
        p.setBackground(Color.white);
        JTextArea text = new JTextArea( "License Terms:\n" +
                "\n" +
                "Do not evil\n" +
                "\n" +
                "The MIT License (MIT)\n" +
                "\n" +
                "Copyright (c) 2015 Matthew Lake\n" +
                "\n" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                "of this software and associated documentation files (the \"Software\"), to deal\n" +
                "in the Software without restriction, including without limitation the rights\n" +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                "copies of the Software, and to permit persons to whom the Software is\n" +
                "furnished to do so, subject to the following conditions:\n" +
                "\n" +
                "The above copyright notice and this permission notice shall be included in all\n" +
                "copies or substantial portions of the Software.\n" +
                "\n" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                "SOFTWARE.\n",10,50);
        text.setFont(new Font("sansSerif", Font.PLAIN, 18));
        text.setEditable(false);
        p.add(text);
        int dialogButton = JOptionPane.YES_NO_OPTION;

        String[] options = new String[2];
        options[0] = new String("Accept");
        options[1] = new String("Decline");
        int result = JOptionPane.showOptionDialog(null, p, "License",0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
        if(result == 1){
            System.exit(0);
        }
    }

    public void prep(String url) {
        try {
            url = url.substring(0, url.indexOf('m')) + "media/media_load_hls_mp4.php" + url.split("php")[1];
            String doc = get(url);
            playlistUrl = "http" + doc.split("m3u8")[0].split("http")[doc.split("m3u8")[0].split("http").length - 1] + "m3u8";
            playlist = get(playlistUrl);
            System.out.println(playlist);
            bandwidth = Integer.parseInt(playlist.split("BANDWIDTH=")[1].split(",")[0]);
            System.out.println(bandwidth);
            final String command = "-i " + playlistUrl + " -c copy \"working\\temp.ts\" -y";
            if (JOptionPane.showConfirmDialog(null,"Download" + playlistUrl + "?","Confirm Download",0) == 0) {
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
//        workingPath = "C:\\Users\\mgtlake\\Downloads\\ffmpeg-20150312-git-3bedc99-win64-static\\ffmpeg-20150312-git-3bedc99-win64-static\\bin\\";
        String path = workingPath + "ffmpeg.exe";
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
        File file = new File(workingPath + "temp.ts");
        File destination = new File("");
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("line.separator")+ "Music"));
        fc.setDialogTitle("Save Video");

        int userSelection = fc.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            destination = fc.getSelectedFile();
            System.out.println("Save as file: " + destination.getAbsolutePath());

//            if (FilenameUtils.getExtension(destination.getName()).equalsIgnoreCase("ts")) {
//                // filename is OK as-is
//            } else {
//                destination = new File(destination.getParentFile(), FilenameUtils.getBaseName(destination.getName())+".ts");
//            }
        }

        try {
            java.nio.file.Files.move(file.toPath(),destination.toPath());
        }
        catch (IOException e) {
            showError(e,"Error saving video");
        }
    }

    static String convertStreamToString(InputStream is) {
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

    public void showHelp() {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);

        JPanel p = new JPanel();
        p.setBackground(Color.white);
        JTextArea text = new JTextArea(
                "Check that a directory called 'working' exists on the same level as this program, and contains FFmpeg.exe. If errors persist, try updating FFmpeg. I can be reached at mgtlake@outlook.com if you want to report a bug or request a feature." +
                "\n" +
                "Known bugs:\n" +

                "- Stream Saver will only download the first part of a multi part video\n" +
                "- The progress bar is only an approximate based upon the best available information. It is normal for it to end before 100% or to linger on 100%.\n" +
                "\n" +
                "License Terms:\n" +
                "Do not evil\n" +
                "Licensed under the The MIT License (MIT), accessable at http://opensource.org/licenses/MIT\n" +
                "Copyright (c) 2015 Matthew Lake", 15, 40);
        text.setFont(new Font("sansSerif", Font.PLAIN, 18));
        text.setEditable(false);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        p.add(text);
        JOptionPane.showMessageDialog(null, p, "Help", JOptionPane.INFORMATION_MESSAGE);
    }
}