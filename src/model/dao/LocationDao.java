package model.dao;

import java.util.ArrayList;
import org.hibernate.Query;
import model.dto.Location;


public class LocationDao extends Dao<Location> {

    @Override
    public void create(Location loc) {
        try{
            openSession();
            session.save(loc);
            closeSession();
        }catch (Exception e) {
            e.printStackTrace();

        }
        
    }

    @Override
    public Location retrieve(int id) {
        Location loc = new Location();
        try{
            openSession();
            loc = (Location) session.get(Location.class, id);
            closeSession();
        }catch (Exception e) {
            e.printStackTrace();
        }

                return loc;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Location> retrieveAll() {
        openSession();
        Query query = session.createQuery("from Location");
        ArrayList<Location> locs = (ArrayList<Location>) query.list();
        closeSession();

        return locs;
    }

    @Override
    public void update(Location loc) {
        try{
            openSession();
            session.update(loc);
            closeSession();
        }catch (Exception e) {
            e.printStackTrace();

        }           
    }

    @Override
    public void delete(int id) {
        try{
            Location loc = retrieve(id);
            if(loc!=null){
                openSession();
                session.delete(loc);
                closeSession();
            }
        }catch (Exception e) {
            e.printStackTrace();

        }       
    }

}
