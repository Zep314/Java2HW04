package util;

import model.WorkJob;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;

public class SaveToJSON {  // Пишем список в файл JSON
    public static void save(List<WorkJob> localList) throws IOException {
        JSONObject jsonObj = new JSONObject();
        for(WorkJob job: localList) {   // Проходим по всей базе и формируем JSON объект
            JSONObject jsonRec = new JSONObject();  // новый элемент (строка в таблице)
            jsonRec.put("Priority", job.getPriority().toString());
            jsonRec.put("Subject", job.getSubject());
            jsonRec.put("Author", job.getAuthor());
            jsonRec.put("Created", job.getCreationDT().format(Settings.formatter));
            jsonRec.put("Deadline", job.getDeadlineDT().format(Settings.formatter));
            jsonObj.put(job.getId().toString(),jsonRec);
        }
        Files.write(Paths.get(Settings.db), jsonObj.toJSONString().getBytes());  // пишем JSON в файл
    }
}
