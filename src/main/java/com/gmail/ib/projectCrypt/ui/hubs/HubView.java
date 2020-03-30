package com.gmail.ib.projectCrypt.ui.hubs;

import com.gmail.ib.projectCrypt.backend.cryptData.SQLConnector;
import com.gmail.ib.projectCrypt.backend.cryptData.cryptCurrencyPriceData;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.gmail.ib.projectCrypt.ui.MainLayout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Route(value = "Hubs", layout = MainLayout.class)
@PageTitle("Hubs")
public class HubView extends VerticalLayout {

    public HubView() throws SQLException {

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.STRETCH);

        //making a grid list

        //now for the grid list below
        List<cryptCurrencyPriceData> coinList = new ArrayList<>();
        SQLConnector connect = new SQLConnector();
        Statement stmt = connect.SQLConnection();
        String extract = "SELECT * FROM coins";
        ResultSet rs = stmt.executeQuery(extract);
        while(rs.next()) {
            coinList.add(new cryptCurrencyPriceData(rs.getString("name"), rs.getDouble("price"),
                    rs.getString("symbol"), rs.getString("slug"), rs.getString("date_added"),
                    rs.getDouble("volume_24h"), rs.getDouble("percent_change_1h"),
                    rs.getDouble("percent_change_24h"), rs.getDouble("percent_change_7d"), rs.getDouble("market_cap")));
        }

        Grid<cryptCurrencyPriceData> coinGrid = new Grid<>(cryptCurrencyPriceData.class);
        coinGrid.setItems(coinList);

        coinGrid.removeColumnByKey("slug");
        coinGrid.removeColumnByKey("dateAdded");
        coinGrid.setColumns("name", "symbol", "price", "volume_24h", "percent_change_1h", "percentChange_24h", "percent_change_7d", "market_cap");

        coinGrid.setSizeFull();
        coinGrid.setHeightFull();

        coinGrid.setHeightByRows(true);

        coinGrid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS, GridVariant.LUMO_NO_BORDER);

        add(coinGrid);

    }

}
