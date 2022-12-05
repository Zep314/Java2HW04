package model;

import java.util.Comparator;


public class JobComparator implements Comparator<WorkJob>{  // Класс - сравниватель 2-х задач
                                                            // сравниваем задачи по приоритетам

    @Override
    public int compare(WorkJob a, WorkJob b) {
        return (-1)*Integer.compare(a.priority.priority,b.priority.priority);
    }
}
