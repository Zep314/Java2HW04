package controller;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.WorkJob;
import org.json.simple.parser.ParseException;
import view.View;
import util.SaveToJSON;
import util.LoadFromJSON;

public class Controller {  // Класс контроллера - тут все и происходит
    private final Logger log = Logger.getLogger(Controller.class.getName());
    private Integer index;
    View view = new View(this.log);  // Цепляем вьювер
    List<WorkJob> list = new ArrayList<>();  // Данные из модели
    public Controller() {  // Активируем логгер
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Controller.class.getResourceAsStream("../log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }
    public void run() throws IOException, ParseException {  // основной метод
        boolean ex = false;
        Scanner scan = new Scanner(System.in);
        String inputLine = "";
        view.info();
        loadFromJSON();
        while (!ex) {  // главный цикл программы
            System.out.print(">>> ");
            inputLine = scan.nextLine();
            if (inputLine.length()>0) {
                switch (inputLine) {
                    // ждем команду
                    case "/quit" -> ex = true;         // Выход
                    case "/info" -> this.view.info();  // Инофрмация
                    case "/help" -> this.view.help();  // Помощь
                    case "/addRecord" -> addRecord();  // Добавляем новую запись
                    case "/editCurrent" -> editRecord();  // Редактируем текущую запись
                    case "/printAll" -> this.view.printAll(this.list,this.index);  // Выводим на печать весь список задач
                                                                                   // в соответствие с приоритетами задач
                    case "/printCurrent" -> printCurrent();  // Печатаем текущую запись
                    case "/setRecord" -> setRecord();        // Установка указателя на нужную запись
                    case "/save" -> saveToJSON();            // Сохранение всего списка задач в файл JSON
                    case "/load" -> loadFromJSON();          // Загрузка из файла JSON списка задач
                    default -> view.errorCommand(inputLine);
                }
            }
        }
        saveToJSON();
        view.bye();
    }

    private void printCurrent() { // Печать текущей записи
        if (this.index<this.list.size()) {
            this.view.printOneJob(list.get(this.index));
        }
    }
    private void addRecord() {  // Добавление нового элемента
        WorkJob job = this.view.recordAddEdit(false, null);
        if (job != null) {
            this.list.add(job);
            this.index = job.getId();
        }
    }
    private void editRecord() {  // Редактирование текущего элемента
        int idx = -1;
        for (int i = 0; i < this.list.size(); i++) {  // Ищем элемент с индексом в списке
            if (this.index == list.get(i).getId()) {
                idx = i;
                break;
            }
        }
        if (idx > -1) {
            WorkJob job = this.view.recordAddEdit(true, this.list.get(idx));
            if (job != null) {
                this.list.set(idx, job);
                this.index = job.getId();
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
        if (this.list.size()>0) {
            this.index = this.list.get(0).getId();
        }
        System.out.println("Данные прочитаны.");
    }
    private void setRecord() {  // Установка указателя на требуемый элемент
        Integer tmpIndex = view.setIndex();
        for(WorkJob job: this.list) {
            if (job.getId() == tmpIndex) {
                this.index = tmpIndex;
                System.out.printf("Новое значение текущей записи: %d%n", this.index);
                log.info(String.format("/setIndex -> %d", this.index));
            }
        }
    }
}
