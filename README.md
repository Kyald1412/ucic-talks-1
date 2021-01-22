## PHP CRUD API
* `GET - http://api.yourwish.id/api/hari.php` Fetch ALL `hari` Records
* `GET - http://api.yourwish.id/api/matkul.php` Fetch ALL `matkul` Records
* `GET - api.yourwish.id/api/matkul_by_hari.php?id_hari=1` Fetch Single `matkul` Record
* `POST - api.yourwish.id/api/delete_matkul.php` Remove `matkul` Records
```
    id : 1

```
* `POST - http://api.yourwish.id/api/create_matkul.php` Create `matkul` Record
```
    matkul : Logika Algoritma Dasar Banget
    id_hari : 1

```

* `POST - http://api.yourwish.id/api/update_matkul.php` Update `matkul` Record
```
    id : 1
    matkul : Logika Algoritma
    id_hari : 1

```

* `POST - api.yourwish.id/api/login.php` Login
```
    username: admin,
    password : admin

```
