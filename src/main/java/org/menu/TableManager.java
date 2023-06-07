package org.menu;

import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private List<Table> tables;

    public TableManager() {
        this.tables = new ArrayList<>();
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void removeTable(Table table) {
        tables.remove(table);
    }

    public List<Table> getAllTables() {
        return tables;
    }

    public Table getTableById(int tableId) {
        for (Table table : tables) {
            if (table.getTableId() == tableId) {
                return table;
            }
        }
        return null; // Table not found
    }

    public void assignCustomerToTable(int tableId) {
        Table table = getTableById(tableId);
        if (table != null) {
            table.setStatus("Occupied");
        }
    }

    public void reserveTable(int tableId) {
        Table table = getTableById(tableId);
        if (table != null) {
            table.setStatus("Reserved");
        }
    }

    public void releaseTable(int tableId) {
        Table table = getTableById(tableId);
        if (table != null) {
            table.setStatus("Available");
        }
    }

    public void displayOccupiedTables() {
        System.out.println("Occupied Tables:");
        for (Table table : tables) {
            if (table.getStatus().equals("Occupied")) {
                System.out.println("Table ID: " + table.getTableId() + ", Table Size: " + table.getTableSize());
            }
        }
    }

}

