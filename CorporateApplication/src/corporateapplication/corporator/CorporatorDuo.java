/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corporateapplication.corporator;

import javafx.collections.ObservableList;

/**
 *
 * @author unique
 */
public interface CorporatorDuo {
    
    public int insert(Corporator corporator);

    public int delete(int customerId);
    
    public int update(Corporator corporator);

    public ObservableList<Corporator> getAllCorporator();
}
