package dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Admin;

public class AdminDao {
	Scanner s=new Scanner(System.in);
	private EntityManager manager= Persistence.createEntityManagerFactory("adhos").createEntityManager();
	public void Saveuser(Admin admin) {
		EntityTransaction transaction=manager.getTransaction();
		manager.persist(admin);
		transaction.begin();
		transaction.commit();
		System.out.println("transaction completed successfully");
	}
	public void UpdateAdmin(Admin admin) {
		Admin ad=FindById(admin.getId());
		if(ad!=null) {
		EntityTransaction transaction=manager.getTransaction();
		System.out.println("Enter name ,phone,mail,pass to update");
		ad.setName(s.next());
		ad.setPhone(s.nextLong());
		ad.setMail(s.next());
		ad.setPassword(s.next());
		transaction.begin();
		transaction.commit();
		System.out.println("Admin updated successfully");
		}
		else {
			System.err.println("Error updating");
		}
	}
	public Admin FindById(int id) {
		Query q=manager.createQuery("select a from Admin a where a.id=?1");
		q.setParameter(1, id);
		Admin admin=(Admin) q.getSingleResult();
		if(admin!=null) {
		return admin;
		}
		else {
			return null;}
	}
	public Admin VerifyAdminByPhonePass(Long phone,String pass) {
		Query q=manager.createQuery("select a from Admin a where a.phone=?1 and a.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, pass);
		Admin admin=(Admin) q.getSingleResult();
		if(admin!=null) {
		return admin;
		}
		else {
			return null;}
	}
	public Admin VerifyAdminByMailPass(String mail, String pass) {
		Query q=manager.createQuery("select a from Admin a where a.mail=?1 and a.password=?2");
		q.setParameter(1, mail);
		q.setParameter(2, pass);
		Admin admin=(Admin) q.getSingleResult();
		if(admin!=null) {
		return admin;
		}
		else {
			return null;}
	}
	
}
