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
import model.entidade.Funcionarios;
import model.entidade.Movimentos;

/**
 *
 * @author ronan
 */
public class MovimentosJpaController implements Serializable {

    public MovimentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimentos movimentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estabelecimentos idestabelecimento = movimentos.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento = em.getReference(idestabelecimento.getClass(), idestabelecimento.getIdestabelecimento());
                movimentos.setIdestabelecimento(idestabelecimento);
            }
            Funcionarios idfuncionario = movimentos.getIdfuncionario();
            if (idfuncionario != null) {
                idfuncionario = em.getReference(idfuncionario.getClass(), idfuncionario.getIdfuncionario());
                movimentos.setIdfuncionario(idfuncionario);
            }
            em.persist(movimentos);
            if (idestabelecimento != null) {
                idestabelecimento.getMovimentosCollection().add(movimentos);
                idestabelecimento = em.merge(idestabelecimento);
            }
            if (idfuncionario != null) {
                idfuncionario.getMovimentosCollection().add(movimentos);
                idfuncionario = em.merge(idfuncionario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimentos movimentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimentos persistentMovimentos = em.find(Movimentos.class, movimentos.getIdmovimento());
            Estabelecimentos idestabelecimentoOld = persistentMovimentos.getIdestabelecimento();
            Estabelecimentos idestabelecimentoNew = movimentos.getIdestabelecimento();
            Funcionarios idfuncionarioOld = persistentMovimentos.getIdfuncionario();
            Funcionarios idfuncionarioNew = movimentos.getIdfuncionario();
            if (idestabelecimentoNew != null) {
                idestabelecimentoNew = em.getReference(idestabelecimentoNew.getClass(), idestabelecimentoNew.getIdestabelecimento());
                movimentos.setIdestabelecimento(idestabelecimentoNew);
            }
            if (idfuncionarioNew != null) {
                idfuncionarioNew = em.getReference(idfuncionarioNew.getClass(), idfuncionarioNew.getIdfuncionario());
                movimentos.setIdfuncionario(idfuncionarioNew);
            }
            movimentos = em.merge(movimentos);
            if (idestabelecimentoOld != null && !idestabelecimentoOld.equals(idestabelecimentoNew)) {
                idestabelecimentoOld.getMovimentosCollection().remove(movimentos);
                idestabelecimentoOld = em.merge(idestabelecimentoOld);
            }
            if (idestabelecimentoNew != null && !idestabelecimentoNew.equals(idestabelecimentoOld)) {
                idestabelecimentoNew.getMovimentosCollection().add(movimentos);
                idestabelecimentoNew = em.merge(idestabelecimentoNew);
            }
            if (idfuncionarioOld != null && !idfuncionarioOld.equals(idfuncionarioNew)) {
                idfuncionarioOld.getMovimentosCollection().remove(movimentos);
                idfuncionarioOld = em.merge(idfuncionarioOld);
            }
            if (idfuncionarioNew != null && !idfuncionarioNew.equals(idfuncionarioOld)) {
                idfuncionarioNew.getMovimentosCollection().add(movimentos);
                idfuncionarioNew = em.merge(idfuncionarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimentos.getIdmovimento();
                if (findMovimentos(id) == null) {
                    throw new NonexistentEntityException("The movimentos with id " + id + " no longer exists.");
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
            Movimentos movimentos;
            try {
                movimentos = em.getReference(Movimentos.class, id);
                movimentos.getIdmovimento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimentos with id " + id + " no longer exists.", enfe);
            }
            Estabelecimentos idestabelecimento = movimentos.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento.getMovimentosCollection().remove(movimentos);
                idestabelecimento = em.merge(idestabelecimento);
            }
            Funcionarios idfuncionario = movimentos.getIdfuncionario();
            if (idfuncionario != null) {
                idfuncionario.getMovimentosCollection().remove(movimentos);
                idfuncionario = em.merge(idfuncionario);
            }
            em.remove(movimentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimentos> findMovimentosEntities() {
        return findMovimentosEntities(true, -1, -1);
    }

    public List<Movimentos> findMovimentosEntities(int maxResults, int firstResult) {
        return findMovimentosEntities(false, maxResults, firstResult);
    }

    private List<Movimentos> findMovimentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimentos.class));
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

    public Movimentos findMovimentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimentos> rt = cq.from(Movimentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
