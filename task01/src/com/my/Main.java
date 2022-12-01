package com.my;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import data.WorkJob;

public class Main {
/*
Задание:
С учетом информации полученной ранее знакомимся с параметрическим полиморфизмом и продолжаем погружаться в ООП.
Спроектировать и реализовать планировщик дел для работы с задачами разных приоритетов.
Например:
определить уровень приоритетов: низкий, средний, немедленное выполнение
выделить компоненту для хранения данных
выделить компоненту для организации импорта\экспорта данных
Файл должен содержать следующие данные: id, дату добавления записи, время добавления записи, дедлай задачи, ФИО автора,
данные хранятся в файле csv/jsom/xml
другие компоненты
*/

    public static void main(String[] args) {
        Logger logger = Logger.getAnonymousLogger();

        List<WorkJob> list = new ArrayList<>();
        list.add(new WorkJob());
        list.add(new WorkJob());

        list.get(0).setAuthor("123");
        list.get(1).setAuthor("789");

        for (WorkJob w:list) {
            logger.info(w.toString());
        }

        logger.info("Работа завершена.");
    }
}

