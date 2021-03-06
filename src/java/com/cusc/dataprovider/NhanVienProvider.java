/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.dataprovider;

import com.cusc.model.NhanVienModel;
import com.cusc.util.HibernateUtil;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author thyen
 */
public class NhanVienProvider implements Serializable {  
    
    public List<Map> getListNhanVien(){
        Session session = HibernateUtil.currentSession();
        List<Map> listNhanVien = new ArrayList();
        try {
            session.beginTransaction();
            listNhanVien = session.createSQLQuery("SELECT nv.* ,pb_ten FROM nhanvien nv INNER JOIN phongban pb ON pb.pb_id = nv.pb_id").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            session.getTransaction().commit();
            for(Map dmNhanVien : listNhanVien){
                dmNhanVien.put("editTen", false);
            }
	} catch (Exception e) {
            e.printStackTrace();
	} finally {
            session.close();
	}
        return listNhanVien;
    }
    
    public List<Map> getListNhanVienTheoPhongBan(int pbID){
        Session session = HibernateUtil.currentSession();
        List<Map> listNhanVien = new ArrayList();
        try {
            session.beginTransaction();
            listNhanVien = session.createSQLQuery("SELECT nv.* ,pb_ten"
                    + " FROM nhanvien nv"
                    + " INNER JOIN phongban pb ON pb.pb_id = nv.pb_id"
                    + " WHERE pb.pb_id = "+pbID).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            session.getTransaction().commit();
	} catch (Exception e) {
            e.printStackTrace();
	} finally {
            session.close();
	}
        return listNhanVien;
    }

}
