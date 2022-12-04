package util;

import model.WorkJob;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadFromJSON {
    public static List<WorkJob> load() throws IOException, ParseException {
        List<WorkJob> list = new ArrayList<WorkJob>();
        if (new File(Settings.db).exists()) {
            FileReader reader = new FileReader(Settings.db);               // Читаем файл
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(reader); // И парсим его в JSON объект
//            for(int i=0; i< jsonObj.size(); i++) {
//                WorkJob job = new WorkJob();
//                //job.setSubject(jsonObj.get(i).g);
//                System.out.println(jsonObj.   get(i).toString());
//            }
            jsonObj.keySet().forEach(keyStr ->
            {
                JSONObject jsonRec = (JSONObject) jsonObj.get(keyStr);
                System.out.println(jsonRec.get("Author"));  ///!!!!!!!!!!!!!!!!!!!!
            });
        }
//        JSONParser parser = new JSONParser();
//
//        try (Reader reader = new FileReader(Settings.db)) {
//
//            JSONObject jsonObject = (JSONObject) parser.parse(reader);
//            System.out.println(jsonObject);
//
//            String name = (String) jsonObject.get("1");
//            System.out.println(name);
//
////            long age = (Long) jsonObject.get("age");
////            System.out.println(age);
//
//            // loop array
////            JSONArray msg = (JSONArray) jsonObject.get("messages");
////            Iterator<String> iterator = msg.iterator();
////            while (iterator.hasNext()) {
////                System.out.println(iterator.next());
////            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
