package test;

import javax.imageio.IIOException;
import java.io.*;
import java.net.*;
import java.lang.ProcessBuilder;

public class streamModel {
    private String charset = "UTF-8";
    private String playlistUrl;
    private InputStream response;

//    public streamModel() {
//        result = 0;
//    }

    public void prep(String url) {
        url = url.substring(0, url.indexOf('m')) + "media/media_load_hls_mp4.php" + url.split("php")[1];
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
        String playlist = "http" + strResponse.split("m3u8")[0].split("http")[strResponse.split("m3u8")[0].split("http").length - 1] + "m3u8";
        String command = "-i " + playlist + " -c copy \"" + "test4" + ".ts\"";
        System.out.println(command);
        download(command);
    }

    public void download(String command){
        command = "-i Drive.mkv drive5.mkv -v quiet";
        String path = "C:\\Users\\mgtlake\\Downloads\\ffmpeg-20150312-git-3bedc99-win64-static\\ffmpeg-20150312-git-3bedc99-win64-static\\bin\\";
        path += "ffmpeg.exe";
//        path = "C:\\Program Files\\Internet Explorer\\iexplore.exe";
        File file = new File(path);
        if (! file.exists()) {
            System.out.println("The file " + path + " does not exist");
        }
        try {
            Process p = Runtime.getRuntime().exec(path + " " + command);
//            Process process = new ProcessBuilder(path,command).start();
//            Process process = new ProcessBuilder(path,"-private").start();

//            // Create ProcessBuilder.
//            ProcessBuilder p = new ProcessBuilder();
//
//            // Use command "notepad.exe" and open the file.
//            p.command("notepad.exe");
//            p.start();
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

//    public int getResult() {
//        return result;
//    }

//    public void clear() {
//        result = 0;
//    }

//    var newUrl = url.substr(0, url.indexOf('m')) + "media/media_load_hls_mp4.php" + url.split('php')[1];
//    var request = new XMLHttpRequest();
//    request.withCredentials = true;
//    request.onload = reqListener;
//    request.open("get", newUrl, true);
//    request.send();
//}
//
//    function reqListener () {
//        var doc = this.responseText;
//        var playlist = "http" + doc.split('m3u8')[0].split('http')[doc.split('m3u8')[0].split('http').length - 1] + "m3u8";
//        var command = "ffmpeg -i " + playlist + " -c copy \"" + document.title + ".ts\"";
//        console.log(command);
//    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}