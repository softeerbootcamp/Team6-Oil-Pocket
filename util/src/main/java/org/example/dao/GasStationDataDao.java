package org.example.dao;

import org.example.domain.GasDetail;
import org.example.domain.GasStation;

import java.io.*;
import java.sql.*;
import java.util.List;

import static org.example.FileUploadService.PATH_PREFIX;

public class GasStationDataDao {
    private String url;
    private String username;
    private String password;

    public GasStationDataDao() {
        File file = new File(PATH_PREFIX + "datasource.txt");
        BufferedReader br = null;
        String[] info = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
            info = br.readLine().split(",");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void insertGasDetail(GasDetail gasDetail) {
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement("INSERT INTO gas_detail(address, brand, gas_type, price, created_date)" +
                     "values (?, ?, ?, ?, ?)")) {
            ps.setString(1, gasDetail.getAddress());
            ps.setString(2, gasDetail.getBrand());
            ps.setString(3, gasDetail.getGasType().name());
            ps.setInt(4, gasDetail.getPrice());
            ps.setObject(5, gasDetail.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertGasDetails(List<GasDetail> list) {
        for (GasDetail gasDetail : list) {
            insertGasDetail(gasDetail);
        }
    }
    public GasStation selectGasStation(GasStation gasStation) {
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement("SELECT * FROM gas_station where address like ? and " +
                     "brand like ?");
             ) {
            ps.setString(1, '%' + gasStation.getAddress());
            ps.setString(2, '%' + gasStation.getBrand());
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return new GasStation(rs.getString("station_no"), rs.getString("area"),
                            rs.getString("name"), rs.getString("address"), rs.getString("brand"), rs.getBoolean("is_self"));
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
