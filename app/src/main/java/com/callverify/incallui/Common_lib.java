package com.callverify.incallui;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by c_srayav on 7/24/2017.
 */

public class Common_lib {

    private String Phone_Id;


    public Common_lib() {
        // TODO Auto-generated constructor stub
    }

    public String getPhone_Id() {
        return Phone_Id;
    }

    public void setPhone_Id(String phone_Id) {
        Phone_Id = phone_Id;
    }

    public String ReadFile(String Filename) {

        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, Filename);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

        } catch (IOException e) {
        }
        return text.toString();
    }

    public void read_phoneId() {

        String StrPhone_Id;
        StrPhone_Id = ReadFile("FTP_order.txt");

        Phone_Id = StrPhone_Id.substring(0, 1);
    }

//	  private void startParsing() {
//	    	try {
//	    		File file = new File("/sdcard/input.xml");
//	    		InputStream is = new FileInputStream(file.getPath());
//	    		//String[] itemNames = getResources().getStringArray(R.array.Parameters);
//	    		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//	    		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//
//	    		Document doc = dBuilder.parse(is);
//	    		Element element= doc.getDocumentElement();
//	    		element.normalize();
//	    	}catch (Exception e) {e.printStackTrace();}
//	  }

    public void tracePrint(String Tag, String service, String content) {
        String strMessage;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        System.out.println("Current time => " + strDate);
        strMessage = "DROID2PY>>" + strDate + ";" + "phone_" + Phone_Id + ";" + service + ";" + content;
        Log.v(Tag, strMessage);
    }
}
