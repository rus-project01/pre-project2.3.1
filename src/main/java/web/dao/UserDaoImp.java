package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    @Qualifier("getSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean checkUser(User user) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where name=:name and age=:age and street=:street")
                .setParameter("name", user.getName())
                .setParameter("age", user.getAge())
                .setParameter("street", user.getStreet());
        return query != null;
    }

    @Override
    public void updateUser(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("update User set name=:name, age=:age, street=:street where name=:name");
        query.setParameter("name", user.getName());
        query.setParameter("age", user.getAge());
        query.setParameter("street", user.getStreet());
        query.setParameter("name", user.getName());
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserByName(User user) {
        TypedQuery<User> query =  sessionFactory.getCurrentSession().createQuery("from User where name=:name");
        query.setParameter("name", user.getName());
        return query.getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        User ww = sessionFactory.getCurrentSession().get(User.class, id);
        sessionFactory.getCurrentSession().delete(ww);
    }

}
