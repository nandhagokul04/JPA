package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Admin;
import dto.Hospital;

public class HospitalDao {
	Scanner s=new Scanner(System.in);
	AdminDao adao=new AdminDao();
	private EntityManager manager= Persistence.createEntityManagerFactory("adhos").createEntityManager();
	
	public void AddHospital(int id) {
		EntityTransaction transaction=manager.getTransaction();
		try {
		Admin ad=adao.FindById(id);
		Hospital h=new Hospital();
		System.err.println("Enter hospital name,founder,gst,year");
		h.setName(s.next());
		h.setFounder(s.next());
		h.setGst(s.next());
		h.setYoe(LocalDate.parse(s.next()));
		h.setAdmin(ad);
		manager.persist(h);
		List<Hospital> l1=new ArrayList<Hospital>();
		l1.add(h);
		ad.setHospitals(l1);
		transaction.begin();
		transaction.commit();
		System.out.println("Hospital saved with id : "+h.getId());}
		catch(NoResultException e){System.err.println("invalid data entered");}
	}

	public void UpdateHospital(int id) {
		EntityTransaction transaction=manager.getTransaction();
		try {
		Hospital h=manager.find(Hospital.class, id);
		System.err.println("Enter hospital name,founder,gst,year");
		h.setName(s.next());
		h.setFounder(s.next());
		h.setGst(s.next());
		h.setYoe(LocalDate.parse(s.next()));
		manager.persist(h);
		transaction.begin();
		transaction.commit();
		System.out.println("Hospital Updated with id : "+h.getId());
		}
		catch(NoResultException e) {
			System.err.println("invalid data entered");
		}
		
		
		
	}

	public void FindHospitalbyAdminId(int id) {
		Query q=manager.createQuery("select h from Hospital h where h.admin.id=?1");
		q.setParameter(1,id);
		@SuppressWarnings("unchecked")
		List<Hospital> hospital=(ArrayList<Hospital>) q.getResultList();
		if(hospital.size()!=0){
		for(Hospital h:hospital) {
		System.err.println("------------------------------------------");
		System.out.println("Hospital name: "+h.getName());
		System.out.println("Hospital Founder: "+h.getFounder());
		System.out.println("Hospital GST: "+h.getGst());
		System.out.println("Hospital Year: "+h.getYoe());
		System.err.println("------------------------------------------");
		}
		}
		else{
			System.err.println("invalid data entered");
		}
		
	}

	public void FindHospitalbyAdminPhoneandPassword(Long phone,String password) {
		// TODO Auto-generated method stub
		Query q=manager.createQuery("select h from Hospital h where h.admin.phone=?1 and h.admin.password=?2");
		q.setParameter(1,phone);
		q.setParameter(2,password);
		@SuppressWarnings("unchecked")
		List<Hospital> hospital=(ArrayList<Hospital>) q.getResultList();
		if(hospital.size()!=0){
		for(Hospital h:hospital) {
		System.out.println("Hospital name: "+h.getName());
		System.out.println("Hospital Founder: "+h.getFounder());
		System.out.println("Hospital GST: "+h.getGst());
		System.out.println("Hospital Year: "+h.getYoe());
		System.out.println();
		}
		}
		else{
			System.err.println("invalid data entered");
		}
	}

	public void FindHospitalbyAdminEmailandPassword(String phone, String pass) {
		// TODO Auto-generated method stub
		Query q=manager.createQuery("select h from Hospital h where h.admin.mail=?1 and h.admin.password=?2");
		q.setParameter(1,phone);
		q.setParameter(2,pass);
		@SuppressWarnings("unchecked")
		List<Hospital> hospital=(ArrayList<Hospital>) q.getResultList();
		if(hospital.size()!=0){
		for(Hospital h:hospital) {
		System.out.println("Hospital name: "+h.getName());
		System.out.println("Hospital Founder: "+h.getFounder());
		System.out.println("Hospital GST: "+h.getGst());
		System.out.println("Hospital Year: "+h.getYoe());
		System.out.println();
		}
		}
		else{
			System.err.println("invalid data entered");
		}
	}
}
