package com.tilan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tilan.dao.ComputerDao;
import com.tilan.dao.manager.DaoManager;
import com.tilan.domain.Computer;
import com.tilan.pagination.Pagination;

public class ComputerDaoImpl  implements ComputerDao {

		public ComputerDaoImpl() {

		}

		@SuppressWarnings("unchecked")
		@Override
		public Pagination findAll(int debut, int taille) {

			EntityManager em = null;
			Pagination pagination = null;

			try {
				em = DaoManager.INSTANCE.getEntityManager();
				Query query = em.createQuery("SELECT comp FROM Computer comp");
				Pagination.setNbComputer(query.getResultList().size());
				if (taille > 0) {
	                query.setFirstResult((debut-1)*taille);
	                query.setMaxResults(taille);
	            }

			pagination = new Pagination(query.getResultList());
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
			return pagination;
		}
		
		public Pagination findComputersByName(String name, int debut, int taille) {

			EntityManager em = null;

			Pagination pagination = null;

			try {
				em = DaoManager.INSTANCE.getEntityManager();
				Query query = em.createQuery("SELECT c FROM Computer c WHERE c.name LIKE :nameSearched");
				query.setParameter("nameSearched", "%"+name+"%");
				Pagination.setNbComputer(query.getResultList().size());
				if (taille > 0) {
	                query.setFirstResult((debut-1)*taille);
	                query.setMaxResults(taille);
	            }

				pagination = new Pagination(query.getResultList());
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
			return pagination;
		}

		@Override
		public void create(Computer computer) {

			EntityManager em = null;
			try {
				//Recuperation de l'entityManager qui gere la connexion a la BD
				em = DaoManager.INSTANCE.getEntityManager();
				//Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
				em.getTransaction().begin();
				
				//Sauvegarde de l'utilisateur
				em.persist(computer);
				
				//Commit de la transaction = on applique toutes les operations ci dessus
				em.getTransaction().commit();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
		}

		@Override
		public Pagination findAll() {
			return findAll(0, 0);
		}

		@Override
		public Pagination findComputersByName(String name) {
			
			return findComputersByName(name, 0,0);
		}

		@Override
		public Computer findComputerById(long id) {
			
			EntityManager em = null;
			List<Computer> resultList=null ; 
			Computer computer = null; 
			
			try {
				em = DaoManager.INSTANCE.getEntityManager();
				resultList= em.createQuery("SELECT c FROM Computer c WHERE c.id = :idSearched").setParameter("idSearched", id).getResultList();
				computer= resultList.get(0); 
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
			return computer;
		}

		@Override
		public void deleteComputerById(long id) {
			EntityManager em = null;
			try {
				em = DaoManager.INSTANCE.getEntityManager();
//				em.createQuery("DELETE FROM Computer c WHERE c.id = :idSearched").setParameter("idSearched", id);
	            em.getTransaction().begin();
	            Computer comp = em.find(Computer.class, id);
	            em.remove(comp);
	            em.getTransaction().commit();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
			
		}
		@Override
	    public void update(Computer computer) {
	        EntityManager em = null;
	        try {
	            em = DaoManager.INSTANCE.getEntityManager();
	            em.getTransaction().begin();
	            Computer comp = em.find(Computer.class, computer.getId());

	            comp.setName(computer.getName());
	            comp.setIntroduced(computer.getIntroduced());
	            comp.setDiscontinued(computer.getDiscontinued());
	            comp.setCompany(computer.getCompany());

	            em.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (em != null)
	                em.close();
	        }
	    }
		
//		@Override
//		public void update(Computer computer) {
//			EntityManager em = null;
//			try {
//				//Recuperation de l'entityManager qui gere la connexion a la BD
//				em = DaoManager.INSTANCE.getEntityManager();
//				
//				//Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
//				em.getTransaction().begin();
//				
//				//Merge du computer
//				em.merge(computer);	
//				
//				//Commit de la transaction = on applique toutes les operations ci dessus
//				em.getTransaction().commit();
//			} catch(Exception e) {
//				e.printStackTrace();
//			} finally {
//				if(em != null)
//					em.close();
//			}
//			
//		}
}
