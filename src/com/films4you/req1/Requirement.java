package com.films4you.example;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Requirement implements RequirementInterface {

  // Implementation for Requirement 1

  // Proto-Personas
  private static final String PERSONA_ALEX = "Alex Johnson";
  private static final String PERSONA_LISA = "Lisa Miller";

  // User Stories
  private static final String STORY_ALEX = "As " + PERSONA_ALEX + ", I want to know which store has the most inventory so that I can ensure that our insurance coverage is adequate.";
  private static final String STORY_LISA = "As " + PERSONA_LISA + ", I want to identify the store with the most inventory so that I can redistribute excess stock to other stores as needed.";

  /**
   * Get the store with the most inventory.
   *
   * @param stores List of stores to search through.
   * @return the store with the largest stock count.
   * @throws SQLException on database error.
   */
  private Store getStoreWithMostInventory(List<Store> stores) throws SQLException {
    Store storeWithMostStock = stores.get(0);

    for (Store store : stores) {
      if (store.getStockCount() > storeWithMostStock.getStockCount()) {
        storeWithMostStock = store;
      }
    }

    return storeWithMostStock;
  }

  /**
   * Get the list of stores with their addresses and inventory counts.
   *
   * @return List of stores.
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  private List<Store> getStores() throws SQLException, IllegalStateException {
    Database db = new Database();
    Map<Integer, Store> stores = new HashMap<>();
    Map<Integer, Address> addresses = new HashMap<>();

    // Retrieve addresses and add them to the ID-to-address map.
    ResultSet rs = db.query("SELECT * FROM address");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }

    while (rs.next()) {
      String addrLine1 = rs.getString(2);
      if (addrLine1 != null) {
        addresses.put(rs.getInt(1), new Address(addrLine1, rs.getString(3), rs.getString(6)));
      }
    }

    // Retrieve stores and add them to the ID-to-store map.
    rs = db.query("SELECT * FROM store");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }

    while (rs.next()) {
      Store newStore = new Store(rs.getInt(1));

      // Set the store's address.
      newStore.setAddress(addresses.get(rs.getInt(3)));

      stores.put(newStore.getStoreId(), newStore);
    }

    // For each inventory record, update the corresponding store's inventory counter.
    rs = db.query("SELECT * FROM inventory");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }

    while (rs.next()) {
      Store store = stores.get(rs.getInt(3));
      if (store != null) {
        store.addStockItem();
      }
    }

    db.close(); // Close the database connection.
    return new ArrayList<>(stores.values());
  }

  /**
   * Get the store with the most inventory and its location.
   *
   * @return A string containing the address of the store with the most inventory and its inventory count, separated by a comma. In the format "[ADDRESS]:[COUNT]"
   */
  @Override
  public @Nullable String getValueAsString() {
    try {
      Store mostInventory = getStoreWithMostInventory(getStores());
      return mostInventory.getAddress() + ":" + mostInventory.getStockCount();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Get the human-readable representation of the store with the most inventory.
   *
   * @return A string formatted for the end user containing the store with the most inventory -- its address and inventory count. In the format "The store with the most inventory is on [ADDRESS] with [COUNT] items"
   */
  @Override
  public @NonNull String getHumanReadable() {
    String value = getValueAsString();
    if (value == null) {
      return "Sorry, no results found or an error occurred.";
    }

    String result = "The store with the most inventory is on ";
    result += value.split(":")[0];
    result += " with ";
    result += value.split(":")[1];
    result += " items";

    return result;
  }

}
