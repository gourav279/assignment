package corporateapplication.corporator;

import corporateapplication.dbsource.DBSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CorporatorImpl implements CorporatorDuo {
    

    @Override
    public int insert(Corporator corporator) {
        int i = 0;
        Connection con = DBSource.connectDb();
        String query = "insert into corporate(corporateName, corporateId, accountantNumbers) values (?,?,?)";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, corporator.getCorporateName());
            pst.setString(2, corporator.getCorporateId());
            pst.setInt(3, Integer.parseInt(corporator.getAccountantNumbers()));
            i = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return i;
    }

    @Override
    public int delete(int id) {
        int i = 0;
        Connection con = DBSource.connectDb();
        String query = "delete from corporate where id=" + id;
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            i = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return i;
    }

    @Override
    public int update(Corporator corporator) {
        int i = 0;
        Connection con = DBSource.connectDb();
        String query = "update corporate set corporateName='" + corporator.getCorporateName() + "', corporateId='" + corporator.getCorporateId() + "', accountantNumbers=" + corporator.getAccountantNumbers() + "";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            i = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return i;
    }

    @Override
    public ObservableList<Corporator> getAllCorporator() {
        Connection con = DBSource.connectDb();
        String query = "select * from corporate";
        ObservableList<Corporator> list = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Corporator(rs.getString("id"), rs.getString("corporateName"), rs.getString("corporateId"), rs.getString("accountantNumbers")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

}
