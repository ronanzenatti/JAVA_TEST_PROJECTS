/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entidade.Convenios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entidade.Clientes;
import model.entidade.VeiculosClientes;

/**
 *
 * @author ronan
 */
public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Clientes clientes) {
        boolean ret = true;
        if (clientes.getConveniosCollection() == null) {
            clientes.setConveniosCollection(new ArrayList<Convenios>());
        }
        if (clientes.getVeiculosClientesCollection() == null) {
            clientes.setVeiculosClientesCollection(new ArrayList<VeiculosClientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Convenios> attachedConveniosCollection = new ArrayList<Convenios>();
            for (Convenios conveniosCollectionConveniosToAttach : clientes.getConveniosCollection()) {
                conveniosCollectionConveniosToAttach = em.getReference(conveniosCollectionConveniosToAttach.getClass(), conveniosCollectionConveniosToAttach.getIdconvenio());
                attachedConveniosCollection.add(conveniosCollectionConveniosToAttach);
            }
            clientes.setConveniosCollection(attachedConveniosCollection);
            Collection<VeiculosClientes> attachedVeiculosClientesCollection = new ArrayList<VeiculosClientes>();
            for (VeiculosClientes veiculosClientesCollectionVeiculosClientesToAttach : clientes.getVeiculosClientesCollection()) {
                veiculosClientesCollectionVeiculosClientesToAttach = em.getReference(veiculosClientesCollectionVeiculosClientesToAttach.getClass(), veiculosClientesCollectionVeiculosClientesToAttach.getIdvc());
                attachedVeiculosClientesCollection.add(veiculosClientesCollectionVeiculosClientesToAttach);
            }
            clientes.setVeiculosClientesCollection(attachedVeiculosClientesCollection);
            em.persist(clientes);
            for (Convenios conveniosCollectionConvenios : clientes.getConveniosCollection()) {
                Clientes oldIdclienteOfConveniosCollectionConvenios = conveniosCollectionConvenios.getIdcliente();
                conveniosCollectionConvenios.setIdcliente(clientes);
                conveniosCollectionConvenios = em.merge(conveniosCollectionConvenios);
                if (oldIdclienteOfConveniosCollectionConvenios != null) {
                    oldIdclienteOfConveniosCollectionConvenios.getConveniosCollection().remove(conveniosCollectionConvenios);
                    oldIdclienteOfConveniosCollectionConvenios = em.merge(oldIdclienteOfConveniosCollectionConvenios);
                }
            }
            for (VeiculosClientes veiculosClientesCollectionVeiculosClientes : clientes.getVeiculosClientesCollection()) {
                Clientes oldIdclienteOfVeiculosClientesCollectionVeiculosClientes = veiculosClientesCollectionVeiculosClientes.getIdcliente();
                veiculosClientesCollectionVeiculosClientes.setIdcliente(clientes);
                veiculosClientesCollectionVeiculosClientes = em.merge(veiculosClientesCollectionVeiculosClientes);
                if (oldIdclienteOfVeiculosClientesCollectionVeiculosClientes != null) {
                    oldIdclienteOfVeiculosClientesCollectionVeiculosClientes.getVeiculosClientesCollection().remove(veiculosClientesCollectionVeiculosClientes);
                    oldIdclienteOfVeiculosClientesCollectionVeiculosClientes = em.merge(oldIdclienteOfVeiculosClientesCollectionVeiculosClientes);
                }
            }
            em.getTransaction().commit();
            return ret = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        boolean ret = true;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getIdcliente());
            Collection<Convenios> conveniosCollectionOld = persistentClientes.getConveniosCollection();
            Collection<Convenios> conveniosCollectionNew = clientes.getConveniosCollection();
            Collection<VeiculosClientes> veiculosClientesCollectionOld = persistentClientes.getVeiculosClientesCollection();
            Collection<VeiculosClientes> veiculosClientesCollectionNew = clientes.getVeiculosClientesCollection();
            List<String> illegalOrphanMessages = null;
            for (Convenios conveniosCollectionOldConvenios : conveniosCollectionOld) {
                if (!conveniosCollectionNew.contains(conveniosCollectionOldConvenios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Convenios " + conveniosCollectionOldConvenios + " since its idcliente field is not nullable.");
                }
            }
            for (VeiculosClientes veiculosClientesCollectionOldVeiculosClientes : veiculosClientesCollectionOld) {
                if (!veiculosClientesCollectionNew.contains(veiculosClientesCollectionOldVeiculosClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeiculosClientes " + veiculosClientesCollectionOldVeiculosClientes + " since its idcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Convenios> attachedConveniosCollectionNew = new ArrayList<Convenios>();
            for (Convenios conveniosCollectionNewConveniosToAttach : conveniosCollectionNew) {
                conveniosCollectionNewConveniosToAttach = em.getReference(conveniosCollectionNewConveniosToAttach.getClass(), conveniosCollectionNewConveniosToAttach.getIdconvenio());
                attachedConveniosCollectionNew.add(conveniosCollectionNewConveniosToAttach);
            }
            conveniosCollectionNew = attachedConveniosCollectionNew;
            clientes.setConveniosCollection(conveniosCollectionNew);
            Collection<VeiculosClientes> attachedVeiculosClientesCollectionNew = new ArrayList<VeiculosClientes>();
            for (VeiculosClientes veiculosClientesCollectionNewVeiculosClientesToAttach : veiculosClientesCollectionNew) {
                veiculosClientesCollectionNewVeiculosClientesToAttach = em.getReference(veiculosClientesCollectionNewVeiculosClientesToAttach.getClass(), veiculosClientesCollectionNewVeiculosClientesToAttach.getIdvc());
                attachedVeiculosClientesCollectionNew.add(veiculosClientesCollectionNewVeiculosClientesToAttach);
            }
            veiculosClientesCollectionNew = attachedVeiculosClientesCollectionNew;
            clientes.setVeiculosClientesCollection(veiculosClientesCollectionNew);
            clientes = em.merge(clientes);
            for (Convenios conveniosCollectionNewConvenios : conveniosCollectionNew) {
                if (!conveniosCollectionOld.contains(conveniosCollectionNewConvenios)) {
                    Clientes oldIdclienteOfConveniosCollectionNewConvenios = conveniosCollectionNewConvenios.getIdcliente();
                    conveniosCollectionNewConvenios.setIdcliente(clientes);
                    conveniosCollectionNewConvenios = em.merge(conveniosCollectionNewConvenios);
                    if (oldIdclienteOfConveniosCollectionNewConvenios != null && !oldIdclienteOfConveniosCollectionNewConvenios.equals(clientes)) {
                        oldIdclienteOfConveniosCollectionNewConvenios.getConveniosCollection().remove(conveniosCollectionNewConvenios);
                        oldIdclienteOfConveniosCollectionNewConvenios = em.merge(oldIdclienteOfConveniosCollectionNewConvenios);
                    }
                }
            }
            for (VeiculosClientes veiculosClientesCollectionNewVeiculosClientes : veiculosClientesCollectionNew) {
                if (!veiculosClientesCollectionOld.contains(veiculosClientesCollectionNewVeiculosClientes)) {
                    Clientes oldIdclienteOfVeiculosClientesCollectionNewVeiculosClientes = veiculosClientesCollectionNewVeiculosClientes.getIdcliente();
                    veiculosClientesCollectionNewVeiculosClientes.setIdcliente(clientes);
                    veiculosClientesCollectionNewVeiculosClientes = em.merge(veiculosClientesCollectionNewVeiculosClientes);
                    if (oldIdclienteOfVeiculosClientesCollectionNewVeiculosClientes != null && !oldIdclienteOfVeiculosClientesCollectionNewVeiculosClientes.equals(clientes)) {
                        oldIdclienteOfVeiculosClientesCollectionNewVeiculosClientes.getVeiculosClientesCollection().remove(veiculosClientesCollectionNewVeiculosClientes);
                        oldIdclienteOfVeiculosClientesCollectionNewVeiculosClientes = em.merge(oldIdclienteOfVeiculosClientesCollectionNewVeiculosClientes);
                    }
                }
            }
            em.getTransaction().commit();
            return ret = true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientes.getIdcliente();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        boolean ret = true;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Convenios> conveniosCollectionOrphanCheck = clientes.getConveniosCollection();
            for (Convenios conveniosCollectionOrphanCheckConvenios : conveniosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the Convenios " + conveniosCollectionOrphanCheckConvenios + " in its conveniosCollection field has a non-nullable idcliente field.");
            }
            Collection<VeiculosClientes> veiculosClientesCollectionOrphanCheck = clientes.getVeiculosClientesCollection();
            for (VeiculosClientes veiculosClientesCollectionOrphanCheckVeiculosClientes : veiculosClientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the VeiculosClientes " + veiculosClientesCollectionOrphanCheckVeiculosClientes + " in its veiculosClientesCollection field has a non-nullable idcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(clientes);
            em.getTransaction().commit();
            return ret = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
