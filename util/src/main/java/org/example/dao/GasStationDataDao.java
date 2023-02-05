package org.example.dao;

import org.example.domain.GasStation;

import java.io.*;
import java.sql.*;
import java.util.List;

import static org.example.FileUploadService.PATH_PREFIX;

public class GasStationDataDao {
    private String url;
    private String username;
    private String password;

    public GasStationDataDao() throws IOException {
        File file = new File(PATH_PREFIX + "datasource.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
        String[] info = br.readLine().split(",");
        this.url = info[0];
        this.username = info[1];
        this.password = info[2];
    }

    public void insertGasStation(GasStation gasStation) {
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement("INSERT INTO gas_station(station_no, area, name, address, brand, is_self)" +
                     "values (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, gasStation.getGasStationNo());
            ps.setString(2, gasStation.getArea());
            ps.setString(3, gasStation.getName());
            ps.setString(4, gasStation.getAddress());
            ps.setString(5, gasStation.getBrand());
            ps.setBoolean(6, gasStation.getIsSelf());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
