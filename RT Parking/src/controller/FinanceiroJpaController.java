/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entidade.Estabelecimentos;
import model.entidade.Financeiro;
import model.entidade.Funcionarios;

/**
 *
 * @author ronan
 */
public class FinanceiroJpaController implements Serializable {

    public FinanceiroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Financeiro financeiro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estabelecimentos idestabelecimento = financeiro.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento = em.getReference(idestabelecimento.getClass(), idestabelecimento.getIdestabelecimento());
                financeiro.setIdestabelecimento(idestabelecimento);
            }
            Funcionarios idfuncionario = financeiro.getIdfuncionario();
            if (idfuncionario != null) {
                idfuncionario = em.getReference(idfuncionario.getClass(), idfuncionario.getIdfuncionario());
                financeiro.setIdfuncionario(idfuncionario);
            }
            em.persist(financeiro);
            if (idestabelecimento != null) {
                idestabelecimento.getFinanceiroCollection().add(financeiro);
                idestabelecimento = em.merge(idestabelecimento);
            }
            if (idfuncionario != null) {
                idfuncionario.getFinanceiroCollection().add(financeiro);
                idfuncionario = em.merge(idfuncionario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Financeiro financeiro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Financeiro persistentFinanceiro = em.find(Financeiro.class, financeiro.getIdfinanceiro());
            Estabelecimentos idestabelecimentoOld = persistentFinanceiro.getIdestabelecimento();
            Estabelecimentos idestabelecimentoNew = financeiro.getIdestabelecimento();
            Funcionarios idfuncionarioOld = persistentFinanceiro.getIdfuncionario();
            Funcionarios idfuncionarioNew = financeiro.getIdfuncionario();
            if (idestabelecimentoNew != null) {
                idestabelecimentoNew = em.getReference(idestabelecimentoNew.getClass(), idestabelecimentoNew.getIdestabelecimento());
                financeiro.setIdestabelecimento(idestabelecimentoNew);
            }
            if (idfuncionarioNew != null) {
                idfuncionarioNew = em.getReference(idfuncionarioNew.getClass(), idfuncionarioNew.getIdfuncionario());
                financeiro.setIdfuncionario(idfuncionarioNew);
            }
            financeiro = em.merge(financeiro);
            if (idestabelecimentoOld != null && !idestabelecimentoOld.equals(idestabelecimentoNew)) {
                idestabelecimentoOld.getFinanceiroCollection().remove(financeiro);
                idestabelecimentoOld = em.merge(idestabelecimentoOld);
            }
            if (idestabelecimentoNew != null && !idestabelecimentoNew.equals(idestabelecimentoOld)) {
                idestabelecimentoNew.getFinanceiroCollection().add(financeiro);
                idestabelecimentoNew = em.merge(idestabelecimentoNew);
            }
            if (idfuncionarioOld != null && !idfuncionarioOld.equals(idfuncionarioNew)) {
                idfuncionarioOld.getFinanceiroCollection().remove(financeiro);
                idfuncionarioOld = em.merge(idfuncionarioOld);
            }
            if (idfuncionarioNew != null && !idfuncionarioNew.equals(idfuncionarioOld)) {
                idfuncionarioNew.getFinanceiroCollection().add(financeiro);
                idfuncionarioNew = em.merge(idfuncionarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = financeiro.getIdfinanceiro();
                if (findFinanceiro(id) == null) {
                    throw new NonexistentEntityException("The financeiro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Financeiro financeiro;
            try {
                financeiro = em.getReference(Financeiro.class, id);
                financeiro.getIdfinanceiro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financeiro with id " + id + " no longer exists.", enfe);
            }
            Estabelecimentos idestabelecimento = financeiro.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento.getFinanceiroCollection().remove(financeiro);
                idestabelecimento = em.merge(idestabelecimento);
            }
            Funcionarios idfuncionario = financeiro.getIdfuncionario();
            if (idfuncionario != null) {
                idfuncionario.getFinanceiroCollection().remove(financeiro);
                idfuncionario = em.merge(idfuncionario);
            }
            em.remove(financeiro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Financeiro> findFinanceiroEntities() {
        return findFinanceiroEntities(true, -1, -1);
    }

    public List<Financeiro> findFinanceiroEntities(int maxResults, int firstResult) {
        return findFinanceiroEntities(false, maxResults, firstResult);
    }

    private List<Financeiro> findFinanceiroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Financeiro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Financeiro findFinanceiro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Financeiro.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinanceiroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Financeiro> rt = cq.from(Financeiro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
