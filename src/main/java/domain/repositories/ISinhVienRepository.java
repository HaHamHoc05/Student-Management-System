package domain.repositories;

import domain.models.SinhVien;

import java.util.List;

public interface ISinhVienRepository {
    void save(SinhVien sv);
    SinhVien findById(String masv);
    List<SinhVien> findAll();
    void deleteById(String masv);
}
