package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final String filePlace = "src/main/resources/Employees.json";
    private Gson gson;

    private Comparator<Employee> idComparator = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public EmployeeRepository(Gson gson) {
        this.gson = gson;
    }
    @Async
    private List<Employee> loadData() {
        var list = new ArrayList<Employee>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePlace));
            list = gson.fromJson(bufferedReader, new TypeToken<List<Employee>>() {
            }.getType());
            bufferedReader.close();
            System.out.println("Lighting objects have been read from " + filePlace + " file.");
            list.sort(idComparator);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    private void writeData(List<Employee> employee) {
        try {
            FileWriter fileWriter = new FileWriter(filePlace);
            gson.toJson(employee, fileWriter);
            fileWriter.close();
            System.out.println("Lighting objects have been saved to " + filePlace + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Async
    public Employee getByID(Long id) {
        List<Employee> employee = loadData();
        var buff = employee.stream().filter(x -> x.getId() == Integer.parseInt(id.toString())).findFirst().get();
        return buff;
    }
    @Async
    public void delete(Long myClassId) {
        List<Employee> myClassList = loadData();
        myClassList.removeIf(x -> myClassId - 1 >= 0 && x.getId() == myClassId);
        writeData(myClassList);
    }
    @Async
    public void save(Employee employee) {
        List<Employee> myClassList = loadData();
        if (myClassList.isEmpty()) {
            employee.setId(Long.valueOf(1));
        } else {
            employee.setId(Long.valueOf(myClassList.get(myClassList.size() - 1).getId() + 1));
        }
        myClassList.add(employee);
        writeData(myClassList);
    }
    @Async
    public List<Employee> findAll() {
        List<Employee> myClassList = loadData();
        return myClassList;
    }
    @Async
    public Employee update(Employee employee) {
        List<Employee> employees = loadData();
        if (!employees.isEmpty() && employee != null) {
            var id = 0;
            for (var item : employees) {
                if (item.getId() == employee.getId()) {
                    break;
                }
                id = id + 1;
            }
            employees.set(id, employee);
        }
        writeData(employees);
        employees = loadData();
        return employees.stream().filter(x -> (x.getId()) == employee.getId()).toList().get(0);
    }
}
