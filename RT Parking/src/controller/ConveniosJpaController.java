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
import model.entidade.Clientes;
import model.entidade.Convenios;
import model.entidade.Estabelecimentos;

/**
 *
 * @author ronan
 */
public class ConveniosJpaController implements Serializable {

    public ConveniosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Convenios convenios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes idcliente = convenios.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                convenios.setIdcliente(idcliente);
            }
            Estabelecimentos idestabelecimento = convenios.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento = em.getReference(idestabelecimento.getClass(), idestabelecimento.getIdestabelecimento());
                convenios.setIdestabelecimento(idestabelecimento);
            }
            em.persist(convenios);
            if (idcliente != null) {
                idcliente.getConveniosCollection().add(convenios);
                idcliente = em.merge(idcliente);
            }
            if (idestabelecimento != null) {
                idestabelecimento.getConveniosCollection().add(convenios);
                idestabelecimento = em.merge(idestabelecimento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Convenios convenios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Convenios persistentConvenios = em.find(Convenios.class, convenios.getIdconvenio());
            Clientes idclienteOld = persistentConvenios.getIdcliente();
            Clientes idclienteNew = convenios.getIdcliente();
            Estabelecimentos idestabelecimentoOld = persistentConvenios.getIdestabelecimento();
            Estabelecimentos idestabelecimentoNew = convenios.getIdestabelecimento();
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                convenios.setIdcliente(idclienteNew);
            }
            if (idestabelecimentoNew != null) {
                idestabelecimentoNew = em.getReference(idestabelecimentoNew.getClass(), idestabelecimentoNew.getIdestabelecimento());
                convenios.setIdestabelecimento(idestabelecimentoNew);
            }
            convenios = em.merge(convenios);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getConveniosCollection().remove(convenios);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getConveniosCollection().add(convenios);
                idclienteNew = em.merge(idclienteNew);
            }
            if (idestabelecimentoOld != null && !idestabelecimentoOld.equals(idestabelecimentoNew)) {
                idestabelecimentoOld.getConveniosCollection().remove(convenios);
                idestabelecimentoOld = em.merge(idestabelecimentoOld);
            }
            if (idestabelecimentoNew != null && !idestabelecimentoNew.equals(idestabelecimentoOld)) {
                idestabelecimentoNew.getConveniosCollection().add(convenios);
                idestabelecimentoNew = em.merge(idestabelecimentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = convenios.getIdconvenio();
                if (findConvenios(id) == null) {
                    throw new NonexistentEntityException("The convenios with id " + id + " no longer exists.");
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
            Convenios convenios;
            try {
                convenios = em.getReference(Convenios.class, id);
                convenios.getIdconvenio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenios with id " + id + " no longer exists.", enfe);
            }
            Clientes idcliente = convenios.getIdcliente();
            if (idcliente != null) {
                idcliente.getConveniosCollection().remove(convenios);
                idcliente = em.merge(idcliente);
            }
            Estabelecimentos idestabelecimento = convenios.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento.getConveniosCollection().remove(convenios);
                idestabelecimento = em.merge(idestabelecimento);
            }
            em.remove(convenios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Convenios> findConveniosEntities() {
        return findConveniosEntities(true, -1, -1);
    }

    public List<Convenios> findConveniosEntities(int maxResults, int firstResult) {
        return findConveniosEntities(false, maxResults, firstResult);
    }

    private List<Convenios> findConveniosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Convenios.class));
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

    public Convenios findConvenios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Convenios.class, id);
        } finally {
            em.close();
        }
    }

    public int getConveniosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Convenios> rt = cq.from(Convenios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
