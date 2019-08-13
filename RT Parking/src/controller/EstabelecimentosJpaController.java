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
import model.entidade.Estabelecimentos;
import model.entidade.Financeiro;
import model.entidade.Movimentos;

/**
 *
 * @author ronan
 */
public class EstabelecimentosJpaController implements Serializable {

    public EstabelecimentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Estabelecimentos estabelecimentos) {
        boolean ret = false;
        if (estabelecimentos.getConveniosCollection() == null) {
            estabelecimentos.setConveniosCollection(new ArrayList<Convenios>());
        }
        if (estabelecimentos.getFinanceiroCollection() == null) {
            estabelecimentos.setFinanceiroCollection(new ArrayList<Financeiro>());
        }
        if (estabelecimentos.getMovimentosCollection() == null) {
            estabelecimentos.setMovimentosCollection(new ArrayList<Movimentos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Convenios> attachedConveniosCollection = new ArrayList<Convenios>();
            for (Convenios conveniosCollectionConveniosToAttach : estabelecimentos.getConveniosCollection()) {
                conveniosCollectionConveniosToAttach = em.getReference(conveniosCollectionConveniosToAttach.getClass(), conveniosCollectionConveniosToAttach.getIdconvenio());
                attachedConveniosCollection.add(conveniosCollectionConveniosToAttach);
            }
            estabelecimentos.setConveniosCollection(attachedConveniosCollection);
            Collection<Financeiro> attachedFinanceiroCollection = new ArrayList<Financeiro>();
            for (Financeiro financeiroCollectionFinanceiroToAttach : estabelecimentos.getFinanceiroCollection()) {
                financeiroCollectionFinanceiroToAttach = em.getReference(financeiroCollectionFinanceiroToAttach.getClass(), financeiroCollectionFinanceiroToAttach.getIdfinanceiro());
                attachedFinanceiroCollection.add(financeiroCollectionFinanceiroToAttach);
            }
            estabelecimentos.setFinanceiroCollection(attachedFinanceiroCollection);
            Collection<Movimentos> attachedMovimentosCollection = new ArrayList<Movimentos>();
            for (Movimentos movimentosCollectionMovimentosToAttach : estabelecimentos.getMovimentosCollection()) {
                movimentosCollectionMovimentosToAttach = em.getReference(movimentosCollectionMovimentosToAttach.getClass(), movimentosCollectionMovimentosToAttach.getIdmovimento());
                attachedMovimentosCollection.add(movimentosCollectionMovimentosToAttach);
            }
            estabelecimentos.setMovimentosCollection(attachedMovimentosCollection);
            em.persist(estabelecimentos);
            for (Convenios conveniosCollectionConvenios : estabelecimentos.getConveniosCollection()) {
                Estabelecimentos oldIdestabelecimentoOfConveniosCollectionConvenios = conveniosCollectionConvenios.getIdestabelecimento();
                conveniosCollectionConvenios.setIdestabelecimento(estabelecimentos);
                conveniosCollectionConvenios = em.merge(conveniosCollectionConvenios);
                if (oldIdestabelecimentoOfConveniosCollectionConvenios != null) {
                    oldIdestabelecimentoOfConveniosCollectionConvenios.getConveniosCollection().remove(conveniosCollectionConvenios);
                    oldIdestabelecimentoOfConveniosCollectionConvenios = em.merge(oldIdestabelecimentoOfConveniosCollectionConvenios);
                }
            }
            for (Financeiro financeiroCollectionFinanceiro : estabelecimentos.getFinanceiroCollection()) {
                Estabelecimentos oldIdestabelecimentoOfFinanceiroCollectionFinanceiro = financeiroCollectionFinanceiro.getIdestabelecimento();
                financeiroCollectionFinanceiro.setIdestabelecimento(estabelecimentos);
                financeiroCollectionFinanceiro = em.merge(financeiroCollectionFinanceiro);
                if (oldIdestabelecimentoOfFinanceiroCollectionFinanceiro != null) {
                    oldIdestabelecimentoOfFinanceiroCollectionFinanceiro.getFinanceiroCollection().remove(financeiroCollectionFinanceiro);
                    oldIdestabelecimentoOfFinanceiroCollectionFinanceiro = em.merge(oldIdestabelecimentoOfFinanceiroCollectionFinanceiro);
                }
            }
            for (Movimentos movimentosCollectionMovimentos : estabelecimentos.getMovimentosCollection()) {
                Estabelecimentos oldIdestabelecimentoOfMovimentosCollectionMovimentos = movimentosCollectionMovimentos.getIdestabelecimento();
                movimentosCollectionMovimentos.setIdestabelecimento(estabelecimentos);
                movimentosCollectionMovimentos = em.merge(movimentosCollectionMovimentos);
                if (oldIdestabelecimentoOfMovimentosCollectionMovimentos != null) {
                    oldIdestabelecimentoOfMovimentosCollectionMovimentos.getMovimentosCollection().remove(movimentosCollectionMovimentos);
                    oldIdestabelecimentoOfMovimentosCollectionMovimentos = em.merge(oldIdestabelecimentoOfMovimentosCollectionMovimentos);
                }
            }
            ret = true;
            em.getTransaction().commit();
            return ret;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(Estabelecimentos estabelecimentos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estabelecimentos persistentEstabelecimentos = em.find(Estabelecimentos.class, estabelecimentos.getIdestabelecimento());
            Collection<Convenios> conveniosCollectionOld = persistentEstabelecimentos.getConveniosCollection();
            Collection<Convenios> conveniosCollectionNew = estabelecimentos.getConveniosCollection();
            Collection<Financeiro> financeiroCollectionOld = persistentEstabelecimentos.getFinanceiroCollection();
            Collection<Financeiro> financeiroCollectionNew = estabelecimentos.getFinanceiroCollection();
            Collection<Movimentos> movimentosCollectionOld = persistentEstabelecimentos.getMovimentosCollection();
            Collection<Movimentos> movimentosCollectionNew = estabelecimentos.getMovimentosCollection();
            List<String> illegalOrphanMessages = null;
            for (Convenios conveniosCollectionOldConvenios : conveniosCollectionOld) {
                if (!conveniosCollectionNew.contains(conveniosCollectionOldConvenios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Convenios " + conveniosCollectionOldConvenios + " since its idestabelecimento field is not nullable.");
                }
            }
            for (Financeiro financeiroCollectionOldFinanceiro : financeiroCollectionOld) {
                if (!financeiroCollectionNew.contains(financeiroCollectionOldFinanceiro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Financeiro " + financeiroCollectionOldFinanceiro + " since its idestabelecimento field is not nullable.");
                }
            }
            for (Movimentos movimentosCollectionOldMovimentos : movimentosCollectionOld) {
                if (!movimentosCollectionNew.contains(movimentosCollectionOldMovimentos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimentos " + movimentosCollectionOldMovimentos + " since its idestabelecimento field is not nullable.");
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
            estabelecimentos.setConveniosCollection(conveniosCollectionNew);
            Collection<Financeiro> attachedFinanceiroCollectionNew = new ArrayList<Financeiro>();
            for (Financeiro financeiroCollectionNewFinanceiroToAttach : financeiroCollectionNew) {
                financeiroCollectionNewFinanceiroToAttach = em.getReference(financeiroCollectionNewFinanceiroToAttach.getClass(), financeiroCollectionNewFinanceiroToAttach.getIdfinanceiro());
                attachedFinanceiroCollectionNew.add(financeiroCollectionNewFinanceiroToAttach);
            }
            financeiroCollectionNew = attachedFinanceiroCollectionNew;
            estabelecimentos.setFinanceiroCollection(financeiroCollectionNew);
            Collection<Movimentos> attachedMovimentosCollectionNew = new ArrayList<Movimentos>();
            for (Movimentos movimentosCollectionNewMovimentosToAttach : movimentosCollectionNew) {
                movimentosCollectionNewMovimentosToAttach = em.getReference(movimentosCollectionNewMovimentosToAttach.getClass(), movimentosCollectionNewMovimentosToAttach.getIdmovimento());
                attachedMovimentosCollectionNew.add(movimentosCollectionNewMovimentosToAttach);
            }
            movimentosCollectionNew = attachedMovimentosCollectionNew;
            estabelecimentos.setMovimentosCollection(movimentosCollectionNew);
            estabelecimentos = em.merge(estabelecimentos);
            for (Convenios conveniosCollectionNewConvenios : conveniosCollectionNew) {
                if (!conveniosCollectionOld.contains(conveniosCollectionNewConvenios)) {
                    Estabelecimentos oldIdestabelecimentoOfConveniosCollectionNewConvenios = conveniosCollectionNewConvenios.getIdestabelecimento();
                    conveniosCollectionNewConvenios.setIdestabelecimento(estabelecimentos);
                    conveniosCollectionNewConvenios = em.merge(conveniosCollectionNewConvenios);
                    if (oldIdestabelecimentoOfConveniosCollectionNewConvenios != null && !oldIdestabelecimentoOfConveniosCollectionNewConvenios.equals(estabelecimentos)) {
                        oldIdestabelecimentoOfConveniosCollectionNewConvenios.getConveniosCollection().remove(conveniosCollectionNewConvenios);
                        oldIdestabelecimentoOfConveniosCollectionNewConvenios = em.merge(oldIdestabelecimentoOfConveniosCollectionNewConvenios);
                    }
                }
            }
            for (Financeiro financeiroCollectionNewFinanceiro : financeiroCollectionNew) {
                if (!financeiroCollectionOld.contains(financeiroCollectionNewFinanceiro)) {
                    Estabelecimentos oldIdestabelecimentoOfFinanceiroCollectionNewFinanceiro = financeiroCollectionNewFinanceiro.getIdestabelecimento();
                    financeiroCollectionNewFinanceiro.setIdestabelecimento(estabelecimentos);
                    financeiroCollectionNewFinanceiro = em.merge(financeiroCollectionNewFinanceiro);
                    if (oldIdestabelecimentoOfFinanceiroCollectionNewFinanceiro != null && !oldIdestabelecimentoOfFinanceiroCollectionNewFinanceiro.equals(estabelecimentos)) {
                        oldIdestabelecimentoOfFinanceiroCollectionNewFinanceiro.getFinanceiroCollection().remove(financeiroCollectionNewFinanceiro);
                        oldIdestabelecimentoOfFinanceiroCollectionNewFinanceiro = em.merge(oldIdestabelecimentoOfFinanceiroCollectionNewFinanceiro);
                    }
                }
            }
            for (Movimentos movimentosCollectionNewMovimentos : movimentosCollectionNew) {
                if (!movimentosCollectionOld.contains(movimentosCollectionNewMovimentos)) {
                    Estabelecimentos oldIdestabelecimentoOfMovimentosCollectionNewMovimentos = movimentosCollectionNewMovimentos.getIdestabelecimento();
                    movimentosCollectionNewMovimentos.setIdestabelecimento(estabelecimentos);
                    movimentosCollectionNewMovimentos = em.merge(movimentosCollectionNewMovimentos);
                    if (oldIdestabelecimentoOfMovimentosCollectionNewMovimentos != null && !oldIdestabelecimentoOfMovimentosCollectionNewMovimentos.equals(estabelecimentos)) {
                        oldIdestabelecimentoOfMovimentosCollectionNewMovimentos.getMovimentosCollection().remove(movimentosCollectionNewMovimentos);
                        oldIdestabelecimentoOfMovimentosCollectionNewMovimentos = em.merge(oldIdestabelecimentoOfMovimentosCollectionNewMovimentos);
                    }
                }
            }
            em.getTransaction().commit();
            return ret = true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estabelecimentos.getIdestabelecimento();
                if (findEstabelecimentos(id) == null) {
                    throw new NonexistentEntityException("The estabelecimentos with id " + id + " no longer exists.");
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
        EntityManager em = null;
        boolean ret = false;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estabelecimentos estabelecimentos;
            try {
                estabelecimentos = em.getReference(Estabelecimentos.class, id);
                estabelecimentos.getIdestabelecimento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estabelecimentos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Convenios> conveniosCollectionOrphanCheck = estabelecimentos.getConveniosCollection();
            for (Convenios conveniosCollectionOrphanCheckConvenios : conveniosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estabelecimentos (" + estabelecimentos + ") cannot be destroyed since the Convenios " + conveniosCollectionOrphanCheckConvenios + " in its conveniosCollection field has a non-nullable idestabelecimento field.");
            }
            Collection<Financeiro> financeiroCollectionOrphanCheck = estabelecimentos.getFinanceiroCollection();
            for (Financeiro financeiroCollectionOrphanCheckFinanceiro : financeiroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estabelecimentos (" + estabelecimentos + ") cannot be destroyed since the Financeiro " + financeiroCollectionOrphanCheckFinanceiro + " in its financeiroCollection field has a non-nullable idestabelecimento field.");
            }
            Collection<Movimentos> movimentosCollectionOrphanCheck = estabelecimentos.getMovimentosCollection();
            for (Movimentos movimentosCollectionOrphanCheckMovimentos : movimentosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estabelecimentos (" + estabelecimentos + ") cannot be destroyed since the Movimentos " + movimentosCollectionOrphanCheckMovimentos + " in its movimentosCollection field has a non-nullable idestabelecimento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estabelecimentos);
            em.getTransaction().commit();
            return ret = true;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estabelecimentos> findEstabelecimentosEntities() {
        return findEstabelecimentosEntities(true, -1, -1);
    }

    public List<Estabelecimentos> findEstabelecimentosEntities(int maxResults, int firstResult) {
        return findEstabelecimentosEntities(false, maxResults, firstResult);
    }

    private List<Estabelecimentos> findEstabelecimentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estabelecimentos.class));
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

    public Estabelecimentos findEstabelecimentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estabelecimentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstabelecimentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estabelecimentos> rt = cq.from(Estabelecimentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Estabelecimentos> findForFind(String campo, String busca) {
        EntityManager em = getEntityManager();
        List<Estabelecimentos> list = new ArrayList<>();
        if (!busca.isEmpty()) {
            try {
                switch (campo) {
                    case "idestabelecimento":
                        list = em.createQuery("SELECT e FROM Estabelecimentos e WHERE e.idestabelecimento = :busca")
                                .setParameter("busca", Integer.valueOf(busca))
                                .getResultList();
                        break;
                    case "nome":
                        list = em.createQuery("SELECT e FROM Estabelecimentos e WHERE e.nome LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "cnpj":
                        list = em.createQuery("SELECT e FROM Estabelecimentos e WHERE e.cnpj LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "cidade":
                        list = em.createQuery("SELECT e FROM Estabelecimentos e WHERE e.cidade LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "telefones":
                        list = em.createQuery("SELECT e FROM Estabelecimentos e WHERE e.telefones LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    case "email":
                        list = em.createQuery("SELECT e FROM Estabelecimentos e WHERE e.email LIKE :busca")
                                .setParameter("busca", "%" + busca + "%")
                                .getResultList();
                        break;
                    default:
                        list = em.createQuery("SELECT e FROM Estabelecimentos e").getResultList();
                }
                return list;
            } finally {
                em.close();
            }
        } else {
            return list = this.findEstabelecimentosEntities();
        }
    }
}
