package si.fri.rso.skupina3.user.services.beans;

import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.skupina3.lib.User;
import si.fri.rso.skupina3.user.models.converters.UserConverter;
import si.fri.rso.skupina3.user.models.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class UserBean {
    private Logger log = Logger.getLogger(UserBean.class.getName());

    @Inject
    private EntityManager em;

    @Timed
    public List<User> getUser() {

        TypedQuery<UserEntity> query = em.createNamedQuery(
                "UserEntity.getAll", UserEntity.class);

        List<UserEntity> resultList = query.getResultList();

        return resultList.stream().map(UserConverter::toDto).collect(Collectors.toList());

    }

    public User getByGoogleId(String googleId) {
        Object userEntity = new Object();
        try{
        userEntity = em.createQuery("SELECT u FROM UserEntity u where u.googleId = :googleIdParam")
                .setParameter("googleIdParam", googleId).getSingleResult();
        } catch (NoResultException noResult){
            userEntity = null;
        }


        return userEntity == null ? null : UserConverter.toDto((UserEntity) userEntity);

    }

    public User createUser(User user) {

        UserEntity userEntity = UserConverter.toEntity(user);

        try {
            beginTx();
            em.persist(userEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (userEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return UserConverter.toDto(userEntity);
    }

    public User putUser(Integer id, User user) {

        UserEntity c = em.find(UserEntity.class, id);

        if (c == null) {
            return null;
        }

        UserEntity updatedUserEntity = UserConverter.toEntity(user);

        try {
            beginTx();
            updatedUserEntity.setId(c.getId());
            updatedUserEntity = em.merge(updatedUserEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return UserConverter.toDto(updatedUserEntity);
    }

    public boolean deleteImageMetadata(Integer id) {

        UserEntity user = em.find(UserEntity.class, id);

        if (user != null) {
            try {
                beginTx();
                em.remove(user);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}


