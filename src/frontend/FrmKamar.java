package frontend;

import backend.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmKamar extends javax.swing.JFrame {

    public FrmKamar() {
        initComponents();
        tampilkanData();
        kosongkanForm();
    }

    public void kosongkanForm() {
        txtIdKamar.setText("0");
        txtNomor.setText("");
        txtTipe.setText("");
        txtHarga.setText("");
        cmbStatus.setSelectedItem("kosong");
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Nomor", "Tipe", "Harga", "Status"};
        ArrayList<Kamar> list = new Kamar().getAll();
        Object rowData[] = new Object[5];

        tblKamar.setModel(new DefaultTableModel(new Object[][]{}, kolom));
        for (Kamar km : list) {
            rowData[0] = km.getIdKamar();
            rowData[1] = km.getNomorKamar();
            rowData[2] = km.getTipe();
            rowData[3] = km.getHarga();
            rowData[4] = km.getStatus();
            ((DefaultTableModel) tblKamar.getModel()).addRow(rowData);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Form Data Kamar");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblId = new JLabel("ID Kamar:");
        JLabel lblNomor = new JLabel("Nomor Kamar:");
        JLabel lblTipe = new JLabel("Tipe:");
        JLabel lblHarga = new JLabel("Harga / Malam:");
        JLabel lblStatus = new JLabel("Status:");

        txtIdKamar = new JTextField();
        txtNomor = new JTextField();
        txtTipe = new JTextField();
        txtHarga = new JTextField();

        txtIdKamar.setEnabled(false);

        cmbStatus = new JComboBox<>(new String[]{"kosong", "terisi", "maintenance"});

        btnSimpan = new JButton("Simpan");
        btnTambahBaru = new JButton("Tambah Baru");
        btnHapus = new JButton("Hapus");

        tblKamar = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblKamar);

        setLayout(null);

        lblId.setBounds(20, 20, 100, 25);
        txtIdKamar.setBounds(130, 20, 100, 25);

        lblNomor.setBounds(20, 55, 100, 25);
        txtNomor.setBounds(130, 55, 150, 25);

        lblTipe.setBounds(20, 90, 100, 25);
        txtTipe.setBounds(130, 90, 150, 25);

        lblHarga.setBounds(20, 125, 100, 25);
        txtHarga.setBounds(130, 125, 150, 25);

        lblStatus.setBounds(20, 160, 100, 25);
        cmbStatus.setBounds(130, 160, 150, 25);

        btnTambahBaru.setBounds(320, 20, 120, 30);
        btnSimpan.setBounds(320, 60, 120, 30);
        btnHapus.setBounds(320, 100, 120, 30);

        scrollPane.setBounds(20, 210, 540, 180);

        add(lblId); add(txtIdKamar);
        add(lblNomor); add(txtNomor);
        add(lblTipe); add(txtTipe);
        add(lblHarga); add(txtHarga);
        add(lblStatus); add(cmbStatus);
        add(btnTambahBaru); add(btnSimpan); add(btnHapus);
        add(scrollPane);

        btnTambahBaru.addActionListener(e -> kosongkanForm());

        btnSimpan.addActionListener(e -> {
            Kamar km = new Kamar();
            km.setIdKamar(Integer.parseInt(txtIdKamar.getText()));
            km.setNomorKamar(txtNomor.getText());
            km.setTipe(txtTipe.getText());
            km.setHarga(Double.parseDouble(txtHarga.getText()));
            km.setStatus(cmbStatus.getSelectedItem().toString());

            km.save();
            txtIdKamar.setText(String.valueOf(km.getIdKamar()));
            tampilkanData();
        });

        btnHapus.addActionListener(e -> {
            Kamar km = new Kamar();
            km.setIdKamar(Integer.parseInt(txtIdKamar.getText()));
            km.delete();
            kosongkanForm();
            tampilkanData();
        });

        tblKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblKamar.getSelectedRow();
                txtIdKamar.setText(tblKamar.getValueAt(row, 0).toString());
                txtNomor.setText(tblKamar.getValueAt(row, 1).toString());
                txtTipe.setText(tblKamar.getValueAt(row, 2).toString());
                txtHarga.setText(tblKamar.getValueAt(row, 3).toString());
                cmbStatus.setSelectedItem(tblKamar.getValueAt(row, 4).toString());
            }
        });
    }

    private JTextField txtIdKamar, txtNomor, txtTipe, txtHarga;
    private JComboBox<String> cmbStatus;
    private JButton btnSimpan, btnTambahBaru, btnHapus;
    private JTable tblKamar;

    public static void main(String[] args) {
        new FrmKamar().setVisible(true);
    }
}