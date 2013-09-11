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
		public List<Computer> getComputers() {

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

		@Override
		public void create(Computer computer) {
			
			
		}
}
