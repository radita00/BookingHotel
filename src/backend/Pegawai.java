package backend;

import java.sql.ResultSet;

public class Pegawai {
    
    public static boolean login(String username, String password){
        boolean status = false;
        
        String query = "SELECT * FROM pegawai WHERE username = '"
                + username + "' AND password = '" + password + "'";
        
        ResultSet rs = DBHelper.selectQuery(query);
        
        try{
            if(rs.next()){
                status = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
}
