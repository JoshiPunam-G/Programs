package com.bridgelabz.opps;

public class Test {

	public static void main(String[] args) {
		Employee emp=new Employee();
		emp.setEmpno(100);
        emp.setName("vrushali");
        emp.setSalary(20000);
        
      String jsonEmployee=JsonUtill.convertJavaToJson(emp);
      
      System.out.println(jsonEmployee);

	}

}
