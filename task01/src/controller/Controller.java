package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.Priority;
import model.WorkJob;
import org.json.simple.parser.ParseException;
import view.View;
import util.SaveToJSON;
import util.LoadFromJSON;

public class Controller {
    private final Logger log = Logger.getLogger(Controller.class.getName());
    private Integer index;
    View view = new View(this.log);
    List<WorkJob> list = new ArrayList<>();
    public Controller() {
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Controller.class.getResourceAsStream("../log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }
    public void run() throws IOException, ParseException {
        list.add(new WorkJob());
        list.add(new WorkJob());

        list.get(0).setAuthor("qqqqq");
        list.get(0).setPriority(Priority.LOW);
        list.get(0).setSubject("aaaaaaa");

        list.get(1).setAuthor("pppppp");
        list.get(1).setPriority(Priority.HIGH);
        list.get(1).setSubject("llllkkkk");

        boolean ex = false;
        Scanner scan = new Scanner(System.in);
        String inputLine = "";
        view.info();
        while (!ex) {  // главный цикл программы
            System.out.print(">>> ");
            inputLine = scan.nextLine();
            if (inputLine.length()>0) {
                switch (inputLine.split(" ", 2)[0]) {  // в первой части введенной строки
                    // ждем команду
                    case "/quit" -> ex = true;
                    case "/info" -> this.view.info();
                    case "/help" -> this.view.help();
                    case "/addRecord" -> addRecord();
                    case "/editCurrent" -> editRecord();
                    case "/printAll" -> this.view.printAll(list);
                    case "/printCurrent" -> printCurrent();
//                    case "/setRecord" -> SetRecord(inputLine.split(" ", 2)[1]);
                    case "/save" -> saveToJSON();
                    case "/load" -> loadFromJSON();
                    default -> view.errorCommand(inputLine);
                }
            }
        }
        view.bye();
    }

    private void printCurrent() {
        if (this.index<this.list.size()) {
            this.view.printOneJob(list.get(this.index));
        }
    }
    private void addRecord() {
        WorkJob job = this.view.recordAddEdit(false, null);
        if (job != null) {
            this.list.add(job);
            this.index = job.getId();
        }
    }
    private void editRecord() {
        if (this.index<this.list.size()) {
            WorkJob job = this.view.recordAddEdit(true, this.list.get(this.index));
            if (job != null) {
                this.list.set(this.index, job);
            }
        }
    }
    private void saveToJSON() throws IOException {
        log.info("/save");
        SaveToJSON.save(this.list);
        System.out.println("Данные записаны!");
    }
    private void loadFromJSON() throws IOException, ParseException {
        log.info("/load");
        this.list.clear();
        this.list = LoadFromJSON.load();
        this.index = 0;
        System.out.println("Данные прочитаны.");
    }
}
