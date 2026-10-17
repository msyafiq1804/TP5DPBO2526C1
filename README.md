# JANJI
Saya Muhammad Syafiq A dengan NIM 2500254 mengerjakan TP 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

### Struktur File
```
│   .gitignore
│   PeopleData.iml
│
├───.idea
│       .gitignore
│       misc.xml
│       modules.xml
│       uiDesigner.xml
│       vcs.xml
│       workspace.xml
│
├───lib
│       mysql-connector-j-26.7.0.jar
│
├───out
│   └───production
│       └───PeopleData
│           │   Database.class
│           │   PeopleMenu$1.class
│           │   PeopleMenu$2.class
│           │   PeopleMenu$3.class
│           │   PeopleMenu$4.class
│           │   PeopleMenu$5.class
│           │   PeopleMenu.class
│           │   Person.class
│           │
│           └───com
│               └───intellij
│                   └───uiDesigner
│                       └───core
│                               AbstractLayout.class
│                               DimensionInfo.class
│                               GridConstraints.class
│                               GridLayoutManager.class
│                               HorizontalInfo.class
│                               LayoutState.class
│                               Spacer.class
│                               SupportCode$TextWithMnemonic.class
│                               SupportCode.class
│                               Util.class
│                               VerticalInfo.class
│
└───src
        Database.java
        PeopleMenu.form
        PeopleMenu.java
        Person.java
```

### Deskripsi Program
Aplikasi ini adalah sistem pengelolaan data pasien berbasis Java Swing yang sudah terhubung langsung ke database MySQL (db_people).Sistem penyimpanan data yang dulunya masih sementara menggunakan ArrayList sudah diganti total memakai database MySQL. Hasilnya, seluruh data yang diinput lewat tampilan aplikasi akan tersimpan secara permanen (persistent) dan bisa diolah secara real-time menggunakan query SQL.

Aplikasi mendukung operasi CRUD (Create, Read, Update, Delete) lengkap beserta sistem validasi mandiri untuk mencegah kesalahan input pengguna, seperti penolakan formulir kosong, pencegahan duplikasi ID pasien, serta pemeriksaan tipe data tahun lahir.

### Penjelasan Class
- Person.java

Kelas model yang merepresentasikan struktur entitas pasien. Kelas ini berfungsi sebagai penampung data (data holder) dengan atribut utama meliputi id (String), nama (String), tahunLahir (int), kategori (String), dan statusRawat (String).
- Database.java

Kelas pengelola koneksi antarmuka Java ke server MySQL menggunakan driver JDBC. Kelas ini menyediakan method selectQuery() untuk menjalankan perintah pembacaan data (SELECT) dan method insertUpdateDeleteQuery() untuk mengeksekusi perintah manipulasi data (INSERT, UPDATE, DELETE).
- PeopleMenu.java

Kelas utama yang mengatur seluruh komponen antarmuka grafis (GUI) Java Swing (PeopleMenu.form). Kelas ini bertanggung jawab menangani event listener pada komponen formulir, menyajikan data ke dalam JTable, mengeksekusi logika validasi input, serta menghubungkan aksi pengguna dengan kueri database.

### Penjelasan Alur
- Inisialisasi & Memuat Data Awal

Saat program pertama kali dijalankan, constructor PeopleMenu memicu instansiasi objek Database untuk membuka koneksi ke MySQL. Metode setTable() secara otomatis mengeksekusi kueri SELECT * FROM pasien dan menampilkan seluruh rekam data ke dalam komponen JTable.
- Penambahan Data (Create)

Pengguna mengisi seluruh kolom masukan pada formulir lalu menekan tombol Add. Program menjalankan metode insertData() untuk memeriksa kelengkapan isi formulir, memastikan tahun lahir berupa nilai numerik, dan mengecek ketersediaan ID pada database. Jika seluruh validasi terpenuhi, instruksi INSERT INTO dieksekusi, data tersimpan ke MySQL, formulir dibersihkan, dan JTable diperbarui.

- Pemilihan & Pembaruan Data (Update)

Ketika pengguna mengklik salah satu baris pada JTable, MouseListener akan mengambil nilai dari baris tersebut dan memasukkannya kembali ke dalam komponen formulir. Kotak isian ID secara otomatis dikunci (setEnabled(false)), tombol Add berganti fungsi menjadi Update, dan tombol Delete dimunculkan. Setelah pengguna mengubah isian data dan menekan tombol Update, metode updateData() mengeksekusi kueri UPDATE pasien SET ... WHERE id = ....

- Penghapusan Data (Delete)

Saat baris data dipilih, pengguna dapat menekan tombol Delete. Program memicu metode deleteData() yang memunculkan dialog konfirmasi (JOptionPane). Jika pengguna mengonfirmasi pilihan, perintah DELETE FROM pasien WHERE id = ... dijalankan untuk menghapus baris terkait dari database.

- Reset Formulir (Cancel)

Pengguna dapat menekan tombol Cancel kapan saja untuk memanggil metode clearForm(). Fungsi ini mengosongkan seluruh kolom masukan, mengembalikan status tombol utama menjadi Add, menyembunyikan tombol Delete, serta membuka kembali kuncian pada kolom ID.

### Komponen Antarmuka
Antarmuka dirancang menggunakan Swing GUI Designer melalui file PeopleMenu.form dengan memanfaatkan komponen-komponen berikut:
- JLabel: Menyajikan judul aplikasi serta label penanda pada tiap-tiap input form.
- JTextField: Menerima input teks untuk ID, nama lengkap, dan tahun lahir.
- JComboBox: Menyediakan menu pilihan dropdown untuk kategori penjaminan pasien.
- JRadioButton: Menyediakan opsi pilihan tunggal untuk menentukan status perawatan pasien.
- JButton: Menggerakkan eksekusi aksi seperti penambahan/pembaruan data, pembatalan, dan penghapusan data.
- JTable: Menampilkan daftar rekam medis pasien dari basis data secara terstruktur.
- JScrollPane: Membungkus tabel agar daftar data dapat digulir saat jumlah record bertambah.

## Dokumentasi
### Tampilan data awal


<img width="978" height="730" alt="Tampilan data awal" src="https://github.com/user-attachments/assets/1b89a8bb-aba9-462e-9675-499b07656f94" />


### Tambah Data

<img width="985" height="737" alt="tambah data" src="https://github.com/user-attachments/assets/ebb9fc7a-9898-408f-b84c-675cb7a7ed0e" />


### Update Data

<img width="980" height="737" alt="update data" src="https://github.com/user-attachments/assets/5460dff6-a778-4423-8ebc-bcc07ccd79a4" />


### Hapus Data 

<img width="972" height="733" alt="Hapus Data 1" src="https://github.com/user-attachments/assets/5f957635-14d8-4d97-8c64-19e51abb38bf" />

<img width="978" height="732" alt="Hapus Data 2" src="https://github.com/user-attachments/assets/2a00b284-5109-45ae-aaa6-c7b58b324de6" />



### Error

<img width="975" height="740" alt="Error" src="https://github.com/user-attachments/assets/1f6917b1-f589-4bcc-8f87-e43d45d9200b" />
<img width="985" height="737" alt="Error ID" src="https://github.com/user-attachments/assets/1ca79e65-17ad-447e-8474-5de0157b8db0" />



