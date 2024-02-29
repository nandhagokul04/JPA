package controller;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import dao.AdminDao;
import dao.HospitalDao;
import dto.Admin;

public class AdminHospitalController {

	public static void main(String[] args) {
		boolean res=true;
		EntityManager manager= Persistence.createEntityManagerFactory("adhos").createEntityManager();
		AdminDao adao=new AdminDao();
		HospitalDao hdao= new HospitalDao();
		Scanner s=new Scanner(System.in);
		while(res==true) {
		System.out.println("Enter 1 to save Admin");
		System.out.println("Enter 2 to Update Admin");
		System.out.println("Enter 3 to Find Admin by Id");
		System.out.println("Enter 4 to Verify Admin by Phone and Password");
		System.out.println("Enter 5 to Verify Admin by Email and Password");
		System.out.println("Enter 6 to Add Hospital");
		System.out.println("Enter 7 to Update Hospital");
		System.out.println("Enter 8 to Find Hospital by Admin Id");
		System.out.println("Enter 9 to Find Hospital by Admin Phone and password");
		System.out.println("Enter 10 to Find Hospital by Admin Email and password");
		System.out.println("Enter 11 to exit");
		
		
		int choice=s.nextInt();
		if(choice<=11) {
		switch(choice) {
		case 1:{
			System.err.println("Enter name,phone,email,pass");
			Admin admin=new Admin();
			admin.setName(s.next());
			admin.setPhone(s.nextLong());
			admin.setMail(s.next());
			admin.setPassword(s.next());
			adao.Saveuser(admin);
			break;
		}
		case 2:{
			System.err.println("Enter id to update");
			Admin admin=adao.FindById(s.nextInt());
			adao.UpdateAdmin(admin);
			break;
		}
		case 3:{
			System.err.println("Enter id to Find");
			Admin admin=adao.FindById(s.nextInt());
			System.out.println("Name : "+admin.getName()); 
			System.out.println("Phone : "+admin.getPhone()); 
			System.out.println("Mail : "+admin.getMail()); 
			System.out.println("Password : "+admin.getPassword()); 
			break;
		}
		case 4:{
			System.err.println("Enter phone phn password to Find");
			long phone=s.nextLong();
			String pass=s.next();
			Admin admin=adao.VerifyAdminByPhonePass(phone,pass);
			System.out.println("Id : "+admin.getId());
			System.out.println("Name : "+admin.getName()); 
			System.out.println("Phone : "+admin.getPhone()); 
			System.out.println("Mail : "+admin.getMail()); 
			System.out.println("Password : "+admin.getPassword()); break;
		}
		case 5:{
			System.err.println("Enter phone Mail password to Find");
			String mail=s.next();
			String pass=s.next();
			Admin admin=adao.VerifyAdminByMailPass(mail,pass);
			System.out.println("Id : "+admin.getId());
			System.out.println("Name : "+admin.getName()); 
			System.out.println("Phone : "+admin.getPhone()); 
			System.out.println("Mail : "+admin.getMail()); 
			System.out.println("Password : "+admin.getPassword()); 
		}
		case 6:{
			System.err.println("Enter Admin id to Add hospital");
			hdao.AddHospital(s.nextInt());
			break;
			
		}
		case 7:{
			System.err.println("Enter Admin id to Update hospital");
			hdao.UpdateHospital(s.nextInt());break;
			
		}
		case 8:{
			System.err.println("Enter Admin id to Find hospital");
			hdao.FindHospitalbyAdminId(s.nextInt());break;
			
		}
		case 9:{
			System.err.println("Enter Admin phone and password to Find hospital");
			long phone=s.nextLong();
			String pass=s.next();
			hdao.FindHospitalbyAdminPhoneandPassword(phone,pass);break;
			
		}
		case 10:{
			System.err.println("Enter Admin Email and password to Find hospital");
			String phone=s.next();
			String pass=s.next();
			hdao.FindHospitalbyAdminEmailandPassword(phone,pass);break;
			
		}
		case 11:{
			res=false;break;
		}
		
		}
			}
		else {
			System.err.println("Invalid choice entered please privide any of the given option");
		}}
		manager.close();
		s.close();
	}
}
