## PHP CRUD API
* `GET - http://localhost/api/hari.php` Fetch ALL `hari` Records
* `GET - http://localhost/api/matkul.php` Fetch ALL `matkul` Records
* `GET - localhost/api/matkul_by_hari.php?id_hari=1` Fetch Single `matkul` Record
* `DELETE - localhost/api/delete_matkul.php` Remove `matkul` Records

* `POST - http://localhost/api/create_matkul.php` Create `matkul` Record
```
{
    "matkul" : "Logika Algoritma Dasar Banget",
    "id_hari": 1
}
```

* `POST - http://localhost/api/update_matkul.php` Update `matkul` Record
```
{
    "id": 1,
    "matkul" : "Logika Algoritma",
    "id_hari": 1
}
```

* `POST - localhost/api/login.php` Login
```
{
    "username": "admin",
    "password": "admin"
}
```
