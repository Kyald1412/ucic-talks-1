package com.kyald.jadwalmatkul.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/***

  https://www.kodingindonesia.com/belajar-membuat-aplikasi-crud-android-menggunakan-database-mysql

 ***/


public class Constants {

    public static final String URL_GET_MATKUL="http://api.yourwish.id/api/matkul.php";
    public static final String URL_GET_MATKUL_BY_HARI = "http://api.yourwish.id/api/matkul.php?id_hari=";
    public static final String URL_GET_HARI = "http://api.yourwish.id/api/hari.php";

    public static final String URL_POST_CREATE_MATKUL = "http://api.yourwish.id/api/create_matkul.php";
    public static final String URL_POST_UPDATE_MATKUL = "http://api.yourwish.id/api/update_matkul.php";
    public static final String URL_DELETE_MATKUL = "http://api.yourwish.id/api/delete_matkul.php";

    public static final String URL_LOGIN = "http://api.yourwish.id/api/login.php";

}
