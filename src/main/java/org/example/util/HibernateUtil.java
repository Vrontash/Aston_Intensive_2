package org.example.util;

import org.example.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                //hibernate.properties будет выбран по умолчанию, если не указан другой конфиг при создании sr
                ServiceRegistry sr = new StandardServiceRegistryBuilder().build();
                MetadataSources metadataSources = new MetadataSources(sr);

                //Подключение классов
                metadataSources.addAnnotatedClass(User.class);

                Metadata metadata = metadataSources.buildMetadata();
                sessionFactory = metadata.buildSessionFactory();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static void closeSessionFactory(){
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
