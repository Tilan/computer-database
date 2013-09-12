package com.tilan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.tilan.dao.ComputerDao;
import com.tilan.dao.manager.DaoManager;
import com.tilan.domain.Computer;

public class ComputerDaoImpl  implements ComputerDao {

		public ComputerDaoImpl() {

		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Computer> findAll() {

			EntityManager em = null;

			List<Computer> computers = null;

			try {
				em = DaoManager.INSTANCE.getEntityManager();
				//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
				computers = em.createNamedQuery("findAllComputers").getResultList();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
			return computers;
		}
		
		public List<Computer> findComputersByName(String name) {

			EntityManager em = null;

			List<Computer> computers = null;

			try {
				em = DaoManager.INSTANCE.getEntityManager();
				//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
				computers= em.createQuery("SELECT c FROM Computer c WHERE c.name LIKE :nameSearched").setParameter("nameSearched", "%"+name+"%").getResultList();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
			return computers;
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
}
