import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> kategoriComboBox;
    private JRadioButton rawatInapRadio;
    private JRadioButton rawatJalanRadio;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable personTable;

    private int selectedIndex = -1;
    private Database database;

    public PeopleMenu() {
        database = new Database();

        kategoriComboBox.addItem("???");
        kategoriComboBox.addItem("BPJS");
        kategoriComboBox.addItem("Umum");
        kategoriComboBox.addItem("Asuransi");

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Aplikasi Data Pasien");
        this.pack();
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        deleteButton.setVisible(false);

        personTable.setModel(setTable());

        personTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = personTable.getSelectedRow();

                String curId = personTable.getModel().getValueAt(selectedIndex, 0).toString();
                String curNama = personTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curTahun = personTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curKategori = personTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curStatus = personTable.getModel().getValueAt(selectedIndex, 4).toString();

                idField.setText(curId);
                namaField.setText(curNama);
                tahunLahirField.setText(curTahun);
                kategoriComboBox.setSelectedItem(curKategori);

                if (curStatus.equals("Rawat Inap")) {
                    rawatInapRadio.setSelected(true);
                } else {
                    rawatJalanRadio.setSelected(true);
                }

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
                idField.setEnabled(false);
            }
        });

        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addUpdateButton.getText().equals("Add")) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
    }

    public final DefaultTableModel setTable() {
        Object[] cols = {"ID", "Nama", "Tahun Lahir", "Kategori", "Status Rawat"};
        DefaultTableModel tmp = new DefaultTableModel(null, cols);
        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM pasien");
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getString("id");
                row[1] = resultSet.getString("nama");
                row[2] = resultSet.getString("tahun_lahir");
                row[3] = resultSet.getString("kategori");
                row[4] = resultSet.getString("status_rawat");
                tmp.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }

    public void insertData() {
        String id = idField.getText();
        String nama = namaField.getText();
        String tahunStr = tahunLahirField.getText();
        String kategori = kategoriComboBox.getSelectedItem() != null ? kategoriComboBox.getSelectedItem().toString() : "";
        String statusRawat = rawatInapRadio.isSelected() ? "Rawat Inap" : (rawatJalanRadio.isSelected() ? "Rawat Jalan" : "");

        if (id.isEmpty() || nama.isEmpty() || tahunStr.isEmpty() || kategori.equals("???") || kategori.isEmpty() || statusRawat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Semua kolom data wajib diisi!", "Error Input Kosong", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int tahun = Integer.parseInt(tahunStr);

            ResultSet rs = database.selectQuery("SELECT * FROM pasien WHERE id = '" + id + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Error: ID Pasien '" + id + "' sudah terdaftar!", "Error ID Duplikat", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sqlQuery = "INSERT INTO pasien VALUES('" + id + "', '" + nama + "', " + tahun + ", '" + kategori + "', '" + statusRawat + "')";
            database.insertUpdateDeleteQuery(sqlQuery);

            personTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data pasien berhasil ditambahkan!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Tahun Lahir harus berupa angka!", "Error Tipe Data", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateData() {
        String id = idField.getText();
        String nama = namaField.getText();
        String tahunStr = tahunLahirField.getText();
        String kategori = kategoriComboBox.getSelectedItem() != null ? kategoriComboBox.getSelectedItem().toString() : "";
        String statusRawat = rawatInapRadio.isSelected() ? "Rawat Inap" : (rawatJalanRadio.isSelected() ? "Rawat Jalan" : "");

        if (nama.isEmpty() || tahunStr.isEmpty() || kategori.equals("???") || kategori.isEmpty() || statusRawat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Kolom data tidak boleh dibiarkan kosong!", "Error Update", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int tahun = Integer.parseInt(tahunStr);

            String sqlQuery = "UPDATE pasien SET nama = '" + nama + "', tahun_lahir = " + tahun +
                    ", kategori = '" + kategori + "', status_rawat = '" + statusRawat + "' WHERE id = '" + id + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            personTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Perubahan data pasien berhasil disimpan!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Tahun Lahir harus berupa angka!", "Error Tipe Data", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        String id = idField.getText();

        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data pasien dengan ID " + id + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String sqlQuery = "DELETE FROM pasien WHERE id = '" + id + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            personTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data pasien berhasil dihapus!");
        }
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");

        kategoriComboBox.setSelectedIndex(0);
        rawatInapRadio.setSelected(false);
        rawatJalanRadio.setSelected(false);

        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        idField.setEnabled(true);
        personTable.clearSelection();
        selectedIndex = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PeopleMenu().setVisible(true);
            }
        });
    }
}