package com.company.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Bản chất pattern này là việc thực hiện fun clone.
 * Ở đây ta override fun clone để thực hiện deep clone.
 * Nếu k override là shadow clone, nó chỉ copy các value ở dạng biến nguyên thủy
 */
public class Employees implements Cloneable{

    private List<String> empList;

    public Employees(){
        empList = new ArrayList<String>();
    }

    public Employees(List<String> list){
        this.empList=list;
    }
    public void loadData(){
        //read all employees from database and put into the list
        empList.add("Pankaj");
        empList.add("Raj");
        empList.add("David");
        empList.add("Lisa");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        List<String> temp = new ArrayList<String>();
        for(String s : this.getEmpList()){
            temp.add(s);
        }
        return new Employees(temp);
    }

}
