package test;

public class streamModel {

    private int result;

    public streamModel() {
        result = 0;
    }

    public void add(int n) {
        result += n;
    }

    public int getResult() {
        return result;
    }

    public void clear() {
        result = 0;
    }

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
}