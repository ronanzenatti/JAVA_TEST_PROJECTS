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
import model.entidade.VeiculosClientes;

/**
 *
 * @author ronan
 */
public class VeiculosClientesJpaController implements Serializable {

    public VeiculosClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VeiculosClientes veiculosClientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes idcliente = veiculosClientes.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                veiculosClientes.setIdcliente(idcliente);
            }
            em.persist(veiculosClientes);
            if (idcliente != null) {
                idcliente.getVeiculosClientesCollection().add(veiculosClientes);
                idcliente = em.merge(idcliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VeiculosClientes veiculosClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeiculosClientes persistentVeiculosClientes = em.find(VeiculosClientes.class, veiculosClientes.getIdvc());
            Clientes idclienteOld = persistentVeiculosClientes.getIdcliente();
            Clientes idclienteNew = veiculosClientes.getIdcliente();
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                veiculosClientes.setIdcliente(idclienteNew);
            }
            veiculosClientes = em.merge(veiculosClientes);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getVeiculosClientesCollection().remove(veiculosClientes);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getVeiculosClientesCollection().add(veiculosClientes);
                idclienteNew = em.merge(idclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = veiculosClientes.getIdvc();
                if (findVeiculosClientes(id) == null) {
                    throw new NonexistentEntityException("The veiculosClientes with id " + id + " no longer exists.");
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
            VeiculosClientes veiculosClientes;
            try {
                veiculosClientes = em.getReference(VeiculosClientes.class, id);
                veiculosClientes.getIdvc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veiculosClientes with id " + id + " no longer exists.", enfe);
            }
            Clientes idcliente = veiculosClientes.getIdcliente();
            if (idcliente != null) {
                idcliente.getVeiculosClientesCollection().remove(veiculosClientes);
                idcliente = em.merge(idcliente);
            }
            em.remove(veiculosClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VeiculosClientes> findVeiculosClientesEntities() {
        return findVeiculosClientesEntities(true, -1, -1);
    }

    public List<VeiculosClientes> findVeiculosClientesEntities(int maxResults, int firstResult) {
        return findVeiculosClientesEntities(false, maxResults, firstResult);
    }

    private List<VeiculosClientes> findVeiculosClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VeiculosClientes.class));
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

    public VeiculosClientes findVeiculosClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VeiculosClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeiculosClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VeiculosClientes> rt = cq.from(VeiculosClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
