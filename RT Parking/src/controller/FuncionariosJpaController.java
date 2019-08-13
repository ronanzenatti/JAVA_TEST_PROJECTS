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
import model.entidade.Financeiro;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.entidade.Funcionarios;
import model.entidade.Movimentos;

/**
 *
 * @author ronan
 */
public class FuncionariosJpaController implements Serializable {

    public FuncionariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Funcionarios funcionarios) {
        boolean ret = false;
        if (funcionarios.getFinanceiroCollection() == null) {
            funcionarios.setFinanceiroCollection(new ArrayList<Financeiro>());
        }
        if (funcionarios.getMovimentosCollection() == null) {
            funcionarios.setMovimentosCollection(new ArrayList<Movimentos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Financeiro> attachedFinanceiroCollection = new ArrayList<Financeiro>();
            for (Financeiro financeiroCollectionFinanceiroToAttach : funcionarios.getFinanceiroCollection()) {
                financeiroCollectionFinanceiroToAttach = em.getReference(financeiroCollectionFinanceiroToAttach.getClass(), financeiroCollectionFinanceiroToAttach.getIdfinanceiro());
                attachedFinanceiroCollection.add(financeiroCollectionFinanceiroToAttach);
            }
            funcionarios.setFinanceiroCollection(attachedFinanceiroCollection);
            Collection<Movimentos> attachedMovimentosCollection = new ArrayList<Movimentos>();
            for (Movimentos movimentosCollectionMovimentosToAttach : funcionarios.getMovimentosCollection()) {
                movimentosCollectionMovimentosToAttach = em.getReference(movimentosCollectionMovimentosToAttach.getClass(), movimentosCollectionMovimentosToAttach.getIdmovimento());
                attachedMovimentosCollection.add(movimentosCollectionMovimentosToAttach);
            }
            funcionarios.setMovimentosCollection(attachedMovimentosCollection);
            em.persist(funcionarios);
            for (Financeiro financeiroCollectionFinanceiro : funcionarios.getFinanceiroCollection()) {
                Funcionarios oldIdfuncionarioOfFinanceiroCollectionFinanceiro = financeiroCollectionFinanceiro.getIdfuncionario();
                financeiroCollectionFinanceiro.setIdfuncionario(funcionarios);
                financeiroCollectionFinanceiro = em.merge(financeiroCollectionFinanceiro);
                if (oldIdfuncionarioOfFinanceiroCollectionFinanceiro != null) {
                    oldIdfuncionarioOfFinanceiroCollectionFinanceiro.getFinanceiroCollection().remove(financeiroCollectionFinanceiro);
                    oldIdfuncionarioOfFinanceiroCollectionFinanceiro = em.merge(oldIdfuncionarioOfFinanceiroCollectionFinanceiro);
                }
            }
            for (Movimentos movimentosCollectionMovimentos : funcionarios.getMovimentosCollection()) {
                Funcionarios oldIdfuncionarioOfMovimentosCollectionMovimentos = movimentosCollectionMovimentos.getIdfuncionario();
                movimentosCollectionMovimentos.setIdfuncionario(funcionarios);
                movimentosCollectionMovimentos = em.merge(movimentosCollectionMovimentos);
                if (oldIdfuncionarioOfMovimentosCollectionMovimentos != null) {
                    oldIdfuncionarioOfMovimentosCollectionMovimentos.getMovimentosCollection().remove(movimentosCollectionMovimentos);
                    oldIdfuncionarioOfMovimentosCollectionMovimentos = em.merge(oldIdfuncionarioOfMovimentosCollectionMovimentos);
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

    public boolean edit(Funcionarios funcionarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionarios persistentFuncionarios = em.find(Funcionarios.class, funcionarios.getIdfuncionario());
            Collection<Financeiro> financeiroCollectionOld = persistentFuncionarios.getFinanceiroCollection();
            Collection<Financeiro> financeiroCollectionNew = funcionarios.getFinanceiroCollection();
            Collection<Movimentos> movimentosCollectionOld = persistentFuncionarios.getMovimentosCollection();
            Collection<Movimentos> movimentosCollectionNew = funcionarios.getMovimentosCollection();
            List<String> illegalOrphanMessages = null;
            for (Financeiro financeiroCollectionOldFinanceiro : financeiroCollectionOld) {
                if (!financeiroCollectionNew.contains(financeiroCollectionOldFinanceiro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Financeiro " + financeiroCollectionOldFinanceiro + " since its idfuncionario field is not nullable.");
                }
            }
            for (Movimentos movimentosCollectionOldMovimentos : movimentosCollectionOld) {
                if (!movimentosCollectionNew.contains(movimentosCollectionOldMovimentos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimentos " + movimentosCollectionOldMovimentos + " since its idfuncionario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Financeiro> attachedFinanceiroCollectionNew = new ArrayList<Financeiro>();
            for (Financeiro financeiroCollectionNewFinanceiroToAttach : financeiroCollectionNew) {
                financeiroCollectionNewFinanceiroToAttach = em.getReference(financeiroCollectionNewFinanceiroToAttach.getClass(), financeiroCollectionNewFinanceiroToAttach.getIdfinanceiro());
                attachedFinanceiroCollectionNew.add(financeiroCollectionNewFinanceiroToAttach);
            }
            financeiroCollectionNew = attachedFinanceiroCollectionNew;
            funcionarios.setFinanceiroCollection(financeiroCollectionNew);
            Collection<Movimentos> attachedMovimentosCollectionNew = new ArrayList<Movimentos>();
            for (Movimentos movimentosCollectionNewMovimentosToAttach : movimentosCollectionNew) {
                movimentosCollectionNewMovimentosToAttach = em.getReference(movimentosCollectionNewMovimentosToAttach.getClass(), movimentosCollectionNewMovimentosToAttach.getIdmovimento());
                attachedMovimentosCollectionNew.add(movimentosCollectionNewMovimentosToAttach);
            }
            movimentosCollectionNew = attachedMovimentosCollectionNew;
            funcionarios.setMovimentosCollection(movimentosCollectionNew);
            funcionarios = em.merge(funcionarios);
            for (Financeiro financeiroCollectionNewFinanceiro : financeiroCollectionNew) {
                if (!financeiroCollectionOld.contains(financeiroCollectionNewFinanceiro)) {
                    Funcionarios oldIdfuncionarioOfFinanceiroCollectionNewFinanceiro = financeiroCollectionNewFinanceiro.getIdfuncionario();
                    financeiroCollectionNewFinanceiro.setIdfuncionario(funcionarios);
                    financeiroCollectionNewFinanceiro = em.merge(financeiroCollectionNewFinanceiro);
                    if (oldIdfuncionarioOfFinanceiroCollectionNewFinanceiro != null && !oldIdfuncionarioOfFinanceiroCollectionNewFinanceiro.equals(funcionarios)) {
                        oldIdfuncionarioOfFinanceiroCollectionNewFinanceiro.getFinanceiroCollection().remove(financeiroCollectionNewFinanceiro);
                        oldIdfuncionarioOfFinanceiroCollectionNewFinanceiro = em.merge(oldIdfuncionarioOfFinanceiroCollectionNewFinanceiro);
                    }
                }
            }
            for (Movimentos movimentosCollectionNewMovimentos : movimentosCollectionNew) {
                if (!movimentosCollectionOld.contains(movimentosCollectionNewMovimentos)) {
                    Funcionarios oldIdfuncionarioOfMovimentosCollectionNewMovimentos = movimentosCollectionNewMovimentos.getIdfuncionario();
                    movimentosCollectionNewMovimentos.setIdfuncionario(funcionarios);
                    movimentosCollectionNewMovimentos = em.merge(movimentosCollectionNewMovimentos);
                    if (oldIdfuncionarioOfMovimentosCollectionNewMovimentos != null && !oldIdfuncionarioOfMovimentosCollectionNewMovimentos.equals(funcionarios)) {
                        oldIdfuncionarioOfMovimentosCollectionNewMovimentos.getMovimentosCollection().remove(movimentosCollectionNewMovimentos);
                        oldIdfuncionarioOfMovimentosCollectionNewMovimentos = em.merge(oldIdfuncionarioOfMovimentosCollectionNewMovimentos);
                    }
                }
            }
            em.getTransaction().commit();
            return ret = true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionarios.getIdfuncionario();
                if (findFuncionarios(id) == null) {
                    throw new NonexistentEntityException("The funcionarios with id " + id + " no longer exists.");
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
        boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionarios funcionarios;
            try {
                funcionarios = em.getReference(Funcionarios.class, id);
                funcionarios.getIdfuncionario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Financeiro> financeiroCollectionOrphanCheck = funcionarios.getFinanceiroCollection();
            for (Financeiro financeiroCollectionOrphanCheckFinanceiro : financeiroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionarios (" + funcionarios + ") cannot be destroyed since the Financeiro " + financeiroCollectionOrphanCheckFinanceiro + " in its financeiroCollection field has a non-nullable idfuncionario field.");
            }
            Collection<Movimentos> movimentosCollectionOrphanCheck = funcionarios.getMovimentosCollection();
            for (Movimentos movimentosCollectionOrphanCheckMovimentos : movimentosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionarios (" + funcionarios + ") cannot be destroyed since the Movimentos " + movimentosCollectionOrphanCheckMovimentos + " in its movimentosCollection field has a non-nullable idfuncionario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(funcionarios);
            em.getTransaction().commit();
            return ret = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionarios> findFuncionariosEntities() {
        return findFuncionariosEntities(true, -1, -1);
    }

    public List<Funcionarios> findFuncionariosEntities(int maxResults, int firstResult) {
        return findFuncionariosEntities(false, maxResults, firstResult);
    }

    private List<Funcionarios> findFuncionariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionarios.class));
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

    public Funcionarios findFuncionarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionarios.class, id);
        } finally {
            em.close();
        }
    }

    public Funcionarios findLogin(String usuario) {
        EntityManager em = getEntityManager();
        Funcionarios f;
        try {
            f = (Funcionarios) em.createNamedQuery("Funcionarios.findByUsuario").setParameter("usuario", usuario).getSingleResult();
            return f;
        } finally {
            em.close();
        }
    }

    public int getFuncionariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionarios> rt = cq.from(Funcionarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Funcionarios> findForFind(String campo, String busca) {
        EntityManager em = getEntityManager();
        List<Funcionarios> list = new ArrayList<>();
        if (!busca.isEmpty()) {
            try {
                switch (campo) {
                    case "idfuncionario":
                        list = em.createQuery("SELECT f FROM Funcionarios f WHERE f.idfuncionario = :busca")
                                .setParameter("busca", Integer.valueOf(busca))
                                .getResultList();
                        break;
                    case "nome":
                        list = em.createQuery("SELECT f FROM Funcionarios f WHERE f.nome LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "cpf":
                        list = em.createQuery("SELECT f FROM Funcionarios f WHERE f.cpf LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "cidade":
                        list = em.createQuery("SELECT f FROM Funcionarios f WHERE f.cidade LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "telefones":
                        list = em.createQuery("SELECT f FROM Funcionarios f WHERE f.telefones LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "email":
                        list = em.createQuery("SELECT f FROM Funcionarios f WHERE f.email LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    default:
                        list = em.createQuery("SELECT f FROM Funcionarios f").getResultList();
                }
                return list;
            } finally {
                em.close();
            }
        } else {
            return list = this.findFuncionariosEntities();
        }
    }

}
