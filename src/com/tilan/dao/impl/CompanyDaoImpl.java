package com.tilan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.tilan.dao.CompanyDao;
import com.tilan.dao.manager.DaoManager;
import com.tilan.domain.Company;

public class CompanyDaoImpl  implements CompanyDao {

		public CompanyDaoImpl() {

		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Company> getCompanies() {

			EntityManager em = null;

			List<Company> compagnies = null;

			try {
				em = DaoManager.INSTANCE.getEntityManager();
				//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
				compagnies = em.createNamedQuery("findAllCompagnies").getResultList();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
			return compagnies;
		}

		@Override
		public void create(Company company) {
			
			
		}


}
