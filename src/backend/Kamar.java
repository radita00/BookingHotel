package backend;

import java.sql.*;
import java.util.ArrayList;

public class Kamar {
    private int idKamar;
    private String nomorKamar;
    private String tipe;
    private double harga;
    private String status;

    // ======== GETTER - SETTER ========
    public int getIdKamar() {
        return idKamar;
    }

    public void setIdKamar(int idKamar) {
        this.idKamar = idKamar;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // ===============================
    // GET BY ID
    // ===============================
    public Kamar getById(int id) {
        Kamar km = new Kamar();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kamar WHERE id_kamar = " + id);

        try {
            while (rs.next()) {
                km.setIdKamar(rs.getInt("id_kamar"));
                km.setNomorKamar(rs.getString("nomor_kamar"));
                km.setTipe(rs.getString("tipe"));
                km.setHarga(rs.getDouble("harga"));
                km.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return km;
    }


    // ===============================
    // GET ALL
    // ===============================
    public ArrayList<Kamar> getAll() {
        ArrayList<Kamar> list = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kamar ORDER BY id_kamar");

        try {
            while (rs.next()) {
                Kamar km = new Kamar();
                km.setIdKamar(rs.getInt("id_kamar"));
                km.setNomorKamar(rs.getString("nomor_kamar"));
                km.setTipe(rs.getString("tipe"));
                km.setHarga(rs.getDouble("harga"));
                km.setStatus(rs.getString("status"));
                list.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // ===============================
    // INSERT
    // ===============================
    public void insert() {
        String SQL = "INSERT INTO kamar (nomor_kamar, tipe, harga, status) VALUES ("
                + "'" + this.nomorKamar + "', "
                + "'" + this.tipe + "', "
                + "'" + this.harga + "', "
                + "'" + this.status + "' "
                + ")";

        this.idKamar = DBHelper.insertQueryGetId(SQL);
    }

    // ===============================
    // UPDATE
    // ===============================
    public void update() {
        String SQL = "UPDATE kamar SET "
                + "nomor_kamar = '" + this.nomorKamar + "', "
                + "tipe = '" + this.tipe + "', "
                + "harga = '" + this.harga + "', "
                + "status = '" + this.status + "' "
                + "WHERE id_kamar = '" + this.idKamar + "'";

        DBHelper.executeQuery(SQL);
    }

    // ===============================
    // DELETE
    // ===============================
    public void delete() {
        String SQL = "DELETE FROM kamar WHERE id_kamar = '" + this.idKamar + "'";
        DBHelper.executeQuery(SQL);
    }


    // ===============================
    // SAVE (INSERT / UPDATE)
    // ===============================
    public void save() {
        if (getById(idKamar).getIdKamar() == 0) {
            insert();
        } else {
            update();
        }
    }
}
