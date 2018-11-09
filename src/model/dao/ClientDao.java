package model.dao;

import java.util.List;
import org.hibernate.Query;
import model.dto.Client;

public class ClientDao extends Dao<Client> {

    
    public ClientDao() {
        super();
    }

    @Override
    public void create(Client Client) {
        try{
            openSession();
            session.save(Client);
            closeSession();
        }catch (Exception e) {
            e.printStackTrace();

        }
        
    }

    @Override
    public Client retrieve(int id) {
        Client Client = new Client();
        try{
            openSession();
            Client = (Client) session.get(Client.class, id);
            closeSession();
        }catch (Exception e) {
            e.printStackTrace();
        }

                return Client;
    }
    
    @SuppressWarnings("unchecked")
    public boolean ClientExist(String firstName, String password){
        openSession();
        Query query = session.createQuery("from Client where firstName=? and password=?");
        query.setString(0, firstName);
        query.setString(1, password);
        List<Client> Clients = query.list();
        closeSession();
        return Clients.size()>0;
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Client> retrieveAll() {
        List<Client> Clients =null;
        try{
        openSession();
        
        Query query = session.createQuery("from Client");
        Clients = query.list();
        
        closeSession();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Clients;
    }

    @Override
    public void update(Client Client) {
        try{
            openSession();
            session.update(Client);
            closeSession();
        }catch (Exception e) {
            e.printStackTrace();
        }       
    }

    @Override
    public void delete(int id) {
        Client Client = new Client();
        Client.setIdClient(id);
        try{
            openSession();
            session.delete(Client);
            closeSession();
        }catch (Exception e) {
            e.printStackTrace();
        }       
    }

}
