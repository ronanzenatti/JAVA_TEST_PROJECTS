/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entidade.Estabelecimentos;
import model.entidade.Servicos;

/**
 *
 * @author ronan
 */
public class ServicosJpaController implements Serializable {

    public ServicosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Servicos servicos) {
        boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estabelecimentos idestabelecimento = servicos.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento = em.getReference(idestabelecimento.getClass(), idestabelecimento.getIdestabelecimento());
                servicos.setIdestabelecimento(idestabelecimento);
            }
            em.persist(servicos);
            if (idestabelecimento != null) {
                idestabelecimento.getServicosCollection().add(servicos);
                idestabelecimento = em.merge(idestabelecimento);
            }
            em.getTransaction().commit();
            return ret = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(Servicos servicos) throws NonexistentEntityException, Exception {
        boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicos persistentServicos = em.find(Servicos.class, servicos.getIdservico());
            Estabelecimentos idestabelecimentoOld = persistentServicos.getIdestabelecimento();
            Estabelecimentos idestabelecimentoNew = servicos.getIdestabelecimento();
            if (idestabelecimentoNew != null) {
                idestabelecimentoNew = em.getReference(idestabelecimentoNew.getClass(), idestabelecimentoNew.getIdestabelecimento());
                servicos.setIdestabelecimento(idestabelecimentoNew);
            }
            servicos = em.merge(servicos);
            if (idestabelecimentoOld != null && !idestabelecimentoOld.equals(idestabelecimentoNew)) {
                idestabelecimentoOld.getServicosCollection().remove(servicos);
                idestabelecimentoOld = em.merge(idestabelecimentoOld);
            }
            if (idestabelecimentoNew != null && !idestabelecimentoNew.equals(idestabelecimentoOld)) {
                idestabelecimentoNew.getServicosCollection().add(servicos);
                idestabelecimentoNew = em.merge(idestabelecimentoNew);
            }
            em.getTransaction().commit();
            return ret = true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicos.getIdservico();
                if (findServicos(id) == null) {
                    throw new NonexistentEntityException("The servicos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean destroy(Integer id) throws NonexistentEntityException {
        boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicos servicos;
            try {
                servicos = em.getReference(Servicos.class, id);
                servicos.getIdservico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicos with id " + id + " no longer exists.", enfe);
            }
            Estabelecimentos idestabelecimento = servicos.getIdestabelecimento();
            if (idestabelecimento != null) {
                idestabelecimento.getServicosCollection().remove(servicos);
                idestabelecimento = em.merge(idestabelecimento);
            }
            em.remove(servicos);
            em.getTransaction().commit();
            return ret = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicos> findServicosEntities() {
        return findServicosEntities(true, -1, -1);
    }

    public List<Servicos> findServicosEntities(int maxResults, int firstResult) {
        return findServicosEntities(false, maxResults, firstResult);
    }

    private List<Servicos> findServicosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicos.class));
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

    public Servicos findServicos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicos.class, id);
        } finally {
            em.close();
        }
    }

    public List<Servicos> findForFind(String campo, String busca, Estabelecimentos e) {
        EntityManager em = getEntityManager();
        List<Servicos> list = new ArrayList<>();
        if (!busca.isEmpty()) {
            try {
                switch (campo) {
                    case "idservico":
                        list = em.createQuery("SELECT s FROM Servicos s WHERE s.idestabelecimento = :e AND s.idservico = :busca")
                                .setParameter("e", e)
                                .setParameter("busca", Integer.valueOf(busca))
                                .getResultList();
                        break;
                    case "descricao":
                        list = em.createQuery("SELECT s FROM Servicos s WHERE s.idestabelecimento = :e AND s.descricao LIKE :busca")
                                .setParameter("e", e)
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "tipo_cobranca":
                        list = em.createQuery("SELECT s FROM Servicos s WHERE s.idestabelecimento = :e AND s.tipo_cobranca LIKE :busca")
                                .setParameter("e", e)
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "quantidade":
                        list = em.createQuery("SELECT s FROM Servicos s WHERE s.idestabelecimento = :e AND s.quantidade LIKE :busca")
                                .setParameter("e", e)
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "valor":
                        list = em.createQuery("SELECT s FROM Servicos s WHERE s.idestabelecimento = :e AND s.valor LIKE :busca")
                                .setParameter("e", e)
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    default:
                        list = em.createQuery("SELECT s FROM Servicos s WHERE s.idestabelecimento = :e")
                                .setParameter("e", e)
                                .getResultList();
                }
                return list;
            } finally {
                em.close();
            }
        } else {
            return list = this.findServicosEntities();
        }
    }

    public int getServicosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicos> rt = cq.from(Servicos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
